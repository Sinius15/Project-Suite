package com.sinius15.suite.launcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.sinius15.suite.launcher.io.Downloader;

public class Launcher {

	public static String latestVersion = null;
	public static String[] versionList = null;
	
	public static void main(String[] args) {
		Data.launcherFrame.setVisible(true);
		initOnlineStuff();
		Data.initOptions();
		try {
			OptionManager.loadOptions(Data.CONFIG_FILE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if((Boolean)OptionManager.getValue("userCredentials")){
			Data.launcherFrame.txtUsername.setText((String) OptionManager.getValue("username"));
			Data.launcherFrame.textFieldHadFocus = true;
			Data.launcherFrame.passwordField.setEchoChar((char)9679);
			Data.launcherFrame.passwordField.setText((String) OptionManager.getValue("password"));
			Data.launcherFrame.passwordFieldHadFocus = true;
		}
	}
	
	public static void initOnlineStuff(){
		Thread internetThread = new Thread(new Runnable() {@Override public void run() {
			try {
				Data.launcherFrame.btnPlay.setEnabled(false);
				Launcher.latestVersion = Downloader.getLatestVersion(System.out);
				Launcher.versionList = Downloader.getVersionList(System.out);
				Data.launcherFrame.btnPlay.setEnabled(true);
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("Could not retreive data from the internet. Please try again by restarting the launcher.");
				System.err.println("If a restart didn't work, you can play in offline mode.");
			}
		}}, "getInternetData");
		internetThread.start();
	}
	
	private static Thread runThread, observer;
	public static void launchGameOnline(){
		System.out.println("Starting the game in online mode...");
		Data.launcherFrame.btnPlay.setEnabled(false);
		runThread = new Thread(startGameOnline, "Game");
		runThread.start();
		observer = new Thread(new Runnable() {@Override public void run() {
			while(true)
				if(!runThread.isAlive())
					break;
			if((Integer)OptionManager.getValue("launcherVisability") == Data.LAUNCHVIS_REOPEN)
				Data.launcherFrame.setVisible(true);	
			System.out.println("Game stopped");	
			Data.launcherFrame.btnPlay.setEnabled(true);
			
		}}, "Observer");
		observer.start();
	}
	public static void launchGameOffline(){
		System.out.println("Starting the game in offline mode...");
		Data.launcherFrame.btnPlay.setEnabled(false);
		runThread = new Thread(startGameOffline, "Game");
		runThread.start();
		observer = new Thread(new Runnable() {@Override public void run() {
			while(true)
				if(!runThread.isAlive())
					break;
			if((Integer)OptionManager.getValue("launcherVisability") == Data.LAUNCHVIS_REOPEN)
				Data.launcherFrame.setVisible(true);
			System.out.println("Game stopped");
			Data.launcherFrame.btnPlay.setEnabled(true);
					
		}}, "Observer");
		observer.start();
	}
	
	private static Runnable startGameOffline = new Runnable(){@Override public void run(){
		Data.launcherFrame.btnPlay.setEnabled(false);
		String dataFolder;
		
		if((Boolean)OptionManager.getValue("defaultDataFolder"))
			dataFolder = Data.DEFAULT_DATA_FOLDER.getAbsolutePath();
		else
			dataFolder = (String) OptionManager.getValue("dataFolder");
		String localVersion = getLocalVersion(dataFolder);
		if(localVersion == null){
			JOptionPane.showMessageDialog(Data.launcherFrame, "The game has never downloaded any versions before, so it can't start the game offline.");
			return;
		}
		startJar(dataFolder);	
		
	}};
	
	private static Runnable startGameOnline = new Runnable() {@Override public void run() {
		 
		String versionToStart = ((String) OptionManager.getValue("version")).substring(0, 6);
		
		String dataFolder;
		
		if((Boolean)OptionManager.getValue("defaultDataFolder"))
			dataFolder = Data.DEFAULT_DATA_FOLDER.getAbsolutePath();
		else
			dataFolder = (String) OptionManager.getValue("dataFolder");
		String localVersion = getLocalVersion(dataFolder);
		
		try{
			if((Boolean) OptionManager.getValue("autoUpdate")){
				versionToStart = latestVersion;
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
		
		startJar(dataFolder);
	}};
	
	private static void startJar(String dataFolder){
		String arguments = getArguments();
		System.out.println("The game arguments are: " + arguments);
		
		Process p = null;
		try {
			p = Runtime.getRuntime().exec("java -jar " + dataFolder + "\\game.jar " + arguments);
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}
		
		if((Integer)OptionManager.getValue("launcherVisability") == Data.LAUNCHVIS_CLOSE)
			System.exit(0);
		if((Integer)OptionManager.getValue("launcherVisability") == Data.LAUNCHVIS_REOPEN)
			Data.launcherFrame.setVisible(false);
		
		//this is for the reading of errors
		final InputStream err = p.getErrorStream();
		Thread errThread = new Thread(new Runnable() {@Override public void run() {
			try {
				BufferedReader errReader = new BufferedReader(new InputStreamReader(err));
				String line = null;
				while ((line = errReader.readLine()) != null) 
					System.err.println(line);
				errReader.close();
			} catch (Exception e) { 
				e.printStackTrace();
			}
		}}, "errThread");
		errThread.start();
		
		//this is for the reading of normal output
		InputStream in = p.getInputStream();
		try {
			BufferedReader txtReader = new BufferedReader(new InputStreamReader(in));
			String line = null;
			while ((line = txtReader.readLine()) != null) 
				System.out.println(line);
			txtReader.close();
			
		} catch (Exception e) { 
			e.printStackTrace();
		}
		
		while(true)
			if(!errThread.isAlive())
				break;
		try {
			in.close();
			err.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
