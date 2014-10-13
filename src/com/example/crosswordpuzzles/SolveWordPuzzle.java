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

public class SolveWordPuzzle extends Activity {

	TextView quest;
	EditText ans;
	Button check,back,menumain,next;
	ArrayList<Integer> qids = new ArrayList<Integer>();
	
	int max=11;
	int min=1;
	
	Random randomno = new Random();
	int no2 = randomno.nextInt(max-min)+min;
	
	MySQLiteHelper mh;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_solve_word_puzzle);

		quest = (TextView)findViewById(R.id.quest);
		ans = (EditText)findViewById(R.id.ans);
		check = (Button)findViewById(R.id.check);
		menumain = (Button)findViewById(R.id.backmain);
		next = (Button)findViewById(R.id.next);
		
		mh = MySQLiteHelper.getInstance(getApplicationContext());
		
		next.setText("Start");
		check.setEnabled(false);
		quest.setText("Click Start");
		ans.setEnabled(false);
		
		check.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//DB dbhelper = new DB(getApplicationContext(),"Numbers.db");
				String sol = mh.readsol(no2);
				if(ans.getText().toString().equals(sol.toString()))
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
				ans.setEnabled(true);
				//DB dbhelper = new DB(getApplicationContext(),"Numbers.db");
				
				no2 = randomno.nextInt(max-min)+min;
				
				if(qids.contains(mh.getword(no2))==false){
					quest.setText(mh.readquest(no2));
					ans.setText("");
					qids.add(mh.getword(no2));
				}else if(qids.contains(mh.getword(no2))==true){
					Toast.makeText(getApplicationContext(), "Question already solved !", Toast.LENGTH_SHORT).show();
				}


				/*boolean checkflag = dbhelper.returnflag();
				
				if(checkflag == true)
				{
				//if(qids.contains(dbhelper.getword(no2))==false){
					quest.setText(dbhelper.readquest(no2));
					ans.setText("");
				//	qids.add(dbhelper.getword(no2));
					dbhelper.updateflag(no2);
				//}else if(qids.contains(dbhelper.getword(no2))==true){
					//dbhelper.updateflag(no2);
				//}
				}
				else{
					Toast.makeText(getApplicationContext(), "Quiz end", Toast.LENGTH_SHORT).show();
				}*/
				
			}
		});
		
			
		menumain.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				/*DB dbhelper = new DB(getApplicationContext(),"Numbers.db");
				for(int i=min;i<max;i++)
				dbhelper.reupdateflag(i);*/
				Intent intobj = new Intent(getApplicationContext(),Play_puzzles_Activity.class);
				startActivity(intobj);
				finish();				
			}
		});
	}

}
