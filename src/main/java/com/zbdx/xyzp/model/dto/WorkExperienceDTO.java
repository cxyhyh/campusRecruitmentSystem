package com.zbdx.xyzp.model.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/**
 * @Author hanyuhao
 * @PackageName com.zbdx.xyzp.model.dto
 * @Class WorkExperienceDTO
 * @Date 2021/8/30 11:27
 */
@Data
public class WorkExperienceDTO implements Serializable {

    /**
     *id
     * */
    private Integer id;

    /**
     *用户名
     * */
    private String username;

    /**
     *入职时间
     * */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date entryTime;

    /**
     *离职时间
     * */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date departureTime;

    /**
     *工作公司
     * */
    private String workCompany;

    /**
     *工作岗位
     * */
    private String post;

    /**
     *工作职责
     * */
    private String duty;
}
