package com.github.anurag145.mycheckin;

import android.app.Application;

import com.estimote.sdk.EstimoteSDK;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        EstimoteSDK.initialize(getApplicationContext(), "impulse-attendance-trial-nwm", "80f878460fbfbfadf1ab8d49beb5e588");

    }
}
