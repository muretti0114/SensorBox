package jp.kobe_u.cs27.sensorbox.sensors;

import jp.kobe_u.cs27.sensorbox.framework.Sensor;
import jp.kobe_u.cs27.sensorbox.framework.Specification;
import jp.kobe_u.cs27.sensorbox.library.phidget.OpenIFKit;
import jp.kobe_u.cs27.sensorbox.library.phidget.OpenIFKitManager;
import jp.kobe_u.cs27.sensorbox.type.Humidity;


public class Phidgets1125_Humidity extends Sensor {
	private OpenIFKit oif;
	private int PORT_NUM;

	public Phidgets1125_Humidity(){
		this.id = "HumiditySensor";

		Specification spec = new Specification();
		spec.setSensorName("Phidgets 1125_0 - Humidity Sensor");
		spec.setPropertyType("double");
		spec.setDescription("Measures Relative Humidity from 10% to 95% with a typical error of +-2%RH at 55% RH. ");
		spec.setProperty("Humidity");
		spec.setMin("0.0");
		spec.setMax("100.0");
		this.spec= spec;
		this.type = new Humidity();
		setSpecification(spec);
	}


	// 参照
	// http://www.phidgets.com/documentation/Phidgets/1125_0_Product_Manual.pdf
	@Override
	protected Object convertValue(Object value) {
		double sensorValue = Integer.parseInt(value.toString());
		double temperature = ((sensorValue*0.1906)-40.2);
		return temperature;
	}

	@Override
	public Object getValue() {
		return convertValue(readValue());
	}
	public Object getValueByProperty(String property) {
		return getValue();
	}
	@Override
	protected Object readValue() {
		try {
			return oif.getValue(PORT_NUM);
		} catch (Exception e) {
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
