package com.zbdx.xyzp.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author hanyuhao
 * @PackageName com.zbdx.xyzp.model.dto
 * @Class SelfEvaluationDTO
 * @Date 2021/8/30 14:00
 */
@Data
public class SelfEvaluationDTO implements Serializable {

    /**
     *id
     * */
    private Integer id;

    /**
     *用户名
     * */
    private String username;

    /**
     *自我评价
     * */
    private String selfEvaluation;

}
