package com.demo.hibernate.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "hoc_phan")
public class HocPhan implements Serializable {
	@Id
	@Column(name = "MAHP")
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private String maHP;
	@Column(name = "TENGVLT")
	private String tenGVLT;
	@Column(name = "THU")
	private int thu;
	@Column(name = "SLOT")
	private int slot;
	@Column(name = "CA")
	private int ca;
	@Column(name = "TENPHONG")
	private String tenPhong;
	@Column(name = "MALOP")
	private String maLop;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MAMH", nullable = false)
	private MonHoc monHoc;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MADOT", nullable = false)
	private DotDKHP dotDKHP;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "hocPhan")
	private List<KQDKHP> kqdkhps = new ArrayList<>();

	public List<KQDKHP> getKqdkhps() {
		return kqdkhps;
	}

	public void setKqdkhps(List<KQDKHP> kqdkhps) {
		this.kqdkhps = kqdkhps;
	}

	@Override
	public String toString() {
		return "HocPhan [maHP=" + maHP + ", tenGVLT=" + tenGVLT + ", thu=" + thu + ", slot=" + slot + ", ca=" + ca
				+ ", tenPhong=" + tenPhong + ", maLop=" + maLop + ", monHoc=" + monHoc + ", dotDKHP=" + dotDKHP + "]";
	}

	public String getMaHP() {
		return maHP;
	}

	public void setMaHP(String maHP) {
		this.maHP = maHP;
	}

	public String getTenGVLT() {
		return tenGVLT;
	}

	public void setTenGVLT(String tenGVLT) {
		this.tenGVLT = tenGVLT;
	}

	public int getThu() {
		return thu;
	}

	public void setThu(int thu) {
		this.thu = thu;
	}

	public int getSlot() {
		return slot;
	}

	public void setSlot(int slot) {
		this.slot = slot;
	}

	public int getCa() {
		return ca;
	}

	public void setCa(int ca) {
		this.ca = ca;
	}

	public String getTenPhong() {
		return tenPhong;
	}

	public void setTenPhong(String tenPhong) {
		this.tenPhong = tenPhong;
	}

	public String getMaLop() {
		return maLop;
	}

	public void setMaLop(String maLop) {
		this.maLop = maLop;
	}

	public MonHoc getMonHoc() {
		return monHoc;
	}

	public void setMonHoc(MonHoc monHoc) {
		this.monHoc = monHoc;
	}

	public DotDKHP getDotDKHP() {
		return dotDKHP;
	}

	public void setDotDKHP(DotDKHP dotDKHP) {
		this.dotDKHP = dotDKHP;
	}

	public HocPhan(String maHP, String tenGVLT, int thu, int slot, int ca, String tenPhong, String maLop, MonHoc monHoc,
			DotDKHP dotDKHP) {
		super();
		this.maHP = maHP;
		this.tenGVLT = tenGVLT;
		this.thu = thu;
		this.slot = slot;
		this.ca = ca;
		this.tenPhong = tenPhong;
		this.maLop = maLop;
		this.monHoc = monHoc;
		this.dotDKHP = dotDKHP;
	}

	public HocPhan() {
		super();
	}

}
