package com.sinius15.suite.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.sinius15.suite.game.Entity;
import com.sinius15.suite.game.Level;

public class LevelSaver {

	public static void saveLevel(Level l, File f) throws IOException{
		if(!f.exists())
			f.createNewFile();
		PrintWriter writer = new PrintWriter(f);
		
		writer.println("#This is a save file for the game 'Suite'. If you want to open this file, i reccommand downloading the game for free at ...");
		writer.println("");
		writer.println("#general:");
		writer.println("levelName: \"" + l.name + "\"");
		writer.println("levelWidht: " + l.w);
		writer.println("levelHeight: " + l.h);
		writer.println("levelDificulty: " + 0);		//?
		writer.println("XScroll: " + l.xScroll);
		writer.println("YScroll: " + l.yScroll);
		writer.println("Background: " + "");		//?
		writer.println("");
		writer.println("positions: ");
		ArrayList<Entity> ents;
		for(int x = 0 ; x < l.w ; x++){
			for(int y = 0 ; y < l.h ; y++){
				ents = new ArrayList<>();
				for(Entity e : l.entities)
					if(e.x == x && e.y == y)
						ents.add(e);
				if(l.getTile(x, y).id != 0 && ents.size() == 0)
					continue;
				String builder = "x:" + x + "y:" + y +  "t:" + l.getTile(x,y).id  + "(" + l.getData(x, y) + ") ";
				for(Entity e : ents)
					builder = builder + "e:" + e.getClass().getName() + "(" + e.toSave() + ")";
				writer.println(builder);
			}
		}
		
		writer.close();
		
		
	}
	
	public Level loadLevel(File f){

		return null;
	}
	
	/*	This is how the files are going to look:
	 * 
	 *  general:
	 *  levelname
	 * 	LevelWidht: 100
	 *  LevelHeight: 100
	 *  LevelDificulty: 0 
	 *  XScroll: 20
	 *  YScroll: 20
	 *  Background: images/cookeis.jpg
	 *  StaticBackground: ?
	 *  
	 *  blocks:
	 *  x:12, y:13, t:track(arguments) e:cookiemonster(argumetns) e:cloudmonster(arguments) 
	 *  x:16, y:24, t:track(arguments) e:cookiemonster(argumetns) e:cloudmonster(arguments) 
	 *  */
	
	/*
	 *  t = tile    e = entity
	 *  if a coord=xyz  is not saved,  it has no entitys and the tyle = air)
	 *  The arguments are provided by the getArguments() method. The name is found by the class name.
	 *  This means that all the entitys need all to be in the same package!!!
	 *  
	 *  the arguments are passed in as strings or integers in the constructor of the tile/entity
	 *  
	 * */
}
