package com.lucidlogic.parse.parsedemo;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseUser;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by itowey on 27/05/16.
 */
@RunWith(AndroidJUnit4.class)
public class ParseCloudFunctionTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class, true, true);

    @Test
    public void cloudFunctionTest() throws ParseException {
        Map<String, String> map = new HashMap<String, String>();
        ParseUser user = new ParseUser();
        user.setUsername("iant");
        user.setPassword("iant");
        user.setEmail("iant@gmail.com");
        user.signUp();
        Object s = ParseCloud.callFunction("hello", map);
        Log.e("cloud code example", "response: " + s);
//        ParseCloud.callFunctionInBackground("hello", map, new FunctionCallback<Object>() {
//            @Override
//            public void done(Object response, ParseException exc) {
//                Log.e("cloud code example", "response: " + response);
//            }
//        });
    }
}
