package jp.kobe_u.cs27.sensor.service;

public class SensorInfo {
	// センサID
	private String sensorId;
	// センサタイプ情報
	private String sensorType;
	private String unit;
	// センサ仕様情報
	private String productName;
	private String property;
	private String propertyType;
	private String description;
	private String max;
	private String min;
	// センサ箱の情報
	private String boxId;
	private String owner;
	private String location;
	private String endpoint;


	public String getSensorId() {
		return sensorId;
	}
	public void setSensorId(String id) {
		this.sensorId = id;
	}
	public String getSensorType() {
		return sensorType;
	}
	public void setSensorType(String sensorType) {
		this.sensorType = sensorType;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getPropertyType() {
		return propertyType;
	}
	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMax() {
		return max;
	}
	public void setMax(String max) {
		this.max = max;
	}
	public String getMin() {
		return min;
	}
	public void setMin(String min) {
		this.min = min;
	}
	public String getBoxId() {
		return boxId;
	}
	public void setBoxId(String boxId) {
		this.boxId = boxId;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getEndpoint() {
		return endpoint;
	}
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	@Override
	public String toString() {
		return "SensorInfo [sensorId=" + sensorId + ", sensorType="
				+ sensorType + ", unit=" + unit + ", productName="
				+ productName + ", property=" + property + ", propertyType="
				+ propertyType + ", description=" + description + ", max="
				+ max + ", min=" + min + ", boxId=" + boxId + ", owner="
				+ owner + ", location=" + location + ", endpoint=" + endpoint
				+ "]";
	}



}
