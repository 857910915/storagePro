package servlet;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.alibaba.fastjson.JSON;

import bean.Person;
import service.PersonService;

/**
 * Servlet implementation class PersonServlet
 * @author 陈世杰
 */
@WebServlet(name = "PersonServlet.do", urlPatterns = { "/PersonServlet.do" })
@MultipartConfig
public class PersonServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		String dowhat=req.getParameter("dowhat");	
		System.out.println(dowhat);
		PersonService service=new PersonService();
		if ("addper".equals(dowhat)) {
			//获取文件，二进制数据  name=img
			Part part=req.getPart("perimg");
			String path=req.getServletContext().getRealPath("img");
			String imgpath=service.uploadImg(part, path);
			String perimg=null;
			if (imgpath==null) {
				resp.getWriter().print("文件格式或者大小不正确");
				System.out.println("文件格式或者大小不正确");
				return;			
			}else {
				perimg=imgpath;
			}
			String pername=req.getParameter("pername");
			int perage=Integer.valueOf(req.getParameter("perage"));
			String persex=req.getParameter("persex");
			String depart=req.getParameter("depart");
			int perphone=Integer.valueOf(req.getParameter("perphone"));
			String peremail=req.getParameter("peremail");
			Person per=new Person();
			per.setPerimg(perimg);
			per.setPername(pername);
			per.setPerage(perage);
			per.setPersex(persex);
			per.setDepart(depart);
			per.setPerphone(perphone);
			per.setPeremail(peremail);
			service.addPerson(per);
			resp.sendRedirect("person.html");
		}else if ("showper".equals(dowhat)) {
			String page=req.getParameter("page");
			List<Person>perList=service.getPersons(Integer.valueOf(page));
			String json=JSON.toJSONString(perList);
			resp.getWriter().print(json);
		}else if ("editper".equals(dowhat)) {
			//获取文件，二进制数据  name=img
			Part part=req.getPart("perimg1");
			String path=req.getServletContext().getRealPath("img");
			String imgpath=service.uploadImg(part, path);
			String perimg=null;
			if (imgpath==null) {
				System.out.println("文件格式或者大小不正确");
				return;			
			}else {
				perimg=imgpath;
			}
			int perid=Integer.valueOf(req.getParameter("perid1"));
			String pername=req.getParameter("pername1");
			int perage=Integer.valueOf(req.getParameter("perage1"));
			String persex=req.getParameter("persex1");
			String depart=req.getParameter("depart1");
			int perphone=Integer.valueOf(req.getParameter("perphone1"));
			String peremail=req.getParameter("peremail1");			
			Person per=new Person();
			per.setPerid(perid);
			per.setPerimg(perimg);
			per.setPername(pername);
			per.setPerage(perage);
			per.setPersex(persex);
			per.setDepart(depart);
			per.setPerphone(perphone);
			per.setPeremail(peremail);
			service.updatePerson(per);
			resp.sendRedirect("person.html");
		}else if ("getone".equals(dowhat)) {
			String perid=req.getParameter("perid");
			System.out.println("id==="+perid);
			if (perid!=null) {
				Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
				boolean r=pattern.matcher(perid).matches();  
				if (r) {
					int perid1=Integer.valueOf(perid);
					Person person=service.getList(perid1);
					if (person!=null) {
						String json=JSON.toJSONString(person);
						resp.getWriter().print(json);
					}else {
						resp.getWriter().print("1001");
					}
				}
			}
		}else if ("deleteper".equals(dowhat)) {
			//int perid=Integer.valueOf(req.getParameter("perid1"));
			String perid=req.getParameter("perid");
			if (perid==null) {
				return;
			}			
			service.deletePerson(Integer.valueOf(perid));
			resp.sendRedirect("person.html");
		}
	}

}
