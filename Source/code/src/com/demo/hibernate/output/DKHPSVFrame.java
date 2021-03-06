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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
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

import com.demo.hibernate.dao.DotDKHPDao;
import com.demo.hibernate.dao.HocPhanDao;
import com.demo.hibernate.dao.KQDKHPDao;
import com.demo.hibernate.dao.SinhVienDao;
import com.demo.hibernate.entity.DotDKHP;
import com.demo.hibernate.entity.HocPhan;
import com.demo.hibernate.entity.KQDKHP;
import com.demo.hibernate.entity.SinhVien;

public class DKHPSVFrame extends JFrame implements ActionListener {
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	JButton account_top, logout;
	JButton account_side, dkhp_side, hpddk_side;
	JTable tableMH;
	JTable tableDK;
	SinhVien _sv;
	JButton xacNhanBtn;
	List<HocPhan> listsHP = new ArrayList<>();
	List<KQDKHP> listsDaDK;

	DKHPSVFrame(SinhVien sv) {
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
		this.setTitle("????ng k?? h???c ph???n SV");
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

		JLabel title = new JLabel("????ng k?? h???c ph???n SV");
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
		dkhp_side = CreateManagerSide(".\\src\\resources\\dkhp.png", "????ng k?? h???c ph???n");
		hpddk_side = CreateManagerSide(".\\src\\resources\\history.png", "H???c ph???n ???? DK");
		dkhp_side.setBackground(Color.blue);
		dkhp_side.setForeground(Color.white);
		dkhp_side.setEnabled(false);

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
		LocalDateTime present = java.time.LocalDateTime.now();
		List<DotDKHP> list = DotDKHPDao.layDotDKHPHienTai(present);
		JPanel JContent = new JPanel();

		// System.out.println(present);
		// System.out.println(dot);
		if (list.size() == 0) {
			JLabel label = new JLabel("Hi???n t???i kh??ng ph???i l?? th???i gian ????ng k?? h???c ph???n");
			label.setFont(new Font("", Font.PLAIN, 48));
			label.setForeground(Color.red);
			JContent.add(label);
		} else {
			// Data
			String[] columnNames1 = { "M?? HP", "T??n M??n H???c", "T??n GVLT", "M?? l???p", "S??? TC", "???? ????ng k??", "S?? s???",
					"L???ch h???c ", "H???y ????ng K??" };
			Object[][] data1 = layMonDaDangKy(list);

			String[] columnNames2 = { "M?? HP", "T??n M??n H???c", "T??n GVLT", "M?? l???p", "S??? TC", "???? ????ng k??", "S?? s???",
					"L???ch h???c ", "????ng k??" };
			Object[][] data2 = layMonDuocPhepDangKy(list, data1);

			// Title Cac mon da dang ky
			JPanel panel1 = new JPanel();
			JLabel title1 = new JLabel("C??c M??n ???? ????ng K??");
			title1.setFont(new Font("", Font.PLAIN, 24));
			title1.setForeground(Color.red);
			panel1.add(title1);

			// Table cac mon da DK
			tableMH = createTableContent(columnNames1, data1);
			JScrollPane scrollPane = new JScrollPane(tableMH);
			scrollPane.setMaximumSize(new Dimension(1000, 250));
			scrollPane.setPreferredSize(new Dimension(1000, 250));
			scrollPane.setMinimumSize(new Dimension(1000, 200));
			// tableMH.getModel().addTableModelListener(this);
			// Title DS cac mon DK
			JPanel panel2 = new JPanel();
			JLabel title2 = new JLabel(
					"Danh S??ch C??c H???c Ph???n(" + list.get(0).getTbBD() + "  -   " + list.get(0).getTbKT() + ")");
			title2.setFont(new Font("", Font.PLAIN, 24));
			title2.setForeground(Color.red);
			panel2.add(title2);

			// Table cac mon DK
			tableDK = createTableContent(columnNames2, data2);
			JScrollPane scrollPane1 = new JScrollPane(tableDK);
			scrollPane1.setMaximumSize(new Dimension(1000, 250));
			scrollPane1.setPreferredSize(new Dimension(1000, 250));
			scrollPane1.setMinimumSize(new Dimension(1000, 200));
			// tableDK.getModel().addTableModelListener(this);

			// Btn Xac nhan
			JPanel panelButton = createXacNhanButton();
			// Set LAYOUT
			JContent.setLayout(new BoxLayout(JContent, BoxLayout.Y_AXIS));
			JContent.add(panel1);
			JContent.add(Box.createRigidArea(new Dimension(0, 5)));
			JContent.add(scrollPane);
			JContent.add(Box.createRigidArea(new Dimension(0, 5)));
			JContent.add(panel2);
			JContent.add(Box.createRigidArea(new Dimension(0, 5)));
			JContent.add(scrollPane1);
			JContent.add(Box.createRigidArea(new Dimension(0, 5)));
			JContent.add(panelButton);
		}
		return JContent;
	}

	JPanel account(String name) {
		JPanel JAccount = new JPanel();
		account_top = createButtonIcon(".\\src\\resources\\account.png", 64, 64);
		// Two String
		JPanel temp = new JPanel();
		JLabel greeter_label = new JLabel("Xin Ch??o");
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

	JTable createTableContent(String[] columnNames, Object[][] data) {
//		Object[][] data = { {} };

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
				case 8:
					return Boolean.class;
				default:
					return String.class;
				}
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				/*
				 * Set the 7th column as editable and rest non- editable
				 */
				if (column == 8) {
					return true;
				} else {
					return false;
				}
			}
		};
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.setRowHeight(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(7).setPreferredWidth(200);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < columnNames.length - 1; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		table.setGridColor(Color.orange);

		return table;
	}

	JPanel createXacNhanButton() {
		JPanel PanelButton = new JPanel();
		xacNhanBtn = CreateManagerSide(".\\src\\resources\\add.png", "X??c nh???n");
		xacNhanBtn.setBackground(new Color(15790288));
		xacNhanBtn.setForeground(Color.black);
		xacNhanBtn.setAlignmentX(RIGHT_ALIGNMENT);
		PanelButton.add(xacNhanBtn);
		Dimension dim = new Dimension(250, 50);
		xacNhanBtn.setPreferredSize(dim);
		xacNhanBtn.setMaximumSize(dim);
		xacNhanBtn.addActionListener(this);
		return PanelButton;
	}

	Object[][] layMonDaDangKy(List<DotDKHP> list) {
		// Data
		int soMon, soTC = 0;
		listsDaDK = _sv.getKqdkhps();
		List<Object[]> listHP = new ArrayList<>();
		for (int i = 0; i < listsDaDK.size(); i++) {
			HocPhan hp = listsDaDK.get(i).getHocPhan();
//			HocPhanDao.seftUpdadte(hp);

			if (checkKQDKHP(hp, list)) {
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
						hp.getMonHoc().getSoTC(), hp.getSlotToiDa() - hp.getSlot(), hp.getSlotToiDa(), lichhoc, false };
				listHP.add(temp);
				System.out.println("Hoc Phan Da DK" + hp);
				soTC += hp.getMonHoc().getSoTC();
			}
		}
		soMon = listHP.size();
		Object[][] data = new Object[listHP.size()][];
		for (int i = 0; i < listHP.size(); i++) {
			data[i] = listHP.get(i);
		}
		return data;
	}

	Boolean checkKQDKHP(HocPhan hp, List<DotDKHP> list) {
		for (int i = 0; i < list.size(); i++) {
			if (hp.getDotDKHP().getMaDot().equals(list.get(i).getMaDot()))
				return true;
		}
		return false;
	}

	Object[][] layMonDuocPhepDangKy(List<DotDKHP> list, Object[][] data1) {
		for (int i = 0; i < list.size(); i++) {
			listsHP.addAll(list.get(i).getHocPhans());
		}

		for (int i = 0; i < listsHP.size(); i++) {
			for (int j = 0; j < data1.length; j++) {
				if (listsHP.get(i).getMaHP().equals(data1[j][0])) {
					listsHP.remove(i);
					i--;
					break;
				}
			}
		}
		List<Object[]> listHP = new ArrayList<>();
		for (int i = 0; i < listsHP.size(); i++) {
			HocPhan hp = listsHP.get(i);
//			HocPhanDao.seftUpdadte(hp);

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
					hp.getMonHoc().getSoTC(), hp.getSlotToiDa() - hp.getSlot(), hp.getSlotToiDa(), lichhoc, false };
			listHP.add(temp);
			System.out.println("Hoc Phan Da DK" + hp);
		}
		Object[][] data = new Object[listHP.size()][];
		for (int i = 0; i < listHP.size(); i++) {
			data[i] = listHP.get(i);
		}
		return data;
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
			JOptionPane.showMessageDialog(this, "DKHP  Side");
		} else if (e.getSource() == hpddk_side) {
			this.dispose();
			new XemHPDaDKFrame(_sv);
		} else if (e.getSource() == xacNhanBtn) {
			List<HocPhan> result = new ArrayList<>();
			// Lay mon huy
			List<HocPhan> huyDK = new ArrayList();
			for (int i = 0; i < listsDaDK.size(); i++) {
				if ((boolean) tableMH.getValueAt(i, 8)) {
					huyDK.add(listsDaDK.get(i).getHocPhan());
//					System.out.println(listsDaDK.get(i).getHocPhan());
				} else {
					result.add(listsDaDK.get(i).getHocPhan());
				}
			}
			// Lay mon dk
			List<HocPhan> muonDK = new ArrayList();
			for (int i = 0; i < listsHP.size(); i++) {
				if ((boolean) tableDK.getValueAt(i, 8)) {
					muonDK.add(listsHP.get(i));
					result.add(listsHP.get(i));
//					System.out.println(listsHP.get(i));
				}
			}
			// Kiem tra Dieu kien: mon < 8 + khong trung gio + slot
			for (int i = 0; i < result.size(); i++) {
				System.out.println(result.get(i));
				if (result.get(i).getSlot() <= 0) {
					JOptionPane.showMessageDialog(this, "M??n ???????c ch???n ???? h???t slot" + result.get(i),
							"????ng K?? H???c Ph???n Th???t B???i", JOptionPane.ERROR_MESSAGE);
				}
			}
			if (result.size() > 8) {
				JOptionPane.showMessageDialog(this, "S??? m??n ????ng k?? v?????t qu?? m??c t???i ??a (8 m??n)",
						"????ng K?? H???c Ph???n Th???t B???i", JOptionPane.ERROR_MESSAGE);
				return;
			}
			HashSet<String> lichHoc = new HashSet<String>();
			int size_old = 0;
			for (int i = 0; i < result.size(); i++) {
				lichHoc.add(result.get(i).getThu() + " " + result.get(i).getCa());
				if (size_old == lichHoc.size()) {
					JOptionPane.showMessageDialog(this, "2 m??n tr??ng gi??? " + result.get(i).getMonHoc(),
							"????ng K?? H???c Ph???n Th???t B???i", JOptionPane.ERROR_MESSAGE);
					return;
				}
				size_old = lichHoc.size();
			}
			// Huy DK
			for (int i = 0; i < huyDK.size(); i++) {
				KQDKHPDao.xaoKQDKHP(_sv, huyDK.get(i));
				HocPhan hp = huyDK.get(i);
				hp.setSlot(hp.getSlot() + 1);
				HocPhanDao.updateHocPhan(hp);
			}
			// Dang Ky
			for (int i = 0; i < muonDK.size(); i++) {
				KQDKHPDao.themKQDKHP(new KQDKHP(_sv, muonDK.get(i), LocalDateTime.now()));
//				HocPhanDao.seftUpdadte(muonDK.get(i));
				HocPhan hp = muonDK.get(i);
				hp.setSlot(hp.getSlot() - 1);
				HocPhanDao.updateHocPhan(hp);
			}
			new XemHPDaDKFrame(_sv);
			this.dispose();
		}
	}
}