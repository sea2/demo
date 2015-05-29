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
import com.smt.util.t.DeviceInfo;

public class MimeShareAdapter extends BaseAdapter
{
    
    private LayoutInflater mInflater;
    private List<? extends Map<String, ?>> data;
    private Context mContext;
    private View view[] = null;
    
    public MimeShareAdapter(Context context, List<Map<String, Object>> list)
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
            
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.mime_share_item, parent, false);
            holder.username_tv = (TextView) convertView
                    .findViewById(R.id.username);
            holder.contenttime_tv = (TextView) convertView
                    .findViewById(R.id.contenttime);
            holder.content_tv = (TextView) convertView
                    .findViewById(R.id.content);
            holder.sharecontent_tv = (TextView) convertView
                    .findViewById(R.id.share_content);
            holder.fromcircle_tv = (TextView) convertView
                    .findViewById(R.id.circlefrom);
            holder.circlename_tv = (TextView) convertView
                    .findViewById(R.id.circlename);
            holder.good_tv = (TextView) convertView.findViewById(R.id.good_num);
            holder.comment_tv = (TextView) convertView
                    .findViewById(R.id.comment_num);
            holder.goodname_tv = (TextView) convertView
                    .findViewById(R.id.share_names);
            holder.userimg_iv = (ImageView) convertView.findViewById(R.id.iv);
            holder.shareimg_iv = (ImageView) convertView
                    .findViewById(R.id.share_img);
            holder.comgood_iv = (ImageView) convertView
                    .findViewById(R.id.comment_good_iv);
            holder.contentimg_ll = (LinearLayout) convertView
                    .findViewById(R.id.image_container);
            holder.comment_ll = (LinearLayout) convertView
                    .findViewById(R.id.comment_container);
            holder.comgoodhandle = (RelativeLayout) convertView
                    .findViewById(R.id.comgoodhandle);
            convertView.setTag(holder);
            convertView.setId(position);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        
        List<ImageData> imageMsg = new ArrayList<ImageData>();
        
        for (int i = 0; i < 6; i++)
        {
            imageMsg.add(new ImageData(12, "http://www.qqw21.com/article/uploadpic/2012-7/2012721212253194.jpg"));
        }
        
        if (imageMsg.size() == 1)
        {
            holder.contentimg_ll.removeAllViews();
            ImageView image1 = new ImageView(mContext);
            holder.contentimg_ll.addView(image1);
            Constants.mImageLoader.DisplayImage(imageMsg.get(0).imageURL,image1, false);
        }
        else if (imageMsg.size() == 2)
        {
            holder.contentimg_ll.removeAllViews();
            
            LinearLayout horizonLayout = new LinearLayout(mContext);
            RelativeLayout.LayoutParams params;
            float density = DeviceInfo.getDensity(mContext);
            WindowManager wm = (WindowManager) mContext
                    .getSystemService(Context.WINDOW_SERVICE);
            int width = wm.getDefaultDisplay().getWidth();// фад╩©М╤х
            float imageLayWidth = width - (10 + 12 + 10 + 50) * density;
            for (int i = 0; i < imageMsg.size(); i++)
            {
                params = new RelativeLayout.LayoutParams(
                        (int) (imageLayWidth / 2), (int) (imageLayWidth / 2));
                ImageView image2 = new ImageView(mContext);
                image2.setPadding((int) (4 * density), (int) (4 * density),
                        (int) (4 * density), (int) (4 * density));
                horizonLayout.addView(image2, params);
                Constants.mImageLoader.DisplayImage(imageMsg.get(i).imageURL,
                        image2, false);
            }
            holder.contentimg_ll.addView(horizonLayout);
        }
        else if (imageMsg.size() > 2 && imageMsg.size() <= 9)
        {
            holder.contentimg_ll.removeAllViews();
            
            RelativeLayout.LayoutParams params;
            float density = DeviceInfo.getDensity(mContext);
            WindowManager wm = (WindowManager) mContext
                    .getSystemService(Context.WINDOW_SERVICE);
            int width = wm.getDefaultDisplay().getWidth();// фад╩©М╤х
            float imageLayWidth = width - (10 + 12 + 10 + 50) * density;
            
            int size = imageMsg.size();
            int yuShu = size % 3;
            if (yuShu == 0)
            {
                int hangNum = (int) (size / 3);
                for (int i = 0; i < hangNum; i++)
                {
                    LinearLayout horizonLayout = new LinearLayout(mContext);
                    for (int j = 0; j< 3; j++)
                    {
                        params = new RelativeLayout.LayoutParams(
                                (int) (imageLayWidth / 3),
                                (int) (imageLayWidth / 3));
                        ImageView image3 = new ImageView(mContext);
                        image3.setPadding((int) (2 * density), (int) (2 * density),
                                (int) (2 * density), (int) (2 * density));
                        horizonLayout.addView(image3, params);
                        Constants.mImageLoader.DisplayImage(imageMsg.get(i * 3 + j).imageURL, image3, false);
                    }
                    holder.contentimg_ll.addView(horizonLayout);
                }
            }
            else
            {
                int hangNum = (int) (size / 3) + 1;
                for (int i = 0; i <= hangNum - 1; i++)
                {
                    LinearLayout horizonLayout = new LinearLayout(mContext);
                    if (i < hangNum - 1)
                    {
                        for (int j = 0; j < 3; j++)
                        {
                            params = new RelativeLayout.LayoutParams(
                                    (int) (imageLayWidth / 3),
                                    (int) (imageLayWidth / 3));
                            ImageView image3 = new ImageView(mContext);
                            image3.setPadding((int) (2 * density), (int) (2 * density),
                                    (int) (2 * density), (int) (2 * density));
                            horizonLayout.addView(image3, params);
                            Constants.mImageLoader.DisplayImage(
                                    imageMsg.get(i * 3 + j).imageURL, image3, false);
                        }
                        holder.contentimg_ll.addView(horizonLayout);
                    }
                    else if (i == hangNum - 1)
                    {
                        for (int j = 0; j < yuShu; j++)
                        {
                            params = new RelativeLayout.LayoutParams(
                                    (int) (imageLayWidth / 3),
                                    (int) (imageLayWidth / 3));
                            ImageView image3 = new ImageView(mContext);
                            horizonLayout.addView(image3, params);
                            Constants.mImageLoader.DisplayImage(
                                    imageMsg.get(i * 3 + j).imageURL, image3, false);
                        }
                        holder.contentimg_ll.addView(horizonLayout);
                    }
                }
            }
        }
        
        holder.username_tv.setText(data.get(position).get("key_name")
                .toString());
        
        holder.username_tv.setOnClickListener(new OnClickListener()
        {
            
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                Log.v("ttttttttttttttttttttttttt",
                        data.get(position).get("key_name").toString());
            }
        });
        
        holder.comgood_iv.setOnClickListener(new OnClickListener()
        {
            Boolean flag = false;
            
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                
                if (!flag)
                {
                    
                    holder.comgoodhandle.setVisibility(View.VISIBLE);
                    flag = true;
                }
                else
                {
                    holder.comgoodhandle.setVisibility(View.GONE);
                    flag = false;
                }
                
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
        public RelativeLayout comgoodhandle;
    }
}
