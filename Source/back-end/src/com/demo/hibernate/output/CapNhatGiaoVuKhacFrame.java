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
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;

import com.demo.hibernate.dao.GiaoVuDao;
import com.demo.hibernate.entity.GiaoVu;

public class CapNhatGiaoVuKhacFrame extends JDialog implements ActionListener {
	public static int size = 50;
	GiaoVu _gv;
	JButton quayLaiBtn, xacNhanBtn, changePasswordBtn;
	JTextField tk, diaChi, email, ten;
	JFrame _parent;

	CapNhatGiaoVuKhacFrame(JFrame parent, GiaoVu gv) {
		super(parent, true);
		_parent = parent;
		_gv = GiaoVuDao.layThongTinGiaoVu(gv.getTKGV());
		Container con = this.getContentPane();
		// Title
		JPanel titlePn = createTitle("CẬP NHẬT THÔNG TIN GIÁO VỤ");
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
		this.setTitle("Cap nhat thong tin Giao Vu Frame");
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
		// Tai khoan
		JPanel taiKhoanPanel = createTaiKhoan();
		// Dia chi
		JPanel diaChiPanel = createDiaChi();
		// Email
		JPanel emailPanel = createEmail();
		// Ten tenGV
		JPanel tenGVPanel = createTenGV();
		// Mat khau
		JPanel matKhauPanel = createMatKhau();

		// Set Layout
		panel.setLayout(new GridLayout(6, 1));
		panel.add(taiKhoanPanel);
		panel.add(emailPanel);
		panel.add(tenGVPanel);
		panel.add(diaChiPanel);
		panel.add(matKhauPanel);
		return panel;
	}

	JPanel buttonBottom() {
		JPanel panel = new JPanel();
		Dimension dim = new Dimension(100, 20);
		// Xoa mon hoc btn
		quayLaiBtn = new JButton("Xóa tài khoản");
		quayLaiBtn.setBackground(Color.blue);
		quayLaiBtn.setFont(new Font("", Font.PLAIN, 32));
		quayLaiBtn.setFocusable(false);
		quayLaiBtn.setBackground(Color.red);
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

	JPanel createTaiKhoan() {
		JPanel panel = new JPanel();
		// JLabel
		SpringLayout layout = new SpringLayout();
		panel.setLayout(layout);
		JLabel labelForSlang = new JLabel("          Tài Khoản");
		labelForSlang.setFont(new Font("", Font.PLAIN, 18));
//		labelForSlang.setForeground(new Color(127, 127, 127, 50));
		tk = new JTextField(_gv.getTKGV(), 20);
		tk.setFont(new Font("", Font.PLAIN, 18));
		labelForSlang.setPreferredSize(new Dimension(150, 18));
		panel.add(labelForSlang);
		panel.add(tk);
		layout.putConstraint(SpringLayout.WEST, labelForSlang, 20, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, labelForSlang, 20, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, tk, 20, SpringLayout.EAST, labelForSlang);
		layout.putConstraint(SpringLayout.NORTH, tk, 20, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.EAST, panel, 20, SpringLayout.EAST, tk);
		layout.putConstraint(SpringLayout.SOUTH, panel, 20, SpringLayout.SOUTH, tk);
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
		diaChi = new JTextField(_gv.getDIACHIGV(), 20);
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

	JPanel createEmail() {
		JPanel panel = new JPanel();
		// JLabel
		SpringLayout layout = new SpringLayout();
		panel.setLayout(layout);
		JLabel labelForSlang = new JLabel("          Email");
		labelForSlang.setFont(new Font("", Font.PLAIN, 18));
//		labelForSlang.setForeground(new Color(127, 127, 127, 50));
		email = new JTextField(_gv.getEMAILGV(), 20);
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

	JPanel createTenGV() {
		JPanel panel = new JPanel();
		// JLabel
		SpringLayout layout = new SpringLayout();
		panel.setLayout(layout);
		JLabel labelForSlang = new JLabel("          Tên GV");
		labelForSlang.setFont(new Font("", Font.PLAIN, 18));
//		labelForSlang.setForeground(new Color(127, 127, 127, 50));
		ten = new JTextField(_gv.getTENGV(), 20);
		ten.setFont(new Font("", Font.PLAIN, 18));
		labelForSlang.setPreferredSize(new Dimension(150, 18));
		panel.add(labelForSlang);
		panel.add(ten);
		layout.putConstraint(SpringLayout.WEST, labelForSlang, 20, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, labelForSlang, 20, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, ten, 20, SpringLayout.EAST, labelForSlang);
		layout.putConstraint(SpringLayout.NORTH, ten, 20, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.EAST, panel, 20, SpringLayout.EAST, ten);
		layout.putConstraint(SpringLayout.SOUTH, panel, 20, SpringLayout.SOUTH, ten);
		return panel;
	}

	JPanel createMatKhau() {
		JPanel panel = new JPanel();
		// title mat khau
		JLabel title = new JLabel("              Mật Khẩu");
		title.setFont(new Font("", Font.BOLD, 18));
		// String *******
		JLabel pass = new JLabel("***********************");
		pass.setFont(new Font("", Font.BOLD, 24));

		// Btn Reset pass word
		JPanel temp = new JPanel();
		changePasswordBtn = new JButton("Reset mật khẩu");
		changePasswordBtn.setBackground(Color.blue);
		changePasswordBtn.setFont(new Font("", Font.PLAIN, 24));
		changePasswordBtn.setFocusable(false);
		changePasswordBtn.setBackground(new Color(2204377));
		temp.add(changePasswordBtn);
		changePasswordBtn.addActionListener(this);
		// Set layout
		panel.setLayout(new GridLayout(1, 4));
		panel.add(title);
		panel.add(pass);
		panel.add(temp);
		panel.add(new JPanel());

		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == changePasswordBtn) {
			GiaoVuDao.resetMatKhau(_gv.getTKGV());
			JOptionPane.showMessageDialog(this, "Mật khẩu đã được reset về 123");
		} else if (e.getSource() == quayLaiBtn) {
			// Custom button text
			Object[] options = { "Yes, please", "No, thanks" };
			int n = JOptionPane.showOptionDialog(this, "Bạn thực sự muốn xóa tài khoản này không?", "Cảnh báo",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
			if (n == 0) {
				GiaoVuDao.xaoGiaoVu(_gv.getTKGV());
				_parent.dispose();
				this.dispose();
			} else {

			}
		} else if (e.getSource() == xacNhanBtn) {
			// Check DK
			if (tk.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin", "Cập nhật thông tin thất bại",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			GiaoVu gv = new GiaoVu();
			gv.setTKGV(tk.getText());
			gv.setMKGV(_gv.getMKGV());
			gv.setDIACHIGV(diaChi.getText());
			gv.setEMAILGV(email.getText());
			gv.setTENGV(ten.getText());
			System.out.println(gv);

			if (!_gv.getTKGV().equals(gv.getTKGV())) {
				GiaoVuDao.xaoGiaoVu(_gv.getTKGV());
				if (GiaoVuDao.themGiaoVu(gv)) {
					// default title and icon
					JOptionPane.showMessageDialog(this, "Cập nhật thông tin thành công");
				} else {
					// custom title, error icon
					JOptionPane.showMessageDialog(this, "Cập nhật thông tin thất bại", "Update Error",
							JOptionPane.ERROR_MESSAGE);
					this.dispose();
					return;
				}
			} else if (GiaoVuDao.updateGiaoVu(gv)) {
				// default title and icon
				JOptionPane.showMessageDialog(this, "Cập nhật thông tin thành công");
			} else {
				// custom title, error icon
				JOptionPane.showMessageDialog(this, "Cập nhật thông tin thất bại", "Update Error",
						JOptionPane.ERROR_MESSAGE);
				this.dispose();
				return;
			}
			_parent.dispose();
			this.dispose();
		}
	}

}
