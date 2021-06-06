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

import com.demo.hibernate.dao.GiaoVuDao;
import com.demo.hibernate.entity.GiaoVu;

public class MainScreenGVFrame extends JFrame implements ActionListener {
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	JButton logout, account_top, account_side, giaovu_side, monhoc_side, hocky_side, lophoc_side, sinhvien_side,
			dotDKHP_side, hocphan_side;
	JButton giaovu_content, monhoc_content, hocky_content, lophoc_content, sinhvien_content, dotDKHP_content,
			hocphan_content;
	GiaoVu _gv;

	MainScreenGVFrame(GiaoVu gv) {
		_gv = GiaoVuDao.layThongTinGiaoVu(gv.getTKGV());
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println(dim.getSize());

		// Set layout and component on Frame
		Container con = this.getContentPane();
		con.setLayout(new BorderLayout());
		JPanel JNavbarTop = navbarTop();
		JPanel JNavbarSide = navbarSide();
		JPanel JContent = content();

		JNavbarSide.setSize(new Dimension(300, 100));

		con.add(JNavbarTop, BorderLayout.PAGE_START);
		con.add(JNavbarSide, BorderLayout.LINE_START);
		con.add(JContent, BorderLayout.CENTER);
		// Set Frame attribute
		this.setTitle("Main Screen GV");
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		// this.setUndecorated(true);
		this.setVisible(true);

	}

	JPanel navbarTop() {
		JPanel JNavbarTop = new JPanel();

		JPanel line_start = new JPanel();
		line_start.setSize(100, 100);
		// Title

		JLabel title = new JLabel("Quản lý Giáo Vụ");
		title.setFont(new Font("", Font.PLAIN, 48));

		// Account
		JPanel JAccount = account(_gv.getTENGV());

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

	JPanel navbarSide() {
		JPanel JNavbarSide = new JPanel();
		JPanel temp2 = new JPanel();
		JPanel temp = new JPanel();
		account_side = CreateAccountSide("   " + _gv.getTKGV() + "  ");
		giaovu_side = CreateManagerSide(".\\src\\resources\\manager.png", "Giáo Vụ");
		monhoc_side = CreateManagerSide(".\\src\\resources\\monhoc.png", "Môn Học");
		hocky_side = CreateManagerSide(".\\src\\resources\\hocky.png", "Học Kỳ");
		lophoc_side = CreateManagerSide(".\\src\\resources\\class.png", "Lớp Học");
		sinhvien_side = CreateManagerSide(".\\src\\resources\\sv.png", "Sinh Viên");
		dotDKHP_side = CreateManagerSide(".\\src\\resources\\dotdkhp.png", "Đợt DKHP");
		hocphan_side = CreateManagerSide(".\\src\\resources\\hp.png", "Học Phần");
		// Set layout
		JNavbarSide.setLayout(new BorderLayout());
		temp.setLayout(new GridLayout(12, 1));
		temp.setBackground(new Color(000000));
		// temp2.setBackground(new Color(000000));
		// temp2.add(JAcountSide);
		temp.add(giaovu_side);
		temp.add(monhoc_side);
		temp.add(hocky_side);
		temp.add(lophoc_side);
		temp.add(sinhvien_side);
		temp.add(dotDKHP_side);
		temp.add(hocphan_side);
		JNavbarSide.add(account_side, BorderLayout.PAGE_START);
		JNavbarSide.add(temp, BorderLayout.CENTER);

		// Set Action listener
		giaovu_side.addActionListener(this);
		account_side.addActionListener(this);
		monhoc_side.addActionListener(this);
		hocky_side.addActionListener(this);
		lophoc_side.addActionListener(this);
		sinhvien_side.addActionListener(this);
		dotDKHP_side.addActionListener(this);
		hocphan_side.addActionListener(this);
		return JNavbarSide;
	}

	JPanel content() {
		JPanel JContent = new JPanel();
		giaovu_content = CreateManagerContent(".\\src\\resources\\manager.png", "   Giáo Vụ   ");
		monhoc_content = CreateManagerContent(".\\src\\resources\\monhoc.png", "  Môn Học  ");
		hocky_content = CreateManagerContent(".\\src\\resources\\hocky.png", "  Học Kỳ  ");
		lophoc_content = CreateManagerContent(".\\src\\resources\\class.png", "		Lớp Học		");
		sinhvien_content = CreateManagerContent(".\\src\\resources\\sv.png", "		Sinh Viên		");
		dotDKHP_content = CreateManagerContent(".\\src\\resources\\dotdkhp.png", "		Đợt DKHP		");
		hocphan_content = CreateManagerContent(".\\src\\resources\\hp.png", "		Học Phần		");
		// Set lay out
		JPanel temp = new JPanel();
		temp.setLayout(new GridLayout(2, 4, 100, 100));
		temp.setBackground(new Color(15069416));
		JContent.setBackground(new Color(15069416));
		temp.add(giaovu_content);
		temp.add(monhoc_content);
		temp.add(hocky_content);
		temp.add(lophoc_content);
		temp.add(sinhvien_content);
		temp.add(dotDKHP_content);
		temp.add(hocphan_content);
		temp.setAlignmentX(CENTER_ALIGNMENT);
		JContent.add(temp);

		// Set Action listener
		giaovu_content.addActionListener(this);
		monhoc_content.addActionListener(this);
		hocky_content.addActionListener(this);
		lophoc_content.addActionListener(this);
		sinhvien_content.addActionListener(this);
		dotDKHP_content.addActionListener(this);
		hocphan_content.addActionListener(this);
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

	JButton CreateManagerContent(String imageLink, String name) {
		ImageIcon icon = new ImageIcon(imageLink, "a pretty but meaningless splat");
		icon.setImage(getScaledImage(icon.getImage(), 64, 64));
		JButton JAccountSide = new JButton(name, icon);
		JAccountSide.setForeground(Color.red);
		JAccountSide.setFont(new Font("", Font.PLAIN, 32));
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
			new CapNhatThongTinGiaoVuFrame(this, _gv);
		} else if (e.getSource() == account_side) {
			new CapNhatThongTinGiaoVuFrame(this, _gv);
		} else if (e.getSource() == giaovu_side) {
			new QuanLyGiaoVuFrame(_gv);
			this.dispose();
		} else if (e.getSource() == monhoc_side) {
			new QuanLyMonHocFrame(_gv);
			this.dispose();
		} else if (e.getSource() == hocky_side) {
			new QuanLyHocKyFrame(_gv);
			this.dispose();
		} else if (e.getSource() == lophoc_side) {
			new QuanLyLopHocFrame(_gv);
			this.dispose();
		} else if (e.getSource() == sinhvien_side) {
			new QuanLySinhVienFrame(_gv);
			this.dispose();
		} else if (e.getSource() == hocphan_side) {
			new QuanLyHocPhanFrame(_gv);
			this.dispose();
		} else if (e.getSource() == dotDKHP_side) {
			new QuanLyDotDKHPFrame(_gv);
			this.dispose();
		} else if (e.getSource() == giaovu_content) {
			new QuanLyGiaoVuFrame(_gv);
			this.dispose();
		} else if (e.getSource() == monhoc_content) {
			new QuanLyMonHocFrame(_gv);
			this.dispose();

		} else if (e.getSource() == hocky_content) {
			new QuanLyHocKyFrame(_gv);
			this.dispose();
		} else if (e.getSource() == lophoc_content) {
			new QuanLyLopHocFrame(_gv);
			this.dispose();

		} else if (e.getSource() == sinhvien_content) {
			new QuanLySinhVienFrame(_gv);
			this.dispose();
		} else if (e.getSource() == hocphan_content) {
			new QuanLyHocPhanFrame(_gv);
			this.dispose();

		} else if (e.getSource() == dotDKHP_content) {
			new QuanLyDotDKHPFrame(_gv);
			this.dispose();
		}

	}
}