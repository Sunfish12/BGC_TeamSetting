package model.GroupChoiceGames;

public class GroupChoiceGamesBean {
	// choiceGamesSerialNumber int
	// groupSerialNumber int
	// boardGameStyle varchar(10)
	// boardGameName varchar(50)
	private Integer choiceGamesSerialNumber;
	private Integer groupSerialNumber;
	private String boardGameStyle;
	private String boardGameName;

	public String toString() {
		return choiceGamesSerialNumber + "\n" + groupSerialNumber + "\n"
				+ boardGameStyle + "\n" + boardGameName + "\n";
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

	public Integer getChoiceGamesSerialNumber() {
		return choiceGamesSerialNumber;
	}

	public void setChoiceGamesSerialNumber(Integer choiceGamesSerialNumber) {
		this.choiceGamesSerialNumber = choiceGamesSerialNumber;
	}

	public Integer getGroupSerialNumber() {
		return groupSerialNumber;
	}

	public void setGroupSerialNumber(Integer groupSerialNumber) {
		this.groupSerialNumber = groupSerialNumber;
	}

	public String getBoardGameStyle() {
		return boardGameStyle;
	}

	public void setBoardGameStyle(String boardGameStyle) {
		this.boardGameStyle = boardGameStyle;
	}

	public String getBoardGameName() {
		return boardGameName;
	}

	public void setBoardGameName(String boardGameName) {
		this.boardGameName = boardGameName;
	}

}
