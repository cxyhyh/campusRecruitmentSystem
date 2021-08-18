package com.zbdx.xyzp.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zbdx.xyzp.model.entity.JianLi;
import com.zbdx.xyzp.model.entity.User;
import lombok.Data;

import java.io.Serializable;

@Data
public class JianLiDTO  extends JianLi implements Serializable {
    /**
     *id
     * */
    private Integer jianliId;


    /**
     *简历地址
     * */
    private String fileUrl;

    /**
     *简历名称
     * */
    private String fileName;

    /**
     *用户真实姓名
     * */
    private String username;


    /**
     *申请职位
     * */
    private String applicationPosition;

}
