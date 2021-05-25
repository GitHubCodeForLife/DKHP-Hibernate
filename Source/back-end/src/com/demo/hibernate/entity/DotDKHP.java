package com.demo.hibernate.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LOP")
public class DotDKHP implements Serializable {
	@Id
	@Column(name = "MADOT")
	private String maDot;
	@Column(name = "SSTDOT")
	private int stt;
	@Column(name = "TGBATDAU")
	private LocalDateTime tbBD;
	@Column(name = "TGKETTHUC")
	private LocalDateTime tbKT;

	public String getMaDot() {
		return maDot;
	}

	public void setMaDot(String maDot) {
		this.maDot = maDot;
	}

	public int getStt() {
		return stt;
	}

	public void setStt(int stt) {
		this.stt = stt;
	}

	public LocalDateTime getTbBD() {
		return tbBD;
	}

	public void setTbBD(LocalDateTime tbBD) {
		this.tbBD = tbBD;
	}

	public LocalDateTime getTbKT() {
		return tbKT;
	}

	public void setTbKT(LocalDateTime tbKT) {
		this.tbKT = tbKT;
	}

	public DotDKHP(String maDot, int stt, LocalDateTime tbBD, LocalDateTime tbKT) {
		super();
		this.maDot = maDot;
		this.stt = stt;
		this.tbBD = tbBD;
		this.tbKT = tbKT;
	}

	@Override
	public String toString() {
		return "DotDKHP [maDot=" + maDot + ", stt=" + stt + ", tbBD=" + tbBD + ", tbKT=" + tbKT + "]";
	}

	public DotDKHP() {
		super();
	}

}
