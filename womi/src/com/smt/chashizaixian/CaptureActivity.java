package com.smt.chashizaixian;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.zxing.barcode.util.CameraManager;
import com.zxing.barcode.util.CaptureActivityHandler;
import com.zxing.barcode.util.InactivityTimer;
import com.zxing.barcode.util.ViewfinderView;


/**
 * Initial the camera
 * @author Ryan.Tang
 */
public class CaptureActivity extends Activity implements Callback ,OnClickListener{

	private CaptureActivityHandler handler;
	private ViewfinderView viewfinderView;
	private boolean hasSurface;
	private Vector<BarcodeFormat> decodeFormats;
	private String characterSet;
	private InactivityTimer inactivityTimer;
	private MediaPlayer mediaPlayer;
	private boolean playBeep;
	private static final float BEEP_VOLUME = 0.90f;
	private boolean vibrate;
	
	private TextView capture_back;
	private TextView capture_more;
	
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.camera);
	
		
		CameraManager.init(getApplication());
		viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
		
		
		capture_back=(TextView)findViewById(R.id.activity_sao_back);
		capture_back.setOnClickListener(this);
		capture_more=(TextView)findViewById(R.id.activity_sao_more);
        capture_more.setOnClickListener(this);
		
		hasSurface = false;
		inactivityTimer = new InactivityTimer(this);

	}

	@Override
	protected void onResume() {
		super.onResume();
		//ToastUtil.toastshow(CaptureActivity.this, "执行了了");
		SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
		SurfaceHolder surfaceHolder = surfaceView.getHolder();
		if (hasSurface) {
			initCamera(surfaceHolder);
		} else {
			
			surfaceHolder.addCallback(this);
			surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		}
		decodeFormats = null;
		characterSet = null;

		playBeep = true;
		AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
		if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
			playBeep = false;
		}
		initBeepSound();
		vibrate = true;
		
		
	}

	
	
	@Override
	protected void onPause() {
		super.onPause();
		if (handler != null) {
			handler.quitSynchronously();
			handler = null;
		}
		CameraManager.get().closeDriver();
	}

	@Override
	protected void onDestroy() {
		inactivityTimer.shutdown();
		super.onDestroy();
	}
	
	/**
	 * Handler scan result
	 * @param result
	 * @param barcode
	 */
	public void handleDecode(Result result, Bitmap barcode) {
		inactivityTimer.onActivity();
		playBeepSoundAndVibrate();
	
		final String resultString = result.getText();
		
		
		if (resultString.equals("")) {
			Toast.makeText(CaptureActivity.this, "扫描失败或者二维码无内容!", Toast.LENGTH_SHORT).show();
		}
		
//		else if (resultString.contains("http://q.cha4.net/index.php?app=member&act=getUserInfoApi")) {
//			
//			    Intent intent3 = new Intent(CaptureActivity.this,OtherUserDetailActivity.class);
//				Bundle bundle3 = new Bundle();			
//				bundle3.putString("url", resultString);
//				intent3.putExtras(bundle3);
//				startActivity(intent3);	
//			
//		}
		
		else if (resultString.startsWith("www")) {
		String	url="http://"+resultString;
		    Intent intent2 = new Intent(CaptureActivity.this,IsHttpDialog.class);
			Bundle bundle2 = new Bundle();			
			bundle2.putString("Url", url);
			intent2.putExtras(bundle2);
			startActivity(intent2);	  
		}
		
		else if (resultString.startsWith("http://")) {
			
			
			    Intent intent2 = new Intent(CaptureActivity.this,IsHttpDialog.class);
				Bundle bundle2 = new Bundle();			
				bundle2.putString("Url", resultString);
				intent2.putExtras(bundle2);
				startActivity(intent2);	   
		
		}
		
		
		else {

//			Intent resultIntent = new Intent(CaptureActivity.this,CommentBarcodeActivity.class);
//			Bundle bundle = new Bundle();
//			bundle.putString("content", resultString);
//			resultIntent.putExtras(bundle);
//			startActivity(resultIntent);	   
			
		}
		

		}
	
	
	private void initCamera(SurfaceHolder surfaceHolder) {
		try {
			CameraManager.get().openDriver(surfaceHolder);
		} catch (IOException ioe) {
			return;
		} catch (RuntimeException e) {
			return;
		}
		if (handler == null) {
			handler = new CaptureActivityHandler(this, decodeFormats,
					characterSet);
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (!hasSurface) {
			hasSurface = true;
			initCamera(holder);
		}

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		hasSurface = false;

	}

	public ViewfinderView getViewfinderView() {
		return viewfinderView;
	}

	public Handler getHandler() {
		return handler;
	}

	public void drawViewfinder() {
		viewfinderView.drawViewfinder();

	}

	private void initBeepSound() {
		if (playBeep && mediaPlayer == null) {
			// The volume on STREAM_SYSTEM is not adjustable, and users found it
			// too loud,
			// so we now play on the music stream.
			setVolumeControlStream(AudioManager.STREAM_MUSIC);
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.setOnCompletionListener(beepListener);

			AssetFileDescriptor file = getResources().openRawResourceFd(
					R.raw.qrcode_found);
			try {
				mediaPlayer.setDataSource(file.getFileDescriptor(),
						file.getStartOffset(), file.getLength());
				file.close();
				mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
				mediaPlayer.prepare();
			} catch (IOException e) {
				mediaPlayer = null;
			}
		}
	}

	private static final long VIBRATE_DURATION = 200L;

	private void playBeepSoundAndVibrate() {
		if (playBeep && mediaPlayer != null) {
			mediaPlayer.start();
		}
		if (vibrate) {
			Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
			vibrator.vibrate(VIBRATE_DURATION);
		}
	}

	/**
	 * When the beep has finished playing, rewind to queue up another one.
	 */
	private final OnCompletionListener beepListener = new OnCompletionListener() {
		public void onCompletion(MediaPlayer mediaPlayer) {
			mediaPlayer.seekTo(0);
		}
	};

    
	


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.activity_sao_back:
			this.finish();
			break;

		case R.id.activity_sao_more:

			
			 final CharSequence[] items = { "我的二维码名片"};  
		        AlertDialog dlg = new AlertDialog.Builder(CaptureActivity.this).setTitle("").setItems(items,   
		            new DialogInterface.OnClickListener() {   
		                public void onClick(DialogInterface dialog,int item) {   
		                    //这里item是根据选择的方式,  
		                    //在items数组里面定义了两种方式
		                    if(item==0){   
		                    	Intent intent2 = new Intent(CaptureActivity.this,BarCodeCardActivity.class);		                		
		            			startActivity(intent2);	
		                    }
		                }   
		            }).create();   
		        dlg.show(); 
			
			
			
			break;	
			
			
			
		default:
			break;
		}
	}



	 

	
}