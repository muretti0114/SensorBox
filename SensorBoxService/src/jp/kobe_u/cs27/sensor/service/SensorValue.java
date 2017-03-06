package jp.kobe_u.cs27.sensor.service;

public class SensorValue {
	private String id;
	private String value;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "SensorValue [id=" + id + ", value=" + value + "]";
	}
	public SensorValue(String id, String value) {
		this.id = id;
		this.value = value;
	}

}
