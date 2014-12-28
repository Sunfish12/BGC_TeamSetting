package model.GroupRoomInfo;

import java.io.InputStream;
import java.util.List;

public interface GroupRoomInfoDAO {
	public abstract GroupRoomInfoBean select(String groupSerialNumber);

	public abstract List<GroupRoomInfoBean> select();

	public abstract GroupRoomInfoBean insert(GroupRoomInfoBean bean,
			InputStream is, long size);

	public abstract GroupRoomInfoBean update(GroupRoomInfoBean bean,
			InputStream is, long size);

	public abstract boolean delete(Integer groupSerialNumber);
}
