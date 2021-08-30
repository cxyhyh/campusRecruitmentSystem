package com.zbdx.xyzp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zbdx.xyzp.model.entity.SelfEvaluation;
import org.springframework.stereotype.Repository;

/**
 * @Author hanyuhao
 * @PackageName com.zbdx.xyzp.mapper
 * @Class SelfEvaluationMapper
 * @Date 2021/8/30 13:45
 */
@Repository
public interface SelfEvaluationMapper extends BaseMapper<SelfEvaluation> {

    SelfEvaluation selectByUsername(String username);
}
