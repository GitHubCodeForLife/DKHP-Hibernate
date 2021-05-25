package com.demo.hibernate.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LOP")
public class Lop implements Serializable {
	@Id
	@Column(name = "MALOP")
	private String maLop;
	@Column(name = "TONGSV")
	private int tongSV;
	@Column(name = "TONGNAM")
	private int tongNam;

	public String getMaLop() {
		return maLop;
	}

	public void setMaLop(String maLop) {
		this.maLop = maLop;
	}

	public int getTongSV() {
		return tongSV;
	}

	public void setTongSV(int tongSV) {
		this.tongSV = tongSV;
	}

	public int getTongNam() {
		return tongNam;
	}

	public void setTongNam(int tongNam) {
		this.tongNam = tongNam;
	}

	public Lop(String maLop, int tongSV, int tongNam) {
		super();
		this.maLop = maLop;
		this.tongSV = tongSV;
		this.tongNam = tongNam;
	}

	public Lop() {
		super();
	}

	@Override
	public String toString() {
		return "Lop [maLop=" + maLop + ", tongSV=" + tongSV + ", tongNam=" + tongNam + "]";
	}

}
