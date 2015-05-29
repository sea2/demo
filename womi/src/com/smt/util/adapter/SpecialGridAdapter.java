package com.smt.util.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.smt.chashizaixian.R;
import com.smt.util.t.DeviceInfo;

public class SpecialGridAdapter extends BaseAdapter
{
    
    Activity activity;
    
    public SpecialGridAdapter(Activity activity)
    {
        this.activity = activity;
    }
    
    @Override
    public int getCount()
    {
        return 25;
    }
    
    @Override
    public Object getItem(int position)
    {
        return null;
    }
    
    @Override
    public long getItemId(int position)
    {
        return 0;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder = null;
        if (position != 24)
        {
            if (convertView == null)
            {
                convertView = activity.getLayoutInflater().inflate(R.layout.item_special_grid, null);
                holder = new ViewHolder();
                holder.icon = (ImageView) convertView.findViewById(R.id.special_icon);
                holder.price = (TextView) convertView.findViewById(R.id.special_price);
                holder.like = (TextView) convertView.findViewById(R.id.special_like);
                
                float density = DeviceInfo.getDensity(activity);
                WindowManager wm = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
                int width = wm.getDefaultDisplay().getWidth();//屏幕宽度
                LayoutParams parms = new LayoutParams(LayoutParams.WRAP_CONTENT, (int) ((width - (16*2 + 8*2)*density) / 3));
//              设置图片高度为  [屏幕宽度 - (布局左右间距 + gridView的item左右间距)*屏幕密度]/3 = 图片宽度，解决布局高度有留空白的bug
                holder.icon.setLayoutParams(parms);
                
                convertView.setTag(holder);
            }
            else
            {
                holder = (ViewHolder) convertView.getTag();
            }
        }
        else
        {
            convertView = activity.getLayoutInflater().inflate(R.layout.more_special_grid, null);
            View more = convertView.findViewById(R.id.more);
            
            float density = DeviceInfo.getDensity(activity);
            WindowManager wm = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
            int width = wm.getDefaultDisplay().getWidth();//屏幕宽度
            LayoutParams parms = new LayoutParams(LayoutParams.WRAP_CONTENT, (int) ((width - (16*2)*density) / 3));
            more.setLayoutParams(parms);            
        }
        return convertView;
    }
    
    class ViewHolder
    {
        ImageView icon;
        TextView price;
        TextView like;
    }
}
