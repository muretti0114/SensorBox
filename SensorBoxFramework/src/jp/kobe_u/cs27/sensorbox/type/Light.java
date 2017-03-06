package jp.kobe_u.cs27.sensorbox.type;



public class Light extends SensorType{
	public Light() {
		this.property = "Brightness";
		this.unit = "lux";
		this.type = "double";
	}
}
