package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Outtreasury;
import conn.MySQLContent;

/**
 * 
 * @author 陈世杰
 *
 */
public class OutService {

	//添加出库记录
	public int addOutInfo(Outtreasury out) {
		int n=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;	
		
		try {
			String sql="insert into outtreasury (userid,perid,proid,onumber,otime) values (?,?,?,?,?)";
			con=MySQLContent.getConnect();
			ps=con.prepareStatement(sql);
			//ps.setInt(1, out.getWid());
			ps.setInt(1, out.getUserid());
			ps.setInt(2, out.getPerid());
			ps.setInt(3, out.getProid());
			ps.setInt(4, out.getOnumber());
			ps.setString(5, out.getOtime());
			List<Object>list=new ArrayList<Object>();
			//list.add(out.getWid());
			list.add(out.getUserid());
			list.add(out.getPerid());
			list.add(out.getProid());
			list.add(out.getOnumber());
			list.add(out.getOtime());
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
	
	//查询出库记录
	public List<Outtreasury> getOutInfo(int page) {
		List<Outtreasury>outList=new ArrayList<Outtreasury>();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;	
		
		try {
			String sql="select * from outtreasury limit ?,5";
			con=MySQLContent.getConnect();
			ps=con.prepareStatement(sql);
			ps.setInt(1, (page-1)*5);
			rs=ps.executeQuery();
			while (rs.next()) {
				Outtreasury out=new Outtreasury();
				out.setOid(rs.getInt("oid"));
				//out.setWid(rs.getInt("wid"));
				out.setUserid(rs.getInt("userid"));
				out.setPerid(rs.getInt("perid"));
				out.setProid(rs.getInt("proid"));
				out.setOnumber(rs.getInt("onumber"));
				out.setOtime(rs.getString("otime"));
				outList.add(out);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MySQLContent.closeAll(rs, ps, con);
		}
		
		return outList;	
	}
	
	//通过id查找记录
	public Outtreasury getOutInfoByOid(int oid) {
		Outtreasury out=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;			
		try {
			String sql="select * from outtreasury where oid=?";
			con=MySQLContent.getConnect();
			ps=con.prepareStatement(sql);
			ps.setInt(1, oid);
			rs=ps.executeQuery();
			while (rs.next()) {
				out=new Outtreasury();
				out.setOid(rs.getInt("oid"));
				//out.setWid(rs.getInt("wid"));
				out.setUserid(rs.getInt("userid"));
				out.setPerid(rs.getInt("perid"));
				out.setProid(rs.getInt("proid"));
				out.setOnumber(rs.getInt("onumber"));
				out.setOtime(rs.getString("otime"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MySQLContent.closeAll(rs, ps, con);
		}
		return out;
	}
	
	//删除出库记录
	public boolean delOutInfo(int oid) {
		int n=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;	
		
		try {
			String sql="delete from outtreasury where oid=?";
			con=MySQLContent.getConnect();
			ps=con.prepareStatement(sql);
			ps.setInt(1, oid);
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
	//修改出库记录
	public boolean editOutInfo(Outtreasury out) {
		int n=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;			
		try {
			String sql="update outtreasury set userid=?,perid=?,proid=?,onumber=?,otime=? where oid=?";
			con=MySQLContent.getConnect();
			ps=con.prepareStatement(sql);
			//ps.setInt(1, out.getWid());
			ps.setInt(1, out.getUserid());
			ps.setInt(2, out.getPerid());
			ps.setInt(3, out.getProid());
			ps.setInt(4, out.getOnumber());
			ps.setString(5, out.getOtime());
			ps.setInt(6, out.getOid());
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
