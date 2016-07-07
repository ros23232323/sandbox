package com.lucidlogic.horsetracker;

import android.util.Log;

import com.lucidlogic.fixtureui.BeanTransformers;
import com.lucidlogic.fixtureui.binding.Racecard;
import com.lucidlogic.fixtureui.parse.RacecardParse;
import com.lucidlogic.fixtureui.parse.TrackableEntityParse;
import com.lucidlogic.fixtureui.parse.UserTrackableEntityParse;
import com.lucidlogic.horsetracker.utils.DateUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.Date;
import java.util.HashMap;
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

    @Test
    public void trackableEntityParseTest() throws ParseException, java.text.ParseException {
        TrackableEntityParse trackableEntityParse = ParseObject.create(TrackableEntityParse.class);
        trackableEntityParse.setName("Herald The Dawn (IRE)");
        trackableEntityParse.setType(Constants._HORSE);
        trackableEntityParse.setProfileUrl("http://www.sportinglife.com/racing/profiles/horse/894106/herald-the-dawn");
        trackableEntityParse.setAttributes(new HashMap<String, Object>(){{
            put("age",3);
            put("dob", DateUtils.getDateFromString("20-05-2013"));
            put("owner","Godolphin");
            put("sex","Bay Colt");
            put("last_profile_crawl_dt",new Date());
        }});
        trackableEntityParse.save();
        Log.i(TAG, String.format("# %s",trackableEntityParse.getObjectId()));
    }

    @Test
    public void userRrackableEntityParseTest() throws ParseException, java.text.ParseException {
        TrackableEntityParse trackableEntityParse =
                ParseQuery.getQuery(TrackableEntityParse.class).get("BzB3oLFlrD");
        ParseUser parseUser = ParseUser.logIn("i","i");
        UserTrackableEntityParse userTrackableEntityParse = ParseObject.create(UserTrackableEntityParse.class);
        userTrackableEntityParse.setTrackableEntity(trackableEntityParse);
        userTrackableEntityParse.setUser(parseUser);
        userTrackableEntityParse.save();
    }
}
