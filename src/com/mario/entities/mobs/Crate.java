package com.mario.entities.mobs;

import java.awt.Color;
import java.awt.Graphics;

import com.mario.Handler;
import com.mario.blocks.Block;
import com.mario.gfx.Assets;

public class Crate extends Mob{
	
	public Crate(Handler handler, float x, float y,int size) {
		super(handler, x, y, size * Block.BLOCKWIDTH, size * Block.BLOCKHEIGHT); 
		
	}

	
	
	
	
	public void tick() {
		move();
		
		xMove = 0;
		yMove = 0;
		
		
	}
	public void render(Graphics g) {
		g.drawImage(Assets.crate,(int)(this.x - handler.getxOffset()),(int)(this.y - handler.getyOffset()), width, height,null);
		
		if(handler.getGameState().debug) {
			g.setColor(Color.pink);
			g.drawRect((int)(this.x - handler.getxOffset()),(int)(this.y - handler.getyOffset()), width, height); 
			//g.setColor(Color.GREEN); 
			//g.fillRect((int)(this.x - handler.getxOffset() + CollisionBox.x),(int)(this.y - handler.getyOffset() + CollisionBox.y), CollisionBox.width, CollisionBox.height); 
		}
		
	}
	
	
	



}
