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
@TableName(value = "user")
public class User implements Serializable {

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
     *密码
     * */
    @TableField(value = "password")
        private String password;

    /**
     *用户类型值
     * */
    @TableField(value = "type_key")
    private Integer typeKey;

    /**
     *年龄
     * */
    @TableField(value = "age")
    private Integer age;

    /**
     *政治面貌
     * */
    @TableField(value = "politics_status")
    private Integer politicsStatus;

    /**
     *真实姓名
     * */
    @TableField(value = "real_name")
    private String realName;

    /**
     *性别
     * */
    @TableField(value = "sex")
    private String sex;

    /**
     *出生日期
     * */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(value = "birth")
    private Date birth;

    /**
     *身份证号码
     * */
    @TableField(value = "idCard")
    private String idCard;

    /**
     *民族
     * */
    @TableField(value = "nature")
    private String nature;

    /**
     *籍贯
     * */
    @TableField(value = "hometown")
    private String hometown;

    /**
     *文化程度
     * */
    @TableField(value = "education")
    private String education;

    /**
     *所属院校
     * */
    @TableField(value = "college")
    private String college;

    /**
     *手机号码
     * */
    @TableField(value = "mobilePhone")
    private String mobilePhone;

    /**
     *电子邮箱
     * */
    @TableField(value = "email")
    private String email;

    /**
     *住址
     * */
    @TableField(value = "address")
    private String address;

    /**
     *注册时间
     * */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(value = "regist_time")
    private Date registTime;

}
