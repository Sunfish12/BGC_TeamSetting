package model.GroupRoomInfo;

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

public class GroupRoomInfoDAOJdbc implements GroupRoomInfoDAO {

	private static final String URL = "jdbc:sqlserver://localhost:1433;database=boardgames";
	private static final String USERNAME = "sa";
	private static final String PASSWORD = "123456";

	private static final String SELECT_BY_ID = "select * from GroupRoomInfo where groupSerialNumber=?";

	@Override
	public GroupRoomInfoBean select(String groupSerialNumber) {
		GroupRoomInfoBean result = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.prepareStatement(SELECT_BY_ID);
			stmt.setString(1, groupSerialNumber);
			rs = stmt.executeQuery();

			if (rs.next()) {
				result = new GroupRoomInfoBean();
				result.setGroupSerialNumber(rs.getInt("groupSerialNumber"));
				result.setImgFileName(rs.getString("imgFileName"));
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

	private static final String SELECT_ALL = "select * from GroupRoomInfo";

	@Override
	public List<GroupRoomInfoBean> select() {
		List<GroupRoomInfoBean> result = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.prepareStatement(SELECT_ALL);
			rs = stmt.executeQuery();

			result = new ArrayList<GroupRoomInfoBean>();
			while (rs.next()) {
				GroupRoomInfoBean bean = new GroupRoomInfoBean();
				bean.setGroupSerialNumber(rs.getInt("groupSerialNumber"));
				bean.setImgFileName(rs.getString("imgFileName"));

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

	private static final String INSERT = "insert into GroupRoomInfo (groupSerialNumber,groupPicture,imgFileName) values "
			+ "(?,?,?)";

	@Override
	public GroupRoomInfoBean insert(GroupRoomInfoBean bean, InputStream is,
			long size) {
		Connection conn = null;
		PreparedStatement stmt = null;
		GroupRoomInfoBean result = null;

		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.prepareStatement(INSERT);

			result = new GroupRoomInfoBean();
			stmt.setInt(1, bean.getGroupSerialNumber());
			stmt.setBinaryStream(2, is, size);
			stmt.setString(3, bean.getImgFileName());

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

	private static final String UPDATE = "update GroupRoomInfo set groupPicture=?,imgFileName=? where groupSerialNumber=?";

	@Override
	public GroupRoomInfoBean update(GroupRoomInfoBean bean, InputStream is,
			long size) {
		Connection conn = null;
		PreparedStatement stmt = null;
		GroupRoomInfoBean result = null;

		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.prepareStatement(UPDATE);

			result = new GroupRoomInfoBean();
			stmt.setBinaryStream(1, is, size);
			stmt.setString(2, bean.getImgFileName());
			stmt.setInt(3, bean.getGroupSerialNumber());

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

	private static final String DELETE = "delete from GroupRoomInfo where groupSerialNumber=?";

	@Override
	public boolean delete(Integer groupSerialNumber) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.prepareStatement(DELETE);
			stmt.setInt(1, groupSerialNumber);
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

		GroupRoomInfoDAOJdbc dao = new GroupRoomInfoDAOJdbc();

		// select by id
		// System.out.println(dao.select("4"));

		// select
		// System.out.println(dao.select());

		// insert
		// GroupRoomInfoBean bean = new GroupRoomInfoBean();
		// File f = null;
		// FileInputStream fis = null;
		// long length = 0;
		//
		// try {
		// f = new File("img/java_duke.jpg");
		// fis = new FileInputStream(f);
		// length = f.length();
		// bean.setGroupSerialNumber(4);
		// bean.setImgFileName("456789");
		//
		// dao.insert(bean,fis,length);
		// } catch (FileNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// update
		// GroupRoomInfoBean bean = new GroupRoomInfoBean();
		// File f = null;
		// FileInputStream fis = null;
		// long length = 0;
		//
		// try {
		// f = new File("img/java_duke.jpg");
		// fis = new FileInputStream(f);
		// length = f.length();
		// bean.setGroupSerialNumber(4);
		// bean.setImgFileName("44546465464656789");
		//
		// dao.update(bean,fis,length);
		// } catch (FileNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// delete
		// dao.delete(4);
	}

}
