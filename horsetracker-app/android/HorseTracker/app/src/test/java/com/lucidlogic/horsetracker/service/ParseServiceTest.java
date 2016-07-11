package com.lucidlogic.horsetracker.service;

import android.content.Context;

import com.lucidlogic.horsetracker.model.MeetingDTO;
import com.lucidlogic.horsetracker.model.RaceDTO;
import com.lucidlogic.horsetracker.model.Racecard;
import com.lucidlogic.horsetracker.model.RacecardDTO;
import com.lucidlogic.horsetracker.presenter.impl.RacecardPresenterImpl;
import com.lucidlogic.horsetracker.view.RacecardView;
import com.parse.Parse;
import com.parse.ParseObject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;

import rx.observables.BlockingObservable;

import static org.mockito.Mockito.mock;

/**
 * Created by ian on 10/07/16.
 */
public class ParseServiceTest extends ParseTest{

    @Test
    public void getRacecardsTest(){
        RacecardDTO racecard = ParseService.getRacecards(new Date()).toBlocking().first();
        Assert.assertNotNull(racecard);
    }
}
