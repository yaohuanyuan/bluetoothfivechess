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
import com.example.bluetoothfivechess.view.ServerView;

public class ServerActivity extends Activity{
	
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
				case Constant.SERVER_CONNECTING:
					myDialog.show();
					break;
				case Constant.SERVER_CONNECT_OK:
					myDialog.dismiss();
					break;
				case Constant.SERVER_CONNECT_ERROR:
					finish();
					break;
				case Constant.SERVER_WIN:
					Toast.makeText(getApplicationContext(), "�ҷ���ʤ", Toast.LENGTH_SHORT).show();
					break;
				case Constant.CLIENT_WIN:
					Toast.makeText(getApplicationContext(), "�з���ʤ", Toast.LENGTH_SHORT).show();
					break;
				case Constant.ENEMY_ISEXIT:
					Toast.makeText(getApplicationContext(), "�з��˳���", Toast.LENGTH_SHORT).show();
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
				msg.what = Constant.SERVER_CONNECT_ERROR;
				handler.sendMessage(msg);
				super.onBackPressed();
			}
		};
		myDialog.setMessage("���ڵȴ�����...");
		setContentView(new ServerView(this));
	}
	
	@Override
	protected void onStart() {
		Utils.initGround();
		super.onStart();
	}
	
}
