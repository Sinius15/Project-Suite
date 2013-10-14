package com.sinius15.suite.launcher;

public class Launcher {

	public static void main(String[] args) {
		Data.initOptions();
		try {
			OptionManager.loadOptions(Data.CONFIG_FILE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Data.launcherFrame.setVisible(true);
		if((Boolean)OptionManager.getValue("userCredentials")){
			Data.launcherFrame.txtUsername.setText((String) OptionManager.getValue("username"));
			Data.launcherFrame.passwordField.setEchoChar((char)9679);
			Data.launcherFrame.passwordField.setText((String) OptionManager.getValue("password"));
		}
	}
	
	public static void launchGame(){
		System.out.println(getArguments());
	}
	
	public static String getArguments(){
		String args = 
				"screenW=>" + OptionManager.getValue("screenWidth") + 
				" screenH=>" + OptionManager.getValue("screenHeight");
		
		if((Boolean)OptionManager.getValue("defaultDataFolder"))
			args = args + " \"dataFolder=>" + Data.DEFAULT_DATA_FOLDER.getAbsolutePath() + "\"";
		else
			args = args + " \"dataFolder=>" + (String) OptionManager.getValue("dataFolder") + "\"";
		
		System.out.println("the game arguments are: " + args);
		return args;
	}
	
}
