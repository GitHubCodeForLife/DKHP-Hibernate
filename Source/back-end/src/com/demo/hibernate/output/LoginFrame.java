package com.demo.hibernate.output;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import com.demo.hibernate.dao.GiaoVuDao;
import com.demo.hibernate.dao.SinhVienDao;
import com.demo.hibernate.entity.GiaoVu;
import com.demo.hibernate.entity.SinhVien;

public class LoginFrame extends JFrame implements ActionListener, KeyListener {
	public static int size = 50;
	JButton loginBtn;
	JTextField taikhoan;
	JPasswordField matkhau;

	LoginFrame() {

		Container con = this.getContentPane();

		// title
		JPanel panelTitle = createTitle("QUẢN LÝ HỌC PHẦN");
		// Tai khoan
		JPanel panelTaiKhoan = createTaiKhoan();
		// Mat khau
		JPanel panelMatKhau = createMatKhau();
		// bnt dang nhap
		JPanel panelLoginBtn = createLoginBtn();

		// Set layout
		con.setLayout(new GridLayout(9, 1));
		con.add(new JPanel());
//		con.add(new JPanel());
		con.add(panelTitle);
		con.add(new JPanel());
		con.add(panelTaiKhoan);
		con.add(panelMatKhau);
		con.add(new JPanel());
		con.add(panelLoginBtn);
		// Set Frame attribute
		this.setTitle("Login Frame");
		this.setVisible(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
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

	JPanel createTaiKhoan() {
		JPanel panel = new JPanel();
		// JLabel
		SpringLayout layout = new SpringLayout();
		panel.setLayout(layout);
		JLabel labelForSlang = new JLabel("Tài Khoản: ");
		labelForSlang.setFont(new Font("", Font.PLAIN, 48));
//		labelForSlang.setForeground(new Color(127, 127, 127, 50));
		taikhoan = new JTextField("", 20);
		taikhoan.setFont(new Font("", Font.PLAIN, 48));
		taikhoan.addKeyListener(this);
		labelForSlang.setPreferredSize(new Dimension(400, 50));
		panel.add(labelForSlang);
		panel.add(taikhoan);
		layout.putConstraint(SpringLayout.WEST, labelForSlang, 100, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, labelForSlang, 20, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, taikhoan, 100, SpringLayout.EAST, labelForSlang);
		layout.putConstraint(SpringLayout.NORTH, taikhoan, 20, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.EAST, panel, 100, SpringLayout.EAST, taikhoan);
		layout.putConstraint(SpringLayout.SOUTH, panel, 20, SpringLayout.SOUTH, taikhoan);

		return panel;
	}

	JPanel createMatKhau() {
		JPanel panel = new JPanel();
		// JLabel
		SpringLayout layout = new SpringLayout();
		panel.setLayout(layout);
		JLabel labelForSlang = new JLabel("Mật khẩu: ");
		labelForSlang.setFont(new Font("", Font.PLAIN, 48));
		matkhau = new JPasswordField("", 20);
		matkhau.setFont(new Font("", Font.PLAIN, 48));
		matkhau.addKeyListener(this);
		labelForSlang.setPreferredSize(new Dimension(400, 50));
		panel.add(labelForSlang);
		panel.add(matkhau);
		layout.putConstraint(SpringLayout.WEST, labelForSlang, 100, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, labelForSlang, 20, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, matkhau, 100, SpringLayout.EAST, labelForSlang);
		layout.putConstraint(SpringLayout.NORTH, matkhau, 20, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.EAST, panel, 100, SpringLayout.EAST, matkhau);
		layout.putConstraint(SpringLayout.SOUTH, panel, 20, SpringLayout.SOUTH, matkhau);

		return panel;
	}

	private Image getScaledImage(Image srcImg, int w, int h) {
		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();
		return resizedImg;
	}

	JPanel createLoginBtn() {
		JPanel panel = new JPanel();
		ImageIcon icon = new ImageIcon(".\\src\\resources\\account.png", "a pretty but meaningless splat");
		icon.setImage(getScaledImage(icon.getImage(), 64, 64));
		loginBtn = new JButton("Đăng nhập", icon);
		loginBtn.setForeground(Color.white);
		loginBtn.setFont(new Font("", Font.PLAIN, 24));
		loginBtn.setBackground(new Color(3889560));
		loginBtn.setFocusable(false);
		loginBtn.setSize(new Dimension(300, 80));
		loginBtn.setPreferredSize(new Dimension(300, 80));
		loginBtn.addActionListener(this);
//		loginBtn.setMnemonic(KeyEvent.VK_ENTER);
		panel.add(loginBtn);
//		panel.setBackground(Color.red);
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// JOptionPane.showMessageDialog(this, taikhoan.getText() + " MK: " +
		// matkhau.getText());
		String taiKhoanText = taikhoan.getText();
		String matKhauText = matkhau.getText();
		if (taikhoan.getText().isEmpty() || matkhau.getText().isEmpty()) {
			// custom title, warning icon
			JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ tài khoản và mật khẩu", "Inane warning",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		// Dang nhap vs chuc nang giao vu
		GiaoVuDao gvdao = new GiaoVuDao();
		GiaoVu gv = gvdao.login(taiKhoanText, matKhauText);
		if (gv != null) {
			return;
		}
		// Sinh Vien dang nhap
		SinhVienDao svdao = new SinhVienDao();
		SinhVien sv = svdao.login(taiKhoanText, matKhauText);
		if (sv != null) {
			new MainScreenSvFrame(sv);
			this.dispose();
			return;
		}
		// Sai tai khoan vs mat khau
		JOptionPane.showMessageDialog(this, "Sai thông tin đăng nhập.", "Đăng nhập thất bại",
				JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
//		JOptionPane.showMessageDialog(this, "Key type: " + e.getKeyCode());
		if (e.getKeyCode() == 10) {
			loginBtn.doClick();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
