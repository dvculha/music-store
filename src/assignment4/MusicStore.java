// Dafne Culha - 260785524

package assignment4;

import java.util.ArrayList;
import java.util.LinkedList;

public class MusicStore {
	//ADD YOUR CODE BELOW HERE

	private  MyHashTable<String, Song> title_HT;
	private  MyHashTable<Integer, ArrayList<Song>> year_HT;
	private  MyHashTable<String, ArrayList<Song>> artist_HT;

	//private  MyHashTable<String, Song> songTable = new  MyHashTable<String, Song>(100);
	//private  MyHashTable<Integer, ArrayList<Song>> year_HT = new  MyHashTable<Integer,  ArrayList<Song>>(100);
	//private  MyHashTable<String, ArrayList<Song>> artist_HT = new  MyHashTable<String,  ArrayList<Song>>(100);

	//ADD YOUR CODE ABOVE HERE


	public MusicStore(ArrayList<Song> songs) {
		//ADD YOUR CODE BELOW HERE

		this.title_HT = new MyHashTable<String, Song>(100);
		this.year_HT = new MyHashTable<Integer,  ArrayList<Song>>((100));
		this.artist_HT = new MyHashTable<String,  ArrayList<Song>>(100);

		for (Song song: songs){

			String title = song.getTitle();
			String artist = song.getArtist();
			int year = song.getYear();

			title_HT.put(title, song);

			if(artist_HT.get(artist) == null){
				ArrayList <Song> song_array = new ArrayList <Song>();
				song_array.add(song);
				artist_HT.put(artist, song_array);
			}

			else{
				artist_HT.get(artist).add(song);
			}


			if(year_HT.get(year) == null){
				ArrayList <Song> song_array = new ArrayList <Song>();
				song_array.add(song);
				year_HT.put(year, song_array);	
			}

			else{
				year_HT.get(year).add(song); 
			}
		}


		//ADD YOUR CODE ABOVE HERE
	}


	/**
	 * Add Song s to this MusicStore
	 */
	public void addSong(Song s) {
		// ADD CODE BELOW HERE

		this.title_HT.put(s.getTitle(), s);

		if (this.artist_HT.get(s.getArtist()) == null) {
			ArrayList <Song> songs_by_artist = new ArrayList<> ();
			songs_by_artist.add(s);
			this.artist_HT.put(s.getArtist(), songs_by_artist);
		}
		else this.artist_HT.get(s.getArtist()).add(s);

		if(this.year_HT.get(s.getYear()) == null) {

			ArrayList <Song> songs_by_year = new ArrayList<> ();
			songs_by_year.add(s);
			this.year_HT.put(s.getYear(), songs_by_year);


		}
		else this.year_HT.get(s.getYear()).add(s);

	}
	/*
    	ArrayList<Song> songs_by_artist = artist_HT.get(s.getArtist());
    	ArrayList<Song> songs_by_year = year_HT.get(s.getYear());

    	songs_by_artist.add(s);
    	songs_by_year.add(s);

    	title_HT.put(s.getTitle(), s);
    	artist_HT.put(s.getArtist(), songs_by_artist);
    	year_HT.put(s.getYear(), songs_by_year);

        // ADD CODE ABOVE HERE


    /**
	 * Search this MusicStore for Song by title and return any one song 
	 * by that title 
	 */
	public Song searchByTitle(String title) {
		//ADD CODE BELOW HERE

		return title_HT.get(title);

		//ADD CODE ABOVE HERE
	}

	/**
	 * Search this MusicStore for song by `artist' and return an 
	 * ArrayList of all such Songs.
	 */
	public ArrayList<Song> searchByArtist(String artist) {
		//ADD CODE BELOW HERE

		return artist_HT.get(artist);

		//ADD CODE ABOVE HERE
	}

	/**
	 * Search this MusicSotre for all songs from a `year'
	 *  and return an ArrayList of all such  Songs  
	 */

	public ArrayList<Song> searchByYear(Integer year) {
		//ADD CODE BELOW HERE

		return year_HT.get(year);

		//ADD CODE ABOVE HERE

	}
}
