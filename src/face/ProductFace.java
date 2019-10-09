package face;

import java.util.List;

import bean.Product;
/**
 * 
 * @author 陈世杰
 * 商品模块功能接口
 */
public interface ProductFace {
	//获取用户信息
	public List<Product> getProducts(int page);

	//添加用户信息
	public int addProduct(Product product);

	//删除用户信息
	public boolean deleteProduct(int proid);

	//修改用户信息
	public boolean updateProduct(Product product);

	//通过id查找
	public Product getProductByProid(int proid);
	
	//多条件查询
	//public List<Product> getProInfoByMany(int page,int proid,String proname,String procompany,String protype,int proprice,int prostyate,String protime,int prosort);

	public List<Product> getProInfoByMany(int page, String protype, int proprice, int prostyate, int prosort,String keywords);
}
