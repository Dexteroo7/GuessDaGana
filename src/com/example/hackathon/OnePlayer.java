package com.example.hackathon;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class OnePlayer extends Activity implements OnClickListener {

	Button bsubmit;
	EditText etmovie, ettrack;
	ImageView ivsong;
	MediaPlayer oursong;
	TextView tvtimer, tvcurrentscore;
	Chronometer stopwatch;
	String[] songsrc = { "nothing", "R.drawable.dabbang2_daggabazz_re",
			"R.drawable.idiots3_givemesomesunshine",
			"R.drawable.apgk_tujaanena", "R.drawable.jabwemet_tumsehi",
			"R.drawable.jannat2_tuhimera" };

	String[] movieanswer = { "none", "Dabbang 2", "3 Idiots",
			"Ajab Prem Ki Gajab Kahani", "Jab We Met", "Jannat 2" };

	String[] keywordsanswer = { "none", "Daggabazz Re",
			"Give Me Some Sunshine", "Tu Jaane Na", "Tum Se Hi", "Tu Hi Mera" };

	int counter = 0;
	int countup = 0;
	long temp = 0;
	int pscore = 0;

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
		tvcurrentscore = (TextView) findViewById(R.id.tvcurrentscore);
		stopwatch.setOnChronometerTickListener(new OnChronometerTickListener() {
			@Override
			public void onChronometerTick(Chronometer arg0) {
				Log.d("hell", "Ticking");

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

		switch (v.getId()) {

		case R.id.iVmusic:
			if (oursong.isPlaying()) {

				oursong.pause();
				temp = stopwatch.getBase() - SystemClock.elapsedRealtime();
				stopwatch.stop();
				stopwatch.stop();
				counter = counter + countup;
			} else {
				oursong.start();
				stopwatch.setBase(SystemClock.elapsedRealtime() + temp);
				stopwatch.start();

			}
			break;

		case R.id.bsubmit:
			stopwatch.setBase(SystemClock.elapsedRealtime());
			temp = 0;
			stopwatch.stop();
			String tempmovie = etmovie.getText().toString();
			String tempkeywords = etmovie.getText().toString();
			if (tempmovie == movieanswer[1]
					&& tempkeywords == keywordsanswer[1]) {
				pscore = pscore + counter;
				counter = 0;
				tvcurrentscore.setText("Current Score - " + pscore);
				Log.d("answer", "True");
				Toast.makeText(this, "Correct Answer", Toast.LENGTH_LONG)
						.show();
			} else {
				Toast.makeText(this, "Wrong Answer", Toast.LENGTH_LONG).show();
				counter = counter + 10;
				Log.d("answer", "False");
			}

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
