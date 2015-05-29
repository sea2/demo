package com.smt.chashizaixian;

import java.util.Calendar;

import com.smt.config.Constants;
import com.smt.pub.util.Bimp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;

public class StartActivityActivity extends Activity implements OnClickListener {

	
	private TextView activity_back, time_tv, update_time, local_position;
    private View show_local;
    private ImageView local_icon;
    private String localResult;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start_activity);
		
		initView();
		
		
	}

	
	private void initView(){
		
		activity_back=(TextView)findViewById(R.id.activity_back);
		activity_back.setOnClickListener(this);
		time_tv=(TextView)findViewById(R.id.time_tv);
		time_tv.setText(getDate());
		update_time=(TextView)findViewById(R.id.update_time);
		show_local = findViewById(R.id.show_local);
		local_icon = (ImageView)findViewById(R.id.local_icon);
		local_position=(TextView)findViewById(R.id.local_position);
		
		show_local.setOnClickListener(this);
		update_time.setOnClickListener(this);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    switch (requestCode) {
	    case Constants.LOCAL_REQUEST_CODE:
	        boolean local_clear = false;
	        try
	        {
	            localResult = data.getStringExtra(Constants.LOCAL_RESULT);
	        }
	        catch (Exception e)
	        {
	            Log.e("StartActivityActivity", "onActivityResult------>定位当前信息返回出错");
	        }
	        try
	        {
	            local_clear = data.getBooleanExtra(Constants.LOCAL_IF_CLEAR, false);
	        }
	        catch (Exception e)
	        {
	            Log.e("StartActivityActivity", "onActivityResult------>当前位置信息清除标志返回出错");
	        }
	        if (localResult != null)
            {
                local_icon.setImageResource(R.drawable.s_n_dynamic_btn_gps_selected);
                local_position.setText(localResult);
            }
            if (local_clear)
            {
                local_icon.setImageResource(R.drawable.s_n_dynamic_btn_gps_unselected);
                local_position.setText(getResources().getString(R.string.s_position));
            }
	        break;
	    }
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.activity_back:
			this.finish();
			break;
		case R.id.activity_send:
		    this.finish();
		    break;
		case R.id.update_time:	
	     time_tv.setText(getDate());
			break;
		case R.id.show_local:	
		    Intent intent = new Intent(StartActivityActivity.this, LocationActivity.class);
            startActivityForResult(intent, Constants.LOCAL_REQUEST_CODE);
		    break;
		
		default:
			break;
		}
	}
	
	
	private String getDate() {
		Calendar c = Calendar.getInstance();
		String year = String.valueOf(c.get(Calendar.YEAR));
		String month = String.valueOf(c.get(Calendar.MONTH) + 1);
		String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
		String mins = String.valueOf(c.get(Calendar.MINUTE));
		
//		String senonds= String.valueOf(c.get(Calendar.SECOND));
		StringBuffer sbBuffer = new StringBuffer();
		sbBuffer.append(year + "-" + month + "-" + day + " " + hour + ":"
				+ mins);
		return sbBuffer.toString();
	}
	
}
