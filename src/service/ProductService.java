package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Product;
import conn.MySQLContent;
import face.ProductFace;

/**
 * 
 * @author 陈世杰
 *
 */
public class ProductService implements ProductFace{

	//查询商品信息
	@Override
	public List<Product> getProducts(int page) {
		List<Product>proList=new ArrayList<Product>();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;		
		try {
			if (page==0) {
				String sql="select * from product";
				con=MySQLContent.getConnect();
				ps=con.prepareStatement(sql);
			}else {
				String sql="select * from product order by prosort DESC limit ?,?";
				con=MySQLContent.getConnect();
				ps=con.prepareStatement(sql);
				ps.setInt(1, (page-1)*5);
				ps.setInt(2, 5);
			}			
			rs=ps.executeQuery();
			while (rs.next()) {
				Product pro=new Product();
				pro.setProid(rs.getInt("proid"));
				pro.setProimg(rs.getString("proimg"));
				pro.setProname(rs.getString("proname"));
				pro.setProcompany(rs.getString("procompany"));
				pro.setProtype(rs.getString("protype"));
				pro.setProprice(rs.getDouble("proprice"));
				pro.setProstyate(rs.getInt("prostyate"));
				pro.setProtime(rs.getString("protime"));
				pro.setProsort(rs.getInt("prosort"));
				proList.add(pro);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MySQLContent.closeAll(rs, ps, con);
		}
		return proList;
	}

	//添加商品信息
	@Override
	public int addProduct(Product product) {
		int n=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;		
		try {
			String sql="insert into product (proimg,proname,procompany,protype,proprice,prostyate,protime,prosort) values (?,?,?,?,?,?,?,?)";
			con=MySQLContent.getConnect();
			ps=con.prepareStatement(sql);
			ps.setString(1, product.getProimg());
			ps.setString(2, product.getProname());
			ps.setString(3, product.getProcompany());
			ps.setString(4, product.getProtype());
			ps.setDouble(5, product.getProprice());
			ps.setInt(6, product.getProstyate());
			ps.setString(7, product.getProtime());
			ps.setInt(8, product.getProsort());
			List<Object>list=new ArrayList<Object>();
			list.add(product.getProimg());
			list.add(product.getProname());
			list.add(product.getProcompany());
			list.add(product.getProtype());
			list.add(product.getProprice());
			list.add(product.getProstyate());
			list.add(product.getProtime());
			list.add(product.getProsort());
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

	//删除商品信息
	@Override
	public boolean deleteProduct(int proid) {
		int n=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			String sql="delete from product where proid=?";
			con=MySQLContent.getConnect();
			ps=con.prepareStatement(sql);
			ps.setInt(1, proid);
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
	//改变商品的状态
	public boolean updateStyale(int prostyate,int proid) {
		int n=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;			
		try {
			String sql="update product set prostyate=? where proid=?";
			con=MySQLContent.getConnect();
			ps=con.prepareStatement(sql);
			ps.setInt(1, prostyate);
			ps.setInt(2, proid);
			System.out.println(sql);
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
	
	
	
	//修改商品信息
	@Override
	public boolean updateProduct(Product product) {
		int n=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;		
		try {
			String sql="update product set proimg=?,proname=?,procompany=?,protype=?,proprice=?,prostyate=?,protime=?,prosort=? where proid=?";
			con=MySQLContent.getConnect();
			ps=con.prepareStatement(sql);
			ps.setString(1, product.getProimg());
			ps.setString(2, product.getProname());
			ps.setString(3, product.getProcompany());
			ps.setString(4, product.getProtype());
			ps.setDouble(5, product.getProprice());
			ps.setInt(6, product.getProstyate());
			ps.setString(7, product.getProtime());
			ps.setInt(8, product.getProsort());
			ps.setInt(9, product.getProid());
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

	//通过id查找
	@Override
	public Product getProductByProid(int proid) {
		Product pro=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;

		try {
			String sql="select * from product where proid=?";
			con=MySQLContent.getConnect();
			ps=con.prepareStatement(sql);
			ps.setInt(1, proid);
			rs=ps.executeQuery();
			while (rs.next()) {
				pro=new Product();
				pro.setProid(rs.getInt("proid"));
				pro.setProimg(rs.getString("proimg"));
				pro.setProname(rs.getString("proname"));
				pro.setProcompany(rs.getString("procompany"));
				pro.setProtype(rs.getString("protype"));
				pro.setProprice(rs.getDouble("proprice"));
				pro.setProstyate(rs.getInt("prostyate"));
				pro.setProtime(rs.getString("protime"));
				pro.setProsort(rs.getInt("prosort"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MySQLContent.closeAll(rs, ps, con);
		}

		return pro;	
	}

	//多条件查询
	@Override
	public List<Product> getProInfoByMany(int page,  String protype,
			int proprice, int prostyate,  int prosort, String keywords) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;		
		List<Product>proList=new ArrayList<Product>();
		try {
			String sql="select * from product where 1=1 ";
			if (proprice!=0) {		
				sql+="and proprice between "+(proprice-1)*100+" and "+((proprice-1)*100+99);
			}
			if (protype.length()>0) {
				sql+=" and protype='"+protype+"' ";
			}
			if (prostyate!=0) {
					sql+=" and prostyate="+prostyate;
			}
			if (prosort!=2) {
					sql+=" and prosort="+prosort;
			}
			if (keywords.length()>0) {
				sql+=" and proname like \'%"+keywords+"%\' or procompany like \'%"+keywords+"%\' ";
			}
			sql+=" limit "+(page-1)*5+",5";
			//System.out.println(sql);
			con=MySQLContent.getConnect();
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while (rs.next()) {
				Product pro=new Product();
				pro.setProid(rs.getInt("proid"));
				pro.setProimg(rs.getString("proimg"));
				pro.setProname(rs.getString("proname"));
				pro.setProcompany(rs.getString("procompany"));
				pro.setProtype(rs.getString("protype"));
				pro.setProprice(rs.getDouble("proprice"));
				pro.setProstyate(rs.getInt("prostyate"));
				pro.setProtime(rs.getString("protime"));
				pro.setProsort(rs.getInt("prosort"));
				proList.add(pro);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MySQLContent.closeAll(rs, ps, con);
		}
		return proList;
	}

}
