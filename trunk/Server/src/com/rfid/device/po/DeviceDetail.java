package com.rfid.device.po;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Transient;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.rfid.device.vo.DeviceDetailVo;

/**
 * DeviceDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "device_detail", schema = "dbo", catalog = "rfiddb")
public class DeviceDetail implements java.io.Serializable {

	// Fields

	private Long id;
	private Device device;
	private String deviceName;
	private Double price;
	private String manufactory;
	private Timestamp purchaseDate;
	private String buyer;
	private Long deviceNum;

	// Constructors

	/** default constructor */
	public DeviceDetail() {
	}

	/** full constructor */
	public DeviceDetail(Device device, String deviceName, Double price,
			String manufactory, Timestamp purchaseDate, String buyer,
			Long deviceNum) {
		this.device = device;
		this.deviceName = deviceName;
		this.price = price;
		this.manufactory = manufactory;
		this.purchaseDate = purchaseDate;
		this.buyer = buyer;
		this.deviceNum = deviceNum;
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
	@JoinColumn(name = "deviceId",referencedColumnName="deviceId", nullable = false)
	public Device getDevice() {
		return this.device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	@Column(name = "device_Name", length = 64)
	public String getDeviceName() {
		return this.deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	@Column(name = "price", scale = 4)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "manufactory", length = 64)
	public String getManufactory() {
		return this.manufactory;
	}

	public void setManufactory(String manufactory) {
		this.manufactory = manufactory;
	}

	@Column(name = "purchaseDate", length = 23)
	public Timestamp getPurchaseDate() {
		return this.purchaseDate;
	}

	public void setPurchaseDate(Timestamp purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	@Column(name = "buyer", length = 64)
	public String getBuyer() {
		return this.buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	@Column(name = "device_Num")
	public Long getDeviceNum() {
		return this.deviceNum;
	}

	public void setDeviceNum(Long deviceNum) {
		this.deviceNum = deviceNum;
	}

	@Transient
	public DeviceDetailVo toDeviceDetailVo(){
		DeviceDetailVo vo = new DeviceDetailVo();
		if(this.getBuyer()!=null)
			vo.setBuyer(this.getBuyer());
		if(this.getDevice()!=null)
			vo.setDeviceVo(this.getDevice().toDeviceVo());
		if(this.getDeviceName()!=null)
			vo.setDeviceName(this.getDeviceName());
		if(this.getDeviceNum()!=null)
			vo.setDeviceNum(this.getDeviceNum());
		if(this.getId()!=null)
			vo.setId(this.getId());
		if(this.getManufactory()!=null)
			vo.setManufactory(this.getManufactory());
		if(this.getPrice()!=null)
			vo.setPrice(this.getPrice());
		if(this.getPurchaseDate()!=null)
			vo.setPurchaseDate(this.getPurchaseDate());
		return vo;
	}
}