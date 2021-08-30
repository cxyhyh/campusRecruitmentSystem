package com.zbdx.xyzp.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
/**
 * @Author hanyuhao
 * @PackageName com.zbdx.xyzp.model.entity
 * @Class SelfEvaluation
 * @Date 2021/8/30 11:29
 */
@TableName(value = "self_evaluation")
@Data
public class SelfEvaluation implements Serializable {

    /**
     *id
     * */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     *用户名
     * */
    @TableId(value = "username")
    private String username;

    /**
     *自我评价
     * */
    @TableId(value = "self_evaluation")
    private String selfEvaluation;

}
