package com.demo.hibernate.output;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;

import com.demo.hibernate.dao.GiaoVuDao;
import com.demo.hibernate.dao.HocKiDao;
import com.demo.hibernate.entity.GiaoVu;
import com.demo.hibernate.entity.HocKi;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class ThemHocKiFrame extends JDialog implements ActionListener {
	public static int size = 50;
	GiaoVu _gv;
	JButton quayLaiBtn, xacNhanBtn, changePasswordBtn;
	JTextField maHK, tenHK, nam;
	JDatePickerImpl tgbdPicker, tgktPicker;

	JFrame _parent;

	ThemHocKiFrame(JFrame parent, GiaoVu gv) {
		super(parent, true);
		_parent = parent;
		_gv = GiaoVuDao.layThongTinGiaoVu(gv.getTKGV());
		Container con = this.getContentPane();
		// Title
		JPanel titlePn = createTitle("         THÊM HỌC KỲ MỚI         ");
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
		this.setTitle("Them hoc ky Frame");
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
		// Ma HK - String
		JPanel maHKPanel = createMaHK();
		// Ten HK - String
		JPanel tenHKPanel = createTenHK();
		// Nam - Number
		JPanel namPanel = createNam();
		// TGBD - DatePicker
		JPanel TGBDPanel = createTGBD();
		// TGKT - DatePicker
		JPanel TGKTPanel = createTGKT();
		// Set Layout
		panel.setLayout(new GridLayout(5, 1));
		panel.add(maHKPanel);
		panel.add(tenHKPanel);
		panel.add(namPanel);
		panel.add(TGBDPanel);
		panel.add(TGKTPanel);
		return panel;
	}

	JPanel buttonBottom() {
		JPanel panel = new JPanel();
		Dimension dim = new Dimension(100, 20);
		// Quay lai btn
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

	JPanel createMaHK() {
		JPanel panel = new JPanel();
		// JLabel
		SpringLayout layout = new SpringLayout();
		panel.setLayout(layout);
		JLabel labelForSlang = new JLabel("          Mã HK");
		labelForSlang.setFont(new Font("", Font.PLAIN, 18));
//		labelForSlang.setForeground(new Color(127, 127, 127, 50));
		maHK = new JTextField("", 20);
		maHK.setFont(new Font("", Font.PLAIN, 18));
		labelForSlang.setPreferredSize(new Dimension(175, 18));
		panel.add(labelForSlang);
		panel.add(maHK);
		layout.putConstraint(SpringLayout.WEST, labelForSlang, 20, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, labelForSlang, 20, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, maHK, 20, SpringLayout.EAST, labelForSlang);
		layout.putConstraint(SpringLayout.NORTH, maHK, 20, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.EAST, panel, 20, SpringLayout.EAST, maHK);
		layout.putConstraint(SpringLayout.SOUTH, panel, 20, SpringLayout.SOUTH, maHK);
		return panel;
	}

	JPanel createTenHK() {
		JPanel panel = new JPanel();
		// JLabel
		SpringLayout layout = new SpringLayout();
		panel.setLayout(layout);
		JLabel labelForSlang = new JLabel("          Tên HK");
		labelForSlang.setFont(new Font("", Font.PLAIN, 18));
//		labelForSlang.setForeground(new Color(127, 127, 127, 50));
		tenHK = new JTextField("", 20);
		tenHK.setFont(new Font("", Font.PLAIN, 18));
		labelForSlang.setPreferredSize(new Dimension(175, 18));
		panel.add(labelForSlang);
		panel.add(tenHK);
		layout.putConstraint(SpringLayout.WEST, labelForSlang, 20, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, labelForSlang, 20, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, tenHK, 20, SpringLayout.EAST, labelForSlang);
		layout.putConstraint(SpringLayout.NORTH, tenHK, 20, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.EAST, panel, 20, SpringLayout.EAST, tenHK);
		layout.putConstraint(SpringLayout.SOUTH, panel, 20, SpringLayout.SOUTH, tenHK);
		return panel;
	}

	JPanel createNam() {
		JPanel panel = new JPanel();
		// JLabel
		JLabel labelForSlang = new JLabel("          Năm");
		labelForSlang.setFont(new Font("", Font.PLAIN, 18));
//		labelForSlang.setForeground(new Color(127, 127, 127, 50));

		nam = new JTextField("2021");
		nam.setFont(new Font("", Font.PLAIN, 18));
		nam.setSize(new Dimension(100, 18));
		nam.setPreferredSize(new Dimension(150, 24));
		labelForSlang.setPreferredSize(new Dimension(175, 18));
		panel.add(labelForSlang);
		panel.add(nam);

		return panel;
	}

	JPanel createTGBD() {
		JPanel panel = new JPanel();
		// JLabel
		JLabel labelForSlang = new JLabel("          TGBD");
		labelForSlang.setFont(new Font("", Font.PLAIN, 18));
//		labelForSlang.setForeground(new Color(127, 127, 127, 50));
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		tgbdPicker = new JDatePickerImpl(datePanel);
		labelForSlang.setPreferredSize(new Dimension(175, 18));
		panel.add(labelForSlang);
		panel.add(tgbdPicker);

		return panel;
	}

	JPanel createTGKT() {
		JPanel panel = new JPanel();
		// JLabel
		JLabel labelForSlang = new JLabel("          TGKT");
		labelForSlang.setFont(new Font("", Font.PLAIN, 18));
//		labelForSlang.setForeground(new Color(127, 127, 127, 50));
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		tgktPicker = new JDatePickerImpl(datePanel);
		labelForSlang.setPreferredSize(new Dimension(175, 18));
		panel.add(labelForSlang);
		panel.add(tgktPicker);

		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == quayLaiBtn) {
			this.dispose();
		} else if (e.getSource() == xacNhanBtn) {
			// Check DK
			String maHKS = maHK.getText();
			String tenHKS = tenHK.getText();
			String namS = nam.getText();
			LocalDate tgbd = convertToLocalDateViaInstant((Date) tgbdPicker.getModel().getValue());
			LocalDate tgkt = convertToLocalDateViaInstant((Date) tgktPicker.getModel().getValue());
			if (maHKS.isEmpty() || tenHKS.isEmpty() || namS.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin", "Thêm học kỳ thất bại",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (tgbd == null || tgkt == null || tgbd.isAfter(tgkt) || tgbd.equals(tgkt)) {
				JOptionPane.showMessageDialog(this, "Thời gian kết thúc phải lớn hơn thời gian bắt đầu",
						"Thêm học kỳ thất bại", JOptionPane.ERROR_MESSAGE);
				return;
			}

			// ADD
			HocKi hk = new HocKi(maHKS, tenHKS, namS, tgbd, tgkt);
			System.out.println(hk);
			if (HocKiDao.themHocKi(hk)) {
				JOptionPane.showMessageDialog(this, "Thêm  học kì thành công");
			} else {
				JOptionPane.showMessageDialog(this, "Thêm học kì không thành công", "Thêm học kì thất bại",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.dispose();
		}
	}

	public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
}
