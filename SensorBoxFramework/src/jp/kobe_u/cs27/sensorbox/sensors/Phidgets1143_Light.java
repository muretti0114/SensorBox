package jp.kobe_u.cs27.sensorbox.sensors;

import jp.kobe_u.cs27.sensorbox.framework.Sensor;
import jp.kobe_u.cs27.sensorbox.framework.Specification;
import jp.kobe_u.cs27.sensorbox.library.phidget.OpenIFKit;
import jp.kobe_u.cs27.sensorbox.library.phidget.OpenIFKitManager;
import jp.kobe_u.cs27.sensorbox.type.Light;

import com.phidgets.PhidgetException;

public class Phidgets1143_Light extends Sensor {
	private OpenIFKit oif;
    private int PORT_NUM;

	public Phidgets1143_Light() {

		Specification ea = new Specification();
		ea.setSensorName("Phidgets 1127_0 - Precision Light Sensor");
		ea.setPropertyType("int");
		ea.setDescription("The 1143 Light sensor can measure ambient "
				+ "light up to 70 kilolux "
				+ "(roughly equivalent to the brightness of "
				+ "direct sunlight) ");
		ea.setProperty("Brightness");
		ea.setMin("0");
		ea.setMax("70000");
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
		// 参考：http://www.phidgets.com/docs/1143_User_Guide
		// lux = exp (m*value+b)
		double m = 0.02385;
		double b = -0.56905;
		
		double lux = Math.exp(m * Integer.parseInt(value.toString()) + b);
		long luxval = Math.round(lux);

		return luxval;
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
