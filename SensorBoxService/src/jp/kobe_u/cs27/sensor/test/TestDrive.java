package jp.kobe_u.cs27.sensor.test;

import jp.kobe_u.cs27.sensor.service.SensorValue;
import jp.kobe_u.cs27.sensor.service.SensorBoxService;
import jp.kobe_u.cs27.sensor.service.SensorInfo;

public class TestDrive {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		SensorBoxService ser = new SensorBoxService();

		for (SensorInfo info: ser.getAllInfos()) {
			System.out.println(info);
		}


		for (SensorValue sv: ser.getAllValues()) {
			System.out.println(sv);
		}


	}

}
