package com.balliet.screenoff;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.app.NotificationCompat;


public class MainActivity extends Activity {
    private Button button_on;
    private Button button_off;
    private TextView status;

    private PowerManager powerManager;
    private PowerManager.WakeLock wakeLock;
    private int field = 0x00000020;

    private NotificationManager mNotificationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            field = PowerManager.class.getClass().getField("PROXIMITY_SCREEN_OFF_WAKE_LOCK").getInt(null);
        } catch (Throwable ignored) {
        }

        powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(field, getLocalClassName());

        setContentView(R.layout.activity_main);
        button_on = (Button) findViewById(R.id.button_on);
        button_off = (Button) findViewById(R.id.button_off);
        status = (TextView) findViewById(R.id.status);

        button_on.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!wakeLock.isHeld()) {
                    wakeLock.acquire();
                    status.setTextColor(Color.GREEN);
                    status.setText("Current status : on");
                    showNotification("ScreenSave is running", "Use the proximity sensor to turn off the screen");
                }
            }
        });

        button_off.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(wakeLock.isHeld()) {
                    wakeLock.release();
                    status.setText("Current status : off");
                    status.setTextColor(Color.RED);
                    mNotificationManager.cancel("screensave", 0);
                }
            }
        });
    }

    public void showNotification(String title, String message) {
        mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("screen_save_channel",
                    "screensave",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("description");
            mNotificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "screen_save_channel")
                .setSmallIcon(R.mipmap.ic_launcher) // notification icon
                .setContentTitle(title) // title for notification
                .setContentText(message)// message for notification
                .setOngoing(true);

        Intent intent = newLauncherIntent(getApplicationContext());

        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pi);
        mNotificationManager.notify("screensave", 0, mBuilder.build());
    }

    public static Intent newLauncherIntent(final Context context) {
        final Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        return intent;
    }
}

