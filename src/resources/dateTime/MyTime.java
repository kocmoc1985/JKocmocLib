/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.dateTime;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.commons.net.ntp.TimeStamp;
import resources.SQL.SqlBasicLocal;

/**
 *
 * @author Administrator
 */
public class MyTime {

    private static long[] date_list = {new Long("1352475262539"), new Long("1352474779836"), new Long("1352452070961")};
    private static long ONE_DAY_MILLIS = 86400000;

    
    
    /**
     * [2020-07-24] Fully working
     * Will return false for: "2020-07-31" and for "2020-18-20"
     * @param date_yyyy_mm_dd
     * @return 
     */
    public static boolean isDateValid(String date_yyyy_mm_dd) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        return sdf.parse(date_yyyy_mm_dd, new ParsePosition(0)) != null;
    }

    

    public static boolean isSaturdayOrSunday() {
        //
        Calendar c = Calendar.getInstance();
        //
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        //
//        System.out.println("day of week: " + dayOfWeek);
        //
        return dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY;
    }

    public static Timestamp dateToSqlTimeStamp(String date) {
        //
        String dateFormat = define_date_format(date);
        //
        if (dateFormat != null) {
            //
            long millis = dateToMillisConverter3(date, dateFormat);
            return new Timestamp(millis);
            //
        } else {
            return null;
        }
    }

    public static void setTimeStampSql(SqlBasicLocal sql, String date) {
        //
        String dateFormat = define_date_format(date);
        //
        if (dateFormat != null) {
            //
            long millis = dateToMillisConverter3(date, dateFormat);
            Timestamp timestamp = new Timestamp(millis);
            //
            try {
                sql.getPreparedStatement().setTimestamp(1, timestamp);
            } catch (SQLException ex) {
                Logger.getLogger(MyTime.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //
    }

    /**
     * Rather Important
     *
     * @param date
     * @param dateFormat
     * @return
     */
    public static String javaDateToGivenFormat(Date date, String dateFormat) {
        return millisToDateConverter("" + date.getTime(), dateFormat);
    }

    /**
     * SUPER IMPORTANT This method defines the date format of the input
     * string/date So the input string/date = 26/06/14 will return dd/MM/yy
     *
     * @param d
     * @return
     */
    public static String define_date_format(String date) {
        if (date != null) {
            for (String parse : formats) {
                SimpleDateFormat sdf = new SimpleDateFormat(parse);
                try {
                    sdf.parse(date);
                    return parse;
                } catch (ParseException e) {
                    //Do nothing
                }
            }
        }
        return null;
    }
    /**
     * It's best not to change anything here
     */
    private static final String[] formats = {
        "yyyy-MM-dd'T'HH:mm:ss'Z'", "yyyy-MM-dd'T'HH:mm:ssZ",
        "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
        "yyyy-MM-dd'T'HH:mm:ss.SSSZ", "yyyy-MM-dd HH:mm:ss",
        "MM/dd/yyyy HH:mm:ss", "MM/dd/yyyy'T'HH:mm:ss.SSS'Z'",
        "MM/dd/yyyy'T'HH:mm:ss.SSSZ", "MM/dd/yyyy'T'HH:mm:ss.SSS",
        "MM/dd/yyyy'T'HH:mm:ssZ", "MM/dd/yyyy'T'HH:mm:ss",
        "yyyy:MM:dd HH:mm:ss",
        "yyyy-MM-dd", "yyyy:MM:dd", "yyyyMMdd",
        "dd/MM/yy", "dd/MM/yyyy", "dd-MM-yy", "dd-MM-yyyy",
        "dd:MM:yy", "dd:MM:yyyy"};

    /**
     * Check if a string is date by using regular expression. This method
     * verifies dates in format like: 2014-05-21 (pay attention to "-")
     *
     * @param value_yyyy_MM_dd
     * @return
     */
    private static boolean checkIfDate(String value_yyyy_MM_dd) {
        if (value_yyyy_MM_dd.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return true;
        }
        return false;
    }

    private static boolean checkIfDate2(String value_dd_MM_yyyy) {
        if (value_dd_MM_yyyy.matches("\\d{2}-\\d{2}-\\d{4}")) {
            return true;
        }
        return false;
    }

    /**
     * Checks a date against the given format Ex: Check if date-> 2012-05-11 is
     * of format = yyyyMMdd -> the method will return false
     *
     * @param date
     * @param required_format
     * @return
     */
    public static boolean checkDateFormat(String date, String required_format) {
        DateFormat formatter = new SimpleDateFormat(required_format);
        long x;
        try {
            x = formatter.parse(date).getTime();
        } catch (ParseException ex) {
            Logger.getLogger(MyTime.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        String verify_date;
        verify_date = millisToDateConverter("" + x, required_format);

        if (verify_date.equals(date) == false) {
            return false;
        }
        if (x < 0) {
            return false;
        }
        return true;
    }

    /**
     * Finds among the date_list entry for a date which is nearest 'date'
     * parameter
     *
     * @param date - the date for which the nearest date is to be found
     * @param dates_list - array of dates
     * @return
     */
    public static long findNearestDateTo(long date, long[] dates_list) {
        long nearest_date = 0;
        long diff_temp = 0;
        long diff_min = Long.MAX_VALUE;
        for (long date_temp : dates_list) {
            System.out.println("anlalysing date: " + millisToDateConverter("" + date_temp));
            System.out.println("diviation: " + Math.abs(date - date_temp));
            diff_temp = Math.abs(date - date_temp);
            if (diff_temp < diff_min) {
                diff_min = diff_temp;
                nearest_date = date_temp;
            }
        }
        System.out.println("nearest date: " + millisToDateConverter("" + nearest_date));
        return nearest_date;
    }

    /**
     * ("2014-03-21 11:19:15.45", "yyyy-MM-dd HH:mm:ss"
     *
     * @param date1
     * @param date_format1
     * @param date2
     * @param date_format2
     * @return
     */
    public static long get_diff_between_two_dates(String date1, String date_format1, String date2, String date_format2) {
        long ms_date1 = dateToMillisConverter3(date1, date_format1);
        long ms_date2 = dateToMillisConverter3(date2, date_format2);
        return ms_date1 - ms_date2;
    }
    
    /**
     * 
     * @param date1
     * @param date_format1
     * @param date2
     * @param date_format2
     * @return 
     */
    public static boolean compareDates(String date1, String date_format1, String date2, String date_format2){
         //
         long ms_date1 = dateToMillisConverter3(date1, date_format1);
         long ms_date2 = dateToMillisConverter3(date2, date_format2);
         //
         if(ms_date1 > ms_date2){
             return true;
         }else{
             return false;
         }
    }

    public static void main(String[] args) {
        System.out.println("" + compareDates("2020-09-24", "yyyy-MM-dd", "2020-09-22 13:00:00", "yyyy-MM-dd HH:mm:ss"));
    }
    
    /**
     * Usage example: get_date_time_minus_some_time_in_ms("2014-03-21
     * 11:19:15.45", "yyyy-MM-dd HH:mm:ss", 600000));
     *
     * @param date
     * @param date_format
     * @param time_to_minus - vremja kotoroje nado vichastj
     * @return
     */
    public static String get_date_time_minus_some_time_in_ms(String date, String date_format, long time_to_minus) {
        long ms = dateToMillisConverter3(date, date_format);
        long new_date_in_ms = ms - time_to_minus;
        String new_date = millisToDateConverter("" + new_date_in_ms, date_format);
        return new_date;
    }

    public static String get_date_time_plus_some_time_in_ms(String date, String date_format, long time_to_plus) {
        long ms = dateToMillisConverter3(date, date_format);
        long new_date_in_ms = ms + time_to_plus;
        String new_date = millisToDateConverter("" + new_date_in_ms, date_format);
        return new_date;
    }

    public static String get_date_time_plus_some_time_in_days(String date, long days) {
        String date_format = "yyyy-MM-dd";
        long time_to_plus = 86400000 * days;
        long ms = dateToMillisConverter3(date, date_format);
        long new_date_in_ms = ms + time_to_plus;
        String new_date = millisToDateConverter("" + new_date_in_ms, date_format);
        return new_date;
    }

    public static String get_date_time_minus_some_time_in_days(String date, long days) {
        String date_format = "yyyy-MM-dd";
        long time_to_minus = 86400000 * Math.abs(days);
        long ms = dateToMillisConverter3(date, date_format);
        long new_date_in_ms = ms - time_to_minus;
        String new_date = millisToDateConverter("" + new_date_in_ms, date_format);
        return new_date;
    }

    /**
     * Ex: transforms a date like "10-01-2013" to "2013-01-10"
     *
     * @param date - date to transform
     * @param date_format_1 - date format of the date to be formatted
     * @param date_format_2 - the required date format
     * @return
     * @throws ParseException
     */
    public static String dateToDateConverter(String date, String date_format_1, String date_format_2) throws ParseException {
        DateFormat formatter = new SimpleDateFormat(date_format_1);
        long x = formatter.parse(date).getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(x);
        formatter = new SimpleDateFormat(date_format_2);
        return formatter.format(calendar.getTime());
    }

    /**
     *
     * @param millis
     * @param format
     * @return
     */
    public static String millisToDateConverter(String millis, String format) {
        DateFormat formatter = new SimpleDateFormat(format); // this works to!
        long now = Long.parseLong(millis);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(now);
        return formatter.format(calendar.getTime());
    }

    /**
     * Converts milliseconds into date
     *
     * @param millis That millis is a String is not a mistake!
     * @return
     * @last feb-2012
     * @tags millis to date ; millisToDate ; milliseconds to date ;
     * millisecondsToDate
     */
    public static String millisToDateConverter(String millis) {

//        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS"); //this works!
        //note if to write hh instead of HH it will show like 03:15:16 and not 15:15:16
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // this works to!

        long now = Long.parseLong(millis);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(now);

//        System.out.println(now + " = " + formatter.format(calendar.getTime()));
        return formatter.format(calendar.getTime());
    }

    public static String millisToTimeConverter(String millis) {

//        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS"); //this works!
        //note if to write hh instead of HH it will show like 03:15:16 and not 15:15:16
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss"); // this works to!

        long now = Long.parseLong(millis);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(now);

//        System.out.println(now + " = " + formatter.format(calendar.getTime()));
        return formatter.format(calendar.getTime());
    }

    /**
     * Converts date into milli seconds
     *
     * @param date_yyyy_MM_dd - should be written as ex. 2012-02-02
     * @return
     * @tags date to milliseconds, date to millis, dateToMillis
     */
    public static long timeToMillisConverter(String date_HH_mm_ss) {
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss"); // this works to!
        try {
            return formatter.parse(date_HH_mm_ss).getTime();
        } catch (ParseException ex) {
            Logger.getLogger(MyTime.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

    }

    /**
     * Converts date into milli seconds
     *
     * @param date_yyyy_MM_dd - should be written as ex. 2012-02-02
     * @return
     * @tags date to milliseconds, date to millis, dateToMillis
     */
    public static long dateToMillisConverter(String date_yyyy_MM_dd) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // this works to!
        try {
            return formatter.parse(date_yyyy_MM_dd).getTime();
        } catch (ParseException ex) {
            Logger.getLogger(MyTime.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    /**
     * Converts date into milli seconds
     *
     * @param date_yyyy_mm_dd - should be written as ex. 2012-02-02 16:34:22
     * @return
     * @tags date to milliseconds, date to millis, dateToMillis
     */
    public static long dateToMillisConverter2(String date_yyyy_MM_dd__HH_mm_ss) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return formatter.parse(date_yyyy_MM_dd__HH_mm_ss).getTime();
        } catch (ParseException ex) {
            Logger.getLogger(MyCalcDiffBetweenTwoTimePoints.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    /**
     * Note that the MM should be written in upper case Example ddMMyyyy Example
     * "yyyy-MM-dd HH:mm:ss" Example "dd/MM/yyyy hh:mm:ss.SSS"
     *
     * @param date
     * @param date_format
     * @return
     */
    public static long dateToMillisConverter3(String date, String date_format) {
        DateFormat formatter = new SimpleDateFormat(date_format);
        try {
            return formatter.parse(date).getTime();
        } catch (ParseException ex) {
            Logger.getLogger(MyCalcDiffBetweenTwoTimePoints.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
    
    /**
     * Will return "act-yera-01-01" (2021-01-01)
     * @return 
     */
     public static String get_act_year_first_date() {
        DateFormat formatter = new SimpleDateFormat("yyyy");
        Calendar calendar = Calendar.getInstance();
        return formatter.format(calendar.getTime()) + "-01-01";
    }

    /**
     * This method is the best one to get the local default time used on the
     * computer
     *
     * @return
     */
    public static String get_proper_date_and_time_default_format() {
        TimeZone tz = TimeZone.getDefault();
        Calendar cal = Calendar.getInstance(tz);
        DateFormat f1 = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM);
        Date d = cal.getTime();
//        System.out.println(f1.format(d));
        return f1.format(d);
    }

    /**
     *
     * @param style
     * @return
     */
    public static String get_proper_date_adjusted_format(int style) {
        TimeZone tz = TimeZone.getDefault();
        Calendar cal = Calendar.getInstance(tz);
        DateFormat f1 = DateFormat.getDateInstance(style);
        Date d = cal.getTime();
        return f1.format(d);
    }

    public static String millisToDefaultDate(long millis) {
        TimeZone tz = TimeZone.getDefault();
        Calendar cal = Calendar.getInstance(tz);
        //
        int style = 3;
        //
        Locale locale = Locale.getDefault();
        //
        if (locale == Locale.GERMAN || locale == Locale.GERMANY || locale.getCountry().equals("CH")) {
            style = 2;
        }
        //
        DateFormat f1 = DateFormat.getDateInstance(style);
        cal.setTimeInMillis(millis);
        Date d = cal.getTime();
        return f1.format(d);
    }

    public static String get_date_time_plus_some_time_in_ms_temp(String date, String date_format, long time_to_minus) {
        long ms = dateToMillisConverter3(date, date_format);
        long new_date_in_ms = ms + time_to_minus;
        String new_date = millisToDateConverter("" + new_date_in_ms, date_format);
        return new_date;
    }

    public static String get_proper_date_time_same_format_on_all_computers() {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        return formatter.format(calendar.getTime());
    }

    public static String get_today_with_00_00_00() {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        return formatter.format(calendar.getTime()) + " 00:00:00";
    }

    public static String get_today_plus_24h() {
        String dateToday = get_today_with_00_00_00();
        return get_date_time_plus_some_time_in_ms(dateToday, "yyyy-MM-dd HH:mm:ss", ONE_DAY_MILLIS);
    }

    public static String get_today_plus_x_days(long days) {
        long millis = 86400000 * days;
        String dateToday = get_today_with_00_00_00();
        return get_date_time_plus_some_time_in_ms(dateToday, "yyyy-MM-dd", millis);
    }

    public static String get_proper_date_given_format(String format) {
        DateFormat formatter = new SimpleDateFormat(format);
        Calendar calendar = Calendar.getInstance();
        return formatter.format(calendar.getTime());
    }

    /**
     * This one is super good! This will return the same time regardless the
     * machine it's running on
     *
     * @tested
     * @return
     */
    public static String get_proper_time_same_format_on_all_computers() {
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        Calendar calendar = Calendar.getInstance();
        return formatter.format(calendar.getTime());
    }

    /**
     * This method is the best one to get the local default time used on the
     * computer Style 3 is to recommend
     *
     * @return
     */
    public static String get_proper_time_default_format(int style) {
        TimeZone tz = TimeZone.getDefault();
        Calendar cal = Calendar.getInstance(tz);
        DateFormat f1 = DateFormat.getTimeInstance(style);
        Date d = cal.getTime();
        return f1.format(d);
    }

    /**
     *
     * @param date_format
     * @param time_format
     * @return
     */
    public static String get_proper_date_adjusted_format(int date_format, int time_format) {
        TimeZone tz = TimeZone.getDefault();
        Calendar cal = Calendar.getInstance(tz);
        DateFormat f1 = DateFormat.getDateTimeInstance(date_format, time_format);
        Date d = cal.getTime();
        return f1.format(d);
    }

    /**
     * sv,sv for swedisch template EXTREMLY IMPORTANT
     * SOMETIMES***********************************************!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     *
     * @param date_format
     * @param time_format
     * @return
     */
    public static String get_proper_date_adjusted_format_and_locale(int date_format, String lang, String country) {
        Locale l = new Locale(lang, country);
        Locale.setDefault(l);
        TimeZone tz = TimeZone.getDefault();
        Calendar cal = Calendar.getInstance(tz, l);
        DateFormat f1 = DateFormat.getDateInstance(date_format);
        Date d = cal.getTime();
        return f1.format(d);
    }

    /**
     *
     * @param date_format
     * @param time_format
     * @return
     */
    public static String get_proper_date_time_adjusted_format_and_locale(int date_format, int time_format, String lang, String country) {
        Locale l = new Locale(lang, country);
        Locale.setDefault(l);
        TimeZone tz = TimeZone.getDefault();
        Calendar cal = Calendar.getInstance(tz, l);
        DateFormat f1 = DateFormat.getDateTimeInstance(date_format, time_format);
        Date d = cal.getTime();
        return f1.format(d);
    }

    public static String secondsToMiniutesAndSeconds(int seconds) {
        int sec = seconds % 60;
        int min = (seconds - sec) / 60;
        if (sec < 10) {
            return min + ":0" + sec;
        } else {
            return min + ":" + sec;
        }
    }

    public static double millis_to_seconds_converter(long millis) {
        return millis / 1000;
    }

    /**
     *
     * @param minutes
     * @return
     */
    public static long minutes_to_milliseconds_converter(long minutes) {
        return minutes * 60000;
    }

    /**
     *
     * @param minutes
     * @return
     */
    public static long hours_to_milliseconds_converter(int hours) {
        return hours * 3600000;
    }

    /**
     *
     * @param minutes
     * @return
     */
    public static long days_to_milliseconds_converter(long days) {
        long minutes = 1440 * days;
        return minutes * 60000;
    }
}
