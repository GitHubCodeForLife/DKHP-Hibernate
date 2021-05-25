/**
 *
 */
package com.demo.hibernate.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GIAO_VU")
public class GiaoVu implements Serializable {

	@Id
	private String TKGV;

	@Override
	public String toString() {
		return "GiaoVu [DIACHIGV=" + DIACHIGV + ", EMAILGV=" + EMAILGV + ", MKGV=" + MKGV + ", TENGV=" + TENGV
				+ ", TKGV=" + TKGV + "]";
	}

	private String MKGV;

	private String DIACHIGV;
	private String EMAILGV;
	private String TENGV;

	public String getTKGV() {
		return TKGV;
	}

	public void setTKGV(String tKGV) {
		TKGV = tKGV;
	}

	public String getMKGV() {
		return MKGV;
	}

	public void setMKGV(String mKGV) {
		MKGV = mKGV;
	}

	public String getDIACHIGV() {
		return DIACHIGV;
	}

	public void setDIACHIGV(String dIACHIGV) {
		DIACHIGV = dIACHIGV;
	}

	public String getEMAILGV() {
		return EMAILGV;
	}

	public void setEMAILGV(String eMAILGV) {
		EMAILGV = eMAILGV;
	}

	public String getTENGV() {
		return TENGV;
	}

	public void setTENGV(String tENGV) {
		TENGV = tENGV;
	}

	public GiaoVu(String tKGV, String mKGV, String dIACHIGV, String eMAILGV, String tENGV) {
		super();
		TKGV = tKGV;
		MKGV = mKGV;
		DIACHIGV = dIACHIGV;
		EMAILGV = eMAILGV;
		TENGV = tENGV;
	}

	public GiaoVu() {
		super();
	}

}
