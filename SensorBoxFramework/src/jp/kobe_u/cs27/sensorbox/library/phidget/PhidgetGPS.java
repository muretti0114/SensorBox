package jp.kobe_u.cs27.sensorbox.library.phidget;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import com.phidgets.GPSPhidget;
import com.phidgets.PhidgetException;
import com.phidgets.event.ErrorEvent;
import com.phidgets.event.ErrorListener;
import com.phidgets.event.GPSPositionChangeEvent;
import com.phidgets.event.GPSPositionChangeListener;
import com.phidgets.event.GPSPositionFixStatusChangeEvent;
import com.phidgets.event.GPSPositionFixStatusChangeListener;

/**
 * Phidget 提供のGPSPhidgetのラッパークラス．
 * @author masa-n
 *
 */
public class PhidgetGPS {
//	private static GPS instance = new GPS();
	private GPSBean bean = new GPSBean();
	private GPSPhidget gps;
	private boolean status = false;

	/**
	 * コンストラクタ．
	 * GPSPhidgetクラスを生成し，リスナをいろいろ登録．重要なのは下記の2つ．
	 *  測位可能状態変化リスナー : 測位可能になればすぐにstatusを変更する．
	 *  位置変化リスナー： 位置が変わったら，測位可能であれば beanを更新する．
	 */
	public PhidgetGPS(int serial) {
		try {
			gps = new GPSPhidget();
			gps.open(serial);
			System.out.print("Waiting for the Phidget GPS to be attached...");
			gps.waitForAttachment();

			
			System.out.println("attached. Serial number = #" + gps.getSerialNumber());
			//現在のステータスを取得
			status = gps.getPositionFixStatus();
			bean.setStatus(status);

			//エラーリスナーの設定
			gps.addErrorListener(new ErrorListener() {
				public void error(ErrorEvent ex) {
					System.out.println("\n--->Error: " + ex.getException());
				}
			});

			//測位可能状態変化リスナーの設定
			gps.addGPSPositionFixStatusChangeListener(new GPSPositionFixStatusChangeListener() {
				public void gpsPositionFixStatusChanged(GPSPositionFixStatusChangeEvent g) {
					// 測位可能状態にが変化したら，状態をセットしなおす．
					status = g.getStatus();
					bean.setStatus(status);
					System.out.println(g.toString());
				}
			});

			//位置変化リスナーの設定
			gps.addGPSPositionChangeListener(new GPSPositionChangeListener() {
				public void gpsPositionChanged(GPSPositionChangeEvent gpspce) {
					try {
						// 測位可能な場合のみ，値を更新する．
						if (status == true) {
							bean.setLatitude(gps.getLatitude());
							bean.setLongitude(gps.getLongitude());
							bean.setAltitude(gps.getAltitude());
							bean.setHeading(gps.getHeading());
							bean.setVelocity(gps.getVelocity());
						}
					} catch (PhidgetException ex) {
						System.out.println("\n--->Error: " + ex.getDescription());
					}

				}
			});
		} catch (PhidgetException ex) {
			System.out.println("Phidget Exception:" + ex.getDescription());
		}
	}

	/**
	 * GPSの時刻を更新する．
	 */
	public void updateTime() {
		Calendar cal;
		try {
			//GPSからのデータは，タイムゾーンUTCであるから，セットしておく．
			cal = gps.getDateAndTime();
			TimeZone tz = TimeZone.getTimeZone("UTC");
			cal.setTimeZone(tz);
		} catch (PhidgetException ex) {
			// TODO Auto-generated catch block
			System.out.println("Phidget Exception: Cannot get date/time. " + ex.getDescription());
			return;
		}

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss.SSS z");
        String date = dateFormat.format(cal.getTime());
        String time = timeFormat.format(cal.getTime());
        bean.setDate(date);
        bean.setTime(time);
	}


	/**
	 * 現在のGPSデータを返す．一応，時刻を更新しておく．
	 * @return
	 */
	public GPSBean getData() {
		updateTime();
		return bean;
	}
}
