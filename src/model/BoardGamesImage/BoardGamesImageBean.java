package model.BoardGamesImage;

public class BoardGamesImageBean implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer boardGamesId;
	private Integer storeImageId;
	private String imgFileName;
	private Byte[] boardGameImages;

	public BoardGamesImageBean() {
	}

	@Override
	public String toString() {
		return "[" + boardGamesId + "," + storeImageId + "," + imgFileName
				+ "]";
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

	public Integer getBoardGamesId() {
		return boardGamesId;
	}

	public void setBoardGamesId(Integer boardGamesId) {
		this.boardGamesId = boardGamesId;
	}

	public Integer getStoreImageId() {
		return storeImageId;
	}

	public void setStoreImageId(Integer storeImageId) {
		this.storeImageId = storeImageId;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	public Byte[] getBoardGameImages() {
		return boardGameImages;
	}

	public void setBoardGameImages(Byte[] boardGameImages) {
		this.boardGameImages = boardGameImages;
	}
}
