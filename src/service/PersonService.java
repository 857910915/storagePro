package service;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Part;

import bean.Person;
import conn.MySQLContent;
import face.PersonFace;
/**
 * 
 * @author 陈世杰
 *
 */
public class PersonService implements PersonFace{
	//查询用户信息
	@Override
	public List<Person> getPersons(int page) {
		List<Person>perList=new ArrayList<Person>();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;			
		try {
			
			if (page==0) {
				String sql="select * from person";
				con=MySQLContent.getConnect();
				ps=con.prepareStatement(sql);				
			}else {
				String sql="select * from person limit ?,?";
				con=MySQLContent.getConnect();
				ps=con.prepareStatement(sql);
				ps.setInt(1, (page-1)*5);
				ps.setInt(2, 5);
			}
			 
			
			rs=ps.executeQuery();
			while (rs.next()) {
				Person per=new Person();
				per.setPerid(rs.getInt("perid"));
				per.setPername(rs.getString("pername"));
				per.setPerimg(rs.getString("perimg"));
				per.setPerage(rs.getInt("perage"));
				per.setPersex(rs.getString("persex"));
				per.setDepart(rs.getString("depart"));
				per.setPerphone(rs.getInt("perphone"));
				per.setPeremail(rs.getString("peremail"));
				perList.add(per);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MySQLContent.closeAll(rs, ps, con);
		}
		return perList;
	}
	
	//查询信息
	public Person getList(int perid) {
		Person per=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;			
		try {
			String sql="select * from person where perid=?";
			con=MySQLContent.getConnect();
			ps=con.prepareStatement(sql);
			ps.setInt(1, perid);
			rs=ps.executeQuery();
			while (rs.next()) {
				per=new Person();
				per.setPerid(rs.getInt("perid"));
				per.setPername(rs.getString("pername"));
				per.setPerimg(rs.getString("perimg"));
				per.setPerage(rs.getInt("perage"));
				per.setPersex(rs.getString("persex"));
				per.setDepart(rs.getString("depart"));
				per.setPerphone(rs.getInt("perphone"));
				per.setPeremail(rs.getString("peremail"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MySQLContent.closeAll(rs, ps, con);
		}
		return per;	
	}
	
	
	//添加用户信息
	@Override
	public int addPerson(Person person) {
		int n=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			String sql="insert into person (perimg,pername,perage,depart,persex,perphone,peremail) values (?,?,?,?,?,?,?)";
			con=MySQLContent.getConnect();
			ps=con.prepareStatement(sql);
			ps.setString(1, person.getPerimg());
			ps.setString(2, person.getPername());
			ps.setInt(3, person.getPerage());
			ps.setString(4, person.getDepart());
			ps.setString(5, person.getPersex());
			ps.setInt(6, person.getPerphone());
			ps.setString(7, person.getPeremail());
			List<Object>list=new ArrayList<Object>();
			list.add(person.getPerimg());
			list.add(person.getPername());
			list.add(person.getPerage());
			list.add(person.getDepart());
			list.add(person.getPersex());
			list.add(person.getPerphone());
			list.add(person.getPeremail());
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

	//删除用户信息
	@Override
	public boolean deletePerson(int perid) {
		int n=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;	
		try {
			String sql="delete from person where perid=?";
			con=MySQLContent.getConnect();
			ps=con.prepareStatement(sql);
			ps.setInt(1, perid);
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

	//修改用户信息
	@Override
	public boolean updatePerson(Person person) {
		int n=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			String sql="update person set perimg=?,pername=?,perage=?,depart=?,persex=?,perphone=?,peremail=? where perid=?";
			con=MySQLContent.getConnect();
			ps=con.prepareStatement(sql);
			ps.setString(1, person.getPerimg());
			ps.setString(2, person.getPername());
			ps.setInt(3, person.getPerage());
			ps.setString(4, person.getDepart());
			ps.setString(5, person.getPersex());
			ps.setInt(6, person.getPerphone());
			ps.setString(7, person.getPeremail());
			ps.setInt(8, person.getPerid());
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
	
	//上传文件
	public String uploadImg(Part part,String path) {
		//2.3通过文件的content-type，判断文件的类型
		String type=part.getContentType();
		if (!(type.contains("png")||type.contains("pneg")||type.contains("jpg")||type.contains("jpeg")||type.contains("gif"))) {
			//返回前端文件必须是指定格式		
			return null;
		}
		//2.4判断文件大小
		if (part.getSize()>1000*1024) {
			return null;
		}
		//2.5将文件进行拼接写入到指定文件
		//处理字符串，获取上传的文件名
		String content=part.getHeader("content-disposition");
		String filename=content.substring(content.lastIndexOf("=\"")+2,content.lastIndexOf("\""));	
		String newFile="img/"+filename;		
		File file=new File(path);
		if (!file.exists()) {
			file.mkdir();
		}
		filename=path+File.separatorChar+filename;
		//2.6将文件写到指定位置
		try {
			part.write(filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newFile;
	}
}
