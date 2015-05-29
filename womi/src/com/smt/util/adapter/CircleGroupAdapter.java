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

public class CircleGroupAdapter extends BaseAdapter{

	private Context mContext;
	private ArrayList<Map<String,Object>> mList;

	public CircleGroupAdapter(Context context,ArrayList<Map<String, Object>> mlistHashMaps) {

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
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item_circle_group_big, arg2, false);
			holder.iv = (ImageView) convertView.findViewById(R.id.info_head);
			holder.group_name=(TextView)convertView.findViewById(R.id.group_name);
			convertView.setTag(holder);
		}
		holder.group_name.setText(mList.get(arg0).get("key_name").toString());
		holder.iv .setImageResource(R.drawable.ic_discover_my_groupunion);
		
//		String url=mList.get(arg0).get("portrait").toString();	
//	
//		
//		String alias=mList.get(arg0).get("alias").toString();
//		
//		if (alias.equals("")) {
//		
//			holder.name_tv.setText(mList.get(arg0).get("username").toString());
//			
//		}
//		else {
//			holder.name_tv.setText(alias);
//		}
//		
//		String content=mList.get(arg0).get("content").toString().replace(" ", "");
//		holder.time_tv.setText(mList.get(arg0).get("add_time").toString());
//		
//		holder.tall_tv.setText(content);
//		
//		
//		
//
//	
//		
//		int unread=(Integer)mList.get(arg0).get("unread");
//		
//		if (unread==0) {
//			
//		holder.num_tv.setVisibility(View.GONE);	
//			
//			
//		}
//       else {
//    	 
//    	 holder.num_tv.setVisibility(View.VISIBLE);
//    	 holder.num_tv.setText(""+unread);
//    	 
//		
//	     }
		return convertView;
	}

	public static class ViewHolder {

		 ImageView iv;				
		 TextView group_name;
	}
	
	// 重新加载数据
		public void refreshData(ArrayList<Map<String, Object>> listItems) {
			
		    
			this.mList = listItems;
			notifyDataSetChanged();
		}
		
		// 重新加载数据
		public void norefreshData(String loginid) {
			
			if (this.mList!=null) {
				this.mList.clear();
			}
			
//			this.mList =mDbHelper.queryAll(loginid);
			notifyDataSetChanged();
		}
}