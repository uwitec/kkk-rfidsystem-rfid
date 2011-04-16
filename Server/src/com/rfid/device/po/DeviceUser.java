package com.rfid.device.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.rfid.user.po.Users;

/**
 * DeviceUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "device_user", schema = "dbo", catalog = "rfiddb")
public class DeviceUser implements java.io.Serializable {

	// Fields

	private Long id;
	private Device device;
	private Users users;

	// Constructors

	/** default constructor */
	public DeviceUser() {
	}

	/** full constructor */
	public DeviceUser(Device device, Users users) {
		this.device = device;
		this.users = users;
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
	@JoinColumn(name = "deviceId", nullable = false)
	public Device getDevice() {
		return this.device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", nullable = false)
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

}