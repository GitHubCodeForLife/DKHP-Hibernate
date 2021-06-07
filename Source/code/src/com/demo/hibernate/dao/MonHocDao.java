package com.demo.hibernate.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.demo.hibernate.entity.HocPhan;
import com.demo.hibernate.entity.MonHoc;
import com.demo.hibernate.utility.HibernateUtil;

public class MonHocDao {

	public static List<MonHoc> layDanhSachMonHoc() {
		Session session = HibernateUtil.getSession();
		List<MonHoc> result = session.createQuery("from MonHoc", MonHoc.class).getResultList();
		session.close();
		return result;
	}

	public static MonHoc layThongTinMonHoc(String maMH) {
		MonHoc mh = null;
		Session session = HibernateUtil.getSession();
		try {
			mh = session.get(MonHoc.class, maMH);
		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return mh;
	}

	public static Boolean updateMonHoc(MonHoc mh) {
		Session session = HibernateUtil.getSession();
		if (MonHocDao.layThongTinMonHoc(mh.getMaMH()) == null) {
			System.out.println("Khong co mon hoc");
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(mh);
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

	public static boolean xaoMonHoc(String maMH) {
		Session session = HibernateUtil.getSession();
		MonHoc mh = MonHocDao.layThongTinMonHoc(maMH);
		if (mh == null) {
			return false;
		}
		// Xoa HP
		List<HocPhan> list = mh.getHocPhans();
		for (int i = 0; i < list.size(); i++) {
			HocPhan hp = list.get(i);
			HocPhanDao.xaoHocPhan(hp.getMaHP());
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(mh);
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

	public static boolean themMonHoc(MonHoc mh) {
		Session session = HibernateUtil.getSession();
		if (MonHocDao.layThongTinMonHoc(mh.getMaMH()) != null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(mh);
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

	@SuppressWarnings("unchecked")
	public static List<MonHoc> timKiemMonHoc(String key) {
		List<MonHoc> result = null;
		Session session = HibernateUtil.getSession();
		try {
			String hql = "select mh from MonHoc mh where MAMH like concat('%',:mamh,'%') or TENMH like concat('%',:tenmh,'%')";
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(hql);
			query.setParameter("mamh", key);
			query.setParameter("tenmh", key);
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
