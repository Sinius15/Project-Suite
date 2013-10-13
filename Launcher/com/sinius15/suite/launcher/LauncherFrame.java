package com.sinius15.suite.launcher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LauncherFrame extends JFrame {

	private static final long serialVersionUID = -9026214061969231008L;
	private JPanel contentPane;
	JTextField txtUsername;
	JPasswordField passwordField;
	private boolean passwordFieldHadFocus = false;
	private boolean textFieldHadFocus = false;
	private JButton btnOptions;


	public LauncherFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 696, 461);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Data.launcherFrame.txtUsername.getText().equals("") || new String(Data.launcherFrame.passwordField.getPassword()).equals(""))
					return;
				if(Data.saveUser){
					Data.username = Data.launcherFrame.txtUsername.getText();
					Data.password = new String(Data.launcherFrame.passwordField.getPassword());
				}
				try {
					Data.saveData();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnPlay.setBounds(581, 361, 89, 51);
		contentPane.add(btnPlay);
		
		txtUsername = new JTextField();
		txtUsername.setText("Username");
		txtUsername.setBounds(425, 361, 146, 20);
		txtUsername.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(textFieldHadFocus)
					return;
				txtUsername.setText("");
				textFieldHadFocus = true;
			}
		});
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		passwordField = new JPasswordField("Password");
		passwordField.setEchoChar((char)0);
		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(passwordFieldHadFocus)
					return;
				passwordField.setEchoChar((char)9679);
				passwordField.setText("");
				passwordFieldHadFocus = true;
			}
		});
		passwordField.setBounds(425, 392, 146, 20);
		contentPane.add(passwordField);
		
		btnOptions = new JButton("Options");
		btnOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Data.optionFrame.setVisible(true);
				Data.optionFrame.updateValuesFromData();
				Data.launcherFrame.setEnabled(false);
			}
		});
		btnOptions.setBounds(10, 361, 109, 51);
		contentPane.add(btnOptions);
	}
}
