package jp.kobe_u.cs27.sensorbox.sensors;

import jp.kobe_u.cs27.sensorbox.framework.Sensor;
import jp.kobe_u.cs27.sensorbox.framework.Specification;
import jp.kobe_u.cs27.sensorbox.library.phidget.OpenIFKit;
import jp.kobe_u.cs27.sensorbox.library.phidget.OpenIFKitManager;
import jp.kobe_u.cs27.sensorbox.type.Light;

import com.phidgets.PhidgetException;

public class Phidgets1127_Light extends Sensor {
	private OpenIFKit oif;
    private int PORT_NUM;

	public Phidgets1127_Light() {

		Specification ea = new Specification();
		ea.setSensorName("Phidgets 1127_0 - Precision Light Sensor");
		ea.setPropertyType("int");
		ea.setDescription("The Precision Light Sensor measures human "
				+ "perceptible light level in lux; its measurement"
				+ " range is from 1 lux (Moonlight) to 1000 lux "
				+ "(TV studio lighting) ");
		ea.setProperty("Brightness");
		ea.setMin("0");
		ea.setMax("990");
		this.spec=ea;
		this.type = new Light();
		setSpecification(spec);
	}

	protected Object readValue()  {
		try {
			return oif.getValue(PORT_NUM);
		} catch (PhidgetException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return null;
		}
	}

	protected Object convertValue(Object value){
		//LightSensorは測定値そのものがluxになるらしい。
		return value;
	}

	public Object getValue()  {
		return convertValue(readValue());
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
