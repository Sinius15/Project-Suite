package com.sinius15.suite.game;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import com.sinius15.suite.Lib;
import com.sinius15.suite.util.ArgumentsReader;


public class Game extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;

	public static final int SCREENWIDHTSTART = 1;
	public static final int SCREENHEIGHTSTART = 1;
	public static final String TITLE = "Project Suite";
	public boolean isRunning = false;
	public Level level;
	
	public void start() {
		isRunning = true;
		new Thread(this).start();
	}
	
	public void stop() {
		isRunning = false;
	}
	
	@Override
	public void run() {
		
		init();
		
		while(isRunning) {
			
			tick();
			render();
			
			try {
				Thread.sleep(20);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void tick() {
		if(level!=null) {
			level.tick();
		}
	}
	
	public void render() {
		BufferStrategy buffer = getBufferStrategy();
		if (buffer == null) {
			this.createBufferStrategy(2);
			requestFocus();
			return;
		}
		BufferedImage img = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_ARGB);
		img.createGraphics();
		
		// stop with stuff to render
		int ww = getWidth();
		int hh = getHeight();
		Graphics gBase = buffer.getDrawGraphics();
		gBase.fillRect(0, 0, ww, hh);
		gBase.clearRect(0, 0, ww, hh);
		gBase.drawImage(img, 0, 0, null);
		gBase.dispose();
		buffer.show();
	}
	
	public void init() {
		level = new Level(new Dimension(1,1));		
	}

	public static void main(String[] args) {
		ArgumentsReader argReader = new ArgumentsReader(args);
		if(argReader.getValue("dataFolder") == null)
			Lib.init("default");
		else
			Lib.init(argReader.getValue("dataFolder"));
		
		Game game = new Game();
		game.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		game.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		game.setIgnoreRepaint(true);

		JFrame frame = new JFrame(Game.TITLE);
		frame.setIgnoreRepaint(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(game, BorderLayout.CENTER);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		game.start();
	}

}
