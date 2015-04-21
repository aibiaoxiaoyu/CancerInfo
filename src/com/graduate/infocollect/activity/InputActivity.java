package com.graduate.infocollect.activity;

import android.os.Bundle;
import android.view.View;

import com.graduate.cancerinfocollect.R;

public class InputActivity extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_input);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	public void onBack(View v) {
		finish();
	}
	
	public void onOK(View v) {
		finish();
	}
}
