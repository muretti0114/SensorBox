package jp.kobe_u.cs27.sensorbox.sensors;

import jp.kobe_u.cs27.sensorbox.framework.Sensor;
import jp.kobe_u.cs27.sensorbox.framework.Specification;
import jp.kobe_u.cs27.sensorbox.library.phidget.OpenIFKit;
import jp.kobe_u.cs27.sensorbox.library.phidget.OpenIFKitManager;
import jp.kobe_u.cs27.sensorbox.type.MagneticForce;

import com.phidgets.PhidgetException;

public class Phidgets1108_Magnetic extends Sensor  {

	private OpenIFKit oif;
	private int PORT_NUM;

	public Phidgets1108_Magnetic() {
		Specification ea = new Specification();
		ea.setSensorName("Phidget 1108_0 - Magnetic Sensor");
		ea.setPropertyType("int");
		ea.setDescription("The 1108 is a Hall-effect sensor"
				+ "that provides a voltage output that is proportional"
				+ " to the applied magnetic field.");
		ea.setProperty("Magnetic");
		this.spec=ea;
		this.type = new MagneticForce();
		setSpecification(spec);
	}

	// Magnetic Sensor 1108の変換式
	// http://www.phidgets.com/docs/1108_User_Guide
	protected Object convertValue(Object value){
		int sensorValue = Integer.parseInt(value.toString());
		int gauss;

		gauss = 500 - sensorValue;

		return gauss;

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
