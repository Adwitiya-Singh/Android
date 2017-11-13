package com.example.adwitiyasingh.mediaplayer;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MediaPlayer mPlayer = MediaPlayer.create(this,R.raw.sampleaudio);
//        mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                mPlayer.start();
//
//            }
//        });
//        mPlayer.prepareAsync();
        // Can be done if files are being downloaded from the internet.



//        mPlayer.start();

        VideoView Vview = (VideoView) findViewById(R.id.videoView);
        Vview.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.samplevideo));
        Vview.setMediaController(new MediaController(this));
        Vview.start();
    }
}
