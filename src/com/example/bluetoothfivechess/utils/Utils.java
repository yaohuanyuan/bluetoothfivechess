package com.example.bluetoothfivechess.utils;

public class Utils {

	public static boolean isWin(int x, int y) {
		if (isHFive(x, y, 5) || isVFive(x, y, 5) || isLTFive(x, y, 5) || isRTFive(x, y, 5))
			return true;
		return false;
	}
	
	
	//�ж�ˮƽ
	public static boolean isHFive(int x, int y, int mode) {
		int count = 1;
		for (int i = x; i < 13; i++) {
			if (Constant.ground[y][x] == Constant.ground[y][1+i]) {
				count++;
			} else {
				break;
			}
		}
		for (int j = x; j > 0; j--) {
			if (Constant.ground[y][x] == Constant.ground[y][j-1]) {
				count++;
			} else {
				break;
			}
		}
		if (count >= mode) {
			return true;
		}
		return false;
	}
	
	
	//�ж���ֱ����
	public static boolean isVFive(int x, int y, int mode) {
		int count = 1;
		for (int i = y; i < 13; i++) {
			if (Constant.ground[y][x] == Constant.ground[i+1][x]) {
				count++;
			} else {
				break;
			}
		}
		for (int j = y; j > 0; j--) {
			if (Constant.ground[y][x] == Constant.ground[j-1][x]) {
				count++;
			} else {
				break;
			}
		}
		if (count >= mode) {
			return true;
		}
		return false;
	}
	
	//�ж�����б��
	public static boolean isLTFive(int x, int y, int mode) {
		int count = 1;
		for (int i = 1; y - i >= 0 && x - i >= 0; i++) {
			if (Constant.ground[y][x] == Constant.ground[y - i][x - i]) {
				count++;
			} else {
				break;
			}
		}
		for (int j = 1; y + j < 14 && x + j < 14; j++) {
			if (Constant.ground[y][x] == Constant.ground[y + j][x + j]) {
				count++;
			} else {
				break;
			}
		}
		if (count >= mode) {
			return true;
		}
		return false;
	}
	
	//�ж�����б��
	public static boolean isRTFive(int x, int y, int mode) {
		int count = 1;
		for (int i = 1; y - i >= 0 && x + i < 14; i++) {
			if (Constant.ground[y][x] == Constant.ground[y - i][x + i]) {
				count++;
			} else {
				break;
			}
		}
		for (int j = 1; y + j < 14 && x - j >= 0; j++) {
			if (Constant.ground[y][x] == Constant.ground[y + j][x - j]) {
				count++;
			} else {
				break;
			}
		}
		if (count >= mode) {
			return true;
		}
		return false;
	}
	
//	public static String getStringFromIntArray(int [] data) {
//		StringBuilder ss = new StringBuilder("");
//		for (int i = 0; i < 3; i++) {
//			ss.append(data[i]);
//			
//		}
//	}
	
	public static void initGround() {
		int length = Constant.ground.length;
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				Constant.ground[i][j] = 0;
			}
		}
	}
	
}
