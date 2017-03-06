package jp.kobe_u.cs27.sensorbox.library.phidget;

import java.util.HashMap;

/**
 * PhidgetGPSをシングルトンで管理するためのマネージャ
 *
 * @author masa-n
 *
 */
public class PhidgetGPSManager {
	private static HashMap<Integer, PhidgetGPS> gpsMap = new HashMap<Integer, PhidgetGPS>();

	//
	public static PhidgetGPS getPhidgetGPS(int serial){
		PhidgetGPS gps;

		if (gpsMap.containsKey(serial)) {
			gps = gpsMap.get(serial);
		}
		else { // 新規シリアルの場合，インスタンスを生成
			gps = new PhidgetGPS(serial);
			gpsMap.put(serial, gps);
		}

		return gps;
	}

}
