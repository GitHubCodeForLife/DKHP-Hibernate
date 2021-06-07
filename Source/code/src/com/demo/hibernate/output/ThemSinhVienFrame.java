package com.demo.hibernate.output;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;

import com.demo.hibernate.dao.LopDao;
import com.demo.hibernate.dao.SinhVienDao;
import com.demo.hibernate.entity.Lop;
import com.demo.hibernate.entity.SinhVien;

public class ThemSinhVienFrame extends JDialog implements ActionListener {
	public static int size = 50;
	JButton quayLaiBtn, xacNhanBtn;
	JTextField taikhoan, mssv, sdt, malop, email, tenSV, diaChi;
	JComboBox maLop, gioiTinh;

	List<Lop> list_lop;
	JFrame _parent;

	ThemSinhVienFrame(JFrame parent) {
		super(parent, true);
		_parent = parent;

		Container con = this.getContentPane();
		// Title
		JPanel titlePn = createTitle("THÊM SINH VIÊN");
		// Content
		JPanel contenPanel = content();
		// 2 Button
		JPanel buttonBottomPanel = buttonBottom();
		// Set layout
		con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
		con.setBackground(Color.white);
		con.add(titlePn);
		con.add(contenPanel);
		con.add(new JPanel());

		con.add(buttonBottomPanel);
		con.add(new JPanel());
//		con.add(new JPanel());

		// Set Frame attribute
		this.setTitle("Thêm Sinh Vien Frame");
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setModal(true);
		this.pack();
//		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		// this.setUndecorated(true);
		this.setVisible(true);
	}

	JPanel createTitle(String name) {
		JPanel panel = new JPanel();
		JLabel title = new JLabel(name);
		title.setFont(new Font("", Font.PLAIN, 48));
		title.setForeground(new Color(3889560));
		panel.add(title);
		return panel;
	}

	JPanel content() {
		JPanel panel = new JPanel();
		// MSSV SDT MA LOP
		JPanel dong2 = dong2();
		// Email
		JPanel emailPanel = createEmail();

		// Ten SV
		JPanel tenSVPanel = createTenSV();

		// Dia chi
		JPanel diaChiPanel = createDiaChi();

		// Mat khau
		JPanel matKhauPanel = createMatKhau();

		// Set Layout
		panel.setLayout(new GridLayout(6, 1));
		panel.add(dong2);
		panel.add(emailPanel);
		panel.add(tenSVPanel);
		panel.add(diaChiPanel);
		panel.add(matKhauPanel);
		return panel;
	}

	JPanel buttonBottom() {
		JPanel panel = new JPanel();
		Dimension dim = new Dimension(100, 20);
		// Xoa mon hoc btn
		quayLaiBtn = new JButton("Quay Lại");
		quayLaiBtn.setBackground(Color.blue);
		quayLaiBtn.setFont(new Font("", Font.PLAIN, 32));
		quayLaiBtn.setFocusable(false);
		quayLaiBtn.addActionListener(this);
		quayLaiBtn.setBackground(new Color(2204377));
		// Xac nhan btn
		xacNhanBtn = new JButton("Xác nhận");
		xacNhanBtn.setBackground(Color.blue);
		xacNhanBtn.setFont(new Font("", Font.PLAIN, 32));
		xacNhanBtn.setFocusable(false);
		xacNhanBtn.addActionListener(this);
		xacNhanBtn.setBackground(new Color(2204377));
		// Layout
//		panel.setLayout(new GridLayout(1, 6));

		panel.add(new JPanel());
		panel.add(quayLaiBtn);
		panel.add(new JPanel());
		panel.add(new JPanel());
		panel.add(xacNhanBtn);
		panel.add(new JPanel());
		return panel;
	}

	JPanel createEmail() {
		JPanel panel = new JPanel();
		// JLabel
		SpringLayout layout = new SpringLayout();
		panel.setLayout(layout);
		JLabel labelForSlang = new JLabel("          Email");
		labelForSlang.setFont(new Font("", Font.PLAIN, 18));
//		labelForSlang.setForeground(new Color(127, 127, 127, 50));
		email = new JTextField("", 20);
		email.setFont(new Font("", Font.PLAIN, 18));
		labelForSlang.setPreferredSize(new Dimension(150, 18));
		panel.add(labelForSlang);
		panel.add(email);
		layout.putConstraint(SpringLayout.WEST, labelForSlang, 20, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, labelForSlang, 20, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, email, 20, SpringLayout.EAST, labelForSlang);
		layout.putConstraint(SpringLayout.NORTH, email, 20, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.EAST, panel, 20, SpringLayout.EAST, email);
		layout.putConstraint(SpringLayout.SOUTH, panel, 20, SpringLayout.SOUTH, email);
		return panel;
	}

	JPanel createTenSV() {
		JPanel panel = new JPanel();
		// JLabel
		SpringLayout layout = new SpringLayout();
		panel.setLayout(layout);
		JLabel labelForSlang = new JLabel("          Tên SV");
		labelForSlang.setFont(new Font("", Font.PLAIN, 18));
//		labelForSlang.setForeground(new Color(127, 127, 127, 50));
		tenSV = new JTextField("", 20);
		tenSV.setFont(new Font("", Font.PLAIN, 18));
		labelForSlang.setPreferredSize(new Dimension(150, 18));
		panel.add(labelForSlang);
		panel.add(tenSV);
		layout.putConstraint(SpringLayout.WEST, labelForSlang, 20, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, labelForSlang, 20, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, tenSV, 20, SpringLayout.EAST, labelForSlang);
		layout.putConstraint(SpringLayout.NORTH, tenSV, 20, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.EAST, panel, 20, SpringLayout.EAST, tenSV);
		layout.putConstraint(SpringLayout.SOUTH, panel, 20, SpringLayout.SOUTH, tenSV);
		return panel;
	}

	JPanel createDiaChi() {
		JPanel panel = new JPanel();
		// JLabel
		SpringLayout layout = new SpringLayout();
		panel.setLayout(layout);
		JLabel labelForSlang = new JLabel("          Địa Chỉ");
		labelForSlang.setFont(new Font("", Font.PLAIN, 18));
//		labelForSlang.setForeground(new Color(127, 127, 127, 50));
		diaChi = new JTextField("", 20);
		diaChi.setFont(new Font("", Font.PLAIN, 18));
		labelForSlang.setPreferredSize(new Dimension(150, 18));
		panel.add(labelForSlang);
		panel.add(diaChi);
		layout.putConstraint(SpringLayout.WEST, labelForSlang, 20, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, labelForSlang, 20, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, diaChi, 20, SpringLayout.EAST, labelForSlang);
		layout.putConstraint(SpringLayout.NORTH, diaChi, 20, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.EAST, panel, 20, SpringLayout.EAST, diaChi);
		layout.putConstraint(SpringLayout.SOUTH, panel, 20, SpringLayout.SOUTH, diaChi);
		return panel;
	}

	JPanel dong2() {
		JPanel panel = new JPanel();
		// MSSV
		JPanel mssvPanel = createMSSV();

		// SDT
		JPanel sdtPanel = createSDT();

		// Ma Lop
		JPanel maLopPanel = createMaLop();

		// Gioi tinh
		JPanel gioiTinhPanel = createGioiTinh();
		// Lay out
		panel.setLayout(new GridLayout(1, 4));
		panel.add(mssvPanel);
		panel.add(sdtPanel);
		panel.add(maLopPanel);
		panel.add(gioiTinhPanel);
		return panel;
	}

	JPanel createMSSV() {
		JPanel panel = new JPanel();
		// JLabel
		SpringLayout layout = new SpringLayout();
		panel.setLayout(layout);
		JLabel labelForSlang = new JLabel("           MSSV");
		labelForSlang.setFont(new Font("", Font.PLAIN, 18));
		mssv = new JTextField("", 10);
		mssv.setFont(new Font("", Font.PLAIN, 18));
//		labelForSlang.setPreferredSize(new Dimension(200, 18));
		panel.add(labelForSlang);
		panel.add(mssv);
		layout.putConstraint(SpringLayout.WEST, labelForSlang, 5, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, labelForSlang, 5, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, mssv, 5, SpringLayout.EAST, labelForSlang);
		layout.putConstraint(SpringLayout.NORTH, mssv, 5, SpringLayout.NORTH, panel);
//		layout.putConstraint(SpringLayout.EAST, panel, 5, SpringLayout.EAST, textFieldSlang);
//		layout.putConstraint(SpringLayout.SOUTH, panel, 5, SpringLayout.SOUTH, textFieldSlang);
		return panel;
	}

	JPanel createSDT() {
		JPanel panel = new JPanel();
		// JLabel
		SpringLayout layout = new SpringLayout();
		panel.setLayout(layout);
		JLabel labelForSlang = new JLabel("           SDT");
		labelForSlang.setFont(new Font("", Font.PLAIN, 18));
		sdt = new JTextField("", 10);
		sdt.setFont(new Font("", Font.PLAIN, 18));
//		labelForSlang.setPreferredSize(new Dimension(200, 18));
		panel.add(labelForSlang);
		panel.add(sdt);
		layout.putConstraint(SpringLayout.WEST, labelForSlang, 5, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, labelForSlang, 5, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, sdt, 5, SpringLayout.EAST, labelForSlang);
		layout.putConstraint(SpringLayout.NORTH, sdt, 5, SpringLayout.NORTH, panel);
//		layout.putConstraint(SpringLayout.EAST, panel, 5, SpringLayout.EAST, textFieldSlang);
//		layout.putConstraint(SpringLayout.SOUTH, panel, 5, SpringLayout.SOUTH, textFieldSlang);
		return panel;
	}

	JPanel createMaLop() {
		JPanel panel = new JPanel();
		// Title
		JLabel label = new JLabel("Lớp");
		label.setFont(new Font("", Font.PLAIN, 18));

		// Check box);
		list_lop = LopDao.layDanhSachLop();
		int pos = 0;
		String[] lopStrings = new String[list_lop.size()];
		for (int i = 0; i < list_lop.size(); i++) {
			lopStrings[i] = list_lop.get(i).getMaLop();
		}

		maLop = new JComboBox(lopStrings);
		maLop.setSelectedIndex(pos);
		maLop.addActionListener(this);
		maLop.setFont(new Font("", Font.PLAIN, 18));
		// Layout
		panel.add(label);
		panel.add(maLop);

		return panel;
	}

	JPanel createGioiTinh() {
		JPanel panel = new JPanel();
		// Title
		JLabel label = new JLabel("     Gender       ");
		label.setFont(new Font("", Font.PLAIN, 18));

		// Check box

		LopDao ld = new LopDao();
		int pos = 0;
		String[] lopStrings = { "Nu", "Nam" };
		gioiTinh = new JComboBox(lopStrings);
		gioiTinh.setSelectedIndex(pos);
		gioiTinh.addActionListener(this);
		gioiTinh.setFont(new Font("", Font.PLAIN, 18));
		// Layout
		panel.add(label);
		panel.add(gioiTinh);

		return panel;
	}

	JPanel createMatKhau() {
		JPanel panel = new JPanel();
		// title mat khau
		JLabel title = new JLabel("              Mật Khẩu");
		title.setFont(new Font("", Font.BOLD, 18));
		// String *******
		JLabel pass = new JLabel("Mật khẩu măc định sẽ là 123");
		pass.setFont(new Font("", Font.BOLD, 24));
		// Set layout
		panel.setLayout(new GridLayout(1, 4));
		panel.add(title);
		panel.add(pass);
		panel.add(new JPanel());

		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == xacNhanBtn) {
			String mssvS = mssv.getText();
			String tenSVS = tenSV.getText();
			String sdtS = sdt.getText();
			String diaChiS = diaChi.getText();
			Lop lop = list_lop.get(maLop.getSelectedIndex());
			String emailS = email.getText();
			Boolean gioiTinhS = gioiTinh.getSelectedIndex() == 0 ? false : true;
			if (mssvS.isBlank() || tenSVS.isEmpty() || sdtS.isEmpty() || diaChiS.isEmpty() || emailS.isEmpty()
					|| lop == null) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin", "Thêm Sinh Viên thất bại",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			SinhVien sv = new SinhVien(mssvS, lop, tenSVS, sdtS, "123", diaChiS, emailS, gioiTinhS);
			if (SinhVienDao.themSinhVien(sv)) {
				JOptionPane.showMessageDialog(this, "Thêm Sinh Viên  thành công");
			} else {
				JOptionPane.showMessageDialog(this, "Thêm Sinh Viên  không thành công", "Thêm Sinh Viên  thất bại",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.dispose();

		} else if (e.getSource() == quayLaiBtn) {
			this.dispose();
		}
	}

}
