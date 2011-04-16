package com.rfid.user.vo;

public class RolesVo {

	private Long id;
	private Long roleId;
	private String roleName;
	private String roleNote;
	private Integer isEnable;
	
	
	public RolesVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RolesVo(Long id, Long roleId, String roleName, String roleNote,
			Integer isEnable) {
		super();
		this.id = id;
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleNote = roleNote;
		this.isEnable = isEnable;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleNote() {
		return roleNote;
	}
	public void setRoleNote(String roleNote) {
		this.roleNote = roleNote;
	}
	public Integer getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}
	
	
}
