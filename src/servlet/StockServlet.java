package servlet;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import bean.Product;
import bean.Stock;
import service.StockService;

/**
 * Servlet implementation class StockServlet
 * @author 陈世杰
 */
@WebServlet(name = "StockServlet.do", urlPatterns = { "/StockServlet.do" })
public class StockServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		String dowhat=req.getParameter("dowhat");	
		System.out.println(dowhat);
		StockService service=new StockService();
		if ("showsto".equals(dowhat)) {
			String page=req.getParameter("page");
			List<Stock>stoList=service.getStocks(Integer.valueOf(page));
			String json=JSON.toJSONString(stoList);
			resp.getWriter().print(json);
		}else if ("getone".equals(dowhat)) {
			String proid=req.getParameter("proid");
			if (proid!=null) {
				Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
				boolean r=pattern.matcher(proid).matches();  
				if (r) {
					int proid1=Integer.valueOf(proid);
					Stock sto=service.getStockByProid(proid1);
					if (sto!=null) {
						String json=JSON.toJSONString(sto);
						resp.getWriter().print(json);
					}else {
						resp.getWriter().print("1001");
					}
				}
			}
		}
	}

}
