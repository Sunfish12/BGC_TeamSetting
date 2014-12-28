package model.GroupRoomMessage;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class GroupRoomMessageBean {
	private Integer groupRoomMessageSerialNumber;
	private Integer groupSerialNumber;
	private String messageUsername;
	private String messageContents;
	private java.util.Date messageTime;

	public String toString() {
		return "[" + groupRoomMessageSerialNumber + ":" + groupSerialNumber
				+ ":" + messageUsername + ":" + messageContents + ":"
				+ messageTime + "]";
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

	public Integer getGroupRoomMessageSerialNumber() {
		return groupRoomMessageSerialNumber;
	}

	public void setGroupRoomMessageSerialNumber(
			Integer groupRoomMessageSerialNumber) {
		this.groupRoomMessageSerialNumber = groupRoomMessageSerialNumber;
	}

	public Integer getGroupSerialNumber() {
		return groupSerialNumber;
	}

	public void setGroupSerialNumber(Integer groupSerialNumber) {
		this.groupSerialNumber = groupSerialNumber;
	}

	public String getMessageUsername() {
		return messageUsername;
	}

	public void setMessageUsername(String messageUsername) {
		this.messageUsername = messageUsername;
	}

	public String getMessageContents() {
		return messageContents;
	}

	public void setMessageContents(String messageContents) {
		this.messageContents = messageContents;
	}

	public java.util.Date getMessageTime() {
		return messageTime;
	}

	public void setMessageTime(java.util.Date messageTime) {
		this.messageTime = messageTime;
	}

}
