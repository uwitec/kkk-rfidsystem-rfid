package com.rfid.device.po;

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

import com.rfid.device.vo.StatusVo;

/**
 * Status entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "status", schema = "dbo", catalog = "rfiddb", uniqueConstraints = @UniqueConstraint(columnNames = "statusId"))
public class Status implements java.io.Serializable {

	// Fields

	private Long id;
	private Long statusId;
	private String scription;
	private Integer level;
	private Set<DeviceStatus> deviceStatuses = new HashSet<DeviceStatus>(0);

	// Constructors

	/** default constructor */
	public Status() {
	}

	/** minimal constructor */
	public Status(Long statusId, Integer level) {
		this.statusId = statusId;
		this.level = level;
	}

	/** full constructor */
	public Status(Long statusId, String scription, Integer level,
			Set<DeviceStatus> deviceStatuses) {
		this.statusId = statusId;
		this.scription = scription;
		this.level = level;
		this.deviceStatuses = deviceStatuses;
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

	@Column(name = "statusId", unique = true, nullable = false)
	public Long getStatusId() {
		return this.statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	@Column(name = "scription")
	public String getScription() {
		return this.scription;
	}

	public void setScription(String scription) {
		this.scription = scription;
	}

	@Column(name = "level", nullable = false)
	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "status")
	public Set<DeviceStatus> getDeviceStatuses() {
		return this.deviceStatuses;
	}

	public void setDeviceStatuses(Set<DeviceStatus> deviceStatuses) {
		this.deviceStatuses = deviceStatuses;
	}

	@Transient
	public StatusVo toStatusVo(){
		StatusVo vo = new StatusVo();
		if(this.getId()!=null)
			vo.setId(this.getId());
		if(this.getLevel()!=null)
			vo.setLevel(this.getLevel());
		if(this.getScription()!=null)
			vo.setScription(this.getScription());
		if(this.getStatusId()!=null && this.getStatusId()!=0)
			vo.setStatusId(this.getStatusId());
		return vo;
	}
}