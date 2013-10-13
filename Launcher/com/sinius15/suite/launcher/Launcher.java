package com.sinius15.suite.launcher;

public class Launcher {

	public static void main(String[] args) {
		
		try {
			Data.loadData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Data.launcherFrame.setVisible(true);
		if(Data.saveUser){
			Data.launcherFrame.txtUsername.setText(Data.username);
			Data.launcherFrame.passwordField.setText(Data.password);
		}
	}
	
}
