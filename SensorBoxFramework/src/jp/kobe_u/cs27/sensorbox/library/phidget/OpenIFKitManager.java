package jp.kobe_u.cs27.sensorbox.library.phidget;

import java.util.HashMap;

/***
 * OpenIFKitインスタンスをシリアル番号でシングルトン管理するクラス
 * @author shinsuke-m
 *
 */
public class OpenIFKitManager {
	private static HashMap<Integer, OpenIFKit> ifKitMap = new HashMap<Integer, OpenIFKit>();

	//
	public static OpenIFKit getOpenIFKit(int serial){
		OpenIFKit oif;

		if (ifKitMap.containsKey(serial)) {
			oif = ifKitMap.get(serial);
		}
		else { // 新規シリアルの場合，インスタンスを生成
			oif = new OpenIFKit(serial);
			ifKitMap.put(serial, oif);
		}

		return oif;
	}
}
