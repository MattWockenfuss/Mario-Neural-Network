package com.mario.blocks.special;

import java.awt.Graphics;

import com.mario.blocks.Block;
import com.mario.gfx.Animation;
import com.mario.gfx.Assets;

public class Water extends Block{
	
	private static Animation animation = new Animation(50,Assets.water);
	
	public Water(int x,int y) {
		super(null, animation.getCurrentFrame(), x, y);
	}
	
	public void tick() {
		animation.tick();
	}
	public void render(Graphics g) {
		g.drawImage(animation.getCurrentFrame(),(int)(x * Block.BLOCKWIDTH - handler.getGameCamera().getxOffset()),(int)(y * Block.BLOCKHEIGHT - handler.getGameCamera().getyOffset()), BLOCKWIDTH, BLOCKHEIGHT, null);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//////////////////////////////////////////////////Getters and Setters///////////////////////////
	public boolean isSwimable() {
		return true;
	}
	public boolean isSolid() {
		return false;
	}
	

}
