package com.smt.util.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

public class ProductAdapter extends BaseAdapter
{
    
    Activity activity;
    private List<? extends Map<String, ?>> data;
    
    public ProductAdapter(Activity activity, ArrayList<Map<String, Object>> list)
    {
        this.activity = activity;
        this.data = list;
    }

    @Override
    public int getCount()
    {
        return data.size();
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
        if (convertView == null)
        {
            convertView = activity.getLayoutInflater().inflate(R.layout.item_product_grid, null);
            holder = new ViewHolder();
            holder.icon = (ImageView) convertView.findViewById(R.id.product_icon);
            holder.name = (TextView) convertView.findViewById(R.id.product_name);
            holder.price = (TextView) convertView.findViewById(R.id.product_price);
            holder.like = (TextView) convertView.findViewById(R.id.product_like);
            
            float density = DeviceInfo.getDensity(activity);
            WindowManager wm = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
            int width = wm.getDefaultDisplay().getWidth();//屏幕宽度
            LayoutParams parms = new LayoutParams(LayoutParams.WRAP_CONTENT, (int) ((width - (60 + 8) * density) / 2));
//          设置图片高度为  [屏幕宽度 - (布局左右间距 + gridView的item左右间距)*屏幕密度]/2  = 图片宽度，解决布局高度有留空白的bug
            holder.icon.setLayoutParams(parms);
            
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }
    
    class ViewHolder
    {
        ImageView icon;
        TextView name;
        TextView price;
        TextView like;
    }
}
