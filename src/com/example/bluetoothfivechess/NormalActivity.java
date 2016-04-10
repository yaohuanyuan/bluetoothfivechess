package com.example.bluetoothfivechess;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.example.bluetoothfivechess.view.NormalView;

public class NormalActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(new NormalView(this));
	}
	
}
