package com.demo.hibernate.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.demo.hibernate.entity.HocPhan;
import com.demo.hibernate.entity.KQDKHP;
import com.demo.hibernate.entity.SinhVien;
import com.demo.hibernate.utility.HibernateUtil;

public class KQDKHPDao {
	public static KQDKHP layThongTinKQDKHP(HocPhan hp, SinhVien sv) {
		KQDKHP kq = null;
		Session session = HibernateUtil.getSession();
		try {
			kq = session.get(KQDKHP.class, new KQDKHP(sv, hp));
		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return kq;
	}

	public static boolean themKQDKHP(KQDKHP kq) {
		Session session = HibernateUtil.getSession();
		if (KQDKHPDao.layThongTinKQDKHP(kq.getHocPhan(), kq.getSinhVien()) != null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(kq);
			transaction.commit();

		} catch (HibernateException ex) {
			// Log the exception
			transaction.rollback();
			System.err.println(ex);
		} finally {
			session.close();
		}
		System.out.println("Khong chay dong nay");
		HocPhanDao.seftUpdadte(kq.getHocPhan());
		return true;
	}

	public static boolean xaoKQDKHP(SinhVien sv, HocPhan hp) {
		Session session = HibernateUtil.getSession();
		KQDKHP kq = KQDKHPDao.layThongTinKQDKHP(hp, sv);
		if (kq == null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(kq);
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

	public static Boolean updateKQDKHP(KQDKHP kq) {
		Session session = HibernateUtil.getSession();
		if (KQDKHPDao.layThongTinKQDKHP(kq.getHocPhan(), kq.getSinhVien()) == null) {
			System.out.println("Khong co giao vu");
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(kq);
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

	public static boolean xaoKQDKHP(KQDKHP kq) {
		Session session = HibernateUtil.getSession();
		kq = KQDKHPDao.layThongTinKQDKHP(kq.getHocPhan(), kq.getSinhVien());
		if (kq == null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(kq);
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
