package com.smt.chashizaixian;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smt.config.Constants;
import com.smt.imageload.util.ImageLoader;
import com.smt.util.t.ToastUtil;

public class MimePhotoDetailActivity extends Activity implements OnClickListener {

	private ArrayList<View> listViews = null;
	public List<Bitmap> bmp = new ArrayList<Bitmap>();
	
	private String[] bmp1=null; 
	
	private ImageView img;
    private ViewPager viewpager;
	private MyPageAdapter adapter;
	private TextView mime_pd_back;
	private TextView mime_photo_more;
	private int count;
	private int currentItem = 0; // 当前图片的索引号
	private TextView card_text_01;
	private RelativeLayout todetail;
	
	private RelativeLayout comment_layout_01;
	private RelativeLayout gc_layout_01;
	

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mime_photo);

		
		viewpager=(ViewPager)findViewById(R.id.viewpager);
		
		card_text_01=(TextView)findViewById(R.id.card_text_01);
		
		todetail=(RelativeLayout)findViewById(R.id.todetail);
		todetail.setOnClickListener(this);
		
		mime_pd_back=(TextView)findViewById(R.id.mime_pd_back);
	    mime_pd_back.setOnClickListener(this);
	    
	    mime_photo_more=(TextView)findViewById(R.id.mime_photo_more);
	    mime_photo_more.setOnClickListener(this);
	    
	    comment_layout_01=(RelativeLayout)findViewById(R.id.comment_layout_01);
	    comment_layout_01.setOnClickListener(this);
	    
	    gc_layout_01=(RelativeLayout)findViewById(R.id.gc_layout_01);
	    gc_layout_01.setOnClickListener(this);
	   
	    bmp1=new String[ ]{"http://cimage.tianjimedia.com/uploadImages/2013/199/D981CIN0ON36.jpg","http://www.yn.xinhuanet.com/auto/2013-06/24/132482009_11n.jpg","http://www.yn.xinhuanet.com/auto/2013-06/24/132482009_21n.jpg"};
	    
	    viewpager = (ViewPager) findViewById(R.id.viewpager);
	    viewpager.setOnPageChangeListener(pageChangeListener);
		for (int i = 0; i < bmp1.length; i++) {
			initListViews(bmp1[i],i);//
		}

		adapter = new MyPageAdapter(listViews);// 构造adapter
		viewpager.setAdapter(adapter);// 设置适配器
		Intent intent = getIntent();
		int id = intent.getIntExtra("ID", 0);
		viewpager.setCurrentItem(id);
	    
	    
	    
	    
	    
	}
	
	
	
	private void initListViews(String bm,int i) {
		if (listViews == null)
			listViews = new ArrayList<View>();
		img = new ImageView(this);// 构造textView对象
		img.setBackgroundColor(0xff000000);
		
		if (Constants.mImageLoader==null) {
		
			Constants.mImageLoader=new ImageLoader(MimePhotoDetailActivity.this);
			
		}
		Constants.mImageLoader.DisplayImage(bm, img, false);
		
//		img.setImageBitmap(bm);
	    img.setId(i);
		img.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		listViews.add(img);// 添加view
	}

	private OnPageChangeListener pageChangeListener = new OnPageChangeListener() {
//		private int oldPosition = 0;
		public void onPageSelected(int arg0) {// 页面选择响应函数
			
		
			currentItem = arg0;
			card_text_01.setText(bmp1[currentItem]);
	
		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {// 滑动中。。。

		}

		public void onPageScrollStateChanged(int arg0) {// 滑动状态改变

		}
	};
	
	
	class MyPageAdapter extends PagerAdapter {

		private ArrayList<View> listViews;// content

		private int size;// 页数

		public MyPageAdapter(ArrayList<View> listViews) {// 构造函数
															// 初始化viewpager的时候给的一个页面
			this.listViews = listViews;
			size = listViews == null ? 0 : listViews.size();
		}

		public void setListViews(ArrayList<View> listViews) {// 自己写的一个方法用来添加数据
			this.listViews = listViews;
			size = listViews == null ? 0 : listViews.size();
		}

		public int getCount() {// 返回数量
			return size;
		}

		public int getItemPosition(Object object) {
			return POSITION_NONE;
		}

		public void destroyItem(View arg0, int arg1, Object arg2) {// 销毁view对象
			((ViewPager) arg0).removeView(listViews.get(arg1 % size));
		}

		public void finishUpdate(View arg0) {
		}

		public Object instantiateItem(View arg0, int arg1) {// 返回view对象
			try {
				((ViewPager) arg0).addView(listViews.get(arg1 % size), 0);

			} catch (Exception e) {
			}
			return listViews.get(arg1 % size);
		}

		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

	}
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.mime_pd_back:
			this.finish();
			break;

		case R.id.mime_photo_more:
			new PopupWindows(MimePhotoDetailActivity.this, viewpager);
			break;
			
		case R.id.todetail:
			Intent intent=new Intent(MimePhotoDetailActivity.this, MimeAlbumDetailActivity.class);
			startActivity(intent);
			break;
			
		case R.id.comment_layout_01:
			
			Intent intent1=new Intent(MimePhotoDetailActivity.this, MimeAlbumDetailActivity.class);
			startActivity(intent1);
			break;
            case R.id.gc_layout_01:
			
			Intent intent2=new Intent(MimePhotoDetailActivity.this, MimeAlbumDetailActivity.class);
			startActivity(intent2);
			break;	
			
		default:
			break;
		}
	}

	
	public class PopupWindows extends PopupWindow {

		public PopupWindows(Context mContext, View parent) {

			View view = View.inflate(mContext, R.layout.share_popupwindows, null);
			view.startAnimation(AnimationUtils.loadAnimation(mContext,
					R.anim.fade_ins));
			LinearLayout ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);
			ll_popup.startAnimation(AnimationUtils.loadAnimation(mContext,R.anim.push_bottom_in_2));

			setWidth(LayoutParams.FILL_PARENT);
			setHeight(LayoutParams.FILL_PARENT);
			setBackgroundDrawable(new BitmapDrawable());
			setFocusable(true);
			setOutsideTouchable(true);
			setContentView(view);
			showAtLocation(parent, Gravity.BOTTOM, 0, 0);
			update();

			TextView tv1=(TextView)view.findViewById(R.id.share_01);
			TextView tv2=(TextView)view.findViewById(R.id.share_02);
			TextView tv3=(TextView)view.findViewById(R.id.share_03);
			
			TextView del=(TextView)view.findViewById(R.id.delete);
			
			Button cancel=(Button)view.findViewById(R.id.item_pd_cancel);
			
			
			
			tv1.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
				ToastUtil.toastshow(MimePhotoDetailActivity.this, "暂无接口");
				
					dismiss();
				}
			});
			tv2.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
		
					ToastUtil.toastshow(MimePhotoDetailActivity.this, "暂无接口");
					dismiss();
				}
			});
			tv3.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					ToastUtil.toastshow(MimePhotoDetailActivity.this, "暂无接口");
					dismiss();
				}
			});
			
			del.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					ToastUtil.toastshow(MimePhotoDetailActivity.this, "暂无接口");
					dismiss();
				}
				
			});
			
			cancel.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
			
					dismiss();
				}
			});

		}
	}
	
}
