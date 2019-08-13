package com.dprogs.bonjo.db;


import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SQLDatabaseHelper {//extends SQLiteOpenHelper 

    private static final String TAG = SQLDatabaseHelper.class.getName();							
	
	private SQLiteDatabase database;
    private Context  mContext;

    public SQLiteDatabase getDb() {
        return database;
    }

    /**
     * SQLDatabaseHelper (конструктор)
     * @param mContext
     * @return 
     */
//    public SQLDatabaseHelper(Context mContext) 
//    {
//        super(mContext, DB_NAME, null, 1);
//        this.mContext = mContext;
//    }

    /**
     * deleteEntries - удал€ет запись(си) из указанной таблицы, по заданному полю с заданным значением
     * @param parentActivity	активити из которого вызов
     * @param tableName			им€ таблицы
     * @param rowName			им€ столбца (обычно используетс€ _id)
     * @param rowValues			значение столбца дл€ пол€, которое надо удалить
     */
    public void deleteEntries(Activity parentActivity, String tableName, String rowName, List<String> rowValues) {
    	String sqlStr;						//формируемое-sql выражение
		String path = DBAppData.DB_PATH + DBAppData.DB_NAME;	//полный путь  к Ѕƒ	
    	Log.w(TAG, "deleteEntries " + path);
    	if (checkDataBaseExists()) {
    	   Log.w(TAG, "database is exists");
    	   database = parentActivity.openOrCreateDatabase(path, SQLiteDatabase.CREATE_IF_NECESSARY, null);//, SQLiteDatabase.CREATE_IF_NECESSARY);
	       
    	   //** ** an Example http://www.w3schools.com/sql/sql_delete.asp
	       //DELETE FROM Customers  WHERE CustomerName='Alfreds Futterkiste' AND ContactName='Maria Anders'; 
	       //** ** ** ** ** **
    	   for (String value: rowValues) {
	    	   sqlStr = "DELETE FROM " + tableName + " WHERE " + rowName + " = " + value;	// AND ContactName='Maria Anders'"
		       //execute an sql command   
		       try {
		    	   database.execSQL(sqlStr);
			       //sql  = "DELETE FROM products WHERE _id=250
			       Log.w(TAG,"DELETE ROW ("+rowName+")-->VALUE ("+value+") from TABLE "+tableName);///TABLE IF EXISTS " + tableName);
		       }
		       catch (SQLException e) {
		    	   e.printStackTrace();
		    	   Log.w(TAG, "Unable to delete product with " + rowName + " = " + value);
		       }
	       }
    	}
    }
    
    /**
     * updateEntries - обновл€ет запись(си) из указанной таблицы, по заданному полю с заданным значением
     * @param parentActivity	активити из которого вызов
     * @param tableName			им€ обновл€емой таблицы
     * @param newValuesString   список строк
     */
    public void updateEntries(Activity parentActivity, String tableName, List<String> newValuesString) {
    	String sqlStr;						//формируемое-sql выражение
		String path = DBAppData.DB_PATH + DBAppData.DB_NAME;	//полный путь  к Ѕƒ	
		String updates = "";
		
    	Log.w(TAG, "updateEntries " + path);
    	if (checkDataBaseExists()) {
    	   Log.w(TAG, "database is exists");
    	   database = parentActivity.openOrCreateDatabase(path, SQLiteDatabase.CREATE_IF_NECESSARY, null);//, SQLiteDatabase.CREATE_IF_NECESSARY);
	       
    	   //** ** an Example //http://www.w3schools.com/sql/sql_update.asp
	       //INSERT OR REPLACE INTO Employee ("id", "name", "role") VALUES (1, "John Foo", "CEO")
    	   //INSERT OR REPLACE INTO Employee ("id", "role") VALUES (1, "code monkey")
	       //** ** ** ** ** **
    	   //updates = getTableUpdates(getTableId(tableName));
    	   
    	   for (String value: newValuesString) {
	    	   sqlStr = "INSERT OR REPLACE INTO " + tableName + " (" + updates + ") VALUES (" + value + ")";
		       //sql  = "DELETE FROM products WHERE _id=250
		       Log.w(TAG,"INSERT OR REPLACE --> VALUE (" + value + "), update=" + updates + " in TABLE " + tableName);///TABLE IF EXISTS " + tableName);
		       //execute an sql command   
		       database.execSQL(sqlStr);
	       }
    	}
    }
    
//    /**
//     * updateDBTables 			главный метод процесса обновлени€ таблиц базы
//     * @param parentActivity
//     * @param tableNames
//     * @return
//     */
//	public SQLiteDatabase updateDBTables(Activity parentActivity, List<File> tableNames) {
//		String path = DB_PATH + DB_NAME;		
//    	Log.w(TAG, "updateDBTable " + path);
//    	if (checkDataBase()) {
//    	   Log.w(TAG, "database is exists");
//    	   database = parentActivity.openOrCreateDatabase(path, SQLiteDatabase.CREATE_IF_NECESSARY, null);//, SQLiteDatabase.CREATE_IF_NECESSARY);
//        
////    	   for (File csv: tableNames)
////    	   {
////	           if (csv.getName().contains("deleted"))
////	        	   
////    		   deleteTable(csv.getName().replace(".csv", ""));
////	           createTable(csv.getName().replace(".csv", ""));
////    	   }
//           dumpCSV(parentActivity, tableNames);
//    	}
//    	else
//    		Log.w(TAG, "Database is not exists, update is impossible");
//		return database;
//	}

	/**
     * openDataBase() открываем Ѕƒ (DB_PATH + DB_NAME)
     * @return
     * @throws SQLException
     */
//    public SQLiteDatabase openOrCreateDB(Activity parentActivity) throws SQLException {
//        if (database == null) {
//        	String path = DBAppData.DB_PATH + DBAppData.DB_NAME;
//        	Log.w(TAG, "openOrCreateDatabase " + path);
//        	if (!checkDataBaseExists()) {
//        	   Log.w(TAG, "database not exist");
//        	   database =  parentActivity.openOrCreateDatabase(path, SQLiteDatabase.CREATE_IF_NECESSARY, null);//, SQLiteDatabase.CREATE_IF_NECESSARY);
//            
//               deleteTables();
//               createTables();
//
//               dumpDB(DBAppData.DB_SQL_DUMP_NAME);
//        	}
//        	else {
//         	   Log.w(TAG, "databse is exist, opening..");
//         	   database  = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
//         	   
//         	   if (!isTableExists(database, DBAppData.TABLE_SONG_FILE)) {
//         		  createTable(database, DBAppData.TABLE_SONG_FILE, DBAppData.TABLE_SONG_FILE_FIELDS);         		   
//                  //dumpDB(DB_SQL_DUMP_NAME, TABLE_MUSIC_FILE);
//         	   }
//
//         	   if (!isTableExists(database, DBAppData.TABLE_TAG)) {
//         		  createTable(database, DBAppData.TABLE_TAG, DBAppData.TABLE_TAG_FIELDS);         		   
//                  //dumpDB(DB_SQL_DUMP_NAME, TABLE_TAG);
//         	   }
//
//         	   if (!isTableExists(database, DBAppData.TABLE_SONG_FILE_TAG)) {
//         		  createTable(database, DBAppData.TABLE_SONG_FILE_TAG, DBAppData.TABLE_SONG_FILE_TAG_FIELDS);         		   
//                  //dumpDB(DB_SQL_DUMP_NAME, TABLE_MUSIC_FILE_TAG);
//         	   }
//
//        	}
//        	
////          database.execSQL("INSERT INTO languages (_id, title) VALUES (4, 'English');");
////        	database.execSQL("INSERT INTO languages (_id, title) VALUES (7, 'Russian');");
//        	
//       }
//       Log.w(TAG, "openOrCreare DB="+database);
//       return database;
//    }
    
    
    /**
     * isTableExists - провер€ет, существует ли таблица с именем tableName в базе bd
     * @param db
     * @param tableName
     * @return
     */
    boolean isTableExists(SQLiteDatabase db, String tableName) {
        Log.w(TAG, "isTableExists.. -> " + tableName);
    	if (tableName == null || db == null || !db.isOpen()) {
            return false;
        }
    	//SELECT * FROM sqlite_master WHERE name ='myTable' and type='table'; 
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE type = ? AND name = ?", new String[] {"table", tableName});
        if (!cursor.moveToFirst()) {
            return false;
        }
        
        int count = cursor.getInt(0);
        cursor.close();
        return count > 0;
    }   
    
    /**
     * deleteTable
     * @param db
     * @param tableName
     */
    void deleteTable(SQLiteDatabase db, String tableName) {
        Log.w(TAG, "deleteTable.. -> "+tableName);
    	if (tableName == null || db == null || !db.isOpen()) {
            return;  //выход
        }

    	Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE type = ? AND name = ?", new String[] {"table", tableName});
        if (cursor.moveToFirst()) {
        	Log.w(TAG, "drop a "+tableName+" table");
        	database.execSQL("DROP TABLE IF EXISTS " + tableName);
        }
        
    }   

    @SuppressWarnings("unused")
	void createTable(SQLiteDatabase db, String tableName, String fields) {
        Log.w(TAG, "createTable.. -> "+tableName);
    	if (tableName != null & db != null & fields != null & db.isOpen()) {
	    	Log.w(TAG, "create a "+tableName+" table");
	    	database.execSQL("CREATE TABLE IF NOT EXISTS " + tableName + " " + fields);
        }
    	else
    		if (db == null)
    			Log.w(TAG, " db is null");
    		else
    		    Log.w(TAG, "unable to create table.. table: "+tableName+" fields: "+fields+" db.isopen "+db.isOpen());
    }   

    /**
     * deleteTable
     * @param tableName
     */
    public void deleteTable(String tableName) {
    	//generations
    	Log.w(TAG, "drop a "+tableName+" table (single)");
    	database.execSQL("DROP TABLE IF EXISTS " + tableName);
    }
    
    /**
     * getTableId	- возвращает индекс таблицы
	 * TABLE_SONG_FILE_ID 		= 1;
	 * TABLE_TAG_ID 	= 2;
	 * TABLE_SONG_FILE_TAG_ID 			= 3;
     * @param tableName (им€ таблицы)
     * @return int value
     */
    private int getTableId(String tableName) {
    	int tableId = 0;
    	//1 generation
    	switch (tableName) {
	    	case DBAppData.TABLE_SONG_FILE: tableId = DBAppData.TABLE_SONG_FILE_ID;
	    	break;
	    	case DBAppData.TABLE_TAG: tableId = DBAppData.TABLE_TAG_ID;
	    	break;
	    	case DBAppData.TABLE_SONG_FILE_TAG: tableId = DBAppData.TABLE_SONG_FILE_TAG_ID;
	    	break;
    	}
     	return tableId;
    }
    
    /**
     * getTableFields 		по id таблицы возвращает строку с пол€ми таблицы
     * @param tableId		id таблицы
     * @return
     */
    public String getTableFields(int tableId) {
    	switch (tableId) {
    		case DBAppData.TABLE_SONG_FILE_ID: 	return DBAppData.TABLE_SONG_FILE_FIELDS;
			case DBAppData.TABLE_TAG_ID: 			return DBAppData.TABLE_TAG_FIELDS;
			case DBAppData.TABLE_SONG_FILE_TAG_ID: return DBAppData.TABLE_SONG_FILE_TAG_FIELDS;
	    }
    	return null;
    }
  
//    /**
//     * 
//     * @param tableId
//     * @return
//     */
//    public String getTableUpdates(int tableId)
//    {
//    	switch (tableId)
//    	{
//    		case TABLE_GENERATION_ID: 			return TABLE_GENERATION_UPDATE;
//			case TABLE_GENERATION_LANG_ID: 		return TABLE_GENERATION_LANG_UPDATE;
//			case TABLE_LANGUAGE_ID: 			return TABLE_LANGUAGE_UPDATE;
//			//case TABLE_POP_BUTTONS_ID: 			return TABLE_POP_BUTTONS_UPDATE;
//			case TABLE_POP_BUTTONS_PRODUCT_ID:	return TABLE_POP_BUTTONS_PRODUCT_UPDATE;
//			case TABLE_PRODUCT_ID: 				return TABLE_PRODUCT_UPDATE;
//			case TABLE_PRODUCT_ACCESSORY_ID:	return TABLE_PRODUCT_ACCESSORY_UPDATE;
//			case TABLE_PRODUCT_LANG_ID: 		return TABLE_PRODUCT_LANG_UPDATE;
//			case TABLE_PRODUCT_PARAMETER_ID:	return TABLE_PRODUCT_PARAMETER_UPDATE;
//			case TABLE_PRODUCT_PRICE_ID: 		return TABLE_PRODUCT_PRICE_UPDATE;
//	    }
//    	return null;
//    }
    
    /**
     * createTables - создаем таблицы
     */
    public void createTable(String tableName) {
    	//table name
    	Log.w(TAG, "create a "+tableName+" table");
    	//
    	String field = null;
    	field = getTableFields(getTableId(tableName));
    			
    	Log.w(TAG, "fields "+field+" for table");
    	database.execSQL("CREATE TABLE IF NOT EXISTS " + tableName + " " + field);
    }
    
    public void deleteAllTables() {
    	//generations
    	Log.w(TAG, "drop a " + DBAppData.TABLE_SONG_FILE + " table");
    	database.execSQL("DROP TABLE IF EXISTS " + DBAppData.TABLE_SONG_FILE);
    	//generation language
    	Log.w(TAG, "drop a " + DBAppData.TABLE_TAG + " table");
    	database.execSQL("DROP TABLE IF EXISTS " + DBAppData.TABLE_TAG);
    	//language
    	Log.w(TAG, "drop a " + DBAppData.TABLE_SONG_FILE_TAG + " table");
    	database.execSQL("DROP TABLE IF EXISTS " + DBAppData.TABLE_SONG_FILE_TAG);
    }
    
    /**
     * createTables - создаем таблицы
     */
    public void createTables() {
    	//generations
    	Log.w(TAG, "create a " + DBAppData.TABLE_SONG_FILE + " table");
    	database.execSQL("CREATE TABLE IF NOT EXISTS " + DBAppData.TABLE_SONG_FILE + " " + DBAppData.TABLE_SONG_FILE_FIELDS);
    	//generation language
    	Log.w(TAG, "create a " + DBAppData.TABLE_TAG + " table");
    	database.execSQL("CREATE TABLE IF NOT EXISTS " + DBAppData.TABLE_TAG + " " + DBAppData.TABLE_TAG_FIELDS);
    	//language
    	Log.w(TAG, "create a " + DBAppData.TABLE_SONG_FILE_TAG + " table");
    	database.execSQL("CREATE TABLE IF NOT EXISTS " + DBAppData.TABLE_SONG_FILE_TAG + " " + DBAppData.TABLE_SONG_FILE_TAG_FIELDS);
    }

	public void dumpDB(String sqlFullDumpName) {
		Log.w(TAG, "dumpDB");
		//read sql file
		//parse a string
		//execute sql instruction

		//openOrCreateDataBase();

		//reading the values line by line and save them in the arraylist :"LIST"
        try {
	        File sdcard = Environment.getExternalStorageDirectory();
	        File file = new File(sdcard, /**temp_folder*/DBAppData.app_folder+"/"+sqlFullDumpName);
        	Log.w(TAG, "open: "+/**temp_folder*/DBAppData.app_folder+"/"+sqlFullDumpName);
	
	
	        BufferedReader br = new BufferedReader(new FileReader(file));  
	        String line;   
	        int i = 0;
	        while ((line = br.readLine()) != null) {
	            i++; 
	        	Log.w(TAG, i+". line: "+line);
	        	database.execSQL(line);
	        } 
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        //////////////////////////
		//how to delete database
		//mContext.deleteDatabase("");
		
	}
	
	

//    public void createDataBase() throws IOException 
//    {
//      SQLiteDatabase db_Read = null;
//      Log.e("DBBBBBBBB", "before check");  
//      if(!checkDataBase())
//        {
//          db_Read = this.getReadableDatabase();
//          db_Read.close();
//          Log.e("DBBBBBBBB", "after check");  
//            //getReadableDatabase();
//            try 
//            {
//                copyDataBase();
//            } 
//            catch (IOException e) 
//            {
//                throw new Error("Error copying database");
//            }
//        }
//    }

//        I have found a solution thanks to a user of my application.
//        In the createdatabase function, it must be added:
//        public void createDataBase() throws IOException{
//
//        boolean dbExist = checkDataBase();
//        SQLiteDatabase db_Read = null;
//
//        if(dbExist){
//        //do nothing - database already exist
//        }else{
//
//        //By calling this method and empty database will be created into the default system path
//        //of your application so we are gonna be able to overwrite that database with our database.
//        db_Read = this.getReadableDatabase();
//        db_Read.close();
//
//        try {
//
//        copyDataBase();
//
//        } catch (IOException e) {
//
//        throw new Error("Error copying database");
//
//        }
//        }
//
//        }


    /**
     * ”дал€ет исходник (SQLite dump) базы данных
     * @param src
     * @return
     */
    private static boolean deleteSource(String src) {
    	File file = new File(src);
    	Log.w(TAG, "Deleting... "+src);
    	return file.delete();
    }
 
    static boolean DeleteRecursive(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory())
            for (File child : fileOrDirectory.listFiles())
                DeleteRecursive(child);

        return fileOrDirectory.delete();
    }
    
    public static boolean deleteDir(String src) {
    	File file = new File(src);
    	Log.w(TAG, "Deleting... "+src);
    	return DeleteRecursive(file);
    }

    private void copyDataBase() throws IOException {
        String pathSource = Environment.getExternalStorageDirectory() + DBAppData.app_folder + "/" + DBAppData.DB_NAME;
    	Log.w(TAG, "copyDataBase .. " + pathSource);
    	InputStream myInput =  new FileInputStream(pathSource); //mContext.getAssets().open(DB_NAME);
        String outFileName = DBAppData.DB_PATH + DBAppData.DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();

        Log.w(TAG, pathSource + " deleting...");
        if (deleteSource(pathSource)) 
          Log.w(TAG, pathSource + " successfully deleted");
    }

    /**
     * checkDataBase() 
     * @return true if exist
     */
    public static boolean checkDataBaseExists() {
        SQLiteDatabase checkDb = null;
        Log.w(TAG, "Check that db is exist");
        try {
            String path = DBAppData.DB_PATH + DBAppData.DB_NAME;
            checkDb = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
        } 
        catch (SQLException e) { 
            Log.e(TAG, "Error while checking, db not exist", e);
        }
        if (checkDb != null) {
            checkDb.close();
            Log.w(TAG, "Database is exist");
        }
        return checkDb != null;
    }

    public synchronized void close() {
      if (database != null) {
          database.close();
          database = null;
      }
      //super.close();
    }
}