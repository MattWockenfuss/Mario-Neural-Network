package com.mario.blocks;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.mario.Handler;
import com.mario.entities.mobs.Player;

public class Block {
	protected static Handler handler;
	
	public static final int BLOCKWIDTH = 32;
	public static final int BLOCKHEIGHT = 32;
	
	protected int x,y;
	protected BufferedImage texture;
	protected Rectangle me;
	
	
	public Block(Handler handler,BufferedImage texture, int x,int y) {
		if(Block.handler == null)
			Block.handler = handler;
		
		this.texture = texture;
		this.x = x;
		this.y = y;
		
		me = new Rectangle(x * BLOCKWIDTH,y * BLOCKHEIGHT, BLOCKWIDTH, BLOCKHEIGHT);
		
	}
	
	public void tick() {
		
	}
	public void render(Graphics g) {
		g.drawImage(texture, (int)((x * BLOCKWIDTH) - handler.getGameCamera().getxOffset()), (int)((y * BLOCKHEIGHT) - handler.getGameCamera().getyOffset()), BLOCKWIDTH, BLOCKHEIGHT,null);
//		if(handler.getGameState().debug && isSolid()) {
//			g.setColor(Color.red);
//			g.fillRect((int)(x * Block.BLOCKWIDTH - handler.getxOffset()),(int)(y * Block.BLOCKHEIGHT - handler.getyOffset()), Block.BLOCKWIDTH, Block.BLOCKHEIGHT); 
//		}
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public boolean isSwimable() {
		return false;
	}
	public boolean isSolid() {
		return false;
	}

	
	public Player playerOnTop() {
		Player p = handler.getGameState().player;
		
		Rectangle r = new Rectangle((int)(p.getX() + p.getCollisionBox().x), (int)(p.getY() + p.getCollisionBox().y + 3), p.getCollisionBox().width, p.getCollisionBox().height);
				
		if(me.intersects(r))
			return p;
		
		
		return null;
	}
	public boolean playerCollidedWith() {
		
		Player p = handler.getGameState().player;
		Rectangle r = new Rectangle((int)(p.getX() + p.getCollisionBox().x),(int)(p.getY() + p.getCollisionBox().y),p.getCollisionBox().width,p.getCollisionBox().height); 
		

		
		if(r.intersects(me))
			return true;
		
		
		
		return false;
	}
	
	public boolean contains(float checkX, float checkY) {
		
		if(checkY > (y * BLOCKHEIGHT) && checkY < (y + 1) * BLOCKHEIGHT) {
			if(checkX > (x * BLOCKWIDTH) && checkX < (x + 1) * BLOCKWIDTH) {
				return true;
			}
		}
		
		return false;
	}
	
	
	
	/////////////////////////////Getters and Setters////////////////////////////////////
	public Rectangle getBounds() {
		return me;
	}
	
}
