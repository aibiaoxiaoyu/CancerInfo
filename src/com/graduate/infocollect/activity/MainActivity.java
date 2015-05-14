package com.graduate.infocollect.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.graduate.cancerinfocollect.R;
import com.graduate.infocollect.db.DBHelper;
import com.graduate.infocollect.entity.NotifyEntity;
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
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		List<NotifyEntity> mList = DBHelper.getInstance().getNeededNotifies();
		// StringBuilder sb=new StringBuilder();
		// for(NotifyEntity entity:mList){
		// sb.append(entity.get)
		// }
		if(mList.size() > 0) {
			showNotify(mList.size());
		}
	}
	
	private boolean showNotify(int size) {
		NotificationManager mNotificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
		mBuilder.setContentTitle("我的提醒")// 设置通知栏标题
				.setContentText("您有" + size + "条未完成的随访提醒")
				// .setContentIntent(getDefalutIntent(Notification.FLAG_AUTO_CANCEL))
				// // 设置通知栏点击意图
				// .setNumber(number) //设置通知集合的数量
				.setTicker("您有" + size + "条未完成的随访提醒") // 通知首次出现在通知栏，带上升动画效果的
				.setWhen(System.currentTimeMillis())// 通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
				.setPriority(Notification.PRIORITY_DEFAULT) // 设置该通知优先级
				// .setAutoCancel(true)//设置这个标志当用户单击面板就可以让通知将自动取消
				.setOngoing(false)// ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
//				.setDefaults(Notification.DEFAULT_VIBRATE)//
				// 向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合
				// Notification.DEFAULT_ALL Notification.DEFAULT_SOUND 添加声音 //
				// requires VIBRATE permission
				.setSmallIcon(R.drawable.appicon);// 设置通知小ICON
		Intent intent = new Intent(MainActivity.this, MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
		intent.putExtra("fromNotify", 1);
		PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
		mBuilder.setContentIntent(pendingIntent);
		// Notification notification = mBuilder.build();
		// notification.flags = Notification.FLAG_AUTO_CANCEL;
		mNotificationManager.notify(0, mBuilder.build());
		if(getIntent().getIntExtra("fromNotify", 0) == 1) {
			mViewPager.setCurrentItem(1);
		}
		return false;
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
