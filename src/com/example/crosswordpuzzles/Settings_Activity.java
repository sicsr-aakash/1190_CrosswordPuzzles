package com.example.crosswordpuzzles;

import java.io.IOException;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Settings_Activity extends ActionBarActivity {

	private Button chg_plnm,back_btn,audio_btn;
	private TextView txt_name;
	MySQLiteHelper mh;
	
	String opt="";
	
	final CharSequence[] options = {"Yes","No"};

	AlertDialog levelDialog;
	MediaPlayer msound;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_screen);
		
		chg_plnm = (Button)findViewById(R.id.chgpname_btn);
		back_btn = (Button)findViewById(R.id.backsettings_btn);
		//audio_btn = (Button)findViewById(R.id.chg_audio);
		txt_name = (TextView)findViewById(R.id.name_value);
		
		/*try {
			
			AssetFileDescriptor afd = getAssets().openFd("metapreview.mp3");
			//Toast.makeText(getApplicationContext(), afd.toString(), Toast.LENGTH_SHORT).show();
			msound = new MediaPlayer();
			
			msound.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
			//msound.setOnPreparedListener(new V);
			msound.prepare();
			//msound.start();
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}*/
		
		//msound = MediaPlayer.create(this, R.raw.metapreview);
		
		
		mh = MySQLiteHelper.getInstance(getApplicationContext());
		
		/*msound = new MediaPlayer();
		//int resId = getApplicationContext().getResources().getIdentifier("metapreview", "raw", getApplicationContext().getPackageName());
		
		msound = MediaPlayer.create(this, R.raw.metapreview);
		msound.setAudioStreamType(AudioManager.STREAM_MUSIC);
		msound.setLooping(true);*/
		
		
		try {
			
			String path = getApplicationContext().getPackageName() + "/" + R.raw.metapreview;
			Log.d("path", path);
			
			Uri uri = Uri.parse(path);
			//msound.setAudioStreamType(getApplicationContext().MODE_WORLD_READABLE);
			msound = MediaPlayer.create(Settings_Activity.this, uri);
			
		} catch (Exception e1) {
			
			e1.printStackTrace();
		} 
		
		chg_plnm.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				AlertDialog.Builder builder = new AlertDialog.Builder(Settings_Activity.this);
				builder.setTitle("Change player name");
				builder.setMessage("Enter player name");
				
				final EditText plnm = new EditText(Settings_Activity.this);
				LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
																			LinearLayout.LayoutParams.MATCH_PARENT);
				plnm.setLayoutParams(lp);
				builder.setView(plnm);
				
				builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						try {
							
							Cursor c = mh.getPlayer();
							
							if((c!=null) && (c.getCount() > 0)){
								
								if(c.moveToFirst()){
								
									do{
										
										int id = c.getColumnIndex("player_score");
										int score = c.getInt(id);
										
										int res = mh.updateName(plnm.getText().toString(), score);
										
										if(res > 1){
											
											Toast.makeText(getApplicationContext(),"Player name saved to profile!",Toast.LENGTH_SHORT).show();
										}
										
									}while(c.moveToNext());
								}
							}
							
						} catch (Exception e) {
							
							e.printStackTrace();
						}
						
						Toast.makeText(getApplicationContext(),"Player name saved to profile!",Toast.LENGTH_SHORT).show();
					}
				});
				
				builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						dialog.dismiss();
					}
				});
				
				AlertDialog alert = builder.create();  
			    alert.show();
			}
		});
		
		
		back_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intentObj = new Intent(getApplicationContext(),MainActivity.class);
				startActivity(intentObj);
				finish();
			}
		});
		
	}
}
