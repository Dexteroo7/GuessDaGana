package com.example.hackathon;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	ArrayList<String> artistlisting = new ArrayList<String>();

	ArrayList<Song> Tracklisting = new ArrayList<Song>();
	ArrayList<String> Wronganswers = new ArrayList<String>();
	TextView tvtest;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		tvtest = (TextView) findViewById(R.id.textView1);
		new Read().execute("56");

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}

	class Read extends AsyncTask<String, Integer, String> {

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			tvtest.setText(result);
		}

		@Override
		protected String doInBackground(String[] arg0) {
			// TODO Auto-generated method stub
			try {
				artistlisting = Helper.getartistlisting(Integer
						.parseInt(arg0[0]));
				int number = (int) ((int) 10 * Math.random());
				ArrayList<Song> RawTracklisting = new ArrayList<Song>();
				RawTracklisting = Helper.gettracklisting(arg0,
						artistlisting.get(number));
				
				number = (int) ((int) 10 * Math.random());
				RawTracklisting.addAll(Helper.gettracklisting(arg0,
						artistlisting.get(number)));
				number = (int) ((int) 10 * Math.random());
				RawTracklisting.addAll(Helper.gettracklisting(arg0,
						artistlisting.get(number)));
				int i = 0;
				for (i = 0; i < 11; i++) {
					number = (int) ((int) RawTracklisting.size() * Math
							.random());
					if (!(Tracklisting.contains(RawTracklisting.get(number)))) {
						Tracklisting.add(RawTracklisting.get(number));
					} else {
						i--;
					}
					for(i=0;i<RawTracklisting.size();i++){
						if(!(Tracklisting.contains(RawTracklisting.get(i)))&&!(Wronganswers.contains(RawTracklisting.get(i)))){
							Wronganswers.add(RawTracklisting.get(i).gettracktitle());
							
						}
						
					}

				}

				return Wronganswers.toString();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
		}

	}

}
