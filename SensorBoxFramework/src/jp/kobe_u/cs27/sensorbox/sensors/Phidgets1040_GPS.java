/**
 *
 */
package jp.kobe_u.cs27.sensorbox.sensors;

import jp.kobe_u.cs27.sensorbox.framework.Sensor;
import jp.kobe_u.cs27.sensorbox.framework.Specification;
import jp.kobe_u.cs27.sensorbox.library.phidget.PhidgetGPS;
import jp.kobe_u.cs27.sensorbox.library.phidget.GPSBean;
import jp.kobe_u.cs27.sensorbox.library.phidget.PhidgetGPSManager;
import jp.kobe_u.cs27.sensorbox.type.*;

/**
 * @author masa-n
 *
 */
public class Phidgets1040_GPS extends Sensor {
	private PhidgetGPS gps;


	public Phidgets1040_GPS(){
		this.type = new GPSType();

		Specification ea = new Specification();
		ea.setSensorName("Phidgets 1040_0 - PhidgetGPS");
		ea.setPropertyType("String");
		ea.setDescription("The Phidget GPS provides the longitude and "
				+ "latitude of the board's position in signed decimal "
				+ "degree format. The position accuracy (best case) is "
				+ "2.5m CEP (Circular Error of Probability)."
				+ "The 1040 also provides altitude, heading and velocity"
				+ "as well as the time and date. It will also let you know"
				+ "when it has a satellite fix.");
		ea.setProperty("GPS");
		this.spec=ea;
		setSpecification(spec);
	}


	/* (non-Javadoc)
	 * @see jp.kobe_u.cs27.framework.sensor.SensorDevice#readValue(java.lang.String)
	 */
	@Override
	protected Object readValue() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see jp.kobe_u.cs27.framework.sensor.SensorDevice#convertValue(java.lang.Object)
	 */
	@Override
	protected Object convertValue(Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getValue(){
		String gpsreturn="{" +
				"\"date\":\"" + getValueByProperty("date") + "\","+
				"\"time\":\""  + getValueByProperty("time") + "\"," +
				"\"latitude\":"+getValueByProperty("latitude") + "," +
				"\"longitude\":"+getValueByProperty("longitude") + ","+
				"\"altitude\":"+ getValueByProperty("altitude") +
				"}";

		return gpsreturn;
		//return gps.getData();
	}

	/* (non-Javadoc)
	 * @see jp.kobe_u.cs27.framework.sensor.SensorDevice#getValue(java.lang.String)
	 */
	public Object getValueByProperty(String property) {
		String prop = property.toLowerCase();
		GPSBean data = gps.getData();

		if (prop.equals("status")) {
			return data.getStatus();
		} else if (prop.equals("date")) {
			return data.getDate();
		} else if (prop.equals("time")) {
			return data.getTime();
		} else 	if (prop.equals("latitude")) {
			return data.getLatitude();
		} else if (prop.equals("longitude")) {
			return data.getLongitude();
		} else if (prop.equals("altitude")) {
			return data.getAltitude();
		} else if (prop.equals("heading")) {
			return data.getHeading();
		} else if (prop.equals("velocity")) {
			return data.getVelocity();
		} else if (prop.equals("GPS")) {
			return data;
		} else  {
			return data.toString();
		}
	}

	public void connect(){
		//accessmethodの中でこの名前と併せる必要あり
		int serial = Integer.parseInt(access.get("serial"));
		this.gps = PhidgetGPSManager.getPhidgetGPS(serial);

	}

}
