package com.smt.chashizaixian;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import com.smt.util.adapter.MimeShareAdapter;
import com.smt.util.listview.XListView;
import com.smt.util.listview.XListView.IXListViewListener;

public class MimeGroupContentActivity extends Activity implements IXListViewListener,OnClickListener {

	
	private XListView group_list;
    private MimeShareAdapter adapter;
    private ArrayList<Map<String, Object>> mlist = new ArrayList<Map<String, Object>>();


	private TextView mime_group_back;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mimegroupcontent);
		
		initData();
		
		
		group_list=(XListView)findViewById(R.id.mgroup_list);
		

		group_list.setPullLoadEnable(true);
		group_list.setXListViewListener(this);
		group_list.setRefreshTime(new Date().toLocaleString());
		
		
		mime_group_back=(TextView)findViewById(R.id.activity_mgphoto_back);
		mime_group_back.setOnClickListener(this);
		
	
		
		adapter=new MimeShareAdapter(MimeGroupContentActivity.this, mlist);	
		group_list.setAdapter(adapter);
		
		
		group_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MimeGroupContentActivity.this,MimeAlbumDetailActivity.class);
			    startActivity(intent);
			}
		});
		
	}
	
	
	public void initData()
	{
		for (int i = 0; i < 15; i++)
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("key_name", "qundongtai" + i);
		    mlist.add(map);
		}
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
		   case R.id.activity_mgphoto_back:
	        	
			   this.finish();
	        
		        break;  
		
		  
	      
		default:
			break;
		}
	}

	
	

	
	

	
}
