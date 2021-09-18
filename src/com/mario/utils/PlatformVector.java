package com.mario.utils;

public class PlatformVector {
	
	private float startX,startY;
	private float endX, endY;
	
	private float xSpeed, ySpeed;
	
	private int ticks;
	
	
	public PlatformVector(float startX, float startY, float endX, float endY,int ticks) {
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		
		this.ticks = ticks;
		
		xSpeed = (endX - startX) / ticks;
		ySpeed = (endY - startY) / ticks;
	
	}



	
	
	
	
	
	
	
	
	
	
	
	
	
	/////////////////Getters and setters/////////////////////////////////
	
	public float getStartX() {
		return startX;
	}
	public void setStartX(float startX) {
		this.startX = startX;
	}
	public float getStartY() {
		return startY;
	}
	public void setStartY(float startY) {
		this.startY = startY;
	}
	public float getEndX() {
		return endX;
	}
	public void setEndX(float endX) {
		this.endX = endX;
	}
	public float getEndY() {
		return endY;
	}
	public void setEndY(float endY) {
		this.endY = endY;
	}
	public float getXSpeed() {
		return xSpeed;
	}
	public float getYSpeed() {
		return ySpeed;
	}
	public int getTicks() {
		return ticks;
	}
	
	
}
