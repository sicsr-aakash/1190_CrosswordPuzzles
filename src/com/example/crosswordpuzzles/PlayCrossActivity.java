package com.example.crosswordpuzzles;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PlayCrossActivity extends ActionBarActivity {

	private Button diffbtn;
	private Button newgamebtn;
	private Button catgbtn;
	private Button loadgamebtn;
	private Button backbtn;
	private Button statsbtn;
	
	String catg="",diff_level="";
	final CharSequence[] items = {"Beginner","Professional","Elite"};
	final CharSequence[] categories = {"General","Computers and Technology","German Special"};
	AlertDialog levelDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.play_crossword);
		
		diffbtn = (Button)findViewById(R.id.difflevel_btn);
		newgamebtn = (Button)findViewById(R.id.newgame_btn);
		catgbtn = (Button)findViewById(R.id.catg_btn);
		loadgamebtn = (Button)findViewById(R.id.loadgame_btn);
		backbtn = (Button)findViewById(R.id.back_btn);
		statsbtn = (Button)findViewById(R.id.stats_btn);
		
		diffbtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(PlayCrossActivity.this);
                builder.setTitle("Select Difficulty Level");
                builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int item) {
                   
                    
                    switch(item)
                    {
                        case 0:
                                diff_level = items[0].toString();
                                //Toast.makeText(getApplicationContext(),diff_level.toString(),Toast.LENGTH_LONG).show();
                                
                                break;
          
                    }
                    	levelDialog.dismiss();    
                    }
                });
                
                levelDialog = builder.create();
                levelDialog.show();	
			}
		});
		
		
		catgbtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				AlertDialog.Builder builder1 = new AlertDialog.Builder(PlayCrossActivity.this);
                builder1.setTitle("Select Category");
                builder1.setSingleChoiceItems(categories, -1, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int item) {
                   
                    switch(item)
                    {
                        case 0:
                        	
                        		catg = categories[0].toString();
                        		//Toast.makeText(getApplicationContext(),catg.toString(),Toast.LENGTH_LONG).show();
                                break;
          
                    }
                    	levelDialog.dismiss();    
                    }
                });
                
                levelDialog = builder1.create();
                levelDialog.show();	
				
			}
		});
		
		
		newgamebtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//Toast.makeText(getApplicationContext(),diff_level.toString(),Toast.LENGTH_LONG).show();
				//Toast.makeText(getApplicationContext(),catg.toString(),Toast.LENGTH_LONG).show();
				
				if((diff_level.toString().equals("")) || (catg.toString().equals(""))){
					
					AlertDialog.Builder builder2 = new AlertDialog.Builder(PlayCrossActivity.this);
	                builder2.setTitle("Error...");
					builder2.setMessage("Please select difficulty level and category");
					
					builder2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							
							dialog.dismiss();
						}
					});
					
					AlertDialog alert = builder2.create();  
				    alert.show();
				    
				}
				else if((diff_level.toString().equals("Beginner")) && (catg.toString().equals("General"))){
					
					Intent intentObj = new Intent(getApplicationContext(),Crossui.class);
					startActivity(intentObj);
					finish();
				}
			}
		});
		
		
		loadgamebtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intentObj = new Intent(getApplicationContext(),ListSavedGamesActivity.class);
				startActivity(intentObj);
				finish();
			}
		});
		
		
		backbtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intentObj1 = new Intent(getApplicationContext(),MainActivity.class);
				startActivity(intentObj1);
				finish();
			}
		});
		
		
		statsbtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intentObj2 = new Intent(getApplicationContext(),StatisticsActivity.class);
				startActivity(intentObj2);
				finish();
			}
		});
	}
}
