package com.zbdx.xyzp.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
@Data

@TableName(value = "skill")
public class Skill implements Serializable {

    /**
     * 编号
     * */
    @TableId(value = "skill_id" , type = IdType.AUTO)
    private Integer skillId;

    /**
     *应聘职位
     * */
    @TableField(value = "applied_position")
    private String appliedPosition;

    /**
     *英语水平
     * */
    @TableField(value = "english_skill")
    private String englishSkill;

    /**
     *计算机能力
     * */
    @TableField(value = "computer_skill")
    private String computerSkill;

    /**
     *主要技能
     * */
    @TableField(value = "main_skill")
    private String mainSkill;

    /**
     *真实姓名
     * */
    @TableField(value = "real_name")
    private String realName;

}
