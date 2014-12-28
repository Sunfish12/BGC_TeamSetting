package model.GroupRoom;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupRoomDAOJdbc implements GroupRoomDAO {
	private static final String URL = "jdbc:sqlserver://localhost:1433;database=boardgames";
	private static final String USERNAME = "sa";
	private static final String PASSWORD = "123456";

	private static final String SELECT_BY_ID = "select * from GroupRoom where groupUsername=?";

	@Override
	public GroupRoomBean select(String groupUsername) {
		GroupRoomBean result = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.prepareStatement(SELECT_BY_ID);
			stmt.setString(1, groupUsername);
			rs = stmt.executeQuery();

			if (rs.next()) {
				result = new GroupRoomBean();
				result.setGroupSerialNumber(rs.getInt("groupSerialNumber"));
				result.setStoreUsername(rs.getString("storeUsername"));
				result.setStoreName(rs.getString("storeName"));
				result.setGroupStartTime(rs.getDate("groupUsername"));
				result.setGroupEndTime(rs.getDate("groupStartTime"));
				result.setGroupRoomName(rs.getString("groupEndTime"));
				result.setGroupSuggestNumber(rs.getInt("groupSuggestNumber"));
				result.setGroupLowerLimit(rs.getInt("groupLowerLimit"));
				result.setGroupUpperLimit(rs.getInt("groupUpperLimit"));
				result.setGroupGameTime(rs.getDate("groupGameTime"));
				result.setReserveGroupStartTime(rs
						.getDate("reserveGroupStartTime"));
				result.setReserveGroupEndTime(rs.getDate("reserveGroupEndTime"));
				result.setRoomState(rs.getInt("roomState"));
				result.setImgFileName(rs.getString("imgFileName"));
				result.setPrivateGroupImage(rs.getBytes("privateGroupImage"));
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

	private static final String SELECT_ALL = "select * from GroupRoom";

	@Override
	public List<GroupRoomBean> select() {
		List<GroupRoomBean> result = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.prepareStatement(SELECT_ALL);
			rs = stmt.executeQuery();

			result = new ArrayList<GroupRoomBean>();
			while (rs.next()) {
				GroupRoomBean bean = new GroupRoomBean();
				bean.setGroupSerialNumber(rs.getInt("groupSerialNumber"));
				bean.setStoreUsername(rs.getString("storeUsername"));
				bean.setStoreName(rs.getString("storeName"));
				bean.setGroupStartTime(rs.getDate("groupUsername"));
				bean.setGroupEndTime(rs.getDate("groupStartTime"));
				bean.setGroupRoomName(rs.getString("groupEndTime"));
				bean.setGroupSuggestNumber(rs.getInt("groupSuggestNumber"));
				bean.setGroupLowerLimit(rs.getInt("groupLowerLimit"));
				bean.setGroupUpperLimit(rs.getInt("groupUpperLimit"));
				bean.setGroupGameTime(rs.getDate("groupGameTime"));
				bean.setReserveGroupStartTime(rs
						.getDate("reserveGroupStartTime"));
				bean.setReserveGroupEndTime(rs.getDate("reserveGroupEndTime"));
				bean.setRoomState(rs.getInt("roomState"));
				bean.setImgFileName(rs.getString("imgFileName"));
				bean.setPrivateGroupImage(rs.getBytes("privateGroupImage"));

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

	private static final String INSERT = "insert into GroupRoom (storeUsername,storeName,groupUsername,groupStartTime,groupEndTime,groupRoomName,"
			+ "groupSuggestNumber,groupLowerLimit,groupUpperLimit,groupGameTime,reserveGroupStartTime,reserveGroupEndTime,roomState,"
			+ "imgFileName,privateGroupImage) values "
			+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	@Override
	public GroupRoomBean insert(GroupRoomBean bean, InputStream is, long size) {
		Connection conn = null;
		PreparedStatement stmt = null;
		GroupRoomBean result = null;

		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.prepareStatement(INSERT);

			result = new GroupRoomBean();
			stmt.setString(1, bean.getStoreUsername());
			stmt.setString(2, bean.getStoreName());
			stmt.setString(3, bean.getGroupUsername());

			java.util.Date groupStartTime = bean.getGroupStartTime();
			if (groupStartTime != null) {
				long temp = groupStartTime.getTime();
				stmt.setDate(4, new java.sql.Date(temp));
			} else {
				stmt.setDate(4, null);
			}

			java.util.Date groupEndTime = bean.getGroupEndTime();
			if (groupEndTime != null) {
				long temp = groupEndTime.getTime();
				stmt.setDate(5, new java.sql.Date(temp));
			} else {
				stmt.setDate(5, null);
			}
			stmt.setString(6, bean.getGroupRoomName());
			stmt.setInt(7, bean.getGroupSuggestNumber());
			stmt.setInt(8, bean.getGroupLowerLimit());
			stmt.setInt(9, bean.getGroupUpperLimit());

			java.util.Date groupGameTime = bean.getGroupGameTime();
			if (groupGameTime != null) {
				long temp = groupGameTime.getTime();
				stmt.setDate(10, new java.sql.Date(temp));
			} else {
				stmt.setDate(10, null);
			}

			java.util.Date reserveGroupStartTime = bean
					.getReserveGroupStartTime();
			if (reserveGroupStartTime != null) {
				long temp = reserveGroupStartTime.getTime();
				stmt.setDate(11, new java.sql.Date(temp));
			} else {
				stmt.setDate(11, null);
			}

			java.util.Date reserveGroupEndTime = bean.getReserveGroupEndTime();
			if (reserveGroupEndTime != null) {
				long temp = reserveGroupEndTime.getTime();
				stmt.setDate(12, new java.sql.Date(temp));
			} else {
				stmt.setDate(12, null);
			}
			stmt.setInt(13, bean.getRoomState());
			stmt.setString(14, bean.getImgFileName());
			stmt.setBinaryStream(15, is, size);

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

	private static final String UPDATE = "update GroupRoom set storeUsername=?,storeName=?,groupStartTime=?,groupEndTime=?,groupRoomName=?,"
			+ "groupSuggestNumber=?,groupLowerLimit=?,groupUpperLimit=?,groupGameTime=?,reserveGroupStartTime=?,reserveGroupEndTime=?,roomState=?,"
			+ "imgFileName=?,privateGroupImage=? where groupUsername=?";

	@Override
	public GroupRoomBean update(GroupRoomBean bean, InputStream is, long size) {
		Connection conn = null;
		PreparedStatement stmt = null;
		GroupRoomBean result = null;

		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.prepareStatement(UPDATE);

			result = new GroupRoomBean();
			stmt.setString(1, bean.getStoreUsername());
			stmt.setString(2, bean.getStoreName());

			java.util.Date groupStartTime = bean.getGroupStartTime();
			if (groupStartTime != null) {
				long temp = groupStartTime.getTime();
				stmt.setDate(3, new java.sql.Date(temp));
			} else {
				stmt.setDate(3, null);
			}

			java.util.Date groupEndTime = bean.getGroupEndTime();
			if (groupEndTime != null) {
				long temp = groupEndTime.getTime();
				stmt.setDate(4, new java.sql.Date(temp));
			} else {
				stmt.setDate(4, null);
			}
			stmt.setString(5, bean.getGroupRoomName());
			stmt.setInt(6, bean.getGroupSuggestNumber());
			stmt.setInt(7, bean.getGroupLowerLimit());
			stmt.setInt(8, bean.getGroupUpperLimit());

			java.util.Date groupGameTime = bean.getGroupGameTime();
			if (groupGameTime != null) {
				long temp = groupGameTime.getTime();
				stmt.setDate(9, new java.sql.Date(temp));
			} else {
				stmt.setDate(9, null);
			}

			java.util.Date reserveGroupStartTime = bean
					.getReserveGroupStartTime();
			if (reserveGroupStartTime != null) {
				long temp = reserveGroupStartTime.getTime();
				stmt.setDate(10, new java.sql.Date(temp));
			} else {
				stmt.setDate(10, null);
			}

			java.util.Date reserveGroupEndTime = bean.getReserveGroupEndTime();
			if (reserveGroupEndTime != null) {
				long temp = reserveGroupEndTime.getTime();
				stmt.setDate(11, new java.sql.Date(temp));
			} else {
				stmt.setDate(11, null);
			}
			stmt.setInt(12, bean.getRoomState());
			stmt.setString(13, bean.getImgFileName());
			stmt.setBinaryStream(14, is, size);
			stmt.setString(15, bean.getGroupUsername());
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

	private static final String DELETE = "delete from GroupRoom where groupUsername=?";

	@Override
	public boolean delete(String groupUsername) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.prepareStatement(DELETE);
			stmt.setString(1, groupUsername);
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
		GroupRoomDAOJdbc dao = new GroupRoomDAOJdbc();
		// select by id
		// System.out.println(dao.select(""));

		// select all
		// System.out.println(dao.select());

		// insert
		GroupRoomBean bean = new GroupRoomBean();
		File f = null;
		FileInputStream fis = null;
		long length = 0;

		try {
			f = new File("img/java_duke.jpg");
			fis = new FileInputStream(f);
			length = f.length();
			bean.setStoreUsername("Bob3");
			bean.setStoreName("瘋桌遊");
			bean.setGroupUsername("Bob4");
			bean.setGroupStartTime(new java.util.Date());
			bean.setGroupEndTime(new java.util.Date());
			bean.setGroupRoomName("玩桌遊八");
			bean.setGroupSuggestNumber(60);
			bean.setGroupLowerLimit(3);
			bean.setGroupUpperLimit(15);
			bean.setGroupGameTime(new java.util.Date());
			bean.setReserveGroupStartTime(new java.util.Date());
			bean.setReserveGroupEndTime(new java.util.Date());
			bean.setRoomState(0);
			bean.setImgFileName("123.jpg");
			dao.insert(bean, fis, length);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// update

		// GroupRoomBean bean = new GroupRoomBean();
		// File f = null;
		// FileInputStream fis = null;
		// long length = 0;
		//
		// try {
		// f = new File("img/java_duke.jpg");
		// fis = new FileInputStream(f);
		// length = f.length();
		//
		// bean.setStoreUsername("Bob3");
		// bean.setStoreName("瘋桌遊");
		// bean.setGroupUsername("Bob4");
		// bean.setGroupStartTime(new java.util.Date());
		// bean.setGroupEndTime(new java.util.Date());
		// bean.setGroupRoomName("玩桌遊123八");
		// bean.setGroupSuggestNumber(60);
		// bean.setGroupLowerLimit(2);
		// bean.setGroupUpperLimit(15);
		// bean.setGroupGameTime(new java.util.Date());
		// bean.setReserveGroupStartTime(new java.util.Date());
		// bean.setReserveGroupEndTime(new java.util.Date());
		// bean.setRoomState(0);
		// bean.setImgFileName("123.jpg");
		// dao.update(bean,fis,length);
		// } catch (FileNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// delete
		// dao.delete("Bob4");
	}

}
