package com.demo.hibernate.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "hoc_ki")
public class HocKi implements Serializable {
	@Id
	@Column(name = "MAHOCKI")
	private String maHK;
	@Column(name = "TENHOCKI")
	private String tenHK;
	@Column(name = "NAM")
	private String nam;
	@Column(name = "HKBATDAU")
	private LocalDate tgBD;
	@Column(name = "HKKETTHUC")
	private LocalDate tgKT;
	@Column(name = "IsCurrent")
	private boolean isCurrent;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "hocKi")
	private List<DotDKHP> dotDKHPs = new ArrayList<>();

	public List<DotDKHP> getDotDKHPs() {
		return dotDKHPs;
	}

	public void setDotDKHPs(List<DotDKHP> dotDKHPs) {
		this.dotDKHPs = dotDKHPs;
	}

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

	public LocalDate tgBD() {
		return tgBD;
	}

	public void setTgBG(LocalDate tgBD) {
		this.tgBD = tgBD;
	}

	public LocalDate getTgKT() {
		return tgKT;
	}

	public void setTgKT(LocalDate tgKT) {
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
		return "HocKi [maHK=" + maHK + ", tenHK=" + tenHK + ", nam=" + nam + ", tgBG=" + tgBD + ", tgKT=" + tgKT
				+ ", isCurrent=" + isCurrent + "]";
	}

	public HocKi(String maHK, String tenHK, String nam, LocalDate tgBD, LocalDate tgKT) {
		super();
		this.maHK = maHK;
		this.tenHK = tenHK;
		this.nam = nam;
		this.tgBD = tgBD;
		this.tgKT = tgKT;
		this.isCurrent = false;
	}

	public HocKi() {
		super();
	}

}
