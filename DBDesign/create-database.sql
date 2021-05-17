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
   STTDOT               int not null  comment '',
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
   TKGV                 varchar(30)  comment '',
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
   MAHOCKI              varchar(30)  comment '',
   MADOT                varchar(30)  comment '',
   MAMH                 varchar(30) not null  comment '',
   primary key (MAHP)
);

/*==============================================================*/
/* Table: KQDKHP                                                */
/*==============================================================*/
create table KQDKHP
(
   MASV                 varchar(30) not null  comment '',
   MAHP                 varchar(30)  comment ''
);

/*==============================================================*/
/* Table: LOP                                                   */
/*==============================================================*/
create table LOP
(
   MALOP                varchar(30) not null  comment '',
   TONGSV               int  comment '',
   TONGNAM              int  comment '',
   TONGNU               int  comment '',
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
   primary key (MASV)
);

alter table HOC_KI add constraint FK_HOC_KI_RELATIONS_GIAO_VU foreign key (TKGV)
      references GIAO_VU (TKGV) on delete restrict on update restrict;

alter table HOC_PHAN add constraint FK_HOC_PHAN_CO_MON_HOC foreign key (MAMH)
      references MON_HOC (MAMH) on delete restrict on update restrict;

alter table HOC_PHAN add constraint FK_HOC_PHAN_RELATIONS_HOC_KI foreign key (MAHOCKI)
      references HOC_KI (MAHOCKI) on delete restrict on update restrict;

alter table HOC_PHAN add constraint FK_HOC_PHAN_RELATIONS_DOT_DK_H foreign key (MADOT)
      references DOT_DK_HP (MADOT) on delete restrict on update restrict;

alter table HOC_PHAN add constraint FK_HOC_PHAN_THUOC_LOP foreign key (MALOP)
      references LOP (MALOP) on delete restrict on update restrict;

alter table KQDKHP add constraint FK_KQDKHP_RELATIONS_SINH_VIE foreign key (MASV)
      references SINH_VIEN (MASV) on delete restrict on update restrict;

alter table KQDKHP add constraint FK_KQDKHP_RELATIONS_HOC_PHAN foreign key (MAHP)
      references HOC_PHAN (MAHP) on delete restrict on update restrict;

alter table SINH_VIEN add constraint FK_SINH_VIE_THUOC_LOP_LOP foreign key (MALOP)
      references LOP (MALOP) on delete restrict on update restrict;

INSERT INTO `quanlysinhvien`.`lop` (`MALOP`, `TONGSV`, `TONGNAM`, `TONGNU`) VALUES ('18CTT5', '100', '50', '50');


INSERT INTO `quanlysinhvien`.`sinh_vien` (`MASV`, `MALOP`, `TENSV`, `SDT`, `MKSV`, `DIACHISV`, `EMAILSV`) VALUES ('18120629', '18CTT5', 'Tran Van Tu', '0961050667', '123', 'Ha Noi', '18120629@student.hcmus.edu.vn');
 
 
 INSERT INTO `quanlysinhvien`.`giao_vu` (`TKGV`, `MKGV`, `DIACHIGV`, `EMAILGV`, `TENGV`) VALUES ('giaovu@gmail.com', '123', 'HCM', 'giaovu@gmail.com', 'Giao Vu');


INSERT INTO `quanlysinhvien`.`mon_hoc` (`MAMH`, `TENMH`, `SOTINCHI`) VALUES ('CSC10001', 'Cơ Sở Lập Trình', '4');


INSERT INTO `quanlysinhvien`.`dot_dk_hp` (`MADOT`, `STTDOT`, `TGBATDAU`, `TGKETTHUC`) VALUES ('D1', '1', '2008-11-11 13:23:44', '2008-11-11 13:23:44');


INSERT INTO `quanlysinhvien`.`hoc_ki` (`MAHOCKI`, `TKGV`, `TENHOCKI`, `NAM`, `HKBATDAU`, `HKKETTHUC`) VALUES ('HK1-2021', 'giaovu@gmail.com', 'Học kỳ 1', '2020', '2008-11-11', '2008-11-11');


INSERT INTO `quanlysinhvien`.`hoc_phan` (`TENGVLT`, `THU`, `SLOT`, `CA`, `TENPHONG`, `MAHP`, `MALOP`, `MAHOCKI`, `MADOT`, `MAMH`) VALUES ('Thái Hùng Văn', '4', '100', '1', 'E101', 'SSCC11', '18CTT5', 'HK1-2021', 'D1', 'CSC10001');



