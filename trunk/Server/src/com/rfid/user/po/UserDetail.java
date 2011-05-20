package com.rfid.user.po;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.rfid.user.vo.UserDetailVo;

/**
 * UserDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_detail", schema = "dbo", catalog = "rfiddb", uniqueConstraints = @UniqueConstraint(columnNames = "userId"))
public class UserDetail implements java.io.Serializable {

	// Fields
	private Long id;
	private Users users;
	private String userName;
	private Timestamp birthday;
	private String userAddress;
	private Timestamp registerTime;
	private String connection;

	// Constructors

	/** default constructor */
	public UserDetail() {
	}

	/** minimal constructor */
	public UserDetail(Users users) {
		this.users = users;
	}

	/** full constructor */
	public UserDetail(Users users, String userName, Timestamp birthday,
			String userAddress, Timestamp registerTime, String connection) {
		this.users = users;
		this.userName = userName;
		this.birthday = birthday;
		this.userAddress = userAddress;
		this.registerTime = registerTime;
		this.connection = connection;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid",referencedColumnName="userid", unique = true, nullable = false)
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Column(name = "userName", length = 64)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "birthday", length = 23)
	public Timestamp getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}

	@Column(name = "userAddress", length = 1)
	public String getUserAddress() {
		return this.userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	@Column(name = "registerTime", length = 23)
	public Timestamp getRegisterTime() {
		return this.registerTime;
	}

	public void setRegisterTime(Timestamp registerTime) {
		this.registerTime = registerTime;
	}

	@Column(name = "Connection", length = 10)
	public String getConnection() {
		return this.connection;
	}

	public void setConnection(String connection) {
		this.connection = connection;
	}

	@Transient
	public UserDetailVo toUserDetailVo(){
		UserDetailVo vo = new UserDetailVo();
//		private Long id;
		if(this.getId()!=null && this.getId()>0)
			vo.setId(getId());
//		private String userName;
		if(this.getUserName()!=null)	
			vo.setUserName(getUserName());
//		private Timestamp birthday;
		if(this.getBirthday()!=null)
			vo.setBirthday(getBirthday());
//		private String userAddress;
		if(this.getUserAddress()!=null)
			vo.setUserAddress(getUserAddress());
//		private Timestamp registerTime;
		if(this.getRegisterTime()!=null)
			vo.setRegisterTime(getRegisterTime());
//		private String connection;
		if(this.getConnection()!=null)
			vo.setConnection(getConnection());
		if(this.getUsers()!=null)
			vo.setUsersVo(getUsers().toUsersVO());
		return vo;
	}
}