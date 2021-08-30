package com.zbdx.xyzp.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zbdx.xyzp.mapper.JobMapper;
import com.zbdx.xyzp.model.dto.JobDTO;
import com.zbdx.xyzp.model.entity.Job;
import com.zbdx.xyzp.service.JobService;
import com.zbdx.xyzp.service.UserService;
import com.zbdx.xyzp.util.CityUtils;
import com.zbdx.xyzp.util.DateTimeUtils;
import com.zbdx.xyzp.util.ValidUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.*;

@Slf4j
@Service
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements JobService {

    @Autowired
    private JobMapper jobMapper;
    @Autowired
    private UserService userService;

    @Override
    public List<JobDTO> getJob() {
        return jobMapper.getJob();
    }

    @Override
    public Page<JobDTO> pageJob(Page<JobDTO> page, JobDTO jobDTO) {
        return jobMapper.pageJob(page, jobDTO);
    }

    @Override
    public List<Map<String, Object>> getJobType() {
        return jobMapper.getJobType();
    }

    @Override
    public List<Map<String, Object>> getWorkPosition() {
        return jobMapper.getWorkPosition();
    }

    @Override
    public List<Map<String, Object>> getEducationRequirement() {
        return jobMapper.getEducationRequirement();
    }

    @Override
    public List<JobDTO> jobAndCompanyDetailByjobName(String jobName) {
        return jobMapper.jobAndCompanyDetailByjobName(jobName);
    }

    @Override
    public XSSFWorkbook exportJob(Map<String, Object> param) {
        XSSFWorkbook wb = null;
        try {
            wb = userService.copyTemplate("模板-职位.xlsx");
            Sheet sheet = wb.getSheetAt(0);
            List<JobDTO> stList = this.getList(param);
            if (stList != null && stList.size() > 0) {
                Integer lastRowNum = 1;
                for (int i = 0; i < stList.size(); i++) {
                    JobDTO obj = stList.get(i);
                    Integer index = lastRowNum + i;
                    log.info("index = {} ", index);
                    Row row = sheet.createRow(index);
                    int cellIndex = 0;
                    row.createCell(cellIndex++).setCellValue(obj.getJobName());
                    row.createCell(cellIndex++).setCellValue(obj.getJobType());
                    row.createCell(cellIndex++).setCellValue(obj.getEducationRequirement());
                    row.createCell(cellIndex++).setCellValue(obj.getSexRequirement());
                    row.createCell(cellIndex++).setCellValue(obj.getAgeRequirement());
                    row.createCell(cellIndex++).setCellValue(obj.getWorkTimeRequirement());
                    row.createCell(cellIndex++).setCellValue(obj.getWorkPosition());
                    row.createCell(cellIndex++).setCellValue(obj.getRecruitNum());
                    row.createCell(cellIndex++).setCellValue(obj.getSpecificRequirement());

                    row.createCell(cellIndex++).setCellValue(DateTimeUtils.DateToStr(obj.getCreateTime()));
                    row.createCell(cellIndex++).setCellValue(obj.getSalary());
                    row.createCell(cellIndex++).setCellValue(obj.getBelongCompany());
                }
            }
            return wb;
        } catch (Exception e) {
            log.error("导出职位信息报错：{}", e);
            return wb;
        }
    }

    private List<JobDTO> getList(Map<String, Object> param) {
        List<JobDTO> records = this.baseMapper.getList(param);
        return records;
    }

    @Override
    public Map<String, Object> importJob(List<Map<String, Object>> list) {
        Map<String, Object> map = new HashMap<String, Object>();
        //校验数据
        List<String> error = Lists.newArrayList();
        StringBuffer message = null;
        for (int i = 0; i < list.size(); i++) {
            message = new StringBuffer();
            message.append(i + "#");
            Map<String, Object> param = list.get(i);
            log.info("第{}条,内容：{}", i + 1, JSONObject.toJSONString(param));

            message = ValidUtils.judgeKongParam(message, param, "职位名称", "jobName");
            message = ValidUtils.judgeKongParam(message, param, "职位类型", "jobType");
            message = ValidUtils.judgeKongParam(message, param, "学历要求", "educationRequirement");
            message = ValidUtils.judgeKongParam(message, param, "薪资", "salary");
            message = ValidUtils.judgeKongParam(message, param, "发布时间", "createTime");
            message = ValidUtils.judgeDateParam(message, param, "发布时间", "createTime");
            message = ValidUtils.judgeKongParam(message, param, "所属公司", "belongCompany");

            if (!StringUtils.isEmpty(param.get("jobName"))){
                String isExistJob = this.jobMapper.getJobNameByBelongCompanyAndJobName(param.get("jobName").toString(),param.get("belongCompany").toString());
                if (!StringUtils.isEmpty(isExistJob)){
                    message.append("@jobName:"+param.get("belongCompany")+"下已有该职位"+param.get("jobName"));
                }
            }
            if (message.toString().contains("@")) {
                error.add(message.toString());
            }

        }
        if (!error.isEmpty()) {
            map.put("code",400);
            map.put("status", "fail");
            map.put("message", error);
            return map;
        }
        List<Job> resultList = JSON.parseArray(JSON.toJSONString(list), Job.class);
        return importBatchJob(resultList);
    }

    @Override
    public List<Map<String, Object>> getJobTypeNum() {
        List<Map<String,Object>> jobTypeMap = this.jobMapper.getJobType();

        List<Map<String,Object>> list = new ArrayList<>();

        for (Map<String,Object> param:jobTypeMap) {

            Map<String,Object> map = Maps.newHashMap();
            Integer result = this.jobMapper.getNumByJobType(param.get("jobType").toString());
            map.put("name", param.get("jobType").toString());
            map.put("value", result);
            list.add(map);
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> getWorkPositionNum() {

        List<Map<String,Object>> workPositionMap = this.jobMapper.getWorkPosition();
        List<Map<String,Object>> list = new ArrayList<>();

        for (Map<String,Object> param:workPositionMap) {
            Map<String,Object> map = Maps.newHashMap();
            Integer result = this.jobMapper.getNumByWorkPosition(param.get("workPosition").toString());
            String name = CityUtils.findObjectProvince(param.get("workPosition").toString());
            map.put("name", name);
            map.put("value", result);
            list.add(map);
        }

        return list;
    }

    private Map<String, Object> importBatchJob(List<Job> list) {
        Map<String, Object> retMap = Maps.newHashMap();
        StringBuffer ret = new StringBuffer("职位信息批量入库情况：");
            try {
                for (Job job : list) {
                    boolean save = this.save(job);
                    if (save) {
                        retMap.put("result", list);
                        retMap.put("code",200);
                        retMap.put("status", "success");
                    } else {
                        retMap.put("result", save);
                        retMap.put("code",400);
                        retMap.put("status", "fail");
                        retMap.put("message", "批量入库失败");
                    }
                }
                return retMap;
            } catch (Exception e) {
                log.error("职位信息导入错误:{}", e);
                ret.append("职位信息导入错误;");
                retMap.put("status", "fail");
                retMap.put("message", ret.toString());
                return retMap;
            }

    }
}
