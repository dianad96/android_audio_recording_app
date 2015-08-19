package com.example.diana.myapplication15;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;


public class MainActivity extends Activity {
    private MediaRecorder myAudioRecorder;
    private MediaPlayer myPlayer;
    private String outputFile = null;

    private Button startBtn;
    private Button stopBtn;
    private Button playBtn;
    private Button stopPlayBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = (Button)findViewById(R.id.button1);
        stopBtn = (Button)findViewById(R.id.button2);
        playBtn = (Button)findViewById(R.id.button3);
        stopPlayBtn = (Button) findViewById(R.id.button4);

        outputFile = Environment.getExternalStorageDirectory().
                getAbsolutePath() + "/myrecording.3gp";

        myAudioRecorder = new MediaRecorder();
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        myAudioRecorder.setOutputFile(outputFile);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                start(v);
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                stop(v);
            }
        });

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                play(v);
            }
        });

        stopPlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                stopPlay(v);
            }
        });

    }

    public void start(View view){
        try {
            myAudioRecorder.prepare();
            myAudioRecorder.start();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        startBtn.setEnabled(false);
        stopBtn.setEnabled(true);

        Toast.makeText(getApplicationContext(), "Recording started",
                Toast.LENGTH_LONG).show();
    }

    public void stop(View view){
        myAudioRecorder.stop();
        myAudioRecorder.release();
        myAudioRecorder = null;

        stopBtn.setEnabled(false);
        playBtn.setEnabled(true);

        Toast.makeText(getApplicationContext(), "Audio recorded successfully",
                Toast.LENGTH_LONG).show();
    }


    public void play(View view){

        try {

           myPlayer = new MediaPlayer();
           myPlayer.setDataSource(outputFile);
           myPlayer.prepare();
           myPlayer.start();
            Toast.makeText(getApplicationContext(), "Playing audio",
                    Toast.LENGTH_LONG).show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }



    public void stopPlay(View view) {
        try {
            if (myPlayer != null) {
                myPlayer.stop();
                myPlayer.release();
                myPlayer = null;
                playBtn.setEnabled(true);
                stopBtn.setEnabled(false);

                Toast.makeText(getApplicationContext(), "Stop playing the recording...",
                        Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}