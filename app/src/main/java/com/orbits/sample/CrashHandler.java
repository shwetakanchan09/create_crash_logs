package com.orbits.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Build;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private final Context context;

    public CrashHandler(Context context) {
        this.context = context;
    }

    public void uncaughtException(Thread thread, Throwable exception) {
        StringWriter stackTrace = new StringWriter();
        exception.printStackTrace(new PrintWriter(stackTrace));
        String errorReport = new Date() + "\n\n************ CAUSE OF ERROR ************\n\n" +
                stackTrace +
                "\n************ DEVICE INFORMATION ***********\n" +
                "Brand: " + Build.BRAND + "\n" +
                "Manufacturer: " + Build.MANUFACTURER + "\n" +
                "Device: " + Build.DEVICE + "\n" +
                "Model: " + Build.MODEL + "\n" +
                "Id: " + Build.ID + "\n" +
                "Product: " + Build.PRODUCT + "\n" +
                "\n************ FIRMWARE ************\n" +
                "SDK: " + Build.VERSION.SDK_INT + "\n" +
                "Android Version: " + Build.VERSION.RELEASE + "\n" +
                "Incremental: " + Build.VERSION.INCREMENTAL + "\n" +
                "\n************ APP INFORMATION ************\n" +
                "Build Type: " + BuildConfig.BUILD_TYPE + "\n" +
                "Version: " + BuildConfig.VERSION_NAME + "\n" +
                "Version Code: " + BuildConfig.VERSION_CODE + "\n";
        Intent intent = new Intent(context, ExceptionActivity.class);
        intent.putExtra("error", errorReport);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        try {
            File logFile = new File(context.getExternalFilesDir(""), "Log.txt");
            if (!logFile.exists())
                logFile.createNewFile();
            FileWriter writer = new FileWriter(logFile, true);
            String seperator = "---------------------------------------------------------------------------------------";
            writer.append(errorReport + "\n" + seperator + "\n");
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(10);
    }
}