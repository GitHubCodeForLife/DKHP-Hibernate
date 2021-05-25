package com.demo.hibernate.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.demo.hibernate.entity.Lop;
import com.demo.hibernate.utility.HibernateUtil;

public class LopDao {
	public List<Lop> layDanhSachLop() {
		Session session = HibernateUtil.getSession();
		List<Lop> result = session.createQuery("from Lop", Lop.class).getResultList();
		session.close();
		return result;
	}

	public static Lop layThongTinLop(String maLop) {
		Lop lop = null;
		Session session = HibernateUtil.getSession();
		try {
			lop = session.get(Lop.class, maLop);
		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return lop;
	}

	public static boolean xaoLop(String maLop) {
		Session session = HibernateUtil.getSession();
		Lop lop = LopDao.layThongTinLop(maLop);
		if (lop == null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(lop);
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

	public static boolean themLop(Lop lop) {
		Session session = HibernateUtil.getSession();
		if (LopDao.layThongTinLop(lop.getMaLop()) != null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(lop);
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

}