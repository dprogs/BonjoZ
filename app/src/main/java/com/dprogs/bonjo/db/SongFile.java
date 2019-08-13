package com.dprogs.bonjo.db;

/**
 * Song file's structure
 * @author L
 *
 */
public class SongFile {

	private int id;				//0
	private String destFolder;	//1
	private String fileName;	//2
	
	private String song;		//3
	private String duration;	//4
	private String artist;		//5
	private String album;		//6
	
	public final static int ID_FIELD 			= 0;
	public final static int DEST_FOLDER_FIELD 	= 1;
	public final static int FILE_NAME_FIELD 	= 2;
	public final static int SONG_FIELD 			= 3;
	public final static int DURATION			= 4;
	public final static int ARTIST_FIELD 		= 5;
	public final static int ALBUM_FIELD 		= 6;
	
    public static final String COLUMN_ID 		= "_id";
    public static final String COLUMN_FOLDER 	= "folder";
    public static final String COLUMN_FILENAME	= "filename";
    public static final String COLUMN_SONG		= "song";
    public static final String COLUMN_DURATION 	= "duration";
    public static final String COLUMN_ARTIST	= "artist";
    public static final String COLUMN_ALBUM		= "album";

    public SongFile() {
		
	}
	
	public SongFile(String destFolder, String fileName, String song, String duration, String artist, String album) {
//		this.id = id;
		this.destFolder = destFolder;
		this.fileName = fileName;
		this.song = song;
		this.duration = duration;
		this.artist = artist;
		this.album = album;
	}
	
	public void setId(int _id) {
		this.id = _id;
	}
	
	public void setDestFolder(String folder) {
		this.destFolder = folder;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public void setSong(String song) {
		this.song = song;
	}
	
	public void setDuration(String duration) {
		this.duration = duration;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	public void setAlbum(String album) {
		this.album = album;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getDestFolder() {
		return this.destFolder;
	}
	
	public String getFileName() {
		return this.fileName;
	}
	
	public String getSong() {
		return this.song;
	}
	
	public String getDuration() {
		return this.duration;
	}
	
	public String getArtist() {
		return this.artist;
	}
	
	public String getAlbum() {
		return this.album;
	}
	
	@Override
	public String toString() {
		return "SongFile: \n" + 
                "id = " + id + ";\n" +
                "destFolder = " + destFolder + ";\n" +
                "fileName =" + fileName + ";\n" +
                "song = " + song + ";\n" +
                "artist = " + artist + ";\n" +
                "album = " + album + ";\n";
	}
}