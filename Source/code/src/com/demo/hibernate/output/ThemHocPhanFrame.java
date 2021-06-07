package com.demo.hibernate.output;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;

import com.demo.hibernate.dao.DotDKHPDao;
import com.demo.hibernate.dao.GiaoVuDao;
import com.demo.hibernate.dao.HocPhanDao;
import com.demo.hibernate.dao.MonHocDao;
import com.demo.hibernate.entity.DotDKHP;
import com.demo.hibernate.entity.GiaoVu;
import com.demo.hibernate.entity.HocPhan;
import com.demo.hibernate.entity.MonHoc;

public class ThemHocPhanFrame extends JDialog implements ActionListener {
	public static int size = 50;
	GiaoVu _gv;
	JButton quayLaiBtn, xacNhanBtn;
	JTextField maHP, tenGVLT, tenPhong, lop;
	JComboBox monHoc, dotDKHP, ca, thu;
	JSpinner slot;
	JFrame _parent;

	ThemHocPhanFrame(JFrame parent, GiaoVu gv) {
		super(parent, true);
		_parent = parent;
		_gv = GiaoVuDao.layThongTinGiaoVu(gv.getTKGV());
		Container con = this.getContentPane();
		// Title
		JPanel titlePn = createTitle("         THÊM HỌC PHẦN         ");
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
		this.setTitle("Them giao vu Frame");
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
		// MaHP
		JPanel hocPhanPanel = createMaHP();

		// Dong 2
		JPanel dong2 = new JPanel();
		// Mon Hoc
		JPanel monHocPanel = createMonHoc();
		// Dot dang ky hoc phan
		JPanel dotDKHPPanel = createDotDKHP();

		dong2.setLayout(new BoxLayout(dong2, BoxLayout.X_AXIS));
		dong2.add(monHocPanel);
		dong2.add(dotDKHPPanel);

		// Label Lich hoc
		JLabel lichHocLabel = new JLabel("Lịch Học");
		lichHocLabel.setFont(new Font("", Font.BOLD, 18));
		// Dong 3
		JPanel dong3 = new JPanel();
		JPanel caPanel = createCa();
		JPanel thuPanel = createThu();
		JPanel slotPanel = createSlot();

		dong3.add(caPanel);
		dong3.add(thuPanel);
		dong3.add(slotPanel);

		// ten gvlt
		JPanel gvltPanel = createGVLT();
		// ten phong
		JPanel phongPanel = createPhong();
		// Lop
		JPanel lopPanel = createLop();

		// Set Layout
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(hocPhanPanel);
		panel.add(dong2);
		panel.add(lichHocLabel);
		panel.add(dong3);
		panel.add(gvltPanel);
		panel.add(phongPanel);
		panel.add(lopPanel);
		return panel;
	}

	JPanel buttonBottom() {
		JPanel panel = new JPanel();
		Dimension dim = new Dimension(100, 20);
		// Xoa mon hoc btn
		quayLaiBtn = new JButton("Quay lại");
		quayLaiBtn.setBackground(Color.blue);
		quayLaiBtn.setFont(new Font("", Font.PLAIN, 32));
		quayLaiBtn.setFocusable(false);
		quayLaiBtn.setBackground(new Color(2204377));
		quayLaiBtn.addActionListener(this);
		// Xac nhan btn
		xacNhanBtn = new JButton("Xác Nhận");
		xacNhanBtn.setBackground(Color.blue);
		xacNhanBtn.setFont(new Font("", Font.PLAIN, 32));
		xacNhanBtn.setFocusable(false);
		xacNhanBtn.setBackground(new Color(2204377));
		xacNhanBtn.addActionListener(this);
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

	JPanel createMaHP() {
		JPanel panel = new JPanel();
		// JLabel
		SpringLayout layout = new SpringLayout();
		panel.setLayout(layout);
		JLabel labelForSlang = new JLabel("          Mã Học Phần");
		labelForSlang.setFont(new Font("", Font.PLAIN, 18));
//		labelForSlang.setForeground(new Color(127, 127, 127, 50));
		maHP = new JTextField("", 20);
		maHP.setFont(new Font("", Font.PLAIN, 18));
		labelForSlang.setPreferredSize(new Dimension(175, 18));
		panel.add(labelForSlang);
		panel.add(maHP);
		layout.putConstraint(SpringLayout.WEST, labelForSlang, 20, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, labelForSlang, 20, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, maHP, 20, SpringLayout.EAST, labelForSlang);
		layout.putConstraint(SpringLayout.NORTH, maHP, 20, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.EAST, panel, 20, SpringLayout.EAST, maHP);
		layout.putConstraint(SpringLayout.SOUTH, panel, 20, SpringLayout.SOUTH, maHP);
		return panel;
	}

	JPanel createMonHoc() {
		JPanel panel = new JPanel();
		// Title
		JLabel label = new JLabel("Môn học");
		label.setFont(new Font("", Font.PLAIN, 18));

		// Check box);
		List<MonHoc> list = MonHocDao.layDanhSachMonHoc();
		int pos = 0;
		String[] lopStrings = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			lopStrings[i] = list.get(i).getTenMH();
		}

		monHoc = new JComboBox(lopStrings);
		monHoc.setSelectedIndex(pos);
		monHoc.addActionListener(this);
		monHoc.setFont(new Font("", Font.PLAIN, 18));
		// Layout
		panel.add(label);
		panel.add(monHoc);

		return panel;
	}

	JPanel createDotDKHP() {
		JPanel panel = new JPanel();
		// Title
		JLabel label = new JLabel("Dot DKHP");
		label.setFont(new Font("", Font.PLAIN, 18));

		// Check box);
		List<DotDKHP> list = DotDKHPDao.layDanhSachDotDKHP();
		int pos = 0;
		String[] lopStrings = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			lopStrings[i] = list.get(i).getMaDot();
		}

		dotDKHP = new JComboBox(lopStrings);
		dotDKHP.setSelectedIndex(pos);
		dotDKHP.addActionListener(this);
		dotDKHP.setFont(new Font("", Font.PLAIN, 18));
		// Layout
		panel.add(label);
		panel.add(dotDKHP);

		return panel;
	}

	JPanel createCa() {
		JPanel panel = new JPanel();
		// Title
		JLabel label = new JLabel("Ca");
		label.setFont(new Font("", Font.PLAIN, 18));

		// Check box);
		String[] lopStrings = { "7h30 - 9h30", "9h30 - 11h30", "13h30 - 15h30", "15h30 - 17h30" };
		int pos = 0;
		ca = new JComboBox(lopStrings);
		ca.setSelectedIndex(pos);
		ca.addActionListener(this);
		ca.setFont(new Font("", Font.PLAIN, 18));
		// Layout
		panel.add(label);
		panel.add(ca);

		return panel;
	}

	JPanel createThu() {
		JPanel panel = new JPanel();
		// Title
		JLabel label = new JLabel("Thứ");
		label.setFont(new Font("", Font.PLAIN, 18));

		// Check box);
		String[] lopStrings = { "Thứ Hai", "Thứ Ba", "Thứ Tư", "Thứ Năm", "Thứ Sáu", "Thứ Bảy" };
		int pos = 0;
		thu = new JComboBox(lopStrings);
		thu.setSelectedIndex(pos);
		thu.addActionListener(this);
		thu.setFont(new Font("", Font.PLAIN, 18));
		// Layout
		panel.add(label);
		panel.add(thu);

		return panel;
	}

	JPanel createSlot() {
		JPanel panel = new JPanel();
		// JLabel
		JLabel labelForSlang = new JLabel("Slot");
		labelForSlang.setFont(new Font("", Font.PLAIN, 18));
//		labelForSlang.setForeground(new Color(127, 127, 127, 50));
		slot = new JSpinner(new SpinnerNumberModel(100, 1, 200, 1));
		slot.setFont(new Font("", Font.PLAIN, 18));
//		labelForSlang.setPreferredSize(new Dimension(175, 18));
		panel.add(labelForSlang);
		panel.add(slot);

		return panel;
	}

	JPanel createGVLT() {
		JPanel panel = new JPanel();
		// JLabel
		SpringLayout layout = new SpringLayout();
		panel.setLayout(layout);
		JLabel labelForSlang = new JLabel("        GV lý thuyết");
		labelForSlang.setFont(new Font("", Font.PLAIN, 18));
//		labelForSlang.setForeground(new Color(127, 127, 127, 50));
		tenGVLT = new JTextField("", 20);
		tenGVLT.setFont(new Font("", Font.PLAIN, 18));
		labelForSlang.setPreferredSize(new Dimension(175, 18));
		panel.add(labelForSlang);
		panel.add(tenGVLT);
		layout.putConstraint(SpringLayout.WEST, labelForSlang, 20, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, labelForSlang, 20, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, tenGVLT, 20, SpringLayout.EAST, labelForSlang);
		layout.putConstraint(SpringLayout.NORTH, tenGVLT, 20, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.EAST, panel, 20, SpringLayout.EAST, tenGVLT);
		layout.putConstraint(SpringLayout.SOUTH, panel, 20, SpringLayout.SOUTH, tenGVLT);
		return panel;
	}

	JPanel createPhong() {
		JPanel panel = new JPanel();
		// JLabel
		SpringLayout layout = new SpringLayout();
		panel.setLayout(layout);
		JLabel labelForSlang = new JLabel("        Tên Phòng");
		labelForSlang.setFont(new Font("", Font.PLAIN, 18));
//		labelForSlang.setForeground(new Color(127, 127, 127, 50));
		tenPhong = new JTextField("", 20);
		tenPhong.setFont(new Font("", Font.PLAIN, 18));
		labelForSlang.setPreferredSize(new Dimension(175, 18));
		panel.add(labelForSlang);
		panel.add(tenPhong);
		layout.putConstraint(SpringLayout.WEST, labelForSlang, 20, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, labelForSlang, 20, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, tenPhong, 20, SpringLayout.EAST, labelForSlang);
		layout.putConstraint(SpringLayout.NORTH, tenPhong, 20, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.EAST, panel, 20, SpringLayout.EAST, tenPhong);
		layout.putConstraint(SpringLayout.SOUTH, panel, 20, SpringLayout.SOUTH, tenPhong);
		return panel;
	}

	JPanel createLop() {
		JPanel panel = new JPanel();
		// JLabel
		SpringLayout layout = new SpringLayout();
		panel.setLayout(layout);
		JLabel labelForSlang = new JLabel("        Lớp");
		labelForSlang.setFont(new Font("", Font.PLAIN, 18));
//		labelForSlang.setForeground(new Color(127, 127, 127, 50));
		lop = new JTextField("", 20);
		lop.setFont(new Font("", Font.PLAIN, 18));
		labelForSlang.setPreferredSize(new Dimension(175, 18));
		panel.add(labelForSlang);
		panel.add(lop);
		layout.putConstraint(SpringLayout.WEST, labelForSlang, 20, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, labelForSlang, 20, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, lop, 20, SpringLayout.EAST, labelForSlang);
		layout.putConstraint(SpringLayout.NORTH, lop, 20, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.EAST, panel, 20, SpringLayout.EAST, lop);
		layout.putConstraint(SpringLayout.SOUTH, panel, 20, SpringLayout.SOUTH, lop);
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == quayLaiBtn) {
			this.dispose();
		} else if (e.getSource() == xacNhanBtn) {
			String maHPS = maHP.getText();
			MonHoc mh = MonHocDao.layDanhSachMonHoc().get(monHoc.getSelectedIndex());
			DotDKHP dot = DotDKHPDao.layDanhSachDotDKHP().get(dotDKHP.getSelectedIndex());
			int caS = ca.getSelectedIndex();
			int thuS = thu.getSelectedIndex() + 2;
			int slotS = (int) slot.getValue();
			String tenGVLTS = tenGVLT.getText();
			String tenPhongS = tenPhong.getText();
			String lopS = lop.getText();
			if (maHPS.isBlank() || tenGVLTS.isBlank() || tenPhongS.isBlank() || lopS.isBlank()) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin", "Thêm học phần thất bại",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			HocPhan hp = new HocPhan(maHPS, tenGVLTS, thuS, slotS, caS, tenPhongS, lopS, slotS, mh, dot);
			if (HocPhanDao.themHocPhan(hp)) {
				JOptionPane.showMessageDialog(this, "Thêm  học phần thành công");
			} else {
				JOptionPane.showMessageDialog(this, "Thêm  học phần không thành công", "Thêm học phần thất bại",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.dispose();
		}
	}

}
