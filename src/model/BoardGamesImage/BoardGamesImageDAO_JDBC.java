package model.BoardGamesImage;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardGamesImageDAO_JDBC implements BoardGamesImageDAO {
	private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=BoardGames";
	private static final String USERNAME = "sa";
	private static final String PASSWORD = "sa123456";

	// private DataSource dataSource;
	//
	// public MemberDAO_JDBC() {
	// try {
	// Context ctx = new InitialContext();
	// dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/xxx");
	// } catch (NamingException e) {
	// e.printStackTrace();
	// }
	// }

	private static final String SELECT_BY_ID = "select * from boardgamesimage where storeimageid = ?";

	@Override
	public BoardGamesImageBean findByPrimeKey(Integer storeImageId) {
		BoardGamesImageBean result = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, storeImageId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = new BoardGamesImageBean();
				result.setBoardGamesId(rs.getInt("boardgamesid"));
				result.setStoreImageId(rs.getInt("storeimageid"));
				result.setImgFileName(rs.getString("imgFileName"));

				// 圖片另存
				File f = new File("imagesDB/image_boardgames.jpg");

				try {
					BufferedOutputStream bos = new BufferedOutputStream(
							new FileOutputStream(f));
					byte[] b = rs.getBytes("boardgameimages");
					if (b != null) {
						bos.write(b, 0, (int) b.length);
						bos.close();
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
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

	private static final String SELECT_ALL = "select * from boardgamesimage order by storeimageid";

	@Override
	public List<BoardGamesImageBean> getAll() {
		List<BoardGamesImageBean> result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = conn.prepareStatement(SELECT_ALL);
			rs = pstmt.executeQuery();
			result = new ArrayList<BoardGamesImageBean>();
			while (rs.next()) {
				BoardGamesImageBean bean = new BoardGamesImageBean();
				bean.setBoardGamesId(rs.getInt("boardgamesid"));
				bean.setStoreImageId(rs.getInt("storeimageid"));
				bean.setImgFileName(rs.getString("imgFileName"));

				// 圖片另存
				File f = new File("imagesDB/image_boardgames.jpg");

				try {
					BufferedOutputStream bos = new BufferedOutputStream(
							new FileOutputStream(f));
					byte[] b = rs.getBytes("boardgameimages");
					if (b != null) {
						bos.write(b, 0, (int) b.length);
						bos.close();
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				result.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
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

	private static final String INSERT = "insert into boardgamesimage (boardgamesid, imgfilename, boardgameimages) values (?, ?, ?)";

	@Override
	public BoardGamesImageBean insert(BoardGamesImageBean bean, InputStream is,
			long size, String filename) {
		BoardGamesImageBean result = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(INSERT);
			pstmt.setInt(1, bean.getBoardGamesId());

			if (filename != null) {
				pstmt.setString(2, filename);
			} else {
				pstmt.setString(2, null);
			}

			// 準備存圖片
			if (is != null && size != 0) {
				pstmt.setBinaryStream(3, is, size);
			} else {
				pstmt.setBinaryStream(3, null, 0);
			}

			int i = pstmt.executeUpdate();
			if (i == 1) {
				result = bean;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
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

	private static final String UPDATE = "update boardgamesimage set boardGamesId=?,"
			+ " imgFileName=?, boardGameImages=? where storeImageId=?";

	@Override
	public BoardGamesImageBean update(BoardGamesImageBean bean, InputStream is,
			long size, String filename) {
		BoardGamesImageBean result = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(UPDATE);

			pstmt.setInt(1, bean.getBoardGamesId());

			if (filename != null) {
				pstmt.setString(2, filename);
			} else {
				pstmt.setString(2, null);
			}

			// 準備存圖片
			if (is != null && size != 0) {
				pstmt.setBinaryStream(3, is, size);
			} else {
				pstmt.setBinaryStream(3, null, 0);
			}

			pstmt.setInt(4, bean.getStoreImageId());

			int i = pstmt.executeUpdate();
			if (i == 1) {
				result = bean;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
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

	private static final String DELETE = "delete from boardgamesimage where storeImageId=?";

	@Override
	public boolean delete(Integer storeImageId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(DELETE);
			pstmt.setInt(1, storeImageId);
			int i = pstmt.executeUpdate();
			if (i == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
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

		BoardGamesImageDAO dao = new BoardGamesImageDAO_JDBC();

		// Insert
		BoardGamesImageBean bean1 = new BoardGamesImageBean();
		bean1.setBoardGamesId(1);
		String filename1 = "boardgames.jpg";
		bean1.setImgFileName(filename1);
		File f = new File("res/" + bean1.getImgFileName());
		long size = 0;
		InputStream is = null;
		try {
			size = f.length();
			is = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		dao.insert(bean1, is, size, filename1);

		// Select All
		List<BoardGamesImageBean> beans = dao.getAll();
		System.out.println(beans);
	}
}
