package com.zbdx.xyzp.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zbdx.xyzp.model.dto.CompanyDTO;
import com.zbdx.xyzp.model.dto.JobDTO;
import com.zbdx.xyzp.model.dto.UserDTO;
import com.zbdx.xyzp.model.entity.Job;
import com.zbdx.xyzp.model.entity.User;
import com.zbdx.xyzp.service.JobService;
import com.zbdx.xyzp.util.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/job")
@CrossOrigin("http://localhost:8081")
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

}
