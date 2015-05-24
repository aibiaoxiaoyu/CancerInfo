package com.graduate.infocollect.activity;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.graduate.cancerinfocollect.R;
import com.graduate.infocollect.db.DBHelper;
import com.graduate.infocollect.db.InfoProvider;

/**
 * @包名：com.graduate.infocollect.activity
 * @类名：InputActivity
 * @描述：新建数据界面
 * @作者：cmcc
 * @版本：1.0.0
 */
public class InputActivity extends BaseActivity {
	private EditText et_name, et_psa, et_ca, et_afp, et_history;
	private CheckBox cb_smoke, cb_drink, cb_normal;
	private TextView birthday;
	private RadioButton rbtnMan;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_input);
		initView();
		initListener();
	}
	
	/**
	 * @方法名：initView
	 * @描述：初始化布局
	 * @输出：void
	 */
	private void initView() {
		et_name = (EditText)findViewById(R.id.et_name);
		et_history = (EditText)findViewById(R.id.et_history);
		et_psa = (EditText)findViewById(R.id.et_psa);
		et_ca = (EditText)findViewById(R.id.et_ca);
		et_afp = (EditText)findViewById(R.id.et_afp);
		cb_normal = (CheckBox)findViewById(R.id.cb_normal);
		rbtnMan = (RadioButton)findViewById(R.id.rbtnMan);
		cb_smoke = (CheckBox)findViewById(R.id.cb_smoke);
		cb_drink = (CheckBox)findViewById(R.id.cb_drink);
		birthday = (TextView)findViewById(R.id.tv_birthday);
	}
	
	// 设置日期事件处理方法
	public void onSetDateClick(View v) {
		SetDateDialog sdt = new SetDateDialog();
		sdt.show(getFragmentManager(), "datePicker");
	}
	
	/**
	 * @方法名：saveData
	 * @描述：保存数据
	 * @输出：void
	 * @作者：cmcc
	 */
	private void saveData() {
		String nameString = et_name.getText().toString();
		String psaString = et_psa.getText().toString();
		String caString = et_ca.getText().toString();
		String afpString = et_afp.getText().toString();
		if(TextUtils.isEmpty(nameString)) {
			showToast("请输入姓名... ");
			return;
		}
		if(TextUtils.isEmpty(psaString) || TextUtils.isEmpty(caString) || TextUtils.isEmpty(afpString)) {
			showToast("请先输入完全 ");
			return;
		}
		ContentValues cv = new ContentValues();
		cv.put(InfoProvider.CONTACT_NAME, nameString);// name
		cv.put(InfoProvider.CONTACT_SEX, rbtnMan.isChecked());// sex
		cv.put(InfoProvider.CONTACT_BIRTHDAY, birthday.getText().toString());// birthday
		cv.put(InfoProvider.CONTACT_IS_CT_NORMAL, cb_normal.isChecked());// ct
		cv.put(InfoProvider.CONTACT_IS_SMOKE, cb_smoke.isChecked());// smoke
		cv.put(InfoProvider.CONTACT_IS_DRINK, cb_drink.isChecked());// drink
		cv.put(InfoProvider.CONTACT_HISTORY, et_history.getText().toString());// 病史
		int id = (int)DBHelper.getInstance().insert(InfoProvider.TABLE_CONTACT, cv);// id
		
		cv = new ContentValues();
		cv.put(InfoProvider.MEDICALDATA_CONTACT_ID, id);
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
				startActivity(new Intent(InputActivity.this, HelpActivity.class));
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
	
	// 创建日期选择对话框
	class SetDateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {
		
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Calendar 是一个抽象类，是通过getInstance()来获得实例,设置成系统默认时间
			final Calendar c = Calendar.getInstance();
			// 获取年，月，日
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int day = c.get(Calendar.DAY_OF_MONTH);
			
			DatePickerDialog dpd = new DatePickerDialog(getActivity(), this, year, month, day);
			dpd.setTitle("设置生日");
			return dpd;
		}
		
		public void onDateSet(DatePicker view, int year, int month, int day) {
			StringBuilder sb = new StringBuilder();
			sb.append(year + "-");
			if(month <= 9) {
				sb.append("0" + (month + 1));
			}
			else {
				sb.append(month + 1);
			}
			sb.append("-");
			if(day <= 9) {
				sb.append("0" + day);
			}
			else {
				sb.append(day);
			}
			birthday.setText(sb.toString());
		}
	}
	
}
