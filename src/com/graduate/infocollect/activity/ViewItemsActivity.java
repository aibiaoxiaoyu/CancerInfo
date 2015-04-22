package com.graduate.infocollect.activity;

import com.graduate.cancerinfocollect.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class ViewItemsActivity extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewitems);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	public void onBack(View v) {
		finish();
	}
	
}
