package bloetin1_Interfaces_Graficas_1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SpringLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Vista extends JFrame {

	private static final long serialVersionUID = 1L;
	private final JButton button = new JButton("/");
	private final JButton btnNewButton_3 = new JButton("-");
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vista frame = new Vista();
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
	public Vista() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_3.setBounds(105, 96, 85, 21);
		getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("    +");
		btnNewButton_4.setBounds(10, 96, 85, 21);
		getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_2 = new JButton("*");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setBounds(190, 96, 85, 21);
		getContentPane().add(btnNewButton_2);
		button.setBounds(275, 96, 85, 21);
		getContentPane().add(button);
		
		textField = new JTextField();
		textField.setBounds(20, 46, 324, 40);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("7");
		btnNewButton.setBounds(10, 129, 85, 21);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("8");
		btnNewButton_1.setBounds(105, 129, 85, 21);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_5 = new JButton("9");
		btnNewButton_5.setBounds(200, 129, 85, 21);
		getContentPane().add(btnNewButton_5);
		
		JButton btnNewButton_5_1 = new JButton("4");
		btnNewButton_5_1.setBounds(10, 160, 85, 21);
		getContentPane().add(btnNewButton_5_1);
		
		JButton btnNewButton_5_2 = new JButton("5");
		btnNewButton_5_2.setBounds(105, 160, 85, 21);
		getContentPane().add(btnNewButton_5_2);
		
		JButton btnNewButton_5_3 = new JButton("6");
		btnNewButton_5_3.setBounds(200, 160, 85, 21);
		getContentPane().add(btnNewButton_5_3);
		
		JButton btnNewButton_5_4 = new JButton("3");
		btnNewButton_5_4.setBounds(200, 191, 85, 21);
		getContentPane().add(btnNewButton_5_4);
		
		JButton btnNewButton_5_5 = new JButton("2");
		btnNewButton_5_5.setBounds(105, 191, 85, 21);
		getContentPane().add(btnNewButton_5_5);
		
		JButton btnNewButton_5_6 = new JButton("1");
		btnNewButton_5_6.setBounds(10, 191, 85, 21);
		getContentPane().add(btnNewButton_5_6);
		
		JButton btnNewButton_5_7 = new JButton("Intro");
		btnNewButton_5_7.setBounds(295, 122, 65, 113);
		getContentPane().add(btnNewButton_5_7);
		
		JButton btnNewButton_6 = new JButton("0");
		btnNewButton_6.setBounds(10, 222, 275, 21);
		getContentPane().add(btnNewButton_6);
	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public JButton getButton() {
		return button;
	}

	public JButton getBtnNewButton_3() {
		return btnNewButton_3;
	}
	
	
}
