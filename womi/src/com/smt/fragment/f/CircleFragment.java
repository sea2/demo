package com.smt.fragment.f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.smt.chashizaixian.CircleDetailActivity;
import com.smt.chashizaixian.R;
import com.smt.util.adapter.MimeCircleAdapter;
import com.smt.util.listview.XListView;
import com.smt.util.listview.XListView.IXListViewListener;


public class CircleFragment extends Fragment implements IXListViewListener, OnClickListener{
	
	private XListView mListView;
	private MimeCircleAdapter adapter;

	private ArrayList<Map<String, Object>> mlist = new ArrayList<Map<String, Object>>();
	
	public static CircleFragment newInstance() {
		CircleFragment fragment = new CircleFragment();			
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View CircleView=inflater.inflate(R.layout.circle_tab_fragment, null);
		
		   mListView=(XListView)CircleView.findViewById(R.id.mime_circle_list);
		
		    initData();
			mListView.setPullLoadEnable(true);
			mListView.setPullRefreshEnable(false);
			mListView.setXListViewListener(this);
		    adapter=new MimeCircleAdapter(getActivity(), mlist);		
			mListView.setAdapter(adapter);
			
//			mListView.setOnTouchListener(new OnTouchListener(){
//				   @Override
//				   public boolean onTouch(View v, MotionEvent event) {
//					    int eventaction = event.getActionMasked();
//		
//					    if (eventaction==MotionEvent.ACTION_DOWN) {
//							return true;
//						}
//				       if (eventaction==MotionEvent.ACTION_UP) {
//				    	   return false;
//					   }
//					    if (eventaction==MotionEvent.ACTION_MOVE) {
//							return true;
//						}
//					    if (eventaction==MotionEvent.ACTION_CANCEL) {
//							return false;
//						}
//						return false;
//					    
//					   
//				   }
//				});  
			mListView.setOnItemClickListener(new OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                        int arg2, long arg3)
                {
                    startActivity(new Intent(getActivity(), CircleDetailActivity.class));
                }
            });

		 return CircleView;
	}



	public void initData()
	{
		for (int i = 0; i < 15; i++)
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("key_name", "circlename" + i);
		    mlist.add(map);
		}
	}
	@Override
	public void onStart() {
		super.onStart();
	
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		
	}


	    
	    
	
	
}
