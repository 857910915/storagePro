package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Warehous;
import conn.MySQLContent;
/**
 * 
 * @author 陈世杰
 *
 */
public class WarehousService {
	//添加入库记录
	public int addWareInfo(Warehous w) {
		int n=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;		
		try {
			String sql="insert into warehous (proid,wnumber,wtime,lasttime,uid) values (?,?,?,?,?)";
			con=MySQLContent.getConnect();
			ps=con.prepareStatement(sql);
			ps.setInt(1, w.getProid());
			ps.setInt(2, w.getWnumber());
			ps.setString(3, w.getWtime());
			ps.setString(4, w.getLasttime());
			ps.setInt(5, w.getUid());
			List<Object>list=new ArrayList<Object>();
			list.add(w.getProid());
			list.add(w.getWnumber());
			list.add(w.getWtime());
			list.add(w.getLasttime());
			list.add(w.getUid());
			n=ps.executeUpdate();
			if (n>0) {
				System.out.println("添加成功！");
			}else {
				System.out.println("添加失败");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MySQLContent.closeAll(rs, ps, con);
		}
		return n;
	}

	//查询记录
	public List<Warehous> getWareInfo(int page) {
		List<Warehous>wList=new ArrayList<Warehous>();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;			
		try {
			String sql="select * from warehous limit ?,5";
			con=MySQLContent.getConnect();
			ps=con.prepareStatement(sql);
			ps.setInt(1, (page-1)*5);
			rs=ps.executeQuery();
			while (rs.next()) {
				Warehous w=new Warehous();
				w.setWid(rs.getInt("wid"));
				w.setProid(rs.getInt("proid"));
				w.setWnumber(rs.getInt("wnumber"));
				w.setWtime(rs.getString("wtime"));
				w.setLasttime(rs.getString("lasttime"));
				w.setUid(rs.getInt("uid"));
				wList.add(w);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MySQLContent.closeAll(rs, ps, con);
		}
		return wList;
	}

	//通过id查找记录
	public Warehous getWareInfoByWid(int wid) {
		Warehous w=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;	
		try {
			String sql="select * from warehous where wid=?";
			con=MySQLContent.getConnect();
			ps=con.prepareStatement(sql);
			ps.setInt(1, wid);
			rs=ps.executeQuery();
			while (rs.next()) {
				w=new Warehous();
				w.setWid(rs.getInt("wid"));
				w.setProid(rs.getInt("proid"));
				w.setWnumber(rs.getInt("wnumber"));
				w.setWtime(rs.getString("wtime"));
				w.setLasttime(rs.getString("wtime"));
				w.setUid(rs.getInt("uid"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MySQLContent.closeAll(rs, ps, con);
		}

		return w;
	}

	//删除入库记录
	public boolean delWareInfo(int wid){
		int n=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;	
		try {
			String sql="delete from warehous where wid=?";
			con=MySQLContent.getConnect();
			ps=con.prepareStatement(sql);
			ps.setInt(1, wid);
			n=ps.executeUpdate();
			if (n>0) {
				System.out.println("删除成功");
				return true;
			}else {
				System.out.println("删除失败");
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MySQLContent.closeAll(rs, ps, con);
		}
		return false;	
	}

	//修改入库记录
	public boolean updateWareInfo(Warehous w){
		int n=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;		

		try {
			String sql="update warehous set proid=?,wnumber=?,wtime=?,lasttime=?,uid=? where wid=?";
			con=MySQLContent.getConnect();
			ps=con.prepareStatement(sql);
			ps.setInt(1, w.getProid());
			ps.setInt(2, w.getWnumber());
			ps.setString(3, w.getWtime());
			ps.setString(4, w.getLasttime());
			ps.setInt(5, w.getUid());
			ps.setInt(6, w.getWid());
			n=ps.executeUpdate();
			if (n>0) {
				System.out.println("修改成功！");
				return true;				
			}else {
				System.out.println("修改失败");
				return false;				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MySQLContent.closeAll(rs, ps, con);
		}
		return false;
	}
}
