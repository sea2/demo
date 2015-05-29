package com.smt.chashizaixian;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AddFriendsActivity extends Activity implements OnClickListener,OnTouchListener{

	private ImageView radar_iv;
	private TextView add_back;
	
	private RelativeLayout add_layout_01;
	private RelativeLayout add_layout_05;
	private RelativeLayout add_layout_03;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addfriends);
		
		radar_iv=(ImageView)findViewById(R.id.radar_btn);
		add_back=(TextView)findViewById(R.id.add_back);
		add_layout_01=(RelativeLayout)findViewById(R.id.add_layout_01);
		
		radar_iv.setOnTouchListener(this);
		add_back.setOnClickListener(this);
		add_layout_01.setOnClickListener(this);
		
		
		add_layout_01=(RelativeLayout)findViewById(R.id.add_layout_01);
		add_layout_01.setOnClickListener(this);
		
		add_layout_05=(RelativeLayout)findViewById(R.id.add_layout_07);
		add_layout_05.setOnClickListener(this);
		
		add_layout_03=(RelativeLayout)findViewById(R.id.add_layout_03);
		add_layout_03.setOnClickListener(this);
	}

    @Override
    public void onClick(View v)
    {
       switch (v.getId()) {
	  case R.id.add_back:
		this.finish();
		break;
		
	  case R.id.add_layout_01:
	
		 startActivity(new Intent(AddFriendsActivity.this, SearchUserActivity.class)); 
		  
			break;
			
	  case R.id.add_layout_07:
			
			 startActivity(new Intent(AddFriendsActivity.this, CaptureActivity.class)); 
			  
				break;
				
	  case R.id.add_layout_03:
			
			 startActivity(new Intent(AddFriendsActivity.this, AddFromContactsActivity.class)); 
			  
				break;

	default:
		break;
	}
    }

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction())
		{
		case MotionEvent.ACTION_DOWN:
			
		
		    Intent intent=new Intent(AddFriendsActivity.this, RadarActivity.class);
	        startActivity(intent);
	        this.finish();
			
			break;
			
	
		}
		

		return false;
	}
}
