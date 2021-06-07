package com.demo.hibernate.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.demo.hibernate.entity.DotDKHP;
import com.demo.hibernate.entity.HocKi;
import com.demo.hibernate.utility.HibernateUtil;

public class HocKiDao {
	public static List<HocKi> layDanhSachHocKi() {
		Session session = HibernateUtil.getSession();
		List<HocKi> result = session.createQuery("from HocKi", HocKi.class).getResultList();
		session.close();
		return result;
	}

	public static HocKi layThongTinHocKi(String maHK) {
		HocKi hk = null;
		Session session = HibernateUtil.getSession();
		try {
			hk = session.get(HocKi.class, maHK);
		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return hk;
	}

	public static Boolean updateHocKi(HocKi hk) {
		Session session = HibernateUtil.getSession();
		if (HocKiDao.layThongTinHocKi(hk.getMaHK()) == null) {
			System.out.println("Khong co giao vu");
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(hk);
			transaction.commit();
		} catch (HibernateException ex) {
			// Log the exception
			transaction.rollback();
			System.err.println(ex);
		} finally {
			session.close();
		}
		System.out.println("Update successfully!");
		return true;

	}

	public static boolean xaoHocKi(String maHK) {
		Session session = HibernateUtil.getSession();
		HocKi hk = HocKiDao.layThongTinHocKi(maHK);
		if (hk == null) {
			return false;
		}
		// Xoa DotDKHP
		List<DotDKHP> list = hk.getDotDKHPs();
		for (int i = 0; i < list.size(); i++) {
			DotDKHPDao.xaoDotDKHP(list.get(i).getMaDot());
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(hk);
			transaction.commit();
		} catch (HibernateException ex) {
			// Log the exception
			transaction.rollback();
			System.err.println(ex);
		} finally {
			session.close();
		}
		return true;
	}

	public static HocKi layHocKiHienTai() {

		HocKi result = null;
		Session session = HibernateUtil.getSession();
		try {
			String hql = "select gv from HocKi gv where IsCurrent = 1";
			Query query = session.createQuery(hql);
			result = (HocKi) query.list().get(0);
		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return result;
	}

	public static boolean themHocKi(HocKi hk) {
		Session session = HibernateUtil.getSession();
		if (HocKiDao.layThongTinHocKi(hk.getMaHK()) != null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(hk);
			transaction.commit();
		} catch (HibernateException ex) {
			// Log the exception
			transaction.rollback();
			System.err.println(ex);
		} finally {
			session.close();
		}
		return true;
	}

	public static boolean setCurrentHocKi(String maHK) {
		HocKi hk1 = HocKiDao.layThongTinHocKi(maHK);
		hk1.setCurrent(true);
		HocKi hk2 = HocKiDao.layHocKiHienTai();
		hk2.setCurrent(false);
//		if (hk1.tgBD().isAfter(LocalDate.now()) && hk1.getTgKT().isBefore(LocalDate.now())) {
//		} else
//			return false;

		if (!HocKiDao.updateHocKi(hk2))
			return false;
		if (!HocKiDao.updateHocKi(hk1))
			return false;
		return true;
	}

}
