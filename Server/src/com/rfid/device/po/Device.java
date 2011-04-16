package com.rfid.device.po;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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

}