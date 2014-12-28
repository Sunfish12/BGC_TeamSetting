package model.TabuUsernameTable;

//tabuId	int
//tabuUsername	varchar(30)
//toTabuUsername	varchar(30)
//tabuReason	varchar(400)
public class TabuUsernameTableBean {
	private Integer tabuId;
	private String tabuUsername;
	private String toTabuUsername;
	private String tabuReason;

	@Override
	public String toString() {

		return tabuId + "\n" + tabuUsername + "\n" + toTabuUsername + "\n"
				+ tabuReason + "\n";
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

	public Integer getTabuId() {
		return tabuId;
	}

	public void setTabuId(Integer tabuId) {
		this.tabuId = tabuId;
	}

	public String getTabuUsername() {
		return tabuUsername;
	}

	public void setTabuUsername(String tabuUsername) {
		this.tabuUsername = tabuUsername;
	}

	public String getToTabuUsername() {
		return toTabuUsername;
	}

	public void setToTabuUsername(String toTabuUsername) {
		this.toTabuUsername = toTabuUsername;
	}

	public String getTabuReason() {
		return tabuReason;
	}

	public void setTabuReason(String tabuReason) {
		this.tabuReason = tabuReason;
	}

}
