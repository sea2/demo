package com.smt.chashizaixian;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MimeNewFriendsActivity extends Activity implements OnClickListener {

	private TextView new_back;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mimenewfriend);
		
		
		new_back=(TextView)findViewById(R.id.activity_new_back);
		new_back.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.activity_new_back:
			
			this.finish();
			
			break;

		default:
			break;
		}
	}

	
}
