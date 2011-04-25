package com.rfid.device.vo;


public class StatusVo {
	
	private Long id;
	private Long statusId;
	private String scription;
	private Integer level;
	public StatusVo() {
		super();
	}
	public StatusVo(Long id, Long statusId, String scription, Integer level) {
		super();
		this.id = id;
		this.statusId = statusId;
		this.scription = scription;
		this.level = level;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public String getScription() {
		return scription;
	}
	public void setScription(String scription) {
		this.scription = scription;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}

	
}
