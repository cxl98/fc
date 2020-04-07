package com.easyarch.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
    private static String ymd = "yyyy-MM-dd";
    private static String ymdhms = "yyyy-MM-dd HH:mm:ss";
    private static SimpleDateFormat sdf ;

    public static String getAllTime(){
        sdf = new SimpleDateFormat(ymdhms);
        return sdf.format(new Date());
    }
}
