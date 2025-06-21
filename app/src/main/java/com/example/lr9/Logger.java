package com.example.lr9;

import android.content.Context;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

public class Logger {
    private static final String LOG_FILE_NAME = "app_logs.txt";
    private final Context context;

    public Logger(Context context) {
        this.context = context;
    }

    public void log(String message) {
        String logEntry = DateFormat.getDateTimeInstance().format(new Date()) +
                " - " + message + "\n";

        writeToFile(logEntry);
        System.out.println("LOG: " + logEntry.trim());
    }

    private void writeToFile(String data) {
        try {
            File file = new File(context.getFilesDir(), LOG_FILE_NAME);
            FileOutputStream outputStream;

            if (file.exists()) {
                outputStream = context.openFileOutput(LOG_FILE_NAME, Context.MODE_APPEND);
            } else {
                outputStream = context.openFileOutput(LOG_FILE_NAME, Context.MODE_PRIVATE);
            }

            outputStream.write(data.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getLogs() {
        try {
            File file = new File(context.getFilesDir(), LOG_FILE_NAME);
            if (file.exists()) {
                java.util.Scanner scanner = new java.util.Scanner(file);
                StringBuilder builder = new StringBuilder();

                while (scanner.hasNextLine()) {
                    builder.append(scanner.nextLine()).append("\n");
                }

                scanner.close();
                return builder.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Логи отсутствуют";
    }
}