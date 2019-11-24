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

    public static void main(String[] args) {
        System.out.println(getDate());
    }
}
