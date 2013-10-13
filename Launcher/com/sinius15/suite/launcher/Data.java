package com.sinius15.suite.launcher;

import java.io.File;
import java.io.IOException;

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
	
	public static OptionFrame optionFrame = new OptionFrame();
	public static LauncherFrame launcherFrame = new LauncherFrame();
	
	
	public static void loadData() throws IOException, Exception{
		File file = new File(DEFAULT_DATA_FOLDER.getPath() + "\\launcherOptions.yml");
		if(!file.exists()){
			DEFAULT_DATA_FOLDER.mkdirs();
			file.createNewFile();
			YAMLFile creator = new YAMLFile(true);
			
			creator.addboolean("defautlDataFolder", true);
			creator.addString("dataFolder", "default");
			creator.addboolean("autoUpdate", true);
			creator.addString("version", "latest");
			creator.addInt("launcherVisability", LAUNCHVIS_CLOSE);
			
			creator.Save(file);
		}
		
		YAMLFile dataFile = new YAMLFile(true);
		dataFile.Load(file);
		
		
		defaultDataFolder = dataFile.getboolean("defautlDataFolder");
		dataFolder = dataFile.getString("dataFolder");
		autoUpdate = dataFile.getboolean("autoUpdate");
		version = dataFile.getString("version");
		launcherVis = dataFile.getInt("launcherVisability");
		
		
		
	}
}
