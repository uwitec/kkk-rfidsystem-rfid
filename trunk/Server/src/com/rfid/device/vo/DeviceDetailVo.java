package com.rfid.device.vo;

import java.sql.Timestamp;
import java.util.Date;

import com.rfid.device.po.Device;

public class DeviceDetailVo {

	private Long id;
	private DeviceVo deviceVo = new DeviceVo();
	private String deviceName;
	private Double price;
	private String manufactory;
	private Date purchaseDate;
	private String buyer;
	private Long deviceNum;
	public DeviceDetailVo() {
		super();
	}
	public DeviceDetailVo(Long id, DeviceVo deviceVo, String deviceName,
			Double price, String manufactory, Date purchaseDate, String buyer,
			Long deviceNum) {
		super();
		this.id = id;
		this.deviceVo = deviceVo;
		this.deviceName = deviceName;
		this.price = price;
		this.manufactory = manufactory;
		this.purchaseDate = purchaseDate;
		this.buyer = buyer;
		this.deviceNum = deviceNum;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public DeviceVo getDeviceVo() {
		return deviceVo;
	}
	public void setDeviceVo(DeviceVo deviceVo) {
		this.deviceVo = deviceVo;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getManufactory() {
		return manufactory;
	}
	public void setManufactory(String manufactory) {
		this.manufactory = manufactory;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public void setPurchaseDate(Timestamp purchaseDate){
		this.purchaseDate =  new Date(purchaseDate.getTime());
	}
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	public Long getDeviceNum() {
		return deviceNum;
	}
	public void setDeviceNum(Long deviceNum) {
		this.deviceNum = deviceNum;
	}
	
}
