package jp.kobe_u.cs27.sensorbox.sensors;

import jp.kobe_u.cs27.sensorbox.framework.Sensor;
import jp.kobe_u.cs27.sensorbox.framework.Specification;
import jp.kobe_u.cs27.sensorbox.library.phidget.OpenIFKit;
import jp.kobe_u.cs27.sensorbox.library.phidget.OpenIFKitManager;
import jp.kobe_u.cs27.sensorbox.type.Motion;

import com.phidgets.PhidgetException;

public class Phidgets1106_Force extends Sensor {

	private OpenIFKit oif;
	private int PORT_NUM;

	public Phidgets1106_Force() {
		Specification ea = new Specification();
		ea.setSensorName("Phidgets 1106_0 - Force Sensor");
		ea.setPropertyType("boolean");
		ea.setDescription("The 1106 reads 0 when no force is applied."
				+ " As force increases on the circular button the value "
				+ "increases towards 1000. It is intended as a user input "
				+ "device (i.e. recognizing that someone is pushing a button). ");
		ea.setProperty("Pressure");
		this.spec=ea;

		this.type = new Motion();
		setSpecification(spec);
	}

	protected Object convertValue(Object value){
		Integer v = new Integer(value.toString());
		if(v.intValue()>100){
			return true;
		}else{
			return false;
		}
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
