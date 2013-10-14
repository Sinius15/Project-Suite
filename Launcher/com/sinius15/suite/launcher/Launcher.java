package com.sinius15.suite.launcher;

import java.io.File;

public class Launcher {

	public static void main(String[] args) {
		Data.initOptions();
		try {
			OptionManager.loadOptions(new File(Data.DEFAULT_DATA_FOLDER.getPath() + "\\launcherOptions.yml"));
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
	
}
