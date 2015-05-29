package com.smt.chashizaixian;


import java.io.File;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.smt.config.Constants;
import com.smt.fragment.f.CircleFragment;
import com.smt.fragment.f.ShareFragment;
import com.smt.fragment.f.SpaceFragment;
import com.smt.imageload.util.ImageLoader;
import com.smt.pub.util.Bimp;
import com.smt.pullview.util.RefreshableView;
import com.smt.util.t.ToastUtil;



public class MimeActivity extends FragmentActivity implements RefreshableView.RefreshListener,OnClickListener{
	private RefreshableView mRefreshableView;
	private TextView tvshare, tvspace, tvcollect;
	private RelativeLayout layshare,layspace,laycollect;
	private Resources resources;
	private Fragment fragment;
	
	private RelativeLayout pub_layout;
	private RelativeLayout pub_layout_01;
	
	private Boolean flag=true;
	private ScrollView scroll_view_root;
	
	private ImageButton ib01;
	private ImageButton ib02;
	private ImageButton ib03;
	private ImageButton ib04;
	
	
	//private int currIndex = 0;
	@SuppressLint("HandlerLeak") Handler handler = new Handler() {
		@SuppressLint("HandlerLeak") 
		public void handleMessage(Message message) {
			super.handleMessage(message);
			mRefreshableView.finishRefresh();
			Toast.makeText(MimeActivity.this, R.string.toast_text, Toast.LENGTH_SHORT).show();
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Constants.mImageLoader = new ImageLoader(MimeActivity.this); 
		setContentView(R.layout.mime);
	    resources = getResources();
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		mRefreshableView = (RefreshableView) findViewById(R.id.refresh_root);
		initData();
		
		fragment=new ShareFragment();		
	    getSupportFragmentManager().beginTransaction().replace(R.id.mime_info_frame, fragment).commit();
	    
		tvshare=(TextView)findViewById(R.id.mime_tv_share);
		tvspace=(TextView)findViewById(R.id.mime_tv_space);
		tvcollect=(TextView)findViewById(R.id.mime_tv_col);
		
		layshare=(RelativeLayout)findViewById(R.id.mime_tab_share);
		layspace=(RelativeLayout)findViewById(R.id.mime_tab_space);
		laycollect=(RelativeLayout)findViewById(R.id.mime_tab_col);
		
		layshare.setOnClickListener(this);
		layspace.setOnClickListener(this);
		laycollect.setOnClickListener(this);
		
		pub_layout=(RelativeLayout)findViewById(R.id.blay);
		pub_layout.setOnClickListener(this);
		pub_layout_01=(RelativeLayout)findViewById(R.id.blay1);
		
		ib01=(ImageButton)findViewById(R.id.compose_01);
		ib02=(ImageButton)findViewById(R.id.compose_02);
		ib03=(ImageButton)findViewById(R.id.compose_03);
		ib04=(ImageButton)findViewById(R.id.compose_04);
		
		ib01.setOnClickListener(this);
		ib02.setOnClickListener(this);
		ib03.setOnClickListener(this);
		ib04.setOnClickListener(this);
		
		
		scroll_view_root=(ScrollView)findViewById(R.id.scroll_view_root);
	
		
		
		
		scroll_view_root.setOnTouchListener(new OnTouchListener() {
			
			private int lastY = 0;
			
			private int touchEventId = -9983761;
			
			 
		
			@SuppressLint("HandlerLeak")
			Handler handler = new Handler() {
			
			    @Override
			
			    public void handleMessage(Message msg) {
			
			        super.handleMessage(msg);
		
			        View scroller = (View)msg.obj;
			
			        if(msg.what==touchEventId) {
		
			            if(lastY ==scroller.getScrollY()) {
			
			                handleStop(scroller);
			
			            }else {
			         
			                handler.sendMessageDelayed(handler.obtainMessage(touchEventId,scroller), 5);
			                
			                lastY = scroller.getScrollY();
			
			            }
		
			        }
			
			    }
			
			};
			
			@Override
			
			public boolean onTouch(View v, MotionEvent event) {
			
			    if(event.getAction() == MotionEvent.ACTION_UP) {
			
			        handler.sendMessageDelayed(handler.obtainMessage(touchEventId,v), 5);
			
			    }
			    
			   if (event.getAction()==MotionEvent.ACTION_MOVE) {
				  
				   pub_layout.setVisibility(View.GONE);
				   pub_layout_01.setVisibility(View.GONE);
			 
	               
	           
			} 
			    
			
			    return false;
			}
	
			//这里写真正的事件
		
			private void handleStop(Object view) {

			        //Do Something
				 pub_layout.setVisibility(View.VISIBLE);
			    }
			
			});

	}
	private void initData() {
		
		mRefreshableView.setRefreshListener(MimeActivity.this);
		
	}
	
	//实现刷新RefreshListener 中方法
	public void onRefresh(RefreshableView view) {
		//伪处理
		handler.sendEmptyMessageDelayed(1, 2000);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.mime_tab_share:
			tvcollect.setTextColor(resources.getColor(R.color.black_color));
        	tvspace.setTextColor(resources.getColor(R.color.black_color));
        	tvshare.setTextColor(resources.getColor(R.color.lightwhite));
            layshare.setBackgroundResource(R.drawable.whisper_btn_bg_all_hover);
            layspace.setBackgroundResource(0);
            laycollect.setBackgroundResource(0);            
            fragment=new ShareFragment();		
		    getSupportFragmentManager().beginTransaction().replace(R.id.mime_info_frame, fragment).commit();	
            
			break;
        case R.id.mime_tab_space:
        	 tvcollect.setTextColor(resources.getColor(R.color.black_color));
        	 tvspace.setTextColor(resources.getColor(R.color.lightwhite));
        	 tvshare.setTextColor(resources.getColor(R.color.black_color));
        	 layspace.setBackgroundResource(R.drawable.whisper_btn_bg_all_hover);
        	 layshare.setBackgroundResource(0);
             laycollect.setBackgroundResource(0);
             fragment=new SpaceFragment();		
 		     getSupportFragmentManager().beginTransaction().replace(R.id.mime_info_frame, fragment).commit();	

			break;
        case R.id.mime_tab_col:
        	tvcollect.setTextColor(resources.getColor(R.color.lightwhite));
        	tvspace.setTextColor(resources.getColor(R.color.black_color));
        	tvshare.setTextColor(resources.getColor(R.color.black_color));
        	laycollect.setBackgroundResource(R.drawable.whisper_btn_bg_all_hover);
        	layshare.setBackgroundResource(0);
            layspace.setBackgroundResource(0);
            fragment=new CircleFragment();		
		    getSupportFragmentManager().beginTransaction().replace(R.id.mime_info_frame, fragment).commit();	
	        break;
	        
        case R.id.blay:
        	
        	if (flag) {
        		pub_layout_01.setVisibility(View.VISIBLE);	
        		flag=false;
			}
        	else {
        		pub_layout_01.setVisibility(View.GONE);	
        		flag=true;
			}
        	
        	
	        break;  
	        
        case R.id.compose_01:   
	        
    		Intent intent = new Intent(MimeActivity.this,GetPicActivity.class);    	
			startActivity(intent);
        	pub_layout_01.setVisibility(View.GONE);
        	
	        break;
	        
        case R.id.compose_02:   
	        
        	photo();        	
        	pub_layout_01.setVisibility(View.GONE);
        	
	        break;
        case R.id.compose_03:   
     
        	Intent intent1 = new Intent(MimeActivity.this,PublishedActivity.class);
    		Bundle bundle1 = new Bundle();
    		bundle1.putBoolean("pubsort", false);
			intent1.putExtras(bundle1);
			startActivity(intent1);
			pub_layout_01.setVisibility(View.GONE);
        	
            break;
        case R.id.compose_04:   
	
         ToastUtil.toastshow(MimeActivity.this, "只有管理员以上才可以发起活动");	
         
            Intent intent2= new Intent(MimeActivity.this,StartActivityActivity.class);		
			startActivity(intent2);
			pub_layout_01.setVisibility(View.GONE);
        	
            break;
            
		default:
			break;
		}
	}
	
	private static final int TAKE_PICTURE = 0x000000;
	private String path = "";
	public void photo() {
		Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		File file = new File(Environment.getExternalStorageDirectory()
				+ "/myimage/", String.valueOf(System.currentTimeMillis())
				+ ".jpg");
		path = file.getPath();
		Uri imageUri = Uri.fromFile(file);
		openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		startActivityForResult(openCameraIntent, TAKE_PICTURE);
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	
	
		if (data==null&&resultCode!=-1) {
			
			Intent intent = new Intent(MimeActivity.this,PublishedActivity.class);			
			Bundle bundle1 = new Bundle();
    		bundle1.putBoolean("pubsort", true);
			intent.putExtras(bundle1);
			startActivity(intent);
			
			
		}
		

		if (resultCode==-1) {

			if (Bimp.drr.size() < 9) {
				Bimp.drr.add(path);
			}
			Intent intent = new Intent(MimeActivity.this,PublishedActivity.class);			
			Bundle bundle1 = new Bundle();
    		bundle1.putBoolean("pubsort", true);
			intent.putExtras(bundle1);
			startActivity(intent);
			
		
		   }
	
		
		
		
	}
	
	
	  @Override
			public boolean onKeyDown(int keyCode, KeyEvent event)
			{
				// TODO Auto-generated method stub
				if (keyCode == KeyEvent.KEYCODE_BACK&&!flag)
				{
					pub_layout_01.setVisibility(View.GONE);
					flag=true;
					return true;
				}
				if (keyCode == KeyEvent.KEYCODE_BACK) {
					MimeActivity.this.finish();
				}
				
				return super.onKeyDown(keyCode, event);
					

				

			}
	 
}
