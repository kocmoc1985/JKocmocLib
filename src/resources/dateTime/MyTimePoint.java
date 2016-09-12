/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.dateTime;

import org.jfree.data.time.Second;

/**
 * This easy class is only to easy create a instance of "Second" class
 * @author Administrator
 */
public class MyTimePoint {

    private Second SECOND;
    private String dateNoTime;
    private String timeNoDate;
    private String initialDateTimeString;
    private String seconds;
    private String hours;
    private String minutes;

    /**
     * 
     * @param dateTimeString the date time string from the DB as it is : 2011-10-25 15:28:04
     */
    public MyTimePoint(String dateTimeString) {
        initialDateTimeString = dateTimeString;
        String[] timeFromDateExtract = dateTimeString.split(" ");
        dateNoTime = timeFromDateExtract[0];
        timeNoDate = timeFromDateExtract[1];
        

        int[] t = extractHourMinSec(dateTimeString);

        seconds = "" + t[2];
        minutes = "" + t[1];
        hours = "" + t[0];
        SECOND = new Second(t[2], t[1], t[0], t[5], t[4], t[3]);
    }

    /**
     * 
     * @return
     */
    public Second getSecond() {
        return this.SECOND;
    }

    public String getTimePlusXSeconds(int secToAdd) {
        Second s = SECOND;
        for (int i = 0; i < secToAdd; i++) {
            s = (Second) s.next();
        }
        String timeToReturn = "";

        String hour = "" + s.getMinute().getHourValue();
        String min = "" + s.getMinute().getMinute();
        String sec = "" + s.getSecond();

        if (hour.length() == 1) {
            hour = "0" + hour;
        }

        if (min.length() == 1) {
            min = "0" + min;
        }

        if (sec.length() == 1) {
            sec = "0" + sec;
        }
        timeToReturn += hour + ":" + min + ":" + sec;
        return timeToReturn;
    }

    public String getTimeDatePlusXSeconds(int secToAdd) {
        Second s = SECOND;
        for (int i = 0; i < secToAdd; i++) {
            s = (Second) s.next();
        }
        String timeToReturn = "";
        String dateToReturn = dateNoTime;

        String hour = "" + s.getMinute().getHourValue();
        String min = "" + s.getMinute().getMinute();
        String sec = "" + s.getSecond();

        if (hour.length() == 1) {
            hour = "0" + hour;
        }

        if (min.length() == 1) {
            min = "0" + min;
        }

        if (sec.length() == 1) {
            sec = "0" + sec;
        }
        timeToReturn += hour + ":" + min + ":" + sec;

        return "" + dateToReturn + " " + timeToReturn;
    }
    

    public static String getTimePlusXSeconds(int secToAdd, Second second_act) {
        Second s = second_act;
        for (int i = 0; i < secToAdd; i++) {
            s = (Second) s.next();
        }
        String timeToReturn = "";

        String hour = "" + s.getMinute().getHourValue();
        String min = "" + s.getMinute().getMinute();
        String sec = "" + s.getSecond();

        if (hour.length() == 1) {
            hour = "0" + hour;
        }

        if (min.length() == 1) {
            min = "0" + min;
        }

        if (sec.length() == 1) {
            sec = "0" + sec;
        }
        timeToReturn += hour + ":" + min + ":" + sec;
        return timeToReturn;
    }
    /**
     * 
     * @param date 
     */
    public static String getDateNoTime(String date){
        String[]dateNoTime = date.split(" ");
        return dateNoTime[0];
    }

    public Second getSecondPlusX(int secToAdd, Second second_act) {
        Second s = second_act;
        for (int i = 0; i < secToAdd; i++) {
            s = (Second) s.next();
        }

        return s;
    }

    /**
     * This method extracts the dateTime from the date/time String which is 
     * aquired from the DB and looks like 2011-10-25 15:28:04
     * @param dateTime
     * @return 
     */
    private int[] extractHourMinSec(String dateTime) {
        String[] timeFromDateExtract = dateTime.split(" ");
        String[] timeExtract = timeFromDateExtract[1].split(":");
        String[] dateExtract = timeFromDateExtract[0].split("-");

        int year = Integer.parseInt(dateExtract[0]);
        int month = Integer.parseInt(dateExtract[1]);
        int day = Integer.parseInt(dateExtract[2]);

        int hourr = Integer.parseInt(timeExtract[0]);
        int min = Integer.parseInt(timeExtract[1]);
        int sec = Integer.parseInt(timeExtract[2]);
        int[] t = {hourr, min, sec, year, month, day};
        return t;
    }

    public String getDate() {
        return this.dateNoTime;
    }

    public String getTime() {
        return this.timeNoDate;
    }

    public String getInitialDateTimeString() {
        return this.initialDateTimeString;
    }

    public String getSeconds() {
        return this.seconds;
    }

    public String getHours() {
        return this.hours;
    }

    public String getMinutes() {
        return this.minutes;
    }
//    public static void main(String[] args) {
//        TimePoint tp = new TimePoint("2011-10-25 15:28:04");
//        System.out.println("" + tp.getTimeDatePlusXSeconds(189));
////        System.out.println("" + tp.getSecond().toString());
//    }
}
