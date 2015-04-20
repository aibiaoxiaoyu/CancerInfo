package com.graduate.infocollect.fragement;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.graduate.cancerinfocollect.R;
import com.graduate.infocollect.entity.NotifyEntity;

public class NotifyFragment extends Fragment {
	private View view;
	private ListView mListview;
	private List<NotifyEntity> mList = new ArrayList<NotifyEntity>();
	private LayoutInflater mInflater;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		mInflater = inflater;
		view = inflater.inflate(R.layout.fragment_notify, container, false);
		mListview = (ListView)view.findViewById(R.id.listview);
		// initView();
		// initData();
		mList.add(new NotifyEntity("-1", "time"));
		mListview.setAdapter(new NotifyAdapter(mList));
		return view;
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
				// viewHolder.conv_name =
				// ((TextView)convertView.findViewById(R.id.conv_name));
				// viewHolder.conv_note =
				// ((TextView)convertView.findViewById(R.id.conv_note));
				// viewHolder.conv_date =
				// ((TextView)convertView.findViewById(R.id.conv_date));
				// viewHolder.conv_portrait =
				// ((ImageView)convertView.findViewById(R.id.conv_portrait));
				convertView.setTag(viewHolder);
			}
			else {
				viewHolder = (ViewHolder)convertView.getTag();
			}
			return convertView;
		}
		
	}
	
	private static class ViewHolder {
		private TextView conv_name;
		private TextView conv_note;
		private TextView conv_date;
		private ImageView conv_portrait;
	}
}
