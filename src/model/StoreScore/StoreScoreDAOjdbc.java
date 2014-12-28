package model.StoreScore;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StoreScoreDAOjdbc implements StoreScoreDAO
{
	private static final String URL = "jdbc:sqlserver://localhost:1433;database=boardgames";
	private static final String USERNAME = "sa";
	private static final String PASSWORD = "123456";

	private static final String SELECT_BY_ID = "select * from StorScore where storeId = ?";
	@Override
	public StoreScoreBean select(Integer storeId) 
	{
		StoreScoreBean result = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		
		try {
			conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			stmt = conn.prepareStatement(SELECT_BY_ID);
			stmt.setInt(1, storeId);
			rset = stmt.executeQuery();
			
			if(rset.next())
			{
				result = new StoreScoreBean();
				result.setStoreId(rset.getInt("storeId"));
				result.setUsername(rset.getString("username"));
				result.setStoreScore(rset.getDouble("storeScore"));
				result.setStoreScoreReason(rset.getString("storeScoreReason"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			if (conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt!=null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rset!=null) {
				try {
					rset.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public static final String SELECT_ALL = "select * from StoreScore";
	@Override
	public List<StoreScoreBean> select() 
	{
		List<StoreScoreBean> result = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		
		try {
			conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			stmt = conn.prepareStatement(SELECT_ALL);
			rset = stmt.executeQuery();
			result = new ArrayList<StoreScoreBean>();
			while(rset.next())
			{
				StoreScoreBean ssbean = new StoreScoreBean();
				ssbean.setStoreId(rset.getInt("storeId"));
				ssbean.setStoreScore(rset.getDouble("storeScore"));
				ssbean.setUsername(rset.getString("username"));
				ssbean.setStoreScoreReason(rset.getString("storeScoreReason"));
				result.add(ssbean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if (conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (stmt!=null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (rset!=null) {
				try {
					rset.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public static final String insert = "insert into StoreScore(storeId = ?,storeScore = ?,storeScoreReason = ? where username = ?)";
	@Override
	public StoreScoreBean insert(StoreScoreBean ssbean, InputStream is,
			long size) 
	{
		
		Connection conn = null;
		PreparedStatement stmt = null;
		StoreScoreBean result = null;
		
		try {
			conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			stmt = conn.prepareStatement(insert);
			
			result = new StoreScoreBean();
			stmt.setInt(1, ssbean.getStoreId());
			stmt.setDouble(2, ssbean.getStoreScore());
			stmt.setString(3, ssbean.getStoreScoreReason());
			
			int i =stmt.executeUpdate();
			if(i==1)
			{
				System.out.println("Insert successfully!");
				return ssbean;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		return result;
	}

	public static final String UPDATE = "update StoreScore set storeId=?,storeScore=?,storeScoreReason=? where username=?";
	@Override
	public StoreScoreBean update(StoreScoreBean ssbean, InputStream is,
			long size) 
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		StoreScoreBean result = null;
		
		try {
			conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			stmt = conn.prepareStatement(UPDATE);
			stmt.setInt(1, ssbean.getStoreId());
			stmt.setDouble(2, ssbean.getStoreScore());
			stmt.setString(3, ssbean.getStoreScoreReason());
			
			int i = stmt.executeUpdate();
			if(i==1)
			{
				System.out.println("Update successfully!");
				return ssbean;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return result;
	}
   
	public static final String DELETE = "delete from StoreScore where username = ?";
	@Override
	public boolean delete(Integer storeId) 
	{
		Connection conn= null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			stmt = conn.prepareStatement(DELETE);
			stmt.setInt(1, storeId);
			int i =stmt.executeUpdate();
			if(i==1)
			{
				System.out.println("Delete succefully!");
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if (conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (stmt!=null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		// TODO Auto-generated method stub
		return false;
	}
}
