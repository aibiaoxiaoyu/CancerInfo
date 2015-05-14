package com.graduate.infocollect.fragement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.graduate.infocollect.entity.NotifyEntity;

public class NotifyFragment extends Fragment {
	private View view;
	private ListView mListview;
	private List<NotifyEntity> mList = new ArrayList<NotifyEntity>();
	private LayoutInflater mInflater;
	private Map<String, Contact> map;
	private NotifyAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		mInflater = inflater;
		view = inflater.inflate(R.layout.fragment_notify, container, false);
		mListview = (ListView)view.findViewById(R.id.listview);
		// mList.clear();
		// mList.add(new NotifyEntity("-1", "-1", "time"));
		// mList.addAll(DBHelper.getInstance().getNotifyList());
		adapter = new NotifyAdapter(mList);
		mListview.setAdapter(adapter);
		mListview.setOnItemLongClickListener(new OnItemLongClickListener() {
			
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
				// TODO Auto-generated method stub
				
				// TODO Auto-generated method stub
				final String notifyid = mList.get(position).getNo();
				if(notifyid.equals("-1")) {
					return true;
				}
				final CharSequence[] items = {"删除该条提醒" }; // 设置选择内容
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				builder.setItems(items, new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						mList.remove(position);
						adapter.notifyDataSetChanged();
						DBHelper.getInstance().deleteNotifyEntity(notifyid);
						dialog.dismiss();
					}
				});
				builder.show();
				return true;
				
			}
		});
		return view;
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(adapter != null) {
			mList.clear();
			mList.add(new NotifyEntity("-1", "-1", "time"));
			mList.addAll(DBHelper.getInstance().getNotifyList());
			map = DBHelper.getInstance().getContactMap();
			adapter.notifyDataSetChanged();
		}
	}
	
	public class NotifyAdapter extends BaseAdapter {
		private List<NotifyEntity> mList;
		
		public NotifyAdapter(List<NotifyEntity> mList) {
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
				convertView = mInflater.inflate(R.layout.item_notify, null);
				viewHolder.no = ((TextView)convertView.findViewById(R.id.no));
				viewHolder.name = ((TextView)convertView.findViewById(R.id.name));
				viewHolder.sex = ((TextView)convertView.findViewById(R.id.sex));
				viewHolder.birthday = ((TextView)convertView.findViewById(R.id.birthday));
				viewHolder.vistTime = ((TextView)convertView.findViewById(R.id.vistTime));
				convertView.setTag(viewHolder);
			}
			else {
				viewHolder = (ViewHolder)convertView.getTag();
			}
			NotifyEntity entity = mList.get(position);
			if(entity != null && !entity.getNo().equals("-1")) {
				viewHolder.no.setText(position + "");
				viewHolder.name.setText(map.get(entity.getContacID()).getName());
				viewHolder.sex.setText(map.get(entity.getContacID()).getSex() == Contact.SEX_MAN ? "男" : "女");
				viewHolder.vistTime.setText(entity.getVisitTime());
				viewHolder.birthday.setText(map.get(entity.getContacID()).getBirthday());
			}
			return convertView;
		}
		
	}
	
	private static class ViewHolder {
		private TextView no, name, sex, birthday, vistTime;
	}
}
