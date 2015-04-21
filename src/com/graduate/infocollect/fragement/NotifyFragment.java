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
		mList.add(new NotifyEntity("-1", "-1", "time"));
		mList.add(new NotifyEntity("0", "-1", "time"));
		mList.add(new NotifyEntity("1", "-1", "time"));
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
			if(!entity.getNo().equals("-1")) {
				viewHolder.no.setText(position + "");
				viewHolder.name.setText(entity.getVisitTime());
				viewHolder.vistTime.setText(entity.getVisitTime());
			}
			return convertView;
		}
		
	}
	
	private static class ViewHolder {
		private TextView no, name, sex, birthday, vistTime;
	}
}
