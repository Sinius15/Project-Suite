package com.sinius15.suite.launcher.games;

public class Chess extends Game{

	@Override
	public String getName() {
		return "chess";
	}

	@Override
	public String getJarName(){
		return "chess.jar";
	}
	
	@Override
	public String getArguments() {
		return "";
	}
	
	@Override
	public int getVersionLength() {
		return 3;
	}
	
}
