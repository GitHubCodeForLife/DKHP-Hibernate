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
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.demo.hibernate.dao.GiaoVuDao;
import com.demo.hibernate.dao.LopDao;
import com.demo.hibernate.entity.GiaoVu;
import com.demo.hibernate.entity.Lop;

public class QuanLyLopHocFrame extends JFrame implements ActionListener, TableModelListener {
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	JButton logout, account_top, account_side, monhoc_side, hocky_side, lophoc_side, sinhvien_side, dotDKHP_side,
			hocphan_side, giaovu_side;
	JTable tableHK;
	GiaoVu _gv;
	JButton add_content, search_content;
	JTextField search_textField;
	List<Lop> listLopHoc;
	DefaultTableModel model;

	QuanLyLopHocFrame(GiaoVu gv) {
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
		this.setTitle("Quan Ly Lop Hoc Frame");
		this.setVisible(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		// this.setUndecorated(true);
		this.setVisible(true);
	}

	JPanel navbarTop() {
		JPanel JNavbarTop = new JPanel();

		JPanel line_start = new JPanel();
		line_start.setSize(100, 100);
		// Title

		JLabel title = new JLabel("Quản lý Lớp Học");
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
		lophoc_side.setBackground(Color.blue);
		lophoc_side.setForeground(Color.black);
		lophoc_side.setEnabled(false);
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

		// Table
		tableHK = createTableContent();
		JScrollPane scrollPane = new JScrollPane(tableHK);
		scrollPane.setMaximumSize(new Dimension(1000, 650));
		scrollPane.setPreferredSize(new Dimension(1000, 600));
		scrollPane.setMinimumSize(new Dimension(1000, 200));
		tableHK.getModel().addTableModelListener(this);
		// Add button
		JPanel panelButton = createAddButton();
//		addBtn.setMaximumSize(new Dimension(200, 100));
		// Layout
		JContent.setLayout(new BoxLayout(JContent, BoxLayout.Y_AXIS));
		JContent.add(Box.createRigidArea(new Dimension(0, 10)));
		JContent.add(Box.createRigidArea(new Dimension(0, 10)));
		JContent.add(scrollPane);
		JContent.add(Box.createRigidArea(new Dimension(0, 10)));
		JContent.add(panelButton);
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

	Object[][] convertData(List<Lop> listLopHoc) {
		List<Object[]> listHKO = new ArrayList<>();
		for (int i = 0; i < listLopHoc.size(); i++) {
			Lop lop = listLopHoc.get(i);
			LopDao.seftUpdate(lop);
			Object[] temp = { i + 1, lop.getMaLop(), lop.getTongNam(), lop.getTongSV() - lop.getTongNam(),
					lop.getTongSV(), false };
			listHKO.add(temp);
			System.out.println("Danh Sach Lop Hoc" + lop);

		}
		Object[][] data = new Object[listHKO.size()][];
		for (int i = 0; i < listHKO.size(); i++) {
			data[i] = listHKO.get(i);
		}
		return data;
	}

	JTable createTableContent() {
		String[] columnNames = { "STT", "Mã Lớp", "Tổng Sinh Viên Nam", "Tổng Sinh Viên Nữ", "Tổng Sinh Viên",
				"Xóa Lớp Học" };
		listLopHoc = LopDao.layDanhSachLop();
		Object[][] data = convertData(listLopHoc);
		model = new DefaultTableModel(data, columnNames);

		JTable table = new JTable(model) {
			private static final long serialVersionUID = 1L;

			/*
			 * @Override public Class getColumnClass(int column) { return getValueAt(0,
			 * column).getClass(); }
			 */
			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 5:
					return Boolean.class;
				default:
					return String.class;

				}
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				/*
				 * Set the 5th column as editable and rest non- editable
				 */
				if (column == 5) {
					return true;
				} else {
					return false;
				}
			}
		};
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.setRowHeight(30);
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
//		table.getColumnModel().getColumn(2).setPreferredWidth(200);
//		table.getColumnModel().getColumn(5).setPreferredWidth(200);
//		table.getColumnModel().getColumn(6).setPreferredWidth(200);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < columnNames.length - 1; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		table.setGridColor(Color.orange);

		return table;
	}

	JPanel createAddButton() {
		JPanel PanelButton = new JPanel();
		add_content = CreateManagerSide(".\\src\\resources\\add.png", "Thêm Lớp Học Mới");
		add_content.setBackground(new Color(15790288));
		add_content.setForeground(Color.black);
		add_content.setAlignmentX(RIGHT_ALIGNMENT);
		PanelButton.add(add_content);
		add_content.addActionListener(this);
		return PanelButton;
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
		} else if (e.getSource() == add_content) {
			// Them lop hoc moi
			String s = JOptionPane.showInputDialog(this, "Nhập Mã Lớp muốn thêm vào", "Thêm Lớp Mới",
					JOptionPane.PLAIN_MESSAGE);
			if (s == null) {
				JOptionPane.showMessageDialog(this, "Không được để mã lớp trống", "Thêm Lớp thất bại",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			Lop lop = new Lop(s, 0, 0);
			if (LopDao.themLop(lop)) {
				JOptionPane.showMessageDialog(this, "Đã thêm một lớp học mới");
			} else {
				JOptionPane.showMessageDialog(this, "Thêm lớp học không thành công", "Thêm lớp học thất bại",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			listLopHoc = LopDao.layDanhSachLop();
			Object[][] data = convertData(listLopHoc);
			// Xoa bang
			clearTable();
			// Add content
			for (int i = 0; i < data.length; i++)
				model.addRow(data[i]);
		}
	}

	public void clearTable() {
		int rowCount = model.getRowCount();
		// Remove rows one by one from the end of the table
		for (int i = rowCount - 1; i >= 0; i--) {
			model.removeRow(i);
		}
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		int row = tableHK.getSelectedRow();
		int col = tableHK.getSelectedColumn();
		if (row == -1 || col == -1)
			return;

		Lop lop = listLopHoc.get(row);
//
//		// Custom button text
//		if (lop.getSinhViens().size() > 0) {
//			JOptionPane.showMessageDialog(this, "Lớp hiện tại đang có sinh viên nên không thể xóa được.",
//					"Xóa Lớp thất bại", JOptionPane.ERROR_MESSAGE);
//		} else {
		Object[] options = { "Quay Lại", "Xóa Lớp Học" };

		int n = JOptionPane.showOptionDialog(this, "Bạn có thực sự muốn xóa lớp học này không", "Xóa lớp học",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if (n == 1) {
			if (LopDao.xaoLop(lop.getMaLop())) {
				JOptionPane.showMessageDialog(this, "Đã xóa lớp học hành công");
			} else {
				JOptionPane.showMessageDialog(this, "Xóa lớp học không thành công", "Xóa lớp học thất bại",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
//		}

		this.dispose();
		new QuanLyLopHocFrame(_gv);
	}
}