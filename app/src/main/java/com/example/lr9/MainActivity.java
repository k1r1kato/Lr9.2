package com.example.lr9;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Logger logger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logger = new Logger(this);
        logger.log("Приложение запущено");

        TextView selectableTextView = findViewById(R.id.selectableTextView);
        TextView linkTextView = findViewById(R.id.linkTextView);
        TextView spannableTextView = findViewById(R.id.spannableTextView);
        TextView multilineTextView = findViewById(R.id.multilineTextView);
        TextView dateTextView = findViewById(R.id.dateTextView);
        Button updateButton = findViewById(R.id.updateButton);

        setupSpannableText(spannableTextView);

        updateDateTime(dateTextView);

        updateButton.setOnClickListener(v -> {
            updateDateTime(dateTextView);
            logger.log("Пользователь обновил дату и время");
            showToast(getString(R.string.data_updated_message));
        });
    }

    private void setupSpannableText(TextView textView) {
        String text = getString(R.string.spannable_text);
        SpannableString spannableString = new SpannableString(text);

        spannableString.setSpan(
                new StyleSpan(Typeface.BOLD),
                text.indexOf("жирный"),
                text.indexOf("жирный") + "жирный".length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        spannableString.setSpan(
                new ForegroundColorSpan(Color.RED),
                text.indexOf("красный"),
                text.indexOf("красный") + "красный".length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        spannableString.setSpan(
                new UnderlineSpan(),
                text.indexOf("подчеркнутый"),
                text.indexOf("подчеркнутый") + "подчеркнутый".length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        textView.setText(spannableString);
    }

    private void updateDateTime(TextView textView) {
        String currentDateTime = DateFormat.getDateTimeInstance().format(new Date());
        textView.setText(getString(R.string.date_prefix) + " " + currentDateTime);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        logger.log("Приложение закрыто");
    }
}