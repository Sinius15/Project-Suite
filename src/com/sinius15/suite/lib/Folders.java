package com.sinius15.suite.lib;

import java.io.File;

public class Folders {

	public static File MAIN;
	public static File SAVES;
	public static File FONTS;
	
	//the lib package bevat allemaal static variablen, de naar dingen toewijzen.
	//int het lib package hoort bijvoorbeeld een file "general.java". die variablen zoals  "name" & "version"
	//ook kan hier een file met "error mesages" met error berichten.
	//
	//dit lijkt mij handig zodat als er iets(map, naam, stukje tekst) moet worden verandert, dan staat dat in 1 plek.
	//
	//
	//deze file is een voorbeeld, met alle mappen. omdat alle mappen afhangen van de "main data folder" is er een method
	//die alle mappen initializert.  dat is deze hier onder.
	public static void initFiles(String folderArg){
		if(folderArg.equals("default"))
			MAIN = new File(System.getenv("APPDATA") + "\\Suite");
		else
			MAIN = new File(folderArg);
		
		SAVES = new File(MAIN.getPath() + "\\saves");
		FONTS = new File(MAIN.getPath() + "\\fonts");
		
	}
	
}
