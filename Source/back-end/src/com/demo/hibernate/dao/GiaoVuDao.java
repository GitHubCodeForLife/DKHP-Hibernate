/**#################################################################
 *
 **#################################################################*/
package com.demo.hibernate.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.demo.hibernate.entity.GiaoVu;
import com.demo.hibernate.utility.HibernateUtil;

public class GiaoVuDao {

	public GiaoVu login(String tkgv, String mkgv) {
		GiaoVu gv = null;
		Session session = HibernateUtil.getSession();
		try {
			gv = session.get(GiaoVu.class, tkgv);
		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		if (gv.getMKGV().equals(mkgv)) {
			System.out.println("Login success!");
			return gv;
		}
		return null;
	}

	public List<GiaoVu> layDanhSachGiaoVu() {
		Session session = HibernateUtil.getSession();
		List<GiaoVu> result = session.createQuery("from GiaoVu", GiaoVu.class).getResultList();
		session.close();
		return result;
	}

	public static GiaoVu layThongTinGiaoVu(String tkgv) {
		GiaoVu gv = null;
		Session session = HibernateUtil.getSession();
		try {
			gv = session.get(GiaoVu.class, tkgv);
		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return gv;
	}

	public Boolean updateGiaoVu(GiaoVu gv) {
		Session session = HibernateUtil.getSession();
		if (GiaoVuDao.layThongTinGiaoVu(gv.getTKGV()) == null) {
			System.out.println("Khong co giao vu");
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(gv);
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

	public static boolean xaoGiaoVu(String tkgv) {
		Session session = HibernateUtil.getSession();
		GiaoVu gv = GiaoVuDao.layThongTinGiaoVu(tkgv);
		if (gv == null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(gv);
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

	public static boolean themGiaoVu(GiaoVu gv) {
		Session session = HibernateUtil.getSession();
		if (GiaoVuDao.layThongTinGiaoVu(gv.getTKGV()) != null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(gv);
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

	public void resetMatKhau(String tkgv) {
		Session session = HibernateUtil.getSession();
		GiaoVu gv = GiaoVuDao.layThongTinGiaoVu(tkgv);
		if (gv == null) {
			System.out.println("Khong co giao vu");
			return;
		}
		gv.setMKGV("123");
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(gv);
			transaction.commit();
		} catch (HibernateException ex) {
			// Log the exception
			transaction.rollback();
			System.err.println(ex);
		} finally {
			session.close();
		}
		System.out.println("reset passswork successfully!");
	}

	public List<GiaoVu> timKiemGiaoVu(String key) {
		List<GiaoVu> result = null;
		Session session = HibernateUtil.getSession();
		try {
			String hql = "select gv from GiaoVu gv where TKGV like concat('%',:tkgv,'%') or TENGV like concat('%',:tengv,'%')";
			Query query = session.createQuery(hql);
			query.setParameter("tkgv", key);
			query.setParameter("tengv", key);
			result = query.list();
		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return result;
	}

}
