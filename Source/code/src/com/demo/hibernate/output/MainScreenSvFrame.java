package com.demo.hibernate.output;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.demo.hibernate.dao.SinhVienDao;
import com.demo.hibernate.entity.SinhVien;

public class MainScreenSvFrame extends JFrame implements ActionListener {
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	JButton account_top, logout;
	JButton account_side, dkhp_side, hpddk_side;
	JButton dkhp_content, hpddk_content;
	SinhVien _sv;

	MainScreenSvFrame(SinhVien sv) {
		_sv = SinhVienDao.layThongTinSinhVien(sv.getMaSV());
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println(dim.getSize());

		// Set layout and component on Frame
		Container con = this.getContentPane();
		con.setLayout(new BorderLayout());
		JPanel JNavbarTop = navbarTop(_sv.getTenSV());
		JPanel JNavbarSide = navbarSide(_sv.getTenSV());
		JPanel JContent = content();

		JNavbarSide.setSize(new Dimension(300, 100));

		con.add(JNavbarTop, BorderLayout.PAGE_START);
		con.add(JNavbarSide, BorderLayout.LINE_START);
		con.add(JContent, BorderLayout.CENTER);
		// Set Frame attribute
		this.setTitle("Sinh Vien Main Screen");
		this.setVisible(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		// this.setUndecorated(true);
		this.setVisible(true);
	}

	JPanel navbarTop(String name) {
		JPanel JNavbarTop = new JPanel();

		JPanel line_start = new JPanel();
		line_start.setSize(100, 100);
		// Title

		JLabel title = new JLabel("Hệ thống DKHP - SV");
		title.setFont(new Font("", Font.PLAIN, 48));

		// Account
		JPanel JAccount = account(name);

		// Set layout
		JNavbarTop.setLayout(new GridLayout(0, 3));
		line_start.setBackground(new Color(16777215));
		JNavbarTop.setBackground(new Color(16777215));
		JNavbarTop.add(line_start);
		JNavbarTop.add(title);
		JNavbarTop.add(JAccount);
		JNavbarTop.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
		return JNavbarTop;
	}

	JPanel navbarSide(String name) {
		JPanel JNavbarSide = new JPanel();
//		JPanel temp2 = new JPanel();
		JPanel temp = new JPanel();
		account_side = CreateAccountSide("   " + name + "   ");
		dkhp_side = CreateManagerSide(".\\src\\resources\\dkhp.png", "Đăng ký học phần");
		hpddk_side = CreateManagerSide(".\\src\\resources\\history.png", "Học phần đã DK");

		// Set layout
		JNavbarSide.setLayout(new BorderLayout());
		temp.setLayout(new GridLayout(12, 1));
		temp.setBackground(new Color(000000));
		// temp2.setBackground(new Color(000000));
		// temp2.add(JAcountSide);
		temp.add(dkhp_side);
		temp.add(hpddk_side);
		JNavbarSide.add(account_side, BorderLayout.PAGE_START);
		JNavbarSide.add(temp, BorderLayout.CENTER);

		// Set Action listener
		dkhp_side.addActionListener(this);
		account_side.addActionListener(this);
		hpddk_side.addActionListener(this);

		return JNavbarSide;
	}

	JPanel content() {
		JPanel JContent = new JPanel();
		dkhp_content = CreateManagerContent(".\\src\\resources\\dkhp.png", "   Đăng ký học phần   ");
		hpddk_content = CreateManagerContent(".\\src\\resources\\history.png", "  Học phần đã DK  ");

		// Set lay out
		JPanel temp = new JPanel();
		temp.setLayout(new GridLayout(1, 4, 100, 100));
		temp.setBackground(new Color(15069416));
		JContent.setBackground(new Color(15069416));
//		temp.add(new JPanel());
//		temp.add(new JPanel());
//		temp.add(new JPanel());
//		temp.add(new JPanel());
		temp.add(dkhp_content);
		temp.add(hpddk_content);
		temp.add(new JPanel());
		temp.add(new JPanel());

		temp.setAlignmentX(CENTER_ALIGNMENT);
		JContent.add(temp);

		// Set Action listener
		dkhp_content.addActionListener(this);
		hpddk_content.addActionListener(this);

		return JContent;
	}

	JPanel account(String name) {
		JPanel JAccount = new JPanel();
		account_top = createButtonIcon(".\\src\\resources\\account.png", 64, 64);
		// Two String
		JPanel temp = new JPanel();
		JLabel greeter_label = new JLabel("Xin Chào");
		JLabel name_label = new JLabel(name);
		greeter_label.setFont(new Font("", Font.PLAIN, 24));
		name_label.setFont(new Font("", Font.PLAIN, 24));
		greeter_label.setForeground(new Color(0, 0, 0, 40));
		temp.setLayout(new BoxLayout(temp, BoxLayout.PAGE_AXIS));
		temp.add(greeter_label);
		temp.add(name_label);

		// Logout
		// Button Icon
		logout = createButtonIcon(".\\src\\resources\\logout.png", 32, 32);

		// Set layout
		JAccount.setLayout(new BoxLayout(JAccount, BoxLayout.X_AXIS));
		JAccount.setBackground(new Color(16777215));
		temp.setBackground(new Color(16777215));
		account_top.setBackground(new Color(16777215));
		JAccount.add(Box.createRigidArea(new Dimension(20, 12)));
		JAccount.add(account_top);
		JAccount.add(Box.createRigidArea(new Dimension(20, 12)));
		JAccount.add(temp);
		JAccount.add(Box.createRigidArea(new Dimension(20, 12)));
		JAccount.add(logout);

		// Set Action
		logout.addActionListener(this);
		account_top.addActionListener(this);
		return JAccount;
	}

	private Image getScaledImage(Image srcImg, int w, int h) {
		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();
		return resizedImg;
	}

	JButton createButtonIcon(String link, int w, int h) {
		// Image
		ImageIcon icon = new ImageIcon(link, "a pretty but meaningless splat");
		icon.setImage(getScaledImage(icon.getImage(), w, h));
		JButton image = new JButton(icon);
		image.setBorder(null);
		image.setBackground(new Color(16777215));
		return image;
	}

	JButton CreateManagerSide(String imageLink, String name) {
		ImageIcon icon = new ImageIcon(imageLink, "a pretty but meaningless splat");
		icon.setImage(getScaledImage(icon.getImage(), 32, 32));
		JButton JAccountSide = new JButton(name, icon);
		JAccountSide.setForeground(Color.white);
		JAccountSide.setFont(new Font("", Font.PLAIN, 24));
		JAccountSide.setBackground(new Color(000000));
		JAccountSide.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.white));
		JAccountSide.setFocusable(false);
		return JAccountSide;
	}

	JButton CreateAccountSide(String name) {
		ImageIcon icon = new ImageIcon(".\\src\\resources\\account.png", "a pretty but meaningless splat");
		icon.setImage(getScaledImage(icon.getImage(), 64, 64));
		JButton JAccountSide = new JButton(name, icon);
		JAccountSide.setForeground(Color.white);
		JAccountSide.setFont(new Font("", Font.PLAIN, 24));
		JAccountSide.setVerticalTextPosition(AbstractButton.BOTTOM);
		JAccountSide.setHorizontalTextPosition(AbstractButton.CENTER);
		JAccountSide.setBackground(new Color(000000));
		JAccountSide.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.white));
		JAccountSide.setFocusable(false);
		return JAccountSide;
	}

	JButton CreateManagerContent(String imageLink, String name) {
		ImageIcon icon = new ImageIcon(imageLink, "a pretty but meaningless splat");
		icon.setImage(getScaledImage(icon.getImage(), 64, 64));
		JButton JAccountSide = new JButton(name, icon);
		JAccountSide.setForeground(Color.red);
		JAccountSide.setFont(new Font("", Font.PLAIN, 18));
		JAccountSide.setBackground(Color.white);
		JAccountSide.setBorder(BorderFactory.createLineBorder(Color.red));
		JAccountSide.setFocusable(false);
		JAccountSide.setVerticalTextPosition(AbstractButton.BOTTOM);
		JAccountSide.setHorizontalTextPosition(AbstractButton.CENTER);
		JAccountSide.setSize(new Dimension(200, 150));
		JAccountSide.setPreferredSize(new Dimension(200, 150));
		return JAccountSide;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == logout) {
			new LoginFrame();
			this.dispose();
		} else if (e.getSource() == account_top) {
			new CapNhatThongTinSinhVienFrame(this, _sv);
			this.dispose();
			new MainScreenSvFrame(_sv);
		} else if (e.getSource() == account_side) {
			new CapNhatThongTinSinhVienFrame(this, _sv);

		} else if (e.getSource() == dkhp_side) {
			this.dispose();
			new DKHPSVFrame(_sv);
		} else if (e.getSource() == hpddk_side) {
			this.dispose();
			new XemHPDaDKFrame(_sv);
		} else if (e.getSource() == dkhp_content) {
			this.dispose();
			new DKHPSVFrame(_sv);
		} else if (e.getSource() == hpddk_content) {
			this.dispose();
			new XemHPDaDKFrame(_sv);
		}
	}
}
