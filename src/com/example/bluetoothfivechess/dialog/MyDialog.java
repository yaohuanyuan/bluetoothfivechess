package com.example.bluetoothfivechess.dialog;

import android.app.ProgressDialog;
import android.content.Context;

public class MyDialog extends ProgressDialog{
	
	public MyDialog(Context context) {
		super(context);
		setProgressStyle(ProgressDialog.STYLE_SPINNER);    //Բ����ת
		setIndeterminate(false);         //���Ȳ���ȷ
		setCanceledOnTouchOutside(false);      //��������治��ʧ
		setCancelable(true);              //�����ؿ�ȡ��
	}
	
}
