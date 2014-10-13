package com.example.crosswordpuzzles;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {

	String quest,ans;
	private static final String pid = "pid";
	private static final String clue="clue";
	private static final String solution="solution";
	private static final String table="puzzle";
	private static final String table2="word";
	private static final String wid = "wid";
	private static final String col[] = {pid,clue,solution};
	private static final String col1[] = {wid,clue,solution};
	
	public DB(Context context,String name){
		super(context, name, null, 1);
	}

	/*private DB(Context ctx){
		super(ctx,db_name,null,db_version);
		this.ctx=ctx;
	}
	
	public static DB getInstance(Context ctx){
		
		if(instance==null)
		{
			instance = new DB(ctx.getApplicationContext());
		}
		
		return instance;
	}*/
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		String query = "CREATE TABLE puzzle(pid integer primary key autoincrement, clue varchar(700), solution varchar(10));";
		String query1 = "CREATE TABLE word(wid integer primary key autoincrement, clue varchar(100), solution varchar(100));";
		db.execSQL(query);
		db.execSQL(query1);
	}
	
	public void insert(String quest,String ans){
		ContentValues values = new ContentValues();
		values.put("clue", quest);
		values.put("solution", ans);
		
		SQLiteDatabase db = this.getWritableDatabase();
		db.insert("puzzle", null, values);
		db.close();
	}
	
	public void insertword(String quest,String ans){
		ContentValues values = new ContentValues();
		values.put("clue", quest);
		values.put("solution", ans);
		
		SQLiteDatabase db = this.getWritableDatabase();
		db.insert("word", null, values);
		db.close();
	}
	
	public int getquest(int pid){
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.query(table,col," pid=?", new String[] {String.valueOf(pid)},null,null,null,null);
		if(c!=null)
			c.moveToFirst();
		int qid = c.getInt(0);
		return qid;
	}
	
	public String read(int pid){
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.query(table,col," pid=?", new String[] {String.valueOf(pid)},null,null,null,null);
		if(c!=null)
			c.moveToFirst();
		String ans = c.getString(1);
		return ans;
			
	}
	
	public String readans(int pid){
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.query(table,col," pid=?", new String[] {String.valueOf(pid)},null,null,null,null);
		if(c!=null)
			c.moveToFirst();
		String ans = c.getString(2);
		return ans;		
	}
	
	public int getword(int wid){
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.query(table2,col1," wid=?", new String[] {String.valueOf(wid)},null,null,null,null);
		if(c!=null)
			c.moveToFirst();
		int qid = c.getInt(0);
		return qid;
	}

	public String readquest(int wid){
	
	SQLiteDatabase db = this.getReadableDatabase();
	Cursor c = db.query(table2,col1," wid=? AND flag=0", new String[] {String.valueOf(wid)},null,null,null,null);
	if(c!=null)
		c.moveToFirst();
	String ans = c.getString(1);
	return ans;
	}

	public String readsol(int wid){
	
	SQLiteDatabase db = this.getReadableDatabase();
	Cursor c = db.query(table2,col1," wid=?", new String[] {String.valueOf(wid)},null,null,null,null);
	if(c!=null)
		c.moveToFirst();
	String ans = c.getString(2);
	return ans;
	}
	
	/*public void updateflag(int wid){
		
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("flag", 1);
		db.update(table2, cv ," wid=?", new String[] {String.valueOf(wid)});
	}
	
	public void reupdateflag(int wid){
		
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("flag", 0);
		db.update(table2, cv," wid=?", new String[] {String.valueOf(wid)});
	}
	
	public boolean returnflag(){
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery("SELECT * FROM "+table2+" WHERE flag=0", null);
		if(c!=null)
			return true;
		else
		return false;
	}*/

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
}
