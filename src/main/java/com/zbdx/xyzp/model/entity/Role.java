package com.zbdx.xyzp.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
@Data
@TableName(value = "role")
public class Role  implements Serializable {

    /**
     *id
     * */
    @TableId(value = "role_id", type = IdType.AUTO)
    private Integer roleId;

    /**
     *用户类型
     * */
    @TableField(value = "role_type")
    private String roleType;

    /**
     *用户类型值
     * */
    @TableField(value = "type_key")
    private Integer typeKey;
}
