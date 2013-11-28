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
	ArrayList artistlisting;
	static HttpClient client=new DefaultHttpClient();
	
	public static ArrayList getartistlisting(int genreid) throws ClientProtocolException, IOException, JSONException{
		
		
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
		JSONArray	jsonArray = timeline.getJSONArray("artist");
Log.d("ArtistDetail", jsonArray.toString());
			//return jsonArray;
		}

		
		return null;

		
		
		
	}
	
	

	

}
