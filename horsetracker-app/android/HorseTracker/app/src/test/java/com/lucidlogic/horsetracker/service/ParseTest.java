package com.lucidlogic.horsetracker.service;

import android.content.Context;

import com.lucidlogic.horsetracker.BuildConfig;
import com.lucidlogic.horsetracker.MockApp;
import com.lucidlogic.horsetracker.model.MeetingDTO;
import com.lucidlogic.horsetracker.model.RaceDTO;
import com.lucidlogic.horsetracker.model.RacecardDTO;
import com.parse.Parse;
import com.parse.ParseObject;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.mockito.Mockito.mock;

/**
 * Created by ian on 10/07/16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, application = MockApp.class)
public class ParseTest {

}
