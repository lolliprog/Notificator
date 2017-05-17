package com.example.alexandrova.notificator;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.alexandrova.notificator.dialog.AddNewNotificationDialog;
import com.example.inquallity.notificator.R;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements AddNewNotificationDialog.OnNotificationSaveListener {

    private static final int RESULT = 0;

    private static final int NOTIFY_ID = 33;

    private static final int MAX_HOUR = 23;

    private static final int MAX_MINUTE = 59;

    private static final int HOUR_STEP = 3;

    private static final int MINUTES_STEP = 45;

    private TextView mTime;

    private TextView mTimeTwo;

    private TextView mTimeThree;

    private TextView mTimeFour;

    private final TimePickerDialog.OnTimeSetListener mOnTimeSetCallback =
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    changeTimeTable(hourOfDay, minute);
                }
            };

    public void onLastMealClick(View view) {
        final Calendar now = Calendar.getInstance();
        final int hour = now.get(Calendar.HOUR_OF_DAY);
        final int minute = now.get(Calendar.MINUTE);
        final TimePickerDialog tpd = new TimePickerDialog(this, mOnTimeSetCallback, hour, minute, true);
        tpd.show();
    }

    public void changeTimeTable(int hour, int minute) {
        final String first = String.format(Locale.getDefault(), "%1$02d : %2$02d", hour, minute);
        mTime.setText(first);

        hour += HOUR_STEP;
        minute += MINUTES_STEP;
        if (hour > MAX_HOUR) {
            hour -= 24;
        }
        if (minute > MAX_MINUTE) {
            hour++;
            minute -= 60;
        }
        final String second = String.format(Locale.getDefault(), "%1$02d : %2$02d", hour, minute);
        mTimeTwo.setText(second);

        hour += HOUR_STEP;
        minute += MINUTES_STEP;
        if (hour > MAX_HOUR) {
            hour -= 24;
        }
        if (minute > MAX_MINUTE) {
            hour++;
            minute -= 60;
        }
        final String third = String.format(Locale.getDefault(), "%1$02d : %2$02d", hour, minute);
        mTimeThree.setText(third);

        hour += HOUR_STEP;
        minute += MINUTES_STEP;
        if (hour > MAX_HOUR) {
            hour -= 24;
        }
        if (minute > MAX_MINUTE) {
            hour++;
            minute -= 60;
        }
        final String fourth = String.format(Locale.getDefault(), "%1$02d : %2$02d", hour, minute);
        mTimeFour.setText(fourth);
    }

    public void onStartDayClick(View view) {
        final Calendar now = Calendar.getInstance();
        final int hour = now.get(Calendar.HOUR_OF_DAY);
        final int minute = now.get(Calendar.MINUTE);
        changeTimeTable(hour, minute);
    }

    public void showNotify() {
        Context context = getApplicationContext();
        Intent notificationIntent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        Resources res = context.getResources();
        Notification.Builder builder = new Notification.Builder(context);

        builder.setContentIntent(contentIntent)
                .setSmallIcon(R.drawable.ic_food)
                .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.ic_food))
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
        final AddNewNotificationDialog dialog = new AddNewNotificationDialog();
        dialog.show(getFragmentManager(), AddNewNotificationDialog.class.getName());
    }

    @Override
    public void onSave(@NonNull String time, @NonNull String text) {
        Toast.makeText(this, "Selected time: " + time + " -> " + text, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);

        mTime = (TextView) findViewById(R.id.tvTime);
        mTimeTwo = (TextView) findViewById(R.id.tvTime2);
        mTimeThree = (TextView) findViewById(R.id.tvTime3);
        mTimeFour = (TextView) findViewById(R.id.tvTime4);
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
                mTime.setText(String.format(Locale.getDefault(), "%1$02d : %2$02d", hour, minute));
                changeTimeTable(hour, minute);
            } else {
                mTime.setText("");
            }
        }
    }

}
