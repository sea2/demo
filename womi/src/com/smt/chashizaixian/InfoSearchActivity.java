package com.smt.chashizaixian;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.smt.util.adapter.InfoAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class InfoSearchActivity extends Activity implements OnClickListener{

	
	private Button cancel;
	private EditText sear_et;
	private InfoAdapter adapter;
	private ListView mListView;
	private ArrayList<Map<String, Object>> mlist = new ArrayList<Map<String, Object>>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.infosearch);
		
		initView();
		initData();
		
	}

	private void initView(){
		cancel=(Button)findViewById(R.id.search_cancel_btn);		
		cancel.setOnClickListener(this);
		sear_et=(EditText)findViewById(R.id.info_search_edit);
		sear_et.requestFocus();
		mListView=(ListView)findViewById(R.id.info_search_list);
	}
	
	
	public void initData()
	{
		for (int i = 0; i < 3; i++)
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("key_name", "username" + i);
			mlist.add(map);
		    adapter=new InfoAdapter(InfoSearchActivity.this, mlist);	
		    mListView.setAdapter(adapter);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.search_cancel_btn:
			this.finish();
			break;

		default:
			break;
		}
	}
}
