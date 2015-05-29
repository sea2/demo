package com.smt.util.adapter;

import java.util.ArrayList;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.smt.chashizaixian.R;




public class AddFromContactsAdapter extends BaseAdapter{


	private Context mContext;
	private ArrayList<Map<String,Object>> mList;


	
	public AddFromContactsAdapter(Context context,ArrayList<Map<String, Object>> mlistHashMaps) {

		this.mContext = context;
		this.mList = mlistHashMaps;
		
		
	}
	
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return mList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public View getView(final int arg0, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		 final ViewHolder holder;
		if (convertView !=null) {
			holder = (ViewHolder) convertView.getTag();
		} else {
			
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.phonecontacts_item, arg2, false);
			holder.iv = (ImageView) convertView.findViewById(R.id.localuser_head);
			holder.name_tv=(TextView)convertView.findViewById(R.id.localuser_name);
			holder.phone_tv=(TextView)convertView.findViewById(R.id.localuser_phone);
			convertView.setTag(holder);
		}
		
		
		holder.name_tv.setText(mList.get(arg0).get("name").toString());
		holder.phone_tv.setText(mList.get(arg0).get("phone").toString());
		

		return convertView;
	}

	public static class ViewHolder {

		 ImageView iv;				
		 TextView name_tv;
		 TextView phone_tv;
    
	}
	
	// ���¼�������
		public void refreshData(ArrayList<Map<String, Object>> listItems) {
			
		    
			this.mList = listItems;
			notifyDataSetChanged();
		}
		
		// ���¼�������
				public void norefreshData(String loginid) {
					
					if (this.mList!=null) {
						this.mList.clear();
					}
					

//					this.mList =mDbHelper.queryAll(loginid);
					notifyDataSetChanged();
				}
		
		
		
		
}