package com.smt.chashizaixian;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.smt.fragment.f.MimeStartFragment;
import com.smt.fragment.f.MimeTakePartFragment;
import com.smt.util.adapter.MyFragmentPagerAdapter;
public class MimeActivityActivity extends FragmentActivity implements OnClickListener {

	

	private TextView mime_group_back;
    private ViewPager mPager;
	private ArrayList<Fragment> fragmentsList;
	private int currIndex = 0;
	private TextView mimestart_tv;
	private TextView mimetakepart_tv;
	 
	 
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mimeactivity);

		mime_group_back=(TextView)findViewById(R.id.activity_activity_back);
		mime_group_back.setOnClickListener(this);
		InitTextView();
		InitViewPager();
	}
	
	
	
	
	
		
		 private void InitTextView() {
		    	
		    	
	    
		        mimestart_tv = (TextView) findViewById(R.id.tv_01);
		        mimetakepart_tv = (TextView) findViewById(R.id.tv_02);
		   

		        mimestart_tv.setOnClickListener(new MyOnClickListener(0));
		        mimetakepart_tv.setOnClickListener(new MyOnClickListener(1));
		 
		        mimestart_tv.setBackgroundResource(R.drawable.navigationbar_button_send_background);	
		    }	
		
	
	
	
	
	 private void InitViewPager() {
	        mPager = (ViewPager) findViewById(R.id.activity_pager);
	        fragmentsList = new ArrayList<Fragment>();


	      
	       Fragment Fragment01=new MimeStartFragment();
	       Fragment Fragment02=new MimeTakePartFragment();
	       


	     
	         fragmentsList.add(Fragment01);
	         fragmentsList.add(Fragment02);	
	        
	        
	        mPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentsList));
	        mPager.setCurrentItem(0);
	        mPager.setOnPageChangeListener(new MyOnPageChangeListener());
	    }


	 
	 
	  public class MyOnClickListener implements View.OnClickListener {
	        private int index = 0;

	        public MyOnClickListener(int i) {
	            index = i;
	        }

	        @Override
	        public void onClick(View v) {
	            mPager.setCurrentItem(index);
	        }
	    };

	    public class MyOnPageChangeListener implements OnPageChangeListener {

	        @Override
	        public void onPageSelected(int arg0) {

	        
	        	
	            switch (arg0) {
	            case 0:
	                if (currIndex == 1) {
	                  
	                	mimestart_tv.setBackgroundResource(R.drawable.navigationbar_button_send_background);	
	                	mimestart_tv.setTextColor(getResources().getColor(R.color.lightwhite));
	                	
	                } else if (currIndex == 0) {
	                	mimetakepart_tv.setBackgroundResource(0);
	                	mimetakepart_tv.setTextColor(getResources().getColor(R.color.tab_text));
					}  
	                  
	               mimetakepart_tv.setBackgroundResource(0);
	               mimetakepart_tv.setTextColor(getResources().getColor(R.color.tab_text));
	                break;
	            case 1:
	                if (currIndex ==1) {
	                	
	                	 mimestart_tv.setBackgroundResource(0);	
	                	 mimestart_tv.setTextColor(getResources().getColor(R.color.tab_text));
	                } else if (currIndex == 0) {
	                	mimetakepart_tv.setBackgroundResource(R.drawable.navigationbar_button_send_background);
	                	mimetakepart_tv.setTextColor(getResources().getColor(R.color.lightwhite));
					} 
	                
	               mimestart_tv.setBackgroundResource(0);	  
	               mimestart_tv.setTextColor(getResources().getColor(R.color.tab_text));
	             
	                break;

	       
	            }
	            currIndex = arg0;

	        }

	        @Override
	        public void onPageScrolled(int arg0, float arg1, int arg2) {
	        }

	        @Override
	        public void onPageScrollStateChanged(int arg0) {
	        }
	    }
     
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		   case R.id.activity_activity_back:
	        	
			   this.finish();
	        
		        break;  
		
		  
	      
		default:
			break;
		}
	}

	
	

	
	

	
}
