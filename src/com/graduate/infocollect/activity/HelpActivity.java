package com.graduate.infocollect.activity;

import com.graduate.cancerinfocollect.R;

import android.os.Bundle;
import android.view.View;

/**
 * @包名：com.graduate.infocollect.activity
 * @类名：HelpActivity
 * @描述：help 界面
 * @版本：1.0.0
 * 
*/
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
