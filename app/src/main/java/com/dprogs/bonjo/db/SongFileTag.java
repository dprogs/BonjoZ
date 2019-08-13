package com.dprogs.bonjo.db;

/**
 * Tag file's structure
 * @author L
 *
 */
public class SongFileTag {
	
	private int songFileId;
	private int tagId;
	
	public final static int SONG_FILE_ID_FIELD = 0;
	public final static int TAG_ID_FIELD = 1;
	
    public static final String COLUMN_SONG_ID 		= "song_id";
    public static final String COLUMN_TAG_ID		= "tag_id";

    public SongFileTag() {
		
	}
	
	public SongFileTag(int SongFileId, int tagId) {
		this.songFileId = SongFileId;
		this.tagId = tagId;
	}

	public void setSongFileId(int _id) {
		this.songFileId = _id;
	}
	
	public void setTagId(int tagId) {
		this.tagId = tagId;
	}
	
	public int getSongFileId() {
		return this.songFileId;
	}
	
	public int getTagId() {
		return this.tagId;
	}
	
	@Override
	public String toString() {
		return "SongFileId = " + songFileId +";\n" +
				"TagId = " + tagId + "\n";
	}
}