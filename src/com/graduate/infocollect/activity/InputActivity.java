package com.graduate.infocollect.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.graduate.cancerinfocollect.R;
import com.graduate.infocollect.db.DBHelper;
import com.graduate.infocollect.db.InfoProvider;

public class InputActivity extends BaseActivity {
	private EditText name, ct, psa;
	private TextView birthday;
	
	// private ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_input);
		name = (EditText)findViewById(R.id.et_name);
		ct = (EditText)findViewById(R.id.et_ct);
		psa = (EditText)findViewById(R.id.et_psa);
		birthday = (TextView)findViewById(R.id.tv_birthday);
		initData();
	}
	
	private void initData() {
		// List<String> mList = new ArrayList<String>();
		// mList.add("男");
		// mList.add("女");
		// adapter = new ArrayAdapter<String>(this,
		// android.R.layout.simple_spinner_item, mList);
		// spinner.setAdapter(adapter);
		findViewById(R.id.imBtnHelp).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(InputActivity.this, HelpActivity.class));
			}
		});
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
		saveData();
	}
	
	private void saveData() {
		String nameString = name.getText().toString();
		String ctString = ct.getText().toString();
		String psaString = psa.getText().toString();
		if(TextUtils.isEmpty(nameString)) {
			showToast("请输入姓名... ");
			return;
		}
		if(TextUtils.isEmpty(psaString) || TextUtils.isEmpty(ctString)) {
			showToast("请先输入完全 ");
			return;
		}
		ContentValues cv = new ContentValues();
		cv.put(InfoProvider.CONTACT_NAME, nameString);
		cv.put(InfoProvider.CONTACT_BIRTHDAY, birthday.getText().toString());
		// cv.put(InfoProvider.CONTACT_SEX, spinner.getSelectedItemPosition());
		DBHelper.getInstance().insert(InfoProvider.TABLE_CONTACT, cv);
		cv = new ContentValues();
		cv.put(InfoProvider.MEDICALDATA_CT, ctString);
		cv.put(InfoProvider.MEDICALDATA_PSA, psaString);
		DBHelper.getInstance().insert(InfoProvider.TABLE_MEDICALDATA, cv);
		finish();
	}
	
}
