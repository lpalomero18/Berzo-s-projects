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

public class VentanaSimple {

	static ClienteAWS cliente = new ClienteAWS();
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaSimple window = new VentanaSimple();
					window.frame.setVisible(true);
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
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 250, 250 };
		gridBagLayout.rowHeights = new int[] { 80, 100, 29, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0 };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, 0.0,
				Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		frame.getContentPane().add(panel, gbc_panel);
		
				JLabel Logo = new JLabel("");
				panel.add(Logo);
				Logo.setBackground(Color.WHITE);
				Logo.setForeground(Color.WHITE);
				Logo.setIcon(new ImageIcon(getClass().getResource("viewCompanyLogo.png")));

		JTextPane textPane = new JTextPane();
		textPane.setBackground(new Color(238,238,238));
		textPane.setEnabled(false);
		textPane.setEditable(false);
		textPane.setText("...conectando...");
		cliente.getObsMaquina().registraComponente(textPane);
		GridBagConstraints gbc_textPane = new GridBagConstraints();
		gbc_textPane.fill = GridBagConstraints.BOTH;
		gbc_textPane.insets = new Insets(0, 0, 5, 0);
		gbc_textPane.gridx = 1;
		gbc_textPane.gridy = 0;
		frame.getContentPane().add(textPane, gbc_textPane);
		JButton btnEncender = new JButton("Encendido");
		GridBagConstraints gbc_btnEncender = new GridBagConstraints();
		gbc_btnEncender.fill = GridBagConstraints.BOTH;
		gbc_btnEncender.insets = new Insets(0, 0, 5, 5);
		gbc_btnEncender.gridx = 0;
		gbc_btnEncender.gridy = 1;
		frame.getContentPane().add(btnEncender, gbc_btnEncender);
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
		frame.getContentPane().add(btnApagar, gbc_btnApagar);
		btnApagar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				cliente.getMainInstance().apaga();
			}
		});

		JButton btnScaleUp = new JButton("Aumenta");
		GridBagConstraints gbc_btnScaleUp = new GridBagConstraints();
		gbc_btnScaleUp.anchor = GridBagConstraints.EAST;
		gbc_btnScaleUp.insets = new Insets(0, 0, 0, 5);
		gbc_btnScaleUp.gridx = 0;
		gbc_btnScaleUp.gridy = 2;
		frame.getContentPane().add(btnScaleUp, gbc_btnScaleUp);
		btnScaleUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cliente.getMainInstance().cambiaLarge();
			}
		});

		JButton btnScaleDown = new JButton("Reduce");
		GridBagConstraints gbc_btnScaleDown = new GridBagConstraints();
		gbc_btnScaleDown.anchor = GridBagConstraints.WEST;
		gbc_btnScaleDown.gridx = 1;
		gbc_btnScaleDown.gridy = 2;
		frame.getContentPane().add(btnScaleDown, gbc_btnScaleDown);
		btnScaleDown.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cliente.getMainInstance().cambiaSmall();
			}
		});
	}

}
