package utils.time;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TimeUtil {
    public static Long perHour = Long.valueOf(3600000);
    public static long perDay = 86400000L;
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final int TIMEZONE_OFFSET = Calendar.getInstance().getTimeZone().getOffset(1);
    public static final String UTC_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";


    //时间戳转化为日期
    public static String convertToDate(Long timeStamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH");
        String time = format.format(timeStamp);
        return time;
    }

    public static Long generateTimeStampByTime(String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH");
        Date date = null;
        try {
            date = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Long timeStamp = date.getTime();
        return timeStamp;
    }

    public static Long generateTimeStampByFormat(String format, String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Long timeStamp = date.getTime();
        return timeStamp;
    }

    //时间戳转化为日期
    public static String convertToDateByFormat(Long timeStamp, String format) {
        SimpleDateFormat formatTemp = new SimpleDateFormat(format);
        String time = formatTemp.format(timeStamp);
        return time;
    }

    public static List<String> getDateRange(String start, String end, String... pattern) {
        List<String> res = new ArrayList<String>();
        String p = pattern.length == 0 ? DATE_PATTERN : pattern[0];
        try {
            Date s = parse(start, p);
            Date e = parse(end, p);
            if (s.after(e)) {
                return res;
            }
            Date temp = s;
            do {
                res.add(getDateStr(temp));
                temp = DateUtils.addDays(temp, 1);
            } while (!temp.after(e));
            return res;
        } catch (ParseException e1) {
            e1.printStackTrace();
            return res;
        }
    }

    public static Date parse(String str, String... pattern) throws ParseException {
        String p = pattern.length == 0 ? DATE_PATTERN : pattern[0];
        Date date = DateUtils.parseDate(str, new String[]{p});
        return date;
    }

    //根据模式获取日期
    public static String getDateStr(Date date, String... pattern) {
        String p = pattern.length > 0 ? pattern[0] : DATE_PATTERN;
        return DateFormatUtils.format(date, p);
    }

    //得到该日期的最早的时间戳
    public static Long getStartTimeStamp(Date date) {
        try {
            date = parse(getDateStr(date));
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    //得到该日期的最晚的时间戳
    public static Long getEndTimeStamp(Date date) {
        try {
            date = parse(getDateStr(date));
            date = DateUtils.addDays(date, 1);
            return date.getTime() - 1;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    //获取当前时间
    public static String getCurrentTime() {
        return getTimeStr(new Date());
    }

    //根据模式获取时间
    public static String getTimeStr(Date date, String... pattern) {
        String p = pattern.length > 0 ? pattern[0] : TIME_PATTERN;
        return DateFormatUtils.format(date, p);
    }

    //获取今天日期
    public static String getTodayDate() {
        return getDateStr(new Date());
    }

    public static long getDayTime(long time) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTimeInMillis();
    }

    public static Date getoldDay(Date date, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -n);
        date = calendar.getTime();
        return date;
    }

    public static String transferToUTC(Long timestamp) {
        timestamp = timestamp - 8 * perHour;
        return convertToDateByFormat(timestamp, UTC_PATTERN);
    }

    public static String getISO8601Timestamp(Date date) {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        df.setTimeZone(tz);
        String nowAsISO = df.format(date);
        return nowAsISO;
    }

    public static void main(String[] args) {
        System.out.println(transferToUTC(System.currentTimeMillis()));
    }
}
