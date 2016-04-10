package com.example.bluetoothfivechess.utils;

import java.util.UUID;

public class Constant {

	public static String address;      //����ѡ�е�
	
	public final static UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
	
	
	//��Ļ��߶�
	public static int SCREEN_WIDTH;
	
	public static int SCREEN_HEIGHT;
	
	
	//���Ӻͷ�����
	public static int RECT_WIDTH;
	
	public static int CHESS_WIDTH;
	
	//�������
	public static final int WHITE_CHESS = 1;
	
	public static final int BLACK_CHESS = 2;
	
	
	//����
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
	
	
	
	
	
	//�����handle
	public final static int SERVER_CONNECTING = 1;
	
	public final static int SERVER_CONNECT_OK = 2;
	
	public final static int SERVER_CONNECT_ERROR = 3;
	
	
	//�ͻ���
	public final static int CLIENT_CONNECTING = 1;
	
	public final static int CLIENT_CONNECT_OK = 2;
	
	public final static int CLIENT_CONNECT_ERROR = 3;
	
	//��������
	public final static int SERVER_WIN = 4;
	
	public final static int CLIENT_WIN = 5;
	
	public final static int SERVER_NOWIN = 6;
	
	public final static int CLIENT_NOWIN = 7;
	
	public final static int ENEMY_ISEXIT = 8;
	
}
