package model.StoreInformationImage;
public class StoreInformationImageBean 
{
	private Integer storeId;      							  //int,
	private Integer storeImageId;							  //int IDENTITY (1,1),
	private String boardGameHelp;							  //varchar(MAX),
	private String imgFileName;								  //varchar(50),
	private byte[] areaImage;								  //image,
	
	public static int ConverInt(String data)
	{
		Integer result = 0;
		try {
			result = Integer.parseInt(data);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = -1000;
		}
		return result; 
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public Integer getStoreImageId() {
		return storeImageId;
	}
	public void setStoreImageId(Integer storeImageId) {
		this.storeImageId = storeImageId;
	}
	public String getBoardGameHelp() {
		return boardGameHelp;
	}
	public void setBoardGameHelp(String boardGameHelp) {
		this.boardGameHelp = boardGameHelp;
	}
	public String getImgFileName() {
		return imgFileName;
	}
	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}
	public byte[] getAreaImage() {
		return areaImage;
	}
	public void setAreaImage(byte[] areaImage) {
		this.areaImage = areaImage;
	}
	
}
