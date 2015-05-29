package com.smt.fragment.f;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.smt.chashizaixian.R;


public class CollectFragment extends Fragment implements OnClickListener{
	
	public static CollectFragment newInstance() {
		CollectFragment fragment = new CollectFragment();			
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View shareView=inflater.inflate(R.layout.collect_tab_fragment, null);
		
		
		
		
		return shareView;
	}



	
	@Override
	public void onResume() {
		
	
		
		super.onResume();
	
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
