package com.zbdx.xyzp.util;

import com.google.common.base.Strings;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ValidUtils {

    /**
     * 非空判断
     * @param message
     * @param param
     * @param fieldCn
     * @param fieldEn
     * @return
     */
    public  static StringBuffer judgeKongParam(StringBuffer message, Map<String,Object> param, String fieldCn, String fieldEn){
        if(param.containsKey(fieldEn)){
            if(Strings.isNullOrEmpty(param.get(fieldEn).toString())){
                message.append("@").append(fieldCn).append(":").append(fieldCn).append("不能为空");
            }
        }else{
            message.append("@").append(fieldEn).append(":").append(fieldCn).append("不能为空");
        }
        return message;
    }

    /**
     * 日期格式判断
     * @param message
     * @param param
     * @param fieldCn
     * @param fieldEn
     * @return
     */
    public  static StringBuffer judgeDateParam(StringBuffer message,Map<String,Object> param,String fieldCn,String fieldEn){
        if(param.containsKey(fieldEn)){
            if(!Strings.isNullOrEmpty(param.get(fieldEn).toString())){
                if (!checkDate(param.get(fieldEn).toString())){
                    message.append("@").append(fieldEn).append(":").append(fieldCn).append("格式不正确");
                }
            }
        }
        return message;
    }

    public  static StringBuffer judgeNumParam(StringBuffer message,Map<String,Object> param,String fieldCn,String fieldEn){
        if(param.containsKey(fieldEn)){
            if(!Strings.isNullOrEmpty(param.get(fieldEn).toString())){
                if (!checkNumber(param.get(fieldEn).toString())){
                    message.append("@").append(fieldEn).append(":").append(fieldCn).append("应为正数");
                }
            }
        }
        return message;
    }

    public  static StringBuffer judgeRatioParam(StringBuffer message,Map<String,Object> param,String fieldCn,String fieldEn){
        if(param.containsKey(fieldEn)){
            if(!Strings.isNullOrEmpty(param.get(fieldEn).toString())){
                if (!checkRatio(param.get(fieldEn).toString())){
                    message.append("@").append(fieldEn).append(":").append(fieldCn).append("格式不正确");
                }
            }
        }
        return message;
    }

    /**
     * 校验生产日期   yyyy-MM-dd
     * @param date 被校验的参数信息
     * @return
     */
    public static boolean checkDate(String date){//yyyy-MM-dd
        //严格校验格式
        if (date.trim().length()!=10){
            return false;
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            format.setLenient(false);
            format.parse(date);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 校验数字
     * @param stringVal
     * @return
     */
    public static boolean checkNumber(String stringVal){
        String check = "^[0-9]*$";
        Pattern pat = Pattern.compile(check);
        Matcher mat = pat.matcher(stringVal);
        boolean number = mat.matches();
        return number;
    }
    
    
    /**
     * 校验正整数和正小数
     * @param stringVal
     * @return
     */
    public static boolean checkElectricity(String stringVal){
        String check ="^[+]{0,1}(\\d+)$|^[+]{0,1}(\\d+\\.\\d+)$";
        Pattern pat = Pattern.compile(check);
        Matcher mat = pat.matcher(stringVal);
        boolean number = mat.matches();
        return number;
    }

    public static boolean checkRatio(String stringVal){

        if (stringVal==null || !stringVal.contains("/")){
            return false;
        }
        String [] strFlag = stringVal.split("/");
        String check = "^\\d+(\\.\\d+)?$";
        Pattern pattern = Pattern.compile(check);
        if (Double.parseDouble(strFlag[0].trim())<=0|| Double.parseDouble(strFlag[1].trim())<=0){
            return false;
        }
        return pattern.matcher(strFlag[0].trim()).matches() && pattern.matcher(strFlag[1].trim()).matches();
    }
}
