package com.zbdx.xyzp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Maps;
import com.zbdx.xyzp.model.dto.CompanyDTO;
import com.zbdx.xyzp.model.dto.UserDTO;
import com.zbdx.xyzp.model.entity.Company;
import com.zbdx.xyzp.model.entity.Job;
import com.zbdx.xyzp.service.CompanyService;
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
@RequestMapping("/company")
@CrossOrigin("http://localhost:8081")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @CrossOrigin
    @ApiOperation("查询所有企业")
    @GetMapping("/getCompany")
    public Result getCompany() {
        return Result.success(companyService.getCompany());
    }

    @ApiOperation("分页查询企业信息")
    @GetMapping("/pageCompany")
    public Result pageCompany(Page<CompanyDTO> page, CompanyDTO companyDTO) {
        return Result.success(companyService.pageCompany(page, companyDTO));

    }

    @ApiOperation("新增企业")
    @PostMapping("/addCompany")
    public Result addCompany(@RequestBody Company company) {

        return Result.success(companyService.save(company));
    }

    @ApiOperation("根据id更新企业")
    @PostMapping("/updateCompany")
    public Result updateCompany(@RequestBody Company company) {
        return Result.success(companyService.updateById(company));
    }

    @ApiOperation("根据id删除企业")
    @GetMapping("/deleteCompany")
    public Result deleteCompany(int companyId) {
        return Result.success(companyService.removeById(companyId));

    }

    @ApiOperation("获取企业名称")
    @GetMapping("/getCompanyName")
    public Result getCompanyName() {
        return Result.success(companyService.getCompanyName());
    }

    @ApiOperation("获取企业类型")
    @GetMapping("/getCompanyType")
    public Result getCompanyType() {
        return Result.success(companyService.getCompanyType());
    }

    @ApiOperation("获取企业所在位置")
    @GetMapping("/getLocation")
    public Result getLocation() {
        return Result.success(companyService.getLocation());
    }

    @ApiOperation("导入企业信息")
    @PostMapping("/importCompany")
    public Result importCompany(@RequestBody Map<String, Object> param) {
        String logStr = "importCompany";
        Map<String, Object> retMap = Maps.newHashMap();
        try {
            log.info("导入企业信息：{}", JSONObject.toJSONString(param));
            if (param.containsKey("list")) {
                log.error("{} 开始：", logStr);
                List<Map<String, Object>> list = (List<Map<String, Object>>) param.get("list");
                retMap = companyService.importJob(list);
                return Result.success(retMap);
            } else {
                return Result.fail("未包含必要的参数");
            }
        } catch (Exception e) {
            log.error("{} 报错：{}", logStr, e);
            return Result.fail("所选excel文件数据不规范！！！");
        }
    }

    @ApiOperation(value = "下载企业模板" ,httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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
    @GetMapping("/exportCompany")
    public Response exportCompany(@RequestParam Map<String, Object> param,
                              HttpServletResponse response) {
        String logStr = "企业信息导出";
        log.info("接受到参数信息{}", JSON.toJSONString(param));
        try {
            String fileNames = URLEncoder.encode("企业信息.xlsx", "UTF-8");
            response.setHeader("content-disposition", "attachment;filename=" + fileNames);
            ExcelTool.setResponseHeader(response, fileNames);
            OutputStream os = response.getOutputStream();
            XSSFWorkbook xssfWorkbook = companyService.exportJob(param);
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
