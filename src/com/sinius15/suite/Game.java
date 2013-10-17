package com.sinius15.suite;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import com.sinius15.suite.game.Level;
import com.sinius15.suite.util.ArgumentsReader;

public class Game extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	
	public boolean isRunning = false;
	public Level level;
	
	public void start() {
		isRunning = true;
		new Thread(this).start();
		//Mabye run
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
		BufferedImage img = new BufferedImage(Lib.SCREEN_WIDTH, Lib.SCREEN_HEIGHT,
				BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.createGraphics();
		//Start rendering stuff
		if(level!=null) {
			g.drawImage(level.render(), 0, 0, null);
		}
		
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
		level = new Level(new Dimension(Lib.SCREEN_WIDTH,Lib.SCREEN_HEIGHT),new Dimension(Lib.SCREEN_WIDTH,Lib.SCREEN_HEIGHT));		
	}

	public static void main(String[] args) {
		System.out.println("Starting the game...");
		try{
			ArgumentsReader argReader = new ArgumentsReader(args);
			Lib.init(argReader.getValue("dataFolder"), Integer.parseInt(argReader.getValue("screenW")), Integer.parseInt(argReader.getValue("screenH")));
		}catch(NullPointerException e){
			e.printStackTrace();
			System.exit(1);
		}
		Game game = new Game();
		game.setMinimumSize(new Dimension(Lib.SCREEN_WIDTH, Lib.SCREEN_HEIGHT));
		game.setMaximumSize(new Dimension(Lib.SCREEN_WIDTH, Lib.SCREEN_HEIGHT));
		game.setPreferredSize(new Dimension(Lib.SCREEN_WIDTH, Lib.SCREEN_HEIGHT));
		game.setIgnoreRepaint(true);

		JFrame frame = new JFrame(Lib.TITLE);
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
