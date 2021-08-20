package com.zbdx.xyzp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zbdx.xyzp.model.dto.JobDTO;
import com.zbdx.xyzp.model.dto.UserDTO;
import com.zbdx.xyzp.model.entity.Job;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface JobMapper extends BaseMapper<Job> {

    List<JobDTO> getJob();

    Page<JobDTO> pageJob(Page<JobDTO> page, @Param("param") JobDTO jobDTO);

    List<Map<String , Object>> getJobType();

    List<Map<String , Object>> getWorkPosition();

    List<Map<String , Object>> getEducationRequirement();

    List<JobDTO> jobAndCompanyDetailByjobName(String jobName);

    List<JobDTO> getList(Map<String, Object> param);

    String getJobNameByBelongCompanyAndJobName(String jobName,String belongCompany);
}
