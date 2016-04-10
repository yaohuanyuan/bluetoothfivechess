package com.example.bluetoothfivechess.view;

import com.example.bluetoothfivechess.R;
import com.example.bluetoothfivechess.utils.Constant;
import com.example.bluetoothfivechess.utils.Load;
import com.example.bluetoothfivechess.utils.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class NormalView extends SurfaceView implements Runnable, Callback{
	
	private SurfaceHolder sfh;
	
	private Bitmap whiteChess;
	
	private Bitmap blackChess;
	
	private Bitmap background;
	
	private Canvas canvas;
	
	private Paint paint;
	
	private Boolean isStop = false;
	
	private Boolean isBlack = true;
	
	private Thread th = new Thread(this);

	public NormalView(Context context) {
		super(context);
		sfh = this.getHolder();
		sfh.addCallback(this);
		paint = new Paint();
		paint.setColor(Color.BLUE);;
		paint.setTextSize(100);
		paint.setAntiAlias(true);
		whiteChess = BitmapFactory.decodeResource(getResources(), R.drawable.white);
		blackChess = BitmapFactory.decodeResource(getResources(), R.drawable.black);
		background = BitmapFactory.decodeResource(getResources(), R.drawable.background2);
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			if (!isStop) {
				for (int y = 0; y < 14; y++) {
					for (int x = 0; x < 14; x++) {
						if (Constant.ground[y][x] == 0 && isInCircle(event.getX(), event.getY(), x, y) && isBlack) {
							Constant.ground[y][x] = Constant.BLACK_CHESS;
							
							Load.playSound(getContext(), Load.putId);
							isBlack = !isBlack;
							draw();
							if (Utils.isWin(x, y)) {
								isStop = true;
								draw();
							}
						} else if (Constant.ground[y][x] == 0 && isInCircle(event.getX(), event.getY(), x, y) && !isBlack) {
							Constant.ground[y][x] = Constant.WHITE_CHESS;
							
							Load.playSound(getContext(), Load.putId);
							isBlack = !isBlack;
							draw();
							if (Utils.isWin(x, y)) {
								isStop = true;
								draw();
							}
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
			if (!isStop && isBlack) { 
				canvas.drawText("轮到    黑子   下棋", 100, 1400, paint);
			} else if (!isStop && !isBlack) {
				canvas.drawText("轮到    白子   下棋", 100, 1400, paint);
			} else if (isStop && !isBlack) {
				canvas.drawText("黑 子 胜 利", 100, 1400, paint);
			} else {
				canvas.drawText("白 子 胜 利", 100, 1400, paint);
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
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
			draw();
			
	}
	
}
