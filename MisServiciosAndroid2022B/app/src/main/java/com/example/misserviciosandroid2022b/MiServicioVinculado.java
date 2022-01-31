package com.example.misserviciosandroid2022b;

import android.app.Service;
import android.content.Intent;
import android.media.MicrophoneDirection;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.Random;

public class MiServicioVinculado extends Service {
    private final IBinder mibinder = new MyBinder();
    private final Random random = new Random();
    class MyBinder extends Binder{
         MiServicioVinculado getService(){
            return MiServicioVinculado.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mibinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        /*
        Intent notificationIntent = new Intent(this, ExampleActivity.class);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(this, 0, notificationIntent, 0);

        Notification notification =
                new Notification.Builder(this, CHANNEL_DEFAULT_IMPORTANCE)
                        .setContentTitle(getText(R.string.notification_title))
                        .setContentText(getText(R.string.notification_message))
                        .setSmallIcon(R.drawable.icon)
                        .setContentIntent(pendingIntent)
                        .setTicker(getText(R.string.ticker_text))
                        .build();

        // Notification ID cannot be 0.
        startForeground(ONGOING_NOTIFICATION_ID, notification);
         */

        return super.onStartCommand(intent, flags, startId);
    }

    public int getRandomNumber(){
        return random.nextInt(1000);
    }

}
