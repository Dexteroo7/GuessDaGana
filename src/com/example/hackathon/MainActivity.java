package com.example.hackathon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	
	Button oneplayer,twoplayer,leaderboard;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	oneplayer = (Button)findViewById(R.id.boneplayer);

	twoplayer = (Button)findViewById(R.id.btwoplayer);

	leaderboard = (Button)findViewById(R.id.bleaderboard);
	
	oneplayer.setOnClickListener(this);
	twoplayer.setOnClickListener(this);
	leaderboard.setOnClickListener(this);
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		switch(arg0.getId()){
		
		case R.id.boneplayer:
			
			Intent i = new Intent(getApplication(), OnePlayer.class);
			startActivity(i);

			
			break;
			
		case R.id.btwoplayer:
			
			
			
			break;
			
		case R.id.bleaderboard:
			
			break;
		
		
		
		}
		
	}

}
