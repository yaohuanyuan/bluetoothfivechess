package com.example.bluetoothfivechess;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.widget.Toast;

import com.example.bluetoothfivechess.dialog.MyDialog;
import com.example.bluetoothfivechess.utils.Constant;
import com.example.bluetoothfivechess.utils.Utils;
import com.example.bluetoothfivechess.view.ClientView;

public class ClientActivity extends Activity{
	
	private MyDialog myDialog;
	
	public static Handler handler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		handler = new Handler() {
			
			@Override
			public void handleMessage(Message msg) {
				
				switch (msg.what) {
				case Constant.CLIENT_CONNECTING:
					myDialog.show();
					break;
				case Constant.CLIENT_CONNECT_OK:
					myDialog.dismiss();
					break;
				case Constant.SERVER_CONNECT_ERROR:
					finish();
					break;
				case Constant.CLIENT_WIN:
					Toast.makeText(ClientActivity.this, "�ҷ���ʤ", Toast.LENGTH_SHORT).show();
					break;
				case Constant.SERVER_WIN:
					Toast.makeText(ClientActivity.this, "�ҷ�ʧ��", Toast.LENGTH_SHORT).show();
					break;
				case Constant.ENEMY_ISEXIT:
					Toast.makeText(ClientActivity.this, "�з����˳�", Toast.LENGTH_SHORT).show();
					finish();
					break;
				default:
					break;
				}
				super.handleMessage(msg);
			}
		};
		
		
		myDialog = new MyDialog(this) {
			@Override
			public void onBackPressed() {
				Message msg = new Message();
				msg.what = Constant.CLIENT_CONNECT_ERROR;
				handler.sendMessage(msg);
				super.onBackPressed();
			}
		};
		myDialog.setMessage("����Ѱ������...");
		setContentView(new ClientView(this));         //�ŵ����棬��Ȼִ����handle��û�����
	}
	
	@Override
	protected void onStart() {
		Utils.initGround();
		super.onStart();
	}
	
}
