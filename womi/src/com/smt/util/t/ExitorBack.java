package com.smt.util.t;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class ExitorBack {

	//�˳�
	public static void Exit(final Activity activity)
	{
		new AlertDialog.Builder(activity).setTitle("���Ѱ���ʾ").setMessage("ȷ���˳���").setPositiveButton("ȷ��", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
			activity.finish();
			java.lang.System.exit(0);
			}
		}).setNegativeButton("ȡ��", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
			}
		}).show();
	}
	

	
	
	
}