package com.zbdx.xyzp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zbdx.xyzp.model.dto.UserDTO;
import com.zbdx.xyzp.model.entity.User;
import com.zbdx.xyzp.util.Result;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Service
public interface UserService extends IService<User> {

    List<UserDTO> getUser(UserDTO userDTO);

    List<UserDTO> selectByName(String username);

    Result login(String username, String password);

    List<UserDTO> selectByPassword(String username,String password);

    Page<UserDTO> pageUser(Page<UserDTO> page, UserDTO userDTO);

    List<Map<String , Object>> getUsername();

    List<Map<String , Object>> getUserRoleType();

    List<Map<String , Object>> getRealName();

    List<Map<String , Object>> getEducation();

    Map<String , Object> selectRoleTypeByName(String username);

    Page<UserDTO> getUserAndSkill(Page<UserDTO> page, UserDTO userDTO);

    List<Map<String , Object>> getRealNamePuTong();

    Map<String, Object> importUser(List<Map<String, Object>> list);

    XSSFWorkbook exportUser(Map<String, Object> param);

    XSSFWorkbook copyTemplate(String resourceName);

    List<UserDTO> editUserByUsername(String username);

    List<Map<String,Object>> getRoleTypeNum();

    void exportUserToWord(String username,HttpServletRequest request, HttpServletResponse response);

    User selectByUsername(String username);

    String selectPhoto(String username);
}
