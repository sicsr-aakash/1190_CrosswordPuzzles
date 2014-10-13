package com.example.crosswordpuzzles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

public class Play_puzzles_Activity extends ActionBarActivity {

	private Button backbtn,solvewordbtn,solvenumbtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.play_puzzles);
		
		backbtn = (Button)findViewById(R.id.puzback_btn);
		solvewordbtn = (Button)findViewById(R.id.guessword_btn);
		solvenumbtn = (Button)findViewById(R.id.findnum_btn);
		
		backbtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intentObj = new Intent(getApplicationContext(),MainActivity.class);
				startActivity(intentObj);
				finish();
			}
		});
		
		solvewordbtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intentObj1 = new Intent(getApplicationContext(),SolveWordPuzzle.class);
				startActivity(intentObj1);
				finish();
			}
		});
		
		solvenumbtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intentObj2 = new Intent(getApplicationContext(),SolveNumberPuzzle.class);
				startActivity(intentObj2);
				finish();
			}
		});
	}
}
