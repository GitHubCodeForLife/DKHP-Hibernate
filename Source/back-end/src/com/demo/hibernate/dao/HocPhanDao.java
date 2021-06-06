package com.demo.hibernate.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.demo.hibernate.entity.HocPhan;
import com.demo.hibernate.entity.KQDKHP;
import com.demo.hibernate.utility.HibernateUtil;

public class HocPhanDao {
	public static List<HocPhan> layDanhSachHocPhan() {
		Session session = HibernateUtil.getSession();
		List<HocPhan> result = session.createQuery("from HocPhan", HocPhan.class).getResultList();
		session.close();
		return result;
	}

	public static HocPhan layThongTinHocPhan(String maHP) {
		HocPhan hp = null;
		Session session = HibernateUtil.getSession();
		try {
			hp = session.get(HocPhan.class, maHP);
		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return hp;
	}

	public static Boolean updateHocPhan(HocPhan hp) {
		Session session = HibernateUtil.getSession();
		if (HocPhanDao.layThongTinHocPhan(hp.getMaHP()) == null) {
			System.out.println("Khong co hoc Phan");
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(hp);
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

	public static boolean xaoHocPhan(String maHP) {
		Session session = HibernateUtil.getSession();
		HocPhan hp = HocPhanDao.layThongTinHocPhan(maHP);
		if (hp == null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(hp);
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

	public static boolean themHocPhan(HocPhan hp) {
		Session session = HibernateUtil.getSession();
		if (HocPhanDao.layThongTinHocPhan(hp.getMaHP()) != null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(hp);
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

	public static List<HocPhan> timKiemHocPhan(String key) {
		List<HocPhan> result = null;
		Session session = HibernateUtil.getSession();
		try {
			String hql = "select hp from HocPhan hp where MAHP like concat('%',:mahp,'%') or MAMH like concat('%',:mamh,'%') or  TENPHONG like concat('%',:tenphong,'%') "
					+ "or  TENGVLT like concat('%',:tengvlt,'%') or   MALOP like concat('%',:malop,'%') ";
			Query query = session.createQuery(hql);
			query.setParameter("mahp", key);
			query.setParameter("mamh", key);
			query.setParameter("tenphong", key);
			query.setParameter("tengvlt", key);
			query.setParameter("malop", key);
			result = query.list();
		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return result;
	}

	public static void seftUpdadte(HocPhan hp) {
		List<KQDKHP> list = hp.getKqdkhps();
		for (int i = 0; i < list.size(); i++)
			System.out.println(list.get(i));
		hp.setSlot(hp.getSlotToiDa() - list.size());
		HocPhanDao.updateHocPhan(hp);
		System.out.println("Hoc Phan sau khi update: " + hp);
	}

}
