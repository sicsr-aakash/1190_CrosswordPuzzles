package com.example.crosswordpuzzles;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListSavedGamesActivity extends Activity {

	private ListView list_savedgames;
	private ArrayList<String> savedgames_list;
	String sav_gm,svgm;
	int id,flag;
	public Intent i;
	
	public static final String a1 = "e1";
	public static final String a1_1 = "e2";
	public static final String a1_2 = "e3";
	public static final String d1_1 = "e4";
	public static final String d1_2 = "e5";
	public static final String d1_3 = "e6";
	public static final String d1_4 = "e7";
	public static final String pts = "points";
	
	SharedPreferences sharedPref;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_list_saved_games);
		
		MySQLiteHelper mh = MySQLiteHelper.getInstance(getApplicationContext());
		
		i = new Intent(this, Crossui.class);
		
		list_savedgames = (ListView)findViewById(R.id.list_savedgames);	
		Cursor c;
		
		try {
				c = mh.getSavedGames();
				savedgames_list = new ArrayList<String>();
			
				if((c!=null) && (c.getCount() > 0)){
				
					if(c.moveToFirst()){
					
						do{
							
							id = c.getColumnIndex("savedgame_name");
							sav_gm = c.getString(id);
							//Toast.makeText(getApplicationContext(),sav_gm.toString(), Toast.LENGTH_LONG).show();
							savedgames_list.add(sav_gm);
						
						}while(c.moveToNext());
					}
				
					ArrayAdapter<String> adp = new ArrayAdapter<String>(
							this,
							android.R.layout.simple_list_item_1,
							savedgames_list);
				
					list_savedgames.setAdapter(adp);
				}
				
			}catch (Exception e){
				
				e.printStackTrace();
			}
		
		list_savedgames.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				svgm = (String) (list_savedgames.getItemAtPosition(position));
				//Toast.makeText(getApplicationContext(), svgm.toString(), Toast.LENGTH_LONG).show();
			
				//sharedPref = getApplicationContext().getSharedPreferences(svgm.toString(),Context.MODE_PRIVATE);
			
				if(svgm.toString().equals("")){
					
					flag = 0;
					
					Bundle bundle = new Bundle();
					bundle.putString("svgm", svgm);
					bundle.putInt("flag", flag);
					i.putExtras(bundle);
					startActivity(i);
					
				}
				else{
					
					flag = 1;
					
					Bundle bundle = new Bundle();
					bundle.putString("svgm", svgm);
					bundle.putInt("flag", flag);
					i.putExtras(bundle);
					startActivity(i);
					
				}
				
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    
		getMenuInflater().inflate(R.menu.list_saved_games, menu);
		
	    return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item){
		
		switch(item.getItemId()){
		
		case R.id.back_mainmenu:
			
			Intent intentObj = new Intent(getApplicationContext(),PlayCrossActivity.class);
			startActivity(intentObj);
			finish();
			
		}
		
		return true;
	}
}
