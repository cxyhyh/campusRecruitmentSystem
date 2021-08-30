package com.zbdx.xyzp.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;
@Data
public class JobDTO  implements Serializable {

    /**
     * 职位编号
     * */
    private Integer jobId;

    /**
     *职位名称
     * */
        private String jobName;

    /**
     *职位类型
     * */
    private String jobType;

    /**
     *学历要求
     * */
    private String educationRequirement;

    /**
     *性别要求
     * */
    private String sexRequirement;

    /**
     *薪水
     * */
    private String salary;

    /**
     *年龄要求
     * */
    private String ageRequirement;

    /**
     *工作年限要求
     * */
    private String workTimeRequirement;

    /**
     *工作地点
     * */
    private String workPosition;

    /**
     *招聘人数
     * */
    private Integer recruitNum;

    /**
     *具体要求
     * */
    private String specificRequirement;

    /**
     *发布时间
     * */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    /**
     *所属公司
     * */
    private String belongCompany;

    /**
     *企业名称
     * */
    private String companyName;

    /**
     *企业类型
     * */
    private String companyType;

    /**
     *成立时间
     * */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date foundTime;

    /**
     *员工人数
     * */
    private Integer employNum;

    /**
     *所在位置
     * */
    private String location;

    /**
     *联系电话
     * */
    private String telephone;

    /**
     *联系地址
     * */
    private String address;

    /**
     *邮政编码
     * */
    private Integer postalCode;

    /**
     *网址
     * */
    private String http;

    /**
     *电子邮箱
     * */
    private String email;

    /**
     *企业简介
     * */
    private String introduce;


}
