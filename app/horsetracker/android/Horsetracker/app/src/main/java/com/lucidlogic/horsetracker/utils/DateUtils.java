package com.lucidlogic.horsetracker.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ian on 06/06/16.
 */
public class DateUtils {



    public static final String RACECARD_DATE_FORMAT = "dd-MM-yyyy";
    public static final String DISPLAY_RACECARD_DATE_FORMAT = "EEEE dd MMM";

    public static final ThreadLocal<SimpleDateFormat> SIMPLE_DATE_FORMAT = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue() {
            return  new SimpleDateFormat(RACECARD_DATE_FORMAT);
        }
    };

    public static final ThreadLocal<SimpleDateFormat> DISPLAY_SIMPLE_DATE_FORMAT = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue() {
            return  new SimpleDateFormat(DISPLAY_RACECARD_DATE_FORMAT);
        }
    };

    public static Date getDateFromString(String dateString) throws ParseException {
        return SIMPLE_DATE_FORMAT.get().parse(dateString);
    }

    public static String getStringFromDate(Date dateObject)  {
        return SIMPLE_DATE_FORMAT.get().format(dateObject);
    }

    public static String getDisplayRacecardDateFormat (Date dateObject)  {
        return DISPLAY_SIMPLE_DATE_FORMAT.get().format(dateObject);
    }

    public static boolean compareDate(Date d1, Date d2){
        Calendar cal1 = dateToCalendar(d1);
        Calendar cal2 = dateToCalendar(d2);
        removeTimeFromCal(cal1);
        removeTimeFromCal(cal2);
        return cal1.getTimeInMillis() == cal2.getTimeInMillis();

    };

    public static Calendar dateToCalendar(Date dt){
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(dt);
        return cal1;
    }

    private static void removeTimeFromCal(Calendar calendar){
        calendar.clear(Calendar.HOUR_OF_DAY);
        calendar.clear(Calendar.HOUR);
        calendar.clear(Calendar.AM_PM);
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);
    }

}
