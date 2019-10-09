package bean;
/**
 * 入库类
 * @author 陈世杰
 * @pramati wid 入库编号
 * @pramati proid 入库商品编号
 * @pramati wnumber 入库商品的数量
 * @pramati wtime 入库时间
 * @pramati lasttime 保质期
 * @pramati uid 操作的管理员
 */
public class Warehous {
	
	private int wid;
	private int proid;
	private int wnumber;
	private String wtime;
	private String lasttime;
	private int uid;
	public int getWid() {
		return wid;
	}
	public void setWid(int wid) {
		this.wid = wid;
	}
	public int getProid() {
		return proid;
	}
	public void setProid(int proid) {
		this.proid = proid;
	}
	public int getWnumber() {
		return wnumber;
	}
	public void setWnumber(int wnumber) {
		this.wnumber = wnumber;
	}
	public String getWtime() {
		return wtime;
	}
	public void setWtime(String wtime) {
		this.wtime = wtime;
	}
	public String getLasttime() {
		return lasttime;
	}
	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public Warehous() {
		// TODO Auto-generated constructor stub
	}
}
