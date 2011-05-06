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

/**
 * AreaMonitor entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "area_monitor", schema = "dbo", catalog = "rfiddb")
public class AreaMonitor implements java.io.Serializable {

	// Fields

	private Long id;
	private Area area;
	private Monitor monitor;

	// Constructors

	/** default constructor */
	public AreaMonitor() {
	}

	/** full constructor */
	public AreaMonitor(Area area, Monitor monitor) {
		this.area = area;
		this.monitor = monitor;
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
	@JoinColumn(name = "areaId",referencedColumnName="areaId", nullable = false)
	public Area getArea() {
		return this.area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "monitorId",referencedColumnName="monitorId", nullable = false)
	public Monitor getMonitor() {
		return this.monitor;
	}

	public void setMonitor(Monitor monitor) {
		this.monitor = monitor;
	}

}