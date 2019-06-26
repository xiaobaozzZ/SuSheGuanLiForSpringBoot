package cn.xiaobao.management_system.entity;

public class User {
	private String uname;
	private String upassword;
	private int utype;
	
	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUpassword() {
		return upassword;
	}

	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}

	public int getUtype() {
		return utype;
	}

	public void setUtype(int utype) {
		this.utype = utype;
	}

	@Override
	public String toString() {
		return "User [uname=" + uname + ", upassword=" + upassword + ", utype=" + utype + "]";
	}
	
}
