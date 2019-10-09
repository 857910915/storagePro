package bean;

/**
 * 
 * @author 陈世杰
 * @pramati userid 管理员id 
 * @pramati username 管理员账号
 * @pramati userpwd 管理员密码
 *
 */
public class User {
	private int userid;
	private String username;
	private String userpwd;
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
}
