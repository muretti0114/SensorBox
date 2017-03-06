package jp.kobe_u.cs27.sensorbox.framework;

import java.io.IOException;
//import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


/**
 * センサボックスのためのファクトリ．センサ定義ファイルに基づいて，センサオブジェクトを
 * 生成し，センサボックスに登録する．
 *
 * @author okushi, masa-n
 *
 */
public class SensorBoxFactory {
	//センサボックスの定義
	private Document doc = null;

	/**
	 * 定義ファイル名のパスを与えて，ファクトリを作成する
	 * @param configFile 定義ファイル名のパス
	 */
	public SensorBoxFactory(String configFile) {
		doc = readConfigFile(configFile);
	}
	
	/**
	 * 定義ファイルのURLを与えて，ファクトリを作成する
	 * @param url 定義ファイル名のURL
	 */
	public SensorBoxFactory(URL url) {
		doc = readConfigFile(url);
	}

	/**
	 * 指定されたファイル名で定義ファイルを開く
	 * @param configFile 定義ファイル名のパス
	 * @return 定義ファイルのドキュメントオブジェクト
	 */
	public Document readConfigFile(String configFile){
		System.out.println("Creating a SensorBox from " + configFile);
		//クラスローダで定義ファイルを相対位置で指定（定義ファイルはクラスパスの通った場所に置くこと！）
		URL url = this.getClass().getClassLoader().getResource(configFile);
		if (url==null) {
            throw new IllegalArgumentException(
                    "センサ定義ファイルの読み込みに失敗しました：" + configFile);
		}
		
		return readConfigFile(url);
	}
	
	/**
	 * 指定されたURLで定義ファイルを開く
	 * @param url 定義ファイル名のURL
	 * @return 定義ファイルのドキュメントオブジェクト
	 */
	public Document readConfigFile(URL url){
		Document doc = null;
		try {
			//Jsoupで設定ファイルを開く
			doc = Jsoup.parse(url.openStream(),null,url.toString());
		} catch (IOException e) {
			System.err.println("IO Exception occurs in parsing " + url.toString());
			e.printStackTrace();
		}

		return doc;
		
	}
	/**
	 * センサボックスを作成して返す
	 *
	 * @return センサボックスオブジェクト
	 */
	public SensorBox create(){
		SensorBox box = new SensorBox(); //まずは空箱を作成
		
		
		String boxid = doc.select("boxId").first().text();
		String owner = doc.select("owner").first().text();
		String location = doc.select("location").first().text();
		String endpoint = doc.select("endpoint").first().text();

		box.setId(boxid);
		box.setOwner(owner);
		box.setLocation(location);
		box.setEndpoint(endpoint);

		//センサ登録用の変数など
		SensorFactory f = new SensorFactory();

		//定義ファイルの各センサ毎に，
		for(Element sensor : doc.select("sensor")){

			//センサIDとデバイスを取得
			String sensorid = sensor.select("sensorid").text().toString();
			String device = sensor.select("device").text().toString();

			//アクセスメソッドを取得
			Element accessMethod = sensor.select("accessMethod").first();
			//デバイスアクセスのためのパラメータ（プロパティ名，値）のマップ
			HashMap<String,String> access = new HashMap<String,String>();

			//それぞれのタグから，タグ名→プロパティ名（キー），データ→プロパティ値（バリュー）
			for (Element e: accessMethod.children()) {
				String key  = e.tagName();
				String value = e.text().toString();
				access.put(key, value); //マップに突っ込む
			}

			//ファクトリでセンサを生成し，センサ一覧に登録する
			box.addSensor(sensorid, f.create(sensorid , device, access));
		}
		
		
		//登録された全センサの緒元を画面に出力する（確認用）．
		String [] sensorIds = box.getSensorIds();
		System.out.println("------------------------------------------------");
		System.out.println("* SUCCESS: A sensor box is created as shown below.");
		System.out.println("  - boxID: " + box.getId());
		System.out.println("  - owner: " + box.getOwner());
		System.out.println("  - location: " + box.getLocation());
		System.out.println("  - endpoint: " + box.getEndpoint());
		System.out.println("* Installed sensors: ");
		
		System.out.println();
		
		for (String id: sensorIds) {
			Sensor s = box.getSensor(id);
			System.out.println("  # Sensor ID: " + s.getSensorId());
			System.out.println("    - Name: " + s.getSpecification().getSensorName());
			System.out.println("    - Driver: " + s.getClass().getName());
			System.out.println("    - Type: " + s.getSensorType().getProperty());
			System.out.println("    - Unit: " + s.getSensorType().getUnit());
			System.out.println("    - Description: "+ s.getSpecification().getDescription());
		}
		System.out.println("------------------------------------------------");


		return box;
	}


}
