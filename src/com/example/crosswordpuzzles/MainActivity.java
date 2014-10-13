package com.example.crosswordpuzzles;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

	private Button playcross_btn,playpuzz_btn,sett_btn,exit_btn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_screen);
		
		//MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.)
		playcross_btn = (Button)findViewById(R.id.playcross_btn);
		playpuzz_btn = (Button)findViewById(R.id.playpuzzle_btn);
		sett_btn = (Button)findViewById(R.id.settings_btn);
		exit_btn = (Button)findViewById(R.id.exit_btn);
		
		
		playcross_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intentObject = new Intent(getApplicationContext(),PlayCrossActivity.class);
				startActivity(intentObject);
				finish();
			}
		});
		
		
		playpuzz_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intentObj1 = new Intent(getApplicationContext(),Play_puzzles_Activity.class);
				startActivity(intentObj1);
				finish();
			}
		});
		
		
		sett_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intentObj2 = new Intent(getApplicationContext(),Settings_Activity.class);
				startActivity(intentObj2);
				finish();
			}
		});
		
		
		final Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Quit App");
		builder.setMessage("Are you sure you want to quit the application?");
		
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				finish();
			}
		});
		
		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				dialog.dismiss();
			}
		});
		
		exit_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				AlertDialog alert = builder.create();  
			    alert.show();
			}
		});
	}

	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_action_main, menu);
 
        return super.onCreateOptionsMenu(menu);
    }
	
	public boolean onOptionsItemSelected(MenuItem item){
		
		switch(item.getItemId()){
		
		case R.id.menu_lang:
			
			Intent intentObj3 = new Intent(getApplicationContext(),AboutActivity.class);
			startActivity(intentObj3);
			finish();
		}
		
		return true;
	}
}
