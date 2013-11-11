package com.sinius15.suite.launcher.games;

import java.io.File;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;

import com.sinius15.suite.launcher.Data;
import com.sinius15.suite.launcher.io.Downloader;
import com.sinius15.suite.util.YAMLFile;

public abstract class Game {

	public String getName(){ return null; };
	public String getArguments(){return null;};
	public String getJarName(){return null;};
	public String latestVersion = "";
	public String[] versionList = {""};
	public int getVersionLength(){return 0;}
	public boolean autoUpdate(){
		return true;
	}
	
	public void update(){
		Data.launcherFrame.versionCombo.setModel(new DefaultComboBoxModel<String>(versionList));
		
		File f = new File(Data.DEFAULT_DATA_FOLDER + "\\" + getName());
		f.mkdirs();
		f = new File(Data.DEFAULT_DATA_FOLDER + "\\" + getName() + "\\" + "launcherConfig.yml");
		if(!f.exists()){
			try {
				f.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			YAMLFile dat = new YAMLFile(true);
			dat.addboolean("autoUpdate", true);
			dat.addString("version", "-");
			try {
				dat.Save(f);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		YAMLFile dat = new YAMLFile(true);
		dat.Load(f);
		Data.launcherFrame.autoUpdate.setSelected(dat.getboolean("autoUpdate"));
		Data.launcherFrame.versionCombo.setEnabled(!dat.getboolean("autoUpdate"));
		if(!dat.getString("version").equals("-"))
			Data.launcherFrame.versionCombo.setSelectedItem(dat.getString("version"));
	}
	
	public void saveConfig(){
		File f = new File(Data.DEFAULT_DATA_FOLDER + "\\" + getName() + "\\" + "launcherConfig.yml");
		YAMLFile dat = new YAMLFile(true);
		dat.addboolean("autoUpdate", Data.launcherFrame.autoUpdate.isSelected());
		if(Data.launcherFrame.autoUpdate.isSelected())
			dat.addString("version", "-");
		else{
			dat.addString("version", (String) Data.launcherFrame.versionCombo.getSelectedItem());
		}
		try {
			dat.Save(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void init() throws IOException{
		System.out.println("Loading the game " + getName());
		
		latestVersion = Downloader.getLatestVersion(System.out, this);
		versionList = Downloader.getVersionList(System.out, this);
		versionList[versionList.length-1] = versionList[versionList.length-1].substring(0, getVersionLength());
		latestVersion = latestVersion.substring(0, getVersionLength());
		Data.launcherFrame.gameBox.addItem(getName());
		Data.launcherFrame.gameBox.setSelectedItem(getName());
		
		update();
		
		System.out.println("Done loading the game " + getName());
	};
	
}
