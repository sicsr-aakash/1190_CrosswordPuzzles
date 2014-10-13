package com.example.crosswordpuzzles;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SolveNumberPuzzle extends Activity {

	TextView clue;
	EditText answer;
	Button check,back,menu,next;
	ArrayList<Integer> qids = new ArrayList<Integer>();
	
	int max=11;
	int min=1;
	
	Random randomno = new Random();
	int no2 = randomno.nextInt(max-min)+min;
	String val2 = String.valueOf(no2);
	
	MySQLiteHelper mh;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_solve_number_puzzle);

		clue = (TextView)findViewById(R.id.clue);
		answer = (EditText)findViewById(R.id.answer);
		check = (Button)findViewById(R.id.check);
		menu = (Button)findViewById(R.id.menumain);
		next = (Button)findViewById(R.id.next);
		
		mh = MySQLiteHelper.getInstance(getApplicationContext());
		
		next.setText("Start");
		check.setEnabled(false);
		clue.setText("Click on Start");
		answer.setEnabled(false);
		
		check.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//DB dbhelper = new DB(getApplicationContext(),"Numbers.db");
				String sol = mh.readans(no2);

				if(answer.getText().toString().equals(sol.toString()))
					Toast.makeText(getApplicationContext(), "Correct answer!", Toast.LENGTH_SHORT).show();
				else
					Toast.makeText(getApplicationContext(), "Wrong answer!", Toast.LENGTH_SHORT).show();
			}
		});
		
		next.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				next.setText("Next");
				check.setEnabled(true);
				answer.setEnabled(true);
				//DB dbhelper = new DB(getApplicationContext(),"Numbers.db");
				
				no2 = randomno.nextInt(max-min)+min;
				
				if(qids.contains(mh.getquest(no2))==false){
					clue.setText(mh.read(no2));
					answer.setText("");
					qids.add(mh.getquest(no2));
				}else if(qids.contains(mh.getquest(no2))==true){
					Toast.makeText(getApplicationContext(), "Question already solved !", Toast.LENGTH_SHORT).show();
				}
				
				
				/*if(pids.isEmpty()==false){
					no2 = randomno.nextInt(max-min)+min;
					if(pids.contains(dbhelper.getquest(no2))==true){
						clue.setText(dbhelper.read(no2));
						answer.setText("");
						pids.remove(dbhelper.getquest(no2));
					}else{
						Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
					}
					
				}
				else{
					Toast.makeText(getApplicationContext(), "End of quiz", Toast.LENGTH_SHORT).show();
				}*/
			}
		});
		
		
		menu.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intobj = new Intent(getApplicationContext(),Play_puzzles_Activity.class);
				startActivity(intobj);
				finish();
				
			}
		});
	}

}
