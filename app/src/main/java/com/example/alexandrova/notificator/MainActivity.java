package com.example.alexandrova.notificator;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.inquallity.notificator.R;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    static private final int RESULT = 0;

    private static final int NOTIFY_ID = 33;

    private TextView mTvTime;

    private TextView mTvTimeTwo;

    private TextView mTvTimeThree;

    private TextView mTvTimeFour;

    public void onTimeClick(View view) {

        Intent intent = new Intent(MainActivity.this, TimePickerActivity.class);
        startActivityForResult(intent, RESULT);

    }

    public void changeTimeTable(int hour, int minute) {

        int minute2 = minute + 45;
        int hour2 = hour + 3;
        if (minute2 > 60) {
            hour2++;
            minute2 = minute2 - 60;
        }
        if (hour2 > 23) {
            hour2 = hour2 - 24;
        }
        mTvTimeTwo.setText(String.format("%02d", hour2) + " : " + String.format("%02d", minute2));

        int minute3 = minute2 + 45;
        int hour3 = hour2 + 3;
        if (minute3 > 59) {
            hour3++;
            minute3 = minute3 - 60;
        }
        if (hour3 > 23) {
            hour3 = hour3 - 24;
        }
        mTvTimeThree.setText(String.format("%02d", hour3) + " : " + String.format("%02d", minute3));

        int minute4 = minute3 + 45;
        int hour4 = hour3 + 3;
        if (minute4 > 59) {
            hour4++;
            minute4 = minute4 - 60;
        }
        if (hour4 > 23) {
            hour4 = hour4 - 24;
        }
        mTvTimeFour.setText(String.format("%02d", hour4) + " : " + String.format("%02d", minute4));
    }

    public void onStartDayClick(View view) {

        Calendar now = Calendar.getInstance();

        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        mTvTime.setText(String.format("%02d", hour) + " : " + String.format("%02d", minute));

        changeTimeTable(hour, minute);

    }

    public void showNotify() {

        Context context = getApplicationContext();
        Intent notificationIntent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        Resources res = context.getResources();
        Notification.Builder builder = new Notification.Builder(context);

        builder.setContentIntent(contentIntent)
                .setSmallIcon(R.mipmap.ic_launcher_food)
                .setLargeIcon(BitmapFactory.decodeResource(res, R.mipmap.ic_launcher_food))
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setTicker("Time to eat ))")
                .setContentText("Time to eat ))");

        Notification notification = builder.build();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFY_ID, notification);
    }

    public void onNotifyClick(View view) {
        showNotify();
    }

    public void onAddNewNotify(View view) {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);

        mTvTime = (TextView) findViewById(R.id.tvTime);
        mTvTimeTwo = (TextView) findViewById(R.id.tvTime2);
        mTvTimeThree = (TextView) findViewById(R.id.tvTime3);
        mTvTimeFour = (TextView) findViewById(R.id.tvTime4);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextAppearance(this, R.style.toolbar_text);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT) {
            if (resultCode == RESULT_OK) {
                int minute = data.getIntExtra(TimePickerActivity.CHOOSEN_MINUTE, 10);
                int hour = data.getIntExtra(TimePickerActivity.CHOOSEN_HOUR, 10);
                mTvTime.setText(String.format("%02d", hour) + " : " + String.format("%02d", minute));
                changeTimeTable(hour, minute);
            } else {
                mTvTime.setText("");
            }
        }
    }

}
