package com.mario.entities.mobs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.mario.Handler;
import com.mario.blocks.Block;
import com.mario.entities.Entity;
import com.mario.gfx.Animation;
import com.mario.gfx.Assets;

public class Goomba extends Mob{
	
	private Animation anim;

	public Goomba(Handler handler, float x, float y,int size, BufferedImage[] images) {
		super(handler, x, y, size * Block.BLOCKWIDTH, size * Block.BLOCKHEIGHT); 
		
		anim = new Animation(700, images);
	}

	
	
	
	
	public void tick() {
		
		xMove = 0;
		yMove = 0;
		
		
		anim.tick();
		think();
		move();
		

		
	}
	public void render(Graphics g) {
		if(xMove != 0) {
			g.drawImage(anim.getCurrentFrame(),(int)(this.x - handler.getxOffset()),(int)(this.y - handler.getyOffset()), width, height,null);
		}else {
			g.drawImage(Assets.goombaStill,(int)(this.x - handler.getxOffset()),(int)(this.y - handler.getyOffset()), width, height,null);
		}
			
		
		if(handler.getGameState().debug) {
			g.setColor(Color.pink);
			g.drawRect((int)(this.x - handler.getxOffset()),(int)(this.y - handler.getyOffset()), width, height); 
			g.setColor(Color.GREEN); 
			g.fillRect((int)(this.x - handler.getxOffset() + CollisionBox.x),(int)(this.y - handler.getyOffset() + CollisionBox.y), CollisionBox.width, CollisionBox.height); 
		}
		
	}
	
	
	
	public void think() {

		//first get a player
		Player p = handler.getGameState().player;
		
		//get the difference between my x and the players
		
		float difference = Math.abs(p.getX() - x);
		
		if(difference < 1000 && difference > 5) {
			
			//then go towards the player
			
			if(p.getX() > x) {
				//then we move to the right
				xMove = 1;
			}else {
				//then we move to the left
				xMove = -1;
				
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
			velocity = -7;
			canJump = false;
			isJumping = true;
		}
		
		
	}


}
