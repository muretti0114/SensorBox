package jp.kobe_u.cs27.sensor.service;

import java.util.ArrayList;
import java.util.HashMap;

import jp.kobe_u.cs27.sensorbox.framework.Sensor;
import jp.kobe_u.cs27.sensorbox.framework.SensorBox;
import jp.kobe_u.cs27.sensorbox.framework.SensorBoxFactory;

public class SensorBoxService {
	private SensorBox box = null;
	private HashMap<String,SensorInfo> sensorMap = new HashMap<String,SensorInfo>();
	private ArrayList<SensorInfo> infoList = new ArrayList<SensorInfo>();


	public SensorBoxService () {
		SensorBoxFactory f = new SensorBoxFactory("META-INF/nakamura-box02-g.xml");
		box = f.create();

		for (String id: box.getSensorIds()) {
			Sensor s = box.getSensor(id);
			SensorInfo info = new SensorInfo();
			info.setSensorId(id);
			//タイプ情報のセット
			info.setSensorType(s.getSensorType().getProperty());
			info.setUnit(s.getSensorType().getUnit());

			//スペック情報のセット
			info.setProperty(s.getSpecification().getProperty());
			info.setPropertyType(s.getSpecification().getPropertyType());
			info.setProductName(s.getSpecification().getSensorName());
			info.setMax(s.getSpecification().getMax());
			info.setMin(s.getSpecification().getMin());
			info.setDescription(s.getSpecification().getDescription());

			//ボックス情報のセット
			info.setBoxId(box.getId());
			info.setOwner(box.getOwner());
			info.setEndpoint(box.getEndpoint());
			info.setLocation(box.getLocation());

			sensorMap.put(id,info);
			infoList.add(info);
		}
	}

	public SensorInfo getInfo(String id) {
		if (sensorMap.containsKey(id)) {
			return sensorMap.get(id);
		} else {
			return null;
		}
	}

	public String getValue(String id) {
		if (sensorMap.containsKey(id)) {
			return box.getValue(id).toString();
		} else {
			return "No such sensor exists "+ id;
		}
	}

	public String[] getAllIds () {
		return box.getSensorIds();
	}

	public SensorInfo [] getAllInfos() {
		return infoList.toArray(new SensorInfo[0]);
	}

	public SensorValue[] getAllValues() {
		ArrayList<SensorValue> kvList = new ArrayList<SensorValue>();
		for (String id: box.getSensorIds()) {
			kvList.add(new SensorValue(id, box.getValue(id).toString()));
		}
		return kvList.toArray(new SensorValue[0]);
	}

}
