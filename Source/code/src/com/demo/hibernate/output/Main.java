/**#################################################################
 *
 **#################################################################*/
package com.demo.hibernate.output;

/**
 * #################################################################
 *
 * @author ANICET ERIC KOUAME
 * @Date: 20 mars 2017
 * @Description: Main
 *               #################################################################
 */

public class Main {

	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {

//		GiaoVuDao gvDao = new GiaoVuDao();
//		List<GiaoVu> gvs = gvDao.getGiaoVus();
//		for (int i = 0; i < gvs.size(); i++) {
//			System.out.println(gvs.get(i).toString());
//		}
//		LopDao gvDao = new LopDao();
//		List<Lop> lops = gvDao.getAuthors();
//		for (int i = 0; i < lops.size(); i++) {
//			System.out.println(lops.get(i).toString());
//		}

//		GiaoVuDao gvDao = new GiaoVuDao();
//		GiaoVu gv = gvDao.login("giaovu@gmail.com", "345");
//		if (gv != null) {
//			System.out.println(gv);
//		}
//		gv.setDIACHIGV("Tp HCM");
//		gv.setMKGV("345");
//		gvDao.resetMatKhau(gv.getTKGV());

//		List<GiaoVu> gvs = gvDao.timKiemGiaoVu("nguyen");
//		for (int i = 0; i < gvs.size(); i++) {
//			System.out.println(gvs.get(i).toString());
//		}

//		SinhVienDao svDao = new SinhVienDao();
//		List<SinhVien> svs = svDao.layDanhSachSinhVien();
//		for (int i = 0; i < svs.size(); i++) {
//			System.out.println(svs.get(i).toString());
//		}
//		svDao.login("18120629", "123");
//		System.out.println(SinhVienDao.layThongTinSinhVien("18120629").getLop());
//		SinhVien sv = svDao.layThongTinSinhVien("18120629");
//		sv.setMaSV("18120123");
//		svDao.themSinhVien(sv);
//		List<SinhVien> svs = svDao.timKiemSinhVien("Nguyễn");
//		for (int i = 0; i < svs.size(); i++) {
//			System.out.println(svs.get(i).toString());
//		}

//		LopDao ld = new LopDao();
//////		List<Lop> lops = ld.layDanhSachLop();
//////		for (int i = 0; i < lops.size(); i++) {
//////			System.out.println(lops.get(i).toString());
//////		}
//		Lop lop = new Lop("17CTT2", 100, 120);
//		System.out.println("List Sinh Vien theo LOP");
//		List<SinhVien> lists = ld.layThongTinLop("18CTT5").getSinhViens();
//		for (int i = 0; i < lists.size(); i++) {
//			System.out.println(lists.get(i));
//		}
//		HocKiDao hkd = new HocKiDao();
//		List<HocKi> lists1 = hkd.layDanhSachHocKi();
//		for (int i = 0; i < lists1.size(); i++) {
//			System.out.println(lists1.get(i));
//		}
//
//		List<DotDKHP> lists = hkd.layHocKiHienTai().getDotDKHPs();
//		for (int i = 0; i < lists.size(); i++) {
//			System.out.println(lists.get(i));
//		}
//		hkd.setCurrentHocKi("HK2-2021");

//		MonHocDao mhDao = new MonHocDao();
//		List<HocPhan> lists = MonHocDao.layThongTinMonHoc("CSC10001").getHocPhans();
//		// List<MonHoc> lists = mhDao.timKiemMonHoc("Toan");
//		for (int i = 0; i < lists.size(); i++) {
//			System.out.println(lists.get(i));
//		}
//		System.out.println(mhDao.layThongTinMonHoc("CSC10001"));

//		DotDKHPDao d = new DotDKHPDao();
//		List<DotDKHP> lists = d.layDanhSachDotDKHP();
//		for (int i = 0; i < lists.size(); i++) {
//			System.out.println(lists.get(i));
//		}
//		List<HocPhan> lists = DotDKHPDao.layThongTinDotDKHP("D1").getHocPhans();
//		for (int i = 0; i < lists.size(); i++) {
//			System.out.println(lists.get(i));
//		}

//		// Lay DSHP
//		HocPhanDao hpd = new HocPhanDao();
//		List<HocPhan> lists = hpd.timKiemHocPhan("Hp01");
//		for (int i = 0; i < lists.size(); i++) {
//			System.out.println(lists.get(i));
//		}
//		List<KQDKHP> lists = SinhVienDao.layThongTinSinhVien("18120629").getKqdkhps();
//		for (int i = 0; i < lists.size(); i++) {
//			System.out.println(lists.get(i));
//		}
		new LoginFrame();
	}

}
