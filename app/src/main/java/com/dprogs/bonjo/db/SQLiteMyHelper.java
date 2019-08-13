package com.dprogs.bonjo.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteMyHelper extends SQLiteOpenHelper {
	static String TAG = "SQLiteMyHelper";
	
	public SQLiteMyHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		// music file table
		createIfNotExistsSongs(db);
		//tag table
		createIfNotExistsTags(db);    	
		//split table
		createIfNotExistsSplit(db);
	}

	private void createIfNotExistsSongs(SQLiteDatabase db) {
		// music file table
	   	Log.w(TAG, "create a " + DBAppData.TABLE_SONG_FILE + " table for DB " + DBAppData.DB_NAME);
	   	db.execSQL("CREATE TABLE IF NOT EXISTS " + DBAppData.TABLE_SONG_FILE + " " + DBAppData.TABLE_SONG_FILE_FIELDS);
	}
	
	private void createIfNotExistsTags(SQLiteDatabase db) {
		//tag table
    	Log.w(TAG, "create a " + DBAppData.TABLE_TAG + " table for DB " + DBAppData.DB_NAME);
    	db.execSQL("CREATE TABLE IF NOT EXISTS " + DBAppData.TABLE_TAG + " " + DBAppData.TABLE_TAG_FIELDS);
	}

	private void createIfNotExistsSplit(SQLiteDatabase db) {
    	//split table
    	Log.w(TAG, "create a " + DBAppData.TABLE_SONG_FILE_TAG + " table for DB " + DBAppData.DB_NAME);
    	db.execSQL("CREATE TABLE IF NOT EXISTS " + DBAppData.TABLE_SONG_FILE_TAG + " " + DBAppData.TABLE_SONG_FILE_TAG_FIELDS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		if (DBAppData.DATABASE_VERSION < newVersion) {
			dropAllTables();
			onCreate(db);
		}	
	}

	/** SONG FILE DB METHODS */
	
	/**
	 * Add a new song to table
	 * @param songFile
	 */
	public void addSong(SongFile songFile) {
	  SQLiteDatabase db = this.getWritableDatabase();
	  
	  if (!isTableExists(DBAppData.TABLE_SONG_FILE)) {
		  Log.w(TAG, "The table not exists or was deleted! Create a new one");
		  createIfNotExistsSongs(db);
	  }
	  ContentValues cv = new ContentValues();
	  cv.put(SongFile.COLUMN_FOLDER, songFile.getDestFolder());
	  cv.put(SongFile.COLUMN_FILENAME, songFile.getFileName());
	  cv.put(SongFile.COLUMN_SONG, songFile.getSong());
	  cv.put(SongFile.COLUMN_DURATION, songFile.getDuration());
	  cv.put(SongFile.COLUMN_ARTIST, songFile.getArtist());
	  cv.put(SongFile.COLUMN_ALBUM, songFile.getAlbum());
	  db.insert(DBAppData.TABLE_SONG_FILE, null, cv);
	  db.close();
	}

	/**
	 * Add a new song to table
	 * @param songFile
	 */
//	public void addSong2(SongFile songFile) {
//		//** ** an Example //http://www.w3schools.com/sql/sql_update.asp
//		//INSERT OR REPLACE INTO Employee ("id", "name", "role") VALUES (1, "John Foo", "CEO")
//		//INSERT OR REPLACE INTO Employee ("id", "role") VALUES (1, "code monkey")
//		String fields = "/" + SongFile.COLUMN_FOLDER + "/, " + 
//						"/" + SongFile.COLUMN_FILENAME + "/, " + 
//						"/" + SongFile.COLUMN_SONG + "/, " + 
//						"/" + SongFile.COLUMN_ARTIST + "/, " + 
//						"/" + SongFile.COLUMN_ALBUM + "/, ";
//		
//		String values = "/" + songFile.getDestFolder() + "/, " +
//						"/" + songFile.getFileName() + "/, " +
//						"/" + songFile.getSong() + "/, " +
//						"/" + songFile.getArtist() + "/, " +
//						"/" + songFile.getAlbum() + "/, ";
//		
//		String sql = "INSERT OR REPLACE INTO " + DBAppData.TABLE_SONG_FILE + " (" + fields + ") VALUES (" + values + ")";
//		SQLiteDatabase db = this.getWritableDatabase();
//		db.execSQL(sql);
//		db.close();
//	}

	/**
	 * Delete a song from table
	 * @param songFile - an object of SongFile class
	 * @return
	 */
	public int deleteSongFromTable(SongFile songFile) {
		  SQLiteDatabase db = this.getWritableDatabase();
		  return db.delete(DBAppData.TABLE_SONG_FILE, SongFile.COLUMN_ID + "=" + String.valueOf(songFile.getId()), null);
		  //return db.delete(DBAppData.TABLE_SONG_FILE, SongFile.COLUMN_ID + "=?", new String[] { String.valueOf(songFile.getId()) });
		}

	/**
	 * Delete a song from table by song's id
	 * @param songId
	 */
	public void deleteSongFromTableById(int songId) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("DELETE FROM " + DBAppData.TABLE_SONG_FILE + " WHERE _ID =" + songId);		
	}

//		public int deleteSongFromTableById(int songId) {
//			  SQLiteDatabase db = this.getWritableDatabase();
//			  return db.delete(DBAppData.TABLE_SONG_FILE, SongFile.COLUMN_ID + "=" + songId, null);
//		}

    /**
     * isTableExists - checks that defined table exists in current database
     * @param db
     * @param tableName
     * @return
     */
	public boolean isTableExists(String table) {
		//SELECT * FROM sqlite_master WHERE name ='myTable' and type='table'; 
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE type = ? AND name = ?", new String[] {"table", table});
        if (!cursor.moveToFirst()) {
            return false;
        }
        
        int count = cursor.getInt(0);
        cursor.close();
        return count > 0;
	}
	
	/**
	 * Get all songs list stored in database
	 * @return
	 */
	public List<SongFile> getAllSongs() {
		Cursor cursor;
		List<SongFile> songList = new ArrayList<SongFile>();
		String query = "SELECT * FROM " + DBAppData.TABLE_SONG_FILE;
		SQLiteDatabase db = this.getReadableDatabase();
		try {
			cursor = db.rawQuery(query, null);
		}
		catch (SQLException e) {
			e.printStackTrace();
			Log.e(TAG, "The table probably doesn't exists!");
			return null;
		}
		if(cursor.moveToFirst()) {
			do {
				SongFile songFile = new SongFile();
				songFile.setId(Integer.parseInt(cursor.getString(SongFile.ID_FIELD)));
				songFile.setDestFolder((cursor.getString(SongFile.DEST_FOLDER_FIELD)));
				songFile.setFileName(cursor.getString(SongFile.FILE_NAME_FIELD));
				songFile.setSong(cursor.getString(SongFile.SONG_FIELD));
				songFile.setDuration(cursor.getString(SongFile.DURATION));
				songFile.setArtist(cursor.getString(SongFile.ARTIST_FIELD));
				songFile.setAlbum(cursor.getString(SongFile.ALBUM_FIELD));
				songList.add(songFile);
			} while(cursor.moveToNext());
		}
		return songList;
	}

	/**
	 * Return a full SongFile object that relate to some id
	 * @param songId
	 * @return
	 */
	public SongFile getSongById(int songId) {
		SongFile songFile = new SongFile();
		String query = "SELECT * FROM " + DBAppData.TABLE_SONG_FILE + " WHERE _id = " + songId;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		if (cursor.moveToFirst()) {
			songFile.setId(songId);
			songFile.setDestFolder(cursor.getString(SongFile.DEST_FOLDER_FIELD));
			songFile.setFileName(cursor.getString(SongFile.FILE_NAME_FIELD));
			songFile.setSong(cursor.getString(SongFile.SONG_FIELD));
			songFile.setDuration(cursor.getString(SongFile.DURATION));
			songFile.setArtist(cursor.getString(SongFile.ARTIST_FIELD));
			songFile.setAlbum(cursor.getString(SongFile.ALBUM_FIELD));
			//Log.i(TAG, "getSongById: #" + songFile.getSong() + " id:" + songId);
		}
		return songFile;
	}

	/** TAG DB METHODS */
	
	public void dropAllTables() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("DROP TABLE IF EXISTS " + DBAppData.TABLE_SONG_FILE);
		db.execSQL("DROP TABLE IF EXISTS " + DBAppData.TABLE_TAG);
		db.execSQL("DROP TABLE IF EXISTS " + DBAppData.TABLE_SONG_FILE_TAG);
		db.close();
	}
	
	/**
	 * Delete tag from table by tag's id
	 * @param tagId
	 */
	public void deleteTagFromTableById(int tagId) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("DELETE FROM " + DBAppData.TABLE_TAG + " WHERE _ID =" + tagId);		
	}
	
	/**
	 * Get all tags list stored in database
	 * @return
	 */
	public List<Tag> getAllTags() {
		List<Tag> tagList = new ArrayList<Tag>();
		String query = "SELECT * FROM " + DBAppData.TABLE_TAG;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		if(cursor.moveToFirst()) {
			do {
				Tag tag = new Tag();
				tag.setId(Integer.parseInt(cursor.getString(Tag.ID_FIELD)));
				tag.setTag((cursor.getString(Tag.TAG_NAME)));
				tagList.add(tag);
			} while(cursor.moveToNext());
		}
		return tagList;
	}

	/**
	 * Looking for #tag by id
	 * @param tagId
	 * @return
	 */
	public String getTagById(int tagId) {
		String query = "SELECT * FROM " + DBAppData.TABLE_TAG + " WHERE _id = " + tagId;
		String tagName = "no tag";
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		if (cursor.moveToFirst()) {
			tagName = cursor.getString(Tag.TAG_NAME);
			Log.i(TAG, "getTagById: #" + tagName + " id:" + tagId);
		}
		return tagName;
	}
	
	/**
	 * Collect a list of Tag objects with tag id and tag name
	 * @param songId
	 * @return
	 */
	public List<Tag> getTagsBySongId(int songId) {
		List<Tag> tagList = new ArrayList<Tag>();
		int tagId;
		String tagName;
		
		String query = "SELECT * FROM " + DBAppData.TABLE_SONG_FILE_TAG + " WHERE song_id = " + songId;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		if(cursor.moveToFirst()) {
			do {
				tagId = Integer.parseInt(cursor.getString(SongFileTag.TAG_ID_FIELD));
				tagName = getTagById(tagId);
				Tag tag = new Tag();
				if (tagName != null) {
					tag.setId(tagId);
					tag.setTag(tagName);
					tagList.add(tag);
				}
			} while(cursor.moveToNext());
		}
		return tagList;
	}

	/**
	 * Get all tagged songs list stored in database
	 * @return
	 */
	public List<SongFileTag> getAllTaggedSongs() {
		List<SongFileTag> taggedSongsList = new ArrayList<SongFileTag>();
		String query = "SELECT * FROM " + DBAppData.TABLE_SONG_FILE_TAG;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		if(cursor.moveToFirst()) {
			do {
				SongFileTag songFileTag = new SongFileTag();
				songFileTag.setSongFileId(Integer.parseInt(cursor.getString(SongFileTag.SONG_FILE_ID_FIELD)));
				songFileTag.setTagId(Integer.parseInt(cursor.getString(SongFileTag.TAG_ID_FIELD)));
				taggedSongsList.add(songFileTag);
			} while(cursor.moveToNext());
		}
		return taggedSongsList;
	}
	
	/**
	 * Add a new tag to Tag table. The tag value should be unique
	 * @param tag
	 */
	public void addTag(String tag) {
		//INSERT OR REPLACE INTO Employee ("id", "name", "role") VALUES (1, "John Foo", "CEO")
		 
		SQLiteDatabase db = this.getWritableDatabase();
		String sql = "INSERT OR IGNORE INTO " + DBAppData.TABLE_TAG + "(" + Tag.COLUMN_TAG + ") VALUES ('" + tag + "')";
		try {
		db.execSQL(sql);
		}
		catch (SQLiteException e) {
			Log.w(TAG, "insert tag #" + tag + " error");
			e.printStackTrace();
		}
//		  ContentValues cv = new ContentValues();
//		  if (!cv.containsKey(tag)) {
//			  cv.put(Tag.COLUMN_TAG, tag);
//			  db.insert(DBAppData.TABLE_TAG, null, cv);
//			  Log.w(TAG, "#tag " + tag + " was added");
//		  }
//		  else
//			  Log.w(TAG, "#tag " + tag + " is exists");
//		  db.close();
		}
	
	/**
	 * Collect a list of songs relates to some #tag. <br>
	 * This method fully returned a tagged <b>playlist</b>
	 * @param tagId
	 * @return
	 */
	public List<SongFile> getSongsByTagId(int tagId) {
		List<SongFile> songList = new ArrayList<SongFile>();
		int songId;
		
		String query = "SELECT * FROM " + DBAppData.TABLE_SONG_FILE_TAG + " WHERE tag_id = " + tagId;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		if(cursor.moveToFirst()) {
			do {
				songId = Integer.parseInt(cursor.getString(SongFileTag.SONG_FILE_ID_FIELD));
				songList.add(getSongById(songId));
			} while(cursor.moveToNext());
		}
		return songList;
	}

	/**
	 * Get tag id by tag name (text value)
	 * @param tag
	 * @return
	 */
	public int getTagIdByName(String tag) {
		int tagId = -1;
		
		String query = "SELECT * FROM " + DBAppData.TABLE_TAG + " WHERE tag = '" + tag + "'";
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		if(cursor.moveToFirst()) {
			tagId = Integer.parseInt(cursor.getString(Tag.ID_FIELD));
		}
		return tagId;
	}
	
	/**
	 * Create a record in a split table - song_id to according tag_id
	 * @param songId
	 * @param tagId
	 */
	public void addSongFileTag(int songId, int tagId) {
	  SQLiteDatabase db = this.getWritableDatabase();
	  ContentValues cv = new ContentValues();
	  cv.put(SongFileTag.COLUMN_SONG_ID, songId);
	  cv.put(SongFileTag.COLUMN_TAG_ID, tagId);
	  db.insert(DBAppData.TABLE_SONG_FILE_TAG, null, cv);
	  db.close();
	}
	
	/**
	 * Add a new #tag to song (by song id)
	 * @param songId
	 * @param tag
	 */
	public void TagSong(int songId, String tag) {
		addTag(tag);
		int tagId = getTagIdByName(tag);
		if (tagId > 0 ) {
			addSongFileTag(songId, tagId);
		}
		else
			Log.e(TAG, "#tag " + tag + " not found");
	}
//http://developer.alexanderklimov.ru/android/sqlite/android-sqlite.php#SQLiteDatabase
}