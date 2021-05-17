package com.example.stopwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int seconds;
    private boolean running;
    private  boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null)
        {
            savedInstanceState.getInt("seconds");
            savedInstanceState.getBoolean("running");
            savedInstanceState.getBoolean("wasRunning");
        }

        runTimmer();
    }

    public void Start(View view)
    {
        running = true;
        Toast.makeText(this,"The Timmer is Running",Toast.LENGTH_SHORT).show();
    }

    public void Stop(View view)
    {
        running = false;
        Toast.makeText(this,"The Timmer is Paused",Toast.LENGTH_SHORT).show();
    }

    public void Reset(View view)
    {
        running = false;
        seconds = 0;
        Toast.makeText(this,"The Timmer is Reset",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (wasRunning)
            running = true;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("seconds",seconds);
        outState.putBoolean("running",running);
        outState.putBoolean("wasRunning",wasRunning);
    }

    private void runTimmer() {

        final TextView Timer = findViewById(R.id.textView);
        final Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds /3600;
                int minutes = (seconds %3600) / 60;
                int secs = seconds % 60;

                String time = String.format(Locale.getDefault(),
                                     "%d:%02d:%02d",
                                             hours,minutes,secs);

                Timer.setText(time);

                if(running)
                    seconds++;
                handler.postDelayed(this,950);

            }
        });
    }
}
