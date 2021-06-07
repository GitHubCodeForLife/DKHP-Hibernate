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
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.demo.hibernate.dao.HocKiDao;
import com.demo.hibernate.dao.SinhVienDao;
import com.demo.hibernate.entity.HocKi;
import com.demo.hibernate.entity.HocPhan;
import com.demo.hibernate.entity.KQDKHP;
import com.demo.hibernate.entity.SinhVien;

public class XemHPDaDKFrame extends JFrame implements ActionListener {
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	JButton account_top, logout;
	JButton account_side, dkhp_side, hpddk_side;
	JTable tableMH;
	SinhVien _sv;

	XemHPDaDKFrame(SinhVien sv) {
		_sv = SinhVienDao.layThongTinSinhVien(sv.getMaSV());
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println(dim.getSize());

		// Set layout and component on Frame
		Container con = this.getContentPane();
		con.setLayout(new BorderLayout());
		JPanel JNavbarTop = navbarTop(sv.getTenSV());
		JPanel JNavbarSide = navbarSide(sv.getTenSV());
		JPanel JContent = content();

		JNavbarSide.setSize(new Dimension(300, 100));

		con.add(JNavbarTop, BorderLayout.PAGE_START);
		con.add(JNavbarSide, BorderLayout.LINE_START);
		con.add(JContent, BorderLayout.CENTER);
		// Set Frame attribute
		this.setTitle("Xem Hoc Phan Da DK");
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
		hpddk_side.setBackground(Color.blue);
		hpddk_side.setForeground(Color.white);
		hpddk_side.setEnabled(false);

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
		// Data
		int soMon, soTC = 0;
		List<KQDKHP> lists = _sv.getKqdkhps();
		List<Object[]> listHP = new ArrayList<>();
		for (int i = 0; i < lists.size(); i++) {
			HocPhan hp = lists.get(i).getHocPhan();
			if (!hp.getDotDKHP().getHocKi().getMaHK().equals(HocKiDao.layHocKiHienTai().getMaHK()))
				continue;
			String lichhoc = null;
			if (hp.getCa() == 0) {
				lichhoc = "T" + hp.getThu() + "(7h30-9h30)-" + "P." + hp.getTenPhong();
			} else if (hp.getCa() == 1) {
				lichhoc = "T" + hp.getThu() + "(9h30-11h30)-" + "P." + hp.getTenPhong();
			} else if (hp.getCa() == 2) {
				lichhoc = "T" + hp.getThu() + "(13h30-15h30)-" + "P." + hp.getTenPhong();
			} else if (hp.getCa() == 3) {
				lichhoc = "T" + hp.getThu() + "(15h30-17h30)-" + "P." + hp.getTenPhong();
			}
			Object[] temp = { hp.getMaHP(), hp.getMonHoc().getTenMH(), hp.getTenGVLT(), hp.getMaLop(),
					hp.getMonHoc().getSoTC(), lichhoc };
			listHP.add(temp);
			System.out.println(hp);
			soTC += hp.getMonHoc().getSoTC();
		}
		soMon = listHP.size();
		Object[][] data = new Object[listHP.size()][];
		for (int i = 0; i < listHP.size(); i++) {
			data[i] = listHP.get(i);
		}

		JPanel JContent = new JPanel();
		// Title
		JPanel panelTitle = createTitleContent();
		// Table
		tableMH = createTableContent(data);
		JScrollPane scrollPane = new JScrollPane(tableMH);
		scrollPane.setMaximumSize(new Dimension(1000, 650));
		scrollPane.setPreferredSize(new Dimension(1000, 600));
		scrollPane.setMinimumSize(new Dimension(1000, 200));
		// Result
		JPanel panelResult = createResultContent(soMon, soTC);

		// Layout
		JContent.setLayout(new BoxLayout(JContent, BoxLayout.Y_AXIS));
		JContent.add(Box.createRigidArea(new Dimension(0, 10)));
		JContent.add(panelTitle);
		JContent.add(Box.createRigidArea(new Dimension(0, 10)));
		JContent.add(scrollPane);
		JContent.add(Box.createRigidArea(new Dimension(0, 10)));
		JContent.add(panelResult);
		// JContent.add(tableGV);
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

	JPanel createTitleContent() {
		HocKi hk = HocKiDao.layHocKiHienTai();
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Kết quả DKHP " + hk.getTenHK() + " - Năm " + hk.getNam());
		label.setFont(new Font("", Font.PLAIN, 32));
		label.setForeground(Color.red);
		panel.add(label);
		return panel;
	}

	JTable createTableContent(Object[][] data) {
		String[] columnNames = { "Mã HP", "Tên Môn Học", "Tên GVLT", "Mã Lớp", "STC", "Lịch Học" };

		DefaultTableModel model = new DefaultTableModel(data, columnNames);

		JTable table = new JTable(model) {
			private static final long serialVersionUID = 1L;

			/*
			 * @Override public Class getColumnClass(int column) { return getValueAt(0,
			 * column).getClass(); }
			 */
			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 4:
					return Integer.class;
				default:
					return String.class;
				}
			}
		};
		table.setEnabled(false);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.setRowHeight(30);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(1).setPreferredWidth(300);
		table.getColumnModel().getColumn(5).setPreferredWidth(200);
		for (int i = 0; i < columnNames.length - 1; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		table.setGridColor(Color.orange);

		return table;
	}

	JPanel createResultContent(int soMon, int soTc) {
		JPanel panel = new JPanel();
		// Label
		JLabel label = new JLabel("Kết quả DKHP HK1 Năm 2020 - 2021: ");
		label.setFont(new Font("", Font.PLAIN, 32));
//		label.setForeground(Color.red);
		// result
		JLabel result = new JLabel(soMon + " môn (" + soTc + " tín chỉ)");
		result.setFont(new Font("", Font.BOLD, 32));
		result.setForeground(Color.red);

		panel.add(label);
		panel.add(result);
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == logout) {
			this.dispose();
			new LoginFrame();
		} else if (e.getSource() == account_top) {
			new CapNhatThongTinSinhVienFrame(this, _sv);
			this.dispose();
			new MainScreenSvFrame(_sv);
		} else if (e.getSource() == account_side) {
			new CapNhatThongTinSinhVienFrame(this, _sv);
			this.dispose();
			new MainScreenSvFrame(_sv);
		} else if (e.getSource() == dkhp_side) {
			this.dispose();
			new DKHPSVFrame(_sv);
		} else if (e.getSource() == hpddk_side) {
			JOptionPane.showMessageDialog(this, "HP DDK Side");
		}
	}
}