
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

public class MainScreenGVFrame extends JFrame {
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

	MainScreenGVFrame() {
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println(dim.getSize());

		// Set layout and component on Frame
		Container con = this.getContentPane();
		con.setLayout(new BorderLayout());
		JPanel JNavbarTop = navbarTop("Nguyen Van A");
		JPanel JNavbarSide = navbarSide();
		JPanel JContent = content();

		JNavbarSide.setSize(new Dimension(300, 100));

		con.add(JNavbarTop, BorderLayout.PAGE_START);
		con.add(JNavbarSide, BorderLayout.LINE_START);
		con.add(JContent, BorderLayout.CENTER);
		// Set Frame attribute
		this.setTitle("Main Screen GV");
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

		JLabel title = new JLabel("Hệ Thống ĐKHP - GV");
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

	JPanel navbarSide() {
		JPanel JNavbarSide = new JPanel();
		JPanel temp2 = new JPanel();
		JPanel temp = new JPanel();
		JButton JAcountSide = CreateAccountSide("  Nguyễn Văn A  ");
		JButton giaovu = CreateManagerSide(".\\src\\resources\\manager.png", "Giáo Vụ");
		JButton monhoc = CreateManagerSide(".\\src\\resources\\monhoc.png", "Môn Học");
		JButton hocky = CreateManagerSide(".\\src\\resources\\hocky.png", "Học Kỳ");
		JButton lophoc = CreateManagerSide(".\\src\\resources\\class.png", "Lớp Học");
		JButton sinhvien = CreateManagerSide(".\\src\\resources\\sv.png", "Sinh Viên");
		JButton dotDKHP = CreateManagerSide(".\\src\\resources\\dotdkhp.png", "Đợt DKHP");
		JButton hocphan = CreateManagerSide(".\\src\\resources\\hp.png", "Học Phần");
		// Set layout
		JNavbarSide.setLayout(new BorderLayout());
		temp.setLayout(new GridLayout(12, 1));
		temp.setBackground(new Color(000000));
		// temp2.setBackground(new Color(000000));
		// temp2.add(JAcountSide);
		temp.add(giaovu);
		temp.add(monhoc);
		temp.add(hocky);
		temp.add(lophoc);
		temp.add(sinhvien);
		temp.add(dotDKHP);
		temp.add(hocphan);
		JNavbarSide.add(JAcountSide, BorderLayout.PAGE_START);
		JNavbarSide.add(temp, BorderLayout.CENTER);

		return JNavbarSide;
	}

	JPanel content() {
		JPanel JContent = new JPanel();
		JButton giaovu = CreateManagerContent(".\\src\\resources\\manager.png", "   Giáo Vụ   ");
		JButton monhoc = CreateManagerContent(".\\src\\resources\\monhoc.png", "  Môn Học  ");
		JButton hocky = CreateManagerContent(".\\src\\resources\\hocky.png", "  Học Kỳ  ");
		JButton lophoc = CreateManagerContent(".\\src\\resources\\class.png", "		Lớp Học		");
		JButton sinhvien = CreateManagerContent(".\\src\\resources\\sv.png", "		Sinh Viên		");
		JButton dotDKHP = CreateManagerContent(".\\src\\resources\\dotdkhp.png", "		Đợt DKHP		");
		JButton hocphan = CreateManagerContent(".\\src\\resources\\hp.png", "		Học Phần		");
		// Set lay out
		JPanel temp = new JPanel();
		temp.setLayout(new GridLayout(2, 4, 100, 100));
		temp.setBackground(new Color(15069416));
		JContent.setBackground(new Color(15069416));
		temp.add(giaovu);
		temp.add(monhoc);
		temp.add(hocky);
		temp.add(lophoc);
		temp.add(sinhvien);
		temp.add(dotDKHP);
		temp.add(hocphan);
		temp.setAlignmentX(CENTER_ALIGNMENT);
		JContent.add(temp);
		return JContent;
	}

	JPanel account(String name) {
		JPanel JAccount = new JPanel();
		JButton image = createButtonIcon(".\\src\\resources\\account.png", 64, 64);
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
		JButton logout = createButtonIcon(".\\src\\resources\\logout.png", 32, 32);

		// Set layout
		JAccount.setLayout(new BoxLayout(JAccount, BoxLayout.X_AXIS));
		JAccount.setBackground(new Color(16777215));
		temp.setBackground(new Color(16777215));
		image.setBackground(new Color(16777215));
		JAccount.add(Box.createRigidArea(new Dimension(20, 12)));
		JAccount.add(image);
		JAccount.add(Box.createRigidArea(new Dimension(20, 12)));
		JAccount.add(temp);
		JAccount.add(Box.createRigidArea(new Dimension(20, 12)));
		JAccount.add(logout);
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
		return JAccountSide;
	}
}