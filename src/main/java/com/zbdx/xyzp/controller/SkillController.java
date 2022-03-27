package com.zbdx.xyzp.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zbdx.xyzp.model.dto.SkillDTO;
import com.zbdx.xyzp.model.entity.Skill;
import com.zbdx.xyzp.service.SkillService;
import com.zbdx.xyzp.util.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
@RestController
@RequestMapping("/skill")
@CrossOrigin("http://121.43.158.100:8080")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @CrossOrigin
    @ApiOperation("新增技能")
    @PostMapping("/addSkill")
    public Result addSkill(@RequestBody Skill skill){
        return Result.success(skillService.save(skill));
    }

    @ApiOperation("根据id更新技能")
    @PostMapping("/updateSkill")
    public Result updateSkill(@RequestBody Skill skill){
        return Result.success(skillService.updateById(skill));
    }

    @ApiOperation("根据id删除技能")
    @GetMapping("/deleteSkill")
    public Result deleteSkill(int skillId){
        return Result.success(skillService.removeById(skillId));
    }

    @ApiOperation("查询所有技能")
    @GetMapping("/getSkill")
    public Result getSkill(){
        return Result.success(skillService.getSkill());
    }

    @ApiOperation("分页查询技能信息")
    @GetMapping("/pageSkill")
    public Result pageSkill(Page<SkillDTO> page , SkillDTO skillDTO){
        return Result.success(skillService.pageSkill(page, skillDTO));
    }

    @ApiOperation("获取英语水平能力")
    @GetMapping("/getEnglishSkill")
    public Result getEnglishSkill(){
        return Result.success(skillService.getEnglishSkill());
    }

    @ApiOperation("获取计算机能力")
    @GetMapping("/getComputerSkill")
    public Result getComputerSkill(){
        return Result.success(skillService.getComputerSkill());
    }

    @ApiOperation("获取求职位置")
    @GetMapping("/getAppliedPosition")
    public Result getAppliedPosition(){
        return Result.success(skillService.getAppliedPosition());
    }
}
