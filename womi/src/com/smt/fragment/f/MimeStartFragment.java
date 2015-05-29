package com.smt.fragment.f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.smt.chashizaixian.R;
import com.smt.util.adapter.MimeActivityAdapter;
import com.smt.util.adapter.MimeShareAdapter;
import com.smt.util.listview.XListView;




public class MimeStartFragment extends Fragment implements OnClickListener{
	
	private MimeActivityAdapter adapter;
	private XListView mListView;

	private ArrayList<Map<String, Object>> mlist = new ArrayList<Map<String, Object>>();
	
	public static MimeStartFragment newInstance() {
		MimeStartFragment fragment = new MimeStartFragment();			
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

	    View startView=inflater.inflate(R.layout.mimestartactivity, null);
		
	    mListView=(XListView)startView.findViewById(R.id.startactivity_list);
	    initData();
	    
	    adapter=new MimeActivityAdapter(getActivity(), mlist);
	    mListView.setAdapter(adapter);
	    
		return startView;
	}

	
	
	@Override
	public void onStart() {
		super.onStart();
	
	}

	private void initData(){
		
	 for (int i = 0; i < 10; i++) {
		 
		HashMap<String, Object> temp= new HashMap<String, Object>();
		temp.put("key_name", "ÁÚ¾ÓµÄ¶ú¶ä");
		mlist.add(temp);
	}
		
		
		
	}
	
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	

	
	
	    
	    
	    
}
