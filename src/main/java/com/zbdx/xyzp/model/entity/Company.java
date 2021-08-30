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
@TableName(value = "company")
public class Company implements Serializable {

    /**
     *企业编号
     * */
    @TableId(value = "company_id" , type = IdType.AUTO)
    private Integer companyId;

    /**
     *企业名称
     * */
    @TableField(value = "company_name")
    private String companyName;

    /**
     *企业类型
     * */
    @TableField(value = "company_type")
    private String companyType;

    /**
     *成立时间
     * */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(value = "found_time")
    private Date foundTime;

    /**
     *员工人数
     * */
    @TableField(value = "employ_num")
    private Integer employNum;

    /**
     *所在位置
     * */
    @TableField(value = "location")
    private String location;

    /**
     *联系电话
     * */
    @TableField(value = "telephone")
    private String telephone;

    /**
     *联系地址
     * */
    @TableField(value = "address")
    private String address;

    /**
     *邮政编码
     * */
    @TableField(value = "postal_code")
    private Integer postalCode;

    /**
     *网址
     * */
    @TableField(value = "http")
    private String http;

    /**
     *电子邮箱
     * */
    @TableField(value = "email")
    private String email;

    /**
     *企业简介
     * */
    @TableField(value = "introduce")
    private String introduce;

}
