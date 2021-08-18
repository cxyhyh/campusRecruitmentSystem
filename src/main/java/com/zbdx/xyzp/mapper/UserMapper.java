package com.zbdx.xyzp.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zbdx.xyzp.model.dto.UserDTO;
import com.zbdx.xyzp.model.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper extends BaseMapper<User> {

    List<UserDTO> getUser(UserDTO userDTO);

    List<UserDTO> selectByName(String username);

    List<UserDTO> selectByPassword(String password);

    Page<UserDTO> pageUser(Page<UserDTO> page, @Param("param") UserDTO userDTO);

    List<Map<String , Object>> getUsername();

    List<Map<String , Object>> getUserRoleType();

    List<Map<String , Object>> getRealName();

    List<Map<String , Object>> getEducation();

    Map<String, Object> selectRoleTypeByName(String username);

    Page<UserDTO> getUserAndSkill(Page<UserDTO> page, @Param("param") UserDTO userDTO);

    List<Map<String, Object>> getRealNamePuTong();

    List<UserDTO> getList(Map<String, Object> param);
}
