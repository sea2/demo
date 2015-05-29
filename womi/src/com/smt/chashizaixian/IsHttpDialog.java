package com.smt.chashizaixian;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class IsHttpDialog extends Activity {


    private String URL;
    private String loginid;

    private TextView http_tv;
 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ishttpdialog);
		
        
      
        
          Intent intent;
		  Bundle bundle;
		  intent = this.getIntent();		    
		  bundle = intent.getExtras();		   
		  URL=bundle.getString("Url");
          
         http_tv=(TextView)findViewById(R.id.http);
         
         http_tv.setText(URL);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event){
		this.finish();
		return true;
	}
	
	public void open_link(View v) {  
		
//		    Intent intent2 = new Intent(IsHttpDialog.this,WebViewActivity.class);
//			Bundle bundle2 = new Bundle();			
//			bundle2.putString("Url", URL);
//			intent2.putExtras(bundle2);
//			startActivity(intent2);	
//			this.finish();
//		
	}
	
    public void cancel_link(View v) {  
		
    	this.finish();
		
	}			
	
}
