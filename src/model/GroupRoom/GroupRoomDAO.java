package model.GroupRoom;

import java.io.InputStream;
import java.util.List;

public interface GroupRoomDAO {
	public abstract GroupRoomBean select(String groupUsername);

	public abstract List<GroupRoomBean> select();

	public abstract GroupRoomBean insert(GroupRoomBean bean, InputStream is,
			long size);

	public abstract GroupRoomBean update(GroupRoomBean bean, InputStream is,
			long size);

	public abstract boolean delete(String groupUsername);
}
