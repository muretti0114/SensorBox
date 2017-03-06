package jp.kobe_u.cs27.sensorbox.library.phidget;

/**
 * PhidgetGPSのデータを保持する構造体
 * @author masa-n
 *
 */
public class GPSBean {
	private boolean status;   //測位可能状態
	private String date;      //日付 YYYY/MM/DD
	private String time;      //時刻 hh:mm:ss
	private double latitude;  //緯度
	private double longitude; //経度
	private double altitude;  //高度
	private double heading;   //向き 
	private double velocity;  //速度

	/**
	 * デフォルトコンストラクタ．statusがfalse,　あとはすべて0にセットされる．
	 */
	public GPSBean() {
		this(false, "0", "0", 0, 0, 0, 0, 0);
	}
	/**
	 * 引数つきコンストラクタ
	 * @param status
	 * @param date
	 * @param time
	 * @param latitude
	 * @param longitude
	 * @param altitude
	 * @param heading
	 * @param velocity
	 */
	public GPSBean(boolean status, String date, String time, double latitude,
			double longitude, double altitude, double heading, double velocity) {
		super();
		this.status = status;
		this.date = date;
		this.time = time;
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
		this.heading = heading;
		this.velocity = velocity;
	}
	/**
	 * @return the status
	 */
	public boolean getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}
	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	/**
	 * @return the altitude
	 */
	public double getAltitude() {
		return altitude;
	}
	/**
	 * @param altitude the altitude to set
	 */
	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}
	/**
	 * @return the heading
	 */
	public double getHeading() {
		return heading;
	}
	/**
	 * @param heading the heading to set
	 */
	public void setHeading(double heading) {
		this.heading = heading;
	}
	/**
	 * @return the velocity
	 */
	public double getVelocity() {
		return velocity;
	}
	/**
	 * @param velocity the velocity to set
	 */
	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GPSBean [status=" + status + ", date=" + date + ", time="
				+ time + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", altitude=" + altitude + ", heading=" + heading
				+ ", velocity=" + velocity + "]";
	}
}
