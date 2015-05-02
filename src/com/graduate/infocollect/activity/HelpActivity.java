package com.graduate.infocollect.activity;

import com.graduate.cancerinfocollect.R;

import android.os.Bundle;
import android.view.View;

public class HelpActivity extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
	}
	
	public void onBack(View v) {
		finish();
	}
	
}
