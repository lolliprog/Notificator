package com.example.alexandrova.notificator;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TimePicker;

import com.example.inquallity.notificator.R;

import java.util.Calendar;

public class TimePickerActivity extends AppCompatActivity {

    public final static String CHOOSEN_HOUR = "com.example.inquallity.notificator.HOUR";

    public final static String CHOOSEN_MINUTE = "com.example.inquallity.notificator.MINUTE";

    private TimePicker mTimePicker;

    private int mSelectedHour;

    private int mSelectedMinute;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onSaveTimeClick(View view) {
        final Intent intent = new Intent();
        intent.putExtra(CHOOSEN_HOUR, mSelectedHour);
        intent.putExtra(CHOOSEN_MINUTE, mSelectedMinute);

        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_time_picker);
        mTimePicker = (TimePicker) findViewById(R.id.tpTime);

        Calendar now = Calendar.getInstance();

        mSelectedHour = now.get(Calendar.HOUR_OF_DAY);
        mSelectedMinute = now.get(Calendar.MINUTE);
        mTimePicker.setCurrentHour(mSelectedHour);
        mTimePicker.setCurrentMinute(mSelectedMinute);

        mTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                mSelectedHour = hourOfDay;
                mSelectedMinute = minute;
            }
        });
    }
}
