package com.sinius15.suite.launcher.gui;

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

import com.sinius15.suite.launcher.Data;
import com.sinius15.suite.launcher.OptionManager;

public class OptionFrame extends JFrame {

	private static final long serialVersionUID = 8733682719392396550L;
	public JPanel contentPane;
	public JTextField pathField;
	public JCheckBox AutoUpdate;
	public JCheckBox defaultDataFolder;
	public JComboBox<String> LauncherVisability;
	public JComboBox<String> Version;
	public JButton browse;
	public JCheckBox UserCrd;
	public JTextField heidhtField;
	public JTextField widthField;

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
		setBounds(100, 100, 498, 242);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OptionManager.updateValuesToManager();
				try {
					OptionManager.saveOptions(Data.CONFIG_FILE);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				Data.launcherFrame.setEnabled(true);
				Data.optionFrame.setVisible(false);
				Data.launcherFrame.requestFocus();
			}
		});
		btnSave.setBounds(354, 179, 123, 23);
		contentPane.add(btnSave);
		
		JButton btnDiscard = new JButton("Cancel");
		btnDiscard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Data.launcherFrame.setEnabled(true);
				Data.optionFrame.setVisible(false);
				Data.launcherFrame.requestFocus();
			}
		});
		btnDiscard.setBounds(233, 179, 112, 23);
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
		Version.setModel(new DefaultComboBoxModel<String>(new String[] {"-"}));
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
		AutoUpdate.setBounds(10, 155, 112, 24);
		contentPane.add(AutoUpdate);
		
		UserCrd = new JCheckBox("Save User Credentials");
		UserCrd.setBounds(10, 127, 163, 24);
		contentPane.add(UserCrd);
		
		heidhtField = new JTextField();
		heidhtField.setText("360");
		heidhtField.setBounds(363, 101, 114, 20);
		contentPane.add(heidhtField);
		heidhtField.setColumns(10);
		
		widthField = new JTextField();
		widthField.setText("720");
		widthField.setColumns(10);
		widthField.setBounds(191, 101, 114, 20);
		contentPane.add(widthField);
		
		JLabel lblScreenSize = new JLabel("  Screen size");
		lblScreenSize.setBounds(10, 103, 77, 16);
		contentPane.add(lblScreenSize);
		
		JLabel lblWidht = new JLabel("Widht");
		lblWidht.setBounds(152, 101, 77, 16);
		contentPane.add(lblWidht);
		
		JLabel lblHeight = new JLabel("Height");
		lblHeight.setBounds(320, 103, 77, 16);
		contentPane.add(lblHeight);
		
		
		
	}
	
}