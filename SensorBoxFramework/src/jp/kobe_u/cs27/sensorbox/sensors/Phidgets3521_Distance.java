package jp.kobe_u.cs27.sensorbox.sensors;


import com.phidgets.PhidgetException;

import jp.kobe_u.cs27.sensorbox.framework.Sensor;
import jp.kobe_u.cs27.sensorbox.framework.Specification;
import jp.kobe_u.cs27.sensorbox.library.phidget.OpenIFKit;
import jp.kobe_u.cs27.sensorbox.library.phidget.OpenIFKitManager;
import jp.kobe_u.cs27.sensorbox.type.Distance;


/**
 * DistanceSensor
 * Phidget 1101 Distance Sensor Adapter + SHARP 10-80cm IR Sensor
 * をラップするセンサデバイスクラス
 * @version 1.0, 2012/09/10
 * @author masa-n
 * @see http://www.phidgets.com/products.php?category=2&product_id=3521_0
 * @see http://www.phidgets.com/products.php?category=2&product_id=1101_0
 *
 */
public class Phidgets3521_Distance extends Sensor {

	private OpenIFKit oif;
	private int PORT_NUM;


	public Phidgets3521_Distance() {

		Specification ea = new Specification();
		ea.setSensorName("Phidgets 3521_0 - Sharp Distance Sensor (10-80cm)");
		ea.setPropertyType("double");
		ea.setDescription("This popular sensor made by Sharp produces"
				+ " an analog output that varies from 3.1V at 10cm to 0.3V at 80cm. ");
		ea.setProperty("Distance");
		this.spec=ea;
		this.type = new Distance();
		setSpecification(spec);
	}

	// Distance Sensor　10cm-80cm　の変換式
	// http://www.phidgets.com/products.php?category=2&product_id=3521_0
	protected Object convertValue(Object value){
		double sensorValue = Integer.parseInt(value.toString());
		double distance;

		if (sensorValue <= 82 || sensorValue>= 498) {
			distance = -1.0; //IRセンサのレンジ外，無効な値．
		} else {
			distance = 4800 / (sensorValue -20.0);
		}

		return distance;

	}

	public Object getValue()  {
		return convertValue(readValue());
	}
	public Object getValueByProperty(String property) {
		return getValue();
	}
	protected Object readValue()  {
		try {
			return oif.getValue(PORT_NUM);
		} catch (PhidgetException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void connect(){
		//accessmethodの中でこの名前と併せる必要あり
		int serial = Integer.parseInt(access.get("serial"));
		this.oif =  OpenIFKitManager.getOpenIFKit(serial);
		this.PORT_NUM = Integer.parseInt(access.get("port"));

	}


}
