package com.example.crosswordpuzzles;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Crossui extends ActionBarActivity {

	EditText e1,e2,e3,e4,e5,e6,e7,e8,e9,e10,e11,e12,e13,e14,e15,e16,e17,e18,e19,e20;
	
	String eval,let,ans="",answer,hint,letter;
	MySQLiteHelper mh;
	int cnt = 0,ncnt = 0,flag = 0,f = 0,fl = 0,flag_value,flag_val;
	public int points = 0,temp,counter,cntr,no_hints;
	public int counter1 = 0,i=0;
	int score;
	String player_name;
	//private boolean isDisabled = false;
	
	public static String myPreferences = "";
	
	private static final String diff_level_1 = "Beginner";
	private static final String diff_level_2 = "Professional";
	private static final String diff_level_3 = "Elite";
	
	private static final String catg_1 = "General";
	private static final String catg_2 = "Computers and Technology";
	private static final String catg_3 = "Sports";
	
	private static final String lang_1 = "English";
	private static final String lang_2 = "German";
	
	public static final String a1 = "e1";
	public static final String a1_1 = "e2";
	public static final String a1_2 = "e3";
	public static final String d1_1 = "e4";
	public static final String d1_2 = "e5";
	public static final String d1_3 = "e6";
	public static final String d1_4 = "e7";
	public static final String a2 = "e8";
	public static final String a2_1 = "e9";
	public static final String a2_2 = "e10";
	public static final String a2_3 = "e11";
	public static final String d2 = "e12";
	public static final String d2_1 = "e13";
	public static final String d3 = "e14";
	public static final String d3_1 = "e15";
	public static final String d3_2 = "e16";
	public static final String d4 = "e17";
	public static final String d4_1 = "e18";
	public static final String a3 = "e19";
	public static final String a4 = "e20";
	
	public static final String pts = "points";
	
	
	SharedPreferences sharedPref, newsharedPref, delPref;
	
	final CharSequence[] items = {" 1 Across "," 1 Down "," 2 Across "," 2 Down "," 3 Down "," 4 Down "," 5 Across "," 6 Across "};

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_crossui);
		
		e1=(EditText)findViewById(R.id.a1);
		e2=(EditText)findViewById(R.id.a1_1);
		e3=(EditText)findViewById(R.id.a1_2);
		e4=(EditText)findViewById(R.id.d1_1);
		e5=(EditText)findViewById(R.id.d1_2);
		e6=(EditText)findViewById(R.id.d1_3);
		e7=(EditText)findViewById(R.id.d1_4);
		e8=(EditText)findViewById(R.id.a2);
		e9=(EditText)findViewById(R.id.a2_1);
		e10=(EditText)findViewById(R.id.a2_2);
		e11=(EditText)findViewById(R.id.a2_3);
		e12=(EditText)findViewById(R.id.d2);
		e13=(EditText)findViewById(R.id.d2_1);
		e14=(EditText)findViewById(R.id.d3);
		e15=(EditText)findViewById(R.id.d3_1);
		e16=(EditText)findViewById(R.id.d3_2);
		e17=(EditText)findViewById(R.id.d4);
		e18=(EditText)findViewById(R.id.d4_1);
		e19=(EditText)findViewById(R.id.a3);
		e20=(EditText)findViewById(R.id.a4);
		
		
		e1.setTag(e1.getId());
		e2.setTag(e2.getId());
		e3.setTag(e3.getId());
		e4.setTag(e4.getId());
		e5.setTag(e5.getId());
		e6.setTag(e6.getId());
		e7.setTag(e7.getId());
		e8.setTag(e8.getId());
		e9.setTag(e9.getId());
		e10.setTag(e10.getId());
		e11.setTag(e11.getId());
		e12.setTag(e12.getId());
		e13.setTag(e13.getId());
		e14.setTag(e14.getId());
		e15.setTag(e15.getId());
		e16.setTag(e16.getId());
		e17.setTag(e17.getId());
		e18.setTag(e18.getId());
		e19.setTag(e19.getId());
		e20.setTag(e20.getId());
		
		
		mh = MySQLiteHelper.getInstance(getApplicationContext());
		
		/*mh.addRecords(diff_level_1,catg_1, lang_1, 8, 2, "Incomplete");
		
		mh.addPlayer("Player", 0);
		
		mh.addRecs(1,"SOS",e1.getTag().toString(),1,"Distress Letters","Across","Morse code distress signal");
		
		mh.addRec(1, "S", 1, e1.getTag().toString());
		mh.addRec(1, "O", 1, e2.getTag().toString());
		mh.addRec(1, "S", 1, e3.getTag().toString());
		
		
		mh.addRecs(1,"SAGAS",e1.getTag().toString(),2,"Legendary Stories","Down","A long involved story, account or series of incidents");
		
		mh.addRec(1,"A",2,e4.getTag().toString());
		mh.addRec(1,"G",2,e5.getTag().toString());
		mh.addRec(1,"A",2,e6.getTag().toString());
		mh.addRec(1,"S",2,e7.getTag().toString());
		
		
		mh.addRecs(1,"SANTA",e8.getTag().toString(),3,"Yule Visitor","Across","Christmas");
		
		mh.addRec(1,"S",3,e8.getTag().toString());
		mh.addRec(1,"N",3,e9.getTag().toString());
		mh.addRec(1,"T",3,e10.getTag().toString());
		mh.addRec(1,"A",3,e11.getTag().toString());
		
		
		mh.addRecs(1,"SAT",e8.getTag().toString(),4,"Took a chair","Down","Past and past participle of sit");
		
		mh.addRec(1,"A",4,e12.getTag().toString());
		mh.addRec(1,"T",4,e13.getTag().toString());
		
		
		mh.addRecs(1,"STRIP",e3.getTag().toString(),5,"Remove all coverings from","Down","Leave bare of accessories or fittings");
		
		mh.addRec(1,"R",5,e14.getTag().toString());
		mh.addRec(1,"I",5,e15.getTag().toString());
		mh.addRec(1,"P",5,e16.getTag().toString());
		
		
		mh.addRecs(1,"ASS",e11.getTag().toString(),6,"Braying Beast","Down","A hoofed mammal of the horse family, which is typically smaller than a horse and has longer ears and a braying call");
		
		mh.addRec(1,"S",6,e17.getTag().toString());
		mh.addRec(1,"S",6,e18.getTag().toString());
		
		
		mh.addRecs(1,"TAXIS",e13.getTag().toString(),7,"Restoration of displaced bones or organs by manual pressure alone","Across","A motion or orientation of a cell, organism or part in response to an external stimulus");
		
		mh.addRec(1,"X",7,e19.getTag().toString());
		
		
		mh.addRecs(1,"SAP",e7.getTag().toString(),8,"Fluid which circulates in the vascular system of a plant","Across","Gradually weaken or destroy");
		
		mh.addRec(1,"A",8,e20.getTag().toString());
		
		
		mh.addPuzRec("errorcde", "recorder");
		mh.addPuzRec("outerpmc", "computer");
		mh.addPuzRec("badteasa", "database");
		mh.addPuzRec("zzlepu", "puzzle");
		mh.addPuzRec("gunmass", "samsung");
		mh.addPuzRec("ceheps", "speech");
		mh.addPuzRec("nenoctc", "connect");
		mh.addPuzRec("utedhcs", "deutsch");
		mh.addPuzRec("yticaitv", "activity");
		mh.addPuzRec("roofy", "froyo");
		
		mh.addPuzRec1("sqrt(225) = ?", "15");
		mh.addPuzRec1("10*8 = ?", "80");
		mh.addPuzRec1("Find the number in series: 1,4,9,16,25,?", "36");
		mh.addPuzRec1("Find the number in series: 2,7,14,23,?,47", "34");
		mh.addPuzRec1("Find the number in series: 4,9,13,22,35,?", "60");
		mh.addPuzRec1("Find the number in series: 10,14,26,42,70,?", "114");
		mh.addPuzRec1("If 30% of a number is 12.6, find the number", "42");
		mh.addPuzRec1("What number must be added to 6,16 and 8 to get an average of 13?", "22");
		mh.addPuzRec1("Find the area of a right-angled triangle whose hypotenuse is 10cm and base is 8cm", "24");
		mh.addPuzRec1("5^2 = ?", "25"); */
		
		
		Bundle bundle = getIntent().getExtras();
		
		if(bundle != null){
			
			String saved_game = bundle.getString("svgm");
			//Toast.makeText(getApplicationContext(), saved_game.toString(), Toast.LENGTH_LONG).show();
			int flag1 = bundle.getInt("flag");
			//Toast.makeText(getApplicationContext(), flag1, Toast.LENGTH_LONG).show();
		
		
			if(!(saved_game.toString().equals("")) && (flag1 != 0)){
			
				newsharedPref = getApplicationContext().getSharedPreferences(saved_game,Context.MODE_PRIVATE);

				if(newsharedPref.contains(a1)){
				
					e1.setText(newsharedPref.getString(a1,""));
				}
			
				if(newsharedPref.contains(a1_1)){
				
					e2.setText(newsharedPref.getString(a1_1,""));
				}
			
				if(newsharedPref.contains(a1_2)){
				
					e3.setText(newsharedPref.getString(a1_2,""));
				}
			
				if(newsharedPref.contains(d1_1)){
				
					e4.setText(newsharedPref.getString(d1_1,""));
				}
			
				if(newsharedPref.contains(d1_2)){
				
					e5.setText(newsharedPref.getString(d1_2,""));
				}
			
				if(newsharedPref.contains(d1_3)){
				
					e6.setText(newsharedPref.getString(d1_3,""));
				}
			
				if(newsharedPref.contains(d1_4)){
				
					e7.setText(newsharedPref.getString(d1_4,""));
				}
			
				if(newsharedPref.contains(a2)){
					
					e8.setText(newsharedPref.getString(a2,""));
				}
				
				if(newsharedPref.contains(a2_1)){
					
					e9.setText(newsharedPref.getString(a2_1,""));
				}

				if(newsharedPref.contains(a2_2)){
					
					e10.setText(newsharedPref.getString(a2_2,""));
				}
				
				if(newsharedPref.contains(a2_3)){
	
					e11.setText(newsharedPref.getString(a2_3,""));
				}

				if(newsharedPref.contains(d2)){
					
					e12.setText(newsharedPref.getString(d2,""));
				}
				
				if(newsharedPref.contains(d2_1)){
					
					e13.setText(newsharedPref.getString(d2_1,""));
				}
				
				if(newsharedPref.contains(d3)){
					
					e14.setText(newsharedPref.getString(d3,""));
				}
				
				if(newsharedPref.contains(d3_1)){
					
					e15.setText(newsharedPref.getString(d3_1,""));
				}
				
				if(newsharedPref.contains(d3_2)){
					
					e16.setText(newsharedPref.getString(d3_2,""));
				}
				
				if(newsharedPref.contains(d4)){
					
					e17.setText(newsharedPref.getString(d4,""));
				}
				
				if(newsharedPref.contains(d4_1)){
					
					e18.setText(newsharedPref.getString(d4_1,""));
				}
				
				if(newsharedPref.contains(a3)){
					
					e19.setText(newsharedPref.getString(a3,""));
				}
				
				if(newsharedPref.contains(a4)){
					
					e20.setText(newsharedPref.getString(a4,""));
				}
				
				if(newsharedPref.contains(pts)){
				
					int p = newsharedPref.getInt(pts,0);
					points = p;
				}
				
				saved_game="";
				flag1 = 0;
			}
		
		}
		
		
		e1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				
				letter = e1.getText().toString();
				e1.setText(letter.toString().toUpperCase());
			}
		});
	
		e2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				
				letter = e2.getText().toString();
				e2.setText(letter.toString().toUpperCase());
			}
		});
		
		e3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				
				letter = e3.getText().toString();
				e3.setText(letter.toString().toUpperCase());
			}
		});
		
		e4.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				
				letter = e4.getText().toString();
				e4.setText(letter.toString().toUpperCase());
			}
		});
		
		e5.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				
				letter = e5.getText().toString();
				e5.setText(letter.toString().toUpperCase());
			}
		});
		
		e6.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				
				letter = e6.getText().toString();
				e6.setText(letter.toString().toUpperCase());
			}
		});
		
		e7.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				
				letter = e7.getText().toString();
				e7.setText(letter.toString().toUpperCase());
			}
		});
		
		e8.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				
				letter = e8.getText().toString();
				e8.setText(letter.toString().toUpperCase());
			}
		});
		
		e9.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				
				letter = e9.getText().toString();
				e9.setText(letter.toString().toUpperCase());
			}
		});
		
		e10.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				
				letter = e10.getText().toString();
				e10.setText(letter.toString().toUpperCase());
			}
		});
		
		e11.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				
				letter = e11.getText().toString();
				e11.setText(letter.toString().toUpperCase());
			}
		});
		
		e12.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				
				letter = e12.getText().toString();
				e12.setText(letter.toString().toUpperCase());
			}
		});
		
		e13.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				
				letter = e13.getText().toString();
				e13.setText(letter.toString().toUpperCase());
			}
		});
		
		e14.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				
				letter = e14.getText().toString();
				e14.setText(letter.toString().toUpperCase());
			}
		});
		
		e15.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				
				letter = e15.getText().toString();
				e15.setText(letter.toString().toUpperCase());
			}
		});
		
		e16.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				
				letter = e16.getText().toString();
				e16.setText(letter.toString().toUpperCase());
			}
		});
		
		e17.setOnFocusChangeListener(new View.OnFocusChangeListener() {
	
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				
				letter = e17.getText().toString();
				e17.setText(letter.toString().toUpperCase());
			}
		});
		
		e18.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				
				letter = e18.getText().toString();
				e18.setText(letter.toString().toUpperCase());
			}
		});
		
		e19.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				
				letter = e19.getText().toString();
				e19.setText(letter.toString().toUpperCase());
			}
		});
		
		e20.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				
				letter = e20.getText().toString();
				e20.setText(letter.toString().toUpperCase());
			}
		});
		
		
		
		e1.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
			
				eval="1";
				return false;
			}
		});

		e2.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {

				eval="2";
				return false;
			}
		});
		
		e3.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {

				eval="3";
				return false;
			}
		});
		
		e4.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				eval="4";
				return false;
			}
		});
		
		e5.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				eval="5";
				return false;
			}
		});
		
		e6.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				eval="6";
				return false;
			}
		});
		
		e7.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				eval="7";
				return false;
			}
		});
		
		e8.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				eval="8";
				return false;
			}
		});
		
		e9.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				eval="9";
				return false;
			}
		});
		
		e10.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				eval="10";
				return false;
			}
		});
		
		e11.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				eval="11";
				return false;
			}
		});
		
		e12.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				eval="12";
				return false;
			}
		});
		
		e13.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				eval="13";
				return false;
			}
		});
		
		e14.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				eval="14";
				return false;
			}
		});
		
		e15.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				eval="15";
				return false;
			}
		});
		
		e16.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				eval="16";
				return false;
			}
		});
		
		e17.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				eval="17";
				return false;
			}
		});
		
		e18.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				eval="18";
				return false;
			}
		});
		
		e19.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				eval="19";
				return false;
			}
		});
		
		e20.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				eval="20";
				return false;
			}
		});
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    
		getMenuInflater().inflate(R.menu.crossui, menu);
		//getMenuInflater().inflate(R.menu.play_cross, menu);
		
	    return true;
	}

	
	public boolean onOptionsItemSelected(MenuItem item){
		
		mh = MySQLiteHelper.getInstance(getApplicationContext());
		
		switch(item.getItemId()){
			case R.id.reveal_letter:
				
				if(eval=="1"){
					
					points = points - 1;
					
					Toast.makeText(getApplicationContext(), "Score: " + points , Toast.LENGTH_LONG).show();
					
					let = mh.getLet(1,1,e1.getTag().toString());
					e1.setText(let.toString());
				}
				else if(eval=="2"){
					
					points = points - 1;
					
					Toast.makeText(getApplicationContext(), "Score: " + points , Toast.LENGTH_LONG).show();
					
					let = mh.getLet(1,1,e2.getTag().toString());
					e2.setText(let.toString());
				}
				else if(eval=="3"){
					
					points = points - 1;
					
					Toast.makeText(getApplicationContext(), "Score: " + points , Toast.LENGTH_LONG).show();
					
					let = mh.getLet(1,1,e3.getTag().toString());
					e3.setText(let.toString());
				}
				else if(eval=="4"){
					
					points = points - 1;
					
					Toast.makeText(getApplicationContext(), "Score: " + points , Toast.LENGTH_LONG).show();
					
					let = mh.getLet(1,2,e4.getTag().toString());
					e4.setText(let.toString());
				}
				else if(eval=="5"){
					
					points = points - 1;
					
					Toast.makeText(getApplicationContext(), "Score: " + points , Toast.LENGTH_LONG).show();
					
					let = mh.getLet(1,2,e5.getTag().toString());
					e5.setText(let.toString());
				}
				else if(eval=="6"){
					
					points = points - 1;
					
					Toast.makeText(getApplicationContext(), "Score: " + points , Toast.LENGTH_LONG).show();
					
					let = mh.getLet(1,2,e6.getTag().toString());
					e6.setText(let.toString());
				}
				else if(eval=="7"){
					
					points = points - 1;
					
					Toast.makeText(getApplicationContext(), "Score: " + points , Toast.LENGTH_LONG).show();
					
					let = mh.getLet(1,2,e7.getTag().toString());
					e7.setText(let.toString());
				}
				else if(eval=="8"){
					
					points = points - 1;
					
					Toast.makeText(getApplicationContext(), "Score: " + points , Toast.LENGTH_LONG).show();
					
					let = mh.getLet(1,3,e8.getTag().toString());
					e8.setText(let.toString());
				}
				else if(eval=="9"){
					
					points = points - 1;
					
					Toast.makeText(getApplicationContext(), "Score: " + points , Toast.LENGTH_LONG).show();
					
					let = mh.getLet(1,3,e9.getTag().toString());
					e9.setText(let.toString());
				}
				else if(eval=="10"){
					
					points = points - 1;
					
					Toast.makeText(getApplicationContext(), "Score: " + points , Toast.LENGTH_LONG).show();
					
					let = mh.getLet(1,3,e10.getTag().toString());
					e10.setText(let.toString());
				}
				else if(eval=="11"){
					
					points = points - 1;
					
					Toast.makeText(getApplicationContext(), "Score: " + points , Toast.LENGTH_LONG).show();
					
					let = mh.getLet(1,3,e11.getTag().toString());
					e11.setText(let.toString());
				}
				else if(eval=="12"){
					
					points = points - 1;
					
					Toast.makeText(getApplicationContext(), "Score: " + points , Toast.LENGTH_LONG).show();
					
					let = mh.getLet(1,4,e12.getTag().toString());
					e12.setText(let.toString());
				}
				else if(eval=="13"){
					
					points = points - 1;
					
					Toast.makeText(getApplicationContext(), "Score: " + points , Toast.LENGTH_LONG).show();
					
					let = mh.getLet(1,4,e13.getTag().toString());
					e13.setText(let.toString());
				}
				else if(eval=="14"){
					
					points = points - 1;
					
					Toast.makeText(getApplicationContext(), "Score: " + points , Toast.LENGTH_LONG).show();
					
					let = mh.getLet(1,5,e14.getTag().toString());
					e14.setText(let.toString());
				}
				else if(eval=="15"){
					
					points = points - 1;
					
					Toast.makeText(getApplicationContext(), "Score: " + points , Toast.LENGTH_LONG).show();
					
					let = mh.getLet(1,5,e15.getTag().toString());
					e15.setText(let.toString());
				}
				else if(eval=="16"){
					
					points = points - 1;
					
					Toast.makeText(getApplicationContext(), "Score: " + points , Toast.LENGTH_LONG).show();
					
					let = mh.getLet(1,5,e16.getTag().toString());
					e16.setText(let.toString());
				}
				else if(eval=="17"){
					
					points = points - 1;
					
					Toast.makeText(getApplicationContext(), "Score: " + points , Toast.LENGTH_LONG).show();
					
					let = mh.getLet(1,6,e17.getTag().toString());
					e17.setText(let.toString());
				}
				else if(eval=="18"){
					
					points = points - 1;
					
					Toast.makeText(getApplicationContext(), "Score: " + points , Toast.LENGTH_LONG).show();
					
					let = mh.getLet(1,6,e18.getTag().toString());
					e18.setText(let.toString());
				}
				else if(eval=="19"){
					
					points = points - 1;
					
					Toast.makeText(getApplicationContext(), "Score: " + points , Toast.LENGTH_LONG).show();
					
					let = mh.getLet(1,7,e19.getTag().toString());
					e19.setText(let.toString());
				}
				else if(eval=="20"){
					
					points = points - 1;
					
					Toast.makeText(getApplicationContext(), "Score: " + points , Toast.LENGTH_LONG).show();
					
					let = mh.getLet(1,8,e20.getTag().toString());
					e20.setText(let.toString());
				}
				
				return true;
				
			case R.id.reveal_word:
				Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("Select Clue");
				builder.setSingleChoiceItems(items,-1,new DialogInterface.OnClickListener() {
					
					@SuppressWarnings("deprecation")
					@Override
					public void onClick(DialogInterface dialog, int which) {  
				        
						switch(which){
						
						case 0:
							
							points = points - 5;
							
							let = mh.getLet(1,1,e1.getTag().toString());
							e1.setText(let.toString());

							let = mh.getLet(1,1,e2.getTag().toString());
							e2.setText(let.toString());

							let = mh.getLet(1,1,e3.getTag().toString());
							e3.setText(let.toString());
							
						break;
							
						case 1:
							
							points = points - 5;
							
							let = mh.getLet(1,1,e1.getTag().toString());
							e1.setText(let.toString());

							let = mh.getLet(1,2,e4.getTag().toString());
							e4.setText(let.toString());

							let = mh.getLet(1,2,e5.getTag().toString());
							e5.setText(let.toString());

							let = mh.getLet(1,2,e6.getTag().toString());
							e6.setText(let.toString());
							
							let = mh.getLet(1,2,e7.getTag().toString());
							e7.setText(let.toString());
							
						break;
						
						case 2:
							
							points = points - 5;
							
							let = mh.getLet(1,3,e8.getTag().toString());
							e8.setText(let.toString());
							
							let = mh.getLet(1,2,e4.getTag().toString());
							e4.setText(let.toString());
							
							let = mh.getLet(1,3,e9.getTag().toString());
							e9.setText(let.toString());
							
							let = mh.getLet(1,3,e10.getTag().toString());
							e10.setText(let.toString());
							
							let = mh.getLet(1,3,e11.getTag().toString());
							e11.setText(let.toString());
							
						break;
						
						case 3:
						
							points = points - 5;
							
							let = mh.getLet(1,3,e8.getTag().toString());
							e8.setText(let.toString());
							
							let = mh.getLet(1,4,e12.getTag().toString());
							e12.setText(let.toString());
							
							let = mh.getLet(1,4,e13.getTag().toString());
							e13.setText(let.toString());
							
						break;
						
						case 4:
							
							points = points - 5;
							
							let = mh.getLet(1,1,e3.getTag().toString());
							e3.setText(let.toString());
							
							let = mh.getLet(1,3,e10.getTag().toString());
							e10.setText(let.toString());
							
							let = mh.getLet(1,5,e14.getTag().toString());
							e14.setText(let.toString());
							
							let = mh.getLet(1,5,e15.getTag().toString());
							e15.setText(let.toString());
							
							let = mh.getLet(1,5,e16.getTag().toString());
							e16.setText(let.toString());
							
						break;
						
						case 5:
							
							let = mh.getLet(1,3,e11.getTag().toString());
							e11.setText(let.toString());
							
							let = mh.getLet(1,6,e17.getTag().toString());
							e17.setText(let.toString());
							
							let = mh.getLet(1,6,e18.getTag().toString());
							e18.setText(let.toString());
							
						break;
						
						case 6:
							
							let = mh.getLet(1,4,e13.getTag().toString());
							e13.setText(let.toString());
							
							let = mh.getLet(1,2,e6.getTag().toString());
							e6.setText(let.toString());
							
							let = mh.getLet(1,7,e19.getTag().toString());
							e19.setText(let.toString());
							
							let = mh.getLet(1,5,e15.getTag().toString());
							e15.setText(let.toString());
							
							let = mh.getLet(1,6,e18.getTag().toString());
							e18.setText(let.toString());
							
						break;
						
						case 7:
						
							let = mh.getLet(1,2,e7.getTag().toString());
							e7.setText(let.toString());
							
							let = mh.getLet(1,8,e20.getTag().toString());
							e20.setText(let.toString());
							
							let = mh.getLet(1,5,e16.getTag().toString());
							e16.setText(let.toString());
							
						break;
						
						}
						
						dialog.dismiss(); 
					}
				});
				
				builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						dialog.dismiss();
					}
				});
				
				AlertDialog alert = builder.create();  
			    alert.show();
			    break;
			    
			case R.id.check_letter:
				if(eval=="1"){
					
					let = mh.getLet(1,1,e1.getTag().toString());
					
					if(e1.getText().toString().toUpperCase().equals(let.toString())){
							
						Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_LONG).show();
					}
					else{
							
						Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_LONG).show();
					}
				}
				else if(eval=="2"){
					
					let = mh.getLet(1,1,e2.getTag().toString());
					
					if(e2.getText().toString().toUpperCase().equals(let.toString())){
						
						Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_LONG).show();
					}
					else{
							
						Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_LONG).show();
					}
				}
				else if(eval=="3"){
					
					let = mh.getLet(1,1,e3.getTag().toString());
					
					if(e3.getText().toString().toUpperCase().equals(let.toString())){
						
						Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_LONG).show();
					}
					else{
							
						Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_LONG).show();
					}
				}
				else if(eval=="4"){
					
					let = mh.getLet(1,2,e4.getTag().toString());
					
					if(e4.getText().toString().toUpperCase().equals(let.toString())){
						
						Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_LONG).show();
					}
					else{
							
						Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_LONG).show();
					}
				}
				else if(eval=="5"){
					
					let = mh.getLet(1,2,e5.getTag().toString());
					
					if(e5.getText().toString().toUpperCase().equals(let.toString())){
						
						Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_LONG).show();
					}
					else{
							
						Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_LONG).show();
					}
				}
				else if(eval=="6"){
					
					let = mh.getLet(1,2,e6.getTag().toString());
					
					if(e6.getText().toString().toUpperCase().equals(let.toString())){
						
						Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_LONG).show();
					}
					else{
							
						Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_LONG).show();
					}
				}
				else if(eval=="7"){
					
					let = mh.getLet(1,2,e7.getTag().toString());
					
					if(e7.getText().toString().toUpperCase().equals(let.toString())){
						
						Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_LONG).show();
					}
					else{
							
						Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_LONG).show();
					}
				}
				else if(eval=="8"){
					
					let = mh.getLet(1,3,e8.getTag().toString());
					
					if(e8.getText().toString().toUpperCase().equals(let.toString())){
						
						Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_LONG).show();
					}
					else{
							
						Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_LONG).show();
					}
				}
				else if(eval=="9"){
					
					let = mh.getLet(1,3,e9.getTag().toString());
					
					if(e9.getText().toString().toUpperCase().equals(let.toString())){
						
						Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_LONG).show();
					}
					else{
							
						Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_LONG).show();
					}
				}
				else if(eval=="10"){
					
					let = mh.getLet(1,3,e10.getTag().toString());
					
					if(e10.getText().toString().toUpperCase().equals(let.toString())){
						
						Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_LONG).show();
					}
					else{
							
						Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_LONG).show();
					}
				}
				else if(eval=="11"){
					
					let = mh.getLet(1,3,e11.getTag().toString());
					
					if(e11.getText().toString().toUpperCase().equals(let.toString())){
						
						Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_LONG).show();
					}
					else{
							
						Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_LONG).show();
					}
				}
				else if(eval=="12"){
					
					let = mh.getLet(1,4,e12.getTag().toString());
					
					if(e12.getText().toString().toUpperCase().equals(let.toString())){
						
						Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_LONG).show();
					}
					else{
							
						Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_LONG).show();
					}
				}
				else if(eval=="13"){
					
					let = mh.getLet(1,4,e13.getTag().toString());
					
					if(e13.getText().toString().toUpperCase().equals(let.toString())){
						
						Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_LONG).show();
					}
					else{
							
						Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_LONG).show();
					}
				}
				else if(eval=="14"){
					
					let = mh.getLet(1,5,e14.getTag().toString());
					
					if(e14.getText().toString().toUpperCase().equals(let.toString())){
						
						Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_LONG).show();
					}
					else{
							
						Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_LONG).show();
					}
				}
				else if(eval=="15"){
					
					let = mh.getLet(1,5,e15.getTag().toString());
					
					if(e15.getText().toString().toUpperCase().equals(let.toString())){
						
						Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_LONG).show();
					}
					else{
							
						Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_LONG).show();
					}
				}
				else if(eval=="16"){
					
					let = mh.getLet(1,5,e16.getTag().toString());
					
					if(e16.getText().toString().toUpperCase().equals(let.toString())){
						
						Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_LONG).show();
					}
					else{
							
						Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_LONG).show();
					}
				}
				else if(eval=="17"){
					
					let = mh.getLet(1,6,e17.getTag().toString());
					
					if(e17.getText().toString().toUpperCase().equals(let.toString())){
						
						Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_LONG).show();
					}
					else{
							
						Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_LONG).show();
					}
				}
				else if(eval=="18"){
					
					let = mh.getLet(1,6,e18.getTag().toString());
					
					if(e18.getText().toString().toUpperCase().equals(let.toString())){
						
						Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_LONG).show();
					}
					else{
							
						Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_LONG).show();
					}
				}
				else if(eval=="19"){
					
					let = mh.getLet(1,7,e19.getTag().toString());
					
					if(e19.getText().toString().toUpperCase().equals(let.toString())){
						
						Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_LONG).show();
					}
					else{
							
						Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_LONG).show();
					}
				}
				else if(eval=="20"){
					
					let = mh.getLet(1,8,e20.getTag().toString());
					
					if(e20.getText().toString().toUpperCase().equals(let.toString())){
						
						Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_LONG).show();
					}
					else{
							
						Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_LONG).show();
					}
				}
				
				break;
				
			case R.id.check_word:
				
				Builder builder1 = new AlertDialog.Builder(this);
				builder1.setTitle("Select Clue");
				builder1.setSingleChoiceItems(items,-1,new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {  
				        
						switch(which){
						
						case 0:
							
							ans="";
							//cnt = 0;
							//ncnt = 0;
							
							let = e1.getText().toString();
							ans = ans.concat(let.toString());

							let = e2.getText().toString();
							ans = ans.concat(let.toString());

							let = e3.getText().toString();
							ans = ans.concat(let.toString());
							
							//Toast.makeText(getApplicationContext(),ans.toString(), Toast.LENGTH_LONG).show();
							
							answer = mh.getAns(1,1);
							
							//Toast.makeText(getApplicationContext(),answer.toString(), Toast.LENGTH_LONG).show();
							
							if(!(ans.toString().equals(""))){
								if(answer.toString().equals(ans.toString())){
								
									/*cnt = cnt + 1;
								
									if(cnt == 1){*/
									
									Toast.makeText(getApplicationContext(),"Correct answer!",Toast.LENGTH_LONG).show();
									
									/*}
									else if(cnt > 1){
									
										Toast.makeText(getApplicationContext()," You have " + cnt + " correct answers",Toast.LENGTH_LONG).show();
									}*/
								}
								else{
								
									Animation anim = new AlphaAnimation(1,0);
									anim.setDuration(500);
									anim.setStartOffset(500);
									anim.setInterpolator(new LinearInterpolator());
									anim.setRepeatMode(Animation.REVERSE);
									anim.setRepeatCount(Animation.RELATIVE_TO_PARENT);
									//anim.setBackgroundColor(getResources().getColor(R.color.Redshade));
									
									e1.startAnimation(anim);
									e2.startAnimation(anim);
									e3.startAnimation(anim);
									
									/*ncnt = ncnt + 1;
								
									if(ncnt == 1){*/
									
									Toast.makeText(getApplicationContext(),"Wrong answer!",Toast.LENGTH_LONG).show();
									/*}
									else if(ncnt > 1){
									
										Toast.makeText(getApplicationContext()," You have " + ncnt + " answers wrong",Toast.LENGTH_LONG).show();
									}*/
								}
							}
							else{
								
								Toast.makeText(getApplicationContext()," Please enter your answer... ",Toast.LENGTH_LONG).show();
							}
							
						break;
							
						case 1:
							
							ans = "";
							
							let = e1.getText().toString();
							ans = ans.concat(let.toString());

							let = e4.getText().toString();
							ans = ans.concat(let.toString());

							let = e5.getText().toString();
							ans = ans.concat(let.toString());
							
							let = e6.getText().toString();
							ans = ans.concat(let.toString());

							let = e7.getText().toString();
							ans = ans.concat(let.toString());
							
							answer = mh.getAns(1,2);
							
							if(answer.toString().equals(ans.toString())){
								
								/*cnt = cnt + 1;
								
								if(cnt == 1){*/
									
								Toast.makeText(getApplicationContext(),"Correct answer!",Toast.LENGTH_LONG).show();
								/*}
								else if(cnt > 1){
									
									Toast.makeText(getApplicationContext()," You have " + cnt + " correct answers",Toast.LENGTH_LONG).show();
								}*/
							}
							else{
							
								Animation anim = new AlphaAnimation(1,0);
								anim.setDuration(500);
								anim.setStartOffset(500);
								anim.setInterpolator(new LinearInterpolator());
								anim.setRepeatMode(Animation.REVERSE);
								anim.setRepeatCount(Animation.RELATIVE_TO_PARENT);
								//anim.setBackgroundColor(getResources().getColor(R.color.Redshade));
								
								e1.startAnimation(anim);
								e4.startAnimation(anim);
								e5.startAnimation(anim);
								e6.startAnimation(anim);
								e7.startAnimation(anim);
								
								Toast.makeText(getApplicationContext(),"Wrong answer!",Toast.LENGTH_LONG).show();
							}
							
						break;
						
						case 2:
							
							ans = "";
							
							let = e8.getText().toString();
							ans = ans.concat(let.toString());

							let = e4.getText().toString();
							ans = ans.concat(let.toString());

							let = e9.getText().toString();
							ans = ans.concat(let.toString());
							
							let = e10.getText().toString();
							ans = ans.concat(let.toString());

							let = e11.getText().toString();
							ans = ans.concat(let.toString());
							
							answer = mh.getAns(1,3);
							
							if(answer.toString().equals(ans.toString())){
								
								/*cnt = cnt + 1;
								
								if(cnt == 1){*/
									
								Toast.makeText(getApplicationContext(),"Correct answer!",Toast.LENGTH_LONG).show();
								/*}
								else if(cnt > 1){
									
									Toast.makeText(getApplicationContext()," You have " + cnt + " correct answers",Toast.LENGTH_LONG).show();
								}*/
							}
							else{
							
								Animation anim = new AlphaAnimation(1,0);
								anim.setDuration(500);
								anim.setStartOffset(500);
								anim.setInterpolator(new LinearInterpolator());
								anim.setRepeatMode(Animation.REVERSE);
								anim.setRepeatCount(Animation.RELATIVE_TO_PARENT);
								//anim.setBackgroundColor(getResources().getColor(R.color.Redshade));
								
								e8.startAnimation(anim);
								e4.startAnimation(anim);
								e9.startAnimation(anim);
								e10.startAnimation(anim);
								e11.startAnimation(anim);
								
								Toast.makeText(getApplicationContext(),"Wrong answer!",Toast.LENGTH_LONG).show();
							}
							
						break;
						
						case 3:
							
							ans = "";
							
							let = e8.getText().toString();
							ans = ans.concat(let.toString());

							let = e12.getText().toString();
							ans = ans.concat(let.toString());

							let = e13.getText().toString();
							ans = ans.concat(let.toString());
							
							answer = mh.getAns(1,4);
							
							if(answer.toString().equals(ans.toString())){
								
								/*cnt = cnt + 1;
								
								if(cnt == 1){*/
									
								Toast.makeText(getApplicationContext(),"Correct answer!",Toast.LENGTH_LONG).show();
								/*}
								else if(cnt > 1){
									
									Toast.makeText(getApplicationContext()," You have " + cnt + " correct answers",Toast.LENGTH_LONG).show();
								}*/
							}
							else{
							
								Animation anim = new AlphaAnimation(1,0);
								anim.setDuration(500);
								anim.setStartOffset(500);
								anim.setInterpolator(new LinearInterpolator());
								anim.setRepeatMode(Animation.REVERSE);
								anim.setRepeatCount(Animation.RELATIVE_TO_PARENT);
								//anim.setBackgroundColor(getResources().getColor(R.color.Redshade));
								
								e8.startAnimation(anim);
								e12.startAnimation(anim);
								e13.startAnimation(anim);
								
								Toast.makeText(getApplicationContext(),"Wrong answer!",Toast.LENGTH_LONG).show();
							}
							
						break;
						
						case 4:
							
							ans = "";
							
							let = e3.getText().toString();
							ans = ans.concat(let.toString());

							let = e10.getText().toString();
							ans = ans.concat(let.toString());

							let = e14.getText().toString();
							ans = ans.concat(let.toString());
							
							let = e15.getText().toString();
							ans = ans.concat(let.toString());

							let = e16.getText().toString();
							ans = ans.concat(let.toString());
							
							answer = mh.getAns(1,5);
							
							if(answer.toString().equals(ans.toString())){
								
								/*cnt = cnt + 1;
								
								if(cnt == 1){*/
									
								Toast.makeText(getApplicationContext(),"Correct answer!",Toast.LENGTH_LONG).show();
								/*}
								else if(cnt > 1){
									
									Toast.makeText(getApplicationContext()," You have " + cnt + " correct answers",Toast.LENGTH_LONG).show();
								}*/
							}
							else{
							
								Animation anim = new AlphaAnimation(1,0);
								anim.setDuration(500);
								anim.setStartOffset(500);
								anim.setInterpolator(new LinearInterpolator());
								anim.setRepeatMode(Animation.REVERSE);
								anim.setRepeatCount(Animation.RELATIVE_TO_PARENT);
								//anim.setBackgroundColor(getResources().getColor(R.color.Redshade));
								
								e3.startAnimation(anim);
								e10.startAnimation(anim);
								e14.startAnimation(anim);
								e15.startAnimation(anim);
								e16.startAnimation(anim);
								
								Toast.makeText(getApplicationContext(),"Wrong answer!",Toast.LENGTH_LONG).show();
							}
							
						break;
						
						case 5:
							
							ans = "";
							
							let = e11.getText().toString();
							ans = ans.concat(let.toString());

							let = e17.getText().toString();
							ans = ans.concat(let.toString());

							let = e18.getText().toString();
							ans = ans.concat(let.toString());
							
							answer = mh.getAns(1,6);
							
							if(answer.toString().equals(ans.toString())){
								
								/*cnt = cnt + 1;
								
								if(cnt == 1){*/
									
								Toast.makeText(getApplicationContext(),"Correct answer!",Toast.LENGTH_LONG).show();
								/*}
								else if(cnt > 1){
									
									Toast.makeText(getApplicationContext()," You have " + cnt + " correct answers",Toast.LENGTH_LONG).show();
								}*/
							}
							else{
							
								Animation anim = new AlphaAnimation(1,0);
								anim.setDuration(500);
								anim.setStartOffset(500);
								anim.setInterpolator(new LinearInterpolator());
								anim.setRepeatMode(Animation.REVERSE);
								anim.setRepeatCount(Animation.RELATIVE_TO_PARENT);
								//anim.setBackgroundColor(getResources().getColor(R.color.Redshade));
								
								e11.startAnimation(anim);
								e17.startAnimation(anim);
								e18.startAnimation(anim);
								
								Toast.makeText(getApplicationContext(),"Wrong answer!",Toast.LENGTH_LONG).show();
							}
							
						break;
						
						case 6:
						
							ans = "";
							
							let = e13.getText().toString();
							ans = ans.concat(let.toString());

							let = e6.getText().toString();
							ans = ans.concat(let.toString());

							let = e19.getText().toString();
							ans = ans.concat(let.toString());
							
							let = e15.getText().toString();
							ans = ans.concat(let.toString());

							let = e18.getText().toString();
							ans = ans.concat(let.toString());
							
							answer = mh.getAns(1,7);
							
							if(answer.toString().equals(ans.toString())){
								
								/*cnt = cnt + 1;
								
								if(cnt == 1){*/
									
								Toast.makeText(getApplicationContext(),"Correct answer!",Toast.LENGTH_LONG).show();
								/*}
								else if(cnt > 1){
									
									Toast.makeText(getApplicationContext()," You have " + cnt + " correct answers",Toast.LENGTH_LONG).show();
								}*/
							}
							else{
							
								Animation anim = new AlphaAnimation(1,0);
								anim.setDuration(500);
								anim.setStartOffset(500);
								anim.setInterpolator(new LinearInterpolator());
								anim.setRepeatMode(Animation.REVERSE);
								anim.setRepeatCount(Animation.RELATIVE_TO_PARENT);
								//anim.setBackgroundColor(getResources().getColor(R.color.Redshade));
								
								e13.startAnimation(anim);
								e6.startAnimation(anim);
								e19.startAnimation(anim);
								e15.startAnimation(anim);
								e18.startAnimation(anim);
								
								Toast.makeText(getApplicationContext(),"Wrong answer!",Toast.LENGTH_LONG).show();
							}
							
						break;
						
						case 7:
							
							ans = "";
							
							let = e7.getText().toString();
							ans = ans.concat(let.toString());

							let = e20.getText().toString();
							ans = ans.concat(let.toString());

							let = e16.getText().toString();
							ans = ans.concat(let.toString());
							
							answer = mh.getAns(1,8);
							
							if(answer.toString().equals(ans.toString())){
								
								/*cnt = cnt + 1;
								
								if(cnt == 1){*/
									
								Toast.makeText(getApplicationContext(),"Correct answer!",Toast.LENGTH_LONG).show();
								/*}
								else if(cnt > 1){
									
									Toast.makeText(getApplicationContext()," You have " + cnt + " correct answers",Toast.LENGTH_LONG).show();
								}*/
							}
							else{
							
								Animation anim = new AlphaAnimation(1,0);
								anim.setDuration(500);
								anim.setStartOffset(500);
								anim.setInterpolator(new LinearInterpolator());
								anim.setRepeatMode(Animation.REVERSE);
								anim.setRepeatCount(Animation.RELATIVE_TO_PARENT);
								//anim.setBackgroundColor(getResources().getColor(R.color.Redshade));
								
								e7.startAnimation(anim);
								e20.startAnimation(anim);
								e16.startAnimation(anim);
								
								Toast.makeText(getApplicationContext(),"Wrong answer!",Toast.LENGTH_LONG).show();
							}
							
						break;
						
						}
						
						dialog.dismiss(); 
					}
				});
				
				builder1.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						dialog.dismiss();
					}
				});
				
				AlertDialog alert1 = builder1.create();  
			    alert1.show();
			    
			break;
			
			case R.id.reveal_puz:
				
				Builder builder2 = new AlertDialog.Builder(this);
				builder2.setTitle("Reveal Whole Puzzle");
				builder2.setMessage("Are you sure you want to reveal the whole puzzle?");
				builder2.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						int tot_words = mh.getTotWords(1);
						
						points = 0;
						temp = 0;
						
						temp = tot_words * 5;	// Instead of 2 put tot_words
						points = points - temp;
						
						let = mh.getLet(1,1,e1.getTag().toString());
						e1.setText(let.toString());

						let = mh.getLet(1,1,e2.getTag().toString());
						e2.setText(let.toString());

						let = mh.getLet(1,1,e3.getTag().toString());
						e3.setText(let.toString());
						
						//let = mh.getLet(1,1,e1.getTag().toString());
						//e1.setText(let.toString());

						let = mh.getLet(1,2,e4.getTag().toString());
						e4.setText(let.toString());

						let = mh.getLet(1,2,e5.getTag().toString());
						e5.setText(let.toString());

						let = mh.getLet(1,2,e6.getTag().toString());
						e6.setText(let.toString());
						
						let = mh.getLet(1,2,e7.getTag().toString());
						e7.setText(let.toString());
						
						
						let = mh.getLet(1,3,e8.getTag().toString());
						e8.setText(let.toString());
						
						let = mh.getLet(1,3,e9.getTag().toString());
						e9.setText(let.toString());
						
						let = mh.getLet(1,3,e10.getTag().toString());
						e10.setText(let.toString());
						
						let = mh.getLet(1,3,e11.getTag().toString());
						e11.setText(let.toString());
						
						
						let = mh.getLet(1,4,e12.getTag().toString());
						e12.setText(let.toString());
						
						let = mh.getLet(1,4,e13.getTag().toString());
						e13.setText(let.toString());
						
						
						let = mh.getLet(1,5,e14.getTag().toString());
						e14.setText(let.toString());
						
						let = mh.getLet(1,5,e15.getTag().toString());
						e15.setText(let.toString());
						
						let = mh.getLet(1,5,e16.getTag().toString());
						e16.setText(let.toString());
						
						
						let = mh.getLet(1,6,e17.getTag().toString());
						e17.setText(let.toString());
						
						let = mh.getLet(1,6,e18.getTag().toString());
						e18.setText(let.toString());
						
						
						let = mh.getLet(1,7,e19.getTag().toString());
						e19.setText(let.toString());
						
						let = mh.getLet(1,8,e20.getTag().toString());
						e20.setText(let.toString());
					}
				});
				
				builder2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						dialog.dismiss();
					}
				});
				
				AlertDialog alert2 = builder2.create();  
			    alert2.show();

			    break;
			    
			case R.id.check_puz:
				
				ans = "";
				cnt = 0;
				ncnt = 0;
				flag = 0;
				
				
				let = e1.getText().toString();
				ans = ans.concat(let.toString());

				let = e2.getText().toString();
				ans = ans.concat(let.toString());

				let = e3.getText().toString();
				ans = ans.concat(let.toString());
				
				//Toast.makeText(getApplicationContext(),ans.toString(), Toast.LENGTH_LONG).show();
				
				answer = mh.getAns(1,1);
				
				//Toast.makeText(getApplicationContext(),answer.toString(), Toast.LENGTH_LONG).show();
				
				if(ans.toString().isEmpty() == true){
					
					flag = -1;
				}
				else{
					
					if(ans.toString().equals(answer.toString())){
						
						cnt = cnt + 1;
					
						if(cnt == 1){
						
							//Toast.makeText(getApplicationContext()," You have " + cnt + " correct answer",Toast.LENGTH_LONG).show();
							flag = 1;
						}
						else if(cnt > 1){
						
							//Toast.makeText(getApplicationContext()," You have " + cnt + " correct answers",Toast.LENGTH_LONG).show();
							flag = 2;
						}
						
					}
					else{
					
						ncnt = ncnt + 1;
					
						if(ncnt == 1){
						
							//Toast.makeText(getApplicationContext()," You have " + ncnt + " answer wrong",Toast.LENGTH_LONG).show();
							flag = -2;
						}
						else if(ncnt > 1){
						
							//Toast.makeText(getApplicationContext()," You have " + ncnt + " answers wrong",Toast.LENGTH_LONG).show();
							flag = -3;
						}
					}
				}
				
				ans = "";
				//answer = "";
				flag = 0;
				
				let = e1.getText().toString();
				ans = ans.concat(let.toString());

				let = e4.getText().toString();
				ans = ans.concat(let.toString());

				let = e5.getText().toString();
				ans = ans.concat(let.toString());

				let = e6.getText().toString();
				ans = ans.concat(let.toString());
				
				let = e7.getText().toString();
				ans = ans.concat(let.toString());
				
				//Toast.makeText(getApplicationContext(),ans.toString(), Toast.LENGTH_LONG).show();
				
				answer = mh.getAns(1,2);
				
				//Toast.makeText(getApplicationContext(),answer.toString(), Toast.LENGTH_LONG).show();
				
				if(ans.toString().isEmpty() == true){
					
					flag = -1;
				}
				else{
					
					if(ans.toString().equals(answer.toString())){
						
						cnt = cnt + 1;
					
						if(cnt == 1){
						
							//Toast.makeText(getApplicationContext()," You have " + cnt + " correct answer",Toast.LENGTH_LONG).show();
							flag = 1;
						}
						else if(cnt > 1){
						
							//Toast.makeText(getApplicationContext()," You have " + cnt + " correct answers",Toast.LENGTH_LONG).show();
							flag = 2;
						}
					}
					else{
					
						ncnt = ncnt + 1;
					
						if(ncnt == 1){
						
							//Toast.makeText(getApplicationContext()," You have " + ncnt + " answer wrong",Toast.LENGTH_LONG).show();
							flag = -2;
						}
						else if(ncnt > 1){
						
							//Toast.makeText(getApplicationContext()," You have " + ncnt + " answers wrong",Toast.LENGTH_LONG).show();
							flag = -3;
						}
					}
				}
				
				//ans = "";
				
				if(flag == -1){
					
					Toast.makeText(getApplicationContext()," You have none right! ",Toast.LENGTH_LONG).show();
				}
				if(flag == 1){
					
					Toast.makeText(getApplicationContext()," You have " + cnt + " correct answer",Toast.LENGTH_LONG).show();
				}
				
				
				ans = "";
				//answer = "";
				flag = 0;
				
				let = e8.getText().toString();
				ans = ans.concat(let.toString());

				let = e4.getText().toString();
				ans = ans.concat(let.toString());

				let = e9.getText().toString();
				ans = ans.concat(let.toString());

				let = e10.getText().toString();
				ans = ans.concat(let.toString());
				
				let = e11.getText().toString();
				ans = ans.concat(let.toString());
				
				//Toast.makeText(getApplicationContext(),ans.toString(), Toast.LENGTH_LONG).show();
				
				answer = mh.getAns(1,3);
				
				//Toast.makeText(getApplicationContext(),answer.toString(), Toast.LENGTH_LONG).show();
				
				if(ans.toString().isEmpty() == true){
					
					flag = -1;
				}
				else{
					
					if(ans.toString().equals(answer.toString())){
						
						cnt = cnt + 1;
					
						if(cnt == 1){
						
							//Toast.makeText(getApplicationContext()," You have " + cnt + " correct answer",Toast.LENGTH_LONG).show();
							flag = 1;
						}
						else if(cnt > 1){
						
							//Toast.makeText(getApplicationContext()," You have " + cnt + " correct answers",Toast.LENGTH_LONG).show();
							flag = 2;
						}
					}
					else{
					
						ncnt = ncnt + 1;
					
						if(ncnt == 1){
						
							//Toast.makeText(getApplicationContext()," You have " + ncnt + " answer wrong",Toast.LENGTH_LONG).show();
							flag = -2;
						}
						else if(ncnt > 1){
						
							//Toast.makeText(getApplicationContext()," You have " + ncnt + " answers wrong",Toast.LENGTH_LONG).show();
							flag = -3;
						}
					}
				}
				
				//ans = "";
				
				if(flag == -1){
					
					Toast.makeText(getApplicationContext()," You have none right! ",Toast.LENGTH_LONG).show();
				}
				if(flag == 1){
					
					Toast.makeText(getApplicationContext()," You have " + cnt + " correct answer",Toast.LENGTH_LONG).show();
				}
				
				
				ans = "";
				//answer = "";
				flag = 0;
				
				let = e8.getText().toString();
				ans = ans.concat(let.toString());

				let = e12.getText().toString();
				ans = ans.concat(let.toString());

				let = e13.getText().toString();
				ans = ans.concat(let.toString());
				
				//Toast.makeText(getApplicationContext(),ans.toString(), Toast.LENGTH_LONG).show();
				
				answer = mh.getAns(1,4);
				
				//Toast.makeText(getApplicationContext(),answer.toString(), Toast.LENGTH_LONG).show();
				
				if(ans.toString().isEmpty() == true){
					
					flag = -1;
				}
				else{
					
					if(ans.toString().equals(answer.toString())){
						
						cnt = cnt + 1;
					
						if(cnt == 1){
						
							//Toast.makeText(getApplicationContext()," You have " + cnt + " correct answer",Toast.LENGTH_LONG).show();
							flag = 1;
						}
						else if(cnt > 1){
						
							//Toast.makeText(getApplicationContext()," You have " + cnt + " correct answers",Toast.LENGTH_LONG).show();
							flag = 2;
						}
					}
					else{
					
						ncnt = ncnt + 1;
					
						if(ncnt == 1){
						
							//Toast.makeText(getApplicationContext()," You have " + ncnt + " answer wrong",Toast.LENGTH_LONG).show();
							flag = -2;
						}
						else if(ncnt > 1){
						
							//Toast.makeText(getApplicationContext()," You have " + ncnt + " answers wrong",Toast.LENGTH_LONG).show();
							flag = -3;
						}
					}
				}
				
				//ans = "";
				
				if(flag == -1){
					
					Toast.makeText(getApplicationContext()," You have none right! ",Toast.LENGTH_LONG).show();
				}
				if(flag == 1){
					
					Toast.makeText(getApplicationContext()," You have " + cnt + " correct answer",Toast.LENGTH_LONG).show();
				}
				
				
				ans = "";
				//answer = "";
				flag = 0;
				
				let = e3.getText().toString();
				ans = ans.concat(let.toString());

				let = e10.getText().toString();
				ans = ans.concat(let.toString());

				let = e14.getText().toString();
				ans = ans.concat(let.toString());

				let = e15.getText().toString();
				ans = ans.concat(let.toString());
				
				let = e16.getText().toString();
				ans = ans.concat(let.toString());
				
				//Toast.makeText(getApplicationContext(),ans.toString(), Toast.LENGTH_LONG).show();
				
				answer = mh.getAns(1,5);
				
				//Toast.makeText(getApplicationContext(),answer.toString(), Toast.LENGTH_LONG).show();
				
				if(ans.toString().isEmpty() == true){
					
					flag = -1;
				}
				else{
					
					if(ans.toString().equals(answer.toString())){
						
						cnt = cnt + 1;
					
						if(cnt == 1){
						
							//Toast.makeText(getApplicationContext()," You have " + cnt + " correct answer",Toast.LENGTH_LONG).show();
							flag = 1;
						}
						else if(cnt > 1){
						
							//Toast.makeText(getApplicationContext()," You have " + cnt + " correct answers",Toast.LENGTH_LONG).show();
							flag = 2;
						}
					}
					else{
					
						ncnt = ncnt + 1;
					
						if(ncnt == 1){
						
							//Toast.makeText(getApplicationContext()," You have " + ncnt + " answer wrong",Toast.LENGTH_LONG).show();
							flag = -2;
						}
						else if(ncnt > 1){
						
							//Toast.makeText(getApplicationContext()," You have " + ncnt + " answers wrong",Toast.LENGTH_LONG).show();
							flag = -3;
						}
					}
				}
				
				//ans = "";
				
				if(flag == -1){
					
					Toast.makeText(getApplicationContext()," You have none right! ",Toast.LENGTH_LONG).show();
				}
				if(flag == 1){
					
					Toast.makeText(getApplicationContext()," You have " + cnt + " correct answer",Toast.LENGTH_LONG).show();
				}
				
				
				ans = "";
				//answer = "";
				flag = 0;
				
				let = e11.getText().toString();
				ans = ans.concat(let.toString());

				let = e17.getText().toString();
				ans = ans.concat(let.toString());

				let = e18.getText().toString();
				ans = ans.concat(let.toString());
				
				//Toast.makeText(getApplicationContext(),ans.toString(), Toast.LENGTH_LONG).show();
				
				answer = mh.getAns(1,6);
				
				//Toast.makeText(getApplicationContext(),answer.toString(), Toast.LENGTH_LONG).show();
				
				if(ans.toString().isEmpty() == true){
					
					flag = -1;
				}
				else{
					
					if(ans.toString().equals(answer.toString())){
						
						cnt = cnt + 1;
					
						if(cnt == 1){
						
							//Toast.makeText(getApplicationContext()," You have " + cnt + " correct answer",Toast.LENGTH_LONG).show();
							flag = 1;
						}
						else if(cnt > 1){
						
							//Toast.makeText(getApplicationContext()," You have " + cnt + " correct answers",Toast.LENGTH_LONG).show();
							flag = 2;
						}
					}
					else{
					
						ncnt = ncnt + 1;
					
						if(ncnt == 1){
						
							//Toast.makeText(getApplicationContext()," You have " + ncnt + " answer wrong",Toast.LENGTH_LONG).show();
							flag = -2;
						}
						else if(ncnt > 1){
						
							//Toast.makeText(getApplicationContext()," You have " + ncnt + " answers wrong",Toast.LENGTH_LONG).show();
							flag = -3;
						}
					}
				}
				
				//ans = "";
				
				if(flag == -1){
					
					Toast.makeText(getApplicationContext()," You have none right! ",Toast.LENGTH_LONG).show();
				}
				if(flag == 1){
					
					Toast.makeText(getApplicationContext()," You have " + cnt + " correct answer",Toast.LENGTH_LONG).show();
				}
				
				
				ans = "";
				//answer = "";
				flag = 0;
				
				let = e13.getText().toString();
				ans = ans.concat(let.toString());

				let = e6.getText().toString();
				ans = ans.concat(let.toString());

				let = e19.getText().toString();
				ans = ans.concat(let.toString());

				let = e15.getText().toString();
				ans = ans.concat(let.toString());
				
				let = e18.getText().toString();
				ans = ans.concat(let.toString());
				
				//Toast.makeText(getApplicationContext(),ans.toString(), Toast.LENGTH_LONG).show();
				
				answer = mh.getAns(1,7);
				
				//Toast.makeText(getApplicationContext(),answer.toString(), Toast.LENGTH_LONG).show();
				
				if(ans.toString().isEmpty() == true){
					
					flag = -1;
				}
				else{
					
					if(ans.toString().equals(answer.toString())){
						
						cnt = cnt + 1;
					
						if(cnt == 1){
						
							//Toast.makeText(getApplicationContext()," You have " + cnt + " correct answer",Toast.LENGTH_LONG).show();
							flag = 1;
						}
						else if(cnt > 1){
						
							//Toast.makeText(getApplicationContext()," You have " + cnt + " correct answers",Toast.LENGTH_LONG).show();
							flag = 2;
						}
					}
					else{
					
						ncnt = ncnt + 1;
					
						if(ncnt == 1){
						
							//Toast.makeText(getApplicationContext()," You have " + ncnt + " answer wrong",Toast.LENGTH_LONG).show();
							flag = -2;
						}
						else if(ncnt > 1){
						
							//Toast.makeText(getApplicationContext()," You have " + ncnt + " answers wrong",Toast.LENGTH_LONG).show();
							flag = -3;
						}
					}
				}
				
				//ans = "";
				
				if(flag == -1){
					
					Toast.makeText(getApplicationContext()," You have none right! ",Toast.LENGTH_LONG).show();
				}
				if(flag == 1){
					
					Toast.makeText(getApplicationContext()," You have " + cnt + " correct answer",Toast.LENGTH_LONG).show();
				}
				
				
				
				ans = "";
				//answer = "";
				flag = 0;
				
				let = e7.getText().toString();
				ans = ans.concat(let.toString());

				let = e20.getText().toString();
				ans = ans.concat(let.toString());

				let = e16.getText().toString();
				ans = ans.concat(let.toString());
				
				//Toast.makeText(getApplicationContext(),ans.toString(), Toast.LENGTH_LONG).show();
				
				answer = mh.getAns(1,8);
				
				//Toast.makeText(getApplicationContext(),answer.toString(), Toast.LENGTH_LONG).show();
				
				if(ans.toString().isEmpty() == true){
					
					flag = -1;
				}
				else{
					
					if(ans.toString().equals(answer.toString())){
						
						cnt = cnt + 1;
					
						if(cnt == 1){
						
							//Toast.makeText(getApplicationContext()," You have " + cnt + " correct answer",Toast.LENGTH_LONG).show();
							flag = 1;
						}
						else if(cnt > 1){
						
							//Toast.makeText(getApplicationContext()," You have " + cnt + " correct answers",Toast.LENGTH_LONG).show();
							flag = 2;
						}
					}
					else{
					
						ncnt = ncnt + 1;
					
						if(ncnt == 1){
						
							//Toast.makeText(getApplicationContext()," You have " + ncnt + " answer wrong",Toast.LENGTH_LONG).show();
							flag = -2;
						}
						else if(ncnt > 1){
						
							//Toast.makeText(getApplicationContext()," You have " + ncnt + " answers wrong",Toast.LENGTH_LONG).show();
							flag = -3;
						}
					}
				}
				
				//ans = "";
				
				if(flag == -1){
					
					Toast.makeText(getApplicationContext()," You have none right! ",Toast.LENGTH_LONG).show();
				}
				if(flag == 1){
					
					Toast.makeText(getApplicationContext()," You have " + cnt + " correct answer",Toast.LENGTH_LONG).show();
				}
				
				
				if(flag == 2){
					
					int total_words = mh.getTotWords(1);
					
					//Toast.makeText(getApplicationContext(),"Total Words: " + total_words,Toast.LENGTH_SHORT).show();
					
					if(cnt==total_words){	// Instead of 2 put tot_words
					
						f = f + 1;
						
						if(f==1){
						
							temp = 0;
							temp = cnt * 5;
							points = points + temp;
						}
						
						//Toast.makeText(getApplicationContext(),"show: " + points,Toast.LENGTH_SHORT).show();
						//Toast.makeText(getApplicationContext(),"show: " + temp,Toast.LENGTH_SHORT).show();
						
						if(points == (cnt * 5)){
							
							Cursor c;
							
							try{
								
								c = mh.getPlayer();
								
								if((c!=null) && (c.getCount() > 0)){
									
									if(c.moveToFirst()){
									
										do{
								
												int id1 = c.getColumnIndex("player_name");
												player_name = c.getString(id1);
								
												//Toast.makeText(getApplicationContext(),"Player Name: " + player_name.toString(),Toast.LENGTH_SHORT).show();
								
												int id = c.getColumnIndex("player_score");
												score = c.getInt(id);
								
												score = score + 1;
								
												//Toast.makeText(getApplicationContext(),"Score: " + score,Toast.LENGTH_SHORT).show();
								
												int res = mh.updateScore(score, player_name);
								
												if(res > 0){
									
													Toast.makeText(getApplicationContext(),"Bravo you have completed this crossword!!",Toast.LENGTH_SHORT).show();
												}
												
										}while(c.moveToNext());
									}
								}
								
							}catch(Exception e){
								
								e.printStackTrace();
							}
							
							//Toast.makeText(getApplicationContext(),"Bravo!!",Toast.LENGTH_SHORT).show();
						}
						
						Builder builder3 = new AlertDialog.Builder(this);
						builder3.setTitle("Final Score");
						builder3.setMessage("Your final score:   " + points);
						builder3.setPositiveButton("OK", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								
								//isDisabled = true;
								dialog.dismiss();
							}
						});
						
						AlertDialog alert3 = builder3.create();  
					    alert3.show();
					}
					
					Toast.makeText(getApplicationContext()," You have " + cnt + " correct answers",Toast.LENGTH_LONG).show();
				}
				if(flag == -2){
				
					Toast.makeText(getApplicationContext()," You have " + ncnt + " answer wrong",Toast.LENGTH_LONG).show();
				}
				if(flag == -3){
					
					Toast.makeText(getApplicationContext()," You have " + ncnt + " answers wrong",Toast.LENGTH_LONG).show();
				}
				
			break;
			
			case R.id.clr_puz:
				
				Builder builder4 = new AlertDialog.Builder(this);
				builder4.setTitle("Clear Whole Puzzle");
				builder4.setMessage("Are you sure you want to clear the whole puzzle?");
				builder4.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						points = 0;
						
						e1.setText("");

						e2.setText("");

						e3.setText("");
						
						e1.setText("");

						e4.setText("");

						e5.setText("");

						e6.setText("");
						
						e7.setText("");
						
						e8.setText("");
						
						e9.setText("");
						
						e10.setText("");
						
						e11.setText("");
						
						e12.setText("");
						
						e13.setText("");
						
						e14.setText("");
						
						e15.setText("");
						
						e16.setText("");
						
						e17.setText("");
						
						e18.setText("");
						
						e19.setText("");
						
						e20.setText("");
						
						f = 0;
						fl = 0;
					}
				});
				
				builder4.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						dialog.dismiss();
					}
				});
				
				AlertDialog alert4 = builder4.create();  
			    alert4.show();
				
			break;
			
			case R.id.clue_list:
				
				Intent intentObject = new Intent(getApplicationContext(),CluelistActivityMainActivity.class);
				startActivity(intentObject);
				
			break;
			
			case R.id.show_hint:
				
				mh = MySQLiteHelper.getInstance(getApplicationContext());
				
				no_hints = mh.getNohints(1);
				
				Builder builder5 = new AlertDialog.Builder(this);
				builder5.setTitle("Select Clue");
				builder5.setSingleChoiceItems(items,-1,new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {  
				        
						switch(which){
						
						case 0:
							
							fl = fl + 1;
							
							if(fl > no_hints){ 
							
								Toast.makeText(getApplicationContext(),"Sorry you have no hints left!",Toast.LENGTH_LONG).show();
								break;
							}
							
							hint = mh.getHint(1,1);
							
							Toast.makeText(getApplicationContext(),hint.toString(),Toast.LENGTH_LONG).show();
								
							if(fl == (no_hints - 1)){ 
								
								points = points - 1;
								
								Toast.makeText(getApplicationContext(),"You have only 1 hint left....",Toast.LENGTH_LONG).show();
							}
							
							if(fl == (no_hints - 2)){ 
								
								points = points - 1;
								
								Toast.makeText(getApplicationContext(),"You have no hints left....",Toast.LENGTH_LONG).show();
							}
							
						break;
							
						case 1:
							
							fl = fl + 1;
							
							if(fl > no_hints){ 
							
								Toast.makeText(getApplicationContext(),"Sorry you have no hints left!",Toast.LENGTH_LONG).show();
								break;
							}
							
							hint = mh.getHint(1,2);
							
							Toast.makeText(getApplicationContext(),hint.toString(),Toast.LENGTH_LONG).show();
							
							if(fl == (no_hints - 1)){ 
								
								points = points - 1;
								
								Toast.makeText(getApplicationContext(),"You have only 1 hint left....",Toast.LENGTH_LONG).show();
							}
							
							if(fl == (no_hints - 2)){ 
								
								points = points - 1;
								
								Toast.makeText(getApplicationContext(),"You have no hints left....",Toast.LENGTH_LONG).show();
							}

						break;
						
						case 2:
							
							fl = fl + 1;
							
							if(fl > no_hints){ 
							
								Toast.makeText(getApplicationContext(),"Sorry you have no hints left!",Toast.LENGTH_LONG).show();
								break;
							}
							
							hint = mh.getHint(1,3);
							
							Toast.makeText(getApplicationContext(),hint.toString(),Toast.LENGTH_LONG).show();
							
							if(fl == (no_hints - 1)){ 
								
								points = points - 1;
								
								Toast.makeText(getApplicationContext(),"You have only 1 hint left....",Toast.LENGTH_LONG).show();
							}
							
							if(fl == (no_hints - 2)){ 
								
								points = points - 1;
								
								Toast.makeText(getApplicationContext(),"You have no hints left....",Toast.LENGTH_LONG).show();
							}

						break;
						
						case 3:
						
							fl = fl + 1;
							
							if(fl > no_hints){ 
							
								Toast.makeText(getApplicationContext(),"Sorry you have no hints left!",Toast.LENGTH_LONG).show();
								break;
							}
							
							hint = mh.getHint(1,4);
							
							Toast.makeText(getApplicationContext(),hint.toString(),Toast.LENGTH_LONG).show();
							
							if(fl == (no_hints - 1)){ 
								
								points = points - 1;
								
								Toast.makeText(getApplicationContext(),"You have only 1 hint left....",Toast.LENGTH_LONG).show();
							}
							
							if(fl == (no_hints - 2)){ 
								
								points = points - 1;
								
								Toast.makeText(getApplicationContext(),"You have no hints left....",Toast.LENGTH_LONG).show();
							}

						break;
						
						case 4:
						
							fl = fl + 1;
							
							if(fl > no_hints){ 
							
								Toast.makeText(getApplicationContext(),"Sorry you have no hints left!",Toast.LENGTH_LONG).show();
								break;
							}
							
							hint = mh.getHint(1,5);
							
							Toast.makeText(getApplicationContext(),hint.toString(),Toast.LENGTH_LONG).show();
							
							if(fl == (no_hints - 1)){ 
								
								points = points - 1;
								
								Toast.makeText(getApplicationContext(),"You have only 1 hint left....",Toast.LENGTH_LONG).show();
							}
							
							if(fl == (no_hints - 2)){ 
								
								points = points - 1;
								
								Toast.makeText(getApplicationContext(),"You have no hints left....",Toast.LENGTH_LONG).show();
							}

							
						break;
						
						case 5:
						
							fl = fl + 1;
							
							if(fl > no_hints){ 
							
								Toast.makeText(getApplicationContext(),"Sorry you have no hints left!",Toast.LENGTH_LONG).show();
								break;
							}
							
							hint = mh.getHint(1,6);
							
							Toast.makeText(getApplicationContext(),hint.toString(),Toast.LENGTH_LONG).show();
							
							if(fl == (no_hints - 1)){ 
								
								points = points - 1;
								
								Toast.makeText(getApplicationContext(),"You have only 1 hint left....",Toast.LENGTH_LONG).show();
							}
							
							if(fl == (no_hints - 2)){ 
								
								points = points - 1;
								
								Toast.makeText(getApplicationContext(),"You have no hints left....",Toast.LENGTH_LONG).show();
							}

							
						break;
						
						case 7:
						
							fl = fl + 1;
							
							if(fl > no_hints){ 
							
								Toast.makeText(getApplicationContext(),"Sorry you have no hints left!",Toast.LENGTH_LONG).show();
								break;
							}
							
							hint = mh.getHint(1,8);
							
							Toast.makeText(getApplicationContext(),hint.toString(),Toast.LENGTH_LONG).show();
							
							if(fl == (no_hints - 1)){ 
								
								points = points - 1;
								
								Toast.makeText(getApplicationContext(),"You have only 1 hint left....",Toast.LENGTH_LONG).show();
							}
							
							if(fl == (no_hints - 2)){ 
								
								points = points - 1;
								
								Toast.makeText(getApplicationContext(),"You have no hints left....",Toast.LENGTH_LONG).show();
							}

							
						break;
						
						}
						
						dialog.dismiss(); 
					}
				});
				
				builder5.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						dialog.dismiss();
					}
				});
				
				AlertDialog alert5 = builder5.create();  
			    alert5.show();
			    
			    break;
		
			case R.id.save:
				
				final Builder builder7 = new AlertDialog.Builder(this);
				builder7.setTitle("Warning....");
				builder7.setMessage("A saved game with this name already exists. Do you wish to overwrite it?");
				
				builder7.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						//Toast.makeText(getApplicationContext(),"Done",Toast.LENGTH_LONG).show();
						
						try {
							
							flag_val = mh.deleteSavedGame(myPreferences);
							
						} catch (Exception e) {
							
							e.printStackTrace();
						}
						
						if(flag_val > 0){
						
							SharedPreferences rem = getApplicationContext().getSharedPreferences(myPreferences, Context.MODE_PRIVATE);
							rem.edit().clear().commit();
							
							mh.addSavedgame(myPreferences);
							
							delPref = getApplicationContext().getSharedPreferences(myPreferences,Context.MODE_PRIVATE);
					
							let = "";
							Editor editor = delPref.edit();
					
							let = e1.getText().toString();
							if(!(let.equals(""))){
						
								editor.putString(a1, let);
								let="";
							}
					
							let = e2.getText().toString();
							if(!(let.equals(""))){
						
								editor.putString(a1_1, let);
								let = "";
							}
					
							let = e3.getText().toString();
							if(!(let.equals(""))){
						
								editor.putString(a1_2, let);
								let="";
							}
					
							let = e4.getText().toString();
							if(!(let.equals(""))){
						
								editor.putString(d1_1, let);
								let="";
							}
					
							let = e5.getText().toString();
							if(!(let.equals(""))){
						
								editor.putString(d1_2, let);
								let="";
							}
					
							let = e6.getText().toString();
							if(!(let.equals(""))){
						
								editor.putString(d1_3, let);
								let="";
							}
					
							let = e7.getText().toString();
							if(!(let.equals(""))){
						
								editor.putString(d1_4, let);
								let="";
							}
					
							let = e8.getText().toString();
							if(!(let.equals(""))){
						
								editor.putString(a2, let);
								let="";
							}
							
							let = e9.getText().toString();
							if(!(let.equals(""))){
						
								editor.putString(a2_1, let);
								let="";
							}
							
							let = e10.getText().toString();
							if(!(let.equals(""))){
						
								editor.putString(a2_2, let);
								let="";
							}
							
							let = e11.getText().toString();
							if(!(let.equals(""))){
						
								editor.putString(a2_3, let);
								let="";
							}
							
							let = e12.getText().toString();
							if(!(let.equals(""))){
						
								editor.putString(d2, let);
								let="";
							}
							
							let = e13.getText().toString();
							if(!(let.equals(""))){
						
								editor.putString(d2_1, let);
								let="";
							}
							
							let = e14.getText().toString();
							if(!(let.equals(""))){
						
								editor.putString(d3, let);
								let="";
							}
							
							let = e15.getText().toString();
							if(!(let.equals(""))){
						
								editor.putString(d3_1, let);
								let="";
							}
							
							let = e16.getText().toString();
							if(!(let.equals(""))){
						
								editor.putString(d3_2, let);
								let="";
							}
							
							let = e17.getText().toString();
							if(!(let.equals(""))){
						
								editor.putString(d4, let);
								let="";
							}
							
							let = e18.getText().toString();
							if(!(let.equals(""))){
						
								editor.putString(d4_1, let);
								let="";
							}
							
							let = e19.getText().toString();
							if(!(let.equals(""))){
						
								editor.putString(a3, let);
								let="";
							}
							
							let = e20.getText().toString();
							if(!(let.equals(""))){
						
								editor.putString(a4, let);
								let="";
							}
							
							editor.putInt(pts, points);
					
							boolean val = editor.commit();
					
							if(val == true){
						
								Toast.makeText(getApplicationContext(),"Overwrite performed successfully....",Toast.LENGTH_LONG).show();
							}
							
							//Toast.makeText(getApplicationContext(),"Overwrite performed successfully....",Toast.LENGTH_LONG).show();
						}
						else if(flag_val == 0){
							
							Toast.makeText(getApplicationContext(),"Error in overwriting file....",Toast.LENGTH_LONG).show();
						}
					}
				});
				
				builder7.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						dialog.dismiss();
					}
				});
				
				
				
				Builder builder6 = new AlertDialog.Builder(this);
				builder6.setTitle("Save Game");
				builder6.setMessage("Enter save game name");
				
				final EditText svgm = new EditText(Crossui.this);
				LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
																			LinearLayout.LayoutParams.MATCH_PARENT);
				svgm.setLayoutParams(lp);
				builder6.setView(svgm);
				
				builder6.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						myPreferences = svgm.getText().toString();
						
						try {
							
							flag_value = mh.checkRewrite(myPreferences);
							
						} catch (Exception e) {
							
							e.printStackTrace();
						}
						
						if(flag_value == 0){
						
								mh.addSavedgame(myPreferences);
						
								sharedPref = getApplicationContext().getSharedPreferences(myPreferences,Context.MODE_PRIVATE);
						
								let = "";
								Editor editor = sharedPref.edit();
						
								let = e1.getText().toString();
								if(!(let.equals(""))){
							
									editor.putString(a1, let);
									let="";
								}
						
								let = e2.getText().toString();
								if(!(let.equals(""))){
							
									editor.putString(a1_1, let);
									let = "";
								}
						
								let = e3.getText().toString();
								if(!(let.equals(""))){
							
									editor.putString(a1_2, let);
									let="";
								}
						
								let = e4.getText().toString();
								if(!(let.equals(""))){
							
									editor.putString(d1_1, let);
									let="";
								}
						
								let = e5.getText().toString();
								if(!(let.equals(""))){
							
									editor.putString(d1_2, let);
									let="";
								}
						
								let = e6.getText().toString();
								if(!(let.equals(""))){
							
									editor.putString(d1_3, let);
									let="";
								}
						
								let = e7.getText().toString();
								if(!(let.equals(""))){
							
									editor.putString(d1_4, let);
									let="";
								}
						
								let = e8.getText().toString();
								if(!(let.equals(""))){
							
									editor.putString(a2, let);
									let="";
								}
								
								let = e9.getText().toString();
								if(!(let.equals(""))){
							
									editor.putString(a2_1, let);
									let="";
								}
								
								let = e10.getText().toString();
								if(!(let.equals(""))){
							
									editor.putString(a2_2, let);
									let="";
								}
								
								let = e11.getText().toString();
								if(!(let.equals(""))){
							
									editor.putString(a2_3, let);
									let="";
								}
								
								let = e12.getText().toString();
								if(!(let.equals(""))){
							
									editor.putString(d2, let);
									let="";
								}
								
								let = e13.getText().toString();
								if(!(let.equals(""))){
							
									editor.putString(d2_1, let);
									let="";
								}
								
								let = e14.getText().toString();
								if(!(let.equals(""))){
							
									editor.putString(d3, let);
									let="";
								}
								
								let = e15.getText().toString();
								if(!(let.equals(""))){
							
									editor.putString(d3_1, let);
									let="";
								}
								
								let = e16.getText().toString();
								if(!(let.equals(""))){
							
									editor.putString(d3_2, let);
									let="";
								}
								
								let = e17.getText().toString();
								if(!(let.equals(""))){
							
									editor.putString(d4, let);
									let="";
								}
								
								let = e18.getText().toString();
								if(!(let.equals(""))){
							
									editor.putString(d4_1, let);
									let="";
								}
								
								let = e19.getText().toString();
								if(!(let.equals(""))){
							
									editor.putString(a3, let);
									let="";
								}
								
								let = e20.getText().toString();
								if(!(let.equals(""))){
							
									editor.putString(a4, let);
									let="";
								}
								
								editor.putInt(pts, points);
						
								boolean val = editor.commit();
						
								if(val == true){
							
									Toast.makeText(getApplicationContext(),"Game saved successfully....",Toast.LENGTH_LONG).show();
								}
						}
						else if(flag_value == 1){
							
							AlertDialog alert7 = builder7.create();  
						    alert7.show();
							
						}
					}
				});
				
				builder6.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						dialog.dismiss();
					}
				});
				
				AlertDialog alert6 = builder6.create();  
			    alert6.show();
			    
			    break;
			    
			case R.id.quit:
				
				Intent intentObj = new Intent(getApplicationContext(),PlayCrossActivity.class);
				startActivity(intentObj);
				finish();
			
			break;
			
			case R.id.quit_mainmenu:
				
				Intent newIntentObj = new Intent(getApplicationContext(),MainActivity.class);
				startActivity(newIntentObj);
				finish();
		}
		
		return true;

	}
}
