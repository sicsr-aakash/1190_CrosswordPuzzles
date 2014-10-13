package com.example.crosswordpuzzles;

import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper{

	private static MySQLiteHelper instance = null;
	private static final int db_version=1;
	private static final String db_name="Crossword.db";
	
	private static final String TABLE_CROSSWORD1 = "crossword_main";
	private static final String TABLE_CROSSWORD2 = "crossword_two";
	private static final String TABLE_CROSSWORD3 = "crossword_three";
	private static final String TABLE_CROSSWORD4 = "saved_games";
	private static final String TABLE_CROSSWORD5 = "player_profile";
	private static final String TABLE_PUZZLE1 = "word_games";
	private static final String TABLE_PUZZLE2 = "number_games";
	
	private static final String crossword_no = "crossword_no";
	
	private static final String diff_level = "diff_level";
	private static final String catg = "category";
	private static final String lang = "lang";
	private static final String tot_words = "tot_words";
	private static final String no_hints = "no_hints";
	private static final String status = "status";
	
	private static final String clue_no = "clue_no";
	private static final String answer = "answer";
	private static final String start_pos = "start_pos";
	private static final String clue = "clue";
	private static final String clue_type = "clue_type";
	private static final String hint = "hint";
	
	private static final String letter = "letter";
	private static final String pos = "pos";
	
	private static final String saved_game_no = "savedgame_no";
	private static final String saved_game_name = "savedgame_name";
	
	private static final String player_name = "player_name";
	private static final String player_score = "player_score";
	
	private static final String pid = "puz_id";
	private static final String solution="word_solution";
	private static final String solution1="num_solution";
	private static final String clue2 = "num_clue";
	private static final String clue3 = "word_clue";
	
	private static final String wid = "word_id";
	
	private static final String[] COLUMNS1 = {crossword_no,diff_level,catg,lang,tot_words,no_hints,status};
	private static final String[] COLUMNS2 = {crossword_no,answer,start_pos,clue_no,clue,clue_type,hint};
	private static final String[] COLUMNS3 = {crossword_no,letter,clue_no,pos};
	private static final String[] COLUMNS4 = {saved_game_no,saved_game_name};
	private static final String[] COLUMNS5 = {player_name,player_score};
	private static final String col[] = {pid,clue2,solution1};
	private static final String col1[] = {wid,clue3,solution};
	
	
	private Context ctx;
	int id,flag,flag_val;
	String sav_gm;
	
	public static MySQLiteHelper getInstance(Context ctx){
		
		if(instance==null)
		{
			instance = new MySQLiteHelper(ctx.getApplicationContext());
		}
		
		return instance;
	}
	
	private MySQLiteHelper(Context ctx){
		super(ctx,db_name,null,db_version);
		this.ctx=ctx;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db){
		
		String create_crossword_table1 = "CREATE TABLE IF NOT EXISTS crossword_main (" +
				"crossword_no INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"diff_level TEXT, " +
				"category TEXT, " +
				"lang TEXT, " +
				"tot_words INTEGER, " +
				"no_hints INTEGER, " +
				"status TEXT )";
		
		String create_crossword_table2 = "CREATE TABLE IF NOT EXISTS crossword_two (" +
				"crossword_no INTEGER, " + 
				"answer TEXT, " +
				"start_pos TEXT, " +
				"clue_no INTEGER PRIMARY KEY, " +
				"clue TEXT, " +
				"clue_type TEXT, " +
				"hint TEXT, " +
				"CONSTRAINT fk_crossno FOREIGN KEY(crossword_no) REFERENCES crossword_main(crossword_no) )";
		
		String create_crossword_table3 = "CREATE TABLE IF NOT EXISTS crossword_three ( " + 
				"crossword_no INTEGER, " +
				"letter TEXT, " +
				"clue_no INTEGER, " +
				"pos TEXT, " +
				"CONSTRAINT fk_clueno FOREIGN KEY(clue_no) REFERENCES crossword_table2(clue_no), " +
				"CONSTRAINT fk_crossnum FOREIGN KEY(crossword_no) REFERENCES crossword_main(crossword_no) )";
		
		String create_crossword_table4 = "CREATE TABLE IF NOT EXISTS saved_games (" +
				"savedgame_no INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"savedgame_name TEXT )";
				
		String create_crossword_table5 = "CREATE TABLE IF NOT EXISTS player_profile (" +
				"player_name TEXT, " +
				"player_score INTEGER )";
		
		String create_puzzle_table1 = "CREATE TABLE IF NOT EXISTS word_games (" +
				"word_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"word_clue TEXT, " +
				"word_solution TEXT )";
		
		String create_puzzle_table2 = "CREATE TABLE IF NOT EXISTS number_games (" +
				"puz_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"num_clue TEXT, " +
				"num_solution TEXT )";
		
		db.execSQL(create_crossword_table1);
		db.execSQL(create_crossword_table2);
		db.execSQL(create_crossword_table3);
		db.execSQL(create_crossword_table4);
		db.execSQL(create_crossword_table5);
		
		db.execSQL(create_puzzle_table1);
		db.execSQL(create_puzzle_table2);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
		db.execSQL("DROP TABLE IF EXISTS crossword_main");
		db.execSQL("DROP TABLE IF EXISTS crossword_two");
		db.execSQL("DROP TABLE IF EXISTS crossword_three");
		db.execSQL("DROP TABLE IF EXISTS saved_games");
		db.execSQL("DROP TABLE IF EXISTS player_profile");
		db.execSQL("DROP TABLE IF EXISTS word_games");
		db.execSQL("DROP TABLE IF EXISTS number_games");
		
		this.onCreate(db);
	}
	
	public void addRecords(String diff_level,String catg,String lang,int tot_words,int no_hints,String status){
		
		SQLiteDatabase db=this.getWritableDatabase();
		
		ContentValues values=new ContentValues();
		
		values.put("diff_level", diff_level);
		values.put("category",catg);
		values.put("lang", lang);
		values.put("tot_words", tot_words);
		values.put("no_hints", no_hints);
		values.put("status", status);
		
		db.insert(TABLE_CROSSWORD1, null, values);
		
		db.close();
	}

	public void addRecs(int crossword_no,String answer,String start_pos,int clue_no,String clue,String clue_type,String hint){
		
		SQLiteDatabase db=this.getWritableDatabase();
		
		ContentValues values=new ContentValues();
		
		values.put("crossword_no", crossword_no);
		values.put("answer", answer);
		values.put("start_pos",start_pos);
		values.put("clue_no", clue_no);
		values.put("clue", clue);
		values.put("clue_type", clue_type);
		values.put("hint", hint);
		
		db.insert(TABLE_CROSSWORD2, null, values);
		
		db.close();
	}

	public void addRec(int crossword_no,String letter,int clue_no,String pos){
		
		SQLiteDatabase db=this.getWritableDatabase();
		
		ContentValues values=new ContentValues();
		
		values.put("crossword_no", crossword_no);
		values.put("letter",letter);
		values.put("clue_no", clue_no);
		values.put("pos", pos);
		
		db.insert(TABLE_CROSSWORD3, null, values);
		
		db.close();
	}
	
	public void addPuzRec(String clue,String soln){
		
		SQLiteDatabase db=this.getWritableDatabase();
		
		ContentValues values=new ContentValues();
		
		values.put("word_clue", clue);
		values.put("word_solution", soln);
		
		db.insert(TABLE_PUZZLE1, null, values);
		
		db.close();
		
	}
	
	public void addPuzRec1(String clue,String soln){
		
		SQLiteDatabase db=this.getWritableDatabase();
		
		ContentValues values=new ContentValues();
		
		values.put("num_clue", clue);
		values.put("num_solution", soln);
		
		db.insert(TABLE_PUZZLE2, null, values);
		
		db.close();
		
	}
	
	public void addSavedgame(String savedgame_nm){
		
		SQLiteDatabase db=this.getWritableDatabase();
		
		ContentValues values=new ContentValues();
		
		values.put("savedgame_name", savedgame_nm);
		
		db.insert(TABLE_CROSSWORD4, null, values);
		
		db.close();
	}
	
	public void addPlayer(String player_nm,int player_sc){
		
		SQLiteDatabase db=this.getWritableDatabase();
		
		ContentValues values=new ContentValues();
		
		values.put("player_name", player_nm);
		values.put("player_score", player_sc);
		
		db.insert(TABLE_CROSSWORD5, null, values);
		
		db.close();
	}
	
	
	public String getLet(int crossword_no,int clue_no,String pos){
		
		SQLiteDatabase db=this.getReadableDatabase();
		
		Cursor cursor=
				db.query(TABLE_CROSSWORD3, 
				COLUMNS3, 
				" clue_no = ? and crossword_no = ? and pos = ?", 
				new String[] { String.valueOf(clue_no), String.valueOf(crossword_no), String.valueOf(pos) },
				null, 
				null,
				null);
		
		if(cursor!=null)
			cursor.moveToFirst();
		
		int id = cursor.getColumnIndex("letter");
		String letter = cursor.getString(id);
		
		return letter;
	}

	public String getAns(int crossword_no,int clue_no){
		
		SQLiteDatabase db=this.getReadableDatabase();
		
		Cursor cursor=
				db.query(TABLE_CROSSWORD2, 
				COLUMNS2, 
				" clue_no = ? and crossword_no = ?", 
				new String[] { String.valueOf(clue_no), String.valueOf(crossword_no) },
				null, 
				null,
				null);
		
		if(cursor!=null)
			cursor.moveToFirst();
		
		int id = cursor.getColumnIndex("answer");
		String answer = cursor.getString(id);
		
		return answer;
	}
	
	public String getHint(int crossword_no,int clue_no){
	
		SQLiteDatabase db=this.getReadableDatabase();
		
		Cursor cursor=
				db.query(TABLE_CROSSWORD2, 
				COLUMNS2, 
				" clue_no = ? and crossword_no = ?", 
				new String[] { String.valueOf(clue_no), String.valueOf(crossword_no) },
				null, 
				null,
				null);
		
		if(cursor!=null)
			cursor.moveToFirst();
		
		int id = cursor.getColumnIndex("hint");
		String hint = cursor.getString(id);
		
		return hint;
	}
	
	public int getNohints(int crossword_no){
		
		SQLiteDatabase db=this.getReadableDatabase();
		
		Cursor cursor=
				db.query(TABLE_CROSSWORD1, 
				COLUMNS1, 
				"crossword_no = ?", 
				new String[] { String.valueOf(crossword_no) },
				null, 
				null,
				null);
		
		if(cursor!=null)
			cursor.moveToFirst();
		
		int id = cursor.getColumnIndex("no_hints");
		int no_hints = cursor.getInt(id);
		
		return no_hints;
	}
	
	public int getTotWords(int crossword_no){
		
		SQLiteDatabase db=this.getReadableDatabase();
		
		Cursor cursor=
				db.query(TABLE_CROSSWORD1, 
				COLUMNS1, 
				"crossword_no = ?", 
				new String[] { String.valueOf(crossword_no) },
				null, 
				null,
				null);
		
		if(cursor!=null)
			cursor.moveToFirst();
		
		int id = cursor.getColumnIndex("tot_words");
		int tot_words = cursor.getInt(id);
		
		return tot_words;
	}
	
	
	public Cursor getSavedGames() throws Exception
	{
		SQLiteDatabase db=this.getReadableDatabase();
		
		Cursor c = db.rawQuery("select savedgame_name from saved_games",null);
		return c;
	}
	
	public Cursor getPlayer() throws Exception{
		
		SQLiteDatabase db=this.getReadableDatabase();
		
		Cursor c = db.rawQuery("select player_name,player_score from player_profile",null);
		return c;
	}
	
	public int updateScore(int score,String play_name){
		
		SQLiteDatabase db=this.getWritableDatabase();
		
		ContentValues values=new ContentValues();
		
		values.put("player_score", score);
		
		int i = db.update(TABLE_CROSSWORD5, values, "player_name=?", new String[] { String.valueOf(play_name) });
		
		db.close();
		
		return i;
	}
	
	public int updateName(String play_name,int play_score){
		
		SQLiteDatabase db=this.getWritableDatabase();
		
		ContentValues values=new ContentValues();
		
		values.put("player_name", play_name);
		
		int i = db.update(TABLE_CROSSWORD5, values, "player_score=?", new String[] { String.valueOf(play_score) });
		
		db.close();
		
		return i;
	}
	
	
	public int checkRewrite(String svg_name) throws Exception
	{
		SQLiteDatabase db=this.getReadableDatabase();
		
		Cursor c = db.rawQuery("select savedgame_name from saved_games",null);
		
		if((c!=null) && (c.getCount() > 0)){
			
			if(c.moveToFirst()){
			
				do{
					
					id = c.getColumnIndex("savedgame_name");
					sav_gm = c.getString(id);
					
					if(svg_name.toString().equals(sav_gm.toString())){
						
						flag = 1;
						break;
					}
					else{
						
						flag = 0;
					}
					
				}while(c.moveToNext());
			}
		}
		
		return flag;
	}
	
	public int deleteSavedGame(String svg_name) throws Exception
	{
		SQLiteDatabase db=this.getReadableDatabase();
		
		Cursor c = db.rawQuery("select savedgame_name from saved_games",null);
		
		if((c!=null) && (c.getCount() > 0)){
			
			if(c.moveToFirst()){
			
				do{
					
					id = c.getColumnIndex("savedgame_name");
					sav_gm = c.getString(id);
					
					if(svg_name.toString().equals(sav_gm.toString())){
						
						SQLiteDatabase dbnew=this.getWritableDatabase();
						flag_val = dbnew.delete(TABLE_CROSSWORD4, saved_game_name + "=?", new String[] { svg_name });
						//dbnew.u
						break;
					}
					else{
						
						flag_val = 0;
					}
				}while(c.moveToNext());
			}
		}
		
		return flag_val;
	}
	
	
	/*public List<Crossword> getRec(){
		
		List<Crossword> res = new LinkedList<Crossword>();
		
		String query="SELECT * FROM " + TABLE_CROSSWORD3;
		
		SQLiteDatabase db=this.getWritableDatabase();
		Cursor cursor=db.rawQuery(query, null);
		
		Crossword cr=null;
		
		if(cursor.moveToFirst()){
			do{
				cr=new Crossword();
				cr.setNo(Integer.parseInt(cursor.getString(0)));
				cr.setLetter(cursor.getString(1));
				cr.setPos(cursor.getString(2));
				
				res.add(cr);
			}while(cursor.moveToNext());
		}
		
		return res;
	}*/
	
	public int getquest(int pid){
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.query(TABLE_PUZZLE2,col," puz_id=?", new String[] {String.valueOf(pid)},null,null,null,null);
		if(c!=null)
			c.moveToFirst();
		int qid = c.getInt(0);
		return qid;
	}
	
	public String read(int pid){
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.query(TABLE_PUZZLE2,col," puz_id=?", new String[] {String.valueOf(pid)},null,null,null,null);
		if(c!=null)
			c.moveToFirst();
		String ans = c.getString(1);
		return ans;
			
	}
	
	public String readans(int pid){
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.query(TABLE_PUZZLE2,col," puz_id=?", new String[] {String.valueOf(pid)},null,null,null,null);
		if(c!=null)
			c.moveToFirst();
		String ans = c.getString(2);
		return ans;		
	}
	
	public int getword(int wid){
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.query(TABLE_PUZZLE1,col1," word_id=?", new String[] {String.valueOf(wid)},null,null,null,null);
		if(c!=null)
			c.moveToFirst();
		int qid = c.getInt(0);
		return qid;
	}

	public String readquest(int wid){
	
	SQLiteDatabase db = this.getReadableDatabase();
	Cursor c = db.query(TABLE_PUZZLE1,col1," word_id=?", new String[] {String.valueOf(wid)},null,null,null,null);
	if(c!=null)
		c.moveToFirst();
	String ans = c.getString(1);
	return ans;
	}

	public String readsol(int wid){
	
	SQLiteDatabase db = this.getReadableDatabase();
	Cursor c = db.query(TABLE_PUZZLE1,col1," word_id=?", new String[] {String.valueOf(wid)},null,null,null,null);
	if(c!=null)
		c.moveToFirst();
	String ans = c.getString(2);
	return ans;
	}
}
