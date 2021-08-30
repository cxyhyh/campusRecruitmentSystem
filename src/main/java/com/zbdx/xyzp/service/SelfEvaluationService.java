package com.zbdx.xyzp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zbdx.xyzp.model.entity.SelfEvaluation;
import org.springframework.stereotype.Service;

/**
 * @Author hanyuhao
 * @PackageName com.zbdx.xyzp.service
 * @Class SelfEvaluationService
 * @Date 2021/8/30 13:46
 */
@Service
public interface SelfEvaluationService extends IService<SelfEvaluation> {

    SelfEvaluation selectByUsername(String username);
}
