package com.smt.chashizaixian;

import com.smt.util.t.SlipButton;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MimeSettingActivity extends Activity implements OnClickListener {

	
    private TextView activity_setting_back;
	
	private SlipButton sb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mimesetting);
		
		activity_setting_back=(TextView)findViewById(R.id.activity_setting_back);
		activity_setting_back.setOnClickListener(this);
		
		sb=(SlipButton)findViewById(R.id.save_pic);
		sb.setCheck(true);
	}
	
	
	





	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		   case R.id.activity_setting_back:
	        	
			   this.finish();
	        
		        break;  
		
		  
	      
		default:
			break;
		}
	}

	
	

	
	

	
}
