package com.rfid.user.po;

import java.util.HashSet;
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

import com.rfid.user.vo.RolesVo;
import com.rfid.user.vo.UsersVo;

/**
 * Roles entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "roles", schema = "dbo", catalog = "rfiddb", uniqueConstraints = @UniqueConstraint(columnNames = "roleId"))
public class Roles implements java.io.Serializable {

	// Fields

	private Long id;
	private Long roleId;
	private String roleName;
	private String roleNote;
	private Integer isEnable;
	private Set<UserRole> userRoles = new HashSet<UserRole>(0);

	// Constructors

	/** default constructor */
	public Roles() {
	}

	/** minimal constructor */
	public Roles(Long roleId) {
		this.roleId = roleId;
	}

	/** full constructor */
	public Roles(Long roleId, String roleName, String roleNote,
			Integer isEnable, Set<UserRole> userRoles) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleNote = roleNote;
		this.isEnable = isEnable;
		this.userRoles = userRoles;
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

	@Column(name = "roleId", unique = true, nullable = false)
	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@Column(name = "role_Name", length = 64)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "role_Note", length = 64)
	public String getRoleNote() {
		return this.roleNote;
	}

	public void setRoleNote(String roleNote) {
		this.roleNote = roleNote;
	}

	@Column(name = "isEnable")
	public Integer getIsEnable() {
		return this.isEnable;
	}

	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "roles")
	public Set<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	@Transient 
	public RolesVo toRolesVo() {
		RolesVo vo = new RolesVo();
//		private Long id;
		if(this.getId()!=null)
			vo.setId(this.getId());
//		private Long roleId;
		if(this.getRoleId()!=null)
			vo.setRoleId(this.getRoleId());
//		private String roleName;
		if(this.getRoleName() !=null && !"".equals(this.getRoleName()))
			vo.setRoleName(this.getRoleName());
//		private String roleNote;
		if(this.getRoleNote() !=null && !"".equals(this.getRoleNote()))
			vo.setRoleNote(this.getRoleNote());
//		private Integer isEnable;
		if(this.getIsEnable()!=null)
			vo.setIsEnable(this.getIsEnable());
		return vo;
	}

}