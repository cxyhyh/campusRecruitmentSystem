package com.zbdx.xyzp.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zbdx.xyzp.config.CommonConfig;
import com.zbdx.xyzp.constant.Constant;
import com.zbdx.xyzp.mapper.UserMapper;
import com.zbdx.xyzp.model.dto.UserDTO;
import com.zbdx.xyzp.model.entity.User;
import com.zbdx.xyzp.service.UserService;
import com.zbdx.xyzp.util.DateTimeUtils;
import com.zbdx.xyzp.util.RegexUtils;
import com.zbdx.xyzp.util.Result;
import com.zbdx.xyzp.util.ValidUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommonConfig commonConfig;

    @Override
    public List<UserDTO> getUser(UserDTO userDTO) {
        return userMapper.getUser(userDTO);

    }

    @Override
    public List<UserDTO> selectByName(String username) {
        return userMapper.selectByName(username);
    }

    @Override
    public List<UserDTO> selectByPassword(String password) {
        return userMapper.selectByPassword(password);
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
        return userMapper.getUserAndSkill(page , userDTO);
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
            message = ValidUtils.judgeKongParam(message, param, "密码", "password");
            message = ValidUtils.judgeKongParam(message, param, "用户类型", "roleType");
            message = ValidUtils.judgeKongParam(message, param, "姓名", "realName");
            message = ValidUtils.judgeKongParam(message, param, "性别", "sex");
            message = ValidUtils.judgeKongParam(message, param, "出生日期", "birth");
            message = ValidUtils.judgeKongParam(message, param, "身份证号", "idCard");
            message = ValidUtils.judgeKongParam(message, param, "联系电话", "mobilePhone");

            if (param.get("username") != null){
                boolean isExistUser = isUserExist(param.get("username").toString());
                if (isExistUser){
                    message.append("@username:工作证号已存在");
                }
            }
            if (param.get("mobilePhone") != null){
                boolean isExistMobilePhone = isMobilePhoneExist(param.get("mobilePhone").toString());
                if (isExistMobilePhone){
                    message.append("@mobilePhone:手机号已存在");
                }
            }
            if (param.get("idCard") != null){
                boolean isExistIdNumber = isIdCardExist(param.get("idCard").toString());
                if (isExistIdNumber){
                    message.append("@idCard:身份证号码已存在");
                }
                if (!RegexUtils.validateIdNumber(param.get("idCard").toString())) {
                    message.append("@idCard:身份证格式错误;");
                }
            }
            if (param.get("phone") != null){
                if (!RegexUtils.validateMobilePhone(param.get("phone").toString())) {
                    message.append("@phone:手机号格式错误;");
                }
            }
            if (message.toString().contains("@")) {
                error.add(message.toString());
            }
        }
        if (!error.isEmpty()) {
            map.put("code",400);
            map.put("status", "fail");
            map.put("message", error);
            return map;
        }
        List<User> resultList = JSON.parseArray(JSON.toJSONString(list), User.class);
        return importBatchUser(resultList);
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
                user.setTypeKey(Constant.TYPEKEY);
                user.setPassword(Constant.PASSWORD);
                user.setRegistTime(new Date());
                boolean save = this.save(user);
                if (save) {
                    retMap.put("result", list);
                    retMap.put("code",200);
                    retMap.put("status", "success");
                } else {
                    retMap.put("result", save);
                    retMap.put("code",400);
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
            List<UserDTO> list1 = userMapper.selectByPassword(password);
            if (list1.size() <= 0) {
                return Result.fail(400, "密码错误！！");
            }
            return Result.success(200, "登录成功！！", userMapper.selectByName(username));
        }
    }

}






