package com.sinius15.suite.launcher;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

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
		String versionToStart = ((String) OptionManager.getValue("version")).substring(0, 6);
		
		String dataFolder;
		
		if((Boolean)OptionManager.getValue("defaultDataFolder"))
			dataFolder = Data.DEFAULT_DATA_FOLDER.getAbsolutePath();
		else
			dataFolder = (String) OptionManager.getValue("dataFolder");
		String localVersion = getLocalVersion(dataFolder);
		
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
			System.err.println("[Suite Error] - Something went wrong while checking for latest version. Maybe there is no internet connection?");
			return;
		}
		if(localVersion == null || !localVersion.equals(versionToStart)){
			System.out.println("going to download verision " + versionToStart);
			if(!downloadReqFiles(dataFolder, versionToStart)){
				System.err.println("[Suite Error]  -  Something went wrong while downloading the files. Maybe there is no internet connection?");
				return;
			}
			try {
				Downloader.unZip(dataFolder + "\\temp.zip", dataFolder, System.out);
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("[Suite Error] - Something went wrong while unzipping a file. maybe you have no writing permission in your selected data folder?");
				return;
			}
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
	
	public static boolean downloadReqFiles(String dataFolder, String version){
		try {
			if(OptionManager.getValue("version").equals("-"))
				return false;
			URL url = new URL("http://sinius15.com/suite/versions/"+ version + ".zip");
			Downloader.downloadFile(url, new File(dataFolder + "\\temp.zip"), System.out);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static String getLocalVersion(String dataFolder){
		File f = new File(dataFolder + "\\version.txt");
		if(!f.exists())
			return null;
		try {
			Scanner scan = new Scanner(f);
			String out = scan.next();
			scan.close();
			return out;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}
