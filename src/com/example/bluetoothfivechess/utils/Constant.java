package com.example.bluetoothfivechess.utils;

import java.util.UUID;

public class Constant {

	public static String address;      //搜索选中的
	
	public final static UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
	
	
	//屏幕宽高度
	public static int SCREEN_WIDTH;
	
	public static int SCREEN_HEIGHT;
	
	
	//棋子和方格宽度
	public static int RECT_WIDTH;
	
	public static int CHESS_WIDTH;
	
	//白棋黑棋
	public static final int WHITE_CHESS = 1;
	
	public static final int BLACK_CHESS = 2;
	
	
	//棋盘
	public static int [][] ground = new int [][] { 
		{ 0, 0, 0, 0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 },
		{ 0, 0, 0, 0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 },
		{ 0, 0, 0, 0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 },
		{ 0, 0, 0, 0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 },
		{ 0, 0, 0, 0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 },
		{ 0, 0, 0, 0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 },
		{ 0, 0, 0, 0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 },
		{ 0, 0, 0, 0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 },
		{ 0, 0, 0, 0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 },
		{ 0, 0, 0, 0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 },
		{ 0, 0, 0, 0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 },
		{ 0, 0, 0, 0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 },
		{ 0, 0, 0, 0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 },
		{ 0, 0, 0, 0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 } };
	
	
	
	
	
	//服务端handle
	public final static int SERVER_CONNECTING = 1;
	
	public final static int SERVER_CONNECT_OK = 2;
	
	public final static int SERVER_CONNECT_ERROR = 3;
	
	
	//客户端
	public final static int CLIENT_CONNECTING = 1;
	
	public final static int CLIENT_CONNECT_OK = 2;
	
	public final static int CLIENT_CONNECT_ERROR = 3;
	
	//发送数据
	public final static int SERVER_WIN = 4;
	
	public final static int CLIENT_WIN = 5;
	
	public final static int SERVER_NOWIN = 6;
	
	public final static int CLIENT_NOWIN = 7;
	
	public final static int ENEMY_ISEXIT = 8;
	
}
