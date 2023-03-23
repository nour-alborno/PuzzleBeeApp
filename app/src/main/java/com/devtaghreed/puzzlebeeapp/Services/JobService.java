package com.devtaghreed.puzzlebeeapp.Services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.content.Intent;
import android.os.Build;


import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.devtaghreed.puzzlebeeapp.Activities.HomeActivity;
import com.devtaghreed.puzzlebeeapp.R;


public class JobService extends android.app.job.JobService {

    public static final String CHANNEL_ID = "ChannelId";


    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        notification(jobParameters);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {

        return false;
    }


    public void notification(JobParameters jobParameters) {


        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "channel name",
                    NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        Intent intent=new Intent(getBaseContext(), HomeActivity.class);
        PendingIntent pi=PendingIntent.getActivity(this,0,intent,0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_bee_svgrepo_com);
        builder.setContentTitle("PuzzleBee Game");
        builder.setContentText("it’s been a long time since last played");
        builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        builder.addAction(R.drawable.ic_bee_svgrepo_com,"Let's play",pi);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getApplicationContext());
        managerCompat.notify(1, builder.build());

    }
}
