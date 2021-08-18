package com.zbdx.xyzp.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Maps;
import com.zbdx.xyzp.model.dto.UserDTO;
import com.zbdx.xyzp.model.entity.User;
import com.zbdx.xyzp.service.UserService;
import com.zbdx.xyzp.util.DateTimeUtils;
import com.zbdx.xyzp.util.ExcelTool;
import com.zbdx.xyzp.util.Result;
import com.zhxd.common.web.Response;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
@CrossOrigin("http://localhost:8081")
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin
    @ApiOperation("用户登录")
    @GetMapping("/login")
    @ResponseBody
    public Result login(@NotNull(message = "用户名不能为空") String username,
                        @NotNull(message = "密码不能为空") String password){
        return userService.login(username , password);
    }

    @ApiOperation("根据用户名称查询用户类型")
    @GetMapping("/selectRoleTypeByName")
    public Result selectRoleTypeByName(String username){
        return Result.success(userService.selectRoleTypeByName(username));
    }

    @ApiOperation("根据用户名称查询")
    @GetMapping("/selectByName")
    public Result selectByName(String username){

        return Result.success(userService.selectByName(username));
    }

    @ApiOperation("根据密码名称查询")
    @GetMapping("/selectByPassword")
    public Result selectByPassword(String password){

        return Result.success(userService.selectByPassword(password));
    }

    @ApiOperation("新增用户")
    @PostMapping("/addUser")
    public Result addUser(@RequestBody User user){
        Date date = new Date();
        user.setRegistTime(date);
        return Result.success(userService.save(user));
    }

    @ApiOperation("查询所有用户")
    @GetMapping("/getUser")
    public Result getUser(UserDTO userDTO){

        return Result.success(userService.getUser(userDTO));
    }

    @ApiOperation("分页查询用户")
    @GetMapping("/pageUser")
    public Result pageUser(Page<UserDTO> page ,UserDTO userDTO){
        return Result.success(userService.pageUser(page ,userDTO));

    }

    @ApiOperation("根据id更新用户")
    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody User user){
        user.setRegistTime(new Date());
        return Result.success(userService.updateById(user));
    }

    @ApiOperation("根据id删除用户")
    @GetMapping("/deleteUser")
    public Result deleteUser(int id){
        return Result.success(userService.removeById(id));
    }

    @ApiOperation("获取用户名")
    @GetMapping("/getUsername")
    public Result getUsername(){
        return Result.success(userService.getUsername());
    }

    @ApiOperation("获取用户类型为普通用户的真实姓名")
    @GetMapping("/getRealNamePuTong")
    public Result getRealNamePuTong(){
        return Result.success(userService.getRealNamePuTong());
    }

    @ApiOperation("获取用户真实姓名")
    @GetMapping("/getRealName")
    public Result getRealName(){
        return Result.success(userService.getRealName());
    }

    @ApiOperation("获取用户类型")
    @GetMapping("/getUserRoleType")
    public Result getUserRoleType(){
        return Result.success(userService.getUserRoleType());
    }

    @ApiOperation("获取文化水平")
    @GetMapping("/getEducation")
    public Result getEducation(){
        return Result.success(userService.getEducation());
    }

    @ApiOperation("根据真实姓名查询用户类型为普通用户信息和技能信息")
    @GetMapping("/getUserAndSkill")
    public Result getUserAndSkill(
            Page<UserDTO> page ,UserDTO userDTO
    ){
        return Result.success(userService.getUserAndSkill(page, userDTO));
    }

    @ApiOperation("导入用户信息")
    @PostMapping("/importUser")
    public Response importUser(@RequestBody Map<String, Object> param) {
        String logStr = "importUser";
        Map<String, Object> retMap = Maps.newHashMap();
        try {
            log.info("导入用户信息：{}", JSONObject.toJSONString(param));
            if (param.containsKey("list")) {
                log.error("{} 开始：", logStr);
                List<Map<String, Object>> list = (List<Map<String, Object>>) param.get("list");
                retMap = userService.importUser(list);
                return Response.success(retMap);
            } else {
                return Response.failure("未包含必要的参数");
            }
        } catch (Exception e) {
            log.error("{} 报错：{}", logStr, e);
            return Response.failure("所选excel文件数据不规范！！！");
        }
    }

    @ApiOperation(value = "下载用户模板" ,httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/exportDefaultTemplate")
    public void exportDefaultTemplate(HttpServletResponse response
            , @NotBlank(message = "模板名称不能为空") String moduleNameCn){

        OutputStream out = null;
        InputStream in = null;
        InputStream inNew = null;
        File fi = null;
        try {
            out = response.getOutputStream();
            String fileName = URLEncoder.encode(moduleNameCn, "UTF-8");
            response.setHeader("content-disposition", "attachment;filename=" + fileName);
            try {
                ResourceLoader resourceLoader = new DefaultResourceLoader();
                String realPath = "classpath:template/static/excel/" + moduleNameCn;
                in = resourceLoader.getResource(realPath).getInputStream();
                String tmpFileName = "/tmp/files/" + DateTimeUtils.stamp(new Date())
                        + File.separator + fileName.replace("模板-", "");
                log.info("模板导出路径：{}", tmpFileName);
                fi = new File(tmpFileName);
                FileUtils.copyInputStreamToFile(in, fi);
                in.close();
                inNew = new FileInputStream(fi);
            } catch (IOException e) {
                log.error("模板导出文件出错{}", e);
            }
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inNew.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            inNew.close();
        } catch (Exception e) {
            log.error("模板导出操作异常{}", e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (inNew != null) {
                    inNew.close();
                }
                if (out != null) {
                    out.close();
                }
                Files.delete(fi.toPath());
                if (fi.exists()) {
                    fi.delete();
                }
            } catch (IOException e) {
                log.error("模板导出关闭IO异常:{}", e);
            }
        }
    }

    @ApiOperation(value = "导出用户信息", httpMethod = "GET")
    @GetMapping("/exportUser")
    public Response exportUser(@RequestParam Map<String, Object> param,
                               HttpServletResponse response) {
        String logStr = "用户基本信息导出";
        log.info("接受到参数信息{}", JSON.toJSONString(param));
        try {
            String fileNames = URLEncoder.encode("用户信息.xlsx", "UTF-8");
            response.setHeader("content-disposition", "attachment;filename=" + fileNames);
            ExcelTool.setResponseHeader(response, fileNames);
            OutputStream os = response.getOutputStream();
            XSSFWorkbook xssfWorkbook = userService.exportUser(param);
            if (xssfWorkbook == null) {
                return Response.failure(logStr + "报错");
            }
            xssfWorkbook.write(os);
            os.flush();
            os.close();
            return null;
        } catch (Exception e) {
            log.error("{}报错,{}", logStr, e);
            return Response.failure("{}报错", logStr);
        }

    }
}