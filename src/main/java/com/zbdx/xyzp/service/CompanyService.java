package com.zbdx.xyzp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zbdx.xyzp.model.dto.CompanyDTO;
import com.zbdx.xyzp.model.entity.Company;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public interface CompanyService extends IService<Company> {

    List<CompanyDTO> getCompany();

    Page<CompanyDTO> pageCompany(Page<CompanyDTO> page, CompanyDTO companyDTO);

    List<Map<String , Object>> getCompanyName();

    List<Map<String , Object>> getCompanyType();

    List<Map<String , Object>> getLocation();

    Map<String, Object> importCompany(List<Map<String, Object>> list);

    XSSFWorkbook exportJob(Map<String, Object> param);

    List<Map<String,Object>> getCompanyTypeNum();
}
