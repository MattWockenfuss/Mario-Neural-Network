package com.mario.states;

import java.awt.Graphics;

import com.mario.Handler;

public abstract class State {
	protected Handler handler;
	public State(Handler handler) {
		this.handler = handler;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	
}
