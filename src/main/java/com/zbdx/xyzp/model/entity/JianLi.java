package com.zbdx.xyzp.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
@Data
@TableName(value = "jianli")
public class JianLi implements Serializable {

    /**
     * id
     */
    @TableId(value = "jianli_id", type = IdType.AUTO)
    private Integer jianliId;

    /**
     * 简历地址
     */
    @TableId(value = "file_url")
    private String fileUrl;

    /**
     * 简历名称
     */
    @TableField(value = "file_name")
    private String fileName;

    /**
     * 用户真实姓名
     */
    @TableField(value = "username")
    private String username;

    /**
     *申请职位
     * */
    @TableField(value = "application_position")
    private String applicationPosition;

}
