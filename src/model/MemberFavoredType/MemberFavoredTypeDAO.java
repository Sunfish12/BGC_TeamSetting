package model.MemberFavoredType;

import java.io.InputStream;
import java.util.List;

public interface MemberFavoredTypeDAO {
	public abstract MemberFavoredTypeBean select(String username);

	public abstract List<MemberFavoredTypeBean> select();

	public abstract MemberFavoredTypeBean insert(MemberFavoredTypeBean bean);

	public abstract MemberFavoredTypeBean update(MemberFavoredTypeBean bean);

	public abstract boolean delete(String username);

}
