package model.RentalTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class RentalTimeBean {
	private Integer storeId; // int,
	private java.util.Date monStart; // datetime,
	private java.util.Date monEnd; // datetime,
	private java.util.Date tueStart; // datetime,
	private java.util.Date tueEnd; // datetime,
	private java.util.Date wedStart; // datetime,
	private java.util.Date wedEnd; // datetime,
	private java.util.Date thuStart; // datetime,
	private java.util.Date thuEnd; // datetime,
	private java.util.Date friStart; // datetime,
	private java.util.Date friEnd; // datetime,
	private java.util.Date satStart; // datetime,
	private java.util.Date satEnd; // datetime,
	private java.util.Date sunStart; // datetime,
	private java.util.Date sunEnd; // datetime,

	@Override
	public String toString() {
		String temp = null;
		if (this.monStart != null) {
			temp = sdf.format(this.monStart);
		}
		if (this.monEnd != null) {
			temp = sdf.format(this.monEnd);
		}
		if (this.tueStart != null) {
			temp = sdf.format(this.tueStart);
		}
		if (this.tueEnd != null) {
			temp = sdf.format(this.tueEnd);
		}
		if (this.wedStart != null) {
			temp = sdf.format(this.wedStart);
		}
		if (this.wedEnd != null) {
			temp = sdf.format(this.wedEnd);
		}
		if (this.thuStart != null) {
			temp = sdf.format(this.thuStart);
		}
		if (this.thuEnd != null) {
			temp = sdf.format(this.thuEnd);
		}
		if (this.friStart != null) {
			temp = sdf.format(this.friStart);
		}
		if (this.friEnd != null) {
			temp = sdf.format(this.friEnd);
		}
		if (this.satStart != null) {
			temp = sdf.format(this.satStart);
		}
		if (this.satEnd != null) {
			temp = sdf.format(this.satEnd);
		}
		if (this.sunStart != null) {
			temp = sdf.format(this.sunStart);
		}
		if (this.sunEnd != null) {
			temp = sdf.format(this.sunEnd);
		}

		return "[" + storeId + ":" + monStart + ":" + monEnd + ":" + tueStart
				+ ":" + tueEnd + ":" + wedStart + ":" + wedEnd + ":" + thuStart
				+ ":" + thuEnd + ":" + friStart + ":" + friEnd + ":" + satStart
				+ ":" + satEnd + ":" + sunStart + ":" + sunStart + "]";
	}

	private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

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

	public static java.util.Date convertDate(String data) {
		java.util.Date result = null;
		try {
			result = sdf.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
			result = new java.util.Date(0);
		}
		return result;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public java.util.Date getMonStart() {
		return monStart;
	}

	public void setMonStart(java.util.Date monStart) {
		this.monStart = monStart;
	}

	public java.util.Date getMonEnd() {
		return monEnd;
	}

	public void setMonEnd(java.util.Date monEnd) {
		this.monEnd = monEnd;
	}

	public java.util.Date getTueStart() {
		return tueStart;
	}

	public void setTueStart(java.util.Date tueStart) {
		this.tueStart = tueStart;
	}

	public java.util.Date getTueEnd() {
		return tueEnd;
	}

	public void setTueEnd(java.util.Date tueEnd) {
		this.tueEnd = tueEnd;
	}

	public java.util.Date getWedStart() {
		return wedStart;
	}

	public void setWedStart(java.util.Date wedStart) {
		this.wedStart = wedStart;
	}

	public java.util.Date getWedEnd() {
		return wedEnd;
	}

	public void setWedEnd(java.util.Date wedEnd) {
		this.wedEnd = wedEnd;
	}

	public java.util.Date getThuStart() {
		return thuStart;
	}

	public void setThuStart(java.util.Date thuStart) {
		this.thuStart = thuStart;
	}

	public java.util.Date getThuEnd() {
		return thuEnd;
	}

	public void setThuEnd(java.util.Date thuEnd) {
		this.thuEnd = thuEnd;
	}

	public java.util.Date getFriStart() {
		return friStart;
	}

	public void setFriStart(java.util.Date friStart) {
		this.friStart = friStart;
	}

	public java.util.Date getFriEnd() {
		return friEnd;
	}

	public void setFriEnd(java.util.Date friEnd) {
		this.friEnd = friEnd;
	}

	public java.util.Date getSatStart() {
		return satStart;
	}

	public void setSatStart(java.util.Date satStart) {
		this.satStart = satStart;
	}

	public java.util.Date getSatEnd() {
		return satEnd;
	}

	public void setSatEnd(java.util.Date satEnd) {
		this.satEnd = satEnd;
	}

	public java.util.Date getSunStart() {
		return sunStart;
	}

	public void setSunStart(java.util.Date sunStart) {
		this.sunStart = sunStart;
	}

	public java.util.Date getSunEnd() {
		return sunEnd;
	}

	public void setSunEnd(java.util.Date sunEnd) {
		this.sunEnd = sunEnd;
	}

}
