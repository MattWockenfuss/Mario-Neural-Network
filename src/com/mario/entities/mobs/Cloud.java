package com.mario.entities.mobs;

import java.awt.Color;
import java.awt.Graphics;

import com.mario.Handler;
import com.mario.blocks.Block;
import com.mario.gfx.Assets;
import com.mario.utils.Utils;

public class Cloud extends Mob{
	
	private int currentIndex = 0;

	public Cloud(Handler handler) {
		super(handler, 0, 0, 1, 1);
		
		collidesWithOthers = false;
		hasGravity = false;
		restart();
	}

	
	
	
	
	public void tick() {
		
		xMove = (float) 0.2;
		yMove = 0;
		
		
		move();
		
		if(y > handler.getWorld().getWidth() * Block.BLOCKWIDTH - width) {
			restart();
		}
		
	}
	public void render(Graphics g) {
		g.drawImage(Assets.clouds[currentIndex],(int)(this.x - handler.getxOffset()),(int)(this.y - handler.getyOffset()), Assets.clouds[currentIndex].getWidth(), Assets.clouds[currentIndex].getHeight(),null);
		
		if(handler.getGameState().debug) {
			g.setColor(Color.pink);
			g.drawRect((int)(this.x - handler.getxOffset()),(int)(this.y - handler.getyOffset()), width, height); 
		}
		
	}
	
	public void restart() {
		//this method is called when the cloud reaches the end of the map and needs to be redrawn
		currentIndex = Utils.randomNumber(0, 4);
		x = Utils.randomNumber(0, 500);
		y = Utils.randomNumber(0, 500);
	}
	


}
