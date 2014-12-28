package autoInsertData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.Administrator.AdministratorBean;
import model.BoardGameKind.BoardGameKindBean;
import model.BoardGameKind.BoardGameKindDAO;
import model.BoardGameKind.BoardGameKindDAO_JDBC;
import model.BoardGames.BoardGamesBean;
import model.BoardGames.BoardGamesDAO;
import model.BoardGames.BoardGamesDAO_JDBC;
import model.BoardGamesImage.BoardGamesImageBean;
import model.BoardGamesImage.BoardGamesImageDAO;
import model.BoardGamesImage.BoardGamesImageDAO_JDBC;
import model.GroupChoiceGames.GroupChoiceGamesBean;
import model.GroupRoom.GroupRoomBean;
import model.GroupRoomInfo.GroupRoomInfoBean;
import model.GroupRoomMessage.GroupRoomMessageBean;
import model.Joiner_Info.Joiner_InfoBean;
import model.Member.MemberBean;
import model.Member.MemberDAO;
import model.Member.MemberDAO_JDBC;
import model.MemberFavoredType.MemberFavoredTypeBean;
import model.RentalTime.RentalTimeBean;
import model.StoreInformation.StoreInformationBean;
import model.StoreInformation.StoreInformationDAO;
import model.StoreInformation.StoreInformationDAO_JDBC;
import model.StoreInformationImage.StoreInformationImageBean;
import model.StoreMember.StoreMemberBean;
import model.StoreMember.StoreMemberDAO_JDBC;
import model.TabuUsernameTable.TabuUsernameTableBean;

public class AutoInsertData {

	private static final String URL = "jdbc:sqlserver://localhost:1433;database=boardgames";
	private static final String USERNAME = "sa";
	private static final String PASSWORD = "123456";

	public TabuUsernameTableBean insert(TabuUsernameTableBean bean) {
		Connection conn = null;
		PreparedStatement stmt = null;
		TabuUsernameTableBean result = null;

		try {
			String INSERT = "insert into TabuUsernameTable (tabuUsername, toTabuUsername, tabuReason) values (?, ?, ?)";
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
			e.printStackTrace();
		}
		return result;
	}

	public StoreMemberBean insert(StoreMemberBean bean, InputStream is,
			long size, String filename) {
		StoreMemberBean result = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String INSERT = "insert into StoreMember (storeUsername, storePswd,"
					+ " storeJoinDate, storePhone, imgFileName, storeImage, storeEmail,"
					+ " storeWebsite) values (?, ?, ?, ?, ?, ?, ?, ?)";
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(INSERT);
			pstmt.setString(1, bean.getStoreUsername());
			pstmt.setBytes(2, bean.getStorePswd());

			// 接收storeJoinDate字串轉java.util.Date
			java.util.Date storeJoinDate = bean.getStoreJoinDate();
			if (storeJoinDate != null) {
				long temp = storeJoinDate.getTime();
				pstmt.setDate(3, new java.sql.Date(temp));
			} else {
				pstmt.setDate(3, null);
			}

			pstmt.setString(4, bean.getStorePhone());

			if (filename != null) {
				pstmt.setString(5, filename);
			} else {
				pstmt.setString(5, null);
			}

			// 準備存圖片
			if (is != null && size != 0) {
				pstmt.setBinaryStream(6, is, size);
			} else {
				pstmt.setBinaryStream(6, null, 0);
			}

			pstmt.setString(7, bean.getStoreEmail());
			pstmt.setString(8, bean.getStoreWebsite());

			int i = pstmt.executeUpdate();
			if (i == 1) {
				System.out.println("Insert Successful!");
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

	public StoreInformationImageBean insert(StoreInformationImageBean sibean,
			InputStream is, long size) {
		// TODO Auto-generated method stub
		return null;
	}

	public StoreInformationBean insert(StoreInformationBean bean,
			InputStream is, long size, String filename) {
		StoreInformationBean result = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String INSERT = "insert into storeinformation (storeUsername,"
					+ " storeName, storeAddress, imgFileName, storeImage, storeTel, rentAreaCost,"
					+ " groupUpperLimit) values (?, ?, ?, ?, ?, ?, ?, ?)";
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(INSERT);
			pstmt.setString(1, bean.getStoreUsername());
			pstmt.setString(2, bean.getStoreName());
			pstmt.setString(3, bean.getStoreAddress());

			if (filename != null) {
				pstmt.setString(4, filename);
			} else {
				pstmt.setString(4, null);
			}

			// 準備存圖片
			if (is != null && size != 0) {
				pstmt.setBinaryStream(5, is, size);
			} else {
				pstmt.setBinaryStream(5, null, 0);
			}

			pstmt.setString(6, bean.getStoreTel());
			pstmt.setDouble(7, bean.getRentAreaCost());
			pstmt.setInt(8, bean.getGroupUpperLimit());

			int i = pstmt.executeUpdate();
			if (i == 1) {
				System.out.println("Insert Successful!");
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

	public RentalTimeBean insert(RentalTimeBean rtbean, InputStream is,
			long size) {
		// TODO Auto-generated method stub
		return null;
	}

	public MemberFavoredTypeBean insert(MemberFavoredTypeBean bean) {
		Connection conn = null;
		PreparedStatement stmt = null;
		MemberFavoredTypeBean result = null;

		try {
			String INSERT = "insert into MemberFavoredType (username, favoredType) values (?, ?)";
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
			e.printStackTrace();
		}
		return result;
	}

	public MemberBean insert(MemberBean bean, InputStream is, long size,
			String filename) {
		MemberBean result = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String INSERT = "insert into Member (username, pswd, email, lastname,"
					+ " firstname, gender, nickname, birthday, idCard, joinDate, phone, memberAddress,"
					+ "imgFileName, memberImage, isGroupBan, isCommentBan, notBanTime, banTime)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, null, null)";
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(INSERT);
			pstmt.setString(1, bean.getUsername());
			pstmt.setBytes(2, bean.getPswd());
			pstmt.setString(3, bean.getEmail());
			pstmt.setString(4, bean.getLastname());
			pstmt.setString(5, bean.getFirstname());
			pstmt.setString(6, bean.getGender());
			pstmt.setNString(7, bean.getNickname());

			// 接收birthday字串轉java.util.Date
			java.util.Date birthday = bean.getBirthday();
			if (birthday != null) {
				long temp = birthday.getTime();
				pstmt.setDate(8, new java.sql.Date(temp));
			} else {
				pstmt.setDate(8, null);
			}

			pstmt.setString(9, bean.getIdCard());

			// 接收joinDate字串轉java.util.Date
			java.util.Date joinDate = bean.getJoinDate();
			if (joinDate != null) {
				long temp = joinDate.getTime();
				pstmt.setDate(10, new java.sql.Date(temp));
			} else {
				pstmt.setDate(10, null);
			}

			pstmt.setString(11, bean.getPhone());
			pstmt.setString(12, bean.getMemberAddress());

			if (filename != null) {
				pstmt.setString(13, filename);
			} else {
				pstmt.setString(13, null);
			}

			// 準備存圖片
			if (is != null && size != 0) {
				pstmt.setBinaryStream(14, is, size);
			} else {
				pstmt.setBinaryStream(14, null, 0);
			}

			pstmt.setBoolean(15, false);
			pstmt.setBoolean(16, false);
			int i = pstmt.executeUpdate();
			if (i == 1) {
				System.out.println("Insert Successful!");
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

	public Joiner_InfoBean insert(Joiner_InfoBean bean) {
		Connection conn = null;
		PreparedStatement stmt = null;
		Joiner_InfoBean result = null;

		try {
			String INSERT = "insert into Joiner_Info (groupSerialNumber,joinTime,username) values "
					+ "(?,?,?)";
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

	public GroupRoomMessageBean insert(GroupRoomMessageBean bean) {
		Connection conn = null;
		PreparedStatement stmt = null;
		GroupRoomMessageBean result = null;

		try {
			String INSERT = "insert into GroupRoomMessage (groupSerialNumber,messageUsername,messageContents,messageTime) values "
					+ "(?,?,?,?)";
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

	public GroupRoomInfoBean insert(GroupRoomInfoBean bean, InputStream is,
			long size) {
		Connection conn = null;
		PreparedStatement stmt = null;
		GroupRoomInfoBean result = null;

		try {
			String INSERT = "insert into GroupRoomInfo (groupSerialNumber,groupPicture,imgFileName) values "
					+ "(?,?,?)";
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

	public GroupRoomBean insert(GroupRoomBean bean, InputStream is, long size) {
		Connection conn = null;
		PreparedStatement stmt = null;
		GroupRoomBean result = null;

		try {
			String INSERT = "insert into GroupRoom (storeUsername,storeName,groupUsername,groupStartTime,groupEndTime,groupRoomName,"
					+ "groupSuggestNumber,groupLowerLimit,groupUpperLimit,groupGameTime,reserveGroupStartTime,reserveGroupEndTime,roomState,"
					+ "imgFileName,privateGroupImage) values "
					+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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

	public GroupChoiceGamesBean insert(GroupChoiceGamesBean bean) {
		Connection conn = null;
		PreparedStatement stmt = null;
		GroupChoiceGamesBean result = null;

		try {
			String INSERT = "insert into GroupChoiceGames(choiceGamesSerialNumber, groupSerialNumber, boardGameStyle, boardGameName) values (?, ?, ?, ?)";
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.prepareStatement(INSERT);

			result = new GroupChoiceGamesBean();
			stmt.setInt(1, bean.getChoiceGamesSerialNumber());
			stmt.setInt(2, bean.getGroupSerialNumber());
			stmt.setString(3, bean.getBoardGameStyle());
			stmt.setString(4, bean.getBoardGameName());
			int i = stmt.executeUpdate();
			if (i == 1) {
				System.out.println("Insert Successful!");
				return bean;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public BoardGamesImageBean insert(BoardGamesImageBean bean, InputStream is,
			long size, String filename) {
		BoardGamesImageBean result = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String INSERT = "insert into boardgamesimage (boardgamesid, imgfilename, boardgameimages) values (?, ?, ?)";
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
				System.out.println("Insert Successful!");
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

	public BoardGamesBean insert(BoardGamesBean bean, InputStream is,
			long size, String filename) {
		BoardGamesBean result = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String INSERT = "insert into boardgames (storeusername, storename, boardgamename, boardgamestyle,"
					+ " boardgamenumber, imgfilename, boardgameimage, boardgameexplan) values (?, ?, ?, ?, ?, ?, ?, ?)";
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(INSERT);
			pstmt.setString(1, bean.getStoreUsername());
			pstmt.setString(2, bean.getStoreName());
			pstmt.setString(3, bean.getBoardGameName());
			pstmt.setString(4, bean.getBoardGameStyle());
			pstmt.setString(5, bean.getBoardGameNumber());

			if (filename != null) {
				pstmt.setString(6, filename);
			} else {
				pstmt.setString(6, null);
			}

			// 準備存圖片
			if (is != null && size != 0) {
				pstmt.setBinaryStream(7, is, size);
			} else {
				pstmt.setBinaryStream(7, null, 0);
			}

			pstmt.setString(8, bean.getBoardGameExplan());
			int i = pstmt.executeUpdate();
			if (i == 1) {
				System.out.println("Insert Successful!");
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

	public BoardGameKindBean insert(BoardGameKindBean bean) {
		BoardGameKindBean result = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String INSERT = "insert into boardgamekind (boardgamenumber, boardgamestyle) values (?, ?)";
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(INSERT);
			pstmt.setInt(1, bean.getBoardGameNumber());
			pstmt.setString(2, bean.getBoardGameStyle());
			int i = pstmt.executeUpdate();
			if (i == 1) {
				System.out.println("Insert Successful!");
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

	public AdministratorBean insert(AdministratorBean bean, InputStream is,
			long size) {
		Connection conn = null;
		PreparedStatement stmt = null;
		AdministratorBean result = null;

		try {
			String INSERT = "insert into Administrator (adminUsername, adminPswd, imgFileName, adminMemberImage) values (?, ?, ?, ?)";
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.prepareStatement(INSERT);

			result = new AdministratorBean();
			stmt.setString(1, bean.getAdminUsername());
			stmt.setBytes(2, bean.getAdminPswd());
			stmt.setString(3, bean.getImgFileName());
			stmt.setBinaryStream(4, is, size);

			int i = stmt.executeUpdate();
			if (i == 1) {
				System.out.println("Insert Successful!");
				return bean;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
		// 新增會員
		MemberDAO md = new MemberDAO_JDBC();
		MemberBean mb1 = new MemberBean();
		mb1.setUsername("sunfisher");
		mb1.setPswd("Aa@123".getBytes());
		mb1.setEmail("sunfisher@gmail.com");
		mb1.setLastname("Freeman");
		mb1.setFirstname("Gold");
		mb1.setGender("male");
		mb1.setNickname("戰士");
		mb1.setBirthday(MemberBean.convertDate("1990-10-10"));
		mb1.setIdCard("A1234567890");
		mb1.setJoinDate(MemberBean.convertDate("2014-10-10"));
		mb1.setPhone("0911222333");
		mb1.setMemberAddress("新北市三重區集美街219號3樓");
		String mfilename1 = "boardgames.jpg";
		mb1.setImgFileName(mfilename1);
		File mf = new File("res/" + mb1.getImgFileName());
		long msize = 0;
		InputStream mis = null;
		try {
			msize = mf.length();
			mis = new FileInputStream(mf);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		md.insert(mb1, mis, msize, mfilename1);
		// 印出全部
		List<MemberBean> mbs = md.getAll();
		System.out.println(mbs);

		// 新增店家會員
		StoreMemberDAO_JDBC smd = new StoreMemberDAO_JDBC();
		StoreMemberBean smb1 = new StoreMemberBean();
		smb1.setStoreUsername("sunfisher");
		smb1.setStorePswd("Aa@123".getBytes());
		smb1.setStoreJoinDate(StoreMemberBean.convertDate("2014-10-10"));
		smb1.setStorePhone("0911222333");
		String smbfilename1 = "boardgames.jpg";
		smb1.setImgFileName(smbfilename1);
		File smbf = new File("res/" + smb1.getImgFileName());
		long smbsize = 0;
		InputStream smbis = null;
		try {
			smbsize = smbf.length();
			smbis = new FileInputStream(smbf);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		smb1.setStoreEmail("sunfisher@gmail.com");
		smb1.setStoreWebsite("http://www.boardgamesclub.com.tw");
		smd.insert(smb1, smbis, smbsize, smbfilename1);
		// 印出全部
		List<StoreMemberBean> smbs = smd.getAll();
		System.out.println(smbs);

		// 新增店家專賣店
		StoreInformationDAO sid = new StoreInformationDAO_JDBC();
		StoreInformationBean sib1 = new StoreInformationBean();
		sib1.setStoreUsername("sunfisher");
		sib1.setStoreName("瘋桌遊");
		sib1.setStoreAddress("台北市松山區三民路102巷20號");
		String sifilename1 = "boardgames.jpg";
		sib1.setImgFileName(sifilename1);
		File sif = new File("res/" + sib1.getImgFileName());
		long sisize = 0;
		InputStream siis = null;
		try {
			sisize = sif.length();
			siis = new FileInputStream(sif);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		sib1.setStoreTel("(02)2528-2765");
		sib1.setRentAreaCost(120.0);
		sib1.setGroupUpperLimit(50);
		sid.insert(sib1, siis, sisize, sifilename1);
		// 印出全部
		List<StoreInformationBean> sibs = sid.getAll();
		System.out.println(sibs);

		// 新增桌遊類型
		BoardGameKindDAO bgkd = new BoardGameKindDAO_JDBC();
		BoardGameKindBean bgkb1 = new BoardGameKindBean();
		bgkb1.setBoardGameNumber(1);
		bgkb1.setBoardGameStyle("策略遊戲");
		bgkd.insert(bgkb1);

		BoardGameKindBean bgkb2 = new BoardGameKindBean();
		bgkb2.setBoardGameNumber(2);
		bgkb2.setBoardGameStyle("益智遊戲");
		bgkd.insert(bgkb2);

		BoardGameKindBean bgkb3 = new BoardGameKindBean();
		bgkb3.setBoardGameNumber(3);
		bgkb3.setBoardGameStyle("推理遊戲");
		bgkd.insert(bgkb3);

		BoardGameKindBean bgkb4 = new BoardGameKindBean();
		bgkb4.setBoardGameNumber(4);
		bgkb4.setBoardGameStyle("角色扮演遊戲");
		bgkd.insert(bgkb4);

		BoardGameKindBean bgkb5 = new BoardGameKindBean();
		bgkb5.setBoardGameNumber(5);
		bgkb5.setBoardGameStyle("幼教遊戲");
		bgkd.insert(bgkb5);

		BoardGameKindBean bgkb6 = new BoardGameKindBean();
		bgkb6.setBoardGameNumber(6);
		bgkb6.setBoardGameStyle("小品遊戲");
		bgkd.insert(bgkb6);
		// 印出全部
		List<BoardGameKindBean> sgkbs = bgkd.getAll();
		System.out.println(sgkbs);

		// 新增專賣店桌遊項目
		BoardGamesDAO bgd = new BoardGamesDAO_JDBC();
		BoardGamesBean bgb1 = new BoardGamesBean();
		bgb1.setStoreUsername("sunfisher");
		bgb1.setStoreName("瘋桌遊");
		bgb1.setBoardGameName("三國殺");
		bgb1.setBoardGameStyle("策略遊戲");
		bgb1.setBoardGameNumber("6-10");
		String bgfilename1 = "boardgames.jpg";
		bgb1.setImgFileName(bgfilename1);
		File bgf = new File("res/" + bgb1.getImgFileName());
		long bgsize = 0;
		InputStream bgis = null;
		try {
			bgsize = bgf.length();
			bgis = new FileInputStream(bgf);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		bgb1.setBoardGameExplan("三國殺規則簡介"
				+ "三國殺基本上是一個陣營遊戲，所以他最重要的目的就是「辨別陣營，排除異己」"
				+ "在這個遊戲中，共有四種身分，分別是主公、忠臣、反賊還有內奸"
				+ "他們分別有不同的勝利條件："
				+ "主公：殺掉反賊和內奸"
				+ "忠臣：幫助主公殺掉反賊跟內奸"
				+ "反賊：殺掉主公"
				+ "內奸：殺掉忠臣與反賊，最後與主公單挑獲勝"
				+ "在遊戲一開始，只有主公的身分是公開的，其餘的玩家必須將自己的身分保密"
				+ "在挑選好身分之後，就是決定武將的階段了，三國殺的武將是這個遊戲的精髓所在，我之後會以單篇單篇的方式來介紹各個武將的使用心得，敬請期待囉～"
				+ "選好武將之後，從公用牌庫為每位玩家派給4張牌做為起始手牌，然後由主公開始，逆時鐘順序進行遊戲"
				+ "每個回合都有六個階段："
				+ "1.回合開始階段"
				+ "2.判定階段"
				+ "3.抽牌階段"
				+ "4.出牌階段"
				+ "5.棄牌階段"
				+ "6.回合結束階段"
				+ "1.回合開始階段：除非有武將有特別的技能需要處理，否則通常是直接跳過"
				+ "2.判定階段：判定階段可能會需要處理一些延時性錦囊，所以記得不要錯過判定喔，所謂判定，就是從公用牌庫的牌庫頂翻開一張牌，以這張牌的花色、顏色、數字等等條件來做為判定結果"
				+ "3.抽牌階段：每個人在這回合都可以抽2張牌"
				+ "4.出牌階段：你可以在這個階段使用任意張的牌，或是使用武將的技能，但要注意，每個人基本上只能使用一張「殺」喔，使用後的牌都必須放入棄牌堆"
				+ "5.棄牌階段：在這個階段，你要檢查自己的現有血量與手牌數，你的手牌數不可以大於現有血量喔，多的牌必須放入棄牌堆"
				+ "6.回合結束階段：你宣告本回合結束" + "遊戲結束的判定，遊戲基本上會有三種情況結束"
				+ "1.主公掛掉，內奸若是唯一存活的，內奸獲勝" + "2.主公掛掉，只要有反賊或忠臣存活，反賊獲勝"
				+ "3.反賊、內奸死光，主公和忠臣一起獲勝");
		bgd.insert(bgb1, bgis, bgsize, bgfilename1);
		// 印出全部
		List<BoardGamesBean> bgbs = bgd.getAll();
		System.out.println(bgbs);

		// 新增桌遊介紹圖片
		BoardGamesImageDAO bgid = new BoardGamesImageDAO_JDBC();
		BoardGamesImageBean bgib1 = new BoardGamesImageBean();
		bgib1.setBoardGamesId(1);
		String bgifilename1 = "boardgames.jpg";
		bgib1.setImgFileName(bgifilename1);
		File bgif = new File("res/" + bgib1.getImgFileName());
		long bgisize = 0;
		InputStream bgiis = null;
		try {
			bgisize = bgif.length();
			bgiis = new FileInputStream(bgif);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		bgid.insert(bgib1, bgiis, bgisize, bgifilename1);
		// 印出全部
		List<BoardGamesImageBean> bgibs = bgid.getAll();
		System.out.println(bgibs);
	}
}
