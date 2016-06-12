package com.lucidlogic.horsetracker;

import com.lucidlogic.horsetracker.utils.DateUtils;

import junit.framework.Assert;

import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by ian on 08/06/16.
 */
public class DateUtilsTest {

    @Test
    public void testCompareDate(){
        Date d1 =  new Date();
        Date d2 =  new Date();
//        Assert.assertEquals(d1,d2);
        d2.setHours(3);
        Assert.assertTrue(DateUtils.compareDate(d1,d2));
    }

    @Test
    public void testGateDateFromString() throws ParseException {

        Date dt = DateUtils.getDateFromString("04-06-2016");
        log(dt.toString());
        log(DateUtils.getDisplayRacecardDateFormat(dt));
    }

    private void log(String s) {
        System.out.println(s);
    }

}
