package com.graduate.infocollect.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class BaseActivity extends Activity {
	public void showToast(String text) {
		Toast.makeText(BaseActivity.this, text, Toast.LENGTH_SHORT).show();
	}
	
	public void onBack(View v) {
		finish();
	}

}
