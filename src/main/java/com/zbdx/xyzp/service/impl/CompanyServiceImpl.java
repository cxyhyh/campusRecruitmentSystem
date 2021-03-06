package com.zbdx.xyzp.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zbdx.xyzp.mapper.CompanyMapper;
import com.zbdx.xyzp.model.dto.CompanyDTO;
import com.zbdx.xyzp.model.entity.Company;
import com.zbdx.xyzp.service.CompanyService;
import com.zbdx.xyzp.service.UserService;
import com.zbdx.xyzp.util.DateTimeUtils;
import com.zbdx.xyzp.util.ValidUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper , Company> implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private UserService userService;

    @Override
    public List<CompanyDTO> getCompany() {
        return companyMapper.getCompany();
    }

    @Override
    public Page<CompanyDTO> pageCompany(Page<CompanyDTO> page, CompanyDTO companyDTO) {
        return companyMapper.pageCompany(page , companyDTO);
    }

    @Override
    public List<Map<String, Object>> getCompanyName() {
        return companyMapper.getCompanyName();
    }

    @Override
    public List<Map<String, Object>> getCompanyType() {
        return companyMapper.getCompanyType();
    }

    @Override
    public List<Map<String, Object>> getLocation() {
        return companyMapper.getLocation();
    }

    @Override
    public Map<String, Object> importCompany(List<Map<String, Object>> list) {
        Map<String, Object> map = new HashMap<String, Object>();
        //????????????
        List<String> error = Lists.newArrayList();
        StringBuffer message = null;
        for (int i = 0; i < list.size(); i++) {
            message = new StringBuffer();
            message.append(i + "#");
            Map<String, Object> param = list.get(i);
            log.info("???{}???,?????????{}", i + 1, JSONObject.toJSONString(param));

            message = ValidUtils.judgeKongParam(message, param, "????????????", "companyName");
            message = ValidUtils.judgeKongParam(message, param, "????????????", "companyType");
            message = ValidUtils.judgeKongParam(message, param, "????????????", "foundTime");
            message = ValidUtils.judgeDateParam(message, param, "????????????", "foundTime");
            message = ValidUtils.judgeKongParam(message, param, "????????????", "location");
            message = ValidUtils.judgeKongParam(message, param, "????????????", "telephone");
            message = ValidUtils.judgeKongParam(message, param, "????????????", "address");
            message = ValidUtils.judgeKongParam(message, param, "????????????", "postalCode");
            message = ValidUtils.judgeKongParam(message, param, "??????", "http");
            message = ValidUtils.judgeKongParam(message, param, "????????????", "email");

            if (param.get("companyName") != null){
                boolean isExistCompany = isCompanyExist(param.get("companyName").toString());
                if (isExistCompany){
                    message.append("@companyName:?????????????????????");
                }
            }
            if (param.get("telephone") != null){
                boolean isExistTelePhone = isTelePhoneExist(param.get("telephone").toString());
                if (isExistTelePhone){
                    message.append("@telephone:??????????????????");
                }
            }
            if (param.get("http") != null){
                boolean isExistHttp = isHttpExist(param.get("http").toString());
                if (isExistHttp){
                    message.append("@http:???????????????");
                }
            }
            if (param.get("email") != null){
                boolean isExistEmail = isEmailExist(param.get("email").toString());
                if (isExistEmail){
                    message.append("@email:?????????????????????");
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
        List<Company> resultList = JSON.parseArray(JSON.toJSONString(list), Company.class);
        return importBatchJob(resultList);
    }

    private boolean isEmailExist(String email) {
        boolean flag = false;
        QueryWrapper<Company> query = new QueryWrapper<>();
        query.eq("email", email);
        List list = this.list(query);
        if (list != null && list.size() > 0) {
            flag = true;
        }
        return flag;
    }

    private boolean isHttpExist(String http) {
        boolean flag = false;
        QueryWrapper<Company> query = new QueryWrapper<>();
        query.eq("http", http);
        List list = this.list(query);
        if (list != null && list.size() > 0) {
            flag = true;
        }
        return flag;
    }

    private boolean isTelePhoneExist(String telephone) {
        boolean flag = false;
        QueryWrapper<Company> query = new QueryWrapper<>();
        query.eq("telephone", telephone);
        List list = this.list(query);
        if (list != null && list.size() > 0) {
            flag = true;
        }
        return flag;
    }

    private boolean isCompanyExist(String companyName) {
        boolean flag = false;
        QueryWrapper<Company> query = new QueryWrapper<>();
        query.eq("company_name", companyName);
        List list = this.list(query);
        if (list != null && list.size() > 0) {
            flag = true;
        }
        return flag;
    }

    private Map<String, Object> importBatchJob(List<Company> list) {
        Map<String, Object> retMap = Maps.newHashMap();
        StringBuffer ret = new StringBuffer("???????????????????????????");
        try {
            for (Company company : list) {
                boolean save = this.save(company);
                if (save) {
                    retMap.put("result", list);
                    retMap.put("code",200);
                    retMap.put("status", "success");
                } else {
                    retMap.put("result", save);
                    retMap.put("code",400);
                    retMap.put("status", "fail");
                    retMap.put("message", "??????????????????");
                }
            }
            return retMap;
        } catch (Exception e) {
            log.error("????????????????????????:{}", e);
            ret.append("????????????????????????;");
            retMap.put("status", "fail");
            retMap.put("message", ret.toString());
            return retMap;
        }
    }

    @Override
    public XSSFWorkbook exportJob(Map<String, Object> param) {
        XSSFWorkbook wb = null;
        try {
            wb = userService.copyTemplate("??????-??????.xlsx");
            Sheet sheet = wb.getSheetAt(0);
            List<CompanyDTO> stList = this.getList(param);
            if (stList != null && stList.size() > 0) {
                Integer lastRowNum = 1;
                for (int i = 0; i < stList.size(); i++) {
                    CompanyDTO obj = stList.get(i);
                    Integer index = lastRowNum + i;
                    log.info("index = {} ", index);
                    Row row = sheet.createRow(index);
                    int cellIndex = 0;
                    row.createCell(cellIndex++).setCellValue(obj.getCompanyName());
                    row.createCell(cellIndex++).setCellValue(obj.getCompanyType());
                    row.createCell(cellIndex++).setCellValue(DateTimeUtils.DateToStr(obj.getFoundTime()));
                    row.createCell(cellIndex++).setCellValue(obj.getEmployNum());
                    row.createCell(cellIndex++).setCellValue(obj.getLocation());
                    row.createCell(cellIndex++).setCellValue(obj.getTelephone());
                    row.createCell(cellIndex++).setCellValue(obj.getAddress());
                    row.createCell(cellIndex++).setCellValue(obj.getPostalCode());
                    row.createCell(cellIndex++).setCellValue(obj.getHttp());
                    row.createCell(cellIndex++).setCellValue(obj.getEmail());
                    row.createCell(cellIndex++).setCellValue(obj.getIntroduce());
                }
            }
            return wb;
        } catch (Exception e) {
            log.error("???????????????????????????{}", e);
            return wb;
        }
    }

    @Override
    public List<Map<String, Object>> getCompanyTypeNum() {
        List<Map<String,Object>> companyTypeMap = this.companyMapper.getCompanyType();

        List<Map<String,Object>> list = new ArrayList<>();

        for (Map<String,Object> param:companyTypeMap) {

            Map<String,Object> map = Maps.newHashMap();
            Integer result = this.companyMapper.getNumByCompanyType(param.get("companyType").toString());
            map.put("name", param.get("companyType").toString());
            map.put("value", result);
            list.add(map);
        }
        return list;
    }

    private List<CompanyDTO> getList(Map<String, Object> param) {
        List<CompanyDTO> records = this.baseMapper.getList(param);
        return records;
    }
}
