package jp.kobe_u.cs27.sensorbox.framework;

import java.util.HashMap;

import jp.kobe_u.cs27.sensorbox.type.SensorType;

/**
 * 抽象センサクラス．ID, 仕様，型，デバイスアクセスのパラメタ等を持つ．
 * @author okushi, masa-n
 *
 */
public abstract class Sensor {
	protected Specification spec; //センサの仕様
	protected String id; //ID
	protected HashMap<String,String> access; //デバイスアクセスのためのパラメタ（キーバリュー）
	protected SensorType type;   //センサの型
	protected int samplingRate; // サンプリングレート．milisec.

	/**
	 * コンストラクタ
	 */
	public Sensor() {
	}

	/**
	 * デバイスから値を読み出す
	 * @return 生データ
	 */
	protected abstract Object readValue();

	
	/**
	 * 生データをデバイス固有値の変換式にかけて，標準的な値に変換する
	 * @param value 生データ
	 * @return 標準化された値
	 */
	protected abstract Object convertValue(Object value);

	
	/**
	 * デバイスに接続する
	 */
	public  abstract void connect();
	
	
	/**
	 * センサから値を取得する（一番主要なメソッド）
	 * @return センサの（標準化された）現在値
	 */
	public abstract Object getValue() ;

	public abstract Object getValueByProperty(String property);
	/**
	 * センサの仕様を取得する
	 * @return 仕様
	 */
	public Specification getSpecification(){
		return this.spec;
	}
	
	
	/**
	 * センサの型を取得する
	 * @return センサの型
	 */
	public SensorType getSensorType(){
		return this.type;
	}
	
	
	/**
	 * センサのアクセスパラメタを取得する
	 * @return アクセスパラメタのマップ（パラメタ名，値）
	 */
	public HashMap<String,String> getAccess(){
		return this.access;
	}

	
	/**
	 * センサのIDを取得する
	 * @return センサID
	 */
	public String getSensorId() {
		return id;
	}

	
	/**
	 * センサのIDを設定する
	 * @param sensorId センサID
	 */
	public void setSensorId(String sensorId) {
		this.id = sensorId;
	}

	
	/**
	 * センサの仕様を設定する
	 * @param spec センサの仕様
	 */
	public void setSpecification(Specification spec) {
		this.spec = spec;
	}

	
	/**
	 * センサのアクセスパラメタをセットする
	 * @param access アクセスパラメタのマップ（パラメタ名，値）
	 */
	public void setAccess(HashMap<String,String> access){
		this.access = access;
	}


}