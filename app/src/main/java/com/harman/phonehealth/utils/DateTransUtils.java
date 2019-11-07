package com.harman.phonehealth.utils;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateTransUtils {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("M-d-yyyy");
    private static final String TAG = "DateTransUtils";
    public static final long DAY_IN_MILLIS = 24 * 60 * 60 * 1000;

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String stamp) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(stamp);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    public static String stampToDate(long stamp) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(stamp);
        res = simpleDateFormat.format(date);
        return res;
    }

    //获取今日某时间的时间戳
    public static long getTodayStartStamp(int hour, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        long todayStamp = cal.getTimeInMillis();

        Log.i("Wingbu", " DateTransUtils-getTodayStartStamp()  获取当日" + hour + ":" + minute + ":" + second + "的时间戳 :" + todayStamp);

        return todayStamp;
    }

    //获取当日00:00:00的时间戳
    public static long getZeroClockTimestamp(long time) {
        long nowTime = System.currentTimeMillis();
        long todayStartTime = nowTime - ((nowTime + TimeZone.getDefault().getRawOffset()) % (24 * 60 * 60 * 1000L));
        Log.d(TAG, "getZeroClockTimestamp: " + todayStartTime);
        return todayStartTime;
    }

    public static long getStartClockTimeStamp(String date) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long startTime = dateFormat.parse(date + " 00:00:00").getTime();
        return startTime;
    }


    public static long getEndClockTimeStamp(String date) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long startTime = dateFormat.parse(date + " 00:00:00").getTime();
        long endTime = dateFormat.parse(date + " 23:59:59").getTime();
        return endTime;

    }

    //获取最近7天的日期,用于查询这7天的系统数据
    public static ArrayList<String> getSevenDays() {
        ArrayList<String> dayList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            dayList.add(getDateString(i));
        }
        return dayList;
    }

    //获取dayNumber天前，当天的日期字符串
    public static String getDateString(int dayNumber) {
        long time = System.currentTimeMillis() - dayNumber * DAY_IN_MILLIS;
        Log.i("Wingbu", " DateTransUtils-getDateString()  获取查询的日期 :" + dateFormat.format(time));
        return dateFormat.format(time);
    }
}