package com.demo.hibernate.output;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
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

import com.demo.hibernate.dao.GiaoVuDao;
import com.demo.hibernate.dao.MonHocDao;
import com.demo.hibernate.entity.GiaoVu;
import com.demo.hibernate.entity.MonHoc;

public class ThemHocPhanFrame extends JDialog implements ActionListener {
	public static int size = 50;
	GiaoVu _gv;
	JButton quayLaiBtn, xacNhanBtn, changePasswordBtn;
	JTextField maMH, tenMH;
	JSpinner soTC;
	JFrame _parent;

	ThemHocPhanFrame(JFrame parent, GiaoVu gv) {
		super(parent, true);
		_parent = parent;
		_gv = GiaoVuDao.layThongTinGiaoVu(gv.getTKGV());
		Container con = this.getContentPane();
		// Title
		JPanel titlePn = createTitle("         THÊM TÀI KHOẢN GIÁO VỤ         ");
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
		// Ma MH
		JPanel maMHPanel = createMaMH();
		// Ten MH
		JPanel tenMHPanel = createTenMH();
		// SoTC
		JPanel soTCPanel = createSoTC();

		// Set Layout
		panel.setLayout(new GridLayout(5, 1));
		panel.add(maMHPanel);
		panel.add(tenMHPanel);
		panel.add(soTCPanel);
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

	JPanel createMaMH() {
		JPanel panel = new JPanel();
		// JLabel
		SpringLayout layout = new SpringLayout();
		panel.setLayout(layout);
		JLabel labelForSlang = new JLabel("          Mã MH");
		labelForSlang.setFont(new Font("", Font.PLAIN, 18));
//		labelForSlang.setForeground(new Color(127, 127, 127, 50));
		maMH = new JTextField("", 20);
		maMH.setFont(new Font("", Font.PLAIN, 18));
		labelForSlang.setPreferredSize(new Dimension(175, 18));
		panel.add(labelForSlang);
		panel.add(maMH);
		layout.putConstraint(SpringLayout.WEST, labelForSlang, 20, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, labelForSlang, 20, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, maMH, 20, SpringLayout.EAST, labelForSlang);
		layout.putConstraint(SpringLayout.NORTH, maMH, 20, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.EAST, panel, 20, SpringLayout.EAST, maMH);
		layout.putConstraint(SpringLayout.SOUTH, panel, 20, SpringLayout.SOUTH, maMH);
		return panel;
	}

	JPanel createTenMH() {
		JPanel panel = new JPanel();
		// JLabel
		SpringLayout layout = new SpringLayout();
		panel.setLayout(layout);
		JLabel labelForSlang = new JLabel("          Tên MH");
		labelForSlang.setFont(new Font("", Font.PLAIN, 18));
//		labelForSlang.setForeground(new Color(127, 127, 127, 50));
		tenMH = new JTextField("", 20);
		tenMH.setFont(new Font("", Font.PLAIN, 18));
		labelForSlang.setPreferredSize(new Dimension(175, 18));
		panel.add(labelForSlang);
		panel.add(tenMH);
		layout.putConstraint(SpringLayout.WEST, labelForSlang, 20, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, labelForSlang, 20, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, tenMH, 20, SpringLayout.EAST, labelForSlang);
		layout.putConstraint(SpringLayout.NORTH, tenMH, 20, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.EAST, panel, 20, SpringLayout.EAST, tenMH);
		layout.putConstraint(SpringLayout.SOUTH, panel, 20, SpringLayout.SOUTH, tenMH);
		return panel;
	}

	JPanel createSoTC() {
		JPanel panel = new JPanel();
		// JLabel
		JLabel labelForSlang = new JLabel("          Số TC");
		labelForSlang.setFont(new Font("", Font.PLAIN, 18));
//		labelForSlang.setForeground(new Color(127, 127, 127, 50));
		soTC = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
		soTC.setFont(new Font("", Font.PLAIN, 18));
		labelForSlang.setPreferredSize(new Dimension(175, 18));
		panel.add(labelForSlang);
		panel.add(soTC);

		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == quayLaiBtn) {
			this.dispose();
		} else if (e.getSource() == xacNhanBtn) {
			// Check DK
			if (tenMH.getText().isEmpty() || maMH.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin", "Thêm môn học thất bại",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			// ADD
			MonHoc mh = new MonHoc(maMH.getText(), tenMH.getText(), (int) soTC.getValue());
			System.out.println(mh);
			if (MonHocDao.themMonHoc(mh)) {
				JOptionPane.showMessageDialog(this, "Thêm  môn học thành công");
			} else {
				JOptionPane.showMessageDialog(this, "Thêm môn học không thành công", "Thêm môn học thất bại",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.dispose();
		}
	}

}
