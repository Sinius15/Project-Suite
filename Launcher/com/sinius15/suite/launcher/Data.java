package com.sinius15.suite.launcher;

import java.io.File;

import com.sinius15.suite.util.YAMLFile;

public class Data {
	
	public static final File DEFAULT_DATA_FOLDER = new File(System.getenv("APPDATA") + "\\Suite");
	

	public static final int LAUNCHVIS_CLOSE = 0;
	public static final int LAUNCHVIS_REOPEN = 1;
	public static final int LAUNCHVIS_OPEN = 2;
	
	public static boolean defaultDataFolder;
	public static String dataFolder;
	public static boolean autoUpdate;
	public static String version;
	public static int launcherVis;
	public static boolean saveUser;
	public static String username, password;
	
	public static OptionFrame optionFrame = new OptionFrame();
	public static LauncherFrame launcherFrame = new LauncherFrame();
	
	
	public static void loadData() throws Exception{
		File file = new File(DEFAULT_DATA_FOLDER.getPath() + "\\launcherOptions.yml");
		if(!file.exists()){
			DEFAULT_DATA_FOLDER.mkdirs();
			file.createNewFile();
			YAMLFile creator = new YAMLFile(true);
			
			creator.addboolean("defaultDataFolder", true);
			creator.addString("dataFolder", "default");
			creator.addboolean("autoUpdate", true);
			creator.addString("version", "latest");
			creator.addInt("launcherVisability", LAUNCHVIS_CLOSE);
			creator.addboolean("userCredentials", false);
			creator.addString("username", "-");
			creator.addString("password", "-");
			creator.Save(file);
		}
		
		YAMLFile dataFile = new YAMLFile(true);
		dataFile.Load(file);
		
		
		defaultDataFolder = dataFile.getboolean("defaultDataFolder");
		dataFolder = dataFile.getString("dataFolder");
		autoUpdate = dataFile.getboolean("autoUpdate");
		version = dataFile.getString("version");
		launcherVis = dataFile.getInt("launcherVisability");
		saveUser = dataFile.getboolean("userCredentials");
		username = dataFile.getString("username");
		password = dataFile.getString("password");
	}
	
	public static void saveData() throws Exception{
		File file = new File(DEFAULT_DATA_FOLDER.getPath() + "\\launcherOptions.yml");
		YAMLFile dataFile = new YAMLFile(true);
		
		dataFile.addboolean("defaultDataFolder", defaultDataFolder);
		dataFile.addString("dataFolder", dataFolder);
		dataFile.addboolean("autoUpdate", autoUpdate);
		dataFile.addString("version", version);
		dataFile.addInt("launcherVisability", launcherVis);
		dataFile.addboolean("userCredentials", saveUser);
		dataFile.addString("username", username);
		dataFile.addString("password", password);
		
		dataFile.Save(file);
	}
}
