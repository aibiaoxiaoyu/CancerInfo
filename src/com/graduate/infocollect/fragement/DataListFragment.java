package com.graduate.infocollect.fragement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import com.graduate.cancerinfocollect.R;
import com.graduate.infocollect.activity.ItemListActivity;
import com.graduate.infocollect.db.DBHelper;
import com.graduate.infocollect.db.InfoProvider;
import com.graduate.infocollect.entity.Contact;

/**
 * @包名：com.graduate.infocollect.fragement
 * @类名：DataListFragment
 * @描述：我的数据列表
 * @作者：cmcc
 * @版本：1.0.0
 */
public class DataListFragment extends Fragment {
	private View view;
	private ListView mListview;
	private List<Contact> mList = new ArrayList<Contact>();
	private LayoutInflater mInflater;
	private ContactAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		mInflater = inflater;
		view = inflater.inflate(R.layout.fragment_contact, container, false);
		mListview = (ListView)view.findViewById(R.id.listview);
		initData();
		mListview.setOnItemLongClickListener((new OnItemLongClickListener() {
			
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				System.out.println("onItemLongClick");
				if(position == 0)
					return false;
				SetDateDialog sdt = new SetDateDialog(mList.get(position).getId());
				sdt.show(getActivity().getFragmentManager(), "datePicker");
				return false;
				
			}
		}));
		mListview.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				System.out.println("setOnItemClickListener");
				if(position == 0)
					return;
				Intent intent = new Intent(getActivity(), ItemListActivity.class);
				intent.putExtra(Contact.CONTACT, mList.get(position));
				startActivity(intent);
			}
		});
		
		return view;
	}
	
	private void initData() {
		mList.clear();
		mList.add(new Contact("-1", "time"));
		mList.addAll(DBHelper.getInstance().getContactList());
		adapter = new ContactAdapter(mList);
		mListview.setAdapter(adapter);
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		initData();
		adapter.notifyDataSetChanged();
		
	}
	
	// 创建日期选择对话框
	class SetDateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {
		private String contactID;
		
		public SetDateDialog(String contactID) {
			super();
			this.contactID = contactID;
		}
		
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Calendar 是一个抽象类，是通过getInstance()来获得实例,设置成系统默认时间
			final Calendar c = Calendar.getInstance();
			// 获取年，月，日
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int day = c.get(Calendar.DAY_OF_MONTH);
			
			DatePickerDialog dpd = new DatePickerDialog(getActivity(), this, year, month, day);
			dpd.setTitle("添加提醒");
			return dpd;
		}
		
		public void onDateSet(DatePicker view, int year, int month, int day) {
			ContentValues cv = new ContentValues();
			cv.put(InfoProvider.NOTIFY_VISTTIME, year + "-" + (month + 1) + "-" + day + "");
			cv.put(InfoProvider.NOTIFY_CONTACTID, contactID);
			DBHelper.getInstance().insert(InfoProvider.TABLE_NOTIFY, cv);
		}
	}
	
	public class ContactAdapter extends BaseAdapter {
		private List<Contact> mList;
		
		public ContactAdapter(List<Contact> mList) {
			super();
			this.mList = mList;
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mList.size();
		}
		
		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return mList.get(position);
		}
		
		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder viewHolder = new ViewHolder();
			if(convertView == null) {
				convertView = mInflater.inflate(R.layout.item_contact, null);
				viewHolder.no = ((TextView)convertView.findViewById(R.id.no));
				viewHolder.name = ((TextView)convertView.findViewById(R.id.name));
				viewHolder.sex = ((TextView)convertView.findViewById(R.id.sex));
				viewHolder.birthday = ((TextView)convertView.findViewById(R.id.birthday));
				convertView.setTag(viewHolder);
			}
			else {
				viewHolder = (ViewHolder)convertView.getTag();
			}
			Contact entity = mList.get(position);
			if(!entity.getId().equals("-1")) {
				viewHolder.no.setText(position + "");
				viewHolder.name.setText(entity.getName());
				viewHolder.sex.setText(entity.getSex() == Contact.SEX_MAN ? "男" : "女");
				viewHolder.birthday.setText(entity.getBirthday());
			}
			return convertView;
		}
		
	}
	
	private static class ViewHolder {
		private TextView no, name, sex, birthday;
	}
}
