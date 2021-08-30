package com.zbdx.xyzp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zbdx.xyzp.model.dto.JianLiDTO;
import com.zbdx.xyzp.model.entity.JianLi;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public interface JianliService extends IService<JianLi> {
    List<JianLiDTO> getJianli(JianLiDTO jianLiDTO);

    boolean updateUsernameById(String fileName, String username, String applicationPosition);

    Integer getMaxJianliId(String fileName);

    List<JianLiDTO> getNewFileNameByUsername(String username);

    Page<JianLiDTO> pageJianLi(Page<JianLiDTO> page, JianLiDTO jianLiDTO);

    List<Map<String , Object>> getUserName();

    List<Map<String , Object>> getApplicationPosition();
}
