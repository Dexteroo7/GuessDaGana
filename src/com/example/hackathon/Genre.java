package com.example.hackathon;

public class Genre {

	private static String[] genrenames = { "Bollywood", "Tamil", "Classical",
			"Dance", "Electronic", "Folk", "Punjabi", "R&B", "Romantic" };
	private static int[] genreids = { 56, 25, 206, 67, 71, 85, 23, 88, 5 };
	private int genreid;
	private int[] artistids;
	private String genrename;

	public Genre(int genreid, String genrename) {
		this.genreid = genreid;
		this.genrename = genrename;

	}

	public void setartisisid(int[] artistlist) {

		this.artistids = artistlist;
	}

	public int[] getartistids() {

		return this.artistids;

	}

	public static int getgenreid(String genrenme) {

		int i = 0;

		for (i = 0; i < genrenames.length; i++) {
			if (genrenme.equals(genrenames[i])) {

				return genreids[i];

			}
		}
		return 0;

	}

}
