package com.smt.chashizaixian;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;




@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity implements OnClickListener {
	public static String TAB_TAG_HOME = "home";
	public static String TAB_TAG_CHANNEL = "channel";
	public static String TAB_TAG_ACCOUNT = "account";
	public static String TAB_TAG_SEARCH = "search";
	public static String TAB_TAB_MORE = "more";
	public static TabHost mTabHost;
	static final int COLOR1 = Color.parseColor("#838992");
	static final int COLOR2 = Color.parseColor("#b87721");
	ImageView mBut1, mBut2, mBut3, mBut4, mBut5;
	TextView mCateText1, mCateText2, mCateText3, mCateText4, mCateText5;

	Intent mshouyeIntent, mHomeItent, mChannelIntent, mSearchIntent,mMoreIntent;

	

	int mCurTabId = R.id.channel3;
	
	// Animation
	private Animation left_in, left_out;
	private Animation right_in, right_out;



	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		prepareAnim();
		prepareIntent();
		setupIntent();
		prepareView();
		 				    
	}
	

	private void prepareAnim() {
		left_in = AnimationUtils.loadAnimation(this, R.anim.left_in);
		left_out = AnimationUtils.loadAnimation(this, R.anim.left_out);
		right_in = AnimationUtils.loadAnimation(this, R.anim.right_in);
		right_out = AnimationUtils.loadAnimation(this, R.anim.right_out);
	}

	private void prepareView() {
		mBut1 = (ImageView) findViewById(R.id.imageView1);
		mBut2 = (ImageView) findViewById(R.id.imageView2);
        mBut3=(ImageView)findViewById(R.id.imageView3);
		mBut4 = (ImageView) findViewById(R.id.imageView4);
		mBut5 = (ImageView) findViewById(R.id.imageView5);
		findViewById(R.id.channel1).setOnClickListener(this);
		findViewById(R.id.channel2).setOnClickListener(this);
		findViewById(R.id.channel3).setOnClickListener(this);
		findViewById(R.id.channel4).setOnClickListener(this);
		findViewById(R.id.channel5).setOnClickListener(this);
		mCateText1 = (TextView) findViewById(R.id.textView1);
		mCateText2 = (TextView) findViewById(R.id.textView2);
        mCateText3=(TextView)findViewById(R.id.textView3);
		mCateText4 = (TextView) findViewById(R.id.textView4);
		mCateText5 = (TextView) findViewById(R.id.textView5);
		
		
       
	}

	private void prepareIntent() {
		
		mshouyeIntent=new Intent(this,InfoActivity.class);
		
	    mHomeItent = new Intent(this, MimeGroupActivity.class);	

	    
	    mChannelIntent = new Intent(this, MimeWeiCircle.class);
	 
	    
	
	    mSearchIntent = new Intent(this,ContactsActivity.class);

		

	    mMoreIntent = new Intent(this, MimePageActivity.class);
	   
	}


	
	private void setupIntent() {
		
		mTabHost=getTabHost();
		 
		mTabHost.addTab(buildTabSpec(TAB_TAG_SEARCH, R.string.bottomtitle01,R.drawable.shouyemain1, mshouyeIntent));

		mTabHost.addTab(buildTabSpec(TAB_TAG_HOME, R.string.bottomtitle02,R.drawable.shouyemain1, mHomeItent));
		
		mTabHost.addTab(buildTabSpec(TAB_TAG_CHANNEL,R.string.bottomtitle03, R.drawable.msg1, mChannelIntent));
	
		mTabHost.addTab(buildTabSpec(TAB_TAG_ACCOUNT,R.string.bottomtitle04, R.drawable.help1,mSearchIntent));
		
		mTabHost.addTab(buildTabSpec(TAB_TAB_MORE, R.string.bottomtitle05,R.drawable.mimetab1, mMoreIntent));
	}

	private TabHost.TabSpec buildTabSpec(String tag, int resLabel, int resIcon,
			final Intent content) {
		return mTabHost
				.newTabSpec(tag)
				.setIndicator(getString(resLabel),
						getResources().getDrawable(resIcon))
				.setContent(content);
	}

	public static void setCurrentTabByTag(String tab) {
		mTabHost.setCurrentTabByTag(tab);
	}


	@Override
	public void onClick(View v) {

		// TODO Auto-generated method stub
		if (mCurTabId == v.getId()) {
			

			return;
		}

		mBut3.setImageResource(R.drawable.shouyemain1);
		mBut1.setImageResource(R.drawable.msg1);
		mBut2.setImageResource(R.drawable.mimetab1);		
		mBut4.setImageResource(R.drawable.help1);
		mBut5.setImageResource(R.drawable.mime1);
		mCateText1.setTextColor(COLOR1);
		mCateText2.setTextColor(COLOR1);
		mCateText3.setTextColor(COLOR1);
		mCateText4.setTextColor(COLOR1);
		mCateText5.setTextColor(COLOR1);
		int checkedId = v.getId();
		final boolean o;
		if (mCurTabId < checkedId)
			o = true;
		else
			o = false;
		if (o)
			mTabHost.getCurrentView().startAnimation(left_out);
		else
			mTabHost.getCurrentView().startAnimation(right_out);
		switch (checkedId) {
		
		case R.id.channel3:
			mTabHost.setCurrentTabByTag(TAB_TAG_SEARCH);
			mBut3.setImageResource(R.drawable.shouyemain);
			mCateText3.setTextColor(COLOR2);

			break;
		
		
		case R.id.channel1:
			mTabHost.setCurrentTabByTag(TAB_TAG_HOME);
			mBut1.setImageResource(R.drawable.msg);
			mCateText1.setTextColor(COLOR2);

			break;
		case R.id.channel2:
			mTabHost.setCurrentTabByTag(TAB_TAG_CHANNEL);
			mBut2.setImageResource(R.drawable.mimetab);
			mCateText2.setTextColor(COLOR2);

			break;
	
		case R.id.channel4:
			mTabHost.setCurrentTabByTag(TAB_TAG_ACCOUNT);
			mBut4.setImageResource(R.drawable.help);
			mCateText4.setTextColor(COLOR2);

			break;
		case R.id.channel5:
			mTabHost.setCurrentTabByTag(TAB_TAB_MORE);
			mBut5.setImageResource(R.drawable.mime);
			mCateText5.setTextColor(COLOR2);

			break;
		default:
			break;
		}

		if (o)
			mTabHost.getCurrentView().startAnimation(left_in);
		else
			mTabHost.getCurrentView().startAnimation(right_in);
		mCurTabId = checkedId;
	}
	
	
	
	
	
	
}