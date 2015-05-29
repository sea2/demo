package com.smt.util.t;

import java.lang.ref.SoftReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.TextView;

import com.smt.chashizaixian.R;
import com.smt.model.Emotion;


/**
 * 处理字体高亮
 * 
 * @author pelinter
 * 
 */
public class TextUtils {
	private static Map<String, SoftReference<Drawable>> imageCache = new HashMap<String, SoftReference<Drawable>>();
	private static List<Emotion> emotions = new ArrayList<Emotion>();
	private static String[] emotionsarray;
	
	private static String[] emotionsName={"aini","aini2","aixinchuandi","aotuman","baibai","beishang","binggun","bishi"	
    ,"bizui","buyao2","chijing","dahaqian","dangao","dianying","ding","feiji","fengshan","fennu","fuyun","fuyun","ganmao"
    ,"geili","good","guzhang","haha","haixiu","han","hehe","heixian","heng","hongsidai","huatong","huaxin","hufen"
	,"jiong","kafei","keai","kelian","ku","kun","lai2","landeli","lazhu","lei","liwu","lvsidai","maozi","meng","nanhai",
	"nu","nvhai","ok2","qian","qiche","qinqin","quantou2","ruo2","shachenbao","shengbing","shenma","shiwang","shizhong",
	"shouji","shoutao","shuai","shuai4","shudaizi","shuijiao","shuye","sikao","taikaixin","taiyang","touxiao","tu","tuzi",
	"v5","wabi","weibo","weifeng","weiguan","weiqu","woshou2","xi","xiangji","xianhua","xiaozhu","xiayu","xigua","xin",
	"xinsui","xiongmao","xixi","xu","xuehua","xueren","ye2","yinxian","yinyue","yiwen","youhengheng","yueliang","yun",
	"zan2","zhi","zhouma","zhuakuang","zixingche","zuicha2","zuichan","zuoguilian","zuohengheng","zuqiu"
	};
	
	/**
	 * 
	 * @param mContext
	 * @param textview
	 * @param content
	 * @param hasClick
	 * 是否添加click
	 */
	public static void textViewSpan(Context mContext, TextView textview, String content, boolean hasClick) {
		List<PositionItem> list = paresString2(content);
		Spannable span = new SpannableString(content);
		
		
		emotionsarray = mContext.getResources().getStringArray(R.array.defualt_emotions);
		
		
		
		int length = emotionsarray.length;
		
		if(emotionsName.length != emotionsarray.length)
			throw new RuntimeException("表情解析有误");
		
		for (int i=0 ; i < length; i++)
		{
			Emotion emotion = new Emotion(emotionsName[i], emotionsarray[i]);
			emotions.add(emotion);
		}
		

         
		for (PositionItem pi : list) {
			if (pi.getPrefixType() == 4) {
				String imageName = "";
				for (Emotion em : emotions) {
					if (em.getPhrase().equals(pi.getContent())) {
						imageName = em.getSaveName2();
						break;
					}
				}
				//
				try {
					Field f = (Field) R.drawable.class.getDeclaredField(imageName);
					int eId = f.getInt(R.drawable.class);
					Drawable drawable = mContext.getResources().getDrawable(eId);
					if (drawable != null) {
						drawable.setBounds(0, 0, drawable.getIntrinsicWidth()/2, drawable.getIntrinsicHeight()/2);
						ImageSpan imgSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
						span.setSpan(imgSpan, pi.start, pi.end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
					} else {
						span.setSpan(new ForegroundColorSpan(Color.BLUE), pi.start, pi.end,
								Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
					}
				} catch (Exception e) {
					// TODO: handle exception
					span.setSpan(new ForegroundColorSpan(Color.BLUE), pi.start, pi.end,
							Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
				}
			} else {
				if (hasClick)
					span.setSpan(new TextClickSapn(mContext, pi), pi.start, pi.end,
							Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
				else
					span.setSpan(new ForegroundColorSpan(Color.BLUE), pi.start, pi.end,
							Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			}
		}
		textview.setText(span);
		if (hasClick)
			textview.setMovementMethod(LinkMovementMethod.getInstance());
	}

	public static List<PositionItem> paresString(String content) {
		String regex = "@[^\\s:：《]+([\\s:：《]|$)|#(.+?)#|http://t\\.cn/\\w+|\\[(.*?)\\]";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(content);
		boolean b = m.find();
		List<PositionItem> list = new ArrayList<PositionItem>();
		while (b) {

			int start = m.start();
			int end = m.end();
			String str = m.group();
			list.add(new PositionItem(start, end, str, content.length()));
			b = m.find(m.end() - 1);
		}
		return list;
	}

	/**
	 * 这个是处理一条信息有多个#...
	 * 
	 * @param content
	 * @return
	 */
	public static List<PositionItem> paresString2(String content) {
		String regex = "@[^\\s:：《]+([\\s:：《]|$)|#(.+?)#|http://t\\.cn/\\w+|\\[(.*?)\\]";
		//String regex = "@[^,，：:\\s]+([\\s:：《]|$)|#(.+?)#|http://t\\.cn/\\w+|\\[(.*?)\\]";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(content);
		boolean b = m.find();
		List<PositionItem> list = new ArrayList<PositionItem>();
		int count = 0;
		int lastIndex = 0;
		while (b) {

			int start = m.start();
			int end = m.end();
			String str = m.group();
			if (str.startsWith("#")) {
				count++;
				if (count % 2 == 0) {
					b = m.find(lastIndex);
					continue;
				}
			}
			list.add(new PositionItem(start, end, str, content.length()));
			b = m.find(m.end() - 1);
			try {
				lastIndex = m.start() + 1;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return list;
	}


	private static class TextClickSapn extends ClickableSpan {
		private PositionItem item;
		private Context mContext;

		public TextClickSapn(Context context, PositionItem item) {
			// TODO Auto-generated constructor stub
			this.item = item;
			this.mContext = context;
		}

		@Override
		public void onClick(View widget) {
			// TODO Auto-generated method stub
			switch (item.getPrefixType()) {
			case 1:
				
			//Log.v("tttttttttttttttttttttttt", item.getContentWithoutPrefix());
				
//				Intent atintent=new Intent(mContext, MimePageActivity.class);				
//				Bundle mBundle=new Bundle();
//				mBundle.putBoolean("ismime", false);
//				mBundle.putString("uid", "");
//				mBundle.putString("name", item.getContentWithoutPrefix());
//				mBundle.putString("img", "");
//				atintent.putExtras(mBundle);
//			    mContext.startActivity(atintent);	
	
				break;
			case 2:
//				Intent Tintent=new Intent(mContext, TopicPageActivity.class);				
//				Bundle tBundle=new Bundle();
//				tBundle.putString("name", item.getContentWithoutPrefix());
//				Tintent.putExtras(tBundle);
//			    mContext.startActivity(Tintent);	
			    
			 //   ToastUtil.toastshow(mContext, item.getContentWithoutPrefix());
			    
				break;
			case 3:
				// 直接使用调用浏览器
				// 这个是短链 ，还需要条用微博接口，转成原始连接 才能访问
				// 先使用短链去调用接口，获取长链，再启动浏览器
//				Intent intent = new Intent();
//				// intent.setAction("android.intent.action.VIEW");
//				Uri content_url = Uri.parse("http://www.baidu.com");
//				intent = new Intent(Intent.ACTION_VIEW);
//				intent.setData(content_url);
//				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//				mContext.startActivity(intent);
				break;
			default:
				break;
			}
		}
	}

	public static class PositionItem {
		public int start;
		public int end;
		private int prefixType;
		private String content;
		private int strLenght;

		public PositionItem(int start, int end, String content, int strLenght) {
			// TODO Auto-generated constructor stub
			this.start = start;
			this.end = end;
			this.content = content;
			this.strLenght = strLenght;
		}

		public PositionItem(int start, int end, String content) {
			// TODO Auto-generated constructor stub
			this.start = start;
			this.end = end;
			this.content = content;
		}

		public String getContent() {
			return content;
		}

		public String getContentWithoutPrefix() {
			switch (getPrefixType()) {
			case 1:
				if (end == strLenght)
                return content.substring(1, strLenght);						
				return content.substring(1, content.length() - 1);
			case 2:
				return content.substring(1, content.length() - 1);
			case 3:
				return content;
			default:
				return content;
			}
		}

		/**
		 * 1 @ 人物   2 # 话题   3 http://t.cn/ 短链   4 [ 表情
		 * 
		 * @return
		 */
		public int getPrefixType() {
			if (content.startsWith("@"))
				return 1;
			if (content.startsWith("#"))
				return 2;
			if (content.startsWith("http://"))
				return 3;
			if (content.startsWith("["))
				return 4;
			return -1;
		}
		
	}
}
