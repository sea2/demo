package com.smt.chashizaixian;


import java.io.IOException;
import java.util.HashMap;


import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.smt.util.adapter.SearchPeopleAdapter;
import com.smt.util.t.ToastUtil;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.location.Location;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class RadarActivity extends Activity implements OnClickListener,OnTouchListener,AMapLocationListener {
	
	private boolean playBeep;
	private MediaPlayer mediaPlayer;
	private static final float BEEP_VOLUME = 0.80f;
	private Button radar_back;

	private Button radar_btn;
	private TextView xx_tv;
	
	 Animation operatingAnimation;
	 private ImageView rotation_img;
     private LinearLayout rotation_rl;
     private boolean hasGot;//是否已经获得当前位置
     private LocationManagerProxy mAMapLocManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.radar);
		
		mAMapLocManager = LocationManagerProxy.getInstance(this);
        mAMapLocManager.requestLocationUpdates(LocationProviderProxy.AMapNetwork, 5000, 10, this);
        
		radar_back=(Button)findViewById(R.id.radar_back);
		radar_back.setOnClickListener(this);
		radar_btn=(Button)findViewById(R.id.radar_btn);
		radar_btn.setOnTouchListener(this);
		xx_tv=(TextView)findViewById(R.id.xx_tv);
		rotation_img=(ImageView)findViewById(R.id.radar_rotation);	
		rotation_rl=(LinearLayout)findViewById(R.id.rotation_rl);
		initmplayer();
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.radar_back:
			this.finish();
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
			
			//initmplayer();
			playBeepSound();
			rotation_rl.setVisibility(View.VISIBLE);
			radar_back.setVisibility(View.GONE);
			xx_tv.setVisibility(View.GONE);
			radar_rotation();
			
			
			break;
			
			
		case MotionEvent.ACTION_UP:
		
			StopBeepSound();
			rotation_img.clearAnimation();
			rotation_rl.setVisibility(View.GONE);
			radar_back.setVisibility(View.VISIBLE);
			xx_tv.setVisibility(View.VISIBLE);
			playBeep = true;

			
			break;
		}
		
		
		return false;
	}

	
	private void playBeepSound() {
		if (playBeep && mediaPlayer != null) {
			mediaPlayer.start();
		}
		
	}

	private void StopBeepSound() {
		if (playBeep && mediaPlayer != null) {

			mediaPlayer.pause();
		}
		
	}
	
	
	private void initBeepSound() {
		if (playBeep && mediaPlayer == null) {
			// The volume on STREAM_SYSTEM is not adjustable, and users found it
			// too loud,
			// so we now play on the music stream.
			setVolumeControlStream(AudioManager.STREAM_MUSIC);
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.setLooping(true); 
			mediaPlayer.setOnCompletionListener(beepListener);

			AssetFileDescriptor file = getResources().openRawResourceFd(
					R.raw.radarsound);
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
	/**
	 * When the beep has finished playing, rewind to queue up another one.
	 */
	private final OnCompletionListener beepListener = new OnCompletionListener() {
		public void onCompletion(MediaPlayer mediaPlayer) {
			mediaPlayer.seekTo(0);
		}
	};
	
	
	@Override
	protected void onDestroy() {
		
      
		if ( mediaPlayer != null){
        	mediaPlayer.stop();	
		}
        
		super.onDestroy();
	} 
	
	private void initmplayer(){
		
		playBeep = true;
		AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
		if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
			playBeep = false;
		}
		initBeepSound();
	}
	
	
	private void radar_rotation(){
		
		// 设置图片旋转动画的效果    ，R.anim.tip为动画文件。
        operatingAnimation = AnimationUtils.loadAnimation(RadarActivity.this, R.anim.rotation);
        LinearInterpolator lin = new LinearInterpolator(); // 设置旋转速度 此处设置为匀速旋转
        operatingAnimation.setInterpolator(lin);//将旋转速度配置给动画。
        rotation_img.startAnimation(operatingAnimation);
		
		
	}
	
	/**
	 * 在此处理获得当前经纬度之后的后续动作
	 */
	@Override
    public void onLocationChanged(AMapLocation location)
    {
	    if (location != null) {
            if (!hasGot)
            {
                Double geoLat = location.getLatitude();//纬度
                Double geoLng = location.getLongitude();//经度
                
                Toast.makeText(this, "当前经纬度：(" + geoLng + "," + geoLat + ")", Toast.LENGTH_SHORT).show();
            }
            hasGot = true;
        }
    }
    @Override
    public void onLocationChanged(Location location)
    {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void onProviderDisabled(String provider)
    {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void onProviderEnabled(String provider)
    {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras)
    {
        // TODO Auto-generated method stub
        
    }
}
