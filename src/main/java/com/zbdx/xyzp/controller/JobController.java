package com.zbdx.xyzp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Maps;
import com.zbdx.xyzp.model.dto.JobDTO;
import com.zbdx.xyzp.model.entity.Job;
import com.zbdx.xyzp.service.JobService;
import com.zbdx.xyzp.util.DateTimeUtils;
import com.zbdx.xyzp.util.ExcelTool;
import com.zbdx.xyzp.util.Result;
import com.zhxd.common.web.Response;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;
import java.util.Map;
@Slf4j
@RestController
@RequestMapping("/job")
@CrossOrigin("http://121.43.158.100:8081")
public class JobController {

    @Autowired
    private JobService jobService;

    @CrossOrigin
    @ApiOperation("查询所有职位")
    @GetMapping("/getJob")
    public Result getJob() {
        return Result.success(jobService.getJob());
    }

    @ApiOperation("分页查询职位信息")
    @GetMapping("/pageJob")
    public Result pageJob(Page<JobDTO> page, JobDTO jobDTO) {
        return Result.success(jobService.pageJob(page, jobDTO));
    }

    @ApiOperation("根据职位名称查询职位和公司详细信息")
    @GetMapping("/jobAndCompanyDetailByjobName")
    public Result jobAndCompanyDetailByjobName(String jobName) {
        return Result.success(jobService.jobAndCompanyDetailByjobName(jobName));
    }

    @ApiOperation("新增职位")
    @PostMapping("/addJob")
    public Result addJob(@RequestBody Job job) {
        return Result.success(jobService.save(job));
    }

    @ApiOperation("根据i更新职位")
    @PostMapping("/updateJob")
    public Result updateJob(@RequestBody Job job) {
        return Result.success(jobService.updateById(job));
    }

    @ApiOperation("根据id删除职位")
    @GetMapping("/deleteJob")
    public Result deleteJob(int jobId) {
        return Result.success(jobService.removeById(jobId));
    }

    @ApiOperation("获取职位类型")
    @GetMapping("/getJobType")
    public Result getJobType() {
        return Result.success(jobService.getJobType());
    }

    @ApiOperation("获取工作地点")
    @GetMapping("/getWorkPosition")
    public Result getWorkPosition() {
        return Result.success(jobService.getWorkPosition());
    }

    @ApiOperation("获取学历要求")
    @GetMapping("/getEducationRequirement")
    public Result getEducationRequirement() {
        return Result.success(jobService.getEducationRequirement());
    }

    @ApiOperation("根据不同职位类型获取数量")
    @GetMapping("/getJobTypeNum")
    public Result getJobTypeNum(){
        return Result.success(jobService.getJobTypeNum());
    }

    @ApiOperation("根据工作地点获取数量")
    @GetMapping("/getWorkPositionNum")
    public Result getWorkPositionNum(){
        return Result.success(jobService.getWorkPositionNum());
    }

    @ApiOperation("导入职位信息")
    @PostMapping("/importJob")
    public Result importJob(@RequestBody Map<String, Object> param) {
        String logStr = "importJob";
        Map<String, Object> retMap = Maps.newHashMap();
        try {
            log.info("导入职位信息：{}", JSONObject.toJSONString(param));
            if (param.containsKey("list")) {
                log.error("{} 开始：", logStr);
                List<Map<String, Object>> list = (List<Map<String, Object>>) param.get("list");
                retMap = jobService.importJob(list);
                return Result.success(retMap);
            } else {
                return Result.fail("未包含必要的参数");
            }
        } catch (Exception e) {
            log.error("{} 报错：{}", logStr, e);
            return Result.fail("所选excel文件数据不规范！！！");
        }
    }

    @ApiOperation(value = "下载职位模板" ,httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/exportDefaultTemplate")
    public void exportDefaultTemplate(HttpServletResponse response
            , @NotBlank(message = "模板名称不能为空") String moduleNameCn){

        OutputStream out = null;
        InputStream in = null;
        InputStream inNew = null;
        File fi = null;
        try {
            out = response.getOutputStream();
            String fileName = URLEncoder.encode(moduleNameCn, "UTF-8");
            response.setHeader("content-disposition", "attachment;filename=" + fileName);
            try {
                ResourceLoader resourceLoader = new DefaultResourceLoader();
                String realPath = "classpath:template/static/excel/" + moduleNameCn;
                in = resourceLoader.getResource(realPath).getInputStream();
                String tmpFileName = "/tmp/files/" + DateTimeUtils.stamp(new Date())
                        + File.separator + fileName.replace("模板-", "");
                log.info("模板导出路径：{}", tmpFileName);
                fi = new File(tmpFileName);
                FileUtils.copyInputStreamToFile(in, fi);
                in.close();
                inNew = new FileInputStream(fi);
            } catch (IOException e) {
                log.error("模板导出文件出错{}", e);
            }
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inNew.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            inNew.close();
        } catch (Exception e) {
            log.error("模板导出操作异常{}", e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (inNew != null) {
                    inNew.close();
                }
                if (out != null) {
                    out.close();
                }
                Files.delete(fi.toPath());
                if (fi.exists()) {
                    fi.delete();
                }
            } catch (IOException e) {
                log.error("模板导出关闭IO异常:{}", e);
            }
        }
    }

    @ApiOperation(value = "导出职位信息", httpMethod = "GET")
    @GetMapping("/exportJob")
    public Response exportJob(@RequestParam Map<String, Object> param,
                               HttpServletResponse response) {
        String logStr = "职位信息导出";
        log.info("接受到参数信息{}", JSON.toJSONString(param));
        try {
            String fileNames = URLEncoder.encode("职位信息.xlsx", "UTF-8");
            response.setHeader("content-disposition", "attachment;filename=" + fileNames);
            ExcelTool.setResponseHeader(response, fileNames);
            OutputStream os = response.getOutputStream();
            XSSFWorkbook xssfWorkbook = jobService.exportJob(param);
            if (xssfWorkbook == null) {
                return Response.failure(logStr + "报错");
            }
            xssfWorkbook.write(os);
            os.flush();
            os.close();
            return null;
        } catch (Exception e) {
            log.error("{}报错,{}", logStr, e);
            return Response.failure("{}报错", logStr);
        }

    }
}
