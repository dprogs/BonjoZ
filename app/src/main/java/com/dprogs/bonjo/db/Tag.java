package com.dprogs.bonjo.db;

public class Tag {
	
	private int id;
	private String tagName;
	
	public final static int ID_FIELD = 0;
	public final static int TAG_NAME = 1;

    public static final String COLUMN_ID 		= "_id";
    public static final String COLUMN_TAG 		= "tag";
	
	public Tag() {
		
	}
	
	public Tag(int id, String tag) {
		this.id = id;
		this.tagName = tag;
	}
	
	public void setId(int _id) {
		this.id = _id;
	}
	
	public void setTag(String tag) {
		this.tagName = tag;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getTag() {
		return this.tagName;
	}
	
	@Override
	public String toString() {
		return 	"id = " + id + ";\n" +
				"tag: #" + tagName + ";\n";
	}
}
