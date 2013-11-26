package com.example.hackathon;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.shephertz.app42.paas.sdk.android.ServiceAPI;
import com.shephertz.app42.paas.sdk.android.game.Game;
import com.shephertz.app42.paas.sdk.android.game.ScoreBoardService;

public class LeaderBoard extends Activity {

	private final String YOUR_API_KEY = "3e64a1d1a501cf90ca981ecd2dd4453af4ec4e0309c2746ce75ece53e9e508e3";
	private final String YOUR_SECRET_KEY = "069a4ad07ff72efc47617855da24ada4df1baa3f07baa0a068beefe8e8db1f67";
	TextView tvleaderboard;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.leaderboard);
		tvleaderboard = (TextView) findViewById(R.id.tvleaderboard);
		String gameName = "GuessTheGanna";
		ServiceAPI api = new ServiceAPI(YOUR_API_KEY, YOUR_SECRET_KEY);
		ScoreBoardService scoreBoardService = api.buildScoreBoardService();
		Game game = scoreBoardService.getTopRankings(gameName);
		String jsonResponse = game.toString();
		tvleaderboard.setText(jsonResponse);

	}

}
