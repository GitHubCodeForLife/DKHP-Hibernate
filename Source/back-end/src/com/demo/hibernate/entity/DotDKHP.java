package com.demo.hibernate.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "dot_dk_hp")
public class DotDKHP implements Serializable {
	@Id
	@Column(name = "MADOT")
	private String maDot;
	@Column(name = "STTDOT")
	private int stt;
	@Column(name = "TGBATDAU")
	private LocalDateTime tbBD;
	@Column(name = "TGKETTHUC")
	private LocalDateTime tbKT;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MAHOCKI", nullable = false)
	private HocKi hocKi;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "dotDKHP")
	private List<HocPhan> hocPhans = new ArrayList<>();

	public List<HocPhan> getHocPhans() {
		return hocPhans;
	}

	public void setHocPhans(List<HocPhan> hocPhans) {
		this.hocPhans = hocPhans;
	}

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

	public HocKi getHocKi() {
		return hocKi;
	}

	public void setHocKi(HocKi hocKi) {
		this.hocKi = hocKi;
	}

	@Override
	public String toString() {
		return "DotDKHP [maDot=" + maDot + ", stt=" + stt + ", tbBD=" + tbBD + ", tbKT=" + tbKT + ", hocKi=" + hocKi
				+ "]";
	}

	public DotDKHP(String maDot, int stt, LocalDateTime tbBD, LocalDateTime tbKT, HocKi hocKi) {
		super();
		this.maDot = maDot;
		this.stt = stt;
		this.tbBD = tbBD;
		this.tbKT = tbKT;
		this.hocKi = hocKi;
	}

	public DotDKHP() {
		super();
	}

}
