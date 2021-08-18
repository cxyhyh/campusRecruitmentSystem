package com.zbdx.xyzp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zbdx.xyzp.model.dto.ApplicationDTO;
import com.zbdx.xyzp.model.entity.Application;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ApplicationMapper extends BaseMapper<Application> {

    List<ApplicationDTO> getApplication();

    List<ApplicationDTO> selectUsername(String username);

    int addApplication(String username,String applicationPosition ,String status, String roleType );

    Page<ApplicationDTO> pageApplication(Page<ApplicationDTO> page, @Param("param") ApplicationDTO applicationDTO);

    List<Map<String, Object>> getApplicationPositionForA();

    List<Map<String, Object>> getStatus();

    List<ApplicationDTO> getUsernameAndApplicationPosition(String username, String applicationPosition);
}
