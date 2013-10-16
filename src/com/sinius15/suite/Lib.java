package com.sinius15.suite;

import java.io.File;

public class Lib {

	public static File FOLDER_MAIN;
	public static File FOLDER_SAVES;
	public static File FOLDER_RESOURCE;
	public static final String TITLE = "Project Suite";
	
	public static int SCREEN_WIDTH, SCREEN_HEIGHT;
	
	public static void init(String folderArg, int screenW, int screenH){
		FOLDER_MAIN = new File(folderArg);
		
		FOLDER_SAVES = new File(FOLDER_MAIN.getPath() + "\\saves");
		FOLDER_RESOURCE = new File(FOLDER_MAIN.getPath() + "\\resource");
		
		SCREEN_HEIGHT = screenH;
		SCREEN_WIDTH = screenW;
	}
	
}
