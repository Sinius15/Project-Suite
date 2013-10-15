package com.sinius15.suite.launcher;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.swing.JOptionPane;

import com.sinius15.suite.launcher.io.Downloader;

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
		String versionToStart = (String) OptionManager.getValue("version");
		String versionOnPC = "gET VERSION ON PC!";
		try{
			String latestVersion = Downloader.getLatestVersion(System.out);
			if((Boolean) OptionManager.getValue("autoUpdate") && !latestVersion.equals(versionToStart)){
				int n = JOptionPane.showConfirmDialog(Data.launcherFrame, "You have selected an outdated version in the option menu. do you want to update to the latest version?", 
						"update?", JOptionPane.YES_NO_OPTION);
				if(n == JOptionPane.YES_NO_OPTION){
					versionToStart = latestVersion;
				}
			}
		}catch (Exception e) {
			
			e.printStackTrace();
		}
		
		if(!downloadReqFiles()){
			System.err.println("[Suite Error]  -  Something went wrong while downloading the files. Maybe there is no internet connection");
			return;
		}
		System.out.println("The game arguments are: " + getArguments());
	}
	
	public static String getArguments(){
		String args = 
				"screenW=>" + OptionManager.getValue("screenWidth") + 
				" screenH=>" + OptionManager.getValue("screenHeight");
		
		if((Boolean)OptionManager.getValue("defaultDataFolder"))
			args = args + " \"dataFolder=>" + Data.DEFAULT_DATA_FOLDER.getAbsolutePath() + "\"";
		else
			args = args + " \"dataFolder=>" + (String) OptionManager.getValue("dataFolder") + "\"";

		return args;
	}
	
	public static boolean downloadReqFiles(){
		try {
			if(OptionManager.getValue("version").equals("-"))
				return false;
			Downloader.downloadFile(new URL("http://sinius15.com/suite/versions/"+ OptionManager.getValue("version") + ".zip"), new File("PUT A FIEL HERE !!!"), System.out);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}
