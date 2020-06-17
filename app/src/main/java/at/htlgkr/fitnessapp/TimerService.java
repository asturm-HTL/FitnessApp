package at.htlgkr.fitnessapp;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import static at.htlgkr.fitnessapp.Timer.CHANNEL_ID;

public class TimerService extends Service
{

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        String name = intent.getStringExtra("inputExtra");

        Intent notificationIntent = new Intent(this, Timer.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID).setContentTitle("Timer Service").setContentText(name).setSmallIcon(R.drawable.ic_timer_black_24dp).setContentIntent(pendingIntent).build();

    startForeground(1, notification);

    return START_NOT_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

}
