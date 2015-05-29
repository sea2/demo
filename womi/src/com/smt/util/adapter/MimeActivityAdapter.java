package com.smt.util.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smt.chashizaixian.R;
import com.smt.config.Constants;
import com.smt.imageload.util.ImageLoader;

public class MimeActivityAdapter extends BaseAdapter
{
    
    private LayoutInflater mInflater;
    private List<? extends Map<String, ?>> data;
    private Context mContext;
    private View view[] = null;
    
    public MimeActivityAdapter(Context context, List<Map<String, Object>> list)
    {
        this.data = list;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mContext = context;
        Constants.mImageLoader=new ImageLoader(context);
    }
    
    class ImageData
    {
        public long imageID;
        public String imageURL;
        
        public ImageData(long imageID, String imageURL)
        {
            super();
            this.imageID = imageID;
            this.imageURL = imageURL;
        }
    }
    
    @Override
    public int getCount()
    {
        return data.size();
    }
    
    @Override
    public Object getItem(int position)
    {
        return position;
    }
    
    @Override
    public long getItemId(int position)
    {
        return position;
    }
    
    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        final ViewHolder holder;
        if (convertView == null)
        {
            holder = new ViewHolder();
            
            convertView = LayoutInflater.from(mContext).inflate(R.layout.mime_activity_item, parent, false);
            holder.username_tv=(TextView)convertView.findViewById(R.id.username);
            convertView.setTag(holder);
            convertView.setId(position);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        
        holder.username_tv.setText(data.get(position).get("key_name").toString());
        
        
        
        
        return convertView;
    }
    
    public class ViewHolder
    {
       
        public TextView username_tv;
     
    }
}
