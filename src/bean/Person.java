package bean;
/**
 * 
 * @author 陈世杰
 * @pramati perid 人员id
 * @pramati perimg 人员头像
 * @pramati perage 人员年龄
 * @pramati persex 人员性别
 * @pramati depart 人员所在部门
 * @pramati perphone 人员联系电话
 * @pramati peremail 人员电子邮箱
 *
 */
public class Person {
	
	private int perid;
	private String perimg;
	private String pername;
	private int perage;
	private String depart;
	private String persex;
	private int perphone;
	private String peremail;
	public int getPerid() {
		return perid;
	}
	public void setPerid(int perid) {
		this.perid = perid;
	}
	public String getPerimg() {
		return perimg;
	}
	public void setPerimg(String perimg) {
		this.perimg = perimg;
	}
	public String getPername() {
		return pername;
	}
	public void setPername(String pername) {
		this.pername = pername;
	}
	public int getPerage() {
		return perage;
	}
	public void setPerage(int perage) {
		this.perage = perage;
	}
	public String getDepart() {
		return depart;
	}
	public void setDepart(String depart) {
		this.depart = depart;
	}
	public String getPersex() {
		return persex;
	}
	public void setPersex(String persex) {
		this.persex = persex;
	}
	public int getPerphone() {
		return perphone;
	}
	public void setPerphone(int perphone) {
		this.perphone = perphone;
	}
	public String getPeremail() {
		return peremail;
	}
	public void setPeremail(String peremail) {
		this.peremail = peremail;
	}
	
	public Person() {
		// TODO Auto-generated constructor stub
	}
}
