package com.graduate.infocollect.fragement;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.graduate.cancerinfocollect.R;
import com.graduate.infocollect.activity.ViewItemsActivity;
import com.graduate.infocollect.db.DBHelper;
import com.graduate.infocollect.entity.Contact;

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
		adapter = new ContactAdapter(mList);
		mListview.setAdapter(adapter);
		mListview.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				if(position == 0)
					return;
				Intent intent = new Intent(getActivity(), ViewItemsActivity.class);
				startActivity(intent);
			}
		});
		return view;
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		initData();
		adapter.notifyDataSetChanged();
		
	}
	
	private void initData() {
		mList.clear();
		mList.add(new Contact("-1", "time"));
		mList.addAll(DBHelper.getInstance().getContactList());
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
