package jp.kobe_u.cs27.sensorbox.framework;

import java.util.Arrays;

/**
 * センサデバイスに固有の仕様情報．デバイス名，プロパティの型，プロパティ名，
 * 説明，最大・最小値，列挙尺度を保持する
 * 
 * @author okushi, masa-n
 *
 */
public class Specification {
	private String sensorName;    //センサデバイスの名前(例：Phidgets 1127_0 - Precision Light Sensor)
	private String propertyType;  //プロパティの型．boolean,int,double,string,complex
	private String property;      //プロパティの名前
	private String description;   //説明
	private String min;           //プロパティの最小値
	private String max;           //プロパティの最大値
	private String[] enums;       //センサがとりえる名義尺度
	
	/**
	 * コンストラクタ
	 */
	public Specification() {
		this.description="";
		this.max="";
		this.min="";
		this.sensorName="";
		this.propertyType="";
//		this.enums= new String[1];
//		enums[0] = "hot";
//		enums[1] = "cold";
	}
	
	//以下基本的なsetter/getterなので，javadocは省略
	
	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}
	
//	public boolean isInRange(Object value) {
//		if(type.equals("bool")) {
//			return true;
//		}
//		else if(type.equals("int")) {
//			return true;
//		}
//		else if(type.equals("string")) {
//			return true;
//		}
//		return false;
//	}

	public String getSensorName() {
		return sensorName;
	}

	public void setSensorName(String sensorName) {
		this.sensorName = sensorName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public String[] getEnums() {
		return enums;
	}

	public void setEnums(String[] enums) {
		this.enums = enums;
	}

	public String getPropertyType() {
		return propertyType;
	}
	
	public void setPropertyType(String type) {
		this.propertyType = type;
	}


	@Override
	public String toString() {
		return "Specification [sensorName=" + sensorName + ", propertyType="
				+ propertyType + ", property=" + property + ", description="
				+ description + ", min=" + min + ", max=" + max + ", enums="
				+ Arrays.toString(enums) + "]";
	}
	
	
}
