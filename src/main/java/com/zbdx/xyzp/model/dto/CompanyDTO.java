package com.zbdx.xyzp.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;
@Data
public class CompanyDTO implements Serializable {

    /**
     *企业编号
     * */
    private Integer companyId;

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
