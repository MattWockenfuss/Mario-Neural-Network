package com.mario.entities.mobs;

import com.mario.Handler;
import com.mario.entities.Entity;

public abstract class Mob extends Entity{
	
	protected boolean isSwimming = false;

	

	public Mob(Handler handler,float x, float y,int width,int height) {
		super(handler,x, y,width,height);
	}

	
	
	
	
	
	
	
	
	
	
	/////////////////////////////////////GETTERS AND SETTERS////////////////////////////////////////////
	public boolean isSwimming() {
		return isSwimming;
	}
	
	

}
