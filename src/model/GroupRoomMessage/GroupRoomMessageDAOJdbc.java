package model.GroupRoomMessage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupRoomMessageDAOJdbc implements GroupRoomMessageDAO {

	private static final String URL = "jdbc:sqlserver://localhost:1433;database=boardgames";
	private static final String USERNAME = "sa";
	private static final String PASSWORD = "123456";

	private static final String SELECT_BY_ID = "select * from GroupRoomMessage where groupSerialNumber=?";

	@Override
	public GroupRoomMessageBean select(String groupSerialNumber) {
		GroupRoomMessageBean result = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.prepareStatement(SELECT_BY_ID);
			stmt.setString(1, groupSerialNumber);
			rs = stmt.executeQuery();

			if (rs.next()) {
				result = new GroupRoomMessageBean();
				result.setGroupRoomMessageSerialNumber(rs
						.getInt("groupRoomMessageSerialNumber"));
				result.setGroupSerialNumber(rs.getInt("groupSerialNumber"));
				result.setMessageUsername(rs.getString("messageUsername"));
				result.setMessageContents(rs.getString("messageContents"));
				result.setMessageTime(rs.getDate("messageTime"));
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

	private static final String SELECT_ALL = "select * from GroupRoomMessage";

	@Override
	public List<GroupRoomMessageBean> select() {
		List<GroupRoomMessageBean> result = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.prepareStatement(SELECT_ALL);
			rs = stmt.executeQuery();

			result = new ArrayList<GroupRoomMessageBean>();
			while (rs.next()) {
				GroupRoomMessageBean bean = new GroupRoomMessageBean();
				bean.setGroupRoomMessageSerialNumber(rs
						.getInt("groupRoomMessageSerialNumber"));
				bean.setGroupSerialNumber(rs.getInt("groupSerialNumber"));
				bean.setMessageUsername(rs.getString("messageUsername"));
				bean.setMessageContents(rs.getString("messageContents"));
				bean.setMessageTime(rs.getDate("messageTime"));

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

	private static final String INSERT = "insert into GroupRoomMessage (groupSerialNumber,messageUsername,messageContents,messageTime) values "
			+ "(?,?,?,?)";

	@Override
	public GroupRoomMessageBean insert(GroupRoomMessageBean bean) {
		Connection conn = null;
		PreparedStatement stmt = null;
		GroupRoomMessageBean result = null;

		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.prepareStatement(INSERT);

			result = new GroupRoomMessageBean();
			stmt.setInt(1, bean.getGroupSerialNumber());
			stmt.setString(2, bean.getMessageUsername());
			stmt.setString(3, bean.getMessageContents());
			java.util.Date MessageTime = bean.getMessageTime();
			if (MessageTime != null) {
				long temp = MessageTime.getTime();
				stmt.setDate(4, new java.sql.Date(temp));
			} else {
				stmt.setDate(4, null);
			}

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

	private static final String UPDATE = "update GroupRoomMessage set groupSerialNumber=?,messageUsername=?,messageContents=?,messageTime=? where groupRoomMessageSerialNumber=?";

	@Override
	public GroupRoomMessageBean update(GroupRoomMessageBean bean) {
		Connection conn = null;
		PreparedStatement stmt = null;
		GroupRoomMessageBean result = null;

		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.prepareStatement(UPDATE);

			result = new GroupRoomMessageBean();
			stmt.setInt(1, bean.getGroupSerialNumber());
			stmt.setString(2, bean.getMessageUsername());
			stmt.setString(3, bean.getMessageContents());
			java.util.Date MessageTime = bean.getMessageTime();
			if (MessageTime != null) {
				long temp = MessageTime.getTime();
				stmt.setDate(4, new java.sql.Date(temp));
			} else {
				stmt.setDate(4, null);
			}
			stmt.setInt(5, bean.getGroupRoomMessageSerialNumber());
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

	private static final String DELETE = "delete from GroupRoomMessage where groupRoomMessageSerialNumber=?";

	@Override
	public boolean delete(Integer groupRoomMessageSerialNumber) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.prepareStatement(DELETE);
			stmt.setInt(1, groupRoomMessageSerialNumber);
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
		GroupRoomMessageDAOJdbc dao = new GroupRoomMessageDAOJdbc();

		// select by id
		// System.out.println(dao.select("4"));

		// select
		// System.out.println(dao.select());

		// insert
		// GroupRoomMessageBean bean = new GroupRoomMessageBean();
		//
		// bean.setGroupSerialNumber(4);
		// bean.setMessageContents("有人在嗎！！！！！");
		// bean.setMessageTime(GroupRoomMessageBean.convertDate("2012-08-03"));
		// bean.setMessageUsername("Geroge");
		// dao.insert(bean);

		// update
		// GroupRoomMessageBean bean = new GroupRoomMessageBean();
		//
		// bean.setGroupSerialNumber(4);
		// bean.setGroupRoomMessageSerialNumber(1);
		// bean.setMessageContents("有GOGO嗎！!?!?！！");
		// bean.setMessageTime(GroupRoomMessageBean.convertDate("2012-08-03"));
		// bean.setMessageUsername("Geroge");
		// dao.update(bean);

		// delete
		// dao.delete(1);
	}

}
