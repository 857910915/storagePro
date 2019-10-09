package service;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import bean.Person;
import bean.User;
import conn.MySQLContent;

/**
 * 该类对管理员进行操作
 * @author 陈世杰
 *
 */

public class UserService {
/*	String valid="0123456789zxcvbnmasdfghjklqwertyuiopZXCVBNMASDFGHJKLQWERTYUIOP";
	//随机生成验证码
	public String getStr() {
		StringBuffer str=new StringBuffer();
		ThreadLocalRandom random=ThreadLocalRandom.current();
		for (int i = 0; i < 4; i++) {
			int num=random.nextInt(0,valid.length());
			char c=valid.charAt(num);
			str.append(c);
		}
		return str.toString();
	}
	//通过验证码创建图片
	public void creatImg(String str) {
		//创建一个图片
		BufferedImage img=new BufferedImage(50, 30,BufferedImage.TYPE_INT_RGB);
		//获取图片上下文
		Graphics g=img.getGraphics();
		//设置背景颜色
		g.setColor(Color.white);
		g.fillRect(0, 0, 10, 10);
		//绘制文字
		g.setColor(Color.red);
		g.drawString(str, 10, 10);
	}
*/
	
	//获取所有的用户
	public List<User> getUserList() {
		List<User>userList=new ArrayList<User>();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;	
		
		try {
			String sql="select * from user";
			con=MySQLContent.getConnect();
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while (rs.next()) {
				User user=new User();
				user.setUserid(rs.getInt("userid"));
				user.setUsername(rs.getString("username"));
				user.setUserpwd(rs.getString("userpwd"));
				userList.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MySQLContent.closeAll(rs, ps, con);
		}
		return userList;		
	}
	
	//判断用户名密码是否正确
	public boolean getUserInfo(User user) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;	
		try {
			String sql="select * from user where username=? and userpwd=?";
			con=MySQLContent.getConnect();
			ps=con.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getUserpwd());
			//System.out.println(sql);
			//System.out.println(user.getUsername());
			rs=ps.executeQuery();
			while (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MySQLContent.closeAll(rs, ps, con);
		}
		return false;	
	}
	//修改密码
	public boolean editUserPwd(String username,String oldpwd,String newpwd) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;	
		try {
			String sql="update user set userpwd=? where username=? and userpwd=?";
			con=MySQLContent.getConnect();
			ps=con.prepareStatement(sql);
			ps.setString(1, newpwd);
			ps.setString(2, username);
			ps.setString(3, oldpwd);
			int n=ps.executeUpdate();
			if (n>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MySQLContent.closeAll(rs, ps, con);
		}
		
		return false;	
	}
	//通过id查找管理员
	public User getList(int userid) {
		User user=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;			
		try {
			String sql="select * from user where userid=?";
			con=MySQLContent.getConnect();
			ps=con.prepareStatement(sql);
			ps.setInt(1, userid);
			rs=ps.executeQuery();
			while (rs.next()) {
				user=new User();
				user.setUserid(rs.getInt("userid"));
				user.setUsername(rs.getString("username"));
				user.setUserpwd(rs.getString("userpwd"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MySQLContent.closeAll(rs, ps, con);
		}
		return user;	
	}
	
	//通过id查找管理员
		public User getuserqq(String username) {
			User user=null;
			Connection con=null;
			PreparedStatement ps=null;
			ResultSet rs=null;			
			try {
				String sql="select * from user where username=?";
				con=MySQLContent.getConnect();
				ps=con.prepareStatement(sql);
				ps.setString(1, username);
				rs=ps.executeQuery();
				while (rs.next()) {
					user=new User();
					user.setUserid(rs.getInt("userid"));
					user.setUsername(rs.getString("username"));
					user.setUserpwd(rs.getString("userpwd"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				MySQLContent.closeAll(rs, ps, con);
			}
			return user;	
		}
	
	public boolean getUserNmae(String username) {
		
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;			
		try {
			String sql="select * from user where username=?";
			con=MySQLContent.getConnect();
			ps=con.prepareStatement(sql);
			ps.setString(1, username);
			rs=ps.executeQuery();
			while (rs.next()) {
				return true;
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
