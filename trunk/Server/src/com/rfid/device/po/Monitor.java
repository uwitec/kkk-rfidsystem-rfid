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


import com.rfid.device.vo.AreaVo;
import com.rfid.device.vo.MonitorVo;

/**
 * Monitor entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "monitor", schema = "dbo", catalog = "rfiddb", uniqueConstraints = @UniqueConstraint(columnNames = "monitorId"))
public class Monitor implements java.io.Serializable {

	// Fields

	private Long id;
	private Long monitorId;
	private String monitorIp;
	private String monitorPort;
	private Set<AreaMonitor> areaMonitors = new HashSet<AreaMonitor>(0);

	// Constructors

	/** default constructor */
	public Monitor() {
	}

	/** minimal constructor */
	public Monitor(Long monitorId, String monitorIp, String monitorPort) {
		this.monitorId = monitorId;
		this.monitorIp = monitorIp;
		this.monitorPort = monitorPort;
	}

	/** full constructor */
	public Monitor(Long monitorId, String monitorIp, String monitorPort,
			Set<AreaMonitor> areaMonitors) {
		this.monitorId = monitorId;
		this.monitorIp = monitorIp;
		this.monitorPort = monitorPort;
		this.areaMonitors = areaMonitors;
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

	@Column(name = "monitorId", unique = true, nullable = false)
	public Long getMonitorId() {
		return this.monitorId;
	}

	public void setMonitorId(Long monitorId) {
		this.monitorId = monitorId;
	}

	@Column(name = "monitor_IP", nullable = false, length = 64)
	public String getMonitorIp() {
		return this.monitorIp;
	}

	public void setMonitorIp(String monitorIp) {
		this.monitorIp = monitorIp;
	}

	@Column(name = "monitor_Port", nullable = false, length = 64)
	public String getMonitorPort() {
		return this.monitorPort;
	}

	public void setMonitorPort(String monitorPort) {
		this.monitorPort = monitorPort;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "monitor")
	public Set<AreaMonitor> getAreaMonitors() {
		return this.areaMonitors;
	}

	public void setAreaMonitors(Set<AreaMonitor> areaMonitors) {
		this.areaMonitors = areaMonitors;
	}

	@Transient
	public MonitorVo toMonitorVo(){
		MonitorVo vo = new MonitorVo();
		if(this.getId()!=null)
			vo.setId(this.getId());
		if(this.getMonitorId()!=null)
			vo.setMonitorId(this.getMonitorId());
		if(this.getMonitorIp()!=null)
			vo.setMonitorIp(this.getMonitorIp());
		if(this.getMonitorPort()!=null)
			vo.setMonitorPort(this.getMonitorPort());
		if(this.getAreaMonitors()!=null && this.getAreaMonitors().size()>0)
		{
			List<AreaVo> list = new ArrayList<AreaVo>();
			Iterator it = this.getAreaMonitors().iterator();
			while(it.hasNext())
			{
				AreaMonitor am = (AreaMonitor)it.next();
				Area a = am.getArea();
				AreaVo av = a.toAreaVo();
				list.add(av);
			}
			vo.setAreas(list);
		}
		return vo;
		
	}
}