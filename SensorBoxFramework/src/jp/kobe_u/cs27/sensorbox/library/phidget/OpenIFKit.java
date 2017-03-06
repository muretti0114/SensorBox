package jp.kobe_u.cs27.sensorbox.library.phidget;

import com.phidgets.InterfaceKitPhidget;
import com.phidgets.PhidgetException;
import com.phidgets.event.AttachEvent;
import com.phidgets.event.AttachListener;
import com.phidgets.event.DetachEvent;
import com.phidgets.event.DetachListener;
import com.phidgets.event.ErrorEvent;
import com.phidgets.event.ErrorListener;
import com.phidgets.event.SensorChangeEvent;
import com.phidgets.event.SensorChangeListener;

/***
 * Phidgetデバイスとのコネクション処理を行うクラス
 *
 * @author shinsuke-m
 *
 */
public class OpenIFKit {

	private InterfaceKitPhidget ik;
	private int serial;

	public OpenIFKit(int serial) {
		this.serial = serial;

		//System.out.println(Phidget.getLibraryVersion());
		try {
			ik = new InterfaceKitPhidget();
		} catch (PhidgetException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		ik.addAttachListener(new AttachListener() {
			public void attached(AttachEvent ae) {
				System.out.println("attachment of " + ae);
			}
		});
		ik.addDetachListener(new DetachListener() {
			public void detached(DetachEvent ae) {
				System.out.println("detachment of " + ae);
			}
		});
		ik.addErrorListener(new ErrorListener() {
			public void error(ErrorEvent ee) {
				System.out.println("error event for " + ee);
			}
		});
		/*おそらく入(出)力値が変化するたびに呼び出されてその内容を表示させるリスナ
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
		*/
		ik.addSensorChangeListener(new SensorChangeListener() {
			public void sensorChanged(SensorChangeEvent se) {
				//System.out.println("SensorChangeListener:" + se);
			}
		});

		//ik.openAny();
		try {
			ik.open(this.serial);
			System.out.print("Waiting for InterfaceKit attachment...");
			ik.waitForAttachment();

			System.out.println(ik.getDeviceName());

			ik.setSensorChangeTrigger(0,20);
			
		} catch (PhidgetException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}


		//Thread.sleep(200);

//	System.out.println("input(7,8) = (" +
//	  ik.getInputState(7) + "," +
//	  ik.getInputState(8) + ")");

		/*
		System.out.println("Outputting events.  Input to stop.");
		System.in.read();
		System.out.print("closing...");
		ik.close();
		ik = null;
		System.out.println(" ok");
		if (false) {
			System.out.println("wait for finalization...");
			System.gc();
		}
		*/
	}

	public Object getValue(int i) throws PhidgetException {
		int v1 = ik.getSensorValue(i);
		Integer v2 = new Integer(v1);
		return v2;
	}

	/**
	 * phidgetインタフェースキットのデジタルインプットの値を調べる
	 * （コンタクトスイッチなどに使用）
	 * @param portNum
	 * @return
	 * @throws PhidgetException
	 */
	public Object getDegitalInputValue(int portNum) throws PhidgetException{
		boolean diValue = ik.getInputState(portNum);
		return new Boolean(diValue);
	}
	
	public InterfaceKitPhidget getInterfaceKit() {
		return ik;
	}	

}