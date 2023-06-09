package dbHisseCalculatorGUI;

import java.awt.BorderLayout;
import java.sql.*;
import java.util.Scanner;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class dbHisseCalculator extends JFrame {

	private JPanel contentPane;
	private JTextField fld_buyToplamHisse;
	private JTextField fld_toplamPara;
	private JTextField fld_sellToplamHisse;
	private JTextField fld_sonuc;
	private JTextField fld_hisseBirimFiyati;

	/**
	 * Launch the application.
	 * 
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {
		Connection c = DriverManager.getConnection("jdbc:mariadb://localhost:3308/borsa?user=root&password=123456");
		Statement st = c.createStatement();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dbHisseCalculator frame = new dbHisseCalculator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public dbHisseCalculator() {
		setTitle("Hisse Hesaplama");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 514, 352);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel hisse_panel = new JPanel();
		hisse_panel.setBounds(10, 11, 478, 291);
		contentPane.add(hisse_panel);
		hisse_panel.setLayout(null);

		JLabel lbl_buyToplamHisse = new JLabel("Al\u0131nan Toplam Hisse Say\u0131s\u0131");
		lbl_buyToplamHisse.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 11));
		lbl_buyToplamHisse.setBounds(10, 11, 187, 23);
		hisse_panel.add(lbl_buyToplamHisse);

		fld_buyToplamHisse = new JTextField();
		fld_buyToplamHisse.setEditable(false);
		fld_buyToplamHisse.setBounds(10, 34, 187, 23);
		hisse_panel.add(fld_buyToplamHisse);
		fld_buyToplamHisse.setColumns(10);

		JLabel lbl_toplamOdenenPara = new JLabel("Hisselere \u00D6denen Toplam Para");
		lbl_toplamOdenenPara.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 11));
		lbl_toplamOdenenPara.setBounds(241, 11, 187, 23);
		hisse_panel.add(lbl_toplamOdenenPara);

		fld_toplamPara = new JTextField();
		fld_toplamPara.setEditable(false);
		fld_toplamPara.setColumns(10);
		fld_toplamPara.setBounds(241, 34, 187, 23);
		hisse_panel.add(fld_toplamPara);

		JButton btn_buyToplamHisse = new JButton("Al\u0131nan Total Hisse");
		btn_buyToplamHisse.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 11));
		btn_buyToplamHisse.setBounds(10, 68, 187, 23);
		hisse_panel.add(btn_buyToplamHisse);

		btn_buyToplamHisse.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					alinanToplamHisse();
				} catch (SQLException e1) {

					e1.printStackTrace();
				}

			}
		});

		JButton btn_toplamPara = new JButton("Total \u00D6denen Para");
		btn_toplamPara.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 11));
		btn_toplamPara.setBounds(241, 68, 187, 23);
		hisse_panel.add(btn_toplamPara);

		btn_toplamPara.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					odenenToplamPara();
				} catch (SQLException e1) {

					e1.printStackTrace();
				}

			}
		});

		JLabel lbl_sellToplamHisse = new JLabel("Sat\u0131lacak Toplam Hisse Say\u0131s\u0131");
		lbl_sellToplamHisse.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 11));
		lbl_sellToplamHisse.setBounds(10, 102, 187, 23);
		hisse_panel.add(lbl_sellToplamHisse);

		fld_sellToplamHisse = new JTextField();
		fld_sellToplamHisse.setColumns(10);
		fld_sellToplamHisse.setBounds(10, 125, 187, 23);
		hisse_panel.add(fld_sellToplamHisse);

		JButton btn_sellToplamHisse = new JButton("Sat\u0131lacak Hisse");
		btn_sellToplamHisse.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 11));
		btn_sellToplamHisse.setBounds(10, 159, 187, 23);
		hisse_panel.add(btn_sellToplamHisse);

		btn_sellToplamHisse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					satilacakToplamHisse();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});

		fld_sonuc = new JTextField();
		fld_sonuc.setColumns(10);
		fld_sonuc.setBounds(10, 223, 433, 23);
		hisse_panel.add(fld_sonuc);

		JButton btn_hesapla = new JButton("Hesapla");
		btn_hesapla.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 11));
		btn_hesapla.setBounds(10, 257, 433, 23);
		hisse_panel.add(btn_hesapla);

		btn_hesapla.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				sonuc();

			}
		});

		JLabel lbl_sonuc = new JLabel("Sonu\u00E7");
		lbl_sonuc.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_sonuc.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 11));
		lbl_sonuc.setBounds(10, 193, 433, 23);
		hisse_panel.add(lbl_sonuc);

		JLabel lbl_sellBirimHisseFiyati = new JLabel("Sat\u0131lacak Hisse Birim Fiyat\u0131");
		lbl_sellBirimHisseFiyati.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 11));
		lbl_sellBirimHisseFiyati.setBounds(241, 102, 187, 23);
		hisse_panel.add(lbl_sellBirimHisseFiyati);

		fld_hisseBirimFiyati = new JTextField();
		fld_hisseBirimFiyati.setColumns(10);
		fld_hisseBirimFiyati.setBounds(241, 125, 187, 23);
		hisse_panel.add(fld_hisseBirimFiyati);

		JButton btn_sat = new JButton("Sat\u0131\u015F\u0131 Onayla");
		btn_sat.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 11));
		btn_sat.setBounds(241, 159, 187, 23);
		hisse_panel.add(btn_sat);

		btn_sat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(contentPane, "Satýþ Onaylandý !", "Onaylandý",
						JOptionPane.WARNING_MESSAGE);

			}
		});

	}

	public void odenenToplamPara() throws SQLException {
		Connection c = DriverManager.getConnection("jdbc:mariadb://localhost:3308/borsa?user=root&password=123456");
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM hisseler "); // hisseler tablosundan veri çekme
		// while (rs.next()) {
		/*
		 * System.out.println(rs.getInt("hisse_id") + " -  " +
		 * rs.getString("hisse_name") + " - Birim Hisse Alýþ Fiyatý : " +
		 * rs.getDouble("hisse_alis") + " TL - Alýnan Hisse Miktarý : " +
		 * rs.getInt("hisse_miktar"));
		 */
		// }

		/* Step 1: Creating the statement */
		rs = st.executeQuery("SELECT * FROM " + "hisseler");
		/* Step 2: Execute the statements */
		// Moving the cursor to the last row
		// rs.last();

		double hisselereOdenenToplamPara = 0;
		while (rs.next()) {

			// Hisslere ödenen toplam para
			hisselereOdenenToplamPara = hisselereOdenenToplamPara
					+ (rs.getDouble("hisse_alis") * rs.getInt("hisse_miktar"));

		}
		fld_toplamPara.setText(String.valueOf(hisselereOdenenToplamPara));
		rs.last();
	}

	public void alinanToplamHisse() throws SQLException {
		Connection c = DriverManager.getConnection("jdbc:mariadb://localhost:3308/borsa?user=root&password=123456");
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM hisseler "); // hisseler tablosundan veri çekme
		// while (rs.next()) {
		/*
		 * System.out.println(rs.getInt("hisse_id") + " -  " +
		 * rs.getString("hisse_name") + " - Alýnan Hisse Miktarý : " +
		 * rs.getInt("hisse_miktar"));
		 */
		// }

		rs = st.executeQuery("SELECT * FROM " + "hisseler");

		int toplamHisseSayisi = 0;
		while (rs.next()) {
			// Toplan alinan hisse sayýsý :
			toplamHisseSayisi = toplamHisseSayisi + rs.getInt("hisse_miktar");
		}

		fld_buyToplamHisse.setText("Alýnan Toplam Hisse sayýsý : " + toplamHisseSayisi);

		rs.last();

	}

	public void satilacakToplamHisse() throws SQLException {
		Connection c = DriverManager.getConnection("jdbc:mariadb://localhost:3308/borsa?user=root&password=123456");
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM hisseler "); // hisseler tablosundan veri çekme

		rs = st.executeQuery("SELECT * FROM " + "hisseler");

		int toplamHisseSayisi = 0;
		while (rs.next()) {
			// Toplam alinan hisse sayýsý :
			toplamHisseSayisi = toplamHisseSayisi + rs.getInt("hisse_miktar");
		}

		if (Integer.parseInt(fld_sellToplamHisse.getText()) > toplamHisseSayisi) { // String deðeri int e çevirir.
			fld_sellToplamHisse.setText(String.valueOf(toplamHisseSayisi)); //int deðeri Stringe çevirdi

		}

		else if (Integer.parseInt(fld_sellToplamHisse.getText()) == toplamHisseSayisi) {
			fld_sellToplamHisse.setText(String.valueOf(toplamHisseSayisi));

		}

		else if (Integer.parseInt(fld_sellToplamHisse.getText()) < toplamHisseSayisi
				&& Integer.parseInt(fld_sellToplamHisse.getText()) >= 0) {
			fld_sellToplamHisse.setText(fld_sellToplamHisse.getText());

		}

		else if (Integer.parseInt(fld_sellToplamHisse.getText()) < 0) {
			fld_sellToplamHisse.setText("Error");

		}

		rs.last();

	}

	public void sonuc() {

		double toplamSatisFiyati = (Integer.parseInt(fld_sellToplamHisse.getText()))
				* (Double.parseDouble(fld_hisseBirimFiyati.getText()));

		

		if (toplamSatisFiyati > Double.parseDouble(fld_toplamPara.getText())) {
			fld_sonuc.setText("Toplam kar : " + (toplamSatisFiyati - Double.parseDouble(fld_toplamPara.getText()))
					+ " TL --- " + " Toplam kar % : " + (toplamSatisFiyati - Double.parseDouble(fld_toplamPara.getText()))
							/ Double.parseDouble(fld_toplamPara.getText()) * 100);

		}

		else if (toplamSatisFiyati == Double.parseDouble(fld_toplamPara.getText())) {
			fld_sonuc.setText("Kar ve Zarar Yok !");
		}

		else {
			fld_sonuc.setText("Toplam zarar : " + (Double.parseDouble(fld_toplamPara.getText()) - toplamSatisFiyati)
					+ " TL --- " + " Toplam zarar % : "
					+ (((Double.parseDouble(fld_toplamPara.getText()) - toplamSatisFiyati)
							/ Double.parseDouble(fld_toplamPara.getText())) * 100));
		}
	}
}
