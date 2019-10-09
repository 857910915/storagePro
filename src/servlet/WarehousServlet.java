package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import bean.Product;
import bean.Warehous;
import service.ProductService;
import service.WarehousService;

/**
 * Servlet implementation class WarehousServlet
 * @author 陈世杰
 */
@WebServlet(name = "WarehousServlet.do", urlPatterns = { "/WarehousServlet.do" })

public class WarehousServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		String dowhat=req.getParameter("dowhat");	
		System.out.println(dowhat);
		WarehousService service=new WarehousService();
		if ("addware".equals(dowhat)) {
			int proid=Integer.valueOf(req.getParameter("proname"));			
			int wnumber=Integer.valueOf(req.getParameter("wnumber"));
			String wtime=req.getParameter("wtime");
			String lasttime=req.getParameter("lasttime");
			int uid=Integer.valueOf(req.getParameter("username"));
			Warehous w=new Warehous();
			w.setProid(proid);
			w.setWnumber(wnumber);
			w.setWtime(wtime);
			w.setLasttime(lasttime);
			w.setUid(uid);
			service.addWareInfo(w);
			//改变product的状态
			ProductService service2=new ProductService();
			Product pro=service2.getProductByProid(proid);
			//System.out.println(pro.getProstyate());
			if(pro.getProstyate()==2){
				service2.updateStyale(1, proid);			
			}
			resp.sendRedirect("addstock.html");
		}else if ("showware".equals(dowhat)) {
			String page=req.getParameter("page");
			List<Warehous>wList=service.getWareInfo(Integer.valueOf(page));
			String json=JSON.toJSONString(wList);
			resp.getWriter().print(json);
		}else if ("editware".equals(dowhat)) {
			int wid=Integer.valueOf(req.getParameter("wid1"));
			int proid=Integer.valueOf(req.getParameter("proid1"));
			int wnumber=Integer.valueOf(req.getParameter("wnumber1"));
			String wtime=req.getParameter("wtime1");
			String lasttime=req.getParameter("lasttime1");
			int uid=Integer.valueOf(req.getParameter("uid1"));
			Warehous w=new Warehous();
			w.setWid(wid);
			w.setProid(proid);
			w.setWnumber(wnumber);
			w.setWtime(wtime);
			w.setLasttime(lasttime);
			w.setUid(uid);
			service.updateWareInfo(w);
			resp.sendRedirect("addstock.html");
		}else if ("getone".equals(dowhat)) {
			int wid=Integer.valueOf(req.getParameter("wid"));
			Warehous w=service.getWareInfoByWid(wid);
			if (w!=null) {
				String json=JSON.toJSONString(w);
				resp.getWriter().print(json);
			}else {
				resp.getWriter().print("1001");
			}
		}else if ("deleteware".equals(dowhat)) {
			String wid=req.getParameter("wid");
			if (wid==null) {
				return;
			}			
			service.delWareInfo(Integer.valueOf(wid));
			resp.sendRedirect("addstock.html");
		}
	}

}
