package com.zbdx.xyzp.util;

import java.util.regex.Pattern;

public class RegexUtils {


    private static final Pattern PHONE_PATTERN = Pattern.compile("^[1]\\d{10}$");

    private static final Pattern USERNAME_PATTERN = Pattern.compile("[a-zA-Z0-9]*");

    private static final Pattern AGE_PATTERN = Pattern.compile("^((1[0-5])|[1-9])?\\d$");

    private static final Pattern EMAIL_REGEX = Pattern.compile("^\\s*?(.+)@(.+?)\\s*$");

    private static final Pattern ID_NUMBER__REGEX = Pattern.compile("(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" +
            "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)");

    private static final Pattern PASSWORD__REGEX = Pattern.compile("^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\\\\W_]+$)(?![a-z0-9]+$)(?![a-z\\\\W_]+$)(?![0-9\\\\W_]+$)[a-zA-Z0-9\\\\W_]{8,}$");

    public static boolean validateAge(String in) {
        return AGE_PATTERN.matcher(in).matches();
    }

    public static boolean validateUserName(String in) {
        return USERNAME_PATTERN.matcher(in).matches();
    }

    public static boolean validatePassword(String in) {
        return PASSWORD__REGEX.matcher(in).matches();
    }

    public static boolean validateEmail(String in) {
        return EMAIL_REGEX.matcher(in).matches();
    }

    /**
     *  正则：手机号（简单）, 1字头＋10位数字即可.
     */
    public static boolean validateMobilePhone(String in) {
        return PHONE_PATTERN.matcher(in).matches();
    }

    /**
     * 校验身份证
     */
    public static boolean validateIdNumber(String idnumber) {
        if (idnumber == null || "".equals(idnumber)) {
            return false;
        }
        // 定义判别用户身份证号的正则表达式（15位或者18位，最后一位可以为字母）
        boolean matches = ID_NUMBER__REGEX.matcher(idnumber).matches();
        //判断第18位校验值
        if (matches) {
            if (idnumber.length() == 18) {
                try {
                    char[] charArray = idnumber.toCharArray();
                    //前十七位加权因子
                    int[] idCardWi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
                    //这是除以11后，可能产生的11位余数对应的验证码
                    String[] idCardY = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
                    int sum = 0;
                    for (int i = 0; i < idCardWi.length; i++) {
                        int current = Integer.parseInt(String.valueOf(charArray[i]));
                        int count = current * idCardWi[i];
                        sum += count;
                    }
                    char idCardLast = charArray[17];
                    int idCardMod = sum % 11;
                    if (idCardY[idCardMod].toUpperCase().equals(String.valueOf(idCardLast).toUpperCase())) {
                        return true;
                    } else {
                        return false;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return matches;
    }
}
