package com.zbdx.xyzp.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zbdx.xyzp.mapper.CompanyMapper;
import com.zbdx.xyzp.model.dto.CompanyDTO;
import com.zbdx.xyzp.model.dto.UserDTO;
import com.zbdx.xyzp.model.entity.Company;
import com.zbdx.xyzp.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper , Company> implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

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
}
