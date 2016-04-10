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
					Toast.makeText(ClientActivity.this, "我方获胜", Toast.LENGTH_SHORT).show();
					break;
				case Constant.SERVER_WIN:
					Toast.makeText(ClientActivity.this, "我方失利", Toast.LENGTH_SHORT).show();
					break;
				case Constant.ENEMY_ISEXIT:
					Toast.makeText(ClientActivity.this, "敌方已退出", Toast.LENGTH_SHORT).show();
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
		myDialog.setMessage("正在寻找主机...");
		setContentView(new ClientView(this));         //放到下面，不然执行了handle还没定义好
	}
	
	@Override
	protected void onStart() {
		Utils.initGround();
		super.onStart();
	}
	
}
