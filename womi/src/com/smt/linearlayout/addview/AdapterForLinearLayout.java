package com.smt.linearlayout.addview;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smt.chashizaixian.R;

public class AdapterForLinearLayout extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<? extends Map<String, ?>> data;

    public AdapterForLinearLayout(Context context,List<Map<String,Object>> list)
    {
    	this.data = list;
    	this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
    	ViewHolder	holder;
    	if(convertView == null)
    	{
    		holder = new ViewHolder();
    		convertView = mInflater.inflate(R.layout.mime_share_item, null);
    		holder.username_tv = (TextView) convertView.findViewById(R.id.username);
    		holder.contenttime_tv = (TextView) convertView.findViewById(R.id.contenttime);
    		holder.content_tv = (TextView) convertView.findViewById(R.id.content);
    		holder.sharecontent_tv = (TextView) convertView.findViewById(R.id.share_content);
    		holder.fromcircle_tv = (TextView) convertView.findViewById(R.id.circlefrom);
    		holder.circlename_tv = (TextView) convertView.findViewById(R.id.circlename);
    		holder.good_tv = (TextView) convertView.findViewById(R.id.good_num);
    		holder.comment_tv = (TextView) convertView.findViewById(R.id.comment_num);
    		holder.goodname_tv = (TextView) convertView.findViewById(R.id.share_names);
    		holder.userimg_iv=(ImageView)convertView.findViewById(R.id.iv);
    		holder.shareimg_iv=(ImageView)convertView.findViewById(R.id.share_img);
    		holder.comgood_iv=(ImageView)convertView.findViewById(R.id.comment_good_iv);
    		holder.contentimg_ll=(LinearLayout)convertView.findViewById(R.id.image_container);
    		holder.comment_ll=(LinearLayout)convertView.findViewById(R.id.comment_container);
    		convertView.setTag(holder);
    		convertView.setId(position);
    	}
    	else
    	{
    		holder = (ViewHolder) convertView.getTag();
    	}
    	holder.username_tv.setText(data.get(position).get("key_name").toString());
    
    	holder.username_tv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			Log.v("ttttttttttttttttttttttttt", data.get(position).get("key_name").toString());
			}
		});
        return convertView;
    }

    public class ViewHolder
    {
    	public ImageView userimg_iv;
    	public TextView username_tv;
    	public TextView contenttime_tv;
    	public TextView content_tv;
    	public LinearLayout contentimg_ll; 
    	public ImageView shareimg_iv;
    	public TextView sharecontent_tv;
    	public TextView fromcircle_tv;
    	public TextView circlename_tv;
    	public TextView good_tv;
    	public TextView comment_tv;
    	public ImageView comgood_iv;
    	public TextView goodname_tv;
    	public LinearLayout comment_ll;

    }
    
}
