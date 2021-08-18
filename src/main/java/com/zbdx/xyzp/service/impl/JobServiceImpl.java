package com.zbdx.xyzp.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zbdx.xyzp.mapper.JobMapper;
import com.zbdx.xyzp.model.dto.JobDTO;
import com.zbdx.xyzp.model.dto.UserDTO;
import com.zbdx.xyzp.model.entity.Job;
import com.zbdx.xyzp.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements JobService {

    @Autowired
    private JobMapper jobMapper;

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
}
