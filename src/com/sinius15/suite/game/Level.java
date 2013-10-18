package com.sinius15.suite.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.sinius15.suite.entitys.Entity;
import com.sinius15.suite.tiles.StraightRail;
import com.sinius15.suite.tiles.Tile;

public class Level {

	public String name;
	public int w;
	public int h;
	public int xScroll = 0;
	public int yScroll = 0;
	protected byte[][] tiles;
	protected byte[][] data;
	public List<Entity> entities;
	public Dimension screenSize;
	public Image background;
	private boolean staticBackground = true;
	private File backgroundFile;
	private String stringBack = "standard";
	
	public Level(Dimension d, String name,File background) {
		w = d.width;
		h = d.height;
		tiles = new byte[w][h];
		data = new byte[w][h];
		entities = new ArrayList<Entity>();
		this.name = name;
		backgroundFile = background;
		createImage();
		
		setTile(80, 80, new StraightRail(50, 12), 0);	//for testing purposes only   more testing
	}

	private void createImage() {
		background = new BufferedImage(screenSize.width,screenSize.height,BufferedImage.TYPE_INT_ARGB);
		try {
			if(backgroundFile==null)
				throw new IOException("Making standard background");
			background = ImageIO.read(backgroundFile);
			stringBack = backgroundFile.getAbsolutePath();
		} catch(IOException e) {
			Graphics g = background.getGraphics();
			g.setColor(new Color(0,135,255));
			g.fillRect(0, 0, screenSize.width, screenSize.height);
			g.setColor(Color.black);
			g.drawString("this should be the bg image!", 100, 100);
			stringBack = "standard";
		}
		
	}

	public void tick() {
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			if(e.removed) {
				entities.remove(i);
				continue;
			}
			e.tick();
		}
	}
	
	public BufferedImage render() {
		BufferedImage img = new BufferedImage(screenSize.width, screenSize.height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = img.createGraphics();
		int xImg = staticBackground ? 0 : xScroll, yImg = staticBackground ? 0 : yScroll; 
		g.drawImage(background, 0, 0, screenSize.width, screenSize.height, xImg, yImg, xImg + screenSize.width, yImg + screenSize.height, null);
		for(int x = 0 ; x < w ; x++){
			for(int y = 0 ; y < h ; y++){
				g.drawImage(getTile(x, y).render(this,x,y), x, y, null);//dit moet jij misschien effe doen...   (hier klopt namelijk weinig van)
			}
		}
		return img;
	}
	
	public Tile getTile(int x,int y) {
		if(x<0||y<0||x>=w||y>=h)
			return Tile.sideTile;
		return Tile.tiles[tiles[x][y] & 0xff];
	}
	
	public int getData(int x,int y) {
		if(x<0||y<0||x>=w||y>=h)
			return -1;
		return data[x][y] & 0xff;
	}
	
	public void setData(int x, int y, int meta) {
		if(x<0||y<0||x>=w||y>=h)
			return;
		data[x][y] = (byte)meta;
	}
	
	public void setTile(int x,int y,Tile t,int meta) {
		if(x<0||y<0||x>=w||y>=h)
			return;
		tiles[x][y] = (byte)t.id;
		data[x][y] = (byte) meta;
	}
	
}
