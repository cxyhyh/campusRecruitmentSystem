package com.zbdx.xyzp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zbdx.xyzp.model.dto.RoleDTO;
import com.zbdx.xyzp.model.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface RoleMapper extends BaseMapper<Role> {

    List<RoleDTO> getRole(RoleDTO roleDTO);

    Page<RoleDTO> pageRole(Page<RoleDTO> page, @Param("param") RoleDTO roleDTO);

    List<Map<String , Object>> getRoleType();
}
