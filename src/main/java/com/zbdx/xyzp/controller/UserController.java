package com.zbdx.xyzp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Maps;
import com.zbdx.xyzp.constant.Constant;
import com.zbdx.xyzp.mapper.UserMapper;
import com.zbdx.xyzp.model.dto.UserDTO;
import com.zbdx.xyzp.model.entity.*;
import com.zbdx.xyzp.service.SelfEvaluationService;
import com.zbdx.xyzp.service.SkillService;
import com.zbdx.xyzp.service.UserService;
import com.zbdx.xyzp.service.WorkExperienceService;
import com.zbdx.xyzp.util.DateTimeUtils;
import com.zbdx.xyzp.util.DateUtils;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
@CrossOrigin("http://121.43.158.100:8080")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SkillService skillService;
    @Autowired
    private SelfEvaluationService selfEvaluationService;
    @Autowired
    private WorkExperienceService workExperienceService;

    @CrossOrigin
    @ApiOperation("????????????")
    @GetMapping("/login")
    @ResponseBody
    public Result login(@NotNull(message = "?????????????????????") String username,
                        @NotNull(message = "??????????????????") String password){
        return userService.login(username , password);
    }

    @ApiOperation("????????????????????????????????????")
    @GetMapping("/selectRoleTypeByName")
    public Result selectRoleTypeByName(String username){
        return Result.success(userService.selectRoleTypeByName(username));
    }

    @ApiOperation("????????????????????????")
    @GetMapping("/selectByName")
    public Result selectByName(String username){

        return Result.success(userService.selectByName(username));
    }

    @ApiOperation("????????????????????????")
    @GetMapping("/selectByUsername")
    public Result selectByUsername(String username){
        return Result.success(userService.selectByUsername(username));
    }

    @ApiOperation("??????????????????????????????")
    @GetMapping("/selectPhoto")
    public Result selectPhoto(String username){
        return Result.success(userService.selectPhoto(username));
    }

    @ApiOperation("??????????????????????????????")
    @PostMapping("/updatePhoto")
    @ResponseBody
    public Result updatePhoto(MultipartFile file, HttpServletRequest request) throws IOException {

            String fileDir = "F:" + File.separator + "upload";
            System.out.println("------->>" + fileDir);
            File dir = new File(fileDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String fileName = file.getOriginalFilename();
            File upload_file = new File(fileDir + File.separator + fileName);
            file.transferTo(upload_file);

            String outDir = fileDir + File.separator + fileName;


        return Result.success(outDir);
    }

    @ApiOperation("????????????")
    @GetMapping("/updateByUsername")
    public Result updateByUsername(String username,String dir){
        return Result.success(userMapper.updateByUsername(username,dir));
    }

    @ApiOperation("????????????????????????")
    @GetMapping("/selectByPassword")
    public Result selectByPassword(String username,String password){
        return Result.success(userService.selectByPassword(username,password));
    }

    @ApiOperation("????????????")
    @PostMapping("/addUser")
    public Result addUser(@RequestBody User user) throws Exception {
        Date date = new Date();
        user.setRegistTime(date);
        user.setAge(DateUtils.getAge(user.getBirth()));
        if (user.getSex().equals("???")){
            user.setPhoto(Constant.PHOTO_MAN);
        }
        else {
            user.setPhoto(Constant.PHOTO_WOMAN);
        }
        Skill skill = new Skill();
        skill.setUsername(user.getUsername());
        skillService.save(skill);

        WorkExperience workExperience = new WorkExperience();
        workExperience.setUsername(user.getUsername());
        workExperienceService.save(workExperience);

        SelfEvaluation selfEvaluation = new SelfEvaluation();
        selfEvaluation.setUsername(user.getUsername());
        selfEvaluationService.save(selfEvaluation);
        return Result.success(userService.save(user));
    }

    @ApiOperation("??????????????????")
    @GetMapping("/getUser")
    public Result getUser(UserDTO userDTO){

        return Result.success(userService.getUser(userDTO));
    }

    @ApiOperation("??????????????????")
    @GetMapping("/pageUser")
    public Result pageUser(Page<UserDTO> page ,UserDTO userDTO){
        return Result.success(userService.pageUser(page ,userDTO));

    }

    @ApiOperation("??????id????????????")
    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody User user){
        user.setRegistTime(new Date());
        return Result.success(userService.updateById(user));
    }

    @ApiOperation("????????????")
    @GetMapping("/updatePassword")
    public Result updatePassword(String username,String password){
        return Result.success(userMapper.updatePassword(username,password));
    }

    @ApiOperation("????????????")
    @PostMapping("/editUser")
    public Result editUser(@RequestBody User user){
        return Result.success(userService.saveOrUpdate(user));
    }

    @ApiOperation("??????id????????????")
    @GetMapping("/deleteUser")
    public Result deleteUser(int id){
        return Result.success(userService.removeById(id));
    }

    @ApiOperation("???????????????")
    @GetMapping("/getUsername")
    public Result getUsername(){
        return Result.success(userService.getUsername());
    }

    @ApiOperation("????????????????????????????????????????????????")
    @GetMapping("/getRealNamePuTong")
    public Result getRealNamePuTong(){
        return Result.success(userService.getRealNamePuTong());
    }

    @ApiOperation("????????????????????????")
    @GetMapping("/getRealName")
    public Result getRealName(){
        return Result.success(userService.getRealName());
    }

    @ApiOperation("??????????????????")
    @GetMapping("/getUserRoleType")
    public Result getUserRoleType(){
        return Result.success(userService.getUserRoleType());
    }

    @ApiOperation("??????????????????")
    @GetMapping("/getEducation")
    public Result getEducation(){
        return Result.success(userService.getEducation());
    }

    @ApiOperation("????????????????????????????????????????????????????????????????????????")
    @GetMapping("/getUserAndSkill")
    public Result getUserAndSkill(Page<UserDTO> page ,UserDTO userDTO){
        return Result.success(userService.getUserAndSkill(page, userDTO));
    }

    @ApiOperation("???????????????????????????????????????")
    @GetMapping("/editUserByUsername")
    public Result editUserByUsername(String username){
        return Result.success(userService.editUserByUsername(username));
    }

    @ApiOperation("????????????????????????????????????")
    @GetMapping("/getRoleTypeNum")
    public Result getRoleTypeNum(){
        return Result.success(userService.getRoleTypeNum());
    }

    @ApiOperation("??????????????????")
    @PostMapping("/importUser")
    public Result importUser(@RequestBody Map<String, Object> param) {
        String logStr = "importUser";
        Map<String, Object> retMap = Maps.newHashMap();
        try {
            log.info("?????????????????????{}", JSONObject.toJSONString(param));
            if (param.containsKey("list")) {
                log.error("{} ?????????", logStr);
                List<Map<String, Object>> list = (List<Map<String, Object>>) param.get("list");
                retMap = userService.importUser(list);
                return Result.success(retMap);
            } else {
                return Result.fail("????????????????????????");
            }
        } catch (Exception e) {
            log.error("{} ?????????{}", logStr, e);
            return Result.fail("??????excel??????????????????????????????");
        }
    }

    @ApiOperation(value = "??????????????????" ,httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/exportDefaultTemplate")
    public void exportDefaultTemplate(HttpServletResponse response,@NotBlank(message = "????????????????????????") String moduleNameCn){

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
                        + File.separator + fileName.replace("??????-", "");
                log.info("?????????????????????{}", tmpFileName);
                fi = new File(tmpFileName);
                FileUtils.copyInputStreamToFile(in, fi);
                in.close();
                inNew = new FileInputStream(fi);
            } catch (IOException e) {
                log.error("????????????????????????{}", e);
            }
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inNew.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            inNew.close();
        } catch (Exception e) {
            log.error("????????????????????????{}", e);
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
                log.error("??????????????????IO??????:{}", e);
            }
        }
    }

    @ApiOperation(value = "??????????????????", httpMethod = "GET")
    @GetMapping("/exportUser")
    public Response exportUser(@RequestParam Map<String, Object> param, HttpServletResponse response) {
        String logStr = "????????????????????????";
        log.info("?????????????????????{}", JSON.toJSONString(param));
        try {
            String fileNames = URLEncoder.encode("????????????.xlsx", "UTF-8");
            response.setHeader("content-disposition", "attachment;filename=" + fileNames);
            ExcelTool.setResponseHeader(response, fileNames);
            OutputStream os = response.getOutputStream();
            XSSFWorkbook xssfWorkbook = userService.exportUser(param);
            if (xssfWorkbook == null) {
                return Response.failure(logStr + "??????");
            }
            xssfWorkbook.write(os);
            os.flush();
            os.close();
            return null;
        } catch (Exception e) {
            log.error("{}??????,{}", logStr, e);
            return Response.failure("{}??????", logStr);
        }

    }

    @ApiOperation(value = "exportUserToWord" ,httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/exportUserToWord")
    public void exportUserToWord(HttpServletRequest request, HttpServletResponse response , String username) {
        userService.exportUserToWord(username,request,response);
    }
}
