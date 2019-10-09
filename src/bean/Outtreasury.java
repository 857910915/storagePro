package bean;
/**
 * 出库类
 * @author 陈世杰
 * @pramati oid 出库编号
 * @pramati wid入库编号
 * @pramati userid 操作员
 * @pramati perid 取货人ID
 * @pramati onumber 取货数量
 * @pramati otime 取货时间
 *
 */
public class Outtreasury {
	
	private int oid;
	private int wid;
	private int userid;
	private int perid;
	private int proid;
	private int onumber;
	private String otime;
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public int getWid() {
		return wid;
	}
	public void setWid(int wid) {
		this.wid = wid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getPerid() {
		return perid;
	}
	public void setPerid(int perid) {
		this.perid = perid;
	}
	public int getProid() {
		return proid;
	}
	public void setProid(int proid) {
		this.proid = proid;
	}
	public int getOnumber() {
		return onumber;
	}
	public void setOnumber(int onumber) {
		this.onumber = onumber;
	}
	public String getOtime() {
		return otime;
	}
	public void setOtime(String otime) {
		this.otime = otime;
	}
	public Outtreasury() {
		// TODO Auto-generated constructor stub
	}
}
