package com.smt.util.adapter;

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
import android.widget.TextView;

import com.smt.chashizaixian.R;

public class MimeCircleAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<? extends Map<String, ?>> data;

    public MimeCircleAdapter(Context context,List<Map<String,Object>> list)
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
    		convertView = mInflater.inflate(R.layout.mime_circle_item, null);
    		holder.circleimg_iv=(ImageView)convertView.findViewById(R.id.circleiv);
    		holder.circle_biaoshi=(ImageView)convertView.findViewById(R.id.biaoshi);
    		holder.circlename_tv=(TextView)convertView.findViewById(R.id.circlename);
    		holder.circlenum_tv=(TextView)convertView.findViewById(R.id.circle_num);
    		holder.address_tv=(TextView)convertView.findViewById(R.id.addresstv);
    		holder.circlecontent_tv=(TextView)convertView.findViewById(R.id.content);
    		
    		convertView.setTag(holder);
    		convertView.setId(position);
    	}
    	else
    	{
    		holder = (ViewHolder) convertView.getTag();
    	}
    	holder.circlename_tv.setText(data.get(position).get("key_name").toString());
    
    	holder.circlename_tv.setOnClickListener(new OnClickListener() {
			
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
    	public ImageView circleimg_iv;    	   
    	public TextView circlename_tv;
    	public TextView circlenum_tv;
    	public TextView address_tv;
    	public TextView circlecontent_tv; 
    	public ImageView circle_biaoshi;


    }
    
}
