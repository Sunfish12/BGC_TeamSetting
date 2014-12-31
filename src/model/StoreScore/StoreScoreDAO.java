package model.StoreScore;

import java.io.InputStream;
import java.util.List;

public interface StoreScoreDAO {
	public abstract StoreScoreBean select(Integer storeId);

	public abstract List<StoreScoreBean> select();

	public abstract StoreScoreBean insert(StoreScoreBean ssbean);

	public abstract StoreScoreBean update(StoreScoreBean ssbean);

	public abstract boolean delete(Integer storeId);
}
