package com.example.bluetoothfivechess.utils;

import com.example.bluetoothfivechess.R;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class Load {
	
	public static SoundPool soundPool;
	
	public static int clickId;
	
	public static int putId;
	
	public static void load(Context context) {
		soundLoad(context);
	}
	
	public static void soundLoad(Context context) {
		soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
		clickId = soundPool.load(context, R.raw.click, 1);       //4个load方法中都有一个priority参数，该参数目前还没有任何作用，Android建议将该 参数设为1，保持和未来的兼容性。
		putId = soundPool.load(context, R.raw.put, 1);
	}
	
	public static void playSound(Context context, int soundId) {
		AudioManager manager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		float maxVol = manager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		soundPool.play(soundId, maxVol, maxVol, 1, 0, 1.0f);
		
	}
	
}
