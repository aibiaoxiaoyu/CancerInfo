package com.graduate.infocollect.activity;

import android.app.Activity;
import android.os.Bundle;

public abstract class BaseActivity  extends Activity{

	@Override
	protected abstract void onCreate(Bundle savedInstanceState);

	@Override
	protected abstract void onDestroy();
	
}
