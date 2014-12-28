package model.BoardGamesImage;

import java.io.InputStream;
import java.util.List;

public interface BoardGamesImageDAO {
	public abstract BoardGamesImageBean findByPrimeKey(Integer storeImageId);

	public abstract List<BoardGamesImageBean> getAll();

	public abstract BoardGamesImageBean insert(BoardGamesImageBean bean,
			InputStream is, long size, String filename);

	public abstract BoardGamesImageBean update(BoardGamesImageBean bean,
			InputStream is, long size, String filename);

	public abstract boolean delete(Integer storeImageId);
}
