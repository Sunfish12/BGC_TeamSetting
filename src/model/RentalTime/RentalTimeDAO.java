package model.RentalTime;

import java.io.InputStream;
import java.util.List;

public interface RentalTimeDAO {
	public abstract RentalTimeBean select(Integer storeId);

	public abstract List<RentalTimeBean> select();

	public abstract RentalTimeBean insert(RentalTimeBean rtbean);

	public abstract RentalTimeBean update(RentalTimeBean rtbean);

	public abstract boolean delete(Integer storeId);
}
