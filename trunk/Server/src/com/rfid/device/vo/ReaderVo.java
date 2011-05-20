package com.rfid.device.vo;

import java.util.HashSet;
import java.util.Set;

import com.rfid.device.po.Area;

public class ReaderVo {

	private Long id;
	
	private Long readerid;
	private String readerIp;
	
	public ReaderVo() {
		super();
	}

	public ReaderVo(Long id, Long readerid, String readerIp) {
		super();
		this.id = id;
		this.readerid = readerid;
		this.readerIp = readerIp;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getReaderid() {
		return readerid;
	}

	public void setReaderid(Long readerid) {
		this.readerid = readerid;
	}

	public String getReaderIp() {
		return readerIp;
	}

	public void setReaderIp(String readerIp) {
		this.readerIp = readerIp;
	}
	
	
}
