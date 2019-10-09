package bean;
/**
 * 库存类
 * @author 陈世杰
 * @pramati proid 商品编号	
 * @pramati proimg 商品图片
 * @pramati proname 商品名字
 * @pramati procompany 生产商
 * @pramati protype 商品类型
 * @pramati proprice 商品单价
 * @pramati prostyate 商品状态
 * @pramati protime 生产日期
 * @pramati prosort 商品排序
 * @pramati number 库存数量
 * @pramati time 保质期
 */
public class Stock extends Product{
	private int proid;
	private String proimg;
	private String proname;
	private String procompany;
	private String protype;
	private double proprice;
	private int prostyate;
	private String protime;
	private int prosort;
	private int number;
	private String time;
	public int getProid() {
		return proid;
	}
	public void setProid(int proid) {
		this.proid = proid;
	}
	public String getProimg() {
		return proimg;
	}
	public void setProimg(String proimg) {
		this.proimg = proimg;
	}
	public String getProname() {
		return proname;
	}
	public void setProname(String proname) {
		this.proname = proname;
	}
	public String getProcompany() {
		return procompany;
	}
	public void setProcompany(String procompany) {
		this.procompany = procompany;
	}
	public String getProtype() {
		return protype;
	}
	public void setProtype(String protype) {
		this.protype = protype;
	}
	public double getProprice() {
		return proprice;
	}
	public void setProprice(double proprice) {
		this.proprice = proprice;
	}
	public int getProstyate() {
		return prostyate;
	}
	public void setProstyate(int prostyate) {
		this.prostyate = prostyate;
	}
	public String getProtime() {
		return protime;
	}
	public void setProtime(String protime) {
		this.protime = protime;
	}
	public int getProsort() {
		return prosort;
	}
	public void setProsort(int prosort) {
		this.prosort = prosort;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Stock() {
		// TODO Auto-generated constructor stub
	}
}
