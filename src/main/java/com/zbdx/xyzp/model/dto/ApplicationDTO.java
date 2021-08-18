package com.zbdx.xyzp.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
public class ApplicationDTO implements Serializable {

    /**
     *职位申请id
     * */
    private Integer applicationId;


    /**
     *用户名
     * */
    private String username;

    /**
     *用户类型
     * */
    private String roleType;

    /**
     *回复内容
     * */
    private String status;

    /**
     *申请职位
     * */
    private String applicationPosition;


}
