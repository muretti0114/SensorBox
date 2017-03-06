package jp.kobe_u.cs27.sensorbox.framework.test;
import java.net.URL;

import jp.kobe_u.cs27.sensorbox.framework.Sensor;
import jp.kobe_u.cs27.sensorbox.framework.SensorBox;
import jp.kobe_u.cs27.sensorbox.framework.SensorBoxFactory;
import jp.kobe_u.cs27.sensorbox.library.phidget.IFKitUtility;

public class TestDrive {

	public static void main(String args[]) throws Exception{

		/* ---------------- 定義ファイルを決め打ちする方法 -----------------------*/
		//SensorBoxFactory boxFactory = new SensorBoxFactory("sbox-phidget-408127.xml");
		
		/* ---------------- 定義ファイルをクラウドからダウンロードする方法 -----------------------*/
		//(1) シリアル番号取得
		int serial = IFKitUtility.getSerialNumber(); 
		//(2) デバイスIDを生成
		String deviceid = "sbox-phidget-"+serial;
		//(3) 定義ファイルをダウンロード
		String urlStr = "http://wsapp.cs.kobe-u.ac.jp/SensorBoxManageService/getConfig.cgi?id=" + deviceid;
		URL url = new URL(urlStr);
		SensorBoxFactory boxFactory = new SensorBoxFactory(url);

		//ここからは共通
		SensorBox box = boxFactory.create();
		String [] sensorIds = box.getSensorIds();

		while(true){

			for (String id: sensorIds) {
				Sensor s = box.getSensor(id);
				System.out.println(s.getSensorId() + ":" + s.getValue() + ",");
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			System.out.println("---------------------------");
		}


	}

}
