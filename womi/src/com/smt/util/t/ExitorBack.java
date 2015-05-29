package com.smt.util.t;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class ExitorBack {

	//退出
	public static void Exit(final Activity activity)
	{
		new AlertDialog.Builder(activity).setTitle("茶友帮提示").setMessage("确定退出？").setPositiveButton("确定", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
			activity.finish();
			java.lang.System.exit(0);
			}
		}).setNegativeButton("取消", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
			}
		}).show();
	}
	

	
	
	
}