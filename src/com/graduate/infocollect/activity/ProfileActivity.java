package com.graduate.infocollect.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.graduate.cancerinfocollect.R;
import com.graduate.infocollect.entity.Contact;

/**
 * @包名：com.graduate.infocollect.activity
 * @类名：ProfileActivity
 * @描述：患者详情
 * @版本：1.0.0
 */
public class ProfileActivity extends BaseActivity {
	private TextView name, sex, birth, smoke, drink, ct, history;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		initView();
		initData();
	}
	
	private void initView() {
		name = (TextView)findViewById(R.id.name);
		sex = (TextView)findViewById(R.id.sex);
		birth = (TextView)findViewById(R.id.birth);
		smoke = (TextView)findViewById(R.id.smoke);
		drink = (TextView)findViewById(R.id.drink);
		ct = (TextView)findViewById(R.id.ct);
		history = (TextView)findViewById(R.id.history);
	}
	
	private void initData() {
		Contact contact = (Contact)getIntent().getExtras().get(Contact.CONTACT);
		name.setText(name.getText().toString() + contact.getName());
		sex.setText(sex.getText().toString() + (contact.getSex() == Contact.SEX_MAN ? "男" : "女"));
		birth.setText(birth.getText().toString() + contact.getBirthday());
		smoke.setText(smoke.getText().toString() + (contact.isSmork() ? "是" : "否"));
		drink.setText(drink.getText().toString() + (contact.isDrink() ? "是" : "否"));
		ct.setText(ct.getText().toString() + (contact.isCtNormal() ? "正常" : "异常"));
		history.setText(history.getText().toString() + (TextUtils.isEmpty(contact.getHistory()) ? "无" : contact.getHistory()));
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
}
