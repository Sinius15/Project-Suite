package com.sinius15.suite.game;

import com.sinius15.suite.lib.Folders;
import com.sinius15.suite.util.ArgumentsReader;

public class Sinius_Main {

	//This would be the main method...
	//see for your self, where this needs to be implemented
	public void Main(String[] args){
		ArgumentsReader argReader = new ArgumentsReader(args);
		if(argReader.getValue("dataFolder") == null)
			Folders.initFiles("default");
		else
			Folders.initFiles(argReader.getValue("dataFolder"));
	}
	
}
