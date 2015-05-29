package com.smt.chashizaixian;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smt.util.t.SexySlipButton;
import com.smt.util.t.SexySlipButton.OnChangedListener;


public class MimeDetailActivity extends Activity implements OnClickListener {

	
	
	private  TextView detail_back;
	private ImageView detail_user_img;

    private String id;
    private String username;
    private String userimg;
    
  
    private TextView nick;   
    private RelativeLayout signature_rl;
    private RelativeLayout user_pic;
    private RelativeLayout nickname;    
    private RelativeLayout area;
    private RelativeLayout sex_rl;
    
    private String signatrue;
    private TextView write_tv;
    private String mnick;
    private static final int PHOTO_SUCCESS = 1;  
	private static final int CAMERA_SUCCESS = 2;   
	private static final int NONE=0;
	private static final int PHOTORESOULT = 3;// 结果	
	public static final String IMAGE_UNSPECIFIED = "image/*";
    
	private TextView area_tv;
	private TextView sex_tv;
    
	private Button detail_weibo_save_btn;
	
	private RelativeLayout detail_rl_06;
	
	private TextView detail_tv_06;
	
	private RelativeLayout detail_rl_07;
	private TextView detail_tv_07;
	
	private RelativeLayout detail_rl_08;
	private TextView detail_tv_08;
	
	private RelativeLayout detail_rl_09;
	private TextView detail_tv_09;
	
	private RelativeLayout detail_rl_10;
	private TextView detail_tv_10;
	private RelativeLayout detail_rl_11;
	private TextView detail_tv_11;
	private RelativeLayout detail_rl_12;
	private TextView detail_tv_12;
	private RelativeLayout detail_rl_13;
	private TextView detail_tv_13;
	
	private String phone;
	private String mail;
	private String qq;
	
	private SexySlipButton ssb;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mimedetail);
	  
		
		initView();
	    
	    ssb=(SexySlipButton)findViewById(R.id.show_sex);
	    ssb.setCheck(true);
	    setListener();
	}


	private void initView(){
		
		
		detail_back=(TextView)findViewById(R.id.mimedetail_back);
		detail_back.setOnClickListener(this);
		
		detail_user_img=(ImageView)findViewById(R.id.detail_img);
	 
	    nick=(TextView)findViewById(R.id.nick_name);
	    nick.setText("比邻特");
	    
	    signature_rl=(RelativeLayout)findViewById(R.id.signature_rl);
	    signature_rl.setOnClickListener(this);
	    
	    user_pic=(RelativeLayout)findViewById(R.id.layout);
	    user_pic.setOnClickListener(this);
	    
	    nickname=(RelativeLayout)findViewById(R.id.nickname_rl);
	    nickname.setOnClickListener(this);
	    
	    area=(RelativeLayout)findViewById(R.id.area_rl);
	    area.setOnClickListener(this);
	    
	    sex_rl=(RelativeLayout)findViewById(R.id.sex_rl);
	    sex_rl.setOnClickListener(this);
	    
	    write_tv=(TextView)findViewById(R.id.write);
	    area_tv=(TextView)findViewById(R.id.place);
	    
	    sex_tv=(TextView)findViewById(R.id.sexy_tv);
	  
	    
	    detail_rl_06=(RelativeLayout)findViewById(R.id.detail_rl_06);
	    detail_rl_06.setOnClickListener(this);	    
	    detail_tv_06=(TextView)findViewById(R.id.detail_tv_06);
	    
	  
	    detail_rl_07=(RelativeLayout)findViewById(R.id.detail_rl_07);
	    detail_rl_07.setOnClickListener(this);	    
	    detail_tv_07=(TextView)findViewById(R.id.detail_tv_07);
	    
	    detail_rl_08=(RelativeLayout)findViewById(R.id.detail_rl_08);
	    detail_rl_08.setOnClickListener(this);	    
	    detail_tv_08=(TextView)findViewById(R.id.detail_tv_08);
	    
	    detail_rl_09=(RelativeLayout)findViewById(R.id.detail_rl_09);
	    detail_rl_09.setOnClickListener(this);	    
	    detail_tv_09=(TextView)findViewById(R.id.detail_tv_09);
	    
	    detail_rl_10=(RelativeLayout)findViewById(R.id.detail_rl_10);
	    detail_rl_10.setOnClickListener(this);	    
	    detail_tv_10=(TextView)findViewById(R.id.detail_tv_10);
		
	    detail_rl_11=(RelativeLayout)findViewById(R.id.detail_rl_11);
	    detail_rl_11.setOnClickListener(this);	    
	    detail_tv_11=(TextView)findViewById(R.id.detail_tv_11);
	    
	    detail_rl_12=(RelativeLayout)findViewById(R.id.detail_rl_12);
	    detail_rl_12.setOnClickListener(this);	    
	    detail_tv_12=(TextView)findViewById(R.id.detail_tv_12);
	    
	    detail_rl_13=(RelativeLayout)findViewById(R.id.detail_rl_13);
	    detail_rl_13.setOnClickListener(this);	    
	    detail_tv_13=(TextView)findViewById(R.id.detail_tv_13);
		
	}
	
	
	private void setListener()
    {
        ssb.SetOnChangedListener(new OnChangedListener()
        {

            public void OnChanged(boolean CheckState)
            {
                if(CheckState){
                	
                	sex_tv.setText("男");
                	
                    
                }
                else{
                   sex_tv.setText("女");
                }
              
            }
        });
    }
	
	


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	switch (v.getId()) {
	case R.id.mimedetail_back:
		
		this.finish();
		
		break;
		
        case R.id.signature_rl:
		signatrue=write_tv.getText().toString().trim();
        Intent intent=new Intent(MimeDetailActivity.this, UpdateSignatureActivity.class);
        Bundle bundle= new Bundle();
        bundle.putInt("resultcode", 8);
		bundle.putString("title","个性签名");
		bundle.putString("mimecontent",signatrue);
		bundle.putInt("num", 75);
		intent.putExtras(bundle);        
	    startActivityForResult(intent, 8);
		
		break;
		
        case R.id.nickname_rl:
    		mnick=nick.getText().toString().trim();
            Intent intent1=new Intent(MimeDetailActivity.this, UpdateSignatureActivity.class);
            Bundle bundle1= new Bundle();
            bundle1.putInt("resultcode", 9);
            bundle1.putString("title","昵称");
    		bundle1.putString("mimecontent",mnick);
    		bundle1.putInt("num", 15);
    		intent1.putExtras(bundle1);        
    		startActivityForResult(intent1, 9);
    		
    		break;
    		
        case R.id.detail_rl_07:
    		phone=detail_tv_07.getText().toString().trim();
            Intent intent10=new Intent(MimeDetailActivity.this, UpdateSignatureActivity.class);
            Bundle bundle10= new Bundle();
            bundle10.putInt("resultcode", 10);
            bundle10.putString("title","手机");
    		bundle10.putString("mimecontent",phone);
    		 bundle10.putInt("num", 11);
    		intent10.putExtras(bundle10);        
    		startActivityForResult(intent10, 10);
    		
    		break;	
    	
        case R.id.detail_rl_08:
    		mail=detail_tv_08.getText().toString().trim();
            Intent intent11=new Intent(MimeDetailActivity.this, UpdateSignatureActivity.class);
            Bundle bundle11= new Bundle();
            bundle11.putInt("resultcode", 11);
            bundle11.putString("title","邮箱");
    		bundle11.putString("mimecontent",mail);
    		bundle11.putInt("num", 50);
    		intent11.putExtras(bundle11);        
    		startActivityForResult(intent11, 11);
    		
    		break;	
    		
        case R.id.detail_rl_09:
    		qq=detail_tv_09.getText().toString().trim();
            Intent intent12=new Intent(MimeDetailActivity.this, UpdateSignatureActivity.class); 
            Bundle bundle12= new Bundle();
            bundle12.putInt("resultcode", 12);
            bundle12.putString("title","QQ");
    		bundle12.putString("mimecontent",qq);
    		bundle12.putInt("num", 15);
    		intent12.putExtras(bundle12);        
    		startActivityForResult(intent12, 12);
    		
    		break;	
    		
    		
        case R.id.detail_rl_10:
    		String school=detail_tv_10.getText().toString().trim();
            Intent intent13=new Intent(MimeDetailActivity.this, UpdateSignatureActivity.class); 
            Bundle bundle13= new Bundle();
            bundle13.putInt("resultcode", 13);
            bundle13.putString("title","学校");
            bundle13.putString("mimecontent",school);
            bundle13.putInt("num", 30);
            intent13.putExtras(bundle13);        
    		startActivityForResult(intent13, 13);   		
    		break;	
        
    		
        case R.id.detail_rl_11:
    		String company=detail_tv_11.getText().toString().trim();
            Intent intent14=new Intent(MimeDetailActivity.this, UpdateSignatureActivity.class); 
            Bundle bundle14= new Bundle();
            bundle14.putInt("resultcode", 14);
            bundle14.putString("title","公司");
            bundle14.putString("mimecontent",company);
            bundle14.putInt("num", 30);
            intent14.putExtras(bundle14);        
    		startActivityForResult(intent14, 14);   		
    		break;
		
    		
        case R.id.detail_rl_12:
    		String work=detail_tv_12.getText().toString().trim();
            Intent intent15=new Intent(MimeDetailActivity.this, UpdateSignatureActivity.class); 
            Bundle bundle15= new Bundle();
            bundle15.putInt("resultcode", 15);
            bundle15.putString("title","职业");
            bundle15.putString("mimecontent",work);
            bundle15.putInt("num", 30);
            intent15.putExtras(bundle15);        
    		startActivityForResult(intent15, 15);   		
    		break;
    		
        case R.id.detail_rl_13:
    		String love=detail_tv_13.getText().toString().trim();
            Intent intent16=new Intent(MimeDetailActivity.this, UpdateSignatureActivity.class); 
            Bundle bundle16= new Bundle();
            bundle16.putInt("resultcode", 16);
            bundle16.putString("title","兴趣");
            bundle16.putString("mimecontent",love);
            bundle16.putInt("num", 100);
            intent16.putExtras(bundle16);        
    		startActivityForResult(intent16, 16);   		
    		break;
    		
    		
        case R.id.layout:
    		
        	 final CharSequence[] items = { "手机相册", "相机拍摄" };  
		        AlertDialog dlg = new AlertDialog.Builder(MimeDetailActivity.this).setTitle("选择图片").setItems(items,   
		            new DialogInterface.OnClickListener() {   
		                public void onClick(DialogInterface dialog,int item) {   
		                    //这里item是根据选择的方式,  
		                    //在items数组里面定义了两种方式, 拍照的下标为1所以就调用拍照方法         
		                    if(item==1){   
		                        Intent getImageByCamera= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		                        
		                        getImageByCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(
		        						Environment.getExternalStorageDirectory(), "myImage/newtemp.jpg")));
		                        
		                        startActivityForResult(getImageByCamera, CAMERA_SUCCESS);     
		                    }else{ 
		                    	
		                        Intent getImage = new Intent(Intent.ACTION_GET_CONTENT); 		                       		                	                        
		                        getImage.addCategory(Intent.CATEGORY_OPENABLE);   
		                        getImage.setType("image/*");   
		                        startActivityForResult(getImage, PHOTO_SUCCESS);   
		                     }   
		                }   
		            }).create();   
		        dlg.show(); 
    		
    		break;	
		
        case R.id.area_rl:
    		
        	Intent iii = new Intent(MimeDetailActivity.this, provinceList.class);
			startActivityForResult(iii, 5);
		
    		
    		break;
    		
      
    		
         case R.id.detail_rl_06:
    		
//        	Intent idate = new Intent(MimeDetailActivity.this, WheelViewActivity.class);
//        	
//        	startActivityForResult(idate, 7); 
        	
			
			
    		
    		break;	
    		

	default:
		break;
	}	
		
		
		
	}

	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		 getContentResolver(); 
		
		
		if (resultCode == NONE)
			return;
		// 拍照
		if (requestCode == CAMERA_SUCCESS) {
			
			// 设置文件保存路径这里放在跟目录下
			File picture = new File(Environment.getExternalStorageDirectory()
					+ "/myImage/newtemp.jpg");
			//System.out.println("------------------------" + picture.getPath());
			startPhotoZoom(Uri.fromFile(picture));
		}

		if (data == null)
		{				
			return;
		}
		// 读取相册缩放图片
		if (requestCode ==PHOTO_SUCCESS) {
			
			startPhotoZoom(data.getData());
	          
		}
		  // 处理结果
		  if (requestCode == PHOTORESOULT) {
			Bundle extras = data.getExtras();
			if (extras != null) {
				
				Bitmap photo = extras.getParcelable("data");				
				savePicture(photo);				
				//这边要处理提交头像的操作
			//	xxxxxxxxxxxxxx
			}

		}
		  
		  if (requestCode ==5) {
			 String cityname;
			 String provincename;
			 provincename=data.getStringExtra("provincename");
			 cityname = data.getStringExtra("cityname");
			 if (cityname.equals("北京市")||cityname.equals("天津市")||cityname.equals("上海市")||cityname.equals("重庆市")||cityname.equals("台湾省")||cityname.equals("香港特别行政区")||cityname.equals("澳门特别行政区")) {
				
				 area_tv.setText(cityname);
				}
				else {

					area_tv.setText(provincename+" "+cityname);	
				}
			   
				
				//成功修改之后才将省市改掉
				
				

			}
		  
		  if (requestCode ==6) {
				   String sex;				
				   sex=data.getStringExtra("sex");
					sex_tv.setText(sex);

				}
		  
		  if (requestCode ==7) {
			   String birth;				
			   birth=data.getStringExtra("birth");
			   detail_tv_06.setText(birth);

			}
		  
		  
		  if (requestCode ==8) {
			  
			   String intro;				
			   intro=data.getStringExtra("result");
			   write_tv.setText(intro);

			}
		  
		  if (requestCode ==9) {
			  
			   String nickname;				
			   nickname=data.getStringExtra("result");
			   nick.setText(nickname);

			}
		  
		  if (requestCode ==10) {
			  
			   String phone;				
			   phone=data.getStringExtra("result");
			   detail_tv_07.setText(phone);

			}
		  
		  if (requestCode ==11) {
			  
			   String email;				
			   email=data.getStringExtra("result");
			   detail_tv_08.setText(email);

			}
		  
		  if (requestCode ==12) {
			  
			   String QQ;				
			   QQ=data.getStringExtra("result");
			   detail_tv_09.setText(QQ);

			}
		  
		  
		  if (requestCode ==13) {
			  
			   String school;				
			   school=data.getStringExtra("result");
			   detail_tv_10.setText(school);

			}
		  
		  if (requestCode ==14) {
			  
			   String company;				
			   company=data.getStringExtra("result");
			   detail_tv_11.setText(company);

			}
		  
		  if (requestCode ==15) {
			  
			   String work;				
			   work=data.getStringExtra("result");
			   detail_tv_12.setText(work);

			}
		  
		  if (requestCode ==16) {
			  
			   String love;				
			   love=data.getStringExtra("result");
			   detail_tv_13.setText(love);

			}
		  		  
		super.onActivityResult(requestCode, resultCode, data);
		
		
	}	
	
//	     @Override
//		protected void onActivityResult(int resultCode, int re, Intent data) {
//			String cityname;
//			String provincename;
//			if (data == null) {
//
//				return;
//			}
//			cityname = data.getStringExtra("cityname");
//			provincename=data.getStringExtra("provincename");
//			area_tv.setText(provincename+"  "+cityname);
//		}
	
	
	
	/* 将压缩图片保存到自定义的文件中
	 * Bitmap类有一compress成员，可以把bitmap保存到一个stream中。
	 */
	@SuppressLint("SdCardPath")
	public void savePicture(Bitmap bitmap) { 
		     
		
		
	    String sdStatus = Environment.getExternalStorageState(); 
	    if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用 
	      //  Log.v("TestFile", "SD card is not avaiable/writeable right now."); 
	        return; 
	    } 
	    FileOutputStream b = null; 
	    File file = new File("/sdcard/myImage/"); 
	    file.mkdirs();// 创建文件夹 
	    String fileName = "/sdcard/myImage/temp.jpg"; 
	  
	    try { 
	        b = new FileOutputStream(fileName); 
	        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件 
	    } catch (FileNotFoundException e) { 
	        e.printStackTrace(); 
         } 
	    finally { 
	        try { 
	            b.flush(); 
	            b.close();     
	        } catch (IOException e) { 
	            e.printStackTrace(); 
	        } 
	    } 
	}
	
	 /*
	   * 进行图片剪裁
	   */
		public void startPhotoZoom(Uri uri) {
			Intent intent = new Intent("com.android.camera.action.CROP");
			intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
			intent.putExtra("crop", "true");
			// aspectX aspectY 是宽高的比例
			intent.putExtra("aspectX", 1);
			intent.putExtra("aspectY", 1);
			// outputX outputY 是裁剪图片宽高
			intent.putExtra("outputX",640);
			intent.putExtra("outputY",640);
			intent.putExtra("return-data", true);
			startActivityForResult(intent, PHOTORESOULT);
		}
	
	
	
	private	void scanOldImageFile(){
		File file = new File(Environment.getExternalStorageDirectory(), "/myImage/newtemp.jpg");
		File file1 = new File(Environment.getExternalStorageDirectory(), "/myImage/temp.jpg");
		if(file.exists()||file1.exists()){
			file.delete();
			file1.delete();
		}
		
		
	}
	
	
	//获取文件手机路径
	private String getImagePath(){
		
		
		File file=new File(Environment.getExternalStorageDirectory(), "/myImage/temp.jpg");			
		return file.getAbsolutePath();
	}

	

	
	
	  @Override
	   public void onDestroy(){
		   
		   scanOldImageFile();
		   
		   super.onDestroy();
	   }
	
}