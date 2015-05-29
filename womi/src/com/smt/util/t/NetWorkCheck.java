package com.smt.util.t;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**网络提示
 * @author by Tipsy|Bear
 *
 */
public class NetWorkCheck
{
	//欢迎页面使用
	public static  boolean note_Intent(Context context) {
		ConnectivityManager con = (ConnectivityManager) context
		.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkinfo = con.getActiveNetworkInfo();
		if (networkinfo == null || !networkinfo.isAvailable()) {
		// 当前网络不可用
//		ToastUtil.toastshow(context.getApplicationContext(), "无网络连接");
		return false;
		}
		boolean wifi = con.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
		.isConnectedOrConnecting();
		if (!wifi)
		{ // 提示使用wifi
//			ToastUtil.toastshow(context.getApplicationContext(),"建议您使用WIFI以减少流量！" );
		}
		return true;
		}
	//按钮使用
	public static  boolean checkNetWork(Context context) {
		ConnectivityManager con = (ConnectivityManager) context
		.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkinfo = con.getActiveNetworkInfo();
		if (networkinfo == null || !networkinfo.isAvailable()) {
		// 当前网络不可用
//		ToastUtil.toastshow(context.getApplicationContext(), "无网络连接");
		return false;
		}
//		boolean wifi = con.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
//		.isConnectedOrConnecting();
//		if (!wifi)
//		{ // 提示使用wifi
//			ToastUtil.toastshow(context.getApplicationContext(),"建议您使用WIFI以减少流量！" );
//		}
		return true;
		}
}
