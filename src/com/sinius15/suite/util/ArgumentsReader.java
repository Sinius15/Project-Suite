package com.sinius15.suite.util;

import java.util.HashMap;

public class ArgumentsReader {
	
	private HashMap<String, String> args = new HashMap<>(); 
	
	public ArgumentsReader(String args[]){
		for(int i = 0; i < args.length; i++){
			String[] split = args[i].split("=>");
			if(split.length != 2) return;
			this.args.put(split[0], split[1]);
		}
	}
	
	public String getValue(String key){
		try{
			return args.get(key);
		}catch(NullPointerException e){
			return null;
		}
		
	}

}
