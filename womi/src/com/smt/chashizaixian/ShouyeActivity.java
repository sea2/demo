package com.smt.chashizaixian;


import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.smt.util.listview.XListView;
import com.smt.util.listview.XListView.IXListViewListener;

public class ShouyeActivity extends Activity implements IXListViewListener{
	private XListView mListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shouye);
		
		
        mListView=(XListView)findViewById(R.id.shouye_list);
        mListView.setXListViewListener(this);
		mListView.setPullLoadEnable(false);
		mListView.setRefreshTime(new Date().toLocaleString());
		
		mListView.addHeaderView(Getshouyeheadview());
		
		mListView.setAdapter(null);
		
	}
	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		
	}
	
	private View Getshouyeheadview(){
	
		 View view =LayoutInflater.from(ShouyeActivity.this).inflate(R.layout.shouye_head_layout, null);
		 return view;	
		
		
	}
}
