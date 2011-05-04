package com.rfid.device.vo;

import java.util.HashSet;
import java.util.Set;

import com.rfid.device.po.AreaMonitor;
import com.rfid.device.po.DeviceStatus;
import com.rfid.device.po.Reader;

/**
 * @author Administrator
 *
 */
public class AreaVo {
	private Long id;
	private ReaderVo reader;
	private Long areaId;
	private String areaAddress;
	private String scription;
	public AreaVo() {
		super();
	}
	public AreaVo(Long id, ReaderVo reader, Long areaId, String areaAddress,
			String scription) {
		super();
		this.id = id;
		this.reader = reader;
		this.areaId = areaId;
		this.areaAddress = areaAddress;
		this.scription = scription;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ReaderVo getReader() {
		return reader;
	}
	public void setReader(ReaderVo reader) {
		this.reader = reader;
	}
	public Long getAreaId() {
		return areaId;
	}
	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
	public String getAreaAddress() {
		return areaAddress;
	}
	public void setAreaAddress(String areaAddress) {
		this.areaAddress = areaAddress;
	}
	public String getScription() {
		return scription;
	}
	public void setScription(String scription) {
		this.scription = scription;
	}
	
	
}
