package jp.kobe_u.cs27.sensorbox.sensors;

import jp.kobe_u.cs27.sensorbox.framework.Sensor;
import jp.kobe_u.cs27.sensorbox.framework.Specification;
import jp.kobe_u.cs27.sensorbox.library.phidget.OpenIFKit;
import jp.kobe_u.cs27.sensorbox.library.phidget.OpenIFKitManager;
import jp.kobe_u.cs27.sensorbox.type.Light;

import com.phidgets.PhidgetException;

public class Phidgets1142_Light extends Sensor {
	private OpenIFKit oif;
	private int PORT_NUM;
	private double m = 1.478777; // Generalized value
	private double b = 33.67076; // Generalized value

	public Phidgets1142_Light() {

		Specification ea = new Specification();
		ea.setSensorName("Phidgets 1142_0 - Light Sensor 1000 lux");
		ea.setPropertyType("int");
		ea.setDescription("The 1142 Light sensor can measure ambient "
				+ "light up to 1000 lux (roughly equivalent to the typical "
				+ "lighting in a studio, or the sky on an overcast day). "
				+ "Each sensor has been individually calibrated, and a label "
				+ "has been applied to the back of the board with a calibration "
				+ "value which can be used in your calculations to increase "
				+ "measurement accuracy. ");
		ea.setProperty("Brightness");
		ea.setMin("0");
		ea.setMax("1000");
		this.spec = ea;
		this.type = new Light();
		setSpecification(spec);
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

	protected Object convertValue(Object value) {
		// 参考：http://www.phidgets.com/docs/1142_User_Guide
		// lux = m * value + b

		double lux =  m * Integer.parseInt(value.toString()) + b;
		long luxval = Math.round(lux);

		return luxval;
	}

	public Object getValue() {
		return convertValue(readValue());
	}

	public Object getValueByProperty(String property) {
		return getValue();
	}

	public void connect() {
		// accessmethodの中でこの名前と併せる必要あり
		int serial = Integer.parseInt(access.get("serial"));
		this.oif = OpenIFKitManager.getOpenIFKit(serial);
		this.PORT_NUM = Integer.parseInt(access.get("port"));
		if (access.containsKey("m")) {
			this.m = Double.parseDouble(access.get("m"));
			System.out.println("Value of m obtained: " + m);
		}
		if (access.containsKey("b")) {
			this.b = Double.parseDouble(access.get("b"));
			System.out.println("Value of b obtained: " + b);

		}

	}
}
