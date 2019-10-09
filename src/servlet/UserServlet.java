package servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;

import bean.Person;
import bean.User;
import service.UserService;

/**
 * Servlet implementation class LoginServlet
 * @author 陈世杰
 */
@WebServlet("/UserServlet.do")
public class UserServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		//接受客户端信息
		String dowhat=req.getParameter("dowhat");
		UserService service=new UserService();
		System.out.println(dowhat);
		if ("login".equals(dowhat)) {
			//OutputStream out = resp.getOutputStream();
			String validationCode = req.getParameter("usercode");
			//System.out.println("code===>"+validationCode);
			HttpSession session = req.getSession();
			String validation_code = (String)session.getAttribute("code");
			//System.out.println(validation_code);
			String username=req.getParameter("username");
			//System.out.println("name==="+username);
			String userpwd=req.getParameter("userpwd");		
			//System.out.println(userpwd);
			User user=new User();
			user.setUsername(username);
			user.setUserpwd(userpwd);
			//处理数据			
			boolean r=service.getUserInfo(user);
			//System.out.println(r);
			if (r) {
				HttpSession session1=req.getSession(true);
				session1.setAttribute("username", username);
				session1.setMaxInactiveInterval(60*60*2);
				//resp.getWriter().print("1000");
				if(validationCode.equalsIgnoreCase(validation_code)){
					resp.getWriter().print("1000");
					
				}else{				
					resp.getWriter().print("1001");
					return;
				}
			}else {
				resp.getWriter().print("1002");
				return;
			}
		}else if ("editpwd".equals(dowhat)) {
			HttpSession session=req.getSession();
			if (session!=null) {
				String username=(String) session.getAttribute("username");
				String oldpwd=req.getParameter("mpass");
				String newpwd=req.getParameter("newpass");
				User user=new User();
				user.setUsername(username);
				user.setUserpwd(oldpwd);
				//String renewpass=req.getParameter("renewpass");
				if (service.getUserInfo(user)) {
					if(newpwd.equals(oldpwd)){
						resp.getWriter().print("1002");
					}else {
						service.editUserPwd(username, oldpwd, newpwd);
						resp.getWriter().print("1000");
					}
				}else {
					resp.getWriter().print("1001");
				}					
			}
		}else if ("getone".equals(dowhat)) {
			String userid=req.getParameter("userid");
			if (userid!=null) {
				Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
				boolean r=pattern.matcher(userid).matches();  
				if (r) {
					int perid1=Integer.valueOf(userid);
					User user=service.getList(perid1);
					if (user!=null) {
						String json=JSON.toJSONString(user);
						resp.getWriter().print(json);
					}else {
						resp.getWriter().print("1001");
					}
				}
			}
		}else if ("getuname".equals(dowhat)) {
			String username=req.getParameter("username");
			if (username.length()==0) {
				return;
			}
			if (service.getUserNmae(username)) {
				resp.getWriter().print("1000");
			}else {
				resp.getWriter().print("1001");
			}
		}else if ("getuser".equals(dowhat)) {
			HttpSession session=req.getSession();
			if (session!=null) {
				String username=(String) session.getAttribute("username");
				resp.getWriter().print(username);
			}else {
				resp.getWriter().print("1001");
			}

		}else if ("showuser".equals(dowhat)) {
			List<User>userList=service.getUserList();
			String json=JSON.toJSONString(userList);
			resp.getWriter().print(json);
		}
	}

}
