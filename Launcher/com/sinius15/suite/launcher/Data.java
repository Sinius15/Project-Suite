package com.sinius15.suite.launcher;

import java.io.File;

public class Data {
	
	public static final File DEFAULT_DATA_FOLDER = new File(System.getenv("APPDATA") + "\\Suite");
	

	public static final int LAUNCHVIS_CLOSE = 0;
	public static final int LAUNCHVIS_REOPEN = 1;
	public static final int LAUNCHVIS_OPEN = 2;
	
	public static OptionFrame optionFrame = new OptionFrame();
	public static LauncherFrame launcherFrame = new LauncherFrame();
	
	public static void initOptions(){
		
		final Option<Boolean> p1 = new Option<Boolean>(Boolean.class, true, "defaultDataFolder", new Option.Update<Boolean>() {@Override public void run() {
			p1.value = optionFrame.defaultDataFolder.isSelected();
		}}, new Option.Update<Boolean>() {@Override public void run() {
			optionFrame.defaultDataFolder.setSelected(p1.value);
		}});
		OptionManager.addOption(p1);
		
		Option<String> p2 = new Option<String>(String.class, "default", "dataFolder", new Option.Update<String>() {@Override public void run() {
			p2.value = optionFrame.pathField.getText();
		}}, new Option.Update<String>() {@Override public void run() {
			optionFrame.pathField.setText(p2.value);
		}});
		OptionManager.addOption(p2);
		
		Option<Boolean> p3 = new Option<Boolean>(Boolean.class, true, "autoUpdate", new Option.Update<Boolean>() {@Override public void run() {
			p3.value = optionFrame.AutoUpdate.isSelected();
		}}, new Option.Update<Boolean>() {@Override public void run() {
			optionFrame.AutoUpdate.setSelected(p3.value);
		}});
		OptionManager.addOption(p3);
		
		Option<String> p4 = new Option<String>(String.class, "latest", "version", new Option.Update<String>() {	@Override public void run() {
			p4.value = (String) optionFrame.Version.getSelectedItem();
		}}, new Option.Update<String>() {@Override public void run() {
			optionFrame.Version.setSelectedItem(p4.value);
		}});
		OptionManager.addOption(p4);
		
		Option<Integer> p5 = new Option<Integer>(Integer.class, LAUNCHVIS_CLOSE, "launcherVisability", new Option.Update<Integer>() {@Override public void run() {
			p5.value = optionFrame.LauncherVisability.getSelectedIndex();
		}}, new Option.Update<Integer>() {@Override public void run() {
			optionFrame.LauncherVisability.setSelectedIndex(p5.value);
		}});
		OptionManager.addOption(p5);
		
		Option<Boolean> p6 = new Option<Boolean>(Boolean.class, false, "userCredentials", new Option.Update<Boolean>() {@Override public void run() {
			p6value = optionFrame.UserCrd.isSelected();
		}}, new Option.Update<Boolean>() {@Override public void run() {
			optionFrame.UserCrd.setSelected(p6.value);
		}});
		OptionManager.addOption(p6);
		
		Option<String> p7 = new Option<String>(String.class, "-", "username", new Option.Update<String>() {@Override public void run() {
			if((Boolean) OptionManager.getValue("userCredentials"))
				p7.value = launcherFrame.txtUsername.getText();
			else
				p7.value = "-";
		}}, new Option.Update<String>() {@Override public void run() {
			
		}});
		OptionManager.addOption(p7);
		
		Option<String> p8 = new Option<String>(String.class, "-", "password", new Option.Update<String>() {@Override public void run() {
			if((Boolean) OptionManager.getValue("userCredentials"))
				p8.value = new String(launcherFrame.passwordField.getPassword());
			else 
				p8.value = "-";
		}}, new Option.Update<String>() {@Override public void run() {
			
		}});
		OptionManager.addOption(p8);
	}
	
}
