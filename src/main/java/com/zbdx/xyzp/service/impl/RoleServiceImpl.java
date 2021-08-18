package com.zbdx.xyzp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zbdx.xyzp.mapper.RoleMapper;
import com.zbdx.xyzp.model.dto.RoleDTO;
import com.zbdx.xyzp.model.entity.Role;
import com.zbdx.xyzp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service

public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;


    @Override
    public List<RoleDTO> getRole(RoleDTO roleDTO) {
        return roleMapper.getRole(roleDTO);
    }

    @Override
    public Page<RoleDTO> pageRole(Page<RoleDTO> page, RoleDTO roleDTO) {
        return roleMapper.pageRole(page, roleDTO);
    }

    @Override
    public List<Map<String, Object>> getRoleType() {
        return roleMapper.getRoleType();
    }


}
