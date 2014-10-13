package com.example.crosswordpuzzles;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CluelistActivityMainActivity extends Activity {

	private Button back_btn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cluelist_fragment_main);
		
		back_btn = (Button)findViewById(R.id.btn_back);
		
		back_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				finish();
			}
		});
	}
}
