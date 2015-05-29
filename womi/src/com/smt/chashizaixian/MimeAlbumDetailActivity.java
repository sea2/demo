package com.smt.chashizaixian;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.smt.pullview.util.RefreshableView;
import com.smt.pullview.util.RefreshableView.RefreshListener;
import com.smt.util.t.Expressions;
import com.smt.util.t.KeyboardListenEdittext;
import com.smt.util.t.TextUtils;
import com.smt.util.t.ToastUtil;
import com.smt.util.t.KeyboardListenEdittext.MOnKeyboardStateChangedListener;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MimeAlbumDetailActivity extends Activity implements OnClickListener,RefreshListener {

	
	private TextView detail_more;
	private TextView detail_back;
	private LinearLayout ll_mid;
	
	   //控制表情布局部分
		private ViewPager viewPager;
		private ArrayList<GridView> grids;
		private int[] expressionImages;
		private String[] expressionImageNames;
		private int[] expressionImages1;
		private String[] expressionImageNames1;
		private int[] expressionImages2;
		private String[] expressionImageNames2;
		private int[] expressionImages3;
		private String[] expressionImageNames3;
		private int[] expressionImages4;
		private String[] expressionImageNames4;
		private GridView gView1;
		private GridView gView2;
		private GridView gView3;
		private GridView gView4;
		private GridView gView5;
		private ImageView page0;
		private ImageView page1;
		private ImageView page2;
		private ImageView page3;
		private ImageView page4;
		private LinearLayout page_select;
		private Boolean biaoqingstate=false;
		private Context mCon;
		private Boolean isfocus=false;
		private KeyboardListenEdittext content_et;
		
		private ImageButton detail_face;
		
		private ImageView comment_good_iv;
		
		private Boolean gflag=false;
		
		private RefreshableView mRefreshableView;
		private ScrollView scroll_view_root;
		
		
		private RelativeLayout show_local;
		
		private TextView comment_content;
		
		private Button btn_send;
		
		
		
		@SuppressLint("HandlerLeak") Handler handler = new Handler() {
			@SuppressLint("HandlerLeak") 
			public void handleMessage(Message message) {
				super.handleMessage(message);
				mRefreshableView.finishRefresh();
				Toast.makeText(MimeAlbumDetailActivity.this, R.string.toast_text, Toast.LENGTH_SHORT).show();
			};
		};
		
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo_detail);
		
		mCon=this;
		detail_more=(TextView)findViewById(R.id.mime_detail_more);
		detail_more.setOnClickListener(this);
		
		detail_back=(TextView)findViewById(R.id.mime_detail_back);
		detail_back.setOnClickListener(this);
		
		ll_mid=(LinearLayout)findViewById(R.id.ll_mid);
		
		detail_face=(ImageButton)findViewById(R.id.detail_face);
		detail_face.setOnClickListener(this);
		
		//下拉刷新
		mRefreshableView = (RefreshableView) findViewById(R.id.ll_mid);
		scroll_view_root=(ScrollView)findViewById(R.id.scroll_view_root);
		mRefreshableView.setRefreshListener(MimeAlbumDetailActivity.this);
	
	
		show_local=(RelativeLayout)findViewById(R.id.show_local);
		show_local.setOnClickListener(this);
		
		comment_content=(TextView)findViewById(R.id.comment_content);
		
		btn_send=(Button)findViewById(R.id.btn_send);
		btn_send.setOnClickListener(this);
		
		
		comment_good_iv=(ImageView)findViewById(R.id.comment_good_iv);
		comment_good_iv.setOnClickListener(this);
		
		
		content_et=(KeyboardListenEdittext)findViewById(R.id.commentmessage_et);

		content_et.setOnClickListener(this);
        ((KeyboardListenEdittext) content_et).setOnKeyboardStateChangedListener(new MOnKeyboardStateChangedListener() {
   		
   		@Override
   		public void onKeyboardStateChanged(int state) {
   			// TODO Auto-generated method stub
   			
   			switch (state) {
   			case KeyboardListenEdittext.KEYBOARD_STATE_HIDE:					

   			   isfocus=false;
   				
   				
   				break;
   				
   			case KeyboardListenEdittext.KEYBOARD_STATE_SHOW:
   				
   				isfocus=true;
   				
   				break;
   			default:
   				break;
   			}
   	
   		}
   	});
		// 引入表情
	    expressionImages =Expressions.expressionImgs;
		expressionImageNames = Expressions.expressionImgNames;
		expressionImages1 = Expressions.expressionImgs1;
		expressionImageNames1 = Expressions.expressionImgNames1;
		expressionImages2 = Expressions.expressionImgs2;
		expressionImageNames2 = Expressions.expressionImgNames2;
		expressionImages3= Expressions.expressionImgs3;
		expressionImageNames3 = Expressions.expressionImgNames3;
		expressionImages4 = Expressions.expressionImgs4;
		expressionImageNames4 = Expressions.expressionImgNames4;
    	// 创建ViewPager
	     viewPager = (ViewPager) findViewById(R.id.viewpager);
		// 从相册或相机选择图片  
		page_select = (LinearLayout) findViewById(R.id.page_select);
		page0 = (ImageView) findViewById(R.id.page0_select);
		page1 = (ImageView) findViewById(R.id.page1_select);
		page2 = (ImageView) findViewById(R.id.page2_select);
		page3 = (ImageView) findViewById(R.id.page3_select);
		page4 = (ImageView) findViewById(R.id.page4_select);
		
		

		initViewPager();
		
	}


	private void initViewPager() {
		LayoutInflater inflater = LayoutInflater.from(this);
		grids = new ArrayList<GridView>();
		gView1 = (GridView) inflater.inflate(R.layout.grid1, null);
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		// 生成24个表情
		for (int i = 0; i < 24; i++) {
			Map<String, Object> listItem = new HashMap<String, Object>();
			listItem.put("image", expressionImages[i]);
			listItems.add(listItem);
		}

		SimpleAdapter simpleAdapter = new SimpleAdapter(MimeAlbumDetailActivity.this, listItems,
				R.layout.singleexpression, new String[] { "image" },
				new int[] { R.id.image });
		gView1.setAdapter(simpleAdapter);
		gView1.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Bitmap bitmap = null;
				bitmap = BitmapFactory.decodeResource(getResources(),
						expressionImages[arg2 % expressionImages.length]);
				int width = bitmap.getWidth();   
		        int height = bitmap.getHeight(); 
		        						 						        
			Matrix matrix = new Matrix();    
		        // 缩放图片动作   
		        matrix.postScale(0.45f, 0.45f);    
			 Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,   
					 width, height, matrix, true);   														 
		    	ImageSpan imageSpan = new ImageSpan(mCon, resizedBitmap);
		    	
		    	
				SpannableString spannableString = new SpannableString(expressionImageNames[arg2].substring(0,expressionImageNames[arg2].length()));

				spannableString.setSpan(imageSpan, 0,expressionImageNames[arg2].length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				// 编辑框设置数据
				content_et.append(spannableString);
				System.out.println("edit的内容 = " + spannableString);
			}
		});
		grids.add(gView1);

		gView2 = (GridView) inflater.inflate(R.layout.grid2, null);
		grids.add(gView2);

		gView3 = (GridView) inflater.inflate(R.layout.grid3, null);
		grids.add(gView3);
		
		gView4 = (GridView) inflater.inflate(R.layout.grid4, null);
		grids.add(gView4);
		
		gView5 = (GridView) inflater.inflate(R.layout.grid5, null);
		grids.add(gView5);
		//System.out.println("GridView的长度 = " + grids.size());

		// 填充ViewPager的数据适配器
		PagerAdapter mPagerAdapter = new PagerAdapter() {
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				return grids.size();
			}

			@Override
			public void destroyItem(View container, int position, Object object) {
				((ViewPager) container).removeView(grids.get(position));
			}

			@Override
			public Object instantiateItem(View container, int position) {
				((ViewPager) container).addView(grids.get(position));
				return grids.get(position);
			}
		};

		viewPager.setAdapter(mPagerAdapter);
		// viewPager.setAdapter();

		viewPager.setOnPageChangeListener(new GuidePageChangeListener());
	}
	
	
	// ** 指引页面改监听器 */
		class GuidePageChangeListener implements OnPageChangeListener {

			@Override
			public void onPageScrollStateChanged(int arg0) {
				//System.out.println("页面滚动" + arg0);

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				//System.out.println("换页了" + arg0);
			}

			@Override
			public void onPageSelected(int arg0) {
				switch (arg0) {
				case 0:
					page0.setImageDrawable(getResources().getDrawable(
							R.drawable.page_focused));
					page1.setImageDrawable(getResources().getDrawable(
							R.drawable.page_unfocused));

					break;
				case 1:
					page1.setImageDrawable(getResources().getDrawable(
							R.drawable.page_focused));
					page0.setImageDrawable(getResources().getDrawable(
							R.drawable.page_unfocused));
					page2.setImageDrawable(getResources().getDrawable(
							R.drawable.page_unfocused));
					page3.setImageDrawable(getResources().getDrawable(
							R.drawable.page_unfocused));
					page4.setImageDrawable(getResources().getDrawable(
							R.drawable.page_unfocused));
					List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
					// 生成24个表情
					for (int i = 0; i < 24; i++) {
						Map<String, Object> listItem = new HashMap<String, Object>();
						listItem.put("image", expressionImages1[i]);
						listItems.add(listItem);
					}

					SimpleAdapter simpleAdapter = new SimpleAdapter(mCon,
							listItems, R.layout.singleexpression,
							new String[] { "image" }, new int[] { R.id.image });
					gView2.setAdapter(simpleAdapter);
					gView2.setOnItemClickListener(new OnItemClickListener() {
						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								int arg2, long arg3) {
							Bitmap bitmap = null;
							bitmap = BitmapFactory.decodeResource(getResources(),
									expressionImages1[arg2
											% expressionImages1.length]);
							int width = bitmap.getWidth();   
					        int height = bitmap.getHeight(); 
					        						 						        
						Matrix matrix = new Matrix();    
					        // 缩放图片动作   
					        matrix.postScale(0.45f, 0.45f);    
						 Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,width, height, matrix, true);   														 
						ImageSpan imageSpan = new ImageSpan(mCon, resizedBitmap);
						SpannableString spannableString = new SpannableString(expressionImageNames[arg2].substring(0,expressionImageNames[arg2].length()));

						spannableString.setSpan(imageSpan, 0,expressionImageNames[arg2].length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
							// 编辑框设置数据
							content_et.append(spannableString);
							//System.out.println("edit的内容 = " + spannableString);
						}
					});
					break;
				case 2:
					page2.setImageDrawable(getResources().getDrawable(
							R.drawable.page_focused));
					page0.setImageDrawable(getResources().getDrawable(
							R.drawable.page_unfocused));
					page1.setImageDrawable(getResources().getDrawable(
							R.drawable.page_unfocused));
					page3.setImageDrawable(getResources().getDrawable(
							R.drawable.page_unfocused));
					page4.setImageDrawable(getResources().getDrawable(
							R.drawable.page_unfocused));
					List<Map<String, Object>> listItems1 = new ArrayList<Map<String, Object>>();
					// 生成24个表情
					for (int i = 0; i < 24; i++) {
						Map<String, Object> listItem = new HashMap<String, Object>();
						listItem.put("image", expressionImages2[i]);
						listItems1.add(listItem);
					}

					SimpleAdapter simpleAdapter1 = new SimpleAdapter(mCon,
							listItems1, R.layout.singleexpression,
							new String[] { "image" }, new int[] { R.id.image });
					gView3.setAdapter(simpleAdapter1);
					gView3.setOnItemClickListener(new OnItemClickListener() {
						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								int arg2, long arg3) {
							Bitmap bitmap = null;
							bitmap = BitmapFactory.decodeResource(getResources(),
									expressionImages2[arg2
											% expressionImages2.length]);
							int width = bitmap.getWidth();   
					        int height = bitmap.getHeight(); 
					        						 						        
						Matrix matrix = new Matrix();    
					        // 缩放图片动作   
					        matrix.postScale(0.45f, 0.45f);    
						 Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,   
								 width, height, matrix, true);   														 
						ImageSpan imageSpan = new ImageSpan(mCon, resizedBitmap);
						SpannableString spannableString = new SpannableString(expressionImageNames[arg2].substring(0,expressionImageNames[arg2].length()));

						spannableString.setSpan(imageSpan, 0,expressionImageNames[arg2].length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
							// 编辑框设置数据
							content_et.append(spannableString);
							//System.out.println("edit的内容 = " + spannableString);
						}
					});
					break;
					
				case 3:
					page3.setImageDrawable(getResources().getDrawable(
							R.drawable.page_focused));
					page0.setImageDrawable(getResources().getDrawable(
							R.drawable.page_unfocused));
					page2.setImageDrawable(getResources().getDrawable(
							R.drawable.page_unfocused));
					page1.setImageDrawable(getResources().getDrawable(
							R.drawable.page_unfocused));
					page4.setImageDrawable(getResources().getDrawable(
							R.drawable.page_unfocused));
					List<Map<String, Object>> listItems2 = new ArrayList<Map<String, Object>>();
					// 生成24个表情
					for (int i = 0; i < 24; i++) {
						Map<String, Object> listItem = new HashMap<String, Object>();
						listItem.put("image", expressionImages3[i]);
						listItems2.add(listItem);
					}

					SimpleAdapter simpleAdapter2 = new SimpleAdapter(mCon,
							listItems2, R.layout.singleexpression,
							new String[] { "image" }, new int[] { R.id.image });
					gView4.setAdapter(simpleAdapter2);
					gView4.setOnItemClickListener(new OnItemClickListener() {
						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								int arg2, long arg3) {
							Bitmap bitmap = null;
							bitmap = BitmapFactory.decodeResource(getResources(),
									expressionImages3[arg2
											% expressionImages3.length]);
							int width = bitmap.getWidth();   
					        int height = bitmap.getHeight(); 
					        						 						        
						Matrix matrix = new Matrix();    
					        // 缩放图片动作   
					        matrix.postScale(0.45f, 0.45f);    
						 Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,   
								 width, height, matrix, true);   														 
						ImageSpan imageSpan = new ImageSpan(mCon, resizedBitmap);
						SpannableString spannableString = new SpannableString(expressionImageNames[arg2].substring(0,expressionImageNames[arg2].length()));

						spannableString.setSpan(imageSpan, 0,expressionImageNames[arg2].length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
							// 编辑框设置数据
							content_et.append(spannableString);
							//System.out.println("edit的内容 = " + spannableString);
						}
					});
					break;
					
				case 4:
					page4.setImageDrawable(getResources().getDrawable(
							R.drawable.page_focused));
					page0.setImageDrawable(getResources().getDrawable(
							R.drawable.page_unfocused));
					page2.setImageDrawable(getResources().getDrawable(
							R.drawable.page_unfocused));
					page3.setImageDrawable(getResources().getDrawable(
							R.drawable.page_unfocused));
					page1.setImageDrawable(getResources().getDrawable(
							R.drawable.page_unfocused));
					List<Map<String, Object>> listItems3 = new ArrayList<Map<String, Object>>();
					// 生成24个表情
					for (int i = 0; i < 15; i++) {
						Map<String, Object> listItem = new HashMap<String, Object>();
						listItem.put("image", expressionImages4[i]);
						listItems3.add(listItem);
					}

					SimpleAdapter simpleAdapter3 = new SimpleAdapter(mCon,
							listItems3, R.layout.singleexpression,
							new String[] { "image" }, new int[] { R.id.image });
					gView5.setAdapter(simpleAdapter3);
					gView5.setOnItemClickListener(new OnItemClickListener() {
						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								int arg2, long arg3) {
							Bitmap bitmap = null;
							bitmap = BitmapFactory.decodeResource(getResources(),
									expressionImages4[arg2% expressionImages4.length]);
							int width = bitmap.getWidth();   
					        int height = bitmap.getHeight(); 
					        						 						        
						Matrix matrix = new Matrix();    
					        // 缩放图片动作   
					        matrix.postScale(0.45f, 0.45f);    
						 Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,   
								 width, height, matrix, true);   														 
						ImageSpan imageSpan = new ImageSpan(mCon, resizedBitmap);
							

						SpannableString spannableString = new SpannableString(expressionImageNames[arg2].substring(0,expressionImageNames[arg2].length()));

						spannableString.setSpan(imageSpan, 0,expressionImageNames[arg2].length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
							// 编辑框设置数据
							content_et.append(spannableString);
							//System.out.println("edit的内容 = " + spannableString);
						}
					});
					break;

				}
			}
		}
	
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.mime_detail_more:
			
			new PopupWindows(MimeAlbumDetailActivity.this, ll_mid);
			
			break;
			
        case R.id.mime_detail_back:
			this.finish();
			break;
			
        case R.id.btn_send:
 
        	String content=content_et.getText().toString();
        	
        	
        	if (content.length()>0) {
        		TextUtils.textViewSpan(MimeAlbumDetailActivity.this, comment_content, content, false);
        		content_et.setText("");
			}
        	
        	
			break;
			
        case R.id.show_local:
		   Intent intent=new Intent(MimeAlbumDetailActivity.this, CirclePlaceActivity.class);
		   startActivity(intent);
			break;
		
        case R.id.comment_good_iv:
			
        	if (gflag) {
				comment_good_iv.setImageResource(R.drawable.feed_praise_select);
				gflag=false;
			}
        	else {
        		comment_good_iv.setImageResource(R.drawable.feed_praise_nor);
				gflag=true;
			}
        	
        	
			break;
			
        case R.id.commentmessage_et:	
			  
        	
        	if(biaoqingstate)
	      		
			  { 
					viewPager.setVisibility(View.GONE);
					page_select.setVisibility(View.GONE);
					biaoqingstate=false;
			  }
				content_et.setFocusable(true);   
				content_et.setFocusableInTouchMode(true);   
				content_et.requestFocus(); 
				InputMethodManager inputManager2 = (InputMethodManager)content_et.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
			    inputManager2.showSoftInput(content_et, 0);
				
				
				break;
				
        case R.id.detail_face:

   		    if (!biaoqingstate) {
   		    	viewPager.setVisibility(View.VISIBLE);
   				page_select.setVisibility(View.VISIBLE);
   				biaoqingstate=true;
			}
   		    else {
   		 	viewPager.setVisibility(View.GONE);
			page_select.setVisibility(View.GONE);
			biaoqingstate=false;
			}
   			
   			if (isfocus) {
   				
   				 InputMethodManager imm1 = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);  
   				 imm1.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
   				
   				
   			}
   			else {
   				InputMethodManager imm1 = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE); 
   				imm1.hideSoftInputFromWindow(v.getWindowToken(), 0); //强制隐藏键盘 
   				
   			}
   	
        	
        	
			break;
		default:
			break;
		}
	}

	public class PopupWindows extends PopupWindow {

		public PopupWindows(Context mContext, View parent) {

			View view = View.inflate(mContext, R.layout.share_popupwindows, null);
			view.startAnimation(AnimationUtils.loadAnimation(mContext,
					R.anim.fade_ins));
			LinearLayout ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);
			ll_popup.startAnimation(AnimationUtils.loadAnimation(mContext,R.anim.push_bottom_in_2));

			setWidth(LayoutParams.FILL_PARENT);
			setHeight(LayoutParams.FILL_PARENT);
			setBackgroundDrawable(new BitmapDrawable());
			setFocusable(true);
			setOutsideTouchable(true);
			setContentView(view);
			showAtLocation(parent, Gravity.BOTTOM, 0, 0);
			update();

			TextView tv1=(TextView)view.findViewById(R.id.share_01);
			TextView tv2=(TextView)view.findViewById(R.id.share_02);
			TextView tv3=(TextView)view.findViewById(R.id.share_03);
			
			TextView del=(TextView)view.findViewById(R.id.delete);
			
			Button cancel=(Button)view.findViewById(R.id.item_pd_cancel);
			
			
			
			tv1.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
				ToastUtil.toastshow(MimeAlbumDetailActivity.this, "暂无接口");
				
					dismiss();
				}
			});
			tv2.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
		
					ToastUtil.toastshow(MimeAlbumDetailActivity.this, "暂无接口");
					dismiss();
				}
			});
			tv3.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					ToastUtil.toastshow(MimeAlbumDetailActivity.this, "暂无接口");
					dismiss();
				}
			});
			
			del.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					ToastUtil.toastshow(MimeAlbumDetailActivity.this, "暂无接口");
					dismiss();
				}
				
			});
			
			cancel.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
			
					dismiss();
				}
			});

		}
	}


	@Override
	public void onRefresh(RefreshableView view) {
		// TODO Auto-generated method stub
		//伪处理
	handler.sendEmptyMessageDelayed(1, 2000);
	}
	
	
	
	
}
