package model.Joiner_Info;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Joiner_InfoDAOJdbc implements Joiner_InfoDAO {

	private static final String URL = "jdbc:sqlserver://localhost:1433;database=boardgames";
	private static final String USERNAME = "sa";
	private static final String PASSWORD = "123456";

	private static final String SELECT_BY_ID = "select * from Joiner_Info where groupSerialNumber=?";

	@Override
	public Joiner_InfoBean select(String groupSerialNumber) {
		Joiner_InfoBean result = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.prepareStatement(SELECT_BY_ID);
			stmt.setString(1, groupSerialNumber);
			rs = stmt.executeQuery();

			if (rs.next()) {
				result = new Joiner_InfoBean();
				result.setJoiner_InfoSerialNumber(rs
						.getInt("joiner_InfoSerialNumber"));
				result.setGroupSerialNumber(rs.getInt("groupSerialNumber"));
				result.setJoinTime(rs.getDate("joinTime"));
				result.setUsername(rs.getString("username"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
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

	private static final String SELECT_ALL = "select * from Joiner_Info";

	@Override
	public List<Joiner_InfoBean> select() {
		List<Joiner_InfoBean> result = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.prepareStatement(SELECT_ALL);
			rs = stmt.executeQuery();

			result = new ArrayList<Joiner_InfoBean>();
			while (rs.next()) {
				Joiner_InfoBean bean = new Joiner_InfoBean();
				bean.setJoiner_InfoSerialNumber(rs
						.getInt("joiner_InfoSerialNumber"));
				bean.setGroupSerialNumber(rs.getInt("groupSerialNumber"));
				bean.setJoinTime(rs.getDate("joinTime"));
				bean.setUsername(rs.getString("username"));

				result.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
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

	private static final String INSERT = "insert into Joiner_Info (groupSerialNumber,joinTime,username) values "
			+ "(?,?,?)";

	@Override
	public Joiner_InfoBean insert(Joiner_InfoBean bean) {
		Connection conn = null;
		PreparedStatement stmt = null;
		Joiner_InfoBean result = null;

		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.prepareStatement(INSERT);

			result = new Joiner_InfoBean();
			stmt.setInt(1, bean.getGroupSerialNumber());
			java.util.Date joinTime = bean.getJoinTime();
			if (joinTime != null) {
				long temp = joinTime.getTime();
				stmt.setDate(2, new java.sql.Date(temp));
			} else {
				stmt.setDate(2, null);
			}
			stmt.setString(3, bean.getUsername());

			int i = stmt.executeUpdate();
			if (i == 1) {
				System.out.println("Insert Successful!");
				return bean;
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

		return result;
	}

	private static final String UPDATE = "update Joiner_Info set username=?,joinTime=? where groupSerialNumber=?";

	@Override
	public Joiner_InfoBean update(Joiner_InfoBean bean) {
		Connection conn = null;
		PreparedStatement stmt = null;
		Joiner_InfoBean result = null;

		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.prepareStatement(UPDATE);

			result = new Joiner_InfoBean();
			stmt.setInt(1, bean.getGroupSerialNumber());
			java.util.Date joinTime = bean.getJoinTime();
			if (joinTime != null) {
				long temp = joinTime.getTime();
				stmt.setDate(2, new java.sql.Date(temp));
			} else {
				stmt.setDate(2, null);
			}
			stmt.setString(3, bean.getUsername());

			int i = stmt.executeUpdate();
			if (i == 1) {
				System.out.println("Update Successful!");
				return bean;
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

		return result;
	}

	private static final String DELETE = "delete from Joiner_Info where joiner_InfoSerialNumber=?";

	@Override
	public boolean delete(Integer joiner_InfoSerialNumber) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.prepareStatement(DELETE);
			stmt.setInt(1, joiner_InfoSerialNumber);
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

		Joiner_InfoDAOJdbc dao = new Joiner_InfoDAOJdbc();

		// select by id
		// System.out.println(dao.select("4"));

		// select
		// System.out.println(dao.select());

		// insert
		// Joiner_InfoBean bean = new Joiner_InfoBean();
		// bean.setGroupSerialNumber(4);
		// bean.setJoinTime(Joiner_InfoBean.convertDate("2012-08-08"));
		// bean.setUsername("GG");
		// dao.insert(bean);

		// update
		// Joiner_InfoBean bean = new Joiner_InfoBean();
		// bean.setGroupSerialNumber(4);
		// bean.setJoinTime(Joiner_InfoBean.convertDate("2012-08-08"));
		// bean.setUsername("GGG");
		// dao.update(bean);

		// delete
		// dao.delete(1);
	}

}
