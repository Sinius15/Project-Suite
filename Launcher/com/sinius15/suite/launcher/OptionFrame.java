package com.sinius15.suite.launcher;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class OptionFrame extends JFrame {

	private static final long serialVersionUID = 8733682719392396550L;
	private JPanel contentPane;
	private JTextField textField;

	public OptionFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				Data.launcherFrame.setEnabled(true);
				Data.optionFrame.setVisible(false);
				Data.launcherFrame.requestFocus();
			}
		});
		setBounds(100, 100, 396, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(239, 228, 123, 23);
		contentPane.add(btnSave);
		
		JButton btnDiscard = new JButton("Cancel");
		btnDiscard.setBounds(117, 228, 112, 23);
		contentPane.add(btnDiscard);
		
		textField = new JTextField();
		textField.setBounds(157, 8, 168, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton button = new JButton("...");
		button.setBounds(336, 7, 26, 23);
		contentPane.add(button);
		
		JCheckBox chckbxDefaultDataFolder = new JCheckBox("Default data folder");
		chckbxDefaultDataFolder.setBounds(10, 7, 141, 23);
		contentPane.add(chckbxDefaultDataFolder);
		
		JCheckBox chckbxLauncherVisibility = new JCheckBox("Launcher Visibility");
		chckbxLauncherVisibility.setBounds(10, 59, 141, 23);
		contentPane.add(chckbxLauncherVisibility);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Latest"}));
		comboBox.setBounds(157, 34, 205, 20);
		contentPane.add(comboBox);
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"Close launcher whe game starts", "Hide launcher and re-open when game closes", "Keep the launcher open"}));
		comboBox_1.setBounds(157, 60, 205, 20);
		contentPane.add(comboBox_1);
		
		JLabel lblVersion = new JLabel("  Version");
		lblVersion.setBounds(10, 37, 112, 18);
		contentPane.add(lblVersion);
	}
}
