DROP DATABASE IF EXISTS `QuanLySinhVien`;
CREATE DATABASE `QuanLySinhVien`; 
USE `QuanLySinhVien`;

drop table if exists DOT_DK_HP;
drop table if exists GIAO_VU;
drop table if exists HOC_KI;
drop table if exists HOC_PHAN;
drop table if exists KQDKHP;
drop table if exists LOP;
drop table if exists MON_HOC;
drop table if exists SINH_VIEN;

/*==============================================================*/
/* Table: DOT_DK_HP                                             */
/*==============================================================*/
create table DOT_DK_HP
(
   MADOT                varchar(30) not null  comment '',
   MAHOCKI              varchar(30) not null  comment '',
   TGBATDAU             datetime  comment '',
   TGKETTHUC            datetime  comment '',
   primary key (MADOT)
);

/*==============================================================*/
/* Table: GIAO_VU                                               */
/*==============================================================*/
create table GIAO_VU
(
   TKGV                 varchar(30) not null  comment '',
   MKGV                 varchar(30)  comment '',
   DIACHIGV             varchar(30)  comment '',
   EMAILGV              varchar(40)  comment '',
   TENGV                varchar(30)  comment '',
   primary key (TKGV)
);

/*==============================================================*/
/* Table: HOC_KI                                                */
/*==============================================================*/
create table HOC_KI
(
   MAHOCKI              varchar(30) not null  comment '',
   IsCurrent            boolean,
   TENHOCKI             varchar(20)  comment '',
   NAM                  varchar(20)  comment '',
   HKBATDAU             date  comment '',
   HKKETTHUC            date  comment '',
   primary key (MAHOCKI)
);

/*==============================================================*/
/* Table: HOC_PHAN                                              */
/*==============================================================*/
create table HOC_PHAN
(
   TENGVLT              varchar(30)  comment '',
   THU                  int  comment '',
   SLOT                 int  comment '',
   CA                   int  comment '',
   TENPHONG             varchar(30)  comment '',
   MAHP                 varchar(30) not null  comment '',
   MALOP                varchar(30) not null  comment '',
   MADOT                varchar(30)  comment '',
   MAMH                 varchar(30) not null  comment '',
   SLOTTOIDA				int comment '',
   primary key (MAHP)
);

/*==============================================================*/
/* Table: KQDKHP                                                */
/*==============================================================*/
create table KQDKHP
(
   MASV                 varchar(30)  comment '',
   MAHP                 varchar(30)  comment '',
   TGDANGKY            	datetime  comment ''
);

/*==============================================================*/
/* Table: LOP                                                   */
/*==============================================================*/
create table LOP
(
   MALOP                varchar(30) not null  comment '',
   TONGSV               int  comment '',
   TONGNAM              int  comment '',
   primary key (MALOP)
);

/*==============================================================*/
/* Table: MON_HOC                                               */
/*==============================================================*/
create table MON_HOC
(
   MAMH                 varchar(30) not null  comment '',
   TENMH                varchar(30) not null  comment '',
   SOTINCHI             int not null  comment '',
   primary key (MAMH)
);

/*==============================================================*/
/* Table: SINH_VIEN                                             */
/*==============================================================*/
create table SINH_VIEN
(
   MASV                 varchar(30) not null  comment '',
   MALOP                varchar(30) not null  comment '',
   TENSV                varchar(30)  comment '',
   SDT                  varchar(10) comment '',
   MKSV                 varchar(30)  comment '',
   DIACHISV             varchar(30)  comment '',
   EMAILSV              varchar(30)  comment '',
   GIOITINH 			boolean comment '',
   primary key (MASV)
);


alter table HOC_PHAN add constraint FK_HOC_PHAN_CO_MON_HOC foreign key (MAMH)
      references MON_HOC (MAMH) on delete restrict on update restrict;

alter table HOC_PHAN add constraint FK_HOC_PHAN_RELATIONS_DOT_DK_H foreign key (MADOT)
      references DOT_DK_HP (MADOT) on delete restrict on update restrict;

-- alter table HOC_PHAN add constraint FK_HOC_PHAN_THUOC_LOP foreign key (MALOP)
--       references LOP (MALOP) on delete restrict on update restrict;

alter table KQDKHP add constraint FK_KQDKHP_RELATIONS_SINH_VIEN foreign key (MASV)
      references SINH_VIEN (MASV) on delete restrict on update restrict;

alter table KQDKHP add constraint FK_KQDKHP_RELATIONS_HOC_PHAN foreign key (MAHP)
      references HOC_PHAN (MAHP) on delete restrict on update restrict;

alter table SINH_VIEN add constraint FK_SINH_VIEN_THUOC_LOP_LOP foreign key (MALOP)
      references LOP (MALOP) on delete restrict on update restrict;


alter table DOT_DK_HP add constraint FK_DOTDKHP_THUOC_HOCKI foreign key (MAHOCKI)
      references HOC_KI (MAHOCKI) on delete restrict on update restrict;


INSERT INTO `quanlysinhvien`.`lop` (`MALOP`, `TONGSV`, `TONGNAM`) VALUES ('18CTT1', '110', '90');
INSERT INTO `quanlysinhvien`.`lop` (`MALOP`, `TONGSV`, `TONGNAM`) VALUES ('18CTT2', '120', '60');
INSERT INTO `quanlysinhvien`.`lop` (`MALOP`, `TONGSV`, `TONGNAM`) VALUES ('18CTT3', '130', '70');
INSERT INTO `quanlysinhvien`.`lop` (`MALOP`, `TONGSV`, `TONGNAM`) VALUES ('18CTT4', '120', '80');
INSERT INTO `quanlysinhvien`.`lop` (`MALOP`, `TONGSV`, `TONGNAM`) VALUES ('18CTT5', '131', '40');
INSERT INTO `quanlysinhvien`.`lop` (`MALOP`, `TONGSV`, `TONGNAM`) VALUES ('19CTT1', '121', '40');
INSERT INTO `quanlysinhvien`.`lop` (`MALOP`, `TONGSV`, `TONGNAM`) VALUES ('19CTT2', '132', '80');
INSERT INTO `quanlysinhvien`.`lop` (`MALOP`, `TONGSV`, `TONGNAM`) VALUES ('19CTT3', '130', '60');
INSERT INTO `quanlysinhvien`.`lop` (`MALOP`, `TONGSV`, `TONGNAM`) VALUES ('19CTT4', '150', '90');
INSERT INTO `quanlysinhvien`.`lop` (`MALOP`, `TONGSV`, `TONGNAM`) VALUES ('19CTT5', '130', '100');

INSERT INTO `quanlysinhvien`.`sinh_vien` (`MASV`, `MALOP`, `TENSV`, `SDT`, `MKSV`, `DIACHISV`, `EMAILSV`,`GIOITINH`) VALUES ('17120629', '18CTT5', 'Ha Ba Quan', '0961050667', '123', 'Da Nang', '18120629@student.hcmus.edu.vn','1');
INSERT INTO `quanlysinhvien`.`sinh_vien` (`MASV`, `MALOP`, `TENSV`, `SDT`, `MKSV`, `DIACHISV`, `EMAILSV`,`GIOITINH`) VALUES ('17120622', '18CTT5', 'Ngo Van A', '0810506675', '123', 'TP HCM', '18120622@student.hcmus.edu.vn','0');
INSERT INTO `quanlysinhvien`.`sinh_vien` (`MASV`, `MALOP`, `TENSV`, `SDT`, `MKSV`, `DIACHISV`, `EMAILSV`,`GIOITINH`) VALUES ('17120623', '18CTT4', 'Dai Ba Sach', '0810506676', '123', 'Dak Lak', '18120623@student.hcmus.edu.vn','1');
INSERT INTO `quanlysinhvien`.`sinh_vien` (`MASV`, `MALOP`, `TENSV`, `SDT`, `MKSV`, `DIACHISV`, `EMAILSV`,`GIOITINH`) VALUES ('19120624', '18CTT4', 'Ton Sach', '0810506677', '123', 'Quang Nam', '18120624@student.hcmus.edu.vn','1');
INSERT INTO `quanlysinhvien`.`sinh_vien` (`MASV`, `MALOP`, `TENSV`, `SDT`, `MKSV`, `DIACHISV`, `EMAILSV`,`GIOITINH`) VALUES ('19120625', '18CTT5', 'Ngo Quyen', '0810506678', '123', 'An Giang', '18120625@student.hcmus.edu.vn','0');
INSERT INTO `quanlysinhvien`.`sinh_vien` (`MASV`, `MALOP`, `TENSV`, `SDT`, `MKSV`, `DIACHISV`, `EMAILSV`,`GIOITINH`) VALUES ('19120626', '19CTT1', 'Nguyen Binh', '0810506679', '123', 'Ha Noi', '18120626@student.hcmus.edu.vn','1');
INSERT INTO `quanlysinhvien`.`sinh_vien` (`MASV`, `MALOP`, `TENSV`, `SDT`, `MKSV`, `DIACHISV`, `EMAILSV`,`GIOITINH`) VALUES ('19120627', '19CTT1', 'Dai Nam', '081050685', '123', 'Ha Noi', '18120627@student.hcmus.edu.vn','1');
INSERT INTO `quanlysinhvien`.`sinh_vien` (`MASV`, `MALOP`, `TENSV`, `SDT`, `MKSV`, `DIACHISV`, `EMAILSV`,`GIOITINH`) VALUES ('18120629', '18CTT5', 'Tran Van Tu', '0961050667', '123', 'Ha Noi', '18120629@student.hcmus.edu.vn','1');
INSERT INTO `quanlysinhvien`.`sinh_vien` (`MASV`, `MALOP`, `TENSV`, `SDT`, `MKSV`, `DIACHISV`, `EMAILSV`,`GIOITINH`) VALUES ('18120622', '18CTT5', 'Nguyễn Văn A', '0810506675', '123', 'Ha Noi', '18120622@student.hcmus.edu.vn','0');
INSERT INTO `quanlysinhvien`.`sinh_vien` (`MASV`, `MALOP`, `TENSV`, `SDT`, `MKSV`, `DIACHISV`, `EMAILSV`,`GIOITINH`) VALUES ('18120623', '18CTT4', 'Nguyễn Văn B', '0810506676', '123', 'Ha Noi', '18120623@student.hcmus.edu.vn','1');
INSERT INTO `quanlysinhvien`.`sinh_vien` (`MASV`, `MALOP`, `TENSV`, `SDT`, `MKSV`, `DIACHISV`, `EMAILSV`,`GIOITINH`) VALUES ('18120624', '18CTT4', 'Nguyễn Văn C', '0810506677', '123', 'Ha Noi', '18120624@student.hcmus.edu.vn','1');
INSERT INTO `quanlysinhvien`.`sinh_vien` (`MASV`, `MALOP`, `TENSV`, `SDT`, `MKSV`, `DIACHISV`, `EMAILSV`,`GIOITINH`) VALUES ('18120625', '18CTT5', 'Nguyễn Văn D', '0810506678', '123', 'Ha Noi', '18120625@student.hcmus.edu.vn','0');
INSERT INTO `quanlysinhvien`.`sinh_vien` (`MASV`, `MALOP`, `TENSV`, `SDT`, `MKSV`, `DIACHISV`, `EMAILSV`,`GIOITINH`) VALUES ('18120626', '19CTT1', 'Nguyễn Văn E', '0810506679', '123', 'Ha Noi', '18120626@student.hcmus.edu.vn','1');
INSERT INTO `quanlysinhvien`.`sinh_vien` (`MASV`, `MALOP`, `TENSV`, `SDT`, `MKSV`, `DIACHISV`, `EMAILSV`,`GIOITINH`) VALUES ('18120627', '19CTT1', 'Nguyễn Văn F', '081050685', '123', 'Ha Noi', '18120627@student.hcmus.edu.vn','1');


 
INSERT INTO `quanlysinhvien`.`giao_vu` (`TKGV`, `MKGV`, `DIACHIGV`, `EMAILGV`, `TENGV`) VALUES ('giaovu@gmail.com', '123', 'HCM', 'giaovu@gmail.com', 'Giao Vu');
INSERT INTO `quanlysinhvien`.`giao_vu` (`TKGV`, `MKGV`, `DIACHIGV`, `EMAILGV`, `TENGV`) VALUES ('giaovu1@gmail.com', '123', 'HCM', 'giaovu1@gmail.com', 'Giao Vu1');
INSERT INTO `quanlysinhvien`.`giao_vu` (`TKGV`, `MKGV`, `DIACHIGV`, `EMAILGV`, `TENGV`) VALUES ('giaovu2@gmail.com', '123', 'HCM', 'giaovu2@gmail.com', 'Giao Vu2');


INSERT INTO `quanlysinhvien`.`mon_hoc` (`MAMH`, `TENMH`, `SOTINCHI`) VALUES ('CSC10001', 'Cơ Sở Lập Trình', '4');
INSERT INTO `quanlysinhvien`.`mon_hoc` (`MAMH`, `TENMH`, `SOTINCHI`) VALUES ('CSC10002', 'Nhập môn lập trình', '4');
INSERT INTO `quanlysinhvien`.`mon_hoc` (`MAMH`, `TENMH`, `SOTINCHI`) VALUES ('CSC10003', 'Lâp trình ứng dụng Java', '4');
INSERT INTO `quanlysinhvien`.`mon_hoc` (`MAMH`, `TENMH`, `SOTINCHI`) VALUES ('CSC10004', 'Toán rời rạc', '4');
INSERT INTO `quanlysinhvien`.`mon_hoc` (`MAMH`, `TENMH`, `SOTINCHI`) VALUES ('CSC10005', 'Toán cao cấp', '3');
INSERT INTO `quanlysinhvien`.`mon_hoc` (`MAMH`, `TENMH`, `SOTINCHI`) VALUES ('CSC10006', 'Cơ sở dữ liệu', '4');
INSERT INTO `quanlysinhvien`.`mon_hoc` (`MAMH`, `TENMH`, `SOTINCHI`) VALUES ('CSC10007', 'Lập trình web', '4');
INSERT INTO `quanlysinhvien`.`mon_hoc` (`MAMH`, `TENMH`, `SOTINCHI`) VALUES ('CSC10008', 'Kiến tập nghê nghiệp', '2');
INSERT INTO `quanlysinhvien`.`mon_hoc` (`MAMH`, `TENMH`, `SOTINCHI`) VALUES ('CSC10009', 'Thể Dục 1', '3');
INSERT INTO `quanlysinhvien`.`mon_hoc` (`MAMH`, `TENMH`, `SOTINCHI`) VALUES ('CSC100010', 'Thể Dục 2', '3');
INSERT INTO `quanlysinhvien`.`mon_hoc` (`MAMH`, `TENMH`, `SOTINCHI`) VALUES ('CSC100011', 'Toán thống kê', '4');
INSERT INTO `quanlysinhvien`.`mon_hoc` (`MAMH`, `TENMH`, `SOTINCHI`) VALUES ('CSC100012', 'Toán c1', '4');
INSERT INTO `quanlysinhvien`.`mon_hoc` (`MAMH`, `TENMH`, `SOTINCHI`) VALUES ('CSC100013', 'Mạng máy tính', '3');
INSERT INTO `quanlysinhvien`.`mon_hoc` (`MAMH`, `TENMH`, `SOTINCHI`) VALUES ('CSC100014', 'Kiến trúc máy tính', '4');
INSERT INTO `quanlysinhvien`.`mon_hoc` (`MAMH`, `TENMH`, `SOTINCHI`) VALUES ('CSC100015', 'Hệ điều hành', '4');
INSERT INTO `quanlysinhvien`.`mon_hoc` (`MAMH`, `TENMH`, `SOTINCHI`) VALUES ('CSC100016', 'Dạy Nghề', '2');


INSERT INTO `quanlysinhvien`.`hoc_ki` (`MAHOCKI`, `TENHOCKI`, `NAM`, `HKBATDAU`, `HKKETTHUC`,`IsCurrent`) VALUES ('HK1-2020', 'Học kỳ 1', '2020', '2020-01-10', '2020-05-01','0');
INSERT INTO `quanlysinhvien`.`hoc_ki` (`MAHOCKI`, `TENHOCKI`, `NAM`, `HKBATDAU`, `HKKETTHUC`,`IsCurrent`) VALUES ('HK2-2020',  'Học kỳ 2', '2020', '2020-05-11', '2020-11-11','0');
INSERT INTO `quanlysinhvien`.`hoc_ki` (`MAHOCKI`, `TENHOCKI`, `NAM`, `HKBATDAU`, `HKKETTHUC`,`IsCurrent`) VALUES ('HK1-2021', 'Học kỳ 1', '2021', '2021-01-10', '2021-05-01','0');
INSERT INTO `quanlysinhvien`.`hoc_ki` (`MAHOCKI`, `TENHOCKI`, `NAM`, `HKBATDAU`, `HKKETTHUC`,`IsCurrent`) VALUES ('HK2-2021',  'Học kỳ 2', '2021', '2021-05-11', '2021-11-11','1');


INSERT INTO `quanlysinhvien`.`dot_dk_hp` (`MADOT`, `TGBATDAU`, `TGKETTHUC`,`MAHOCKI`) VALUES ('D3', '2021-05-01 08:00:00', '2021-07-15 23:59:59','HK2-2021');
INSERT INTO `quanlysinhvien`.`dot_dk_hp` (`MADOT`, `TGBATDAU`, `TGKETTHUC`,`MAHOCKI`) VALUES ('D1', '2021-02-01 08:00:00', '2021-02-15 23:59:59','HK1-2021');
INSERT INTO `quanlysinhvien`.`dot_dk_hp` (`MADOT`, `TGBATDAU`, `TGKETTHUC`,`MAHOCKI`) VALUES ('D2', '2021-03-01 08:00:00', '2021-03-15 23:59:59','HK2-2021');


INSERT INTO `quanlysinhvien`.`hoc_phan` (`TENGVLT`, `THU`, `SLOT`, `CA`, `TENPHONG`, `MAHP`, `MALOP`, `MADOT`, `MAMH`,`SLOTTOIDA`) VALUES ('Thái Hùng Văn', '2', '99', '1', 'E101', 'HP01', '18-21', 'D3', 'CSC10001', '100');
INSERT INTO `quanlysinhvien`.`hoc_phan` (`TENGVLT`, `THU`, `SLOT`, `CA`, `TENPHONG`, `MAHP`, `MALOP`, `MADOT`, `MAMH`,`SLOTTOIDA`) VALUES ('Ngô Bá Khánh', '3', '99', '2', 'E102', 'HP02', '18-21', 'D3', 'CSC10002', '100');
INSERT INTO `quanlysinhvien`.`hoc_phan` (`TENGVLT`, `THU`, `SLOT`, `CA`, `TENPHONG`, `MAHP`, `MALOP`, `MADOT`, `MAMH`,`SLOTTOIDA`) VALUES ('Nguyễn Văn Khánh', '4', '100', '3', 'E103', 'HP03', '18-21', 'D3', 'CSC10003', '100');
INSERT INTO `quanlysinhvien`.`hoc_phan` (`TENGVLT`, `THU`, `SLOT`, `CA`, `TENPHONG`, `MAHP`, `MALOP`, `MADOT`, `MAMH`,`SLOTTOIDA`) VALUES ('Ngô Quốc Đạt', '5', '100', '3', 'F101', 'HP04', '18-21' ,'D1', 'CSC10004', '100');
INSERT INTO `quanlysinhvien`.`hoc_phan` (`TENGVLT`, `THU`, `SLOT`, `CA`, `TENPHONG`, `MAHP`, `MALOP`, `MADOT`, `MAMH`,`SLOTTOIDA`) VALUES ('Cao Huy Cường', '6', '100', '0', 'F102', 'HP05', '18-21', 'D1', 'CSC10005', '100');
INSERT INTO `quanlysinhvien`.`hoc_phan` (`TENGVLT`, `THU`, `SLOT`, `CA`, `TENPHONG`, `MAHP`, `MALOP`, `MADOT`, `MAMH`,`SLOTTOIDA`) VALUES ('Đại Bá Ngô', '2', '100', '2', 'F103', 'HP06', '18-21', 'D3', 'CSC10006', '100');
INSERT INTO `quanlysinhvien`.`hoc_phan` (`TENGVLT`, `THU`, `SLOT`, `CA`, `TENPHONG`, `MAHP`, `MALOP`, `MADOT`, `MAMH`,`SLOTTOIDA`) VALUES ('Ngô Thừa Ân', '3', '99', '3', 'C101', 'HP07', '18-21', 'D3', 'CSC10001', '100');
INSERT INTO `quanlysinhvien`.`hoc_phan` (`TENGVLT`, `THU`, `SLOT`, `CA`, `TENPHONG`, `MAHP`, `MALOP`, `MADOT`, `MAMH`,`SLOTTOIDA`) VALUES ('Võ Tất Cường', '5', '100', '3', 'C102', 'HP08', '18-21', 'D3', 'CSC10002', '100');
INSERT INTO `quanlysinhvien`.`hoc_phan` (`TENGVLT`, `THU`, `SLOT`, `CA`, `TENPHONG`, `MAHP`, `MALOP`, `MADOT`, `MAMH`,`SLOTTOIDA`) VALUES ('Vô Hữu Đại', '6', '100', '0', 'D101', 'HP09', '18-21', 'D3', 'CSC10002', '100');
INSERT INTO `quanlysinhvien`.`hoc_phan` (`TENGVLT`, `THU`, `SLOT`, `CA`, `TENPHONG`, `MAHP`, `MALOP`, `MADOT`, `MAMH`,`SLOTTOIDA`) VALUES ('Đại Bá Đạo', '7', '100', '1', 'D102', 'HP10', '18-21', 'D3', 'CSC10002', '100');

INSERT INTO `quanlysinhvien`.`kqdkhp` (`MASV`,`MAHP`,`TGDANGKY`) VALUES ('18120629','HP01','2021-05-01');
INSERT INTO `quanlysinhvien`.`kqdkhp` (`MASV`,`MAHP`,`TGDANGKY`) VALUES ('18120629','HP02','2021-05-01');
INSERT INTO `quanlysinhvien`.`kqdkhp` (`MASV`,`MAHP`,`TGDANGKY`) VALUES ('18120629','HP07','2021-05-01');

