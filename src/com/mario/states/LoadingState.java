package com.mario.states;

import java.awt.Color;
import java.awt.Graphics;

import com.mario.Handler;
import com.mario.gfx.Assets;

public class LoadingState extends State{
	
	private int progress = 0;//Int from 0 to 100
	private String text = "";//Text to display
	
	
	public LoadingState(Handler handler) {
		super(handler);
	}

	int timer = 0;
	long lastTime = System.currentTimeMillis();
	public void tick() {
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		if(timer > 10 && progress < 400) {
			timer = 0;
			progress++;
			
			if(progress == 400) {
				progress = 0;
				GameStateManager.setState(handler.getMenuState()); 
			}
			
			
		}

		
	}
	public void render(Graphics g) {
		

		
		
		g.drawImage(Assets.brick[0], handler.getWidth() / 2 - (64), 200,128,128,null);
		g.setFont(Assets.menu); 
		g.drawString(text, handler.getWidth() / 2 - (text.length() * Assets.menu.getSize() / 4), handler.getHeight() / 2 - 100);
		
		
		int rectWidth = 400;
		int rectHeight = 40;
		int margin = 4;
		int startX = (handler.getWidth() / 2) - ((rectWidth + margin) / 2);
		int startY = (handler.getHeight() / 2) - ((rectHeight + margin) / 2);
		g.setColor(Color.black);
		g.fillRect(startX, startY, rectWidth + margin, rectHeight + margin); 
		g.setColor(Color.blue);
		g.fillRect(startX + margin / 2, startY + margin / 2, progress, rectHeight); 
		
	}
	
	
	

}
