package com.graduate.infocollect.activity;

import com.graduate.cancerinfocollect.R;
import com.graduate.infocollect.db.DBHelper;
import com.graduate.infocollect.db.InfoProvider;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * @包名：com.graduate.infocollect.activity
 * @类名：AddItemActivity
 * @描述：新建记录
 * @版本：1.0.0
 */
public class AddItemActivity extends BaseActivity {
	private EditText et_psa, et_ca, et_afp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_additem);
		initView();
		initListener();
	}
	
	/**
	 * @方法名：initView
	 * @描述：初始化布局
	 * @输出：void
	 */
	private void initView() {
		et_psa = (EditText)findViewById(R.id.et_psa);
		et_ca = (EditText)findViewById(R.id.et_ca);
		et_afp = (EditText)findViewById(R.id.et_afp);
	}
	
	/**
	 * @方法名：saveData
	 * @描述：保存数据
	 * @输出：void
	 * @作者：cmcc
	 */
	private void saveData() {
		String psaString = et_psa.getText().toString();
		String caString = et_ca.getText().toString();
		String afpString = et_afp.getText().toString();
		if(TextUtils.isEmpty(psaString) || TextUtils.isEmpty(caString) || TextUtils.isEmpty(afpString)) {
			showToast("请先输入完全 ");
			return;
		}
		ContentValues cv = new ContentValues();
		cv.put(InfoProvider.MEDICALDATA_CONTACT_ID, getIntent().getStringExtra("id"));
		cv.put(InfoProvider.MEDICALDATA_PSA, psaString);
		cv.put(InfoProvider.MEDICALDATA_CA, caString);
		cv.put(InfoProvider.MEDICALDATA_AFP, afpString);
		DBHelper.getInstance().insert(InfoProvider.TABLE_MEDICALDATA, cv);
		finish();
	}
	
	/**
	 * @方法名：initListener
	 * @描述：帮助按钮的监听
	 * @输出：void
	 * @作者：cmcc
	 */
	private void initListener() {
		
		findViewById(R.id.imBtnHelp).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(AddItemActivity.this, HelpActivity.class));
			}
		});
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	/**
	 * @方法名：onBack
	 * @描述：返回
	 * @param v
	 * @输出：void
	 */
	public void onBack(View v) {
		finish();
	}
	
	/**
	 * @方法名：onOK
	 * @描述：保存数据
	 * @param v
	 * @输出：void
	 */
	public void onOK(View v) {
		saveData();
	}
}
