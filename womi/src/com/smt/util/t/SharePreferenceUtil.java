package com.smt.util.t;
import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferenceUtil {
	private SharedPreferences sp;
	private SharedPreferences.Editor editor;

	public SharePreferenceUtil(Context context, String file) {
		sp = context.getSharedPreferences(file, context.MODE_PRIVATE);
		editor = sp.edit();
	}

	public String getPhone() {
		return sp.getString("phone", "");
	}

	public void setPhone(String phone) {
		editor.putString("phone", phone);
		editor.commit();
	}
	
	public void setPasswd(String passwd) {
		editor.putString("passwd", passwd);
		editor.commit();
	}

	public String getPasswd() {
		return sp.getString("passwd", "");
	}
	
	public void setToken(String token) {
	    editor.putString("auth_token", token);
	    editor.commit();
	}
	
	public String getToken() {
	    return sp.getString("auth_token", "");
	}


	public void setId(String id) {
		editor.putString("id", id);
		editor.commit();
	}

	public String getId() {
		return sp.getString("id", "");
	}

	
	public String getName() {
		return sp.getString("name", "");
	}

	public void setName(String name) {
		editor.putString("name", name);
		editor.commit();
	}

	public String getImg() {
		return sp.getString("img", "");
	}

	public void setImg(String img) {
		editor.putString("img", img);
		editor.commit();
	}
	
	
	
	public int getType() {
		
		return sp.getInt("type", 0);
	}

	public void setType(int type) {
		editor.putInt("type", type);
		editor.commit();
	}
	
	public boolean getauto(){
		
		return sp.getBoolean("auto", false);
	}
   
	public void setauto(Boolean auto) {
		
		editor.putBoolean("auto", auto);
		editor.commit();
	}
	
	public String getBusiness_id() {
		return sp.getString("business_id", "");
	}

	public void setBusiness_id(String business_id) {
		editor.putString("business_id", business_id);
		editor.commit();
	}
	
	
	public String getSuper_id() {
		return sp.getString("super_id", "");
	}

	public void setSuper_id(String super_id) {
		editor.putString("super_id", super_id);
		editor.commit();
	}
	
   public boolean getfirst(){
		
		return sp.getBoolean("first", false);
	}
   
	public void setfirst(Boolean first) {
		
		editor.putBoolean("first", first);
		editor.commit();
	}
	
	 public boolean getfirst1(){
			
			return sp.getBoolean("first1", false);
		}
	   
		public void setfirst1(Boolean first) {
			
			editor.putBoolean("first1", first);
			editor.commit();
		}
	
	
	
	//性别
	 public String getsex(){
			
		 return sp.getString("sex", "");
		}
	   
		public void setsex(String sex) {
			
			editor.putString("sex", sex);
			editor.commit();
		}
		//简介
		 public String getintro(){
				
			 return sp.getString("intro", "");
			}
		   
			public void setintro(String intro) {
				
				editor.putString("intro", intro);
				editor.commit();
			}
			//地区
			 public String getarea(){
					
				 return sp.getString("area", "");
				}
			   
				public void setarea(String area) {
					
					editor.putString("area", area);
					editor.commit();
				}
				
				
				 public String getload(){
						
					 return sp.getString("load", "");
					}
				   
					public void setload(String load) {
						
						editor.putString("load", load);
						editor.commit();
					}
					
					
							
					
}
