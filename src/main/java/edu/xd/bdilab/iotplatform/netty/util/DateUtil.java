package edu.xd.bdilab.iotplatform.netty.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String getDate(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static String getDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static Date stringToDate(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date temp = null;
        try {
            temp = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return temp;
    }

    private static Date StringToDate(String time){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    public static void main(String[] args) {
        String s1="2019-12-02 11:13:15";
        Date date1 = DateUtil.stringToDate(s1);
        String s2 = "2019-12-02 11:16:45";
        Date date2 = DateUtil.stringToDate(s2);
        System.out.println(date2.after(date1));
    }
}
