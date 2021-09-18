package com.mario.blocks.special;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.mario.blocks.Block;
import com.mario.blocks.common.AirBlock;
import com.mario.gfx.Animation;
import com.mario.gfx.Assets;

public class Coin extends Block{
	
	private static Animation animation = new Animation(50, Assets.coin); 
		
	
	public Coin(int x,int y) {
		super(null, Assets.coin[0],x,y);
		me = new Rectangle((int)(x * BLOCKWIDTH),(int)(y * BLOCKHEIGHT),BLOCKWIDTH,BLOCKHEIGHT);
	}
	
	
	@SuppressWarnings("deprecation")
	public void tick() {
		animation.tick();
		
		//check to see if the player collides with me, if they do then add it to coin bank
		
		if(playerCollidedWith()) {
			//then kill me and give 1 coin
			handler.getGameState().coins++;
			//System.out.println("COLLECTED COIN AT " + x + " , " + y); 
			handler.getWorld().setBlock(new AirBlock(handler, x, y), x, y); 
			
			if(handler.getGameState().coins >= 100) {
				handler.getGameState().coins = 0;
				handler.getGameState().player.setLives(handler.getGameState().player.getLivesLeft() + 1);
			}
			
			try {
				this.finalize();
			} catch (Throwable e) {
				e.printStackTrace();
			}
			
		}
		
	}
	public void render(Graphics g) {
		g.drawImage(animation.getCurrentFrame(),(int)(x * Block.BLOCKWIDTH - handler.getGameCamera().getxOffset()),(int)(y * Block.BLOCKHEIGHT - handler.getGameCamera().getyOffset()), BLOCKWIDTH, BLOCKHEIGHT, null);
		
		g.setColor(Color.yellow);
		//g.drawRect((int)(me.x - handler.getGameCamera().getxOffset()), (int)(me.y - handler.getGameCamera().getyOffset()), me.width, me.height);
		
		
	}

}
