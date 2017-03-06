package jp.kobe_u.cs27.sensorbox.library.phidget;

import java.util.Date;

import com.phidgets.PhidgetException;
import com.phidgets.TextLCDPhidget;
import com.phidgets.event.AttachEvent;
import com.phidgets.event.AttachListener;
import com.phidgets.event.DetachEvent;
import com.phidgets.event.DetachListener;
import com.phidgets.event.ErrorEvent;
import com.phidgets.event.ErrorListener;

/***
 * Phidget提供のTextLCDPhidgetクラスのラッパークラス
 * メソッドを簡略化
 *
 * @author shinsuke-m
 *
 */
public class TextLCD {

	private TextLCDPhidget lcd;
	private int serial;

	// 自動オフのための並列クラス
	private Runnable lcdAutoController;

	// 自動オフのマージン
	private long autoOffInterval;

	// 最後のシグナル送信後，少し待つ必要がある
	private final long SIGNAL_CLOSE_INTERVAL = 1*1000;

	// 割り込み発生した際の自動オフを回避するための記録領域
	private long prev;

	public TextLCD(int serial) {
		this.serial = serial;
		autoOffInterval = 60 * 1000;
		try {
			lcd = createLCDPhidget();

			// Phidget固有の情報
			lcd.open(this.serial);

			System.out.println("[lcd] waiting for TextLCD attachment...");
			lcd.waitForAttachment();
		} catch (PhidgetException e) {
			System.err.println("[phidget] Phidget instance initialization error");
		}

		// 自動オフ機能を無名クラスで定義
		// (tomcat上ではparallelにならない模様)
		lcdAutoController = new Runnable() {
			public void run() {
				try {
					lcd.setBacklight(true);
					Thread.sleep(autoOffInterval);

					lcd.setBacklight(false);
					Thread.sleep(SIGNAL_CLOSE_INTERVAL);

				} catch (PhidgetException e) {
					System.err.println("[phidget] Phidget operation error");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
	}
	private TextLCDPhidget createLCDPhidget () {

		TextLCDPhidget lcd = null;
		try {
			lcd = new TextLCDPhidget();

			lcd.addAttachListener(new AttachListener() {
				public void attached(AttachEvent ae) {
					System.out.println("[lcd] attached lcd " + ae);
				}
			});
			lcd.addDetachListener(new DetachListener() {
				public void detached(DetachEvent ae) {
					System.out.println("[lcd] detached lcd " + ae);
				}
			});
			lcd.addErrorListener(new ErrorListener() {
				public void error(ErrorEvent ee) {
					System.out.println("[lcd] error occured " + ee);
				}
			});
		} catch (PhidgetException e) {
			System.err.println("[phidget] Phidget instance initialization error");
		}

		return lcd;
	}

	// text設定
	public boolean setText(String text) {
		if (text == null || text.length() == 0) {
			return false;
		}
		try {
			lcd.setBacklight(true);
			// 実行ID（日付より生成）をメモ
			long curr = (new Date()).getTime();
			this.prev = curr;

			// LCD書き込み
			lcd.setDisplayString(0, text);

			// tom上では並列処理は無理なので
	//		lcdAutoController.run();
/*
			// 直列で輝度調整
			lcd.setBacklight(true);
			Thread.sleep(autoOffInterval);

			// 実行IDに変化がない＝割り込みがない場合
			if (this.prev == curr) {
				// 画面を消す
				lcd.setBacklight(false);
				Thread.sleep(SIGNAL_CLOSE_INTERVAL);
			}
*/
		} catch (PhidgetException e) {
			System.err.println("[phidget] Phidget operation error");
			return false;
		}
		return true;
	}

	// text設定
	public boolean setDoubleText(String text0, String text1) {
		if (text0 == null || text0.length() == 0 ||
			text1 == null || text1.length() == 0) {
			return false;
		}

		try {
			// 実行ID（日付より生成）をメモ
			long curr = (new Date()).getTime();
			this.prev = curr;

			lcd.setBacklight(true);
			// LCD書き込み
			lcd.setDisplayString(0, text0);
			lcd.setDisplayString(1, text1);
		} catch (PhidgetException e) {
			System.err.println("[phidget] Phidget operation error");
			return false;
		}
		return true;
	}


	public long setAutoOffInterval(long interval) {
		this.autoOffInterval = interval;
		return interval;
	}
	public long getAutoOffInterval() {
		return this.autoOffInterval;
	}

	public boolean setBackLight(boolean light) {
		try {
			lcd.setBacklight(light);
		} catch (PhidgetException e) {
			// TODO Auto-generated catch block
			System.err.println("[phidget] Phidget operation error");
			return false;
		}
		return true;
	}
	
	/*
	public static void main (String args[]) {
		TextLCD lcd = new TextLCD();
		lcd.setText("2");
	}
	 */
}
