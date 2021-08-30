package com.zbdx.xyzp.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;
@Data
@TableName(value = "job")
public class Job implements Serializable {

    /**
    * 职位编号
    * */
    @TableId(value = "job_id" ,type = IdType.AUTO)
    private Integer jobId;

    /**
     *职位名称
     * */
    @TableField(value = "job_name")
    private String jobName;

    /**
     *职位类型
     * */
    @TableField(value = "job_type")
    private String jobType;

    /**
     *学历要求
     * */
    @TableField(value = "education_requirement")
    private String educationRequirement;

    /**
     *薪水
     * */
    @TableField(value = "salary")
    private String salary;

    /**
     *性别要求
     * */
    @TableField(value = "sex_requirement")
    private String sexRequirement;

    /**
     *年龄要求
     * */
    @TableField(value = "age_requirement")
    private String ageRequirement;

    /**
     *工作年限要求
     * */
    @TableField(value = "work_time_requirement")
    private String workTimeRequirement;

    /**
     *工作地点
     * */
    @TableField(value = "work_position")
    private String workPosition;

    /**
     *招聘人数
     * */
    @TableField(value = "recruit_num")
    private Integer recruitNum;

    /**
     *具体要求
     * */
    @TableField(value = "specific_requirement")
    private String specificRequirement;

    /**
     *发布时间
     * */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(value = "create_time")
    private Date createTime;

    /**
     *所属公司
     * */
    @TableField(value = "belong_company")
    private String belongCompany;
}
