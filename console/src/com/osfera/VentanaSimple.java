package com.osfera;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import java.awt.Toolkit;

public class VentanaSimple {

	static ClienteAWS cliente = new ClienteAWS();
	private JFrame frmOsferaLauncher;
	private final JPanel panel_1 = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaSimple window = new VentanaSimple();
					window.frmOsferaLauncher.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaSimple() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmOsferaLauncher = new JFrame();
		frmOsferaLauncher.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaSimple.class.getResource("/com/osfera/mini.png")));
		frmOsferaLauncher.setTitle("Osfera Launcher");
		frmOsferaLauncher.setBounds(100, 100, 600, 300);
		frmOsferaLauncher.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 300, 300 };
		gridBagLayout.rowHeights = new int[] { 82, 129, 89 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0 };
		frmOsferaLauncher.getContentPane().setLayout(gridBagLayout);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		frmOsferaLauncher.getContentPane().add(panel, gbc_panel);

		JLabel Logo = new JLabel("");
		panel.add(Logo);
		Logo.setBackground(Color.WHITE);
		Logo.setForeground(Color.WHITE);
		Logo.setIcon(new ImageIcon(getClass()
				.getResource("viewCompanyLogo.png")));

		JTextPane textPane = new JTextPane();
		textPane.setBackground(new Color(238, 238, 238));
		textPane.setEnabled(false);
		textPane.setEditable(false);
		textPane.setText("...conectando...");
		cliente.getObsMaquina().registraComponente(textPane);
		GridBagConstraints gbc_textPane = new GridBagConstraints();
		gbc_textPane.fill = GridBagConstraints.BOTH;
		gbc_textPane.insets = new Insets(0, 0, 5, 0);
		gbc_textPane.gridx = 1;
		gbc_textPane.gridy = 0;
		frmOsferaLauncher.getContentPane().add(textPane, gbc_textPane);
		JButton btnEncender = new JButton("Encendido");
		GridBagConstraints gbc_btnEncender = new GridBagConstraints();
		gbc_btnEncender.fill = GridBagConstraints.BOTH;
		gbc_btnEncender.insets = new Insets(0, 0, 5, 5);
		gbc_btnEncender.gridx = 0;
		gbc_btnEncender.gridy = 1;
		frmOsferaLauncher.getContentPane().add(btnEncender, gbc_btnEncender);
		btnEncender.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cliente.getMainInstance().enciende();
			}
		});

		JButton btnApagar = new JButton("Apagado");
		GridBagConstraints gbc_btnApagar = new GridBagConstraints();
		gbc_btnApagar.fill = GridBagConstraints.BOTH;
		gbc_btnApagar.insets = new Insets(0, 0, 5, 0);
		gbc_btnApagar.gridx = 1;
		gbc_btnApagar.gridy = 1;
		frmOsferaLauncher.getContentPane().add(btnApagar, gbc_btnApagar);
		btnApagar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				cliente.getMainInstance().apaga();
			}
		});
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 2;
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		frmOsferaLauncher.getContentPane().add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 231, 0 };
		gbl_panel_1.rowHeights = new int[] { 14, 33, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JLabel lblTamaoDelOrdenador = new JLabel("Tama\u00F1o del ordenador");
		GridBagConstraints gbc_lblTamaoDelOrdenador = new GridBagConstraints();
		gbc_lblTamaoDelOrdenador.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblTamaoDelOrdenador.insets = new Insets(0, 0, 5, 0);
		gbc_lblTamaoDelOrdenador.gridx = 0;
		gbc_lblTamaoDelOrdenador.gridy = 0;
		panel_1.add(lblTamaoDelOrdenador, gbc_lblTamaoDelOrdenador);
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_2.anchor = GridBagConstraints.NORTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		panel_1.add(panel_2, gbc_panel_2);

		JButton btnScaleMicro = new JButton("Micro");
		panel_2.add(btnScaleMicro);

		JButton btnScaleSmall = new JButton("Peque\u00F1o");
		panel_2.add(btnScaleSmall);

		JButton btnScaleMedium = new JButton("Mediano");
		panel_2.add(btnScaleMedium);
		btnScaleMedium.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cliente.getMainInstance().cambiaLarge();
			}
		});
		btnScaleSmall.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cliente.getMainInstance().cambiaSmall();
			}
		});
		btnScaleMicro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cliente.getMainInstance().cambiaMicro();
			}
		});
	}

}
