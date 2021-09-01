package com.zbdx.xyzp.model.dto;

import lombok.Data;
import java.io.Serializable;

@Data
public class SkillDTO implements Serializable {

    /**
     * 编号
     * */
    private Integer skillId;

    /**
     *应聘职位
     * */
    private String appliedPosition;

    /**
     *英语水平
     * */
    private String englishSkill;

    /**
     *计算机能力
     * */
    private String computerSkill;

    /**
     *主要技能
     * */
    private String mainSkill;

    /**
     *真实姓名
     * */
    private String realName;

    /**
     *用户名
     * */
    private String username;

}
