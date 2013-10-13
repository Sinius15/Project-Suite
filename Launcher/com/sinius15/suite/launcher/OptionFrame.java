package com.sinius15.suite.launcher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class OptionFrame extends JFrame {

	private static final long serialVersionUID = 8733682719392396550L;
	private JPanel contentPane;
	private JTextField textField;

	public OptionFrame() {
		setResizable(false);
		setTitle("Options");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				Data.launcherFrame.setEnabled(true);
				Data.optionFrame.setVisible(false);
				Data.launcherFrame.requestFocus();
			}
		});
		setBounds(100, 100, 454, 180);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(313, 120, 123, 23);
		contentPane.add(btnSave);
		
		JButton btnDiscard = new JButton("Cancel");
		btnDiscard.setBounds(191, 120, 112, 23);
		contentPane.add(btnDiscard);
		
		textField = new JTextField();
		textField.setBounds(150, 9, 248, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		final JButton button = new JButton("...");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser filechooser = new JFileChooser();
				filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				filechooser.setAcceptAllFileFilterUsed(false);
				filechooser.setDialogType(JFileChooser.SAVE_DIALOG);
				int returnErrorNR = filechooser.showSaveDialog(null);
				if(returnErrorNR != 0)
					return;
				String path = filechooser.getSelectedFile().getAbsolutePath();
				textField.setText(path);
			}
		});
		button.setBounds(410, 8, 26, 23);
		contentPane.add(button);
		
		final JCheckBox chckbxDefaultDataFolder = new JCheckBox("Default data folder");
		chckbxDefaultDataFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					textField.setEditable(!chckbxDefaultDataFolder.isSelected());
					button.setEnabled(!chckbxDefaultDataFolder.isSelected());
			}
		});
		chckbxDefaultDataFolder.setBounds(10, 7, 132, 23);
		contentPane.add(chckbxDefaultDataFolder);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Latest"}));
		comboBox.setBounds(150, 35, 286, 20);
		contentPane.add(comboBox);
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"Close launcher whe game starts", "Hide launcher and re-open when game closes", "Keep the launcher open"}));
		comboBox_1.setBounds(150, 61, 286, 20);
		contentPane.add(comboBox_1);
		
		JLabel lblVersion = new JLabel("  Version");
		lblVersion.setBounds(10, 37, 112, 18);
		contentPane.add(lblVersion);
		
		JLabel lblLauncherVisability = new JLabel("  Launcher Visability");
		lblLauncherVisability.setBounds(10, 62, 128, 18);
		contentPane.add(lblLauncherVisability);
		
		JCheckBox chckbxAutoUpdate = new JCheckBox("Auto Update");
		chckbxAutoUpdate.setBounds(10, 89, 112, 24);
		contentPane.add(chckbxAutoUpdate);
	}
}
