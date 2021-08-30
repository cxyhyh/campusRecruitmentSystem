package com.zbdx.xyzp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zbdx.xyzp.model.entity.User;
import com.zbdx.xyzp.model.entity.WorkExperience;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author hanyuhao
 * @PackageName com.zbdx.xyzp.service
 * @Class WorkExperienceService
 * @Date 2021/8/30 13:43
 */
@Service
public interface WorkExperienceService extends IService<WorkExperience> {
    List<WorkExperience> selectByUsername(String username);
}
