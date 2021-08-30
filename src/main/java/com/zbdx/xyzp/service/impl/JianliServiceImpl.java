package com.zbdx.xyzp.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zbdx.xyzp.mapper.JianliMapper;
import com.zbdx.xyzp.model.dto.JianLiDTO;
import com.zbdx.xyzp.model.entity.JianLi;
import com.zbdx.xyzp.service.JianliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class JianliServiceImpl extends ServiceImpl<JianliMapper, JianLi> implements JianliService {

    @Autowired
    private JianliMapper jianliMapper;
    @Override
    public List<JianLiDTO> getJianli(JianLiDTO jianLiDTO) {
        return jianliMapper.getJianli(jianLiDTO);
    }

    @Override
    public boolean updateUsernameById(String fileName, String username,String applicationPosition) {

        Integer jianliId = this.getMaxJianliId(fileName);

        UpdateWrapper<JianLi> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("username",username).eq("jianli_id",jianliId);
        updateWrapper.set("application_position",applicationPosition).eq("jianli_id",jianliId);
        return this.update(updateWrapper);
    }

    @Override
    public Integer getMaxJianliId(String fileName) {
        return jianliMapper.getMaxJianliId(fileName);
    }

    @Override
    public List<JianLiDTO> getNewFileNameByUsername(String username) {
        return jianliMapper.getNewFileNameByUsername(username);
    }

    @Override
    public Page<JianLiDTO> pageJianLi(Page<JianLiDTO> page, JianLiDTO jianLiDTO) {
        return jianliMapper.pageJianLi(page ,jianLiDTO);
    }

    @Override
    public List<Map<String, Object>> getUserName() {
        return jianliMapper.getUserName();
    }

    @Override
    public List<Map<String, Object>> getApplicationPosition() {
        return jianliMapper.getApplicationPosition();
    }

}
