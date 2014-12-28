package model.GroupChoiceGames;

import java.io.InputStream;
import java.util.List;

public interface GroupChoiceGamesDAO {
	public abstract GroupChoiceGamesBean select(Integer choiceGamesSerialNumber);

	public abstract List<GroupChoiceGamesBean> select();

	public abstract GroupChoiceGamesBean insert(GroupChoiceGamesBean bean);

	public abstract GroupChoiceGamesBean update(GroupChoiceGamesBean bean);

	public abstract boolean delete(String choiceGamesSerialNumber);
}
