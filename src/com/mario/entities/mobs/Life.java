package com.mario.entities.mobs;

import java.awt.Color;
import java.awt.Graphics;

import com.mario.Handler;
import com.mario.blocks.Block;
import com.mario.entities.Entity;
import com.mario.gfx.Assets;

public class Life extends Mob{
	
	public Life(Handler handler, float x, float y) {
		super(handler, x, y, Block.BLOCKWIDTH, Block.BLOCKHEIGHT); 
	}

	
	
	
	
	public void tick() {
		
		xMove = 0;
		yMove = 0;
		
		think();
		move();
		

		
	}
	public void render(Graphics g) {
		g.drawImage(Assets.life,(int)(this.x - handler.getxOffset()),(int)(this.y - handler.getyOffset()), width, height,null);
		
		if(handler.getGameState().debug) {
			g.setColor(Color.pink);
			g.drawRect((int)(this.x - handler.getxOffset()),(int)(this.y - handler.getyOffset()), width, height); 
		}
	}
	
	
	
	public void think() {

		//first get a player
		Player p = handler.getGameState().player;
		
		//get the difference between my x and the players
		
		float difference = Math.abs(p.getX() - x);
		
		if(difference < 400 && difference > 5) {
			
			//then go AWAY from the player
			
			if(p.getX() > x) {
				//then we move to the right
				xMove = -1;
			}else {
				//then we move to the left
				xMove = 1;
				
			}
			
			
		}
		
		
		//if they get stuck then jump
		//we want to check xCollision
		
		float dx = x + CollisionBox.x + xMove;// the place we want to move to
		
		Block preventingBlockLEFT = collisionWithBlock(dx, true,false);
		Block preventingBlockRIGHT = collisionWithBlock(dx, true,true);
		Entity preventingEntity = collisionWithEntity(dx,true);

		if(preventingBlockLEFT != null || preventingBlockRIGHT != null || preventingEntity != null && canJump) {
			//if we are going to hit something, then dont move instead jump
			
			xMove = 0;
//			velocity = -7;
//			canJump = false;
//			isJumping = true;
		}
		
		
	}


}
