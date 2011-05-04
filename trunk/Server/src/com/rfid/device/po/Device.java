package com.rfid.device.po;

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

import com.rfid.device.vo.DeviceVo;
import com.rfid.device.vo.StatusVo;
import com.rfid.user.po.Roles;
import com.rfid.user.po.UserRole;
import com.rfid.user.po.Users;
import com.rfid.user.vo.RolesVo;
import com.rfid.user.vo.UsersVo;

/**
 * Device entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "device", schema = "dbo", catalog = "rfiddb", uniqueConstraints = @UniqueConstraint(columnNames = "deviceId"))
public class Device implements java.io.Serializable {

	// Fields

	private Long id;
	private Long deviceId;
	private Integer monitorEnable;
	private Set<DeviceUser> deviceUsers = new HashSet<DeviceUser>(0);
	private Set<DeviceStatus> deviceStatuses = new HashSet<DeviceStatus>(0);
	private Set<DeviceDetail> deviceDetails = new HashSet<DeviceDetail>(0);

	// Constructors

	/** default constructor */
	public Device() {
	}

	/** full constructor */
	public Device(Long deviceId, Integer monitorEnable,
			Set<DeviceUser> deviceUsers, Set<DeviceStatus> deviceStatuses,
			Set<DeviceDetail> deviceDetails) {
		this.deviceId = deviceId;
		this.monitorEnable = monitorEnable;
		this.deviceUsers = deviceUsers;
		this.deviceStatuses = deviceStatuses;
		this.deviceDetails = deviceDetails;
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

	@Column(name = "deviceId", unique = true)
	public Long getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	@Column(name = "monitor_Enable")
	public Integer getMonitorEnable() {
		return this.monitorEnable;
	}

	public void setMonitorEnable(Integer monitorEnable) {
		this.monitorEnable = monitorEnable;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "device")
	public Set<DeviceUser> getDeviceUsers() {
		return this.deviceUsers;
	}

	public void setDeviceUsers(Set<DeviceUser> deviceUsers) {
		this.deviceUsers = deviceUsers;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "device")
	public Set<DeviceStatus> getDeviceStatuses() {
		return this.deviceStatuses;
	}

	public void setDeviceStatuses(Set<DeviceStatus> deviceStatuses) {
		this.deviceStatuses = deviceStatuses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "device")
	
	public Set<DeviceDetail> getDeviceDetails() {
		return this.deviceDetails;
	}

	public void setDeviceDetails(Set<DeviceDetail> deviceDetails) {
		this.deviceDetails = deviceDetails;
	}

	@Transient 
	public DeviceVo toDeviceVo() {
		DeviceVo vo = new DeviceVo();
		if(this.getDeviceId()!=null)
			vo.setDeviceId(this.getDeviceId());
		if(this.getDeviceStatuses().isEmpty() && this.getDeviceStatuses().size()>0){
			StatusVo statusVo = (StatusVo)this.getDeviceStatuses().toArray()[0];
			vo.setStatusVo(statusVo);
		}
		if(this.getDeviceUsers().isEmpty() && this.getDeviceUsers().size()>0){
			Iterator it = this.getDeviceUsers().iterator();
			List<UsersVo> users = new ArrayList<UsersVo>();
			while(it.hasNext()){
				DeviceUser du = (DeviceUser)it.next();
				Users u = du.getUsers();
				users.add(u.toUsersVO());
			}
			vo.setUsersVo(users);
		}
		if(this.getId()!=null)
			vo.setId(this.getId());
		if(this.getMonitorEnable()!=null)
			vo.setMonitorEnable(this.getMonitorEnable());
		return vo;
	}

}