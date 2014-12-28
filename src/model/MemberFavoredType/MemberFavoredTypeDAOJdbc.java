package model.MemberFavoredType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberFavoredTypeDAOJdbc implements MemberFavoredTypeDAO {
	private static final String URL = "jdbc:sqlserver://localhost:1433;database=boardgames";
	private static final String USERNAME = "sa";
	private static final String PASSWORD = "123456";

	private static final String SELECT_BY_ID = "select * from MemberFavoredType where username=?";

	@Override
	public MemberFavoredTypeBean select(String username) {
		MemberFavoredTypeBean result = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;

		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.prepareStatement(SELECT_BY_ID);
			stmt.setString(1, username);
			rset = stmt.executeQuery();

			if (rset.next()) {
				result = new MemberFavoredTypeBean();
				result.setUsername(rset.getString("username"));
				result.setFavoredType(rset.getString("favoredType"));

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

	private static final String SELECT_ALL = "select * from MemberFavoredType";

	@Override
	public List<MemberFavoredTypeBean> select() {
		List<MemberFavoredTypeBean> result = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;

		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.prepareStatement(SELECT_ALL);
			rset = stmt.executeQuery();

			result = new ArrayList<MemberFavoredTypeBean>();
			while (rset.next()) {
				MemberFavoredTypeBean bean = new MemberFavoredTypeBean();
				bean.setUsername(rset.getString("username"));
				bean.setFavoredType(rset.getString("favoredType"));
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

	private static final String INSERT = "insert into MemberFavoredType (username, favoredType) values (?, ?)";

	@Override
	public MemberFavoredTypeBean insert(MemberFavoredTypeBean bean) {
		Connection conn = null;
		PreparedStatement stmt = null;
		MemberFavoredTypeBean result = null;

		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.prepareStatement(INSERT);

			result = new MemberFavoredTypeBean();
			stmt.setString(1, bean.getUsername());
			stmt.setString(2, bean.getFavoredType());
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

	private static final String UPDATE = "update MemberFavoredType set favoredType=?";

	@Override
	public MemberFavoredTypeBean update(MemberFavoredTypeBean bean) {
		Connection conn = null;
		PreparedStatement stmt = null;
		MemberFavoredTypeBean result = null;

		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.prepareStatement(UPDATE);
			stmt.setString(1, bean.getFavoredType());

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

	private static final String DELETE = "delete from MemberFavoredType where username=?";

	@Override
	public boolean delete(String username) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.prepareStatement(DELETE);
			stmt.setString(1, username);
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
		MemberFavoredTypeDAO dao = new MemberFavoredTypeDAOJdbc();

		// select by id
		// System.out.println(dao.select("Akaitsuki"));

		// select all
		// List<MemberFavoredTypeBean> beans = dao.select();
		// System.out.println(beans);

		// //insert
		MemberFavoredTypeBean bean = new MemberFavoredTypeBean();
		bean.setUsername("Akaitsuki");
		bean.setFavoredType("Arpg");
		dao.insert(bean);

		// update
		// MemberFavoredTypeBean bean = new MemberFavoredTypeBean();
		// bean.setUsername("Akaitsuki");
		// bean.setFavoredType("no soul2");
		// System.out.println(bean);

		// delete
		// dao.delete("Akaitsuki");

	}

}
