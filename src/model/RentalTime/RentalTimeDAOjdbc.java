package model.RentalTime;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RentalTimeDAOjdbc implements RentalTimeDAO
{
	private static final String URL = "jdbc:sqlserver://localhost:1433;database=boardgames";
	private static final String USERNAME = "sa";
	private static final String PASSWORD = "123456";

	private static final String SELECT_BY_ID = "select * from Member where storeId=?";
	@Override
	public RentalTimeBean select(Integer storeId) 
	{
		RentalTimeBean result = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		
		try {
			conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			stmt = conn.prepareStatement(SELECT_BY_ID);
			stmt.setInt(1,storeId);
			rset = stmt.executeQuery();
			
			if(rset.next())
			{
				result = new RentalTimeBean();
				result.setMonStart(rset.getDate("MonStart"));
				result.setMonEnd(rset.getDate("MonEnd"));
				result.setTueStart(rset.getDate("TueStart"));
				result.setTueEnd(rset.getDate("TueEnd"));
				result.setWedStart(rset.getDate("WedStart"));
				result.setWedEnd(rset.getDate("WedEnd"));
				result.setThuStart(rset.getDate("ThuStart"));
				result.setThuEnd(rset.getDate("ThuEnd"));
				result.setFriStart(rset.getDate("FriStart"));
				result.setFriEnd(rset.getDate("FriEnd"));
				result.setSatStart(rset.getDate("SatStart"));
				result.setSatEnd(rset.getDate("SatEnd"));
				result.setSunStart(rset.getDate("SunStart"));
				result.setSunEnd(rset.getDate("SunEnd"));
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

	public static final String SELECT_ALL = "select * from RentalTime";
	@Override
	public List<RentalTimeBean> select() 
	{
		List<RentalTimeBean> result =null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		
		try {
			conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			stmt = conn.prepareStatement(SELECT_ALL);
			rset = stmt.executeQuery();
			while(rset.next())
			{
				RentalTimeBean rtbean = new RentalTimeBean();
				rtbean.setStoreId(rset.getInt("storeId"));
				rtbean.setMonStart(rset.getDate("MonStart"));
				rtbean.setMonEnd(rset.getDate("MonEnd"));
				rtbean.setTueStart(rset.getDate("TueStart"));
				rtbean.setTueEnd(rset.getDate("TueEnd"));
				rtbean.setWedStart(rset.getDate("WedStart"));
				rtbean.setWedEnd(rset.getDate("WedEnd"));
				rtbean.setThuStart(rset.getDate("ThuStart"));
				rtbean.setThuEnd(rset.getDate("ThuEnd"));
				rtbean.setFriStart(rset.getDate("FriStart"));
				rtbean.setFriEnd(rset.getDate("FriEnd"));
				rtbean.setSatStart(rset.getDate("SatStart"));
				rtbean.setSatEnd(rset.getDate("SatEnd"));
				rtbean.setSunStart(rset.getDate("SunStart"));
				rtbean.setSunEnd(rset.getDate("SunEnd"));
				
				result.add(rtbean);
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

	public static final String INSERT ="insert into RentalTime (MonStart,MonEnd,"
			+ "TueStart,TueEnd,WedStart,WedEnd,"
			+ "ThuStart,ThuEnd,FriStart,FriEnd,"
			+ "SatStart,SatEnd,sunStart,sunEnd) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	@Override
	public RentalTimeBean insert(RentalTimeBean rtbean) 
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		RentalTimeBean result = null;
		
		try {
			conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			stmt = conn.prepareStatement(INSERT);

			result = new RentalTimeBean();
			java.util.Date monStart = rtbean.getMonStart();
			if(monStart!=null) {
				long temp = monStart.getTime();
				stmt.setTime(1, new java.sql.Time(temp));
			} else {
				stmt.setTime(1, null);
			}
			
			java.util.Date monEnd = rtbean.getMonEnd();
			if(monEnd!=null) {
				long temp = monEnd.getTime();
				stmt.setTime(2, new java.sql.Time(temp));
			} else {
				stmt.setTime(2, null);
			}
			
			java.util.Date tueStart = rtbean.getTueStart();
			if(tueStart!=null) {
				long temp = tueStart.getTime();
				stmt.setTime(3, new java.sql.Time(temp));
			} else {
				stmt.setTime(3, null);
			}
			
			java.util.Date tueEnd = rtbean.getTueEnd();
			if(tueEnd!=null) {
				long temp = tueEnd.getTime();
				stmt.setTime(4, new java.sql.Time(temp));
			} else {
				stmt.setTime(4, null);
			}
			
			java.util.Date wedStart = rtbean.getWedStart();
			if(wedStart!=null) {
				long temp = wedStart.getTime();
				stmt.setTime(5, new java.sql.Time(temp));
			} else {
				stmt.setTime(5, null);
			}
			
			java.util.Date wedEnd = rtbean.getWedEnd();
			if(wedEnd!=null) {
				long temp = wedEnd.getTime();
				stmt.setTime(6, new java.sql.Time(temp));
			} else {
				stmt.setTime(6, null);
			}
			
			java.util.Date thuStart = rtbean.getThuStart();
			if(thuStart!=null) {
				long temp = thuStart.getTime();
				stmt.setTime(7, new java.sql.Time(temp));
			} else {
				stmt.setTime(7, null);
			}
			
			java.util.Date thuEnd = rtbean.getThuEnd();
			if(thuEnd!=null) {
				long temp = thuEnd.getTime();
				stmt.setTime(8, new java.sql.Time(temp));
			} else {
				stmt.setTime(8, null);
			}
			
			java.util.Date friStart = rtbean.getFriStart();
			if(friStart!=null) {
				long temp = friStart.getTime();
				stmt.setTime(9, new java.sql.Time(temp));
			} else {
				stmt.setTime(9, null);
			}
			
			java.util.Date friEnd = rtbean.getFriEnd();
			if(friEnd!=null) {
				long temp = friEnd.getTime();
				stmt.setTime(10, new java.sql.Time(temp));
			} else {
				stmt.setTime(10, null);
			}
			
			java.util.Date satStart = rtbean.getSatStart();
			if(satStart!=null) {
				long temp = satStart.getTime();
				stmt.setTime(11, new java.sql.Time(temp));
			} else {
				stmt.setTime(11, null);
			}
			
			java.util.Date satEnd = rtbean.getSatEnd();
			if(satEnd!=null) {
				long temp = satEnd.getTime();
				stmt.setTime(12, new java.sql.Time(temp));
			} else {
				stmt.setTime(12, null);
			}
			
			java.util.Date sunStart = rtbean.getSunStart();
			if(sunStart!=null) {
				long temp = sunStart.getTime();
				stmt.setTime(13, new java.sql.Time(temp));
			} else {
				stmt.setTime(13, null);
			}
			
			java.util.Date sunEnd = rtbean.getSunEnd();
			if(sunEnd!=null) {
				long temp = sunEnd.getTime();
				stmt.setTime(14, new java.sql.Time(temp));
			} else {
				stmt.setTime(14, null);
			}
			
			int i =stmt.executeUpdate();
			if(i==1)
			{
				System.out.println("Insert successfully!");
				return rtbean;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	public static final String UPDATE = "update RentalTime set MonStart=?,MonEnd=?,"
			+ "TueStart=?,TueEnd=?,WedStart=?,WedEnd=?,"
			+ "ThuStart=?,ThuEnd=?,FriStart=?,FriEnd=?,"
			+ "SatStart=?,SatEnd=?,sunStart=?,sunEnd=? where storeId=?";
	@Override
	public RentalTimeBean update(RentalTimeBean rtbean) 
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		RentalTimeBean result = null;
		
		try {
			conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			stmt = conn.prepareStatement(UPDATE);
			stmt.setInt(1, rtbean.getStoreId());
			java.util.Date monStart = rtbean.getMonStart();
			if(monStart!=null)
			{
				long temp = monStart.getTime();
				stmt.setDate(2, new java.sql.Date(temp));
			}
			else
			{
				stmt.setDate(2, null);
			}
			
			java.util.Date monEnd = rtbean.getMonEnd();
			if(monEnd!=null)
			{
				long temp = monEnd.getTime();
				stmt.setDate(3, new java.sql.Date(temp));
			}
			else
			{
				stmt.setDate(3, null);
			}
			
			java.util.Date tueStart = rtbean.getTueStart();
			if(tueStart!=null)
			{
				long temp = tueStart.getTime();
				stmt.setDate(4, new java.sql.Date(temp));
			}
			else
			{
				stmt.setDate(4, null);
			}
			
			java.util.Date tueEnd = rtbean.getTueEnd();
			if(tueEnd!=null)
			{
				long temp = tueEnd.getTime();
				stmt.setDate(5, new java.sql.Date(temp));
			}
			else
			{
				stmt.setDate(5, null);
			}
			
			java.util.Date wedStart = rtbean.getWedStart();
			if(wedStart!=null)
			{
				long temp = wedStart.getTime();
				stmt.setDate(6, new java.sql.Date(temp));
			}
			else
			{
				stmt.setDate(6, null);
			}
			
			java.util.Date wedEnd = rtbean.getWedEnd();
			if(wedEnd!=null)
			{
				long temp = wedEnd.getTime();
				stmt.setDate(7, new java.sql.Date(temp));
			}
			else
			{
				stmt.setDate(7, null);
			}
			
			java.util.Date thuStart = rtbean.getThuStart();
			if(thuStart!=null)
			{
				long temp = thuStart.getTime();
				stmt.setDate(8, new java.sql.Date(temp));
			}
			else
			{
				stmt.setDate(8, null);
			}
			
			java.util.Date thuEnd = rtbean.getThuEnd();
			if(thuEnd!=null)
			{
				long temp = thuEnd.getTime();
				stmt.setDate(9, new java.sql.Date(temp));
			}
			else
			{
				stmt.setDate(9, null);
			}
			
			java.util.Date friStart = rtbean.getFriStart();
			if(friStart!=null)
			{
				long temp = friStart.getTime();
				stmt.setDate(10, new java.sql.Date(temp));
			}
			else
			{
				stmt.setDate(10, null);
			}
			
			java.util.Date friEnd = rtbean.getFriEnd();
			if(friEnd!=null)
			{
				long temp = friEnd.getTime();
				stmt.setDate(11, new java.sql.Date(temp));
			}
			else
			{
				stmt.setDate(11, null);
			}
			
			java.util.Date satStart = rtbean.getSatStart();
			if(satStart!=null)
			{
				long temp = satStart.getTime();
				stmt.setDate(12, new java.sql.Date(temp));
			}
			else
			{
				stmt.setDate(12, null);
			}
			
			java.util.Date satEnd = rtbean.getSatEnd();
			if(satEnd!=null)
			{
				long temp = satEnd.getTime();
				stmt.setDate(13, new java.sql.Date(temp));
			}
			else
			{
				stmt.setDate(13, null);
			}
			
			java.util.Date sunStart = rtbean.getSunStart();
			if(sunStart!=null)
			{
				long temp = sunStart.getTime();
				stmt.setDate(14, new java.sql.Date(temp));
			}
			else
			{
				stmt.setDate(14, null);
			}
			
			java.util.Date sunEnd = rtbean.getSunEnd();
			if(sunEnd!=null)
			{
				long temp = sunEnd.getTime();
				stmt.setDate(15, new java.sql.Date(temp));
			}
			else
			{
				stmt.setDate(15, null);
			}
			
			int i = stmt.executeUpdate();
			if(i==1)
			{
				System.out.println("Update successfully!");
				return rtbean;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public static final String DELETE = "delete from RentalTime where storeId=?";
	@Override
	public boolean delete(Integer storeId) 
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			stmt = conn.prepareStatement(DELETE);
			stmt.setInt(1, storeId);
			int i = stmt.executeUpdate();
			if(i==1)
			{
				System.out.println("Delete Successful!");
				return true;
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
		}
		return false;
	}
	public static void main(String[] args) 
	{
		RentalTimeDAOjdbc dao = new RentalTimeDAOjdbc();
		RentalTimeBean rtbean = new RentalTimeBean();
		//List<RentalTimeBean> beans =dao.select();
		//select
		//File f = null;
		//FileInputStream fis = null;
		try {
			rtbean.setStoreId(2);
			rtbean.setMonStart(RentalTimeBean.convertDate("11:30"));
			rtbean.setMonEnd(RentalTimeBean.convertDate("22:30"));
			rtbean.setTueStart(RentalTimeBean.convertDate("11:30"));
			rtbean.setTueEnd(RentalTimeBean.convertDate("22:30"));
			rtbean.setWedStart(RentalTimeBean.convertDate("11:30"));
			rtbean.setWedEnd(RentalTimeBean.convertDate("22:30"));
			rtbean.setThuStart(RentalTimeBean.convertDate("11:30"));
			rtbean.setThuEnd(RentalTimeBean.convertDate("22:30"));
			rtbean.setFriStart(RentalTimeBean.convertDate("11:30"));
			rtbean.setFriEnd(RentalTimeBean.convertDate("22:30"));
			rtbean.setSatStart(RentalTimeBean.convertDate("10:30"));
			rtbean.setSatEnd(RentalTimeBean.convertDate("19:30"));
			rtbean.setSunStart(RentalTimeBean.convertDate("10:30"));
			rtbean.setSunEnd(RentalTimeBean.convertDate("19:30"));
			dao.insert(rtbean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
