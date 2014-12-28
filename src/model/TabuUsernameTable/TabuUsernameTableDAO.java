package model.TabuUsernameTable;

import java.io.InputStream;
import java.util.List;

public interface TabuUsernameTableDAO {
	public abstract TabuUsernameTableBean select(String tabuId);

	public abstract List<TabuUsernameTableBean> select();

	public abstract TabuUsernameTableBean insert(TabuUsernameTableBean bean);

	public abstract TabuUsernameTableBean update(TabuUsernameTableBean bean);

	public abstract boolean delete(String tabuId);

}
