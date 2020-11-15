package com.tgroup.utils;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * @author yqf
 * @date 2020 下午7:45
 */
public class DateUtil {
    public Long timeStamp = System.currentTimeMillis();

    /**
     * 获取标准时间时间
     * */
    public Date createTime(){
        return  new Date(timeStamp);
    }

    /**
     * 获取自定义时间
     * */
    public String createTime(String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.parseLong(String.valueOf(timeStamp))));
    }


}
