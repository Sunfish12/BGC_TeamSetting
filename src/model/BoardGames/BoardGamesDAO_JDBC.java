package model.BoardGames;

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

public class BoardGamesDAO_JDBC implements BoardGamesDAO {
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

	private static final String SELECT_BY_ID = "select * from boardgames where boardgamesid = ?";

	@Override
	public BoardGamesBean findByPrimeKey(Integer boardGamesId) {
		BoardGamesBean result = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, boardGamesId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = new BoardGamesBean();
				result.setBoardGamesId(rs.getInt("boardgamesid"));
				result.setStoreUsername(rs.getString("storeusername"));
				result.setStoreName(rs.getString("storename"));
				result.setBoardGameName(rs.getString("boardgamename"));
				result.setBoardGameStyle(rs.getString("boardgamestyle"));
				result.setBoardGameNumber(rs.getString("boardgamenumber"));
				result.setImgFileName(rs.getString("imgfilename"));

				// 圖片另存
				File f = new File("imagesDB/image_boardgames.jpg");

				try {
					BufferedOutputStream bos = new BufferedOutputStream(
							new FileOutputStream(f));
					byte[] b = rs.getBytes("boardgameimage");
					if (b != null) {
						bos.write(b, 0, (int) b.length);
						bos.close();
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				result.setBoardGameExplan(rs.getString("boardgameexplan"));
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

	private static final String SELECT_ALL = "select * from boardgames order by boardgamesid";

	@Override
	public List<BoardGamesBean> getAll() {
		List<BoardGamesBean> result = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = conn.prepareStatement(SELECT_ALL);
			rs = pstmt.executeQuery();
			result = new ArrayList<BoardGamesBean>();
			while (rs.next()) {
				BoardGamesBean bean = new BoardGamesBean();
				bean.setBoardGamesId(rs.getInt("boardgamesid"));
				bean.setStoreUsername(rs.getString("storeusername"));
				bean.setStoreName(rs.getString("storename"));
				bean.setBoardGameName(rs.getString("boardgamename"));
				bean.setBoardGameStyle(rs.getString("boardgamestyle"));
				bean.setBoardGameNumber(rs.getString("boardgamenumber"));
				bean.setImgFileName(rs.getString("imgfilename"));

				// 圖片另存
				File f = new File("imagesDB/image_boardgames.jpg");

				try {
					BufferedOutputStream bos = new BufferedOutputStream(
							new FileOutputStream(f));
					byte[] b = rs.getBytes("boardgameimage");
					if (b != null) {
						bos.write(b, 0, (int) b.length);
						bos.close();
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				bean.setBoardGameExplan(rs.getString("boardgameexplan"));
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

	private static final String INSERT = "insert into boardgames (storeusername, storename, boardgamename, boardgamestyle,"
			+ " boardgamenumber, imgfilename, boardgameimage, boardgameexplan) values (?, ?, ?, ?, ?, ?, ?, ?)";

	@Override
	public BoardGamesBean insert(BoardGamesBean bean, InputStream is,
			long size, String filename) {
		BoardGamesBean result = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
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

	private static final String UPDATE = "update boardgames set boardgamename=?,storeusername=?, storeName=?, "
			+ " boardgamestyle=?, boardgamenumber=?, imgfilename=?, boardgameimage=?,"
			+ " boardgameexplan=? where boardgamesid=?";

	@Override
	public BoardGamesBean update(BoardGamesBean bean, InputStream is,
			long size, String filename) {
		BoardGamesBean result = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(UPDATE);
			pstmt.setString(1, bean.getBoardGameName());
			pstmt.setString(2, bean.getStoreUsername());
			pstmt.setString(3, bean.getStoreName());

			pstmt.setString(4, bean.getBoardGameStyle());
			pstmt.setString(5, bean.getBoardGameNumber());

			if (filename != null) {
				pstmt.setString(6, filename);
			} else {
				pstmt.setString(6, null);
			}

			if (is != null && size != 0) {
				pstmt.setBinaryStream(7, is, size);
			} else {
				pstmt.setBinaryStream(7, null, 0);
			}

			pstmt.setString(8, bean.getBoardGameExplan());
			pstmt.setInt(8, bean.getBoardGamesId());

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

	private static final String DELETE = "delete from boardgames where boardgamesid=?";

	@Override
	public boolean delete(Integer boardGamesId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(DELETE);
			pstmt.setInt(1, boardGamesId);
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

		BoardGamesDAO dao = new BoardGamesDAO_JDBC();

		// Insert
		BoardGamesBean bean1 = new BoardGamesBean();
		bean1.setStoreUsername("sunfisher");
		bean1.setStoreName("瘋桌遊");
		bean1.setBoardGameName("三國殺");
		bean1.setBoardGameStyle("策略遊戲");
		bean1.setBoardGameNumber("6-10");
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
		bean1.setBoardGameExplan("三國殺規則簡介"
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
		dao.insert(bean1, is, size, filename1);

		// Update
		// BoardGamesBean bean2 = new BoardGamesBean();
		// bean2.setStoreUsername("sunfisher");
		// bean2.setStoreName("瘋桌遊");
		// bean2.setBoardGameName("龍與地下城");
		// bean2.setBoardGameStyle("角色扮演遊戲");
		// bean2.setBoardGameNumber("10-18");
		// String filename2 = "boardgames.jpg";
		// bean2.setImgFileName(filename2);
		// File f1 = new File("res/" + bean2.getImgFileName());
		// long size1 = 0;
		// InputStream is1 = null;
		// try {
		// size1 = f1.length();
		// is1 = new FileInputStream(f1);
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// }
		// bean2.setBoardGameExplan("龍與地下城是一款有結構但結局開放(open-ended)的角色扮演遊戲。"
		// +
		// "遊戲並無明確限制參與人數，通常會有3-5名玩家，以及一位主持人，稱為地下城主。遊戲大多都在室內進行，參與者們會圍坐在桌邊，因而稱為「桌上角色扮演遊戲」。"
		// + "一名玩家往往只會扮演一名角色，該名角色生活在虛擬的世界設定中。"
		// +
		// "玩家角色們（Player Characters，PCs）一般稱作冒險者，將組成一個團隊，每個成員運用自己的能力，為達成目標而發揮所長。"
		// + "在遊戲過程中，每位玩家都要決定自己角色將如何行動，並和其他角色進行互動。"
		// + "所謂角色互動大多由玩家以口語表達或文字的方式來呈現，此外不時也會運用到參與者的邏輯能力、基本算術和想像力。"
		// +
		// "完成一場遊戲的時間不定，說完一個故事稱作一場「冒險(adventure)」，完成一場冒險通常需要玩家多次聚會，由一連串冒險所組成的長篇事件叫做「戰役(campaign)」。"
		// +
		// "玩家在遊戲中做出各種選擇造成的結果，整個遊戲故事線的走向，都是由地下城主（Dungeon Master，DM），根據遊戲規則和DM對規則的解讀來決定。"
		// +
		// "DM負責決定並描述非玩家角色（non-player characters，NPCs）的行動、隊伍在冒險途中有何遭遇、互動發生的背景、以及玩家行動會造成何種結果。"
		// + "遭遇常常採取和「怪物」戰鬥的形式。在龍與地下城中，「怪物」是一種通稱，用來表示任何可能的敵人，像是動物、畸形的生物和神話生物。"
		// + "龍與地下城備有大量的規則來幫助DM判斷，這些規則涵蓋許多主題，如：社交互動、使用魔法、戰鬥、環境對玩家角色造成的影響。 "
		// +
		// "DM也可以選擇不完全照既定的官方規則進行，轉而依照個人需求修改或是重新編寫規則，這種作法通常稱作定立「家規/房規(house rule)」。"
		// +
		// "在龍與地下城的近期版本中，主要遊戲規則收錄在三本核心規則書裡：龍與地下城玩家手冊、龍與地下城城主指南、龍與地下城怪獸圖鑑。這三本書被中文玩家合稱為「三聖書」。"
		// + "進行龍與地下城需要的道具只有三聖書、每個玩家角色一張角色紙(Character Sheet)、幾顆多面骰和筆。"
		// +
		// "其他像是微縮模型、指示物(token)、地圖紙、卡片、擴充規則書、設計好的冒險模組和各種戰役設定書都能增加遊戲的樂趣，但是這些並非必備品。"
		// +
		// "和其他桌上遊戲最大的不同之處在於，龍與地下城的主題並不是個主持人和參與玩家互相競爭，而是所有參與者合力創作故事的遊戲，所以並沒有明確的「贏」或是「輸」，"
		// + "就算所有玩家的角色都被怪物殺死，只要全員都能從過程中獲得樂趣，仍舊算是「贏」。"
		// +
		// "基於遊戲本身開放的特性，DM可以設定玩家操縱一群新的冒險者前來為之前死亡的同伴復仇，或是設定全新的故事走向，遊戲還是可以繼續進行下去。");
		// bean2.setBoardGamesId(1);
		// dao.update(bean2, is1, size1, filename2);

		// Delete By BoardGameStyle
		// boolean b = dao.delete(1);
		// System.out.println(b);
		// Select By BoardGameStyle
		// BoardGamesBean bean3 = dao.findByPrimeKey(1);

		// Select All
		List<BoardGamesBean> beans = dao.getAll();
		System.out.println(beans);
	}
}
