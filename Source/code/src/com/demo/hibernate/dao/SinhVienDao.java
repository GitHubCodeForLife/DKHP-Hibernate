package com.demo.hibernate.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.demo.hibernate.entity.KQDKHP;
import com.demo.hibernate.entity.SinhVien;
import com.demo.hibernate.utility.HibernateUtil;

public class SinhVienDao {
	public static SinhVien login(String mssv, String mk) {
		SinhVien sv = null;
		Session session = HibernateUtil.getSession();
		try {
			sv = session.get(SinhVien.class, mssv);
		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		if (sv == null)
			return null;
		if (sv.getMatKhau().equals(mk)) {
			System.out.println("Login success!");
			return sv;
		}
		return null;
	}

	public static List<SinhVien> layDanhSachSinhVien() {
		Session session = HibernateUtil.getSession();
		List<SinhVien> result = session.createQuery("from SinhVien", SinhVien.class).getResultList();
		session.close();
		return result;
	}

	public static SinhVien layThongTinSinhVien(String mssv) {
		SinhVien sv = null;
		Session session = HibernateUtil.getSession();
		try {
			sv = session.get(SinhVien.class, mssv);
		} catch (HibernateException ex) {
			// Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return sv;
	}

	public static Boolean updateSinhVien(SinhVien sv) {
		Session session = HibernateUtil.getSession();
		if (SinhVienDao.layThongTinSinhVien(sv.getMaSV()) == null) {
			System.out.println("Khong co giao vu");
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(sv);
			transaction.commit();
		} catch (HibernateException ex) {
			// Log the exception
			transaction.rollback();
			System.err.println(ex);
		} finally {
			session.close();
		}
//		// update lop
		LopDao.seftUpdate(sv.getLop());
		System.out.println("Update successfully!");
		return true;

	}

	public static boolean xaoSinhVien(String mssv) {
		Session session = HibernateUtil.getSession();
		SinhVien sv = SinhVienDao.layThongTinSinhVien(mssv);
		if (sv == null) {
			return false;
		}
		List<KQDKHP> list = sv.getKqdkhps();
		for (int i = 0; i < list.size(); i++) {
			KQDKHPDao.xaoKQDKHP(list.get(i));
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(sv);
			transaction.commit();
		} catch (HibernateException ex) {
			// Log the exception
			transaction.rollback();
			System.err.println(ex);
		} finally {
			session.close();
		}
//		// update lop
		LopDao.seftUpdate(sv.getLop());
		return true;
	}

	public static boolean themSinhVien(SinhVien sv) {
		Session session = HibernateUtil.getSession();
		if (SinhVienDao.layThongTinSinhVien(sv.getMaSV()) != null) {
			return false;
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(sv);
			transaction.commit();
		} catch (HibernateException ex) {
			// Log the exception
			transaction.rollback();
			System.err.println(ex);
		} finally {
			session.close();
		}
//		// update lop
		LopDao.seftUpdate(sv.getLop());
		return true;
	}

	public static boolean resetMatKhau(String mssv) {
		Session session = HibernateUtil.getSession();
		SinhVien sv = SinhVienDao.layThongTinSinhVien(mssv);
		if (sv == null) {
			System.out.println("Khong co sinh vien");
			return false;
		}
		sv.setMatKhau("123");
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(sv);
			transaction.commit();
		} catch (HibernateException ex) {
			// Log the exception
			transaction.rollback();
			System.err.println(ex);
		} finally {
			session.close();
		}
		System.out.println("reset passswork successfully!");
		return true;
	}

	public static List<SinhVien> timKiemSinhVien(String key) {
		List<SinhVien> result = null;
		Session session = HibernateUtil.getSession();
		try {
			String hql = "select sv from SinhVien sv where MASV like concat('%',:mssv,'%') or TENSV like concat('%',:tensv,'%') or DIACHISV like concat('%',:diachisv,'%')"
					+ " or MALOP like concat('%',:malop,'%')";
			Query query = session.createQuery(hql);
			query.setParameter("mssv", key);
			query.setParameter("tensv", key);
			query.setParameter("diachisv", key);
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

}
