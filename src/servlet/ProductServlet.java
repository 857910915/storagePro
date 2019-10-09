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
import bean.Product;
import service.PersonService;
import service.ProductService;

/**
 * 商品类servlet
 * Servlet implementation class ProductServlet
 * @author 陈世杰
 */
@WebServlet(name = "ProductServlet.do", urlPatterns = { "/ProductServlet.do" })
@MultipartConfig
public class ProductServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		String dowhat=req.getParameter("dowhat");	
		System.out.println(dowhat);
		PersonService service1=new PersonService();
		ProductService service=new ProductService();
		if ("addpro".equals(dowhat)) {
			//获取文件，二进制数据  name=img
			Part part=req.getPart("proimg");
			String path=req.getServletContext().getRealPath("img");
			String imgpath=service1.uploadImg(part, path);
			String proimg=null;
			if (imgpath==null) {
				System.out.println("文件格式或者大小不正确");
				return;			
			}else {
				proimg=imgpath;
			}
			String proname=req.getParameter("proname");
			String procompany=req.getParameter("procompany");
			String protype=req.getParameter("protype");
			double proprice=Double.valueOf(req.getParameter("proprice"));
			int prostyate=Integer.valueOf(req.getParameter("prostyate"));			
			String protime=req.getParameter("protime");
			int prosort=Integer.valueOf(req.getParameter("prosort"));
			Product pro=new Product();
			pro.setProimg(proimg);
			pro.setProname(proname);
			pro.setProcompany(procompany);
			pro.setProtype(protype);
			pro.setProprice(proprice);
			pro.setProstyate(prostyate);
			pro.setProtime(protime);
			pro.setProsort(prosort);
			service.addProduct(pro);
			resp.sendRedirect("prolist.html");
		}else if ("showpro".equals(dowhat)) {
			String page=req.getParameter("page");
			List<Product>proList=service.getProducts(Integer.valueOf(page));
			String json=JSON.toJSONString(proList);
			resp.getWriter().print(json);
		}else if ("editpro".equals(dowhat)) {
			//获取文件，二进制数据  name=img
			Part part=req.getPart("proimg");
			String path=req.getServletContext().getRealPath("img");
			String imgpath=service1.uploadImg(part, path);
			String proimg=null;
			if (imgpath==null) {
				System.out.println("文件格式或者大小不正确");
				return;			
			}else {
				proimg=imgpath;
			}
			int proid=Integer.valueOf(req.getParameter("proid"));
			String proname=req.getParameter("proname");
			String procompany=req.getParameter("procompany");
			String protype=req.getParameter("protype");
			double proprice=Double.valueOf(req.getParameter("proprice"));
			int prostyate=Integer.valueOf(req.getParameter("prostyate"));			
			String protime=req.getParameter("protime");
			int prosort=Integer.valueOf(req.getParameter("prosort"));		
			Product pro=new Product();
			pro.setProid(proid);
			pro.setProimg(proimg);
			pro.setProname(proname);
			pro.setProcompany(procompany);
			pro.setProtype(protype);
			pro.setProprice(proprice);
			pro.setProstyate(prostyate);
			pro.setProtime(protime);
			pro.setProsort(prosort);
			service.updateProduct(pro);
			resp.sendRedirect("prolist.html");
		}else if ("getone".equals(dowhat)) {
			String proid=req.getParameter("proid");
			System.out.println("id==="+proid);
			if (proid!=null) {
				Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
				boolean r=pattern.matcher(proid).matches();  
				if (r) {
					int proid1=Integer.valueOf(proid);
					Product product=service.getProductByProid(proid1);
					if (product!=null) {
						String json=JSON.toJSONString(product);
						resp.getWriter().print(json);
					}else {
						resp.getWriter().print("1001");
					}
				}
			}
		}else if ("deletepro".equals(dowhat)) {
			String proid=req.getParameter("proid");
			if (proid==null) {
				return;
			}			
			service.deleteProduct(Integer.valueOf(proid));
			resp.sendRedirect("prolist.html");
		}else if ("getpro".equals(dowhat)) {
			int page=Integer.valueOf(req.getParameter("page"));
			int proprice=Integer.valueOf(req.getParameter("proprice"));
			//System.out.println(proprice);
			int prostyate=Integer.valueOf(req.getParameter("prostyate"));
			int prosort=Integer.valueOf(req.getParameter("prosort"));
			String protype=req.getParameter("protype");
			String keywords=req.getParameter("keywords");
			List<Product>proList=service.getProInfoByMany(page, protype, proprice, prostyate, prosort, keywords);
			String json=JSON.toJSONString(proList);
			resp.getWriter().print(json);
		}
	}

}
