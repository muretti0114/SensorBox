package jp.kobe_u.cs27.sensorbox.sensors;

import jp.kobe_u.cs27.sensorbox.framework.Sensor;
import jp.kobe_u.cs27.sensorbox.framework.Specification;
import jp.kobe_u.cs27.sensorbox.library.phidget.OpenIFKit;
import jp.kobe_u.cs27.sensorbox.library.phidget.OpenIFKitManager;
import jp.kobe_u.cs27.sensorbox.type.Motion;

import com.phidgets.PhidgetException;

public class Phidgets1111_Motion extends Sensor{
	private OpenIFKit oif ;
	private int PORT_NUM;

	public Phidgets1111_Motion() {


		Specification spec = new Specification();
		spec.setSensorName("Phidgets 1111_0 - Motion Sensor");
		spec.setPropertyType("boolean");
		spec.setDescription("The 1111 detects changes in infrared radiation"
				+ " that occur when there is movement by a person (or object),"
				+ " which is different in temperature from the surroundings. "
				+ "As this sensor detects temperature differences, it is well "
				+ "suited to detecting the motion of people by their body temperature.");
		spec.setProperty("Motion");

		this.spec=spec;
		this.type = new Motion();
		setSpecification(spec);
	}

	public Object readValue() {
		try {
			return oif.getValue(PORT_NUM);
		} catch (PhidgetException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return null;
		}
	}

	// 参照
	// http://www.phidgets.com/documentation/Phidgets/1111_0_Product_Manual.pdf
	protected Object convertValue(Object value){
		Boolean bool1= Boolean.valueOf(true);
		Boolean bool2= Boolean.valueOf(false);
		Integer v1 =(Integer)value;
		int v2 = v1.intValue();
		if(v2>650)
			return bool1;
		if(v2<350)
			return bool1;
		else
			return bool2;
	}

	public Object getValue()  {
		try {
			return convertValue(readValue());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public Object getValueByProperty(String property) {
		return getValue();
	}
	public void connect(){
		//accessmethodの中でこの名前と併せる必要あり
		int serial = Integer.parseInt(access.get("serial"));
		this.oif =  OpenIFKitManager.getOpenIFKit(serial);
		this.PORT_NUM = Integer.parseInt(access.get("port"));

	}
}
