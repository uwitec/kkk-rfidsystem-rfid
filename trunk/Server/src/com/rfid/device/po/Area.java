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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.rfid.device.vo.AreaVo;

/**
 * Area entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Area", schema = "dbo", catalog = "rfiddb", uniqueConstraints = {
		@UniqueConstraint(columnNames = "areaId"),
		@UniqueConstraint(columnNames = "readerId") })
public class Area implements java.io.Serializable {

	// Fields

	private Long id;
	private Reader reader;
	private Long areaId;
	private String areaAddress;
	private String scription;
	private Set<AreaMonitor> areaMonitors = new HashSet<AreaMonitor>(0);
	private Set<DeviceStatus> deviceStatuses = new HashSet<DeviceStatus>(0);

	// Constructors

	/** default constructor */
	public Area() {
	}

	/** minimal constructor */
	public Area(Reader reader, Long areaId) {
		this.reader = reader;
		this.areaId = areaId;
	}

	/** full constructor */
	public Area(Reader reader, Long areaId, String areaAddress,
			String scription, Set<AreaMonitor> areaMonitors,
			Set<DeviceStatus> deviceStatuses) {
		this.reader = reader;
		this.areaId = areaId;
		this.areaAddress = areaAddress;
		this.scription = scription;
		this.areaMonitors = areaMonitors;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "readerId",referencedColumnName="readerid", unique = true, nullable = false)
	public Reader getReader() {
		return this.reader;
	}

	public void setReader(Reader reader) {
		this.reader = reader;
	}

	@Column(name = "areaId", unique = true, nullable = false)
	public Long getAreaId() {
		return this.areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	@Column(name = "area_Address", length = 200)
	public String getAreaAddress() {
		return this.areaAddress;
	}

	public void setAreaAddress(String areaAddress) {
		this.areaAddress = areaAddress;
	}

	@Column(name = "scription", length = 500)
	public String getScription() {
		return this.scription;
	}

	public void setScription(String scription) {
		this.scription = scription;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "area")
	public Set<AreaMonitor> getAreaMonitors() {
		return this.areaMonitors;
	}

	public void setAreaMonitors(Set<AreaMonitor> areaMonitors) {
		this.areaMonitors = areaMonitors;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "area")
	public Set<DeviceStatus> getDeviceStatuses() {
		return this.deviceStatuses;
	}

	public void setDeviceStatuses(Set<DeviceStatus> deviceStatuses) {
		this.deviceStatuses = deviceStatuses;
	}

	@Transient
	public AreaVo toAreaVo() {
		AreaVo vo = new AreaVo();
		if(this.getAreaAddress()!=null)
			vo.setAreaAddress(this.getAreaAddress());
		if(this.getAreaId()!=null)
			vo.setAreaId(this.getAreaId());
		if(this.getId()!=null)
			vo.setId(this.getId());
		if(this.getReader()!=null)
			vo.setReader(this.getReader().toReaderVo());
		if(this.getScription()!=null)
			vo.setScription(this.getScription());
		return vo;
	}

}