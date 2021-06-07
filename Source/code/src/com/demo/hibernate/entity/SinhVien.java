/**
 *
 */
package com.demo.hibernate.entity;

import java.io.Serializable;
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

@Entity
@Table(name = "sinh_vien")
public class SinhVien implements Serializable {

	@Id
	@Column(name = "MASV")
	private String maSV;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MALOP", nullable = false)
	private Lop lop;
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

	@Column(name = "GIOITINH")
	private Boolean gioiTinh;

	public SinhVien(String maSV, Lop lop, String tenSV, String sdt, String matKhau, String diaChi, String email,
			Boolean gioiTinh) {
		super();
		this.maSV = maSV;
		this.lop = lop;
		this.tenSV = tenSV;
		this.sdt = sdt;
		this.matKhau = matKhau;
		this.diaChi = diaChi;
		this.email = email;
		this.gioiTinh = gioiTinh;
	}

	public Boolean getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(Boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "sinhVien")
	private List<KQDKHP> kqdkhps = new ArrayList<>();

	public List<KQDKHP> getKqdkhps() {
		return kqdkhps;
	}

	public void setKqdkhps(List<KQDKHP> kqdkhps) {
		this.kqdkhps = kqdkhps;
	}

	public String getMaSV() {
		return maSV;
	}

	public void setMaSV(String maSV) {
		this.maSV = maSV;
	}

	public Lop getLop() {
		return lop;
	}

	public void setLop(Lop lop) {
		this.lop = lop;
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

	@Override
	public String toString() {
		return "SinhVien [maSV=" + maSV + ", lop=" + lop + ", tenSV=" + tenSV + ", sdt=" + sdt + ", matKhau=" + matKhau
				+ ", diaChi=" + diaChi + ", email=" + email + ", gioiTinh=" + gioiTinh;
	}

	public SinhVien() {
		super();
	}

}
