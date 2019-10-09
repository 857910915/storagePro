package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Stock;
import conn.MySQLContent;

/**
 * 
 * @author 陈世杰
 *
 */

public class StockService {
	//获取视图的信息
	public List<Stock> getStocks(int page) {
		List<Stock>stoList=new ArrayList<Stock>();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;				
		try {
			String sql="select * from stock1 limit ?,5";
			con=MySQLContent.getConnect();
			ps=con.prepareStatement(sql);
			ps.setInt(1, (page-1)*5);
			rs=ps.executeQuery();
			while (rs.next()) {
				Stock sto=new Stock();
				sto.setProname(rs.getString("proname"));
				sto.setNumber(rs.getInt("number"));
				sto.setProid(rs.getInt("proid"));
				stoList.add(sto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MySQLContent.closeAll(rs, ps, con);
		}
		return stoList;		
	}
	public Stock getStockByProid(int proid) {
		Stock sto=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			String sql="select * from stock where proid=?";
			con=MySQLContent.getConnect();
			ps=con.prepareStatement(sql);
			ps.setInt(1, proid);
			rs=ps.executeQuery();
			while (rs.next()) {
				sto=new Stock();
				sto.setProname(rs.getString("proname"));
				sto.setNumber(rs.getInt("number"));
				sto.setProid(rs.getInt("proid"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MySQLContent.closeAll(rs, ps, con);
		}
		return sto;
	}
	
}
