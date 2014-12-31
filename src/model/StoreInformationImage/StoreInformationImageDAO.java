package model.StoreInformationImage;

import java.io.InputStream;
import java.util.List;

public interface StoreInformationImageDAO {
	public abstract StoreInformationImageBean select(Integer storeImageId);

	public abstract List<StoreInformationImageBean> select();

	public abstract StoreInformationImageBean update(
			StoreInformationImageBean sibean, InputStream is, long size,
			String filename);

	public abstract StoreInformationImageBean insert(
			StoreInformationImageBean sibean, InputStream is, long size,
			String filename);

	public abstract boolean delete(Integer storeImageId);
}
