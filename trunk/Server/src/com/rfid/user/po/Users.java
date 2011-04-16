package com.rfid.user.po;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Transient;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.rfid.device.po.DeviceUser;
import com.rfid.user.vo.RolesVo;
import com.rfid.user.vo.UsersVo;

/**
 * Users entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "users", schema = "dbo", catalog = "rfiddb", uniqueConstraints = {
		@UniqueConstraint(columnNames = "login_Name"),
		@UniqueConstraint(columnNames = "userid") })
public class Users implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userid;
	private String loginName;
	private String loginPassword;
	private Set<UserRole> userRoles = new HashSet<UserRole>(0);
	private Set<DeviceUser> deviceUsers = new HashSet<DeviceUser>(0);
	private Set<UserDetail> userDetails = new HashSet<UserDetail>(0);

	// Constructors

	/** default constructor */
	public Users() {
	}

	/** minimal constructor */
	public Users(Long userid, String loginName, String loginPassword) {
		this.userid = userid;
		this.loginName = loginName;
		this.loginPassword = loginPassword;
	}

	/** full constructor */
	public Users(Long userid, String loginName, String loginPassword,
			Set<UserRole> userRoles, Set<DeviceUser> deviceUsers,
			Set<UserDetail> userDetails) {
		this.userid = userid;
		this.loginName = loginName;
		this.loginPassword = loginPassword;
		this.userRoles = userRoles;
		this.deviceUsers = deviceUsers;
		this.userDetails = userDetails;
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

	@Column(name = "userid", unique = true, nullable = false)
	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	@Column(name = "login_Name", unique = true, nullable = false, length = 64)
	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "login_password", nullable = false, length = 64)
	public String getLoginPassword() {
		return this.loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "users")
	public Set<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "users")
	public Set<DeviceUser> getDeviceUsers() {
		return this.deviceUsers;
	}

	public void setDeviceUsers(Set<DeviceUser> deviceUsers) {
		this.deviceUsers = deviceUsers;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "users")
	public Set<UserDetail> getUserDetails() {
		return this.userDetails;
	}

	public void setUserDetails(Set<UserDetail> userDetails) {
		this.userDetails = userDetails;
	}
	
	@Transient 
	public UsersVo toUsersVO(){
		UsersVo vo = new UsersVo();
		if(this.getId() !=null)
			vo.setId(id);
		if(this.getUserid() !=null)
			vo.setUserid(this.getUserid());
		if(this.getLoginName() !=null && !"".equals(this.getLoginName()))
			vo.setLoginName(this.getLoginName());
		if(this.getLoginPassword() !=null && !"".equals(this.getLoginPassword()))
			vo.setLoginPassword(this.getLoginPassword());
		if(this.getUserRoles() !=null && this.getUserRoles().size()>0){
			Iterator it = this.getUserRoles().iterator();
			List<RolesVo> roles = new ArrayList();
			while(it.hasNext()){
				UserRole ur = (UserRole)it.next();
				Roles r = ur.getRoles();
				roles.add(r.toRolesVo());
			}
			vo.setUserRoles(roles);
		}
		return vo;
	}

}