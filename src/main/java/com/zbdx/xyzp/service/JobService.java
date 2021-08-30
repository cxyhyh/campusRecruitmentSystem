package com.zbdx.xyzp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zbdx.xyzp.model.dto.JobDTO;
import com.zbdx.xyzp.model.entity.Job;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public interface JobService extends IService<Job> {

    List<JobDTO> getJob();

    Page<JobDTO> pageJob(Page<JobDTO> page, JobDTO jobDTO);

    List<Map<String , Object>> getJobType();

    List<Map<String , Object>> getWorkPosition();

    List<Map<String , Object>> getEducationRequirement();

    List<JobDTO> jobAndCompanyDetailByjobName(String jobName);

    XSSFWorkbook exportJob(Map<String, Object> param);

    Map<String, Object> importJob(List<Map<String, Object>> list);

    List<Map<String,Object>> getJobTypeNum();

    List<Map<String,Object>> getWorkPositionNum();
}
