package com.demo.hibernate.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "kqdkhp")
public class KQDKHP implements Serializable {
	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MASV", nullable = false)
	private SinhVien sinhVien;
	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MAHP", nullable = false)
	private HocPhan hocPhan;
	@Column(name = "TGDANGKY")
	private LocalDateTime tgDangKy;

	public SinhVien getSinhVien() {
		return sinhVien;
	}

	public void setSinhVien(SinhVien sinhVien) {
		this.sinhVien = sinhVien;
	}

	public HocPhan getHocPhan() {
		return hocPhan;
	}

	public void setHocPhan(HocPhan hocPhan) {
		this.hocPhan = hocPhan;
	}

	public KQDKHP() {
		super();
	}

	public LocalDateTime getTgDangKy() {
		return tgDangKy;
	}

	public void setTgDangKy(LocalDateTime tgDangKy) {
		this.tgDangKy = tgDangKy;
	}

	@Override
	public String toString() {
		return "KQDKHP [sinhVien=" + sinhVien + ", hocPhan=" + hocPhan + ", tgDangKy=" + tgDangKy + "]";
	}

	public KQDKHP(SinhVien sinhVien, HocPhan hocPhan, LocalDateTime tgDangKy) {
		super();
		this.sinhVien = sinhVien;
		this.hocPhan = hocPhan;
		this.tgDangKy = tgDangKy;
	}

	public KQDKHP(SinhVien sinhVien, HocPhan hocPhan) {
		super();
		this.sinhVien = sinhVien;
		this.hocPhan = hocPhan;
	}

}
