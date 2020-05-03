package com.management.property.shiro;

import org.apache.poi.util.StringUtil;
import org.apache.shiro.util.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

public class Datetime {//时间转换
    public static final String STANDARD="yyyy-MM-dd HH:mm:ss";

    public static Date strToDate(String dateTimeStr){//字符串转时间
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(STANDARD);
        DateTime datatime = dateTimeFormatter.parseDateTime(dateTimeStr);
        return datatime.toDate();
    }

    public static Date strToDate(String dateTimeStr,String formatStr){//字符串转时间
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(formatStr);
        DateTime datatime = DateTime.parse(dateTimeStr, dateTimeFormatter);
        return datatime.toDate();
    }

    public static String dateToStr(Date date){//时间转字符串
        if(date == null){
            return StringUtils.EMPTY_STRING;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(STANDARD);
    }

    public static String dateToStr(Date date,String format){//时间转字符串
        if(date == null){
            return StringUtils.EMPTY_STRING;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(format);
    }
}
