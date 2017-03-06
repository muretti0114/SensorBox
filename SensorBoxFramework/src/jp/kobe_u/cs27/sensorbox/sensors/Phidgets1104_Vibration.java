package jp.kobe_u.cs27.sensorbox.sensors;

import jp.kobe_u.cs27.sensorbox.framework.Sensor;
import jp.kobe_u.cs27.sensorbox.framework.Specification;
import jp.kobe_u.cs27.sensorbox.library.phidget.OpenIFKit;
import jp.kobe_u.cs27.sensorbox.library.phidget.OpenIFKitManager;
import jp.kobe_u.cs27.sensorbox.type.Vibration;

import com.phidgets.PhidgetException;

public class Phidgets1104_Vibration extends Sensor {

	private OpenIFKit oif;
	private int PORT_NUM;

	public Phidgets1104_Vibration() {

		Specification ea = new Specification();
		ea.setSensorName("Phidgets 1104_0 - Vibration Sensor");
		ea.setPropertyType("int");
		ea.setDescription("This sensor buffers a piezoelectric transducer. "
				+ "As the transducer is displaced from the mechanical neutral axis, "
				+ "bending creates strain within the piezoelectric "
				+ "element and generates voltages. ");
		ea.setProperty("Vibration");
		this.spec=ea;
		this.type = new Vibration();
		setSpecification(spec);
	}

	protected Object convertValue(Object value){
		double sensorValue = Integer.parseInt(value.toString());
		return sensorValue;
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

