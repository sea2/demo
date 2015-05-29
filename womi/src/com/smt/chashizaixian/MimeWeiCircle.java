package com.smt.chashizaixian;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.smt.pub.util.Bimp;
import com.smt.util.adapter.MimeShareAdapter;
import com.smt.util.listview.XListView;
import com.smt.util.listview.XListView.IXListViewListener;
import com.smt.util.t.ClearEditText;
import com.smt.util.t.ToastUtil;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;

public class MimeWeiCircle extends Activity implements IXListViewListener,OnClickListener {

	
	private XListView circle_list;
    private MimeShareAdapter adapter;
    private ArrayList<Map<String, Object>> mlist = new ArrayList<Map<String, Object>>();
    
    
	private RelativeLayout pub_layout;
	private RelativeLayout pub_layout_01;
	

	
	private ImageButton ib01;
	private ImageButton ib02;
	private ImageButton ib03;
	private ImageButton ib04;
    
	private Boolean flag=true;
    
	private static final int TAKE_PICTURE = 0x000000;
	private String path = "";
	
	private TextView mime_circle_pub;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mimecircle);
		
		initData();
		
		
		circle_list=(XListView)findViewById(R.id.circle_list);
		
		
		circle_list.addHeaderView(getheadView());
		
		
		
		circle_list.setPullLoadEnable(false);
		circle_list.setXListViewListener(this);
		circle_list.setRefreshTime(new Date().toLocaleString());
		
		
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
		
		mime_circle_pub=(TextView)findViewById(R.id.mime_circle_pub);
		mime_circle_pub.setOnClickListener(this);
		
		adapter=new MimeShareAdapter(MimeWeiCircle.this, mlist);	
		circle_list.setAdapter(adapter);
		
		
		
	}
	
	
	public void initData()
	{
		for (int i = 0; i < 15; i++)
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("key_name", "username" + i);
		    mlist.add(map);
		}
	}

	 private View getheadView(){
			
			
		  View view =LayoutInflater.from(MimeWeiCircle.this).inflate(R.layout.circle_head_layout, null);
		  

			return view;
	   }
	    
	

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
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
		        
	    		Intent intent = new Intent(MimeWeiCircle.this,GetPicActivity.class);    	
				startActivity(intent);
	        	pub_layout_01.setVisibility(View.GONE);
	        	
		        break;
		        
	        case R.id.compose_02:   
		        
	        	photo();        	
	        	pub_layout_01.setVisibility(View.GONE);
	        	
		        break;
	        case R.id.compose_03:   
	     
	        	Intent intent1 = new Intent(MimeWeiCircle.this,PublishedActivity.class);
	    		Bundle bundle1 = new Bundle();
	    		bundle1.putBoolean("pubsort", false);
				intent1.putExtras(bundle1);
				startActivity(intent1);
				pub_layout_01.setVisibility(View.GONE);
	        	
	            break;
	        case R.id.compose_04:   
		
	         ToastUtil.toastshow(MimeWeiCircle.this, "只有管理员以上才可以发起活动");	
	         
	            Intent intent2= new Intent(MimeWeiCircle.this,StartActivityActivity.class);		
				startActivity(intent2);
				pub_layout_01.setVisibility(View.GONE);
	        	
	            break;
	            
	        case R.id. mime_circle_pub:   
	    		
	          	Intent intent3= new Intent(MimeWeiCircle.this,PublishedActivity.class);
	    		Bundle bundle3 = new Bundle();
	    		bundle3.putBoolean("pubsort", true);
				intent3.putExtras(bundle3);
				startActivity(intent3);
				pub_layout_01.setVisibility(View.GONE);	
		            break;
	           
		default:
			break;
		}
	}

	
	
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
			
			if (Bimp.act_bool) {			
			Intent intent = new Intent(MimeWeiCircle.this,PublishedActivity.class);			
			Bundle bundle1 = new Bundle();
    		bundle1.putBoolean("pubsort", true);
			intent.putExtras(bundle1);
			startActivity(intent);
			Bimp.act_bool=false;
			}
			
		}
		

		if (resultCode==-1) {

			if (Bimp.drr.size() < 9) {
				Bimp.drr.add(path);
			}
			
			if (Bimp.act_bool) {
				Intent intent = new Intent(MimeWeiCircle.this,PublishedActivity.class);			
				Bundle bundle1 = new Bundle();
	    		bundle1.putBoolean("pubsort", true);
				intent.putExtras(bundle1);
				startActivity(intent);
				Bimp.act_bool=false;
			}

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
				MimeWeiCircle.this.finish();
			}
			
			return super.onKeyDown(keyCode, event);
				

			

		}

	
}
