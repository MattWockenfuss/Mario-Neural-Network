package com.mario.entities.mobs;

import java.awt.Color;
import java.awt.Graphics;

import com.mario.Handler;
import com.mario.utils.PlatformVector;

public class MovingPlatform extends Mob{
	
	private PlatformVector[] vectors;
	private boolean roundRobin;
	
	private int currentVectorIndex = 0;
	
	public MovingPlatform(Handler handler, int width, int height, PlatformVector[] vectors, boolean roundRobin) {
		super(handler, vectors[0].getStartX(), vectors[0].getStartY(),width,height);
		
		this.vectors = vectors;
		this.roundRobin = roundRobin;
		
		hasGravity = false;
		isHeavy = true;
	}



	public void tick() {
		
		PlatformVector v = vectors[currentVectorIndex];
		//System.out.println(currentVectorIndex + " , " + vectors.length); 

		
		//System.out.println(x + " , " + y + " ::   " + v.getEndX() + " , " + v.getEndY()); 
		if(Math.abs(x - v.getEndX()) <= 1 && Math.abs(y - v.getEndY()) <= 1) {
			
			x = v.getEndX();
			y = v.getEndY();
			
		
			
			
			if(currentVectorIndex + 1 == vectors.length) {
				
				//then we are at the max 
				if(roundRobin) {
					currentVectorIndex--;
				}else {
					currentVectorIndex = 0;

				}
				
				
			}else {
				currentVectorIndex++;
				
			}
			
			
		}else {
			xMove = v.getXSpeed();
			yMove = v.getYSpeed();
			//System.out.println("I AM A MOVING PLATFORM AND SPEEDS: " + xMove + " , " + velocity); 
		}
		

		
		
		
		
		
		
		
		
		
		
		move();
		
		
		
		
		
	}
	public void render(Graphics g) {
		int border = 2;
		
		g.setColor(Color.GRAY);
		g.fillRect((int)(x - handler.getxOffset()),(int)(y - handler.getyOffset()), width, height);
		
		g.setColor(Color.yellow);
		g.fillRect((int)(x - handler.getxOffset()) + border,(int)(y - handler.getyOffset() + border), width - (2 * border), height - (2 * border));
		
		if(handler.getGameState().debug) {
			
			
			for(PlatformVector v : vectors) {
				int Square = 10;
				
				float centerStartX = v.getStartX() + width / 2;
				float centerStartY = v.getStartY() + height / 2;
				
				float centerEndX = v.getEndX() + width / 2;
				float centerEndY = v.getEndY() + height / 2;
				
				g.setColor(Color.red); 
				g.drawRect((int)(centerStartX - (Square / 2) - handler.getxOffset()),(int)(centerStartY - (Square / 2) - handler.getyOffset()), Square, Square);
				g.fillRect((int)(centerEndX - (Square / 2) - handler.getxOffset()),(int)(centerEndY - (Square / 2) - handler.getyOffset()), Square, Square);
				g.setColor(Color.blue);
				g.drawLine((int)(centerStartX - handler.getxOffset()),(int)(centerStartY - handler.getyOffset()),(int)(centerEndX - handler.getxOffset()),(int)(centerEndY - handler.getyOffset())); 
				
				
				
				
				g.setColor(Color.red);
				g.fillRect((int)(x - handler.getxOffset()),(int)(y - handler.getyOffset()), width, height); 
				
			}
			
			
			
			
		}
		
		
	}
	
	
	
	public void setY(float y) {
		this.y = y;
		System.out.println("SET MOVING PLATFORM Y TO " + y); 
	}
	
	
	
}
