package com.example.hackathon;

import java.math.BigDecimal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

import com.shephertz.app42.paas.sdk.android.ServiceAPI;
import com.shephertz.app42.paas.sdk.android.game.Game;
import com.shephertz.app42.paas.sdk.android.game.ScoreBoardService;

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
	int random = 1;
	int counter = 0;
	int countup = 0;
	long temp = 0;
	int pscore = 0;
	private final String YOUR_API_KEY = "3e64a1d1a501cf90ca981ecd2dd4453af4ec4e0309c2746ce75ece53e9e508e3";
	private final String YOUR_SECRET_KEY = "069a4ad07ff72efc47617855da24ada4df1baa3f07baa0a068beefe8e8db1f67";

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

			String tempmovie = etmovie.getText().toString();
			String tempkeywords = ettrack.getText().toString();
			if (tempmovie.equals(movieanswer[random])
					&& tempkeywords.equals(keywordsanswer[random])) {
				pscore = pscore + counter;
				counter = 0;
				temp = 0;
				tvcurrentscore.setText("Current Score - " + pscore);
				Log.d("answer", "True");
				Toast.makeText(this, "Correct Answer", Toast.LENGTH_LONG)
						.show();
				random++;
				switch (random) {

				case 2:
					oursong = MediaPlayer.create(this,
							R.drawable.idiots3_givemesomesunshine);
					break;
				case 3:
					oursong = MediaPlayer.create(this,
							R.drawable.apgk_tujaanena);
					break;
				case 4:
					oursong = MediaPlayer.create(this,
							R.drawable.jabwemet_tumsehi);
					break;
				case 5:
					oursong = MediaPlayer.create(this,
							R.drawable.jannat2_tuhimera);

					Toast.makeText(this, "GameOver", Toast.LENGTH_LONG).show();

					Saveuserdata();
					Intent i = new Intent(this, LeaderBoard.class);
					startActivity(i);
					break;
				}
			} else {
				Toast.makeText(this, "Wrong Answer ", Toast.LENGTH_LONG).show();
				Log.d(tempmovie, tempmovie);
				Log.d(tempkeywords, tempkeywords);
				temp = temp - 10;
				Log.d("answer", "False");
			}

			break;
		}
	}

	private void Saveuserdata() {
		// TODO Auto-generated method stub
		String gameName = "GuessTheGanna";
		String gameUserName = MainActivity.username;
		BigDecimal gameScore = BigDecimal.valueOf(pscore);
		ServiceAPI api = new ServiceAPI(YOUR_API_KEY, YOUR_SECRET_KEY);
		ScoreBoardService scoreBoardService = api.buildScoreBoardService();
		Game game = scoreBoardService.saveUserScore(gameName, gameUserName,
				gameScore);
		String jsonResponse = game.toString();
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
