package com.rfid.user.vo;

import java.sql.Timestamp;
import java.util.Date;


public class UserDetailVo {

	private UsersVo usersVo;
	private Long id;
	private String userName;
	private Date birthday;
	private String userAddress;
	private Date registerTime;
	private String connection;
	public UserDetailVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserDetailVo(UsersVo usersVo, Long id, String userName,
			Date birthday, String userAddress, Date registerTime,
			String connection) {
		super();
		this.usersVo = usersVo;
		this.id = id;
		this.userName = userName;
		this.birthday = birthday;
		this.userAddress = userAddress;
		this.registerTime = registerTime;
		this.connection = connection;
	}


	public UsersVo getUsersVo() {
		return usersVo;
	}
	public void setUsersVo(UsersVo usersVo) {
		this.usersVo = usersVo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Timestamp birthday) {
		this.birthday = new Date(birthday.getTime());
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public Date getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(Timestamp registerTime) {
		this.registerTime =  new Date(registerTime.getTime());
	}
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	public String getConnection() {
		return connection;
	}
	public void setConnection(String connection) {
		this.connection = connection;
	}
	
}
