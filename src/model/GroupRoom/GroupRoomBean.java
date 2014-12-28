package model.GroupRoom;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class GroupRoomBean {
	private Integer groupSerialNumber;
	private String storeUsername;
	private String storeName;
	private String groupUsername;
	private java.util.Date groupStartTime;
	private java.util.Date groupEndTime;
	private String groupRoomName;
	private Integer groupSuggestNumber;
	private Integer groupLowerLimit;
	private Integer groupUpperLimit;
	private java.util.Date groupGameTime;
	private java.util.Date reserveGroupStartTime;
	private java.util.Date reserveGroupEndTime;
	private Integer roomState;
	private String imgFileName;
	private byte[] privateGroupImage;

	public String toString() {

		return "[" + groupSerialNumber + ":" + storeUsername + ":" + storeName
				+ ":" + groupUsername + ":" + groupStartTime + ":"
				+ groupEndTime + ":" + groupRoomName + ":" + groupSuggestNumber
				+ ":" + groupLowerLimit + ":" + groupUpperLimit + ":"
				+ groupGameTime + ":" + reserveGroupStartTime + ":"
				+ reserveGroupEndTime + ":" + roomState + ":" + imgFileName
				+ ":" + privateGroupImage + "]";
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

	public String getStoreUsername() {
		return storeUsername;
	}

	public void setStoreUsername(String storeUsername) {
		this.storeUsername = storeUsername;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getGroupUsername() {
		return groupUsername;
	}

	public void setGroupUsername(String groupUsername) {
		this.groupUsername = groupUsername;
	}

	public java.util.Date getGroupStartTime() {
		return groupStartTime;
	}

	public void setGroupStartTime(java.util.Date groupStartTime) {
		this.groupStartTime = groupStartTime;
	}

	public java.util.Date getGroupEndTime() {
		return groupEndTime;
	}

	public void setGroupEndTime(java.util.Date groupEndTime) {
		this.groupEndTime = groupEndTime;
	}

	public String getGroupRoomName() {
		return groupRoomName;
	}

	public void setGroupRoomName(String groupRoomName) {
		this.groupRoomName = groupRoomName;
	}

	public Integer getGroupSuggestNumber() {
		return groupSuggestNumber;
	}

	public void setGroupSuggestNumber(Integer groupSuggestNumber) {
		this.groupSuggestNumber = groupSuggestNumber;
	}

	public Integer getGroupLowerLimit() {
		return groupLowerLimit;
	}

	public void setGroupLowerLimit(Integer groupLowerLimit) {
		this.groupLowerLimit = groupLowerLimit;
	}

	public Integer getGroupUpperLimit() {
		return groupUpperLimit;
	}

	public void setGroupUpperLimit(Integer groupUpperLimit) {
		this.groupUpperLimit = groupUpperLimit;
	}

	public java.util.Date getGroupGameTime() {
		return groupGameTime;
	}

	public void setGroupGameTime(java.util.Date groupGameTime) {
		this.groupGameTime = groupGameTime;
	}

	public java.util.Date getReserveGroupStartTime() {
		return reserveGroupStartTime;
	}

	public void setReserveGroupStartTime(java.util.Date reserveGroupStartTime) {
		this.reserveGroupStartTime = reserveGroupStartTime;
	}

	public java.util.Date getReserveGroupEndTime() {
		return reserveGroupEndTime;
	}

	public void setReserveGroupEndTime(java.util.Date reserveGroupEndTime) {
		this.reserveGroupEndTime = reserveGroupEndTime;
	}

	public Integer getRoomState() {
		return roomState;
	}

	public void setRoomState(Integer roomState) {
		this.roomState = roomState;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	public byte[] getPrivateGroupImage() {
		return privateGroupImage;
	}

	public void setPrivateGroupImage(byte[] privateGroupImage) {
		this.privateGroupImage = privateGroupImage;
	}

}
