package com.example;

import java.util.ArrayList;
import java.util.List;

 
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
 
public class DBHelper extends SQLiteOpenHelper {
 	
 public DBHelper(Context context, String name, CursorFactory factory, int version) {
  super(context, name, factory, version);
 }
 
 @Override
 public void onCreate(SQLiteDatabase db) {
  String createShopTable = "CREATE TABLE " + AppData.TABLE_NAME + " (" + AppData.COLUMN_ID +
    " INTEGER PRIMARY KEY AUTOINCREMENT, " + AppData.COLUMN_TITLE + " TEXT NOT NULL, " +
    AppData.COLUMN_PRICE + " DOUBLE NOT NULL)";
  db.execSQL(createShopTable);
 }
 
 @Override
 public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
  if(AppData.DATABASE_VERSION < newVersion) {
   db.execSQL("DROP TABLE IF EXISTS " + AppData.TABLE_NAME);
   onCreate(db);
  }
 }
 
 public List<Shop> getAllShop() {
  List<Shop> shopList = new ArrayList<Shop>();
  String query = "SELECT * FROM " + AppData.TABLE_NAME;
  SQLiteDatabase db = this.getReadableDatabase();
  Cursor cursor = db.rawQuery(query, null);
  if(cursor.moveToFirst()) {
   do {
    Shop shop = new Shop();
    shop.setId(Integer.parseInt(cursor.getString(0)));
    shop.setTitle(cursor.getString(1));
    shop.setPrice(Double.parseDouble(cursor.getString(2)));
    shopList.add(shop);
   } while(cursor.moveToNext());
  }
  return shopList;
 }
 
 public void addShop(Shop shop) {
  SQLiteDatabase db = this.getWritableDatabase();
  ContentValues cv = new ContentValues();
  cv.put(AppData.COLUMN_TITLE, shop.getTitle());
  cv.put(AppData.COLUMN_PRICE, shop.getPrice());
  db.insert(AppData.TABLE_NAME, null, cv);
  db.close();
 }
 
 public Shop getShop(int id) {
  SQLiteDatabase db = this.getReadableDatabase();
  Cursor cursor = db.query(AppData.TABLE_NAME, new String[] { AppData.COLUMN_ID, AppData.COLUMN_TITLE, AppData.COLUMN_PRICE },
    AppData.COLUMN_ID + "=?", new String[] { String.valueOf(id) }, null, null, null);
  if(cursor != null)
   cursor.moveToFirst();
  Shop shop = new Shop(Integer.parseInt(cursor.getString(0)), cursor.getString(1), Double.parseDouble(cursor.getString(2)));
  return shop;
 }
 
 public int updateShop(Shop shop) {
  SQLiteDatabase db = this.getWritableDatabase();
  ContentValues cv = new ContentValues();
  cv.put(AppData.COLUMN_TITLE, shop.getTitle());
  cv.put(AppData.COLUMN_PRICE, shop.getPrice());
  return db.update(AppData.TABLE_NAME, cv, AppData.COLUMN_ID + "=?", new String[] { String.valueOf(shop.getId()) });
 }
 
 public int deleteShop(Shop shop) {
  SQLiteDatabase db = this.getWritableDatabase();
  return db.delete(AppData.TABLE_NAME, AppData.COLUMN_ID + "=?", new String[] { String.valueOf(shop.getId()) });
 }
 
 public int getShopCount() {
  String query = "SELECT * FROM " + AppData.TABLE_NAME;
  SQLiteDatabase db = this.getReadableDatabase();
  Cursor cursor = db.rawQuery(query, null);
  cursor.close();
  return cursor.getCount();
 }
 
 public Cursor getAllCursor() {
  SQLiteDatabase db = this.getReadableDatabase();
  return db.query(AppData.TABLE_NAME, null, null, null, null, null, null);
 }
 
}