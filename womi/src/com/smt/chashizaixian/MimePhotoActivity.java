package com.smt.chashizaixian;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.smt.pub.util.Bimp;
import com.smt.util.adapter.MimeShareAdapter;
import com.smt.util.adapter.PhotoAdapter;
import com.smt.util.listview.XListView;
import com.smt.util.listview.XListView.IXListViewListener;
import com.smt.util.t.ClearEditText;
import com.smt.util.t.ToastUtil;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

public class MimePhotoActivity extends Activity implements IXListViewListener,OnClickListener {

	
	private XListView photo_list;
    private PhotoAdapter adapter;
    private ArrayList<Map<String, Object>> mlist = new ArrayList<Map<String, Object>>();


    
	private static final int TAKE_PICTURE = 0x000000;
	private String path = "";
	
	private TextView mime_photo_pub;
	
	private TextView mime_photo_back;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mimephoto);
		
		initData();
		
		
		photo_list=(XListView)findViewById(R.id.photo_list);
		
		
		photo_list.addHeaderView(getheadView());
		
		
		
		photo_list.setPullLoadEnable(false);
		photo_list.setXListViewListener(this);
		photo_list.setRefreshTime(new Date().toLocaleString());
		
		
		mime_photo_back=(TextView)findViewById(R.id.activity_mphoto_back);
		mime_photo_back.setOnClickListener(this);
		
		mime_photo_pub=(TextView)findViewById(R.id.mime_photo_pub);
		mime_photo_pub.setOnClickListener(this);
		
		adapter=new PhotoAdapter(MimePhotoActivity.this, mlist);	
		photo_list.setAdapter(adapter);
		
		photo_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				 Intent intent=new Intent(MimePhotoActivity.this, MimePhotoDetailActivity.class);
				 startActivity(intent);
			}
		});
		
	}
	
	
	public void initData()
	{
		for (int i = 0; i < 15; i++)
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("key_name", "内容" + i);
		    mlist.add(map);
		}
	}

	 private View getheadView(){
			
			
		  View view =LayoutInflater.from(MimePhotoActivity.this).inflate(R.layout.circle_head_layout, null);
		  

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
		   case R.id.activity_mphoto_back:
	        	
			   this.finish();
	        
		        break;  
		
		   case R.id.mime_photo_pub:
	        	
			   final CharSequence[] items = { "我的消息"};  
		        AlertDialog dlg = new AlertDialog.Builder(MimePhotoActivity.this).setTitle("").setItems(items,   
		            new DialogInterface.OnClickListener() {   
		                public void onClick(DialogInterface dialog,int item) {   
		                    //这里item是根据选择的方式,  
		                    //在items数组里面定义了两种方式
		                    if(item==0){   
		                    	Intent intent2 = new Intent(MimePhotoActivity.this,MimeInfoActivity.class);		                		
		            			startActivity(intent2);	
		                    }
		                }   
		            }).create();   
		        dlg.show(); 
			
	        
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
			
			Intent intent = new Intent(MimePhotoActivity.this,PublishedActivity.class);			
			Bundle bundle1 = new Bundle();
    		bundle1.putBoolean("pubsort", true);
			intent.putExtras(bundle1);
			startActivity(intent);
			
			
		}
		

		if (resultCode==-1) {

			if (Bimp.drr.size() < 9) {
				Bimp.drr.add(path);
			}
			Intent intent = new Intent(MimePhotoActivity.this,PublishedActivity.class);			
			Bundle bundle1 = new Bundle();
    		bundle1.putBoolean("pubsort", true);
			intent.putExtras(bundle1);
			startActivity(intent);
			
		
		   }
	
		
		
		
	}
	
	

	
}
