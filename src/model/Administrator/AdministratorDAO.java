package model.Administrator;

import java.util.List;
import java.io.InputStream;

public interface AdministratorDAO {
	public abstract AdministratorBean select(String adminUsername); // select
																	// only one

	public abstract List<AdministratorBean> select(); // select all

	public abstract AdministratorBean insert(AdministratorBean bean,
			InputStream is, long size); // insert one

	public abstract AdministratorBean update(AdministratorBean bean,
			InputStream is, long size); // update one

	public abstract boolean delete(String adminUsername);
}
