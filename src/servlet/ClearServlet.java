package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ClearServlet
 * @author 陈世杰
 */
@WebServlet("/ClearServlet.do")
public class ClearServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		if (session!=null) {
			session.removeAttribute("username");
			session.invalidate();
		}
		resp.sendRedirect("login.html");
	}
}
