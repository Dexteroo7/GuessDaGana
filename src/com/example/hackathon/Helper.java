package com.example.hackathon;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;


public class Helper {
	static ArrayList<String> artistlisting = new ArrayList<String>();
	static ArrayList<String> tracklisting = new ArrayList<String>();
	static HttpClient client=new DefaultHttpClient();
	
	public static ArrayList<String> getartistlisting(int genreid) throws ClientProtocolException, IOException, JSONException{
		JSONArray	jsonArray= new JSONArray();
	JSONObject object;	
		String Url = "http://restservice.gaana.com/index.php?type=genre&subtype=genre_artist_listing&genre_id=";
		StringBuilder sb = new StringBuilder(Url);
		sb.append(genreid);
		sb.append("&limit=20");
		
		HttpGet get = new HttpGet(sb.toString());
		HttpResponse r = client.execute(get);
		int status = r.getStatusLine().getStatusCode();
		if (status == 200) {
			HttpEntity e = r.getEntity();
			String data = EntityUtils.toString(e);
			JSONObject timeline = new JSONObject(data);
			jsonArray = timeline.getJSONArray("artist");
Log.d("ArtistDetail", jsonArray.toString());
int i=0;
for(i=0;i<jsonArray.length();i++){
	object = jsonArray.getJSONObject(i);
	artistlisting.add(object.getString("artist_id"));
	
	
	
	
}
			//return jsonArray;
		}

		
		return artistlisting;

		
		
		
	}

	public static ArrayList<String> gettracklisting(String[] arg0, String artistid) throws ClientProtocolException, IOException, JSONException {
		// TODO Auto-generated method stub
		JSONArray	jsonArray= new JSONArray();
		JSONObject object;	
		
			String Url = "http://restservice.gaana.com/index.php?type=artist&subtype=artist_track_listing&artist_id=";
			StringBuilder sb = new StringBuilder(Url);
			sb.append(artistid);
			sb.append("&genre_id=");
			sb.append(arg0[0]);
			sb.append("&limit=10");
			
			HttpGet get = new HttpGet(sb.toString());
			HttpResponse r = client.execute(get);
			int status = r.getStatusLine().getStatusCode();
			if (status == 200) {
				HttpEntity e = r.getEntity();
				String data = EntityUtils.toString(e);
				JSONObject timeline = new JSONObject(data);
				jsonArray = timeline.getJSONArray("tracks");
	Log.d("TrackDetail", jsonArray.toString());
	int i=0;
	for(i=0;i<jsonArray.length();i++){
		object = jsonArray.getJSONObject(i);
		tracklisting.add(object.getString("track_id"));
		
		
		
		
	}	
	}
	
	return tracklisting;

	}

}
