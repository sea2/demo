package com.smt.fragment.f;

import java.util.ArrayList;
import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.smt.chashizaixian.R;
import com.smt.util.adapter.MimeShareAdapter;
import com.smt.util.listview.XListView;




public class MimeTakePartFragment extends Fragment implements OnClickListener{
	
	private MimeShareAdapter adapter;
	private XListView mListView;

	private ArrayList<Map<String, Object>> mlist = new ArrayList<Map<String, Object>>();
	
	public static MimeTakePartFragment newInstance() {
		MimeTakePartFragment fragment = new MimeTakePartFragment();			
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

	    View shareView=inflater.inflate(R.layout.mimetakepartactivity, null);
		
		   
			
	
			
			
			
		
		
		return shareView;
	}

	
	
	@Override
	public void onStart() {
		super.onStart();
	
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	

	
	
	    
	    
	    
}
