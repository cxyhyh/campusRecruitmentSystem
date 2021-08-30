package com.zbdx.xyzp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zbdx.xyzp.model.entity.WorkExperience;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @Author hanyuhao
 * @PackageName com.zbdx.xyzp.mapper
 * @Class WorkExperienceMapper
 * @Date 2021/8/30 13:42
 */
@Repository
public interface WorkExperienceMapper extends BaseMapper<WorkExperience> {

    List<WorkExperience> selectByUsername(String username);
}
