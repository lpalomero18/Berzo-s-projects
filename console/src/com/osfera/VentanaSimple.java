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
import javax.swing.JFormattedTextField;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.BoxLayout;
import javax.swing.JToggleButton;

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
		gridBagLayout.columnWidths = new int[]{250, 250, 0, 0};
		gridBagLayout.rowHeights = new int[]{85	, 100, 29, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JTextPane textPane = new JTextPane();
		textPane.setEnabled(false);
		textPane.setEditable(false);
		textPane.setText("...conectando...");
		cliente.getObsMaquina().registraComponente(textPane);
		GridBagConstraints gbc_textPane = new GridBagConstraints();
		gbc_textPane.fill = GridBagConstraints.BOTH;
		gbc_textPane.insets = new Insets(0, 0, 5, 5);
		gbc_textPane.gridx = 0;
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
		gbc_btnApagar.insets = new Insets(0, 0, 5, 5);
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
		gbc_btnScaleUp.insets = new Insets(0, 0, 5, 5);
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
		gbc_btnScaleDown.insets = new Insets(0, 0, 5, 5);
		gbc_btnScaleDown.gridx = 1;
		gbc_btnScaleDown.gridy = 2;
		frame.getContentPane().add(btnScaleDown, gbc_btnScaleDown);
		btnScaleDown.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cliente.getMainInstance().cambiaSmall();
			}
		});
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		GridBagConstraints gbc_formattedTextField = new GridBagConstraints();
		gbc_formattedTextField.insets = new Insets(0, 0, 5, 0);
		gbc_formattedTextField.gridx = 2;
		gbc_formattedTextField.gridy = 2;
		frame.getContentPane().add(formattedTextField, gbc_formattedTextField);
	}

}
