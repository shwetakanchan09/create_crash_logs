package com.orbits.sample;

import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.ContextWrapper;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AppConfig extends Application  {
    private static AppConfig mContext;

    public static synchronized AppConfig getInstance() {
        if (mContext == null) {
            mContext = new AppConfig();
        }
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Thread.setDefaultUncaughtExceptionHandler(new CrashHandler(this));
    }

}
