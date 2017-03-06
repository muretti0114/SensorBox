package jp.kobe_u.cs27.sensorbox.sensors;


import jp.kobe_u.cs27.sensorbox.framework.Sensor;
import jp.kobe_u.cs27.sensorbox.framework.Specification;
import jp.kobe_u.cs27.sensorbox.library.phidget.OpenIFKit;
import jp.kobe_u.cs27.sensorbox.library.phidget.OpenIFKitManager;
import jp.kobe_u.cs27.sensorbox.type.Sound;

public class Phidgets1133_Sound extends Sensor {
	private OpenIFKit oif;
	private int PORT_NUM;

	public Phidgets1133_Sound(){

		Specification spec = new Specification();
		spec.setSensorName("Phidgets 1133_0 - Sound Sensor");
		spec.setPropertyType("double");
		spec.setDescription("The 1133 measures sound across a very wide range, "
				+ "with a frequency range of 100Hz to 8kHz and pressure"
				+ " level from 50dB to 100dB. ");
		spec.setProperty("Sound");
		spec.setMin("50.0");
		spec.setMax("100.0");
		this.spec= spec;
		this.type = new Sound();
		setSpecification(spec);
	}

	// 参照
	// http://www.phidgets.com/documentation/Phidgets/1133_0_Product_Manual.pdf
	@Override
	protected Object convertValue(Object value) {
		double sensorValue = Integer.parseInt(value.toString());
		double soundPressure = (16.801 * Math.log(sensorValue) + 9.872);
		
		// 50 - 100 dbの間しか計測できないので丸める
		if (soundPressure > 100) soundPressure = 100;
		else if (soundPressure < 50) soundPressure = 0;
		
		return soundPressure;
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
