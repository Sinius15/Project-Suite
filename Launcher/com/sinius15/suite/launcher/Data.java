package com.sinius15.suite.launcher;

import java.io.File;

@SuppressWarnings("unchecked")
public class Data {
	
	public static final File DEFAULT_DATA_FOLDER = new File(System.getenv("APPDATA") + "\\Suite");

	public static final int LAUNCHVIS_CLOSE = 0;
	public static final int LAUNCHVIS_REOPEN = 1;
	public static final int LAUNCHVIS_OPEN = 2;
	
	public static OptionFrame optionFrame = new OptionFrame();
	public static LauncherFrame launcherFrame = new LauncherFrame();
	
	@SuppressWarnings("rawtypes")
	private static Option p1, p2, p3, p4, p5, p6, p7, p8, p9, p10;
	
	public static void initOptions(){
		
		p1 = new Option<Boolean>(Boolean.class, true, "defaultDataFolder", new Option.Update<Boolean>() {@Override public void run() {
			p1.value = optionFrame.defaultDataFolder.isSelected();
		}}, new Option.Update<Boolean>() {@Override public void run() {
			optionFrame.defaultDataFolder.setSelected((boolean) p1.value);
		}});
		
		OptionManager.addOption(p1);
		
		p2 = new Option<String>(String.class, "default", "dataFolder", new Option.Update<String>() {@Override public void run() {
			p2.value = optionFrame.pathField.getText();
		}}, new Option.Update<String>() {@Override public void run() {
			optionFrame.pathField.setText((String) p2.value);
		}});
		OptionManager.addOption(p2);
		
		p3 = new Option<Boolean>(Boolean.class, true, "autoUpdate", new Option.Update<Boolean>() {@Override public void run() {
			p3.value = optionFrame.AutoUpdate.isSelected();
		}}, new Option.Update<Boolean>() {@Override public void run() {
			optionFrame.AutoUpdate.setSelected((boolean) p3.value);
		}});
		OptionManager.addOption(p3);
		
		p4 = new Option<String>(String.class, "latest", "version", new Option.Update<String>() {	@Override public void run() {
			p4.value = (String) optionFrame.Version.getSelectedItem();
		}}, new Option.Update<String>() {@Override public void run() {
			optionFrame.Version.setSelectedItem(p4.value);
		}});
		OptionManager.addOption(p4);
		
		p5 = new Option<Integer>(Integer.class, LAUNCHVIS_CLOSE, "launcherVisability", new Option.Update<Integer>() {@Override public void run() {
			p5.value = optionFrame.LauncherVisability.getSelectedIndex();
		}}, new Option.Update<Integer>() {@Override public void run() {
			optionFrame.LauncherVisability.setSelectedIndex((int) p5.value);
		}});
		OptionManager.addOption(p5);
		
		p6 = new Option<Boolean>(Boolean.class, false, "userCredentials", new Option.Update<Boolean>() {@Override public void run() {
			p6.value = optionFrame.UserCrd.isSelected();
		}}, new Option.Update<Boolean>() {@Override public void run() {
			optionFrame.UserCrd.setSelected((boolean) p6.value);
		}});
		OptionManager.addOption(p6);
		
		p7 = new Option<String>(String.class, "-", "username", new Option.Update<String>() {
		@Override public void run() {
			if((Boolean) OptionManager.getValue("userCredentials"))
				p7.value = launcherFrame.txtUsername.getText();
			else
				p7.value = "-";
		}}, new Option.Update<String>() {@Override public void run() {
			
		}});
		OptionManager.addOption(p7);
		
		p8 = new Option<String>(String.class, "-", "password", new Option.Update<String>() {@Override public void run() {
			if((Boolean) OptionManager.getValue("userCredentials"))
				p8.value = new String(launcherFrame.passwordField.getPassword());
			else 
				p8.value = "-";
		}}, new Option.Update<String>() {@Override public void run() {
			
		}});
		OptionManager.addOption(p8);
		
		p9 = new Option<Integer>(Integer.class, 720, "screenWidth", new Option.Update<Integer>() {@Override public void run() {
			p9.value = Integer.parseInt(optionFrame.widthField.getText());
		}}, new Option.Update<Integer>() {@Override public void run() {
			optionFrame.widthField.setText(String.valueOf(p9.value));
		}});
		OptionManager.addOption(p9);
		
		p10 = new Option<Integer>(Integer.class, 360, "screenHeight", new Option.Update<Integer>() {@Override public void run() {
			p10.value = Integer.parseInt(optionFrame.heidhtField.getText());
		}}, new Option.Update<Integer>() {@Override public void run() {
			optionFrame.heidhtField.setText(String.valueOf(p10.value));
		}});
		OptionManager.addOption(p10);
	}
	
}
