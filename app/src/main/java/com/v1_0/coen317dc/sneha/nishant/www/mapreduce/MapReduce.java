package com.v1_0.coen317dc.sneha.nishant.www.mapreduce;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;

/**
 * Created by nishant on 10/3/16.
 */
public class MapReduce extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, ParseKeys.ParseKey1, ParseKeys.ParseKey2);
        // Note: I have declared string constants in a class named ParseKeys.
        // This class is not shared on GitHub for Maintaining the privacy
        // Add your Parse ApplicationID and Client Key there

        Log.d("ApplicationClass", "Parse initialized");
    }
}
