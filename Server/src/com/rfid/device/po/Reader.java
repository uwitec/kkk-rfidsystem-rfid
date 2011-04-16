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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Reader entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "reader", schema = "dbo", catalog = "rfiddb", uniqueConstraints = @UniqueConstraint(columnNames = "readerid"))
public class Reader implements java.io.Serializable {

	// Fields

	private Long id;
	private Long readerid;
	private String readerIp;
	private Set<Area> areas = new HashSet<Area>(0);

	// Constructors

	/** default constructor */
	public Reader() {
	}

	/** minimal constructor */
	public Reader(Long readerid, String readerIp) {
		this.readerid = readerid;
		this.readerIp = readerIp;
	}

	/** full constructor */
	public Reader(Long readerid, String readerIp, Set<Area> areas) {
		this.readerid = readerid;
		this.readerIp = readerIp;
		this.areas = areas;
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

	@Column(name = "readerid", unique = true, nullable = false)
	public Long getReaderid() {
		return this.readerid;
	}

	public void setReaderid(Long readerid) {
		this.readerid = readerid;
	}

	@Column(name = "reader_IP", nullable = false, length = 64)
	public String getReaderIp() {
		return this.readerIp;
	}

	public void setReaderIp(String readerIp) {
		this.readerIp = readerIp;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "reader")
	public Set<Area> getAreas() {
		return this.areas;
	}

	public void setAreas(Set<Area> areas) {
		this.areas = areas;
	}

}