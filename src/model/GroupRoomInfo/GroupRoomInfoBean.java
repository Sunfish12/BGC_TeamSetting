package model.GroupRoomInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class GroupRoomInfoBean {
	private Integer groupSerialNumber;
	private byte[] groupPicture;
	private String imgFileName;
	private Integer groupPictureSerialNumber;

	public String toString() {
		return "[" + groupSerialNumber + ":" + imgFileName + ":"
				+ groupPictureSerialNumber + "]";
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

	public byte[] getGroupPicture() {
		return groupPicture;
	}

	public void setGroupPicture(byte[] groupPicture) {
		this.groupPicture = groupPicture;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	public Integer getGroupPictureSerialNumber() {
		return groupPictureSerialNumber;
	}

	public void setGroupPictureSerialNumber(Integer groupPictureSerialNumber) {
		this.groupPictureSerialNumber = groupPictureSerialNumber;
	}

}
