package jp.kobe_u.cs27.sensorbox.type;

public abstract class SensorType{
	protected String property;
	protected String unit;
	protected String type;
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}