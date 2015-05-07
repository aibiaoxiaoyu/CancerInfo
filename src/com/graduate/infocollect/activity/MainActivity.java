package com.graduate.infocollect.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.graduate.cancerinfocollect.R;
import com.graduate.infocollect.fragement.DataListFragment;
import com.graduate.infocollect.fragement.NotifyFragment;

/**
 * @包名：com.graduate.infocollect.activity
 * @类名：MainActivity
 * @描述：主界面
 * @作者：cmcc
 * @版本：1.0.0
 */
public class MainActivity extends FragmentActivity {
	private ViewPager mViewPager;
	private List<Fragment> mFragments;
	private FragmentPagerAdapter mAdapter;
	private ImageView new_data;
	
	DataListFragment dataFragment = new DataListFragment();// 我的数据列表
	NotifyFragment notifyFragment = new NotifyFragment();// 我的提醒
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initListener();
	}
	
	private void initView() {
		mViewPager = (ViewPager)findViewById(R.id.vp_main);
		new_data = (ImageView)findViewById(R.id.new_data);
		mFragments = new ArrayList<Fragment>();
	
		
		mFragments.add(dataFragment);
		mFragments.add(notifyFragment);
		mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
			
			@Override
			public int getCount() {
				return mFragments.size();
			}
			
			@Override
			public Fragment getItem(int arg0) {
				return mFragments.get(arg0);
			}
		};
		
		mViewPager.setAdapter(mAdapter);
	}
	
	private void initListener() {
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				switch(arg0) {
					case 0:
						((TextView)findViewById(R.id.tv_tab_name)).setText("我的数据列表");
						((ImageView)findViewById(R.id.iv_list)).setImageResource(R.drawable.public_icon_tabbar_more_pre);
						((ImageView)findViewById(R.id.iv_set)).setImageResource(R.drawable.public_icon_tabbar_msg_nm);
						new_data.setVisibility(View.VISIBLE);
						break;
					case 1:
						((TextView)findViewById(R.id.tv_tab_name)).setText("我的提醒");
						((ImageView)findViewById(R.id.iv_list)).setImageResource(R.drawable.public_icon_tabbar_more_nm);
						((ImageView)findViewById(R.id.iv_set)).setImageResource(R.drawable.public_icon_tabbar_msg_pre);
						new_data.setVisibility(View.GONE);
						notifyFragment.onResume();
						break;
					
					default:
						break;
				}
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		findViewById(R.id.list_tab_layout).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mViewPager.setCurrentItem(0);
			}
		});
		
		findViewById(R.id.notify_tab_layout).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mViewPager.setCurrentItem(1);
			}
		});
		findViewById(R.id.new_data).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MainActivity.this, InputActivity.class));
			}
		});
	}
	
}
