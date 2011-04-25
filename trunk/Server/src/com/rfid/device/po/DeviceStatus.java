package com.rfid.device.po;

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

/**
 * DeviceStatus entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "device_status", schema = "dbo", catalog = "rfiddb")
public class DeviceStatus implements java.io.Serializable {

	// Fields

	private Long id;
	private Area area;
	private Status status;
	private Device device;
	private Timestamp monitorTime;
	private Long previousId;
	private Integer isEnable;

	// Constructors

	/** default constructor */
	public DeviceStatus() {
	}

	/** full constructor */
	public DeviceStatus(Area area, Status status, Device device,
			Timestamp monitorTime, Long previousId, Integer isEnable) {
		this.area = area;
		this.status = status;
		this.device = device;
		this.monitorTime = monitorTime;
		this.previousId = previousId;
		this.isEnable = isEnable;
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
	@JoinColumn(name = "current_AreaId", nullable = false)
	public Area getArea() {
		return this.area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "current_StatusId",referencedColumnName="statusId", nullable = false)
	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "deviceId",referencedColumnName="deviceId", nullable = false)
	public Device getDevice() {
		return this.device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	@Column(name = "monitor_Time", nullable = false, length = 23)
	public Timestamp getMonitorTime() {
		return this.monitorTime;
	}

	public void setMonitorTime(Timestamp monitorTime) {
		this.monitorTime = monitorTime;
	}

	@Column(name = "previousId", nullable = false)
	public Long getPreviousId() {
		return this.previousId;
	}

	public void setPreviousId(Long previousId) {
		this.previousId = previousId;
	}

	@Column(name = "isEnable", nullable = false)
	public Integer getIsEnable() {
		return this.isEnable;
	}

	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}

}