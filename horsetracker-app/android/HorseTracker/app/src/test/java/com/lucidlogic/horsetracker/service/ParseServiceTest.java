package com.lucidlogic.horsetracker.service;

import com.lucidlogic.horsetracker.model.RacecardDTO;

import com.parse.ParseException;
import com.parse.ParseQuery;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;


/**
 * Created by ian on 10/07/16.
 */
public class ParseServiceTest extends ParseTest{

    @Test
    public void getRacecardsTest() throws ParseException {
        ParseQuery parseQuery = ParseQuery.getQuery(RacecardDTO.class);
        List<RacecardDTO> racecardDTOList = parseQuery.find();
        Assert.assertTrue(racecardDTOList.size() > 0);
        RacecardDTO racecard = ParseService.getRacecards(new Date()).toBlocking().first();
        Assert.assertNotNull(racecard);
    }
}
