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
		
		JButton btnEncender = new JButton("Encendido");
		btnEncender.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cliente.getMainInstance().enciende();
			}
		});
		frame.getContentPane().add(btnEncender, BorderLayout.WEST);
		
		JButton btnApagar = new JButton("Apagado");
		btnApagar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				cliente.getMainInstance().apaga();
			}
		});
		frame.getContentPane().add(btnApagar, BorderLayout.CENTER);
		
		JTextPane textPane = new JTextPane();
		frame.getContentPane().add(textPane, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		
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
