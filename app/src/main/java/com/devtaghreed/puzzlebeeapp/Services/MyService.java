package com.devtaghreed.puzzlebeeapp.Services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.devtaghreed.puzzlebeeapp.R;

public class MyService extends Service {

    MediaPlayer mediaPlayerBG;
    public MyService() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayerBG = MediaPlayer.create(this, R.raw.backgroud_audio);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        mediaPlayerBG.start();
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayerBG.stop();

        mediaPlayerBG.release();
    }


    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}