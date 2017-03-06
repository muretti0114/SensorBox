package jp.kobe_u.cs27.sensorbox.library.phidget;

import java.util.HashMap;

/**
 * TextLCDをシングルトンで管理するためのマネージャ
 *
 * @author masa-n
 *
 */
public class TextLCDManager {
	private static HashMap<Integer, TextLCD> lcdMap = new HashMap<Integer, TextLCD>();

	public static TextLCD getTextLCD(int serial){
		TextLCD lcd;

		if (lcdMap.containsKey(serial)) {
			lcd = lcdMap.get(serial);
		}
		else { // 新規シリアルの場合，インスタンスを生成
			lcd = new TextLCD(serial);
			lcdMap.put(serial, lcd);
		}

		return lcd;
	}

}
