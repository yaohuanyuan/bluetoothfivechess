package com.example.bluetoothfivechess;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.bluetoothfivechess.utils.Constant;
import com.example.bluetoothfivechess.utils.Load;
import com.example.bluetoothfivechess.utils.Utils;

public class MainActivity extends Activity {
	
	private Animation animation;
	
	private Button normalButton;
	
	private Button bluetoothButton;
	
//	private MyDialog loadDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constant.SCREEN_WIDTH = dm.widthPixels;
        Constant.SCREEN_HEIGHT = dm.heightPixels;
        Constant.RECT_WIDTH = Constant.SCREEN_WIDTH/15;
        Constant.CHESS_WIDTH = Constant.SCREEN_WIDTH/20;
//        loadDialog = new MyDialog(this);
//        loadDialog.setCancelable(false);
//        loadDialog.setMessage("ÕýÔÚ¼ÓÔØ....");
//        loadDialog.show();
        Load.load(this);
//        loadDialog.dismiss();
        animation = AnimationUtils.loadAnimation(this, R.anim.button_animation);
        normalButton = (Button)findViewById(R.id.normalButton);
        bluetoothButton = (Button)findViewById(R.id.bluetoothButton);
        
        normalButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				animation.setAnimationListener(new AnimationListener() {
					
					@Override
					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub
						normalButton.setClickable(false);
						bluetoothButton.setClickable(false);
					}
					
					@Override
					public void onAnimationRepeat(Animation animation) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onAnimationEnd(Animation animation) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(MainActivity.this, NormalActivity.class);
						startActivity(intent);
					}
				});
				Load.playSound(getApplicationContext(), Load.clickId);
				normalButton.startAnimation(animation);
			}
		});
        
        bluetoothButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				animation.setAnimationListener(new AnimationListener() {
					
					@Override
					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub
						normalButton.setClickable(false);
						bluetoothButton.setClickable(false);
					}
					
					@Override
					public void onAnimationRepeat(Animation animation) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onAnimationEnd(Animation animation) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(MainActivity.this, RoomActivity.class);
						startActivity(intent);
					}
				});
				Load.playSound(getApplicationContext(), Load.clickId);
				bluetoothButton.startAnimation(animation);
			}
		});
        
    }

    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	Utils.initGround();
    	normalButton.setClickable(true);
    	bluetoothButton.setClickable(true);
    }
   
}
