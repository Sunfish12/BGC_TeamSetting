package model.StoreScore;
public class StoreScoreBean 
{
	private Integer storeId;									  //int, 
	private String username;								  //varchar(30),
	private Double storeScore;								  //float,
	private String storeScoreReason;						  //varchar(400),
	
	public static double ConvertDouble(String data)
	{
		double result = 0;
		try {
			result = Double.parseDouble(data);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			result = -1000;
		}
		return result;
	}
	public static int ConvertInt(String data)
	{
		int result = 0;
		try {
			result=Integer.parseInt(data);
		} catch (NumberFormatException e) {
			result = -1000;
			e.printStackTrace();
		}
		return result;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Double getStoreScore() {
		return storeScore;
	}
	public void setStoreScore(Double storeScore) {
		this.storeScore = storeScore;
	}
	public String getStoreScoreReason() {
		return storeScoreReason;
	}
	public void setStoreScoreReason(String storeScoreReason) {
		this.storeScoreReason = storeScoreReason;
	}
	
	
}
