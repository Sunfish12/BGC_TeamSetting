package model.Joiner_Info;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Joiner_InfoBean {
	private Integer joiner_InfoSerialNumber;
	private Integer groupSerialNumber;
	private java.util.Date joinTime;
	private String username;

	public String toString() {
		return "[" + joiner_InfoSerialNumber + ":" + groupSerialNumber + ":"
				+ joinTime + ":" + username + "]";
	}

	private static SimpleDateFormat sdFormat = new SimpleDateFormat(
			"yyyy-MM-dd");

	public static java.util.Date convertDate(String data) {
		java.util.Date result = null;
		try {
			result = sdFormat.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
			result = new java.util.Date(0);
		}
		return result;
	}

	public static double convertDouble(String data) {
		double result = 0;
		try {
			result = Double.parseDouble(data);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			result = -1000;
		}
		return result;
	}

	public static int convertInt(String data) {
		int result = 0;
		try {
			result = Integer.parseInt(data);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			result = -1000;
		}
		return result;
	}

	public Integer getGroupSerialNumber() {
		return groupSerialNumber;
	}

	public void setGroupSerialNumber(Integer groupSerialNumber) {
		this.groupSerialNumber = groupSerialNumber;
	}

	public Integer getJoiner_InfoSerialNumber() {
		return joiner_InfoSerialNumber;
	}

	public void setJoiner_InfoSerialNumber(Integer joiner_InfoSerialNumber) {
		this.joiner_InfoSerialNumber = joiner_InfoSerialNumber;
	}

	public java.util.Date getJoinTime() {
		return joinTime;
	}

	public void setJoinTime(java.util.Date joinTime) {
		this.joinTime = joinTime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
