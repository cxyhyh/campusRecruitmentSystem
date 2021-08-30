package com.zbdx.xyzp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zbdx.xyzp.mapper.UserMapper;
import com.zbdx.xyzp.mapper.WorkExperienceMapper;
import com.zbdx.xyzp.model.entity.User;
import com.zbdx.xyzp.model.entity.WorkExperience;
import com.zbdx.xyzp.service.UserService;
import com.zbdx.xyzp.service.WorkExperienceService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author hanyuhao
 * @PackageName com.zbdx.xyzp.service.impl
 * @Class WorkExperienceServiceImpl
 * @Date 2021/8/30 13:43
 */
@Service
public class WorkExperienceServiceImpl extends ServiceImpl<WorkExperienceMapper, WorkExperience> implements WorkExperienceService {

    @Override
    public List<WorkExperience> selectByUsername(String username) {
        return this.baseMapper.selectByUsername(username);
    }
}
