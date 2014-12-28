package model.GroupRoomMessage;

import java.io.InputStream;
import java.util.List;

public interface GroupRoomMessageDAO {
	public abstract GroupRoomMessageBean select(String groupSerialNumber);

	public abstract List<GroupRoomMessageBean> select();

	public abstract GroupRoomMessageBean insert(GroupRoomMessageBean bean);

	public abstract GroupRoomMessageBean update(GroupRoomMessageBean bean);

	public abstract boolean delete(Integer groupRoomMessageSerialNumber);
}
