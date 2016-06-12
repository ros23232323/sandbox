package com.lucidlogic.horsetracker;

import android.util.Log;

import com.lucidlogic.horsetracker.model.binding.Racecard;
import com.lucidlogic.horsetracker.model.parse.RacecardParse;
import com.lucidlogic.horsetracker.service.ParseService;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.List;

/**
 * Created by ian on 10/06/16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, application = MockApp.class)
public class ParseTests {
    public static final String TAG = ParseTests.class.getName();

    @Test
    @Ignore
    public void createUserTest() throws ParseException {
        ParseUser parseUser = new ParseUser();
        parseUser.setEmail("i@ht.com");
        parseUser.setUsername("i");
        parseUser.setPassword("i");
        parseUser.signUp();
        parseUser.save();
        Log.i(TAG, String.format("user created %s",parseUser.getObjectId()));

    }

    @Test
    public void racecardParseTest() throws ParseException {
        ParseQuery<RacecardParse> racecardParseQuery = ParseQuery.getQuery(RacecardParse.class);
        racecardParseQuery.include("meetings");
        List<RacecardParse> racecardParseList = racecardParseQuery.find();
        Log.i(TAG, String.format("# %d",racecardParseList.size()));

        Racecard racecard = BeanTransformers.racecardFromRacecardParse(racecardParseList.get(0));
        Log.i(TAG, String.format("# %d",racecard.getMeetings().size()));

    }
}
