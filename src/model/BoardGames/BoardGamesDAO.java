package model.BoardGames;

import java.io.InputStream;
import java.util.List;

public interface BoardGamesDAO {
	public abstract BoardGamesBean findByPrimeKey(Integer boardGamesId);

	public abstract List<BoardGamesBean> getAll();

	public abstract BoardGamesBean insert(BoardGamesBean bean, InputStream is,
			long size, String filename);

	public abstract BoardGamesBean update(BoardGamesBean bean, InputStream is,
			long size, String filename);

	public abstract boolean delete(Integer boardGamesId);
}
