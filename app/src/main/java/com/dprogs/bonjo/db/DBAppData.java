package com.dprogs.bonjo.db;

public class DBAppData {
	 
	public final static int DATABASE_VERSION = 1;
//	public final static String DATABASE_NAME = "shopDB.sqlite3";
//	public final static String TABLE_NAME = "shop";
//	public final static String COLUMN_ID = "_id";
//	public final static String COLUMN_TITLE = "title";
//	public final static String COLUMN_PRICE = "price";

	    public final static String app_folder = "/.bonjo";
		public final static String content_folder[] = {"/atn_products/", "", "/atnint_products/"};
		public static final String DB_PATH = "/data/data/com.dprogs.bonjo/databases/";
		public static final String DB_NAME = "bonjo_sql.db";
//		public static final String DB_SQL_DUMP_NAME = "full_dump.sql";
//		public static final String ZIP_PRODUCT_NAME = "atn_products.zip";
		public static final String pathContent = ".bonjo";//".pop";
	    public static final String pathCatalog = pathContent + content_folder[0];
		
	    /* Table id's */
		public static final int TABLE_SONG_FILE_ID 		= 1;
		public static final int TABLE_TAG_ID 			= 2;
		public static final int TABLE_SONG_FILE_TAG_ID 	= 3;
		
		public static final String TABLE_DELETED_PREFIX 	= "deleted_";
		
		/* Table names */
		public static final String TABLE_SONG_FILE 			= "songfiles";
		public static final String TABLE_TAG 				= "tags";
		public static final String TABLE_SONG_FILE_TAG		= "songfilestags";
		
		/* Tables structure */
		public static final String TABLE_SONG_FILE_FIELDS 		= "(_id integer PRIMARY KEY AUTOINCREMENT,folder varchar,filename varchar,song varchar,duration varchar,artist varchar,album varchar)";
		public static final String TABLE_TAG_FIELDS 			= "(_id integer PRIMARY KEY AUTOINCREMENT,tag varchar unique)";
		public static final String TABLE_SONG_FILE_TAG_FIELDS	= "(song_id integer,tag_id integer,PRIMARY KEY (song_id,tag_id))";

//		//SQL
//		public static final String TABLE_GENERATION_UPDATE			= "_id";									//*
//		public static final String TABLE_GENERATION_LANG_UPDATE	= "gen_id, lang_id, name";
//		
//		public static final String TABLE_LANGUAGE_UPDATE 			= "_id, title";								//*
//		
//		//** miss popbuttons		_id																			//*
//		public static final String TABLE_POP_BUTTONS_PRODUCT_UPDATE= "button_id, product_id";
//		
//		public static final String TABLE_PRODUCT_UPDATE 			= "_id, pid, gen_id, is_active, sort_order";//*
//		public static final String TABLE_PRODUCT_ACCESSORY_UPDATE = "product_id, accessory_id, optional, sort_order, forsale";
//		public static final String TABLE_PRODUCT_LANG_UPDATE 		= "product_id, lang_id, name, nav_name, full_desc";
//		public static final String TABLE_PRODUCT_PARAMETER_UPDATE 	= "product_id, lang_id, name, value, sort_order";
//		public static final String TABLE_PRODUCT_PRICE_UPDATE 		= "product_id, value, current";
	 
}
