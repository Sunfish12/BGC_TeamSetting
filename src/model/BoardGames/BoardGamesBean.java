package model.BoardGames;

public class BoardGamesBean implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer boardGamesId;
	private String storeUsername;
	private String storeName;
	private String boardGameName;
	private String boardGameStyle;
	private String boardGameNumber;
	private String imgFileName;
	private byte[] boardGameImage;
	private String boardGameExplan;

	public BoardGamesBean() {
	}

	@Override
	public String toString() {
		return "[" + boardGamesId + "," + storeUsername + "," + storeName + ","
				+ boardGameName + "," + boardGameStyle + "," + boardGameNumber
				+ "," + imgFileName + "," + boardGameExplan + "]";
	}

	public Integer getBoardGamesId() {
		return boardGamesId;
	}

	public void setBoardGamesId(Integer boardGamesId) {
		this.boardGamesId = boardGamesId;
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

	public String getBoardGameName() {
		return boardGameName;
	}

	public void setBoardGameName(String boardGameName) {
		this.boardGameName = boardGameName;
	}

	public String getBoardGameStyle() {
		return boardGameStyle;
	}

	public void setBoardGameStyle(String boardGameStyle) {
		this.boardGameStyle = boardGameStyle;
	}

	public String getBoardGameNumber() {
		return boardGameNumber;
	}

	public void setBoardGameNumber(String boardGameNumber) {
		this.boardGameNumber = boardGameNumber;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	public byte[] getBoardGameImage() {
		return boardGameImage;
	}

	public void setBoardGameImage(byte[] boardGameImage) {
		this.boardGameImage = boardGameImage;
	}

	public String getBoardGameExplan() {
		return boardGameExplan;
	}

	public void setBoardGameExplan(String boardGameExplan) {
		this.boardGameExplan = boardGameExplan;
	}
}
