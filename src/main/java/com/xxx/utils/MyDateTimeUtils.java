package com.xxx.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// 关于 Date、String 的转化
public class MyDateTimeUtils {

    //设置日期格式
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // 获取时间，String类型，根据传递的参数Date
    public static String getTimeByDate(Date date) {
        return dateFormat.format(date); // new Date()为获取当前系统时间
    }

    // 获取日期，Date类型，根据传递的参数String
    public static Date getDateByTime(String time) throws ParseException {
        return dateFormat.parse(time);
    }

    // 获取当前时间，String类型，根据当前Date
    public static String getNowTime() {
        return dateFormat.format(new Date()); // new Date()为获取当前系统时间
    }

    // 获取当前日期，格式（2021-05）
    public static String getNowMonth() {
        return getNowTime().substring(0,7);
    }

    // 根据年月获取天数
    public static int getLimitsDaysByMonth(String yeayMonth) { // 格式（2021-05）
        int year = Integer.parseInt(yeayMonth.substring(0,4));
        int month = Integer.parseInt(yeayMonth.substring(5,7));

        String nowTime = getNowTime();
        int nowYear = Integer.parseInt(nowTime.substring(0,4));
        int nowMonth = Integer.parseInt(nowTime.substring(5,7));
        int nowDay = Integer.parseInt(nowTime.substring(8,10));

        if (year > nowYear) { // 后面的时间没有数据
            return 0;
        } else if (year == nowYear) {
            if (month > nowMonth) { // 后面的时间没有数据
                return 0;
            } else if (month == nowMonth) { // 当前月，只统计到昨天
                return nowDay-1;
            };
        }
        // 否则全部统计
        if (month == 2) {
            if (judgeLeaps(year)) {
                return 29;
            } else {
                return 28;
            }
        } else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            return 31;
        } else {
            return 30;
        }
    }

    // 判断闰年
    public static boolean judgeLeaps(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    // 判断周几
    public static String getWeekday(String date){//必须yyyy-MM-dd
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdw = new SimpleDateFormat("E");
        Date d = null;
        try {
            d = sd.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sdw.format(d);
    }

    public static int getDiffHours(Date startDate,Date endDate) {
        long nh = 1000 * 60 * 60;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - startDate.getTime();

        // 计算差多少小时
        long hour = diff / nh;

        return (int) hour;
    }

}
