package com.demo.hibernate.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.demo.hibernate.entity.DotDKHP;
import com.demo.hibernate.utility.HibernateUtil;

public class DotDKHPDao {
	public static List<DotDKHP> layDanhSachDotDKHP() {
		Session session = HibernateUtil.getSession();
		List<DotDKHP> result = session.createQuery("from DotDKHP", DotDKHP.class).getResultList();
		session.close();
		return result;
	}

	public static DotDKHP layDotDKHPHienTai(LocalDateTime d) {
		List<DotDKHP> lists = DotDKHPDao.layDanhSachDotDKHP();
		for (int i = 0; i < lists.size(); i++) {
			DotDKHP dot = lists.get(i);
			if (d.isAfter(dot.getTbBD()) && d.isBefore(dot.getTbKT())) {
				return dot;
			}
		}
		return null;
	}

	public static DotDKHP layThongTinDotDKHP(String maDot) {
		DotDKHP dotDKHP = null;
		Session session = HibernateUtil.getSession();
		try {
			dotDKHP = session.get(DotDKHP.class, maDot);
		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return dotDKHP;
	}

	public static boolean themDotDKHP(DotDKHP dotDKHP) {
		Session session = HibernateUtil.getSession();
		if (DotDKHPDao.layThongTinDotDKHP(dotDKHP.getMaDot()) != null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(dotDKHP);
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

	public static boolean xaoDotDKHP(String maDot) {
		Session session = HibernateUtil.getSession();
		DotDKHP dot = DotDKHPDao.layThongTinDotDKHP(maDot);
		if (dot == null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(dot);
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
