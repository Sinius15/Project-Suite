package com.sinius15.suite;

import java.io.File;

public class Lib {

	public static File MAIN;
	public static File SAVES;
	public static File RESOURCE;
	
	public static void init(String folderArg){
		if(folderArg.equals("default"))
			MAIN = new File(System.getenv("APPDATA") + "\\Suite");
		else
			MAIN = new File(folderArg);
		
		SAVES = new File(MAIN.getPath() + "\\saves");
		RESOURCE = new File(MAIN.getPath() + "\\resource");
		
	}
	
}
