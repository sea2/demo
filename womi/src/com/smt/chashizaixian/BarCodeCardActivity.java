package com.smt.chashizaixian;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.smt.imageload.util.ImageLoader;
import com.zxing.barcode.util.EncodingHandler;


public class BarCodeCardActivity extends Activity implements OnClickListener{

	

	private TextView barback;
    private ImageView qrImgImageView;
    private ImageView bar_img;
    private TextView bar_name;
    private TextView bar_id;
    
	/** 生成二维码图片大小 */
	private static final int QRCODE_SIZE = 400;
	/** 头像图片大小 */
	private static final int PORTRAIT_SIZE = 80;
    
	/** 头像图片 */
	private Bitmap portrait;
	
	private TextView card_share_btn;
	
	private String barcontent;
	
	  private ImageLoader mImageLoader;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);	
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.barcode);
		
		  Intent intent;
		  Bundle bundle;
		  intent = this.getIntent();		    
		  bundle = intent.getExtras();
		 
//		  String user_id=bundle.getString("uid");
//		  String user_name=bundle.getString("name");
//		  String user_img=bundle.getString("img");
		  
		  String user_id="510";
		  String user_name="比邻特——邻居的耳朵";
		  String user_img="";
		
		  
		
		   barback=(TextView)findViewById(R.id.activity_qrcard_back);
		   barback.setOnClickListener(this);
		   
		   card_share_btn=(TextView)findViewById(R.id.activity_qrcard_share);
		   card_share_btn.setOnClickListener(this);
		   
		   mImageLoader=new ImageLoader(this);
		   
//		   bar_img=(ImageView)findViewById(R.id.bar_iv);
//		   bar_name=(TextView)findViewById(R.id.bar_name);
//		   bar_id=(TextView)findViewById(R.id.bar_id);
//	
//		  bar_name.setText(user_name);
//		  bar_id.setText(user_id);
//		  mImageLoader.DisplayImage(user_img, bar_img, false);
		
		  barcontent="http://q.cha4.net/index.php?app=member&act=getUserInfoApi&fid="+user_id;
	
		  qrImgImageView=(ImageView)findViewById(R.id.barcode_pic);
		
		// 初始化头像
		Bitmap touxiang=mImageLoader.getCaechBitmap(user_img);
		
	
		
		if (touxiang!=null) {
			
			
			//portrait=initProtrait(touxiang);	
			Bitmap aBitmap;
			try {
				aBitmap = BitmapFactory.decodeStream(getAssets().open("tou.png"));
				
				// 这里采用从asset中加载图片
				  portrait =initProtrait(aBitmap);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else {
			Bitmap aBitmap;
			try {
				aBitmap = BitmapFactory.decodeStream(getAssets().open("tou.png"));
				
				// 这里采用从asset中加载图片
				  portrait =initProtrait(aBitmap);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		Bitmap qrCodeBitmap = null;

		qrCodeBitmap = EncodingHandler.createQRCodeBitmap(QRCODE_SIZE,barcontent);
		
		
		createQRCodeBitmapWithPortrait(qrCodeBitmap, portrait);
		
		
		qrImgImageView.setImageBitmap(qrCodeBitmap);
	
		savePicture(qrCodeBitmap);
}

	
	/**
	 * 初始化头像图片
	 */
	private Bitmap initProtrait(Bitmap bitmap) {

			// 对原有图片压缩显示大小
			Matrix mMatrix = new Matrix();
			float width = bitmap.getWidth();
			float height = bitmap.getHeight();
			mMatrix.setScale(PORTRAIT_SIZE / width, PORTRAIT_SIZE / height);
			
			return Bitmap.createBitmap(bitmap, 0, 0, (int) width,
					(int) height, mMatrix, true);
		
	
	}
	
	/**
	 * 在二维码上绘制头像
	 */
	private void createQRCodeBitmapWithPortrait(Bitmap qr, Bitmap portrait) {
		// 头像图片的大小
		int portrait_W = portrait.getWidth();
		int portrait_H = portrait.getHeight();

		// 设置头像要显示的位置，即居中显示
		int left = (QRCODE_SIZE - portrait_W) / 2;
		int top = (QRCODE_SIZE - portrait_H) / 2;
		int right = left + portrait_W;
		int bottom = top + portrait_H;
		Rect rect1 = new Rect(left, top, right, bottom);

		// 取得qr二维码图片上的画笔，即要在二维码图片上绘制我们的头像
		Canvas canvas = new Canvas(qr);

		// 设置我们要绘制的范围大小，也就是头像的大小范围
		Rect rect2 = new Rect(0, 0, portrait_W, portrait_H);
		// 开始绘制
		canvas.drawBitmap(portrait, rect2, rect1, null);
	}

	
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.activity_qrcard_back:
		   BarCodeCardActivity.this.finish();
			break;
			
		case R.id.activity_qrcard_share:	
			
			String shareName = "这是我的茶友帮二维码名片，扫描之后可以加我为好友。";
            Intent intentItem = new Intent(Intent.ACTION_SEND); //分享发送的数据类型
            intentItem.setType("image/png"); 
            
            File picture = new File(Environment.getExternalStorageDirectory()
    				+ "/myImage/tempQRcode.png");
    		
    		 Uri uri=Uri.fromFile(picture);
    	
            intentItem.putExtra(Intent.EXTRA_STREAM, uri); 
            
            intentItem.setType("image/*"); //分享发送的数据类型
            intentItem.putExtra(Intent.EXTRA_SUBJECT, "名片分享"); //分享的主题 
            intentItem.putExtra(Intent.EXTRA_TEXT, shareName); //分享的内容
            intentItem.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//这个也许是分享列表的背景吧 
            startActivity(Intent.createChooser(intentItem, "分享"));//目标应用选择对话框的标题 

			
			break;

		default:
			break;
		}
	}
	
	
	
	
	
        public void savePicture(Bitmap bitmap) { 

  	    String sdStatus = Environment.getExternalStorageState(); 
  	    if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用 
  	  
  	        return; 
  	    } 
  	    FileOutputStream b = null; 
  	    File file = new File("/sdcard/myImage/"); 
  	    file.mkdirs();// 创建文件夹 
  	    String fileName = "/sdcard/myImage/tempQRcode.png"; 
  	  
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
	
        
      
        
       
	
	
}