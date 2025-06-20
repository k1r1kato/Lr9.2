package com.example.lr9;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView titleTextView = findViewById(R.id.titleTextView);
        TextView contentTextView = findViewById(R.id.contentTextView);
        TextView dateTextView = findViewById(R.id.dateTextView);

        titleTextView.setText(getString(R.string.app_title));
        titleTextView.setTextSize(20);

        String currentDate = DateFormat.getDateInstance().format(new Date());
        dateTextView.setText(getString(R.string.date_label, currentDate));

        Logger.log("Приложение запущено");
    }
}