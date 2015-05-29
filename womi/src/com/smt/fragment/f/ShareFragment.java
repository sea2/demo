package com.smt.fragment.f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;

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
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.smt.chashizaixian.MimeActivity;
import com.smt.chashizaixian.R;
import com.smt.config.Constants;
import com.smt.imageload.util.ImageLoader;
import com.smt.util.adapter.MimeShareAdapter;
import com.smt.util.listview.XListView;
import com.smt.util.listview.XListView.IXListViewListener;




public class ShareFragment extends Fragment implements IXListViewListener, OnClickListener{
	
	private MimeShareAdapter adapter;
	private XListView mListView;

	private ArrayList<Map<String, Object>> mlist = new ArrayList<Map<String, Object>>();
	
	public static ShareFragment newInstance() {
		ShareFragment fragment = new ShareFragment();			
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	    
	    if (Constants.mImageLoader == null)
	    {
	        Constants.mImageLoader = new ImageLoader(getActivity()); 
	    }
	    
		View shareView=inflater.inflate(R.layout.share_tab_fragment, null);
		
		    initData();
		    mListView = (XListView)shareView.findViewById(R.id.mime_share_list);			   
			mListView.setPullLoadEnable(true);
			mListView.setPullRefreshEnable(false);
			mListView.setXListViewListener(this);
		    adapter=new MimeShareAdapter(getActivity(), mlist);		
			mListView.setAdapter(adapter);
			
			mListView.setOnTouchListener(new OnTouchListener(){
				   @Override
				   public boolean onTouch(View v, MotionEvent event) {
					    int eventaction = event.getActionMasked();
		
					    if (eventaction==MotionEvent.ACTION_DOWN) {
							return true;
						}
				       if (eventaction==MotionEvent.ACTION_UP) {
				    	   return false;
					   }
					    if (eventaction==MotionEvent.ACTION_MOVE) {
							return true;
						}
					    if (eventaction==MotionEvent.ACTION_CANCEL) {
							return false;
						}
						return true;
					    
					   
				   }
				});  
			
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
			    android.util.Log.v("ttteeeeeeeeeeeeeeeeeeeeeeee", "dianjile");	
				
				
				
			}
		});	
			
			
			
		
		
		return shareView;
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
