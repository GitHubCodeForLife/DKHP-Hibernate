/**
 *
 */
package com.demo.hibernate.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sinh_vien")
public class SinhVien implements Serializable {

	@Id
	@Column(name = "MASV")
	private String maSV;
	@Column(name = "MALOP")
	private String maLop;
	@Column(name = "TENSV")
	private String tenSV;
	@Column(name = "SDT")
	private String sdt;
	@Column(name = "MKSV")
	private String matKhau;
	@Column(name = "DIACHISV")
	private String diaChi;
	@Column(name = "EMAILSV")
	private String email;

	public String getMaSV() {
		return maSV;
	}

	public void setMaSV(String maSV) {
		this.maSV = maSV;
	}

	public String getMaLop() {
		return maLop;
	}

	public void setMaLop(String maLop) {
		this.maLop = maLop;
	}

	public String getTenSV() {
		return tenSV;
	}

	public void setTenSV(String tenSV) {
		this.tenSV = tenSV;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public SinhVien(String maSV, String maLop, String tenSV, String sdt, String matKhau, String diaChi, String email) {
		super();
		this.maSV = maSV;
		this.maLop = maLop;
		this.tenSV = tenSV;
		this.sdt = sdt;
		this.matKhau = matKhau;
		this.diaChi = diaChi;
		this.email = email;
	}

	public SinhVien() {
		super();
	}

	@Override
	public String toString() {
		return "SinhVien [diaChi=" + diaChi + ", email=" + email + ", maLop=" + maLop + ", maSV=" + maSV + ", matKhau="
				+ matKhau + ", sdt=" + sdt + ", tenSV=" + tenSV + "]";
	}

}
