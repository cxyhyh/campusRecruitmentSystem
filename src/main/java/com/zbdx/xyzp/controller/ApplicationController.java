package com.zbdx.xyzp.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zbdx.xyzp.model.dto.ApplicationDTO;
import com.zbdx.xyzp.model.dto.JianLiDTO;
import com.zbdx.xyzp.service.ApplicationService;
import com.zbdx.xyzp.util.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/application")
@CrossOrigin("http://121.43.158.100:8081")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @ApiOperation("分页查询求职申请")
    @GetMapping("/pageApplication")
    public Result pageApplication(Page<ApplicationDTO> page , ApplicationDTO applicationDTO){
        return Result.success(applicationService.pageApplication(page ,applicationDTO));

    }

    @GetMapping("/getApplication")
    public Result getApplication(){
        return Result.success(applicationService.getApplication());
    }

    @ApiOperation("申请职位")
    @GetMapping("/apply")
    public Result apply(String username,String applicationPosition){
        return Result.success(applicationService.apply(username ,applicationPosition));
    }

    @GetMapping("/selectUsername")
    public Result selectUsername(String username){
        return Result.success(applicationService.selectUsername(username));
    }

    @ApiOperation("根据id删除")
    @GetMapping("/deleteApplication")
    public Result deleteApplication(int applicationId){
        return Result.success(applicationService.removeById(applicationId));
    }

    @GetMapping("/tongYi")
    public Result tongYi(String username){
        return applicationService.tongYi(username);
    }

    @GetMapping("/buTongYi")
    public Result buTongYi(String username){
        return applicationService.buTongYi(username);
    }

    @GetMapping("/addApplication")
    public Result addApplication(String username ,String applicationPosition,String status, String roleType){
        return Result.success(applicationService.addApplication(username, applicationPosition, status, roleType ));
    }

    @ApiOperation("获取申请职位")
    @GetMapping("/getApplicationPositionForA")
    public Result getApplicationPositionForA(){
        return Result.success(applicationService.getApplicationPositionForA());
    }

    @ApiOperation("获取申请职位审核状态")
    @GetMapping("/getStatus")
    public Result getStatus(){
        return Result.success(applicationService.getStatus());
    }

    @GetMapping("/tongGuo")
    public Result tongGuo(String username, String applicationPosition){
        return applicationService.tongGuo(username , applicationPosition);
    }

    @GetMapping("/buTongGuo")
    public Result buTongGuo(String username, String applicationPosition){
        return applicationService.buTongGuo(username , applicationPosition);
    }

    @ApiOperation("通过用户名和申请位置获取申请职位信息")
    @GetMapping("/getUsernameAndApplicationPosition")
    public Result getUsernameAndApplicationPosition(String username, String applicationPosition){
        return Result.success(applicationService.getUsernameAndApplicationPosition(username , applicationPosition));
    }
}
