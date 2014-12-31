package model.StoreInformationImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class StoreInformationImageDAOjdbc implements StoreInformationImageDAO
{
	private static final String URL = "jdbc:sqlserver://localhost:1433;database=boardgames";
	private static final String USERNAME = "sa";
	private static final String PASSWORD = "123456";
	private static final String SELECT_BY_ID = "select * from StoreInformationImage where storeImageId = ?";
	@Override
	public StoreInformationImageBean select(Integer storeImageId) 
	{
		StoreInformationImageBean result = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		
		try {
			conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			stmt = conn.prepareStatement(SELECT_BY_ID);
			stmt.setInt(1, storeImageId);
			rset = stmt.executeQuery();
			if(rset.next())
			{
				result = new StoreInformationImageBean();
				result.setStoreId(rset.getInt("StoreId"));
				result.setStoreImageId(rset.getInt("StoreImageId"));
				result.setBoardGameHelp(rset.getString("BoardGameHelp"));
				result.setImgFileName(rset.getString("ImgFileName"));
				result.setAreaImage(rset.getBytes("AreaImage"));
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
		return null;
	}
	private static final String SELECT_ALL = "select * from StoreInformationImage";
	@Override
	public List<StoreInformationImageBean> select() 
	{
		List<StoreInformationImageBean> result = null;
		Connection conn= null;
		PreparedStatement stmt =null;
		ResultSet rset = null;
		
		try {
			conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			stmt = conn.prepareStatement(SELECT_ALL);
			rset = stmt.executeQuery();
			
			result = new ArrayList<StoreInformationImageBean>();
			while(rset.next())
			{
				StoreInformationImageBean sibean = new StoreInformationImageBean();
				sibean.setStoreImageId(rset.getInt("StoreImageId"));
				sibean.setStoreId(rset.getInt("StoreId"));
				sibean.setBoardGameHelp(rset.getString("BoardGameHelp"));
				sibean.setImgFileName(rset.getString("imgFileName"));
				sibean.setAreaImage(rset.getBytes("AreaImage"));
				result.add(sibean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally
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
	
	public static final String UPDATE = "update StoreInformationImage set storeId=?,boardGameHelp=?,"
			+ "imgFileName=?,areaImage=? where storeImageId=?";
	@Override
	public StoreInformationImageBean update(StoreInformationImageBean sibean,
			InputStream is, long size,String filename) 
	{
		Connection conn = null;
		PreparedStatement stmt =null;
		StoreInformationImageBean result = null;
		
		try {
			conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			stmt = conn.prepareStatement(UPDATE);
			stmt.setInt(1, sibean.getStoreId());
			stmt.setString(2, sibean.getBoardGameHelp());
			if(filename!=null)
			{
				stmt.setString(3, filename);
			}
			else
			{
				stmt.setString(3, null);
			}
			
			if (is != null && size != 0) {
				stmt.setBinaryStream(4, is,size);
			} else {
				stmt.setBinaryStream(4, null, 0);
			}
			stmt.setInt(5, sibean.getStoreImageId());
			
			int i =stmt.executeUpdate();
			if(i==1)
			{
				System.out.println("Update successfully!");
				return sibean;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	public static final String insert = "insert into StoreInformationImage (storeId,storeImageId,boardGameHelp,imgFileName,areaImage)"
			+ " values (?,?,?,?,?)";
	@Override
	public StoreInformationImageBean insert(StoreInformationImageBean sibean,
			InputStream is, long size,String filename) 
	{
		Connection conn= null;
		PreparedStatement stmt = null;
		StoreInformationImageBean result = null;
		
		try {
			conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			stmt = conn.prepareStatement(insert);
			
			result = new StoreInformationImageBean();
			stmt.setInt(1, sibean.getStoreId());
			stmt.setInt(2, sibean.getStoreImageId());
			stmt.setString(3, sibean.getBoardGameHelp());
			if(filename!=null)
			{
				stmt.setString(4, filename);
			}
			else
			{
				stmt.setString(4, null);
			}
			
			if (is != null && size != 0) {
				stmt.setBinaryStream(5, is,size);
			} else {
				stmt.setBinaryStream(5, null, 0);
			}
			
			int i = stmt.executeUpdate();
			if(i==1)
			{
				System.out.println("Insert successfully!");
				return sibean;
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
		return result;
	}
	public static final String DELETE = "delete from StoreInformationImage where storeImageId = ?";
	@Override
	public boolean delete(Integer storeImageId) 
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			stmt = conn.prepareStatement(DELETE);
			stmt.setInt(1, storeImageId);
			int i =stmt.executeUpdate();
			if(i==1)
			{
				System.out.println("Delete successfully!");
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
		return false;
	}
	public static void main(String[] args)
	{
		StoreInformationImageDAOjdbc dao= new StoreInformationImageDAOjdbc();
		StoreInformationImageBean sibean = new StoreInformationImageBean();
		
		//insert
//		sibean.setStoreId(3);
//		sibean.setStoreImageId(6);
//		sibean.setBoardGameHelp("but it's seems a little weird");
//		String filename="test02";
//		sibean.setImgFileName(filename);
//		File f = new File("img/0003002.jpg");
//		long size = 0;
//		InputStream is = null;
//		try {
//		size = f.length();
//		is = new FileInputStream(f);
//		} catch (FileNotFoundException e) {
//		e.printStackTrace();
//		}
//		dao.insert(sibean, is, size, filename);
			
		//select all
//		List<StoreInformationImageBean> sibean = dao.select();
//		System.out.println(sibean);
		
		//select by id
//		System.out.println(dao.select(2));
		
		//update
		File f = null;
		FileInputStream fis = null;
		
		try {
			f = new File("img/0000.jpg");
			fis = new FileInputStream(f);
			long length = f.length();
			
			sibean.setStoreImageId(3);
			sibean.setStoreId(2);
			sibean.setBoardGameHelp("Just fixing...");
			String filename="test.jpg";
			sibean.setImgFileName(filename);
			System.out.println(dao.update(sibean,fis,length,filename));
			sibean = dao.select(3);
			System.out.println(sibean);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}	

