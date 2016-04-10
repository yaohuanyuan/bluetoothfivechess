package com.example.bluetoothfivechess.dialog;

import android.app.ProgressDialog;
import android.content.Context;

public class MyDialog extends ProgressDialog{
	
	public MyDialog(Context context) {
		super(context);
		setProgressStyle(ProgressDialog.STYLE_SPINNER);    //圆形旋转
		setIndeterminate(false);         //进度不明确
		setCanceledOnTouchOutside(false);      //点击框外面不消失
		setCancelable(true);              //按返回可取消
	}
	
}
