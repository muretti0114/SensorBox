package jp.kobe_u.cs27.sensorbox.framework;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 複数のセンサを収容するセンサボックスのクラス．
 * @author okushi, masa-n
 */
public class SensorBox {
	/** センサボックスのID */
	private String id;
	/** センサの一覧．IDをキー，実体を値とするマップで保持する*/
	private HashMap<String, Sensor> sensorList = new HashMap<String,Sensor>();
	/** センサボックスの所持者 */
	private String owner;
	/** センサボックスの設置場所 */
	private String location;
	private String endpoint;

	
	/**
	 * ボックスのIDを取得する
	 * @return ボックスID
	 */
	public String getId() {
		return id;
	}

	/**
	 * ボックスのIDを設定する
	 * @param id ボックスID
	 */
	public void setId(String id) {
		this.id = id;
	}


	/**
	 * センサーリストにセンサを追加する
	 * @param id センサID
	 * @param sensor 追加するセンサ
	 */
	public void addSensor(String id, Sensor sensor){
		sensorList.put(id,sensor);
	}

	/**
	 * 全センサのマップを返す
	 * @return 全センサのマップ
	 */
	public HashMap<String, Sensor> getSensorList() {
		return sensorList;
	}
	
	/**
	 * センサのマップを設定する
	 * @param sensorList 全センサのマップ
	 */
	public void setSensorList(HashMap<String, Sensor> sensorList) {
		this.sensorList = sensorList;
	}
	
	/**
	 * 所持者を取得する
	 * @return 所持者
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * 所持者を設定する
	 * @param owner 所持者
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	
	/**
	 * 設置場所を取得する
	 * @return 設置場所
	 */
	public String getLocation() {
		return location;
	}
	
	/** 
	 * 設置場所を設定する
	 * @param location 設置場所
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	
	/**
	 * エンドポイントを取得する
	 * @return エンドポイント
	 */
	public String getEndpoint() {
		return endpoint;
	}
	
	/**
	 * エンドポイントを設定する
	 * @param endpoint エンドポイント
	 */
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	
	
	/**
	 * IDを指定して，1つのセンサを取得する
	 * @param sensorId センサID
	 * @return そのIDを持つセンサ
	 */
	
	public Sensor getSensor(String sensorId) {
		Sensor sensor = null;
		if (sensorList.containsKey(sensorId)) {
			sensor = sensorList.get(sensorId);
		} else {
			System.err.println("Sendor " + sensorId + " does not exist.");
		}
		
		return sensor;
	}
	
	/**
	 * センサIDを指定して，そのセンサの値を取得する
	 * @param sensorId センサID
	 * @return そのIDを持つセンサの値
	 */
	public Object getValue(String sensorId){
		Sensor sensor = getSensor(sensorId);
		return sensor.getValue();
	}

	/**
	 * センサIDを指定して，そのセンサの仕様を取得する
	 * @param sensorId センサID
	 * @return そのIDを持つセンサの仕様
	 */
	public Specification getSpec(String sensorId){
		Sensor sensor = getSensor(sensorId);
		return sensor.getSpecification();
	}
	
	/**
	 * すべてのセンサのIDの一覧を配列で返す
	 * @return 全センサのIDの配列
	 */
	public String [] getSensorIds() {
		String [] sensorIds = getSensorList().keySet().toArray(new String[0]);
		Arrays.sort(sensorIds);
		
		return sensorIds;
	}

}
