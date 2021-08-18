package com.zbdx.xyzp.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;

@Data
@TableName(value = "application")
public class Application implements Serializable {


    /**
     *职位申请id
     * */
    @TableId(value = "application_id" , type = IdType.AUTO)
    private Integer applicationId;

    /**
     *用户名
     * */
    @TableField(value = "username")
        private String username;

    /**
     *用户类型
     * */
    @TableField(value = "role_type")
    private String roleType;

    /**
     *回复内容
     * */
    @TableField(value = "status")
    private String status;

    /**
     *申请职位
     * */
    @TableField(value = "application_position")
    private String applicationPosition;


}
