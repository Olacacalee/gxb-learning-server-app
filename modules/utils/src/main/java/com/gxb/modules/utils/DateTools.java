package com.gxb.modules.utils;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * @author lh
 */
public class DateTools {
    private static final String DATE = "yyyy-MM-dd";
    private static final String DATE_TIME = "yyyy-MM-dd HH:mm";
    private static final String DATE_TIME_WEB = "yyyy/MM/dd HH:mm";
    private static final String TIME = "yyyy-MM-dd HH:mm:ss";

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE);
    private static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat(DATE_TIME);
    private static final SimpleDateFormat dateWebTimeFormat = new SimpleDateFormat(DATE_TIME_WEB);
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat(TIME);

    /**
     * 获取现在时间
     *added by yyang 2016-02-29
     * @return 如果date为空，并且format为空就把当前时间按照yyyy-MM-dd HH:mm:ss返回
     */
    public static String getStringDate(Date date,String format) {
        if(StringUtils.isBlank(format)){
            format = "yyyy-MM-dd HH:mm:ss" ;
        }
        if(date == null){
            date = new Date();
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 获取现在时间
     *added by yyang 2016-02-29
     * @return 如果date为空，并且format为空就把当前时间按照yyyy-MM-dd HH:mm:ss返回
     */
    public static String getStringDate(Long date,String format) {
        if(StringUtils.isBlank(format)){
            format = "yyyy-MM-dd HH:mm:ss" ;
        }
        Date now = null ;
        if(date != null){
            now = new Date(date);
        }else{
            now = new Date();
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String dateString = formatter.format(date);
        return dateString;
    }

    public static Date getCurrentDateTime() {
        Date date = new Date();
        return date;
    }

    /**
     * 获取yy/mm/dd
     * @return
     */
    public static String getYearMonthDay() {
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        String year = StringTools.getString(c.get(Calendar.YEAR));
        String month = StringTools.getString(c.get(Calendar.MONTH)+1);
        String date = StringTools.getString(c.get(Calendar.DATE));
        return year.substring(year.length()-2,year.length()) + "/" +(month.length()==1 ? "0" + month : month) + "/" + (date.length()==1 ? "0" + date : date)  ;
    }

    public static String compareDate(Date date1, Date date2) {
        if(date1==null){
            return StringTools.getString(0);
        }
        int days = 0;
        try {
            double diff = (double)date1.getTime() - date2.getTime();
            days = (int)Math.floor((diff / (1000 * 60 * 60 * 24)));
        } catch (Exception e) {
        }
        return String.valueOf(days);
    }

    public static String postFormatDate(Date postdate) {
        if(postdate==null||postdate.getTime()==0){
            return null;
        }
        long time = 0;
        try {
            long diff = new Date().getTime() - postdate.getTime();
            time = diff / 1000;
        } catch (Exception e) {
        }
        if (time < 60) {
            return "刚刚";
        } else if (time < 60 * 60) {
            return (time / 60) + "分钟前";
        } else if (time < 60 * 60 * 24) {
            return (time / (60 * 60)) + "小时前";
        }  else{
            return StringTools.getString(format(new Date(postdate.getTime()), DateType.DATE_TIME));
        }

    }

    public enum DateType {
        DATE,
        DATE_TIME,
        DATE_TIME_WEB,
        TIME
    }

    public static Timestamp getTime(String strTime) {
        return getTime(strTime, DateType.DATE);
    }

    public static Date getDate(String strTime) {
        if(strTime==null||strTime.length()==0){
            return null;
        }
        return new Date(StringTools.getLong(strTime));
    }

    public static String format(Date date) {
        if (date == null) {
            return null;
        }
        return format(date, DateType.DATE);
    }

    public static String format(Date date, DateType dateType) {
        if (date == null) {
            return null;
        }

        if (Objects.equals(dateType.name(), DateType.DATE.name())) {
            return dateFormat.format(date);
        } else if (Objects.equals(dateType.name(), DateType.DATE_TIME.name())) {
            return dateTimeFormat.format(date);
        } else if (Objects.equals(dateType.name(), DateType.DATE_TIME_WEB.name())) {
            return dateWebTimeFormat.format(date);
        } else if (Objects.equals(dateType.name(), DateType.TIME.name())) {
            return timeFormat.format(date);
        }
        return "";
    }

    public static Timestamp getTime(String strTime, DateType dateType) {
        if(strTime==null){
            return null;
        }
        Date d = null;
        Timestamp timestamp = null;
        try {
            if (Objects.equals(dateType.name(), DateType.DATE.name())) {
                d = dateFormat.parse(strTime);
            } else if (Objects.equals(dateType.name(), DateType.DATE_TIME.name())) {
                d = dateTimeFormat.parse(strTime);
            }else if (Objects.equals(dateType.name(), DateType.DATE_TIME_WEB.name())) {
                d = dateWebTimeFormat.parse(strTime);
            }
            if (d != null) {
                timestamp = new Timestamp(d.getTime());
            }
        } catch (ParseException e) {
            ExceptionTools.unchecked(e);
        }
        return timestamp;
    }

    /**
     * 获取距离当前日期N天的日期 正值为之后日期 负值为之前日期
     * @param days
     * @return
     */
    public static Date getTimeByDay(Integer days) {
        if (days == null) {
            return null;
        }
        Calendar theCa = Calendar.getInstance();
        theCa.setTime(DateTools.getCurrentDateTime());
        theCa.add(theCa.DATE, days);
        Date date = theCa.getTime();
        return date;

    }

    /**
     * 获取距离当前日期N月的日期 正值为之后日期 负值为之前日期
     * @param mons
     * @return
     */
    public static Date getTimeByMonth(Date oldDate, Integer mons) {
        if (mons == null) {
            return null;
        }
        Calendar theCa = Calendar.getInstance();
        theCa.setTime(oldDate);
        theCa.add(theCa.MONTH, mons);
        Date date = theCa.getTime();
        return date;
    }

    /**
     * 获取时分秒毫秒
     * @return
     */
    public static String getDayMonth() {
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        String year = StringTools.getString(c.get(Calendar.YEAR));
        String month = StringTools.getString(c.get(Calendar.MONTH)+1);
        String date = StringTools.getString(c.get(Calendar.DATE));
        String hour = StringTools.getString(c.get(Calendar.HOUR_OF_DAY));
        String minute = StringTools.getString(c.get(Calendar.MINUTE));
        String second = StringTools.getString(c.get(Calendar.SECOND));
        String millisecond = StringTools.getString(c.get(Calendar.MILLISECOND));
        return year + month + date + hour + minute + second + millisecond;
    }

    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    public static String dateToStrLong(java.util.Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 获取yy/mm/dd
     * @return
     */
    public static String getYearMonthAndDay() {
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        String year = StringTools.getString(c.get(Calendar.YEAR));
        String month = StringTools.getString(c.get(Calendar.MONTH)+1);
        String date = StringTools.getString(c.get(Calendar.DATE));
        return year.substring(year.length()-2,year.length()) +(month.length()==1 ? "0" + month : month) + "/" + (date.length()==1 ? "0" + date : date)  ;
    }

}
