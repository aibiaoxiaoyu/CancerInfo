package com.graduate.infocollect;

import android.app.Application;

public class InfoCollectApplication extends Application {
	private static InfoCollectApplication mAppInstance = null;
	
	@Override
	public void onCreate() {
		super.onCreate();
		init();
	}
	
	// 初始化
	private void init() {
		mAppInstance = this;
	}
	
	public static InfoCollectApplication getInstatce() {
		return mAppInstance;
	}
}
