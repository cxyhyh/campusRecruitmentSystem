package com.zbdx.xyzp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zbdx.xyzp.model.dto.ApplicationDTO;
import com.zbdx.xyzp.model.entity.Application;
import com.zbdx.xyzp.util.Result;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ApplicationService extends IService<Application> {

    List<ApplicationDTO> getApplication();

    Result apply(String username, String applicationPositioon);

    List<ApplicationDTO> selectUsername(String username);

    Result tongYi(String username);

    Result buTongYi(String username);

    int addApplication(String username , String applicationPosition,String status, String roleType);

    Page<ApplicationDTO> pageApplication(Page<ApplicationDTO> page, ApplicationDTO applicationDTO);

    List<Map<String , Object>> getApplicationPositionForA();

    List<Map<String , Object>> getStatus();

    Result tongGuo(String username, String applicationPosition);

    Result buTongGuo(String username, String applicationPosition);

    List<ApplicationDTO> getUsernameAndApplicationPosition(String username, String applicationPosition);
}
