package com.graduate.infocollect.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.graduate.cancerinfocollect.R;
import com.graduate.infocollect.db.DBHelper;
import com.graduate.infocollect.entity.Contact;
import com.graduate.infocollect.entity.MedicalData;

/**
 * @包名：com.graduate.infocollect.activity
 * @类名：ItemListActivity
 * @描述：患者 医疗数据的列表
 * @作者：cmcc
 * @时间：May 9, 201510:23:41 PM
 * @版本：1.0.0
 */
public class ItemListActivity extends BaseActivity {
	private ListView mListview;
	private List<MedicalData> mList = new ArrayList<MedicalData>();
	private TextView tv_name;
	private ItemsAdapter adapter;
	private Contact contact;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_itemlist);
		initView();
		initData();
	}
	
	private void initView() {
		tv_name = (TextView)findViewById(R.id.title_name);
		mListview = (ListView)findViewById(R.id.listview);
		mListview.setOnItemLongClickListener(new OnItemLongClickListener() {
			
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
				final String contactId = mList.get(position).getContactId();
				if(contactId.equals("-1")) {
					return true;
				}
				final CharSequence[] items = {"删除该条提醒", "编辑该条数据" }; // 设置选择内容
				AlertDialog.Builder builder = new AlertDialog.Builder(ItemListActivity.this);
				builder.setItems(items, new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						if(which == 0) {
							DBHelper.getInstance().deleteMedicalData(mList.get(position).getId());
							mList.remove(position);
							adapter.notifyDataSetChanged();
						}
						else {
							Intent intent = new Intent(ItemListActivity.this, AddItemActivity.class);
							intent.putExtra("id", contact.getId());
							intent.putExtra("medicalid", mList.get(position).getId());
							intent.putExtra(MedicalData.MEDICALDATA, mList.get(position));
							startActivity(intent);
						}
						dialog.dismiss();
					}
				});
				builder.show();
				
				return true;
			}
			
		});
	}
	
	private void initData() {
		contact = (Contact)getIntent().getExtras().get(Contact.CONTACT);
		tv_name.setText(contact.getName());
		
	}
	
	/**
	 * @方法名：onShowProfile
	 * @描述：显示患者详情
	 * @param v
	 * @输出：void
	 * @作者：cmcc
	 */
	public void onShowProfile(View v) {
		Intent intent = new Intent(ItemListActivity.this, ProfileActivity.class);
		intent.putExtra(Contact.CONTACT, contact);
		startActivity(intent);
	}
	
	/**
	 * @方法名：onAddItem
	 * @描述：新建数据
	 * @param v
	 * @输出：void
	 * @作者：cmcc
	 */
	public void onAddItem(View v) {
		Intent intent = new Intent(ItemListActivity.this, AddItemActivity.class);
		intent.putExtra("id", contact.getId());
		startActivity(intent);
	}
	
	/**
	 * @方法名：onShowChart
	 * @描述：显示chart
	 * @param v
	 * @输出：void
	 * @作者：cmcc
	 */
	public void onShowChart(View v) {
		if(mList.size() <= 1) {
			return;
		}
		Intent intent = new Intent(ItemListActivity.this, ChartActivity.class);
		intent.putExtra("id", contact.getId());
		startActivity(intent);
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		initAdapter();
		adapter.notifyDataSetChanged();
		
	}
	
	private void initAdapter() {
		mList.clear();
		mList.add(new MedicalData("-1"));
		mList.addAll(DBHelper.getInstance().getMedicalList(contact.getId()));
		adapter = new ItemsAdapter(ItemListActivity.this, mList);
		mListview.setAdapter(adapter);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	public class ItemsAdapter extends BaseAdapter {
		private List<MedicalData> mList;
		private LayoutInflater mInflater;
		
		public ItemsAdapter(Context context, List<MedicalData> mList) {
			super();
			this.mInflater = LayoutInflater.from(context);
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
				convertView = mInflater.inflate(R.layout.item_medical, null);
				viewHolder.no = ((TextView)convertView.findViewById(R.id.no));
				viewHolder.psa = ((TextView)convertView.findViewById(R.id.psa));
				viewHolder.ca = ((TextView)convertView.findViewById(R.id.ca));
				viewHolder.afp = ((TextView)convertView.findViewById(R.id.afp));
				convertView.setTag(viewHolder);
			}
			else {
				viewHolder = (ViewHolder)convertView.getTag();
			}
			MedicalData entity = mList.get(position);
			if(!entity.getContactId().equals("-1")) {
				viewHolder.no.setText(position + "");
				// System.out.println(entity.getPSA()+"");
				viewHolder.psa.setText(entity.getPSA());
				viewHolder.ca.setText(entity.getCA());
				viewHolder.afp.setText(entity.getAFP());
			}
			return convertView;
		}
		
	}
	
	private static class ViewHolder {
		private TextView no, psa, ca, afp;
	}
}
