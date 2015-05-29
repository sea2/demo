package com.smt.chashizaixian;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.smt.util.adapter.InfoAdapter;
import com.smt.util.t.MyListView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class InfoActivity extends Activity implements OnClickListener {

    private EditText info_edit;
    private ArrayList<Map<String, Object>> mlist = new ArrayList<Map<String, Object>>();
	private InfoAdapter adapter;
	private MyListView mListView; 
	private TextView info_more;
	private LinearLayout top_lay;
	private Boolean istop=true;
	
	private RelativeLayout top_layout_01;
	private RelativeLayout top_layout_02;
	private RelativeLayout top_layout_04;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);        
		setContentView(R.layout.info);
		
		InitView();
		
		initData();
		
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				if (arg2>1) {
					Intent intent=new Intent(InfoActivity.this,MessageActivity.class);
				    startActivity(intent);
				}
				else {
					Intent intent=new Intent(InfoActivity.this,CircleDetailActivity.class);
				    startActivity(intent);
				}
				
				
			}
		});
		
	}

	private void InitView(){
		
	   info_edit=(EditText)findViewById(R.id.info_edit);	
	   info_edit.setOnClickListener(this);
	   mListView=(MyListView)findViewById(R.id.info_list);
	   
	   info_more=(TextView)findViewById(R.id.info_more);
	   info_more.setOnClickListener(this);
	   
	   top_lay=(LinearLayout)findViewById(R.id.top_lay);
	   top_layout_01=(RelativeLayout)findViewById(R.id.top_layout_01);
	   top_layout_01.setOnClickListener(this);
	   top_layout_02=(RelativeLayout)findViewById(R.id.top_layout_02);
	   top_layout_02.setOnClickListener(this);
	   top_layout_04=(RelativeLayout)findViewById(R.id.top_layout_04);
	   top_layout_04.setOnClickListener(this);
	}
	
	public void initData()
	{
		for (int i = 0; i < 15; i++)
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("key_name", "username" + i);
		    mlist.add(map);
		  
		}
		
		    adapter=new InfoAdapter(InfoActivity.this, mlist);	
		    mListView.setAdapter(adapter);
	}

//	@Override
//	public boolean onTouchEvent(MotionEvent event){
//		top_lay.setVisibility(View.GONE);
//		return true;
//	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.info_edit:
			
			Intent intent=new Intent(InfoActivity.this,InfoSearchActivity.class);
		    startActivity(intent);
			
			
			break;
			
	    case R.id.info_more:
			if (istop) {
				top_lay.setVisibility(View.VISIBLE);
				istop=false;
			}
			else {
				top_lay.setVisibility(View.GONE);
				istop=true;
			}
	    	
			
			
			break;
			
        case R.id.top_layout_01:
			
			Intent intent3=new Intent(InfoActivity.this,StartChattingActivity.class);
		    startActivity(intent3);		  
		    top_lay.setVisibility(View.GONE);
		    istop=true;
			
			break;	
			
       case R.id.top_layout_02:
			
			Intent intent4=new Intent(InfoActivity.this,RadarActivity.class);
		    startActivity(intent4);		  
		    top_lay.setVisibility(View.GONE);
		    istop=true;
			
			break;	
			
       case R.id.top_layout_04:
			
    			Intent intent5=new Intent(InfoActivity.this,CaptureActivity.class);
    		    startActivity(intent5);		  
    		    top_lay.setVisibility(View.GONE);
    		    istop=true;
    			
    			break;

		default:
			break;
		}
		
		
	}
}
