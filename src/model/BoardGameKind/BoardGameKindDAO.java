package model.BoardGameKind;

import java.io.InputStream;
import java.util.List;

public interface BoardGameKindDAO {
	public abstract BoardGameKindBean findByPrimeKey(String boardGameStyle);

	public abstract List<BoardGameKindBean> getAll();

	public abstract BoardGameKindBean insert(BoardGameKindBean bean);

	public abstract BoardGameKindBean update(BoardGameKindBean bean);

	public abstract boolean delete(String boardGameStyle);
}
