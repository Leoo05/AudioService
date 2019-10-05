package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.provider.MediaStore;
import android.provider.Settings;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AudioService extends Service {

    private MediaPlayer player;
    public ArrayList<Integer> songList;
    private MediaPlayer next;
    public int index;


    public AudioService() {
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        songList = intent.getIntegerArrayListExtra("lista");
        index=intent.getIntExtra("index",0);
        //TODO write your own code
        player = MediaPlayer.create(this, songList.get(index));
        // start the player
        player.start();

        return START_STICKY;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void play(){
        player.start();
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void stop(){
        player.stop();
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Stopping the player when service is destroyed
        player.stop();

    }

}