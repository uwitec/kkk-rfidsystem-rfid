package com.rfid.user.vo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.rfid.user.po.Roles;
import com.rfid.user.po.UserRole;

public class UsersVo {

	private Long id;
	private Long userid;
	private String loginName;
	private String loginPassword;
	private List<RolesVo> userRoles = new ArrayList();
	
	public UsersVo() {
		super();
	}

	public UsersVo(Long id, Long userid, String loginName,
			String loginPassword, List<RolesVo> userRoles) {
		super();
		this.id = id;
		this.userid = userid;
		this.loginName = loginName;
		this.loginPassword = loginPassword;
		this.userRoles = userRoles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public List<RolesVo> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<RolesVo> userRoles) {
		this.userRoles = userRoles;
	}

	

	
}
