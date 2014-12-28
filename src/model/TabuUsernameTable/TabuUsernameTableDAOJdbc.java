package model.TabuUsernameTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TabuUsernameTableDAOJdbc implements TabuUsernameTableDAO {
	private static final String URL = "jdbc:sqlserver://localhost:1433;database=boardgames";
	private static final String USERNAME = "sa";
	private static final String PASSWORD = "123456";

	private static final String SELECT_BY_ID = "select * from TabuUsernameTable where tabuId=?";

	@Override
	public TabuUsernameTableBean select(String tabuId) {
		TabuUsernameTableBean result = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;

		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.prepareStatement(SELECT_BY_ID);
			stmt.setString(1, tabuId);
			rset = stmt.executeQuery();

			if (rset.next()) {
				result = new TabuUsernameTableBean();
				result.setTabuId(rset.getInt("tabuId"));
				result.setTabuUsername(rset.getString("tabuUsername"));
				result.setToTabuUsername(rset.getString("toTabuUsername"));
				result.setTabuReason(rset.getString("tabuReason"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rset != null) {
				try {
					rset.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return result;
	}

	private static final String SELECT_ALL = "select * from TabuUsernameTable";

	@Override
	public List<TabuUsernameTableBean> select() {
		List<TabuUsernameTableBean> result = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;

		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.prepareStatement(SELECT_ALL);
			rset = stmt.executeQuery();

			result = new ArrayList<TabuUsernameTableBean>();
			while (rset.next()) {
				TabuUsernameTableBean bean = new TabuUsernameTableBean();
				bean.setTabuId(rset.getInt("tabuId"));
				bean.setTabuUsername(rset.getString("tabuUsername"));
				bean.setToTabuUsername(rset.getString("toTabuUsername"));
				bean.setTabuReason(rset.getString("tabuReason"));

				result.add(bean);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rset != null) {
				try {
					rset.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return result;
	}

	private static final String INSERT = "insert into TabuUsernameTable (tabuUsername, toTabuUsername, tabuReason) values (?, ?, ?)";

	@Override
	public TabuUsernameTableBean insert(TabuUsernameTableBean bean) {
		Connection conn = null;
		PreparedStatement stmt = null;
		TabuUsernameTableBean result = null;

		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.prepareStatement(INSERT);

			result = new TabuUsernameTableBean();
			stmt.setString(1, bean.getTabuUsername());
			stmt.setString(2, bean.getToTabuUsername());
			stmt.setString(3, bean.getTabuReason());

			int i = stmt.executeUpdate();
			if (i == 1) {
				System.out.println("Insert Successful!");
				return bean;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	private static final String UPDATE = "update TabuUsernameTable set tabuUsername=?, toTabuUsername=?, tabuReason=? where tabuId=?";

	@Override
	public TabuUsernameTableBean update(TabuUsernameTableBean bean) {
		Connection conn = null;
		PreparedStatement stmt = null;
		TabuUsernameTableBean result = null;

		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.prepareStatement(UPDATE);
			stmt.setString(1, bean.getTabuUsername());
			stmt.setString(2, bean.getToTabuUsername());
			stmt.setString(3, bean.getTabuReason());
			stmt.setInt(4, bean.getTabuId());

			int i = stmt.executeUpdate();
			if (i == 1) {
				System.out.println("Update Successful!");
				return bean;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	private static final String DELETE = "delete from TabuUsernameTable where tabuId=?";

	@Override
	public boolean delete(String tabuId) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.prepareStatement(DELETE);
			stmt.setString(1, tabuId);
			int i = stmt.executeUpdate();

			if (i == 1) {
				System.out.println("Delete Successful!");
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		TabuUsernameTableDAOJdbc dao = new TabuUsernameTableDAOJdbc();

		// select by Id
		// System.out.println(dao.select("3"));

		// //select all
		// List<TabuUsernameTableBean> beans = dao.select();
		// System.out.println(beans);

		// insert
		// TabuUsernameTableBean bean = new TabuUsernameTableBean();
		// bean.setTabuUsername("Bob4");
		// bean.setToTabuUsername("Akaitsuki");
		// bean.setTabuReason("No Soul");
		// dao.insert(bean);

		// update
		// TabuUsernameTableBean bean = new TabuUsernameTableBean();
		// bean.setTabuUsername("Bob4");
		// bean.setToTabuUsername("Akaitsuki");
		// bean.setTabuReason("no soul4");
		// bean.setTabuId(3);
		// dao.update(bean);

		// delete
		// dao.delete("4");

	}

}
