package com.smt.chashizaixian;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MimeInfoActivity extends Activity implements OnClickListener {

	
	private TextView info_back;
	
	private TextView info_clear;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mimeinfo);
		
		info_back=(TextView)findViewById(R.id.activity_info_back);
		info_back.setOnClickListener(this);
		info_clear=(TextView)findViewById(R.id.activity_info_clear);
		info_clear.setOnClickListener(this);
		
	}





	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.activity_info_back:
			this.finish();
			break;
			
		case R.id.activity_info_clear:
			
			break;

		default:
			break;
		}
	}

	
}
