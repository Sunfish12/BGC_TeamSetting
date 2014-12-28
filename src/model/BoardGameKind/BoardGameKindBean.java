package model.BoardGameKind;

public class BoardGameKindBean implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer boardGameNumber;
	private String boardGameStyle;

	public BoardGameKindBean() {
	}

	@Override
	public String toString() {
		return "[" + boardGameNumber + "," + boardGameStyle + "]";
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

	public Integer getBoardGameNumber() {
		return boardGameNumber;
	}

	public void setBoardGameNumber(Integer boardGameNumber) {
		this.boardGameNumber = boardGameNumber;
	}

	public String getBoardGameStyle() {
		return boardGameStyle;
	}

	public void setBoardGameStyle(String boardGameStyle) {
		this.boardGameStyle = boardGameStyle;
	}

}
