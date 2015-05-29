package com.smt.chashizaixian;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.smt.config.Constants;
import com.smt.pub.util.Bimp;
import com.smt.pub.util.FileUtils;
import com.smt.util.t.Expressions;
import com.smt.util.t.KeyboardListenEdittext;
import com.smt.util.t.KeyboardListenEdittext.MOnKeyboardStateChangedListener;




public class PublishedActivity extends Activity implements OnClickListener {

	private GridView noScrollgridview;
	private GridAdapter adapter;
	private TextView activity_selectimg_send;
	private TextView activity_selectimg_back;

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
	
	private KeyboardListenEdittext content_et;
	
	private Context mCon;
	
	private RelativeLayout bface_lay;
	private ImageButton face_btn;
	private ImageButton word_btn;
	private Boolean isfocus=false;
	private Boolean biaoqingstate=false;
	
    private Boolean sort;
    private ImageView samleto;
    private RelativeLayout samleto_rl;
    
    private Boolean sameto=false;
    private ImageView local_icon;
    
//     public static PublishedActivity publishedinstance=null;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selectimg);
		mCon=this;
		
		  Intent intent;
		  Bundle bundle;
		  intent = this.getIntent();		    
		  bundle = intent.getExtras();		   
		  sort=bundle.getBoolean("pubsort");
		
		
		 Init();
		
		 
		 samleto=(ImageView)findViewById(R.id.samleto);
		 samleto_rl=(RelativeLayout)findViewById(R.id.samleto_rl);
		 samleto_rl.setOnClickListener(this);
		 
		bface_lay=(RelativeLayout)findViewById(R.id.bottom);
		face_btn=(ImageButton)findViewById(R.id.compose_face);
		word_btn=(ImageButton)findViewById(R.id.compose_word);
		local_icon=(ImageView)findViewById(R.id.local_icon);
		show_local = findViewById(R.id.show_local);
		local_position = (TextView) findViewById(R.id.local_position);
		
		face_btn.setOnClickListener(this);
		word_btn.setOnClickListener(this);
		show_local.setOnClickListener(this);
		
		content_et=(KeyboardListenEdittext)findViewById(R.id.content_et);
		
		 content_et.setOnClickListener(this);
         content_et.setOnKeyboardStateChangedListener(new MOnKeyboardStateChangedListener() {
    		
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

	public void Init() {
		
		noScrollgridview = (GridView) findViewById(R.id.noScrollgridview);
         if (sort) {							
		noScrollgridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
		adapter = new GridAdapter(this);
		adapter.update();
		noScrollgridview.setAdapter(adapter);
		noScrollgridview.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if (arg2 == Bimp.bmp.size()) {
					new PopupWindows(PublishedActivity.this, noScrollgridview);
				} else {
					Intent intent = new Intent(PublishedActivity.this,PhotoActivity.class);
					intent.putExtra("ID", arg2);
					startActivity(intent);
				}
			}
		});
   }
      else {
    	  noScrollgridview.setVisibility(View.GONE);
		}
		activity_selectimg_back=(TextView)findViewById(R.id.activity_selectimg_back);
		activity_selectimg_back.setOnClickListener(this);
		activity_selectimg_send = (TextView) findViewById(R.id.activity_selectimg_send);
		activity_selectimg_send.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				List<String> list = new ArrayList<String>();				
				for (int i = 0; i < Bimp.drr.size(); i++) {
					String Str = Bimp.drr.get(i).substring( 
							Bimp.drr.get(i).lastIndexOf("/") + 1,
							Bimp.drr.get(i).lastIndexOf("."));
					list.add(FileUtils.SDPATH+Str+".JPEG");				
				}
				// 高清的压缩图片全部就在  list 路径里面了
				// 高清的压缩过的 bmp 对象  都在 Bimp.bmp里面
				// 完成上传服务器后 .........
				
				Log.v("bitmappppppppppppppppp", list+"");
				
				
				
				FileUtils.deleteDir();
			}
		});
	}

	@SuppressLint("HandlerLeak")
	public class GridAdapter extends BaseAdapter {
		private LayoutInflater inflater; // 视图容器
		private int selectedPosition = -1;// 选中的位置
		private boolean shape;

		public boolean isShape() {
			return shape;
		}

		public void setShape(boolean shape) {
			this.shape = shape;
		}

		public GridAdapter(Context context) {
			inflater = LayoutInflater.from(context);
		}

		public void update() {
			loading();
		}

		public int getCount() {
			return (Bimp.bmp.size() + 1);
		}

		public Object getItem(int arg0) {

			return null;
		}

		public long getItemId(int arg0) {

			return 0;
		}

		public void setSelectedPosition(int position) {
			selectedPosition = position;
		}

		public int getSelectedPosition() {
			return selectedPosition;
		}

		/**
		 * ListView Item设置
		 */
		public View getView(int position, View convertView, ViewGroup parent) {
			final int coord = position;
			ViewHolder holder = null;
			if (convertView == null) {

				convertView = inflater.inflate(R.layout.item_published_grida,
						parent, false);
				holder = new ViewHolder();
				holder.image = (ImageView) convertView
						.findViewById(R.id.item_grida_image);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			if (position == Bimp.bmp.size()) {
				holder.image.setImageBitmap(BitmapFactory.decodeResource(
						getResources(), R.drawable.add_item_hover));
				if (position == 9) {
					holder.image.setVisibility(View.GONE);
				}
			} else {
				holder.image.setImageBitmap(Bimp.bmp.get(position));
			}

			return convertView;
		}

		public class ViewHolder {
			public ImageView image;
		}

		Handler handler = new Handler() {
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 1:
					adapter.notifyDataSetChanged();
					break;
				}
				super.handleMessage(msg);
			}
		};

		public void loading() {
			new Thread(new Runnable() {
				public void run() {
					while (true) {
						if (Bimp.max == Bimp.drr.size()) {
							Message message = new Message();
							message.what = 1;
							handler.sendMessage(message);
							break;
						} else {
							try {
								String path = Bimp.drr.get(Bimp.max);
								System.out.println(path);
								Bitmap bm = Bimp.revitionImageSize(path);
								Bimp.bmp.add(bm);
								String newStr = path.substring(
										path.lastIndexOf("/") + 1,
										path.lastIndexOf("."));
								FileUtils.saveBitmap(bm, "" + newStr);
								Bimp.max += 1;
								Message message = new Message();
								message.what = 1;
								handler.sendMessage(message);
							} catch (IOException e) {

								e.printStackTrace();
							}
						}
					}
				}
			}).start();
		}
	}

	public String getString(String s) {
		String path = null;
		if (s == null)
			return "";
		for (int i = s.length() - 1; i > 0; i++) {
			s.charAt(i);
		}
		return path;
	}

	protected void onRestart() {
		
		if (sort) {
			adapter.update();
		}
		
		
		super.onRestart();
	}

	public class PopupWindows extends PopupWindow {

		public PopupWindows(Context mContext, View parent) {

			View view = View.inflate(mContext, R.layout.item_popupwindows, null);
			view.startAnimation(AnimationUtils.loadAnimation(mContext,
					R.anim.fade_ins));
			LinearLayout ll_popup = (LinearLayout) view
					.findViewById(R.id.ll_popup);
			ll_popup.startAnimation(AnimationUtils.loadAnimation(mContext,
					R.anim.push_bottom_in_2));

			setWidth(LayoutParams.FILL_PARENT);
			setHeight(LayoutParams.FILL_PARENT);
			setBackgroundDrawable(new BitmapDrawable());
			setFocusable(true);
			setOutsideTouchable(true);
			setContentView(view);
			showAtLocation(parent, Gravity.BOTTOM, 0, 0);
			update();

			Button bt1 = (Button) view
					.findViewById(R.id.item_popupwindows_camera);
			Button bt2 = (Button) view
					.findViewById(R.id.item_popupwindows_Photo);
			Button bt3 = (Button) view
					.findViewById(R.id.item_popupwindows_cancel);
			bt1.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					photo();
					dismiss();
				}
			});
			bt2.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Intent intent = new Intent(PublishedActivity.this,GetPicActivity.class);
					startActivity(intent);
					dismiss();
				}
			});
			bt3.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					dismiss();
				}
			});

		}
	}

	private static final int TAKE_PICTURE = 0x000000;
	private String path = "";
    private View show_local;
    private String localResult;
    private TextView local_position;

	public void photo() {
		Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		File file = new File(Environment.getExternalStorageDirectory()
				+ "/myimage/", String.valueOf(System.currentTimeMillis())
				+ ".jpg");
		path = file.getPath();
		Uri imageUri = Uri.fromFile(file);
		openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		startActivityForResult(openCameraIntent, TAKE_PICTURE);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case TAKE_PICTURE:
			if (Bimp.drr.size() < 9 && resultCode == -1) {
				Bimp.drr.add(path);
			}
			break;
			
		case Constants.LOCAL_REQUEST_CODE:
		    boolean local_clear = false;
		    try
            {
                localResult = data.getStringExtra(Constants.LOCAL_RESULT);
                Log.e("StartActivityActivity", "onActivityResult------>定位当前信息返回出错");
            }
            catch (Exception e)
            {
            }
            try
            {
                local_clear = data.getBooleanExtra(Constants.LOCAL_IF_CLEAR, false);
                Log.e("StartActivityActivity", "onActivityResult------>当前位置信息清除标志返回出错");
            }
            catch (Exception e)
            {
            }
            if (localResult != null)
            {
                local_icon.setImageResource(R.drawable.s_n_dynamic_btn_gps_selected);
                local_position.setText(localResult);
            }
            if (local_clear)
            {
                local_icon.setImageResource(R.drawable.s_n_dynamic_btn_gps_unselected);
                local_position.setText(getResources().getString(R.string.l_position));
            }
            break;
		}
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

		SimpleAdapter simpleAdapter = new SimpleAdapter(PublishedActivity.this, listItems,
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
						 Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,   
								 width, height, matrix, true);   														 
						ImageSpan imageSpan = new ImageSpan(mCon, resizedBitmap);
						SpannableString spannableString = new SpannableString(expressionImageNames1[arg2].substring(0,expressionImageNames1[arg2].length()));

						spannableString.setSpan(imageSpan, 0,expressionImageNames1[arg2].length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
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
						SpannableString spannableString = new SpannableString(expressionImageNames2[arg2].substring(0,expressionImageNames2[arg2].length()));

						spannableString.setSpan(imageSpan, 0,expressionImageNames2[arg2].length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
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
						SpannableString spannableString = new SpannableString(expressionImageNames3[arg2].substring(0,expressionImageNames3[arg2].length()));

						spannableString.setSpan(imageSpan, 0,expressionImageNames3[arg2].length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
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
							

						SpannableString spannableString = new SpannableString(expressionImageNames4[arg2].substring(0,expressionImageNames4[arg2].length()));

						spannableString.setSpan(imageSpan, 0,expressionImageNames4[arg2].length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
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
		
		case R.id.content_et:
			
			  if(biaoqingstate)
      		{
      	       bface_lay.setVisibility(View.GONE);
				word_btn.setVisibility(View.GONE);
				face_btn.setVisibility(View.VISIBLE);	
      		}
			content_et.setFocusable(true);   
			content_et.setFocusableInTouchMode(true);   
			content_et.requestFocus(); 
			InputMethodManager inputManager2 = (InputMethodManager)content_et.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		    inputManager2.showSoftInput(content_et, 0);
			
			
			break;
			
		case R.id.show_local:
		    Intent intent = new Intent(PublishedActivity.this, LocationActivity.class);
		    startActivityForResult(intent, Constants.LOCAL_REQUEST_CODE);
		    
		    break;

		case R.id.compose_face:
			
	      face_btn.setVisibility(View.GONE);
		  word_btn.setVisibility(View.VISIBLE);	
		  bface_lay.setVisibility(View.VISIBLE);				
		  biaoqingstate=true;
			
			
			if (isfocus) {
				
				 InputMethodManager imm1 = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);  
				 imm1.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
				
				
			}
			else {
				InputMethodManager imm1 = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE); 
				imm1.hideSoftInputFromWindow(v.getWindowToken(), 0); //强制隐藏键盘 
				
			}
	
		 
			break;
       case R.id.compose_word:
			
 	      face_btn.setVisibility(View.VISIBLE);
 		  word_btn.setVisibility(View.GONE);	   	   
    	  bface_lay.setVisibility(View.GONE);	
		  biaoqingstate=false;
		 InputMethodManager inputManager = (InputMethodManager)content_et.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
	     inputManager.showSoftInput(content_et, 0);
    	   
    	   
			
			break;

			
       case R.id.activity_selectimg_back:
			
    	   if((content_et.getText().toString().length()>0)||Bimp.drr.size()>0){
         	  
         	  Dialog dialog1 = new AlertDialog.Builder(this).setTitle("友情提示")
       				.setMessage("确定放弃编辑？")
       				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
       					public void onClick(DialogInterface dialog, int which) {
       						Bimp.bmp.clear();
      						Bimp.drr.clear();
      						Bimp.max = 0;
       						FileUtils.deleteDir();
       						Bimp.act_bool = true;
       						PublishedActivity.this.finish();
       						
       					}
       				}).setNegativeButton("取消", null).create();
       		     dialog1.show();  
         	  
           }
           else {
        	   Bimp.act_bool=true;
 			PublishedActivity.this.finish();
 		}  

 			break;
 			
 			
       case R.id.samleto_rl:
    	   
		if (!sameto) {
	
		samleto.setImageResource(R.drawable.s_choose_bg_hover);	
		sameto=true;
			
		}else {
			
			samleto.setImageResource(R.drawable.s_choose_bg_nor);	
			sameto=false;
		}	
    	   

 			break;
	
 			
 			
		default:
			break;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		// TODO Auto-generated method stub
		
		
		if (keyCode == KeyEvent.KEYCODE_BACK&&biaoqingstate)
		{
		  
			bface_lay.setVisibility(View.GONE);
			face_btn.setVisibility(View.VISIBLE);
	 	    word_btn.setVisibility(View.GONE);
			biaoqingstate=false;
			
			return false;
			
		}
		
		if (keyCode == KeyEvent.KEYCODE_BACK) {

			  if((content_et.getText().toString().length()>0)||Bimp.drr.size()>0){
				             
	        	  Dialog dialog1 = new AlertDialog.Builder(this).setTitle("友情提示")
	      				.setMessage("确定放弃编辑？")
	      				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
	      					public void onClick(DialogInterface dialog, int which) {
	      						Bimp.bmp.clear();
	      						Bimp.drr.clear();
	      						Bimp.max = 0;
	      						FileUtils.deleteDir();
	      						Bimp.act_bool = true;
	      						PublishedActivity.this.finish();
	      						
	      					}
	      				}).setNegativeButton("取消", null).create();
	      		     dialog1.show();  
	        	  
	          }

	          else {
	        	  Bimp.act_bool=true;
	        	  PublishedActivity.this.finish();
	        	  
			}
			}
	
			  
			  return super.onKeyDown(keyCode, event);
		

	}	
	
	
	
}
