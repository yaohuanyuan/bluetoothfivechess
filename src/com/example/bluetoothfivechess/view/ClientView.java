package com.example.bluetoothfivechess.view;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Message;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

import com.example.bluetoothfivechess.ClientActivity;
import com.example.bluetoothfivechess.R;
import com.example.bluetoothfivechess.utils.Constant;
import com.example.bluetoothfivechess.utils.Load;
import com.example.bluetoothfivechess.utils.Utils;

public class ClientView extends SurfaceView implements Runnable, Callback{
	
	private BluetoothAdapter adapter;
	
	private BluetoothDevice device;
	
	private BluetoothSocket socket;
	
	private Thread th = new Thread(this);
	
	private Canvas canvas;
	
	private SurfaceHolder sfh;
	
	private Bitmap background;
	
	private Paint paint;
	
	private Bitmap whiteChess;
	
	private Bitmap blackChess;
	
	private boolean isStop = false;
	
	private boolean isMyturn = true;
	
	public ClientView(Context context) {
		super(context);
		sfh = this.getHolder();
		sfh.addCallback(this);
		paint = new Paint();
		paint.setColor(Color.BLUE);;
		paint.setTextSize(100);
		paint.setAntiAlias(true);
		whiteChess = BitmapFactory.decodeResource(this.getContext().getResources(), R.drawable.white);
		blackChess = BitmapFactory.decodeResource(this.getContext().getResources(), R.drawable.black);
		background = BitmapFactory.decodeResource(this.getContext().getResources(), R.drawable.background2);
		initBluetooth();
	}
	
	private void initBluetooth() {
		adapter = BluetoothAdapter.getDefaultAdapter();
		adapter.cancelDiscovery();
		device = adapter.getRemoteDevice(Constant.address);
		ClientThread clientThread = new ClientThread();
		clientThread.start();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			if (!isStop && isMyturn) {
				for (int y = 0; y < 14; y++) {
					for (int x = 0; x < 14; x++) {
						if (Constant.ground[y][x] == 0 && isInCircle(event.getX(), event.getY(), x, y)) {
							Constant.ground[y][x] = Constant.WHITE_CHESS;
							draw();
							Load.playSound(getContext(), Load.putId);
							if (Utils.isWin(x, y)) {
								isStop = true;
								Message msg = ClientActivity.handler.obtainMessage(Constant.CLIENT_WIN);
								ClientActivity.handler.sendMessage(msg);
								
								new WriteThread(x, y, Constant.CLIENT_WIN).start();
							} else {
								new WriteThread(x, y, Constant.CLIENT_NOWIN).start();
							}
							isMyturn = false;
							draw();
						}
					}
				}
			}
		}
		return super.onTouchEvent(event);
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		th.start();
	}
	
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		isStop = true;
		
		new WriteThread(0, 0, Constant.ENEMY_ISEXIT).start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (!isStop) {
			draw();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
private void draw() {
		
		try {
			canvas = sfh.lockCanvas();
			canvas.drawBitmap(background, null, new RectF(0, 0, Constant.SCREEN_WIDTH, Constant.SCREEN_HEIGHT), null);
			//横线
			for (int i = 0; i < 14; i++) {
				canvas.drawLine(Constant.RECT_WIDTH, i*Constant.RECT_WIDTH+Constant.RECT_WIDTH, 14*Constant.RECT_WIDTH, i*Constant.RECT_WIDTH+Constant.RECT_WIDTH, paint);
			}
			for (int i = 0; i < 14; i++) {
				canvas.drawLine(i*Constant.RECT_WIDTH+Constant.RECT_WIDTH, Constant.RECT_WIDTH, i*Constant.RECT_WIDTH+Constant.RECT_WIDTH, 14*Constant.RECT_WIDTH, paint);
			}
			for (int y = 0; y < 14; y++) {
				for (int x = 0; x < 14; x++) {
					if (Constant.ground[y][x] != 0) {
						drawChess(x, y);
					}
				}
			}
			if (!isStop && isMyturn) { 
				canvas.drawText("轮到     我方   下棋", 100, 1400, paint);
			} else if (!isStop && !isMyturn) {
				canvas.drawText("轮到     对方   下棋", 100, 1400, paint);
			} else if (isStop && !isMyturn) {
				canvas.drawText("我 方 胜 利", 100, 1400, paint);
			} else {
				canvas.drawText("我 方 失 败", 100, 1400, paint);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (canvas != null) {
				sfh.unlockCanvasAndPost(canvas);
			}
			
		}
		
	}

	private void drawChess(int x, int y) {
		if (Constant.ground[y][x] == Constant.WHITE_CHESS) {
			canvas.drawBitmap(whiteChess, (((x+1)*Constant.RECT_WIDTH) - (Constant.CHESS_WIDTH/2))-10, (((y+1)*Constant.RECT_WIDTH) - (Constant.CHESS_WIDTH/2)-10), null);
		} else if (Constant.ground[y][x] == Constant.BLACK_CHESS) {
			canvas.drawBitmap(blackChess, (((x+1)*Constant.RECT_WIDTH) - (Constant.CHESS_WIDTH/2))-10, (((y+1)*Constant.RECT_WIDTH) - (Constant.CHESS_WIDTH/2)-10), null);
		}
	}
	
	private boolean isInCircle(float touch_x, float touch_y, int x, int y) {
		return ((touch_x - (x+1)*Constant.RECT_WIDTH)*(touch_x - (x+1)*Constant.RECT_WIDTH) +
			   (touch_y - (y+1)*Constant.RECT_WIDTH)*(touch_y - (y+1)*Constant.RECT_WIDTH)) <
			   (Constant.RECT_WIDTH/2)*(Constant.RECT_WIDTH/2);
	}
	
	private class ClientThread extends Thread {
		@Override
		public void run() {
			try {
				
				socket = device.createRfcommSocketToServiceRecord(Constant.uuid);
				Message connectingMsg = ClientActivity.handler.obtainMessage(Constant.CLIENT_CONNECTING);
				ClientActivity.handler.sendMessage(connectingMsg);
				socket.connect();                                 //阻塞知道连接
				Message connetOkMsg = ClientActivity.handler.obtainMessage(Constant.CLIENT_CONNECT_OK);
				ClientActivity.handler.sendMessage(connetOkMsg);
				new ReadThread().start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private class WriteThread extends Thread {
		
		private byte[] data = new byte[3];
		
		public WriteThread(int x, int y, int type) {
			data[0] = (byte)x;
			data[1] = (byte)y;
			data[2] = (byte)type;
		}
		
		@Override
		public void run() {
			OutputStream outputStream = null;
			try {
				outputStream = socket.getOutputStream();
				outputStream.write(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				try {
					outputStream.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	
	private class ReadThread extends Thread {
		
		@Override
		public void run() {
			byte[] data = new byte[3];
			InputStream inputStream = null;
			try {
				inputStream = socket.getInputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
			while (true) {
				try {
					inputStream.read(data);
					Constant.ground[(int)data[1]][(int)data[0]] = Constant.BLACK_CHESS;   //服务器为黑子
					draw();
					Load.playSound(getContext(), Load.putId);
					if ((int)data[2] == Constant.SERVER_WIN) {
						isStop = true;
						Message msg = ClientActivity.handler.obtainMessage(Constant.SERVER_WIN);
						ClientActivity.handler.sendMessage(msg);
					} else if ((int)data[2] == Constant.ENEMY_ISEXIT) {
						Message msg1 = ClientActivity.handler.obtainMessage(Constant.ENEMY_ISEXIT);
						ClientActivity.handler.sendMessage(msg1);
					}
					isMyturn = true;
					draw();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					try {
						inputStream.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
			
		}
		
	}
	
}
