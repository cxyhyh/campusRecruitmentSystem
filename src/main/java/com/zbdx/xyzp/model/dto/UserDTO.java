package com.zbdx.xyzp.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zbdx.xyzp.model.entity.User;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;
@Data
public class UserDTO extends User implements Serializable {

    /**
     *id
     * */
    private Integer id;

    /**
     *用户名
     * */
    private String username;

    /**
     *密码
     * */
    private String password;

    /**
     *用户类型值
     * */
    private Integer typeKey;

    /**
     *真实姓名
     * */
    private String realName;

    /**
     *性别
     * */
    private String sex;

    /**
     *年龄
     * */
    private Integer age;

    /**
     *政治面貌 0中共党员 1中共预备党员 2共青团员 3民主党派 4无党派人士 5群众
     * */
    private String politicsStatus;

    /**
     *出生日期
     * */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birth;

    /**
     *身份证号码
     * */
    private String idCard;

    /**
     *民族
     * */
    private String nature;

    /**
     *籍贯
     * */
    private String hometown;

    /**
     *文化程度
     * */
    private String education;

    /**
     *所属院校
     * */
    private String college;

    /**
     *手机号码
     * */
    private String mobilePhone;

    /**
     *电子邮箱
     * */
    private String email;

    /**
     *住址
     * */
    private String address;

    /**
     *注册时间
     * */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date registTime;

    /**
     *角色类型
     * */
    private String  roleType;

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
     *简历申请状态
     * */
    private String status;

    /**
     *头像
     * */
    private String photo;

}
