package jp.kobe_u.cs27.sensorbox.sensors;

import jp.kobe_u.cs27.sensorbox.framework.Sensor;
import jp.kobe_u.cs27.sensorbox.framework.Specification;
import jp.kobe_u.cs27.sensorbox.library.phidget.OpenIFKit;
import jp.kobe_u.cs27.sensorbox.library.phidget.OpenIFKitManager;
import jp.kobe_u.cs27.sensorbox.type.GasPressure;

import com.phidgets.PhidgetException;

public class Phidgets1115_GasPressure extends Sensor {
	private OpenIFKit oif ;
	private int PORT_NUM;

	public Phidgets1115_GasPressure(){

		Specification ea = new Specification();
		ea.setSensorName("Phidgets 1115_0 - Pressure Sensor");
		ea.setPropertyType("double");
		ea.setDescription(" The 1115 measures absolute gas pressure from "
				+ "20 to 250 kPa (2.9 to 36.3 psi) with a maximum error of +-1.5%. "
				+ "It is suitable for measuring vacuum, or atmospheric pressure;"
				+ " it can also be used as a crude barometer. ");
		ea.setProperty("GasPressure");
		ea.setMax("250.0");
		ea.setMin("20.0");
		this.spec = ea;
		this.type = new GasPressure();
		setSpecification(spec);
	}

	// 参照
	// http://www.phidgets.com/documentation/Phidgets/1115_0_Product_Manual.pdf
	@Override
	protected Object convertValue(Object value) {
		double sensorValue = Integer.parseInt(value.toString());
		double gaspressure = (1.0 * sensorValue / 4) + 10;
		return gaspressure;
	}

	@Override
	public Object getValue() {
		// TODO 自動生成されたメソッド・スタブ
		return convertValue(readValue());
	}
	public Object getValueByProperty(String property) {
		return getValue();
	}
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
