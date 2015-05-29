package com.smt.chashizaixian;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

public class MimePageActivity extends Activity implements OnClickListener {

	
	private RelativeLayout mime_layout_05;
	private RelativeLayout mime_layout_01;
	private RelativeLayout mime_layout_02;
	private RelativeLayout mime_layout_06;
	
	private RelativeLayout mime_layout_07;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mimepage);
		
		mime_layout_05=(RelativeLayout)findViewById(R.id.mime_layout_05);
		mime_layout_05.setOnClickListener(this);
		
		mime_layout_01=(RelativeLayout)findViewById(R.id.mime_layout_01);
		mime_layout_01.setOnClickListener(this);
		
		mime_layout_02=(RelativeLayout)findViewById(R.id.mime_layout_02);
		mime_layout_02.setOnClickListener(this);
		
		mime_layout_06=(RelativeLayout)findViewById(R.id.mime_layout_06);
		mime_layout_06.setOnClickListener(this);
		
		mime_layout_07=(RelativeLayout)findViewById(R.id.mime_layout_07);
		mime_layout_07.setOnClickListener(this);
		
		
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.mime_layout_05:
			
			Intent intent=new Intent(MimePageActivity.this, MimeDetailActivity.class);
			startActivity(intent);
			
			break;
			
        case R.id.mime_layout_01:
			
			Intent photointent=new Intent(MimePageActivity.this, MimePhotoActivity.class);
			startActivity(photointent);
			
			break;
			
         case R.id.mime_layout_02:
			
			Intent gcontentintent=new Intent(MimePageActivity.this, MimeGroupContentActivity.class);
			startActivity(gcontentintent);
			
			break;
			
         case R.id.mime_layout_06:
 			
 			Intent activityintent=new Intent(MimePageActivity.this, MimeActivityActivity.class);
 			startActivity(activityintent);
 			
 			break;
         case R.id.mime_layout_07:
  			
  			Intent setintent=new Intent(MimePageActivity.this, MimeSettingActivity.class);
  			startActivity(setintent);
  			
  			break;

		default:
			break;
		}
	}

	
}
