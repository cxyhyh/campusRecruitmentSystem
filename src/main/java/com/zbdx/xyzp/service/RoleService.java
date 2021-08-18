package com.zbdx.xyzp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zbdx.xyzp.model.dto.RoleDTO;
import com.zbdx.xyzp.model.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface RoleService extends IService<Role> {

    List<RoleDTO> getRole(RoleDTO roleDTO);

    Page<RoleDTO> pageRole(Page<RoleDTO> page, RoleDTO roleDTO);

    List<Map<String , Object>> getRoleType();

}
