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




public class PhotoAdapter extends BaseAdapter{


	private Context mContext;
	private ArrayList<Map<String,Object>> mList;


	
	public PhotoAdapter(Context context,ArrayList<Map<String, Object>> mlistHashMaps) {

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
			convertView = LayoutInflater.from(mContext).inflate(R.layout.photo_item, arg2, false);
			holder.iv = (ImageView) convertView.findViewById(R.id.photo_head);
			holder.day_tv=(TextView)convertView.findViewById(R.id.photo_date_day);
		    holder.month_tv=(TextView)convertView.findViewById(R.id.photo_date_month);
		    holder.content_tv=(TextView)convertView.findViewById(R.id.photo_name);
		    holder.num_tv=(TextView)convertView.findViewById(R.id.photo_num);
			convertView.setTag(holder);
		}
		
		
		holder.content_tv.setText(mList.get(arg0).get("key_name").toString());
		
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
		 TextView day_tv;
         TextView month_tv;
         TextView content_tv;
         TextView num_tv;
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
					

//					this.mList =mDbHelper.queryAll(loginid);
					notifyDataSetChanged();
				}
		
		
		
		
}