package com.example.myapplication;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    AudioService audio = new AudioService();
    ArrayList<Integer> songList;
    int index=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        songList = new ArrayList<>();
        songList.add(R.raw.uno);
        songList.add(R.raw.dos);
        songList.add(R.raw.tres);

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void startServiceOnClick (View view){

        intent = new Intent(this, AudioService.class);
        stopService(intent);
        intent.putExtra("lista",songList);

        startService(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void stopServiceOnClick (View view){
        intent = new Intent(this, AudioService.class);
        intent.putExtra("lista",songList);
        stopService(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void siguienteOnClick(View view){
        index++;
        Intent intent = new Intent(this, AudioService.class);
        stopService(intent);
        if(index%songList.size()==0){
            index=0;
        }
        intent.putExtra("lista",songList);
        intent.putExtra("index",index);
        startService(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void anteriorOnClick(View view){
        index--;
        Intent intent = new Intent(this, AudioService.class);
        stopService(intent);
        if(index<0){
            index=songList.size()-1;
        }
        intent.putExtra("lista",songList);
        intent.putExtra("index",index);
        startService(intent);
    }

}