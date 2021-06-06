package com.demo.hibernate.output;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import com.demo.hibernate.dao.DotDKHPDao;
import com.demo.hibernate.dao.GiaoVuDao;
import com.demo.hibernate.dao.HocKiDao;
import com.demo.hibernate.entity.DotDKHP;
import com.demo.hibernate.entity.GiaoVu;
import com.demo.hibernate.entity.HocKi;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class ThemDotDKHPFrame extends JDialog implements ActionListener {
	public static int size = 50;
	GiaoVu _gv;
	JButton quayLaiBtn, xacNhanBtn;
	JTextField maDot, maHK;
	JDatePickerImpl tgbdPicker, tgktPicker;
	List<HocKi> listHocKi = new ArrayList<HocKi>();
	JFrame _parent;

	ThemDotDKHPFrame(JFrame parent, GiaoVu gv) {
		super(parent, true);
		_parent = parent;
		_gv = GiaoVuDao.layThongTinGiaoVu(gv.getTKGV());
		Container con = this.getContentPane();
		// Title
		JPanel titlePn = createTitle("         THÊM ĐỢT DKHP         ");
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
		this.setTitle("Them dot dkhp Frame");
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
		// Ma dot
		JPanel maDotPanel = createMaDot();
		// Hoc Ki
		JPanel maHKPanel = createHocKi();
		// TGBD
		JPanel tgbdPanel = createTGBD();
		// TGKT
		JPanel tgktPanel = createTGKT();
		// Set Layout
		panel.setLayout(new GridLayout(5, 1));
		panel.add(maDotPanel);
		panel.add(maHKPanel);
		panel.add(tgbdPanel);
		panel.add(tgktPanel);
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

	JPanel createMaDot() {
		JPanel panel = new JPanel();
		// JLabel
		SpringLayout layout = new SpringLayout();
		panel.setLayout(layout);
		JLabel labelForSlang = new JLabel("          Mã Đợt");
		labelForSlang.setFont(new Font("", Font.PLAIN, 18));
//		labelForSlang.setForeground(new Color(127, 127, 127, 50));
		maDot = new JTextField("", 20);
		maDot.setFont(new Font("", Font.PLAIN, 18));
		labelForSlang.setPreferredSize(new Dimension(175, 18));
		panel.add(labelForSlang);
		panel.add(maDot);
		layout.putConstraint(SpringLayout.WEST, labelForSlang, 20, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, labelForSlang, 20, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, maDot, 20, SpringLayout.EAST, labelForSlang);
		layout.putConstraint(SpringLayout.NORTH, maDot, 20, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.EAST, panel, 20, SpringLayout.EAST, maDot);
		layout.putConstraint(SpringLayout.SOUTH, panel, 20, SpringLayout.SOUTH, maDot);
		return panel;
	}

	JPanel createHocKi() {
		JPanel panel = new JPanel();
		// Title
		JLabel label = new JLabel("     Học Kỳ Hiện tại      ");
		label.setFont(new Font("", Font.PLAIN, 18));

		// Text Field
		maHK = new JTextField(HocKiDao.layHocKiHienTai().getMaHK());
		maHK.setFont(new Font("", Font.PLAIN, 18));
		maHK.setEditable(false);
		// Layout
		panel.add(label);
		panel.add(maHK);

		return panel;
	}

	JPanel createTGBD() {
		JPanel panel = new JPanel();
		// JLabel
		JLabel labelForSlang = new JLabel("          TGBD");
		labelForSlang.setFont(new Font("", Font.PLAIN, 18));
//		labelForSlang.setForeground(new Color(127, 127, 127, 50));
		UtilDateModel model = new UtilDateModel();
		model.setValue(HocKiDao.layHocKiHienTai().getTgBG());
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
			String maDotS = maDot.getText();
			HocKi hocKi = HocKiDao.layHocKiHienTai();
			Date tgbd = (Date) tgbdPicker.getModel().getValue();
			Date tgkt = (Date) tgktPicker.getModel().getValue();

			if (maDotS.isEmpty() || tgbd == null || tgkt == null) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin", "Thêm Đợt DKHP thất bại",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (tgbd.before(hocKi.getTgBG()) || tgkt.after(hocKi.getTgKT())) {
				JOptionPane.showMessageDialog(this, "Thời gian không hợp lệ", "Thêm Đợt DKHP thất bại",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			DotDKHP dot = new DotDKHP(maDotS, convertToLocalDateTime(tgbd), convertToLocalDateTime(tgkt), hocKi);
			System.out.println(dot);
			if (DotDKHPDao.themDotDKHP(dot)) {
				JOptionPane.showMessageDialog(this, "Thêm  Đợt DKHP  thành công");
			} else {
				JOptionPane.showMessageDialog(this, "Thêm Đợt DKHP  không thành công", "Thêm Đợt DKHP  thất bại",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.dispose();
		}
	}

	LocalDateTime convertToLocalDateTime(Date in) {
		LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
		return ldt;
	}
}
