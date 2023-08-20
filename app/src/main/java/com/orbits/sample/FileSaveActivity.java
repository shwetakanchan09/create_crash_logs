package com.orbits.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class FileSaveActivity extends AppCompatActivity {
    static File logFile1;
    static FileWriter writer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_save);


       /* String parentFolderName = "myParentFolder";
        String subFolderName = "mySubFolder";
        String filename = "mytextfile.txt";
        String content = "Hello, this is the content of my text file.";

// Get the root directory of your app's internal storage
        File parentDirectory = getFilesDir();

// Create a File object for the parent folder
        File parentFolder = new File(parentDirectory, parentFolderName);

// Create the parent folder
        if (!parentFolder.exists()) {
            boolean success = parentFolder.mkdir();
            if (!success) {
                // Parent folder creation failed
                return;
            }
        }

// Create a File object for the subfolder within the parent folder
        File subFolder = new File(parentFolder, subFolderName);

// Create the subfolder
        if (!subFolder.exists()) {
            boolean success = subFolder.mkdir();
            if (!success) {
                // Subfolder creation failed
                return;
            }
        }

// Create a File object for the text file within the subfolder
        File textFile = new File(subFolder, filename);

        try {
            FileOutputStream fos = new FileOutputStream(textFile);
            fos.write(content.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        String parentFolderName = "myParentFolder";
        String subFolderName = "mySubFolder";

// Get the root directory of your app's internal storage
        File parentDirectory = getFilesDir();

// Create a File object for the parent folder
        File parentFolder = new File(parentDirectory, parentFolderName);

// Create the parent folder
        if (!parentFolder.exists()) {
            boolean success = parentFolder.mkdir();
            if (!success) {
                // Parent folder creation failed
                return;
            }
        }

// Create a File object for the subfolder within the parent folder
        File subFolder = new File(parentFolder, subFolderName);

// Create the subfolder
        if (!subFolder.exists()) {
            boolean success = subFolder.mkdir();
            if (!success) {
                // Subfolder creation failed
                return;
            }
        }

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable throwable) {
                try {
                    // Create a File object for the crash log file within the subfolder
                    File crashLogFile = new File(subFolder, "crash_log.txt");

                    // Write crash logs to the file
                    PrintWriter writer = new PrintWriter(new FileWriter(crashLogFile, true));
                    writer.println("Crash occurred:");
                    throwable.printStackTrace(writer);
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Terminate the app
                System.exit(1);
            }
        });
    }
}