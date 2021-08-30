package com.zbdx.xyzp.model.dto;
import lombok.Data;
import java.io.Serializable;
@Data
public class RoleDTO implements Serializable {

    /**
     *id
     * */
    private Integer roleId;

    /**
     *用户类型
     * */
    private String roleType;

    /**
     *用户类型值
     * */
    private Integer typeKey;
}
