package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import bean.Outtreasury;
import bean.Product;
import bean.Stock;
import bean.Warehous;
import service.OutService;
import service.ProductService;
import service.StockService;

/**
 * Servlet implementation class OutServlet
 * @author 陈世杰
 */
@WebServlet(name = "OutServlet.do", urlPatterns = { "/OutServlet.do" })
public class OutServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		String dowhat=req.getParameter("dowhat");	
		System.out.println(dowhat);
		OutService service=new OutService();
		StockService service2=new StockService();
		if ("addout".equals(dowhat)) {
			//int wid=Integer.valueOf(req.getParameter("wid"));
			int uid=Integer.valueOf(req.getParameter("username"));
			int perid=Integer.valueOf(req.getParameter("pername"));
			int proid=Integer.valueOf(req.getParameter("proname"));
			int onumber=Integer.valueOf(req.getParameter("onumber"));			
			String otime=req.getParameter("otime");
			Stock sto=service2.getStockByProid(proid);
			int number=sto.getNumber();
			if (onumber>number) {
				return;
			}
			Outtreasury out=new Outtreasury();
			//out.setWid(wid);
			out.setUserid(uid);
			out.setPerid(perid);
			out.setProid(proid);
			out.setOnumber(onumber);			
			out.setOtime(otime);
			service.addOutInfo(out);
			resp.sendRedirect("outstock.html");
		}else if ("showout".equals(dowhat)) {
			String page=req.getParameter("page");
			List<Outtreasury>outList=service.getOutInfo(Integer.valueOf(page));
			String json=JSON.toJSONString(outList);
			resp.getWriter().print(json);
		}else if ("getone".equals(dowhat)) {
			int oid=Integer.valueOf(req.getParameter("oid"));
			Outtreasury out=service.getOutInfoByOid(oid);
			if (out!=null) {
				String json=JSON.toJSONString(out);
				resp.getWriter().print(json);
			}else {
				resp.getWriter().print("1001");
			}
		}else if ("editout".equals(dowhat)) {
			int oid=Integer.valueOf(req.getParameter("oid1"));
			//int wid=Integer.valueOf(req.getParameter("wid1"));
			int uid=Integer.valueOf(req.getParameter("uid1"));
			int perid=Integer.valueOf(req.getParameter("perid1"));
			int proid=Integer.valueOf(req.getParameter("proid1"));
			int onumber=Integer.valueOf(req.getParameter("onumber1"));
			String otime=req.getParameter("otime");
			Outtreasury out=new Outtreasury();
			out.setOid(oid);
			//out.setWid(wid);
			out.setUserid(uid);
			out.setPerid(perid);
			out.setProid(proid);
			out.setOnumber(onumber);
			out.setOtime(otime);
			service.editOutInfo(out);
			resp.sendRedirect("outstock.html");
		}else if ("deleteout".equals(dowhat)) {
			String oid=req.getParameter("oid");
			if (oid==null) {
				return;
			}			
			service.delOutInfo(Integer.valueOf(oid));
			resp.sendRedirect("outstock.html");
		}else if ("getnum".equals(dowhat)) {
			int proid=Integer.valueOf(req.getParameter("proname"));
			//System.out.println("proid--->"+proid);
			int onumber=Integer.valueOf(req.getParameter("onumber"));		
			Stock sto=service2.getStockByProid(proid);
			int number=sto.getNumber();
			//System.out.println(number+" >>>   "+onumber);
			if (onumber>number) {
				resp.getWriter().print("1001");
				return;
			}else {
				resp.getWriter().print("1000");
				if (onumber==number) {
					//改变produ的状态
					ProductService service1=new ProductService();
					Product pro=service1.getProductByProid(proid);
					//System.out.println(pro.getProstyate());
					if(pro.getProstyate()==1){
						service1.updateStyale(2, proid);			
					}
				}
			}
		}
	}

}
