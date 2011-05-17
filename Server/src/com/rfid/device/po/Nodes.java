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
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.rfid.monitorServer.vo.NodeVo;

/**
 * Nodes entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Nodes", schema = "dbo", catalog = "rfiddb", uniqueConstraints = @UniqueConstraint(columnNames = "deviceId"))
public class Nodes implements java.io.Serializable {

	// Fields

	private Long id;
	private Device device;
	private Integer nodeId;

	// Constructors

	/** default constructor */
	public Nodes() {
	}

	/** full constructor */
	public Nodes(Device device, Integer nodeId) {
		this.device = device;
		this.nodeId = nodeId;
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
	@JoinColumn(name = "deviceId",referencedColumnName="deviceId" ,unique = true, nullable = false)
	public Device getDevice() {
		return this.device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	@Column(name = "nodeId", nullable = false)
	public Integer getNodeId() {
		return this.nodeId;
	}

	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}
}