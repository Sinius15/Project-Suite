package com.sinius15.suite.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Level {

	public int w;
	public int h;
	public int xScroll = 0;
	public int yScroll = 0;
	public byte[][] tiles;
	public byte[][] data;
	public List<Entity> entities;
	public Dimension screenSize;
	public Image background;
	private boolean staticBackground = true;
	
	public Level(Dimension d,Dimension sSize) {
		w = d.width;
		h = d.height;
		tiles = new byte[w][h];
		screenSize = sSize;
		entities = new ArrayList<Entity>();
		createImage();
	}

	private void createImage() {
		background = new BufferedImage(screenSize.width,screenSize.height,BufferedImage.TYPE_INT_ARGB);
		Graphics g = background.getGraphics();
		g.setColor(new Color(0,135,255));
		g.fillRect(0, 0, screenSize.width, screenSize.height);
	}

	public void tick() {
		if(true)
			return;
	}
	
	public BufferedImage render() {
		BufferedImage img = new BufferedImage(screenSize.width, screenSize.height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = img.createGraphics();
		int xImg = staticBackground ? 0 : xScroll, yImg = staticBackground ? 0 : yScroll; 
		g.drawImage(background, 0, 0, screenSize.width, screenSize.height, xImg, yImg, xImg + screenSize.width, yImg + screenSize.height, null);
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
