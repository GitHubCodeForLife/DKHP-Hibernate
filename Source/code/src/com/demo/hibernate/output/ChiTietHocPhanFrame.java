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
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.demo.hibernate.dao.GiaoVuDao;
import com.demo.hibernate.dao.HocPhanDao;
import com.demo.hibernate.entity.GiaoVu;
import com.demo.hibernate.entity.HocPhan;
import com.demo.hibernate.entity.KQDKHP;
import com.demo.hibernate.entity.SinhVien;

public class ChiTietHocPhanFrame extends JFrame implements ActionListener {
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	JTable tableGV;
	GiaoVu _gv;
	HocPhan _hp;
	JButton search_content;
	JButton backBtn, deleteBtn, repairBtn;
	JTextField search_textField;
	List<SinhVien> listSinhVien = new ArrayList<>();
	DefaultTableModel model;

	ChiTietHocPhanFrame(GiaoVu gv, HocPhan hp) {
		_gv = GiaoVuDao.layThongTinGiaoVu(gv.getTKGV());
		_hp = HocPhanDao.layThongTinHocPhan(hp.getMaHP());
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println(dim.getSize());

		// Set layout and component on Frame
		Container con = this.getContentPane();
		con.setLayout(new BorderLayout());
		JPanel JNavbarTop = navbarTop();
		JPanel JContent = content();

		con.add(JNavbarTop, BorderLayout.PAGE_START);
		con.add(JContent, BorderLayout.CENTER);
		// Set Frame attribute
		this.setTitle("Chi Tiet Hoc Phan Frame");
		this.setVisible(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		// this.setUndecorated(true);
		this.setVisible(true);
	}

	JPanel navbarTop() {
		JPanel JNavbarTop = new JPanel();

		JPanel line_start = new JPanel();
		backBtn = createButtonIcon(".\\src\\resources\\previous.png", 64, 64);
		line_start.add(backBtn);
		backBtn.addActionListener(this);
		line_start.setSize(100, 100);
		// Title

		JLabel title = new JLabel("Học Phần " + _hp.getMaHP());
		title.setFont(new Font("", Font.PLAIN, 48));

		// Set layout
		JNavbarTop.setLayout(new GridLayout(0, 3));
		line_start.setBackground(new Color(16777215));
		JNavbarTop.setBackground(new Color(16777215));
		JNavbarTop.add(line_start);
		JNavbarTop.add(title);
		JNavbarTop.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
		return JNavbarTop;
	}

	JPanel content() {
		JPanel JContent = new JPanel();
		// search
		JPanel searchPanel = new JPanel();
		JTextField search = createSearchContent();
		search_content = createButtonIcon(".\\src\\resources\\search.png", 50, 50);
		search_content.setFocusable(false);
		searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
		searchPanel.add(search);
		searchPanel.add(search_content);
		search_content.addActionListener(this);
		// Table
		tableGV = createTableContent();
		JScrollPane scrollPane = new JScrollPane(tableGV);
		scrollPane.setMaximumSize(new Dimension(1000, 650));
		scrollPane.setPreferredSize(new Dimension(1000, 600));
		scrollPane.setMinimumSize(new Dimension(1000, 200));
		// J
		JPanel bottom = new JPanel();
		deleteBtn = createButtonIcon(".\\src\\resources\\delete.png", 64, 64);
		repairBtn = createButtonIcon(".\\src\\resources\\settings.png", 64, 64);
		bottom.setLayout(new BoxLayout(bottom, BoxLayout.X_AXIS));
		bottom.add(deleteBtn);
		bottom.add(Box.createRigidArea(new Dimension(500, 100)));
		bottom.add(repairBtn);
		deleteBtn.addActionListener(this);
		repairBtn.addActionListener(this);
		// Layout
		JContent.setLayout(new BoxLayout(JContent, BoxLayout.Y_AXIS));
		JContent.add(Box.createRigidArea(new Dimension(0, 10)));
		JContent.add(searchPanel);
		JContent.add(Box.createRigidArea(new Dimension(0, 10)));
		JContent.add(scrollPane);
		JContent.add(Box.createRigidArea(new Dimension(0, 10)));
		JContent.add(bottom);
		// JContent.add(tableGV);
		return JContent;
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

	Object[][] convertData(List<KQDKHP> list) {
		String lichHoc = null;
		if (_hp.getCa() == 0) {
			lichHoc = "T" + _hp.getThu() + "(7h30-9h30)-" + "P." + _hp.getTenPhong();
		} else if (_hp.getCa() == 1) {
			lichHoc = "T" + _hp.getThu() + "(9h30-11h30)-" + "P." + _hp.getTenPhong();
		} else if (_hp.getCa() == 2) {
			lichHoc = "T" + _hp.getThu() + "(13h30-15h30)-" + "P." + _hp.getTenPhong();
		} else if (_hp.getCa() == 3) {
			lichHoc = "T" + _hp.getThu() + "(15h30-17h30)-" + "P." + _hp.getTenPhong();
		}
		List<Object[]> listGVO = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			KQDKHP kq = list.get(i);
			Object[] temp = { i + 1, kq.getSinhVien().getMaSV(), kq.getSinhVien().getTenSV(), _hp.getMonHoc().getMaMH(),
					_hp.getMonHoc().getTenMH(), _hp.getTenGVLT(), lichHoc, kq.getTgDangKy().toString() };
			listGVO.add(temp);
		}
		Object[][] data = new Object[listGVO.size()][];
		for (int i = 0; i < listGVO.size(); i++) {
			data[i] = listGVO.get(i);
		}
		return data;
	}

	JTable createTableContent() {
		String[] columnNames = { "STT", "MSSV", "Tên", "Mã Môn Học", "Tên Môn học", "GVLT", "Thời Gian Học",
				"Thời Điểm Đăng ký" };

		Object[][] data = convertData(_hp.getKqdkhps());
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
				default:
					return String.class;
				}
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.setRowHeight(30);
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(6).setPreferredWidth(200);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < columnNames.length - 1; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		table.setGridColor(Color.orange);

		return table;
	}

	JTextField createSearchContent() {
		search_textField = new JTextField("");
		search_textField.setMaximumSize(new Dimension(950, 50));
		// Title
		TitledBorder title;
		title = BorderFactory.createTitledBorder("Tìm kiếm");
		search_textField.setBorder(title);
		return search_textField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == backBtn) {
			this.dispose();
			new QuanLyHocPhanFrame(_gv);
		} else if (e.getSource() == search_content) {
			String s = search_textField.getText();
			List<KQDKHP> list;
			if (s.isEmpty())
				list = _hp.getKqdkhps();
			else {
				list = _hp.getKqdkhps();
				for (int i = 0; i < list.size(); i++) {
					if (!list.get(i).getSinhVien().getMaSV().toUpperCase().contains(s.toUpperCase())
							&& !list.get(i).getSinhVien().getTenSV().toUpperCase().contains(s.toUpperCase()))
						list.remove(i);
				}
			}
			Object[][] data = convertData(list);
			// Xoa bang
			clearTable();
			// Add content
			for (int i = 0; i < data.length; i++)
				model.addRow(data[i]);
			JOptionPane.showMessageDialog(this, list.size() + " kết quả được tìm thấy");
		} else if (e.getSource() == deleteBtn) {
			Object[] options = { "Yes, please", "No, thanks" };
			int n = JOptionPane.showOptionDialog(this, "Bạn thực sự muốn xóa học phần này không?", "Cảnh báo",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
			if (n == 0) {
				if (HocPhanDao.xaoHocPhan(_hp.getMaHP())) {
					JOptionPane.showMessageDialog(this, "Xóa học phần thành công");
				} else {
					JOptionPane.showMessageDialog(this, "Xóa học phần không thành công", "Xóa học phần thất bại",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

			}
			this.dispose();
			new QuanLyHocPhanFrame(_gv);
		} else if (e.getSource() == repairBtn) {
			new CapNhatHocPhanFrame(this, _hp);
		}
	}

	public void clearTable() {
		int rowCount = model.getRowCount();
		// Remove rows one by one from the end of the table
		for (int i = rowCount - 1; i >= 0; i--) {
			model.removeRow(i);
		}
	}

}
