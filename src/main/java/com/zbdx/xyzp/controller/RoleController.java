package com.zbdx.xyzp.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zbdx.xyzp.model.dto.RoleDTO;
import com.zbdx.xyzp.model.dto.UserDTO;
import com.zbdx.xyzp.model.entity.Role;
import com.zbdx.xyzp.model.entity.User;
import com.zbdx.xyzp.service.RoleService;
import com.zbdx.xyzp.util.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
@CrossOrigin("http://localhost:8081")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @CrossOrigin
    @ApiOperation("查询所有角色")
    @GetMapping("/getRole")
    public Result getRole(RoleDTO roleDTO) {
        return Result.success(roleService.getRole(roleDTO));

    }

    @ApiOperation("分页查询角色信息")
    @GetMapping("/pageRole")
    public Result pageRole(Page<RoleDTO> page, RoleDTO roleDTO) {
        return Result.success(roleService.pageRole(page, roleDTO));

    }

    @ApiOperation("新增角色")
    @PostMapping("/addRole")
    public Result addRole(@RequestBody Role role) {
        return Result.success(roleService.save(role));
    }

    @ApiOperation("根据id更新角色")
    @PostMapping("/updateRole")
    public Result updateRole(@RequestBody Role role) {
        return Result.success(roleService.updateById(role));
    }

    @ApiOperation("根据id删除角色")
    @GetMapping("/deleteRole")
    public Result deleteRole(int roleId) {
        return Result.success(roleService.removeById(roleId));
    }

    @ApiOperation("获取角色类型")
    @GetMapping("/getRoleType")
    public Result getRoleType() {
        return Result.success(roleService.getRoleType());
    }


}
