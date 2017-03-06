package jp.kobe_u.cs27.sensorbox.sensors;

import jp.kobe_u.cs27.sensorbox.framework.Sensor;
import jp.kobe_u.cs27.sensorbox.framework.Specification;
import jp.kobe_u.cs27.sensorbox.library.phidget.OpenIFKit;
import jp.kobe_u.cs27.sensorbox.library.phidget.OpenIFKitManager;
import jp.kobe_u.cs27.sensorbox.type.Temperature;

import com.phidgets.PhidgetException;

public class Phidgets1125_Temperature extends Sensor {
	private OpenIFKit oif;
	private int PORT_NUM;

	public Phidgets1125_Temperature(){
		this.id = "TemperatureSensor";

		Specification spec = new Specification();
		spec.setSensorName("Phidgets 1125_0 - Temperature Sensor");
		spec.setPropertyType("double");
		spec.setDescription("Measures Ambient Temperature in the range of"
				+ " -30C to +80C with a typical error of +-0.75C in the"
				+ " 0C to 80C range.");
		spec.setProperty("Temperature");
		spec.setMax("80.0");
		spec.setMin("-30.0");
		this.spec = spec;
		this.type = new Temperature();
		setSpecification(spec);
	}

	@Override
	protected Object convertValue(Object value) {
		double sensorValue = Integer.parseInt(value.toString());
		double temperature = ((sensorValue*0.22222)-61.11);
		return temperature;
	}

	@Override
	public Object getValue() {
		// TODO 自動生成されたメソッド・スタブ
		return convertValue(readValue());
	}
	public Object getValueByProperty(String property) {
		return getValue();
	}
	@Override
	protected Object readValue() {
		try {
			return oif.getValue(PORT_NUM);
		} catch (PhidgetException e) {
			// TODO 自動生成された catch ブロック
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
