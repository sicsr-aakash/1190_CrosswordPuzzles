package com.example.crosswordpuzzles;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

public class StatisticsActivity extends Activity {
	
	private TextView txt_name,txt_score,name_head,score_head;
	
	MySQLiteHelper mh;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_statistics);

		mh = MySQLiteHelper.getInstance(getApplicationContext());
		
		txt_name = (TextView)findViewById(R.id.name_value);
		txt_score = (TextView)findViewById(R.id.score_value);
		name_head = (TextView)findViewById(R.id.txt_name);
		score_head = (TextView)findViewById(R.id.txt_score);
		
		try {
			
			Cursor c = mh.getPlayer();
			
			if((c!=null) && (c.getCount() > 0)){
				
				if(c.moveToFirst()){
				
					do{
						
						int id1 = c.getColumnIndex("player_name");
						String player_name = c.getString(id1);
		
						int id = c.getColumnIndex("player_score");
						int score = c.getInt(id);
						
						String sc = String.valueOf(score);
						
						name_head.setText("Player Name");
						score_head.setText("Crosswords Completed");
						
						txt_name.setText(player_name.toString());
						txt_score.setText(sc.toString());
						
					}while(c.moveToNext());
				}
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
