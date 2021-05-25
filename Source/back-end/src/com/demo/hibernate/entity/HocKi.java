package com.demo.hibernate.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HOC_KI")
public class HocKi implements Serializable {
	@Id
	@Column(name = "MAHOCKI")
	private String maHK;
	@Column(name = "TENHOCKI")
	private String tenHK;
	@Column(name = "NAM")
	private String nam;
	@Column(name = "HKBATDAU")
	private Date tgBG;
	@Column(name = "HKKETTHUC")
	private Date tgKT;
	@Column(name = "IsCurrent")
	private boolean isCurrent;

	public String getMaHK() {
		return maHK;
	}

	public void setMaHK(String maHK) {
		this.maHK = maHK;
	}

	public String getTenHK() {
		return tenHK;
	}

	public void setTenHK(String tenHK) {
		this.tenHK = tenHK;
	}

	public String getNam() {
		return nam;
	}

	public void setNam(String nam) {
		this.nam = nam;
	}

	public Date getTgBG() {
		return tgBG;
	}

	public void setTgBG(Date tgBG) {
		this.tgBG = tgBG;
	}

	public Date getTgKT() {
		return tgKT;
	}

	public void setTgKT(Date tgKT) {
		this.tgKT = tgKT;
	}

	public boolean isCurrent() {
		return isCurrent;
	}

	public void setCurrent(boolean isCurrent) {
		this.isCurrent = isCurrent;
	}

	@Override
	public String toString() {
		return "HocKi [maHK=" + maHK + ", tenHK=" + tenHK + ", nam=" + nam + ", tgBG=" + tgBG + ", tgKT=" + tgKT
				+ ", isCurrent=" + isCurrent + "]";
	}

	public HocKi(String maHK, String tenHK, String nam, Date tgBG, Date tgKT) {
		super();
		this.maHK = maHK;
		this.tenHK = tenHK;
		this.nam = nam;
		this.tgBG = tgBG;
		this.tgKT = tgKT;
	}

	public HocKi() {
		super();
	}

}
