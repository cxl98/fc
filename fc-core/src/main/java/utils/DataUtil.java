package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtil {
    private static String idFormat="yyyyMMddHHmm";
    private static Date mDate ;
    private static SimpleDateFormat sDate;


    /*
    获取当前时间并将 时间转换为字符串id
     */
    public static String dateToId(){
        mDate = new Date();
        sDate = new SimpleDateFormat(idFormat);
        return sDate.format(mDate);
    }

}
