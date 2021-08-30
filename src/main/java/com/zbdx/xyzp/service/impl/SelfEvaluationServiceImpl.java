package com.zbdx.xyzp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zbdx.xyzp.mapper.SelfEvaluationMapper;
import com.zbdx.xyzp.model.entity.SelfEvaluation;
import com.zbdx.xyzp.service.SelfEvaluationService;
import org.springframework.stereotype.Service;

/**
 * @Author hanyuhao
 * @PackageName com.zbdx.xyzp.service.impl
 * @Class SelfEvaluationServiceImpl
 * @Date 2021/8/30 13:46
 */
@Service
public class SelfEvaluationServiceImpl extends ServiceImpl<SelfEvaluationMapper, SelfEvaluation> implements SelfEvaluationService {

    @Override
    public SelfEvaluation selectByUsername(String username) {
        return this.baseMapper.selectByUsername(username);
    }
}
