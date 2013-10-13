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
	private JTextField pathField;
	private JCheckBox AutoUpdate, defaultDataFolder;
	private JComboBox<String> LauncherVisability;
	private JComboBox<String> Version;
	private JButton browse;
	private JCheckBox UserCrd;

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
		setBounds(100, 100, 498, 217);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateValuesToData();
				try {
					Data.saveData();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				Data.launcherFrame.setEnabled(true);
				Data.optionFrame.setVisible(false);
				Data.launcherFrame.requestFocus();
			}
		});
		btnSave.setBounds(354, 154, 123, 23);
		contentPane.add(btnSave);
		
		JButton btnDiscard = new JButton("Cancel");
		btnDiscard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Data.launcherFrame.setEnabled(true);
				Data.optionFrame.setVisible(false);
				Data.launcherFrame.requestFocus();
			}
		});
		btnDiscard.setBounds(230, 154, 112, 23);
		contentPane.add(btnDiscard);
		
		pathField = new JTextField();
		pathField.setBounds(191, 69, 248, 20);
		contentPane.add(pathField);
		pathField.setColumns(10);
		
		browse = new JButton("...");
		browse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser filechooser = new JFileChooser();
				filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				filechooser.setAcceptAllFileFilterUsed(false);
				filechooser.setDialogType(JFileChooser.SAVE_DIALOG);
				int returnErrorNR = filechooser.showSaveDialog(null);
				if(returnErrorNR != 0)
					return;
				String path = filechooser.getSelectedFile().getAbsolutePath();
				pathField.setText(path);
			}
		});
		browse.setBounds(451, 68, 26, 23);
		contentPane.add(browse);
		
		defaultDataFolder = new JCheckBox("Default data folder");
		defaultDataFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					pathField.setEditable(!defaultDataFolder.isSelected());
					browse.setEnabled(!defaultDataFolder.isSelected());
			}
		});
		defaultDataFolder.setBounds(10, 68, 132, 23);
		contentPane.add(defaultDataFolder);
		
		Version = new JComboBox<String>();
		Version.setModel(new DefaultComboBoxModel<String>(new String[] {"Latest"}));
		Version.setBounds(191, 12, 286, 20);
		contentPane.add(Version);
		
		LauncherVisability = new JComboBox<String>();
		LauncherVisability.setModel(new DefaultComboBoxModel<String>(new String[] {"Close launcher when game starts", "Hide launcher and re-open when game closes", "Keep the launcher open"}));
		LauncherVisability.setBounds(191, 40, 286, 20);
		contentPane.add(LauncherVisability);
		
		JLabel lblVersion = new JLabel("  Version");
		lblVersion.setBounds(10, 13, 112, 18);
		contentPane.add(lblVersion);
		
		JLabel lblLauncherVisability = new JLabel("  Launcher Visability");
		lblLauncherVisability.setBounds(10, 41, 128, 18);
		contentPane.add(lblLauncherVisability);
		
		AutoUpdate = new JCheckBox("Auto Update");
		AutoUpdate.setBounds(10, 123, 112, 24);
		contentPane.add(AutoUpdate);
		
		UserCrd = new JCheckBox("Save User Credentials");
		UserCrd.setBounds(10, 95, 163, 24);
		contentPane.add(UserCrd);
		
		
		
	}
	
	public void updateValuesFromData(){
		AutoUpdate.setSelected(Data.autoUpdate);
		
		defaultDataFolder.setSelected(Data.defaultDataFolder);
		pathField.setEditable(!defaultDataFolder.isSelected());
		browse.setEnabled(!defaultDataFolder.isSelected());
		if(!Data.defaultDataFolder)
			if(Data.dataFolder.equals("default")) pathField.setText("");
			else pathField.setText(Data.dataFolder);
		
		LauncherVisability.setSelectedIndex(Data.launcherVis);
		UserCrd.setSelected(Data.saveUser);
		
		this.revalidate();
	}
	
	public void updateValuesToData(){
		Data.autoUpdate = AutoUpdate.isSelected();
		
		Data.defaultDataFolder = defaultDataFolder.isSelected();
		Data.dataFolder = pathField.getText();
		
		Data.launcherVis = LauncherVisability.getSelectedIndex();
		
		Data.saveUser = UserCrd.isSelected();
	}
}
