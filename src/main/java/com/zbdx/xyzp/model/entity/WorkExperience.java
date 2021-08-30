package com.zbdx.xyzp.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;
/**
 * @Author hanyuhao
 * @PackageName com.zbdx.xyzp.model.entity
 * @Class WorkExperience
 * @Date 2021/8/30 11:22
 */
@Data
@TableName(value = "work_experience")
public class WorkExperience implements Serializable {

    /**
     *id
     * */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     *用户名
     * */
    @TableId(value = "username")
    private String username;

    /**
     *入职时间
     * */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(value = "entry_time")
    private Date entryTime;

    /**
     *离职时间
     * */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(value = "departure_time")
    private Date departureTime;

    /**
     *工作公司
     * */
    @TableField(value = "work_company")
    private String workCompany;

    /**
     *工作岗位
     * */
    @TableId(value = "post")
    private String post;

    /**
     *工作职责
     * */
    @TableId(value = "duty")
    private String duty;
}
