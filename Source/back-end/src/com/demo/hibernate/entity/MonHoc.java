package com.demo.hibernate.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mon_hoc")
public class MonHoc implements Serializable {
	@Id
	@Column(name = "MAMH")
	private String maMH;
	@Column(name = "TENMH")
	private String tenMH;
	@Column(name = "SOTINCHI")
	private int soTC;

	public String getMaMH() {
		return maMH;
	}

	public void setMaMH(String maMH) {
		this.maMH = maMH;
	}

	public String getTenMH() {
		return tenMH;
	}

	public void setTenMH(String tenMH) {
		this.tenMH = tenMH;
	}

	public int getSoTC() {
		return soTC;
	}

	public void setSoTC(int soTC) {
		this.soTC = soTC;
	}

	@Override
	public String toString() {
		return "MonHoc [maMH=" + maMH + ", tenMH=" + tenMH + ", soTC=" + soTC + "]";
	}

	public MonHoc(String maMH, String tenMH, int soTC) {
		super();
		this.maMH = maMH;
		this.tenMH = tenMH;
		this.soTC = soTC;
	}

	public MonHoc() {
		super();
	}

}
