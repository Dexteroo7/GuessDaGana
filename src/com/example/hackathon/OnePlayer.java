package com.example.hackathon;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class OnePlayer extends Activity implements OnClickListener {

	Button bsubmit;
	EditText etmovie, ettrack;
	ImageView ivsong;
	MediaPlayer oursong;
	TextView tvtimer;
	Chronometer stopwatch;

	String[] movieanswer = new String[10];
	String[] keywordsanswer = new String[10];

	int counter = 0;
	int countup = 0;
	long temp = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.oneplayer);
		oursong = MediaPlayer.create(this, R.drawable.dabbang2_daggabazz_re);
		ivsong = (ImageView) findViewById(R.id.iVmusic);
		bsubmit = (Button) findViewById(R.id.bsubmit);
		etmovie = (EditText) findViewById(R.id.etmovie);
		ettrack = (EditText) findViewById(R.id.ettrack);
		tvtimer = (TextView) findViewById(R.id.tvtimer);
		stopwatch = (Chronometer) findViewById(R.id.chrono1);
		movieanswer[1] = "Dabbang2";
		keywordsanswer[1] = "Daggabaaz Re";

		stopwatch.setOnChronometerTickListener(new OnChronometerTickListener() {
			@Override
			public void onChronometerTick(Chronometer arg0) {

				countup = (int) ((SystemClock.elapsedRealtime() - arg0
						.getBase()) / 1000);
				String asText = (countup / 60) + ":" + (countup % 60);
				tvtimer.setText("Time Elapsed :- " + asText);
			}
		});

		bsubmit.setOnClickListener(this);
		ivsong.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch(v.getId()){
		
		case R.id.iVmusic:
			if(oursong.isPlaying()){

                oursong.pause();
                temp = stopwatch.getBase() - SystemClock.elapsedRealtime();
                stopwatch.stop();
                counter = counter + countup;
			}else
				oursong.start();
                stopwatch.setBase(SystemClock.elapsedRealtime() + temp);
				stopwatch.start();
				
			
			break;
			
		case R.id.bsubmit:
            stopwatch.setBase(SystemClock.elapsedRealtime());
            temp = 0;
            stopwatch.stop();
break;
		}
	}
	@Override
	public void onDestroy() {

		stopwatch.stop();
		oursong.stop();
		super.onDestroy();
	}

	@Override
	public void onPause() {

		if (temp > 0 || oursong.isPlaying()) {
			oursong.pause();
			temp = stopwatch.getBase() - SystemClock.elapsedRealtime();
			stopwatch.stop();
		}
		super.onPause();
	}

	@Override
	public void onResume() {

		if (temp > 0) {
			oursong.start();
			stopwatch.setBase(SystemClock.elapsedRealtime() + temp);
			stopwatch.start();
		}
		super.onResume();
	}

}
