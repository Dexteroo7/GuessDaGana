package com.example.hackathon;

import java.util.ArrayList;

public class Song {

	private int TrackId;
	private String TrackTitle;
	private int AlbumId;
	private String AlbumTitle;
	public Song(int trackid, String TrackTitle, int AlbumId, String Albumtitle) {

		this.TrackId = trackid;
		this.TrackTitle = TrackTitle;
		this.AlbumId = AlbumId;
		this.AlbumTitle = Albumtitle;

	}

	public int gettrackid() {

		return TrackId;
	}

	public void settrackid(int Trackid) {

		this.TrackId = Trackid;

	}

	public String gettracktitle() {

		return TrackTitle;
	}

	public void settracktitle(String Tracktitle) {

		this.TrackTitle = Tracktitle;

	}

	public int getalbumid() {

		return AlbumId;
	}

	public void setalbumid(int albumid) {

		this.AlbumId = albumid;

	}

	public String getalbumname() {

		return AlbumTitle;
	}

	public void setalbumtitleid(String albumtitle) {

		this.AlbumTitle = albumtitle;

	}

}
