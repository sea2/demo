package com.smt.chashizaixian;


import com.smt.util.t.IsMobile;
import com.smt.util.t.ToastUtil;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

public class UpdateSignatureActivity extends Activity implements OnClickListener {

	private EditText signature_et;
    private String loginid;

    private  String mimecontent;
   private TextView editdetail_back;
    private TextView editdetail_finish;
    private int code;
    private TextView edit_title;
    private String titleString;
    private int num;
    
    private String resultcontent;
    
   private TextView edit_num;
   private int len;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.updatesignature);

      
        
          Intent intent;
		  Bundle bundle;
		  intent = this.getIntent();		    
		  bundle = intent.getExtras();		   		
		  mimecontent=bundle.getString("mimecontent");
		  code=bundle.getInt("resultcode");
		  titleString=bundle.getString("title");
		  num=bundle.getInt("num"); 
		  
		  edit_num=(TextView)findViewById(R.id.edit_num);
		  
		 
		  
		  
		  signature_et=(EditText)findViewById(R.id.signature_et);		  
		  signature_et.setText(mimecontent);
		  
		  resultcontent = signature_et.getText().toString();
		  len = resultcontent.length();
		  len=num-len;
		  edit_num.setText(len+"/"+num);
		  
		  
		  edit_title=(TextView)findViewById(R.id.edit_title);
		  
		  edit_title.setText(titleString);
		

		  editdetail_finish=(TextView)findViewById(R.id.editdetail_finish);
		  editdetail_finish.setOnClickListener(this);
		  editdetail_back=(TextView)findViewById(R.id.editdetail_back);
		  editdetail_back.setOnClickListener(this);
		  
		  
		  
		  signature_et.addTextChangedListener(new TextWatcher() {
				
				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {
					// TODO Auto-generated method stub
					resultcontent = signature_et.getText().toString();
						len = resultcontent.length();
						if (len <= num) {
							len = num - len;
							edit_num.setTextColor(getResources().getColor(R.color.gray));
						    edit_num.setText(String.valueOf(len)+"/"+num);
							}
						
						 else {					
							len = len - num;
							edit_num.setTextColor(Color.RED);
							edit_num.setText("-"+String.valueOf(len)+"/"+num);

						}

				}
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count,
						int after) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void afterTextChanged(Editable s) {
					// TODO Auto-generated method stub
					
				}
			});
	}
     
	

	
	
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.editdetail_finish:
			

			resultcontent=signature_et.getText().toString().trim();
            int length=resultcontent.length(); 
            
            if (titleString.equals("手机")) {
				
            	 if (!IsMobile.isMobileNO(resultcontent)) {
                
            		 ToastUtil.toastshow(UpdateSignatureActivity.this, "请正确填写手机号码");		 
            		 
            		return; 
     			}	
            	
            	
			}
           
            
            
			if (length>num) {	
			ToastUtil.toastshow(UpdateSignatureActivity.this, "字数超过限制字数");	
				return;
			}
			
			Intent resultIntent = new Intent();
			Bundle bundle = new Bundle();
		    bundle.putString("result", resultcontent);							
			resultIntent.putExtras(bundle);
			UpdateSignatureActivity.this.setResult(code, resultIntent);	
			
			this.finish();
			
			break;
			
        case R.id.editdetail_back:
			
			
			UpdateSignatureActivity.this.finish();
			
			break;

		default:
			break;
		}
	}

	
 
	
			
			
	
}
