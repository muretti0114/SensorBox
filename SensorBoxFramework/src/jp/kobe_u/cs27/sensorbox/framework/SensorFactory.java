package jp.kobe_u.cs27.sensorbox.framework;

import java.util.HashMap;

/**
 * 指示されたパラメータに基づき，具体的なセンサオブジェクトを生成するファクトリ．
 * 
 * @author okushi, masa-n
 *
 */
public class SensorFactory {
	/**
	 * コンストラクタ
	 */
	public SensorFactory(){};

	
	/**
	 * パラメータに基づいて，具体的なセンサオブジェクトを生成して返す
	 * @param id センサのID
	 * @param sensorClass センサのクラス名
	 * @param access デバイスへのアクセスパラメータ
	 * @return センサオブジェクト
	 */
	public Sensor create(String id,String sensorClass,HashMap<String,String> access){
		 // クラス名．パッケージ名が決め打ちなのが気になる．．．あとで修正可能にすべき．
		String className = "jp.kobe_u.cs27.sensorbox.sensors." + sensorClass;
		
		//センサクラスを動的に生成するためのクラスオブジェクト
		Class<? extends Sensor> clazz;

		//与えられたクラス名でクラスを動的に生成（抽象センサクラスの子クラスであることを明示）
		try {
			clazz = Class.forName(className).asSubclass(Sensor.class);
		} catch (ClassNotFoundException e) {
			System.err.println("sensorclass not found: " + className);
			return null;
		}

		try {
			//センサオブジェクトを生成
			Sensor sensor = clazz.newInstance();

			//ID, アクセスパラメタをセットして，接続
			sensor.setSensorId(id);
			sensor.setAccess(access);
			sensor.connect();

			return sensor;


		} catch (InstantiationException e) {
			System.err.println("InstantiationException");
			return null;
		} catch (IllegalAccessException e) {
			System.err.println("IllegalAccessException");
			return null;
		}


	}

}
