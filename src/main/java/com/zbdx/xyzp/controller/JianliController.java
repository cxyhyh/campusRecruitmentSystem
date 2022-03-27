package com.zbdx.xyzp.controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zbdx.xyzp.model.dto.JianLiDTO;
import com.zbdx.xyzp.model.entity.JianLi;
import com.zbdx.xyzp.service.JianliService;
import com.zbdx.xyzp.util.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/jianli")
@CrossOrigin("http://121.43.158.100:8081")
public class JianliController {

    @Autowired
    private JianliService jianliService;

    @ApiOperation("查询所有简历")
    @GetMapping("/getJianli")
    public Result getJianli(JianLiDTO jianLiDTO){

        return Result.success(jianliService.getJianli(jianLiDTO));
    }

    @ApiOperation("分页查询简历")
    @GetMapping("/pageJianLi")
    public Result pageJianLi(Page<JianLiDTO> page , JianLiDTO jianLiDTO){
        return Result.success(jianliService.pageJianLi(page ,jianLiDTO));

    }

    @ApiOperation("新增简历")
    @PostMapping("/addJianli")
    public Result addJianli(@RequestBody JianLi jianLi){
        return Result.success(jianliService.save(jianLi));
    }

    @ApiOperation("更新用户名通过最新的文件名")
    @GetMapping("/updateUsernameById")
    public Result updateUsernameById(String fileName , String username, String applicationPosition){

        return Result.success(jianliService.updateUsernameById(fileName,username ,applicationPosition));
    }

    @ApiOperation("获取最新的文件名")
    @GetMapping("/getMaxJianliId")
    public Result getMaxJianliId(String fileName){
        return Result.success(jianliService.getMaxJianliId(fileName));
    }

    @ApiOperation("获取最新的文件名")
    @GetMapping("/getNewFileNameByUsername")
    public Result getNewFileNameByUsername(String username){
        return Result.success(jianliService.getNewFileNameByUsername(username));
    }

    @ApiOperation("根据id删除简历")
    @GetMapping("/deleteJianLi")
    public Result deleteJianLi(int jianliId){
        return Result.success(jianliService.removeById(jianliId));
    }

    @ApiOperation("获取用户名")
    @GetMapping("/getUserName")
    public Result getUserName(){
        return Result.success(jianliService.getUserName());
    }

    @ApiOperation("获取申请职位")
    @GetMapping("/getApplicationPosition")
    public Result getApplicationPosition(){
        return Result.success(jianliService.getApplicationPosition());
    }
}
