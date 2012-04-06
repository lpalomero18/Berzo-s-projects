package com.osfera;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;

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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{165, 341, 0};
		gridBagLayout.rowHeights = new int[]{21, 101, 35, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JButton btnEncender = new JButton("Encendido");
		btnEncender.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cliente.getMainInstance().enciende();
			}
		});
		
		JTextPane textPane = new JTextPane();
		GridBagConstraints gbc_textPane = new GridBagConstraints();
		gbc_textPane.anchor = GridBagConstraints.NORTH;
		gbc_textPane.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPane.insets = new Insets(0, 0, 5, 0);
		gbc_textPane.gridwidth = 2;
		gbc_textPane.gridx = 0;
		gbc_textPane.gridy = 0;
		frame.getContentPane().add(textPane, gbc_textPane);
		GridBagConstraints gbc_btnEncender = new GridBagConstraints();
		gbc_btnEncender.fill = GridBagConstraints.BOTH;
		gbc_btnEncender.insets = new Insets(0, 0, 5, 5);
		gbc_btnEncender.gridx = 0;
		gbc_btnEncender.gridy = 1;
		frame.getContentPane().add(btnEncender, gbc_btnEncender);
		
		JButton btnApagar = new JButton("Apagado");
		btnApagar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				cliente.getMainInstance().apaga();
			}
		});
		GridBagConstraints gbc_btnApagar = new GridBagConstraints();
		gbc_btnApagar.fill = GridBagConstraints.BOTH;
		gbc_btnApagar.insets = new Insets(0, 0, 5, 0);
		gbc_btnApagar.gridx = 1;
		gbc_btnApagar.gridy = 1;
		frame.getContentPane().add(btnApagar, gbc_btnApagar);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gbc_panel.gridwidth = 2;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		frame.getContentPane().add(panel, gbc_panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnScaleUp = new JButton("Aumenta");
		panel.add(btnScaleUp);
		
		JButton btnScaleDown = new JButton("Reduce");
		btnScaleDown.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cliente.getMainInstance().cambiaSmall();
			}
		});
		panel.add(btnScaleDown);
		btnScaleUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cliente.getMainInstance().cambiaLarge();
			}
		});
	}

}
