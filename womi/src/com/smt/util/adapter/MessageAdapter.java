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

public class MessageAdapter extends BaseAdapter {

	private Context mContext;
	private ArrayList<Map<String, Object>> mList;

	public MessageAdapter(Context context,
			ArrayList<Map<String, Object>> mlistHashMaps) {

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
	public View getView(final int position, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		final ViewHolder holder;
		if (convertView != null) {
			holder = (ViewHolder) convertView.getTag();
		} else {

			holder = new ViewHolder();
			Map<String, Object> map = this.mList.get(position);
			if (map.get("friend_or_mi").toString().equals("0")) {
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.message_left_item, arg2, false);
				holder.friend_image = (ImageView) convertView
						.findViewById(R.id.friend_image);
				holder.friend_message = (TextView) convertView
						.findViewById(R.id.friend_message);
				holder.friend_message.setText(map.get("message").toString());
				convertView.setTag(holder);

			} else if (map.get("friend_or_mi").toString().equals("1")) {
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.message_right_item, arg2, false);
				holder.mi_image = (ImageView) convertView
						.findViewById(R.id.mi_image);
				holder.mi_message = (TextView) convertView
						.findViewById(R.id.mi_message);
				holder.mi_message.setText(map.get("message").toString());
				convertView.setTag(holder);

			}

		}

		// String url=mList.get(arg0).get("portrait").toString();
		//
		//
		// String alias=mList.get(arg0).get("alias").toString();
		//
		// if (alias.equals("")) {
		//
		// holder.name_tv.setText(mList.get(arg0).get("username").toString());
		//
		// }
		// else {
		// holder.name_tv.setText(alias);
		// }
		//
		// String content=mList.get(arg0).get("content").toString().replace(" ",
		// "");
		// holder.time_tv.setText(mList.get(arg0).get("add_time").toString());
		//
		// holder.tall_tv.setText(content);
		//
		//
		//
		//
		//
		//
		// int unread=(Integer)mList.get(arg0).get("unread");
		//
		// if (unread==0) {
		//
		// holder.num_tv.setVisibility(View.GONE);
		//
		//
		// }
		// else {
		//
		// holder.num_tv.setVisibility(View.VISIBLE);
		// holder.num_tv.setText(""+unread);
		//
		//
		// }
		return convertView;
	}

	public static class ViewHolder {

		ImageView friend_image;
		ImageView mi_image;
		TextView friend_message;
		TextView mi_message;
	}

	// 重新加载数据
	public void refreshData(ArrayList<Map<String, Object>> listItems) {

		this.mList = listItems;
		notifyDataSetChanged();
	}

	// 重新加载数据
	public void norefreshData(String loginid) {

		if (this.mList != null) {
			this.mList.clear();
		}

		// this.mList =mDbHelper.queryAll(loginid);
		notifyDataSetChanged();
	}

}