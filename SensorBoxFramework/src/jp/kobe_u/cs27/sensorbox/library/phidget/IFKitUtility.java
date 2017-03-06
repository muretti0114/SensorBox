package jp.kobe_u.cs27.sensorbox.library.phidget;

import com.phidgets.InterfaceKitPhidget;
import com.phidgets.PhidgetException;
import com.phidgets.event.AttachEvent;
import com.phidgets.event.AttachListener;
import com.phidgets.event.DetachEvent;
import com.phidgets.event.DetachListener;
import com.phidgets.event.ErrorEvent;
import com.phidgets.event.ErrorListener;

/**
 * Phidget Interface Kitのユーティリティ
 * @author masa-n
 *
 */
public class IFKitUtility {

	public static int getSerialNumber () {
		int serial = 0;
		InterfaceKitPhidget ik;

		try {

			ik = new InterfaceKitPhidget();

			//各種リスナの登録
			ik.addAttachListener(new AttachListener() {//接続
				public void attached(AttachEvent ae) {
					System.out.println("attachment of " + ae);
				}
			});
			ik.addDetachListener(new DetachListener() {//切断
				public void detached(DetachEvent ae) {
					System.out.println("detachment of " + ae);
				}
			});
			ik.addErrorListener(new ErrorListener() {//エラー
				public void error(ErrorEvent ee) {
					System.out.println("error event for " + ee);
				}
			});
			/*入(出)力リスナ 
			ik.addInputChangeListener(new InputChangeListener() {
				public void inputChanged(InputChangeEvent oe) {
					System.out.println(oe);
				}
			});
			ik.addOutputChangeListener(new OutputChangeListener() {
				public void outputChanged(OutputChangeEvent oe) {
					System.out.println(oe);
				}
			});
			ik.addSensorChangeListener(new SensorChangeListener() {
				public void sensorChanged(SensorChangeEvent se) {
					//System.out.println("SensorChangeListener:" + se);
				}
			});
			*/
			ik.openAny();
			System.out.print("Waiting for InterfaceKit attachment...");
			ik.waitForAttachment();
			System.out.println(ik.getDeviceName() + ", serial = " + ik.getSerialNumber());
			ik.setSensorChangeTrigger(0,20);
			serial = ik.getSerialNumber();
			ik.close();
		} catch (PhidgetException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return serial;
	}


}
