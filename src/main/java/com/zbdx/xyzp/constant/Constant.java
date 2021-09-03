package com.zbdx.xyzp.constant;

/**
 * @Author hanyuhao
 * @PackageName com.zbdx.xyzp.constant
 * @Class Constant
 * @Date 2021/8/18 15:00
 */
public class Constant {

    /**
     * 默认密码
     */
    public static final String PASSWORD = "123456abC";

    /**
     * 默认角色编号
     */
    public static final Integer TYPEKEY = 0;

    /**
     * 模板路径
     */
    public static final String EXCEL_TEMP_PATH = "classpath:template/static/excel/";

    /**
     * 默认用户头像 男 女
     */
    public static final String PHOTO_MAN = "http://bpic.588ku.com/element_origin_min_pic/00/85/67/3156e965da25551.jpg";

    public static final String PHOTO_WOMAN = "";

    /**
     * 审核状态：0，未审核，1，审核通过，2，审核未通过
     */
    public static final Integer WITHOUT_APPLICATION = 0;

    public static final Integer APPLICATION_PASS = 1;

    public static final Integer APPLICATION_NOT_PASS = 2;

}
