package model.Joiner_Info;

import java.io.InputStream;
import java.util.List;

public interface Joiner_InfoDAO {
	public abstract Joiner_InfoBean select(String groupSerialNumber);

	public abstract List<Joiner_InfoBean> select();

	public abstract Joiner_InfoBean insert(Joiner_InfoBean bean);

	public abstract Joiner_InfoBean update(Joiner_InfoBean bean);

	public abstract boolean delete(Integer joiner_InfoSerialNumber);
}
