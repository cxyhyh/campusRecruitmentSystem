package com.zbdx.xyzp.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zbdx.xyzp.model.dto.CompanyDTO;
import com.zbdx.xyzp.model.dto.UserDTO;
import com.zbdx.xyzp.model.entity.Company;
import com.zbdx.xyzp.model.entity.Job;
import com.zbdx.xyzp.service.CompanyService;
import com.zbdx.xyzp.util.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

}
