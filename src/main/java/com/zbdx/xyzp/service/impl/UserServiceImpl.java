package com.zbdx.xyzp.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zbdx.xyzp.config.CommonConfig;
import com.zbdx.xyzp.constant.Constant;
import com.zbdx.xyzp.mapper.RoleMapper;
import com.zbdx.xyzp.mapper.UserMapper;
import com.zbdx.xyzp.model.dto.UserDTO;
import com.zbdx.xyzp.model.entity.SelfEvaluation;
import com.zbdx.xyzp.model.entity.Skill;
import com.zbdx.xyzp.model.entity.User;
import com.zbdx.xyzp.model.entity.WorkExperience;
import com.zbdx.xyzp.service.SelfEvaluationService;
import com.zbdx.xyzp.service.SkillService;
import com.zbdx.xyzp.service.UserService;
import com.zbdx.xyzp.service.WorkExperienceService;
import com.zbdx.xyzp.util.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.*;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommonConfig commonConfig;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private WordUtils wordUtils;
    @Autowired
    private SkillService skillService;
    @Autowired
    private SelfEvaluationService selfEvaluationService;
    @Autowired
    private WorkExperienceService workExperienceService;

    @Override
    public List<UserDTO> getUser(UserDTO userDTO) {
        return userMapper.getUser(userDTO);
    }

    @Override
    public List<UserDTO> selectByName(String username) {
        return userMapper.selectByName(username);
    }

    @Override
    public List<UserDTO> selectByPassword(String password, String username) {
        return userMapper.selectByPassword(username, password);
    }

    @Override
    public Page<UserDTO> pageUser(Page<UserDTO> page, UserDTO userDTO) {
        return userMapper.pageUser(page, userDTO);
    }

    @Override
    public List<Map<String, Object>> getUsername() {
        return userMapper.getUsername();
    }

    @Override
    public List<Map<String, Object>> getUserRoleType() {
        return userMapper.getUserRoleType();
    }

    @Override
    public List<Map<String, Object>> getRealName() {
        return userMapper.getRealName();
    }

    @Override
    public List<Map<String, Object>> getEducation() {
        return userMapper.getEducation();
    }

    @Override
    public Map<String, Object> selectRoleTypeByName(String username) {
        return userMapper.selectRoleTypeByName(username);
    }

    @Override
    public Page<UserDTO> getUserAndSkill(Page<UserDTO> page, UserDTO userDTO) {
        return userMapper.getUserAndSkill(page, userDTO);
    }

    @Override
    public List<Map<String, Object>> getRealNamePuTong() {
        return userMapper.getRealNamePuTong();
    }

    @Override
    public Map<String, Object> importUser(List<Map<String, Object>> list) {
        Map<String, Object> map = new HashMap<String, Object>();
        //校验数据
        List<String> error = Lists.newArrayList();
        StringBuffer message = null;
        for (int i = 0; i < list.size(); i++) {
            message = new StringBuffer();
            message.append(i + "#");
            Map<String, Object> param = list.get(i);
            log.info("第{}条,内容：{}", i + 1, JSONObject.toJSONString(param));

            message = ValidUtils.judgeKongParam(message, param, "用户名", "username");
            message = ValidUtils.judgeKongParam(message, param, "姓名", "realName");
            message = ValidUtils.judgeKongParam(message, param, "性别", "sex");
            message = ValidUtils.judgeKongParam(message, param, "出生日期", "birth");
            message = ValidUtils.judgeKongParam(message, param, "身份证号", "idCard");
            message = ValidUtils.judgeKongParam(message, param, "联系电话", "mobilePhone");
            message = ValidUtils.judgeKongParam(message, param, "电子邮箱", "email");

            if (param.get("username") != null) {
                boolean isExistUser = isUserExist(param.get("username").toString());
                if (isExistUser) {
                    message.append("@username:用户名已存在");
                }
            }
            if (param.get("mobilePhone") != null) {
                boolean isExistMobilePhone = isMobilePhoneExist(param.get("mobilePhone").toString());
                if (isExistMobilePhone) {
                    message.append("@mobilePhone:手机号已存在");
                }
            }
            if (param.get("idCard") != null) {
                boolean isExistIdNumber = isIdCardExist(param.get("idCard").toString());
                if (isExistIdNumber) {
                    message.append("@idCard:身份证号码已存在");
                }
                if (!RegexUtils.validateIdNumber(param.get("idCard").toString())) {
                    message.append("@idCard:身份证格式错误;");
                }
            }
            if (param.get("phone") != null) {
                if (!RegexUtils.validateMobilePhone(param.get("phone").toString())) {
                    message.append("@phone:手机号格式错误;");
                }
            }
            if (param.get("email") != null) {
                boolean isExistEmail = isEmailExist(param.get("email").toString());
                if (isExistEmail) {
                    message.append("@email:电子邮箱已存在");
                }
            }
            if (message.toString().contains("@")) {
                error.add(message.toString());
            }
        }
        if (!error.isEmpty()) {
            map.put("code", 400);
            map.put("status", "fail");
            map.put("message", error);
            return map;
        }
        List<User> resultList = JSON.parseArray(JSON.toJSONString(list), User.class);
        return importBatchUser(resultList);
    }

    private boolean isEmailExist(String email) {
        boolean flag = false;
        QueryWrapper<User> query = new QueryWrapper<>();
        query.eq("email", email);
        List list = this.list(query);
        if (list != null && list.size() > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public XSSFWorkbook exportUser(Map<String, Object> param) {
        XSSFWorkbook wb = null;
        try {
            wb = this.copyTemplate("模板-用户.xlsx");
            Sheet sheet = wb.getSheetAt(0);
            List<UserDTO> stList = this.getList(param);
            if (stList != null && stList.size() > 0) {
                Integer lastRowNum = 1;
                for (int i = 0; i < stList.size(); i++) {
                    UserDTO obj = stList.get(i);
                    Integer index = lastRowNum + i;
                    log.info("index = {} ", index);
                    Row row = sheet.createRow(index);
                    int cellIndex = 0;
                    row.createCell(cellIndex++).setCellValue(obj.getUsername());
                    row.createCell(cellIndex++).setCellValue(obj.getPassword());
                    row.createCell(cellIndex++).setCellValue(obj.getRoleType());
                    row.createCell(cellIndex++).setCellValue(obj.getRealName());
                    row.createCell(cellIndex++).setCellValue(obj.getSex());
                    row.createCell(cellIndex++).setCellValue(DateTimeUtils.DateToStr(obj.getBirth()));
                    row.createCell(cellIndex++).setCellValue(obj.getNature());
                    row.createCell(cellIndex++).setCellValue(obj.getPoliticsStatus());
                    row.createCell(cellIndex++).setCellValue(obj.getIdCard());
                    row.createCell(cellIndex++).setCellValue(obj.getHometown());
                    row.createCell(cellIndex++).setCellValue(obj.getMobilePhone());
                    row.createCell(cellIndex++).setCellValue(obj.getAddress());
                    row.createCell(cellIndex++).setCellValue(obj.getEmail());
                    row.createCell(cellIndex++).setCellValue(obj.getEducation());
                    row.createCell(cellIndex++).setCellValue(obj.getCollege());
                }
            }
            return wb;
        } catch (Exception e) {
            log.error("导出用户信息报错：{}", e);
            return wb;
        }
    }

    private List<UserDTO> getList(Map<String, Object> param) {
        List<UserDTO> records = this.baseMapper.getList(param);
        if (!records.isEmpty()) {
            for (UserDTO record : records) {
                try {
                    if (record.getBirth() != null) {
                        //根据出生日期计算年龄
                        record.setAge(DateUtils.getAge(record.getBirth()));
                    }
                } catch (Exception e) {
                    log.error("根据出生日期计算年龄错误", e);
                }
            }
        }
        return records;

    }

    public XSSFWorkbook copyTemplate(String resourceName) {
        XSSFWorkbook wb = null;
        try {
            String realPath = Constant.EXCEL_TEMP_PATH + resourceName;
            ResourceLoader resourceLoader = new DefaultResourceLoader();
            String tmpFileName = commonConfig.getExportTmpPath() + File.separator + DateTimeUtils.stamp(new Date())
                    + File.separator + resourceName.replace("模板-", "");
            InputStream inputStream = resourceLoader.getResource(realPath).getInputStream();
            FileUtils.copyInputStreamToFile(inputStream, new File(tmpFileName));
            File fi = new File(tmpFileName);
            FileInputStream is = new FileInputStream(fi);
            wb = new XSSFWorkbook(is);
            is.close();
            Files.delete(fi.toPath());
            File parentFile = fi.getParentFile();
            if (parentFile.isDirectory() && parentFile.list().length == 0) {
                parentFile.delete();
            }
        } catch (IOException e) {
            log.error("创建导出文件出错！{}", e);
            throw e;
        } finally {
            return wb;
        }
    }

    @Override
    public List<UserDTO> editUserByUsername(String username) {
        return userMapper.editUserByUsername(username);
    }

    @Override
    public List<Map<String, Object>> getRoleTypeNum() {

        List<Map<String, Object>> roleTypeMap = this.roleMapper.getRoleType();

        List<Map<String, Object>> list = new ArrayList<>();

        for (Map<String, Object> param : roleTypeMap) {

            Map<String, Object> map = Maps.newHashMap();
            Integer result = this.userMapper.getNumByRoleType(param.get("roleType").toString());
            map.put("name", param.get("roleType").toString());
            map.put("value", result);
            list.add(map);
        }
        return list;
    }

    @Override
    public void exportUserToWord(String username, HttpServletRequest request, HttpServletResponse response) {

            User user = this.selectByUsername(username);

            String RealName = this.userMapper.selectRealNameByUsername(username);
            Skill skill = skillService.selectByUsername(username);
            SelfEvaluation selfEvaluation = selfEvaluationService.selectByUsername(username);
            Map<String, Object> map = new HashMap<>();
            map.put("realName", RealName);
            map.put("sex", user.getSex());
            map.put("birth", DateUtils.DateToString(user.getBirth()));
            String data = getImageStr(user.getPhoto());
            map.put("image",data);
            map.put("politicsStatus",user.getPoliticsStatus());
            map.put("nature", user.getNature());
            map.put("address", user.getAddress());
            map.put("hometown", user.getHometown());
            map.put("phone", user.getMobilePhone());
            map.put("email", user.getEmail());
            map.put("idCard", user.getIdCard());
            map.put("education", user.getEducation());
            map.put("college", user.getCollege());
            map.put("englishSkill", skill.getEnglishSkill());
            map.put("computerSkill", skill.getComputerSkill());
            map.put("appliedPosition", skill.getAppliedPosition());
            map.put("mainSkill", skill.getMainSkill());
            map.put("selfEvaluation", selfEvaluation.getSelfEvaluation());

            List<WorkExperience> workExperiencesList = workExperienceService.selectByUsername(username);
            List<Map<String, String>> list = new ArrayList<>();
            for (WorkExperience workExperience : workExperiencesList) {
                Map<String, String> mapList = new HashMap<>();
                mapList.put("entryTime", DateUtils.DateToString(workExperience.getEntryTime()));
                mapList.put("departureTime", DateUtils.DateToString(workExperience.getDepartureTime()));
                mapList.put("workCompany", workExperience.getWorkCompany());
                mapList.put("post", workExperience.getPost());
                mapList.put("duty", workExperience.getDuty());
                list.add(mapList);
            }
            map.put("workExperienceList", list);
            wordUtils.exportWord(request, response, user.getRealName()+"简历", "用户.ftl", map);

    }

    private String getImageStr(String imgFile) {

        InputStream in = null;
        byte[] data = null;
            try {
                if (imgFile.contains("http://")) {

                    URL url = new URL(imgFile);
                    in = url.openStream();
                    data = new byte[in.available()];
                    in.read(data);
                    in.close();
                }
                else {
                    in = new FileInputStream(imgFile);
                    data = new byte[in.available()];
                    in.read(data);
                    in.close();
                }
            } catch (IOException e) {
                log.error("IO操作图片错误", e);
                e.printStackTrace();
            }

        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);

    }

    @Override
    public User selectByUsername(String username) {
        return this.baseMapper.selectByUsername(username);
    }

    @Override
    public String selectPhoto(String username) {
        String image = this.baseMapper.selectPhoto(username);
        String img = getImageStr(image);
        return "data:iamge/png;base64,"+img;
    }

    private boolean isIdCardExist(String idCard) {
        boolean flag = false;
        QueryWrapper<User> query = new QueryWrapper<>();
        query.eq("idCard", idCard);
        List list = this.list(query);
        if (list != null && list.size() > 0) {
            flag = true;
        }
        return flag;
    }

    private boolean isMobilePhoneExist(String mobilePhone) {
        boolean flag = false;
        QueryWrapper<User> query = new QueryWrapper<>();
        query.eq("mobilePhone", mobilePhone);
        List list = this.list(query);
        if (list != null && list.size() > 0) {
            flag = true;
        }
        return flag;
    }

    private boolean isUserExist(String username) {
        boolean flag = false;
        QueryWrapper<User> query = new QueryWrapper<>();
        query.eq("username", username);
        List list = this.list(query);
        if (list != null && list.size() > 0) {
            flag = true;
        }
        return flag;
    }

    private Map<String, Object> importBatchUser(List<User> list) {

        Map<String, Object> retMap = Maps.newHashMap();
        StringBuffer ret = new StringBuffer("站室批量入库情况：");
        try {
            for (User user : list) {

                if (user.getTypeKey()==null){
                    user.setTypeKey(Constant.TYPEKEY);
                }
                if (user.getPassword() == null){
                    user.setPassword(Constant.PASSWORD);
                }
                user.setRegistTime(new Date());
                user.setAge(DateUtils.getAge(user.getBirth()));
                if (user.getSex().equals("男")){
                    user.setPhoto(Constant.PHOTO_MAN);
                }
                else {
                    user.setPhoto(Constant.PHOTO_WOMAN);
                }

                boolean save = this.save(user);
               Skill skill = new Skill();
               skill.setUsername(user.getUsername());
               skillService.save(skill);

               WorkExperience workExperience = new WorkExperience();
               workExperience.setUsername(user.getUsername());
               workExperienceService.save(workExperience);

               SelfEvaluation selfEvaluation = new SelfEvaluation();
               selfEvaluation.setUsername(user.getUsername());
               selfEvaluationService.save(selfEvaluation);

                if (save) {
                    retMap.put("result", list);
                    retMap.put("code", 200);
                    retMap.put("status", "success");
                } else {
                    retMap.put("result", save);
                    retMap.put("code", 400);
                    retMap.put("status", "fail");
                    retMap.put("message", "批量入库失败");
                }
            }
            return retMap;
        } catch (Exception e) {
            log.error("用户信息导入错误:{}", e);
            ret.append("用户信息导入错误;");
            retMap.put("status", "fail");
            retMap.put("message", ret.toString());
            return retMap;
        }
    }

    @Override
    public Result login(String username, String password) {

        List<UserDTO> list = userMapper.selectByName(username);
        if (list.size() <= 0) {
            return Result.fail(400, "用户不存在，请重新输入或立即注册！！");
        } else {
            List<UserDTO> list1 = userMapper.selectByPassword(username, password);
            if (list1.size() <= 0) {
                return Result.fail(400, "密码错误！！");
            }
            return Result.success(200, "登录成功！！", userMapper.selectByName(username));
        }
    }

}






