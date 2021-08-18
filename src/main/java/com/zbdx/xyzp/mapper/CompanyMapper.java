package com.zbdx.xyzp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zbdx.xyzp.model.dto.CompanyDTO;
import com.zbdx.xyzp.model.dto.UserDTO;
import com.zbdx.xyzp.model.entity.Company;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CompanyMapper extends BaseMapper<Company> {

    List<CompanyDTO> getCompany();

    Page<CompanyDTO> pageCompany(Page<CompanyDTO> page, @Param("param") CompanyDTO companyDTO);

    List<Map<String , Object>> getCompanyName();

    List<Map<String , Object>> getCompanyType();

    List<Map<String , Object>> getLocation();

}
