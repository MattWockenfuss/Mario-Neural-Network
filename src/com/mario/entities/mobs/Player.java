package com.mario.entities.mobs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.mario.Handler;
import com.mario.blocks.Block;
import com.mario.entities.Entity;
import com.mario.gfx.Animation;
import com.mario.gfx.Assets;

public class Player extends Mob{
	
	private int livesLeft = 5;
	
	//Animation stuff
	private Animation left,right;
	private int animationSpeed = 100;
	public static BufferedImage currentAnimationState; //used so we can draw the player in the inventory
	private float lastX = 0;
	protected float speed;	
	private Rectangle crouchCollisionBox,normalCollisionBox;
	
	private boolean isOnJumpPad = false;
	private boolean isCrouching = false;
	
	public Player(Handler handler,float x, float y, int health) {
		super(handler,x, y,(int)(Assets.playerRight[0].getWidth() * 2.5), (int)(Assets.playerRight[0].getHeight() * 2.5));
		
		CollisionBox.x = 6; //multiplied by scale
		CollisionBox.y = 10;
		CollisionBox.width = 25;
		CollisionBox.height = 60;
		
		handler.getWorld().getEntityManager().addEntity(this);
		
		
		left = new Animation(animationSpeed, Assets.playerLeft);
		right = new Animation(animationSpeed, Assets.playerRight);
		
		crouchCollisionBox = new Rectangle(6,30,25,40);
		normalCollisionBox = CollisionBox;
	}
	

	public void tick() {
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);

		
		
	}
	public void render(Graphics g) {
		
		// draw the player
		
		if(handler.getGameState().debug) {
			renderDebug(g);
		}else {
			g.drawImage(currentAnimationState, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), (int)(width),(int) (height), null);
			
		}
		
		
		
	}
	public void renderDebug(Graphics g) {
		
		//draw Collision Box
		g.setColor(Color.red);
		g.fillRect((int) (x - handler.getGameCamera().getxOffset()) + CollisionBox.x,(int) (y - handler.getGameCamera().getyOffset()) + CollisionBox.y, CollisionBox.width, CollisionBox.height);
		
		
		//draw Sprite Box
		//player
		g.setColor(Color.pink);
		//g.drawRect((int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height - 1); 
		
		//drawCollisionCube(g, true, true, false, false); 

		

		//block below us
		//Point p = handler.getWorld().getAbsPos(x + CollisionBox.x + CollisionBox.width / 2, y + CollisionBox.y + CollisionBox.height + 10);
		//g.drawRect((int)(p.x * Block.BLOCKWIDTH - handler.getxOffset()),(int)(p.y * Block.BLOCKHEIGHT - handler.getyOffset()), Block.BLOCKWIDTH, Block.BLOCKHEIGHT);
		
		
		
//		
//		g.setColor(Color.magenta);
//		Rectangle myBounds = new Rectangle((int) (x + CollisionBox.x + xMove - handler.getxOffset()), (int)( y + CollisionBox.y - handler.getyOffset()), CollisionBox.width, CollisionBox.height);
//		g.drawRect(myBounds.x, myBounds.y, myBounds.width, myBounds.height);
//		
//		
		int dy = (int)(y + CollisionBox.y + yMove);
		Rectangle myBounds = new Rectangle((int) x + CollisionBox.x, (int) dy, CollisionBox.width, CollisionBox.height);
		
		g.setColor(Color.yellow);
		g.drawRect((int)(myBounds.x - handler.getxOffset()), (int)(myBounds.y - handler.getyOffset()), myBounds.width, myBounds.height);
		
		
		

		
	}

	
	
	

	private void getInput() {
		xMove = 0;
		yMove = 0;
		
		if(handler.getKeyManager().w)
			yMove = -speed;
		if(handler.getKeyManager().a)
			xMove = -speed;
		if(handler.getKeyManager().d)
			xMove = speed;
		
		isCrouching = handler.getKeyManager().s;
		
		
		if(handler.getKeyManager().shift) {//Sprint
			speed = 4.5f;
			animationSpeed = 50; 
			
		}else if(handler.getKeyManager().left_control){//The Super Sprint button
			speed = 20.0f;
			animationSpeed = 20; 
			
		}else {
			speed = Entity.DEFAULT_SPEED;
			animationSpeed = 100; 
		}
		
		if(velocity == 0 && !isCrouching) {//if we are standing on a solid block and not crouching
			if(handler.getKeyManager().space && canJump) {
				//if we are jumping on jumppad than super jump
				if(isOnJumpPad) {
					velocity = -23;
				}else {
					velocity = -9;
				}
				
				isJumping = true;
				canJump = false;
			}
		}
		

		
		 //this is to see which direction we last were 
		if(!isCrouching) {
			
			if(xMove < 0) {
				currentAnimationState = left.getCurrentFrame();
				lastX = xMove;
			}else if(xMove > 0) {
				currentAnimationState = right.getCurrentFrame();
				lastX = xMove;
			}else {
				if(lastX < 0)
					currentAnimationState = Assets.playerLeft[0];
				else
					currentAnimationState = Assets.playerRight[0];
			}
			
		}

		
		//if we are moving in the y though, then jump
		
		if(!canJump) {
			if(lastX < 0)
				currentAnimationState = Assets.jumpLeft;
			else
				currentAnimationState = Assets.jumpRight;
		}
		
		
		
		if(handler.getKeyManager().s) {
			currentAnimationState = Assets.playerCrouch[1];
			this.CollisionBox = crouchCollisionBox;
		} else {
			this.CollisionBox = normalCollisionBox;
		}
			
		
		if(isCrouching) {
			
			//we are crouching, depending on direction, set crouch
			if(lastX < 0)
				currentAnimationState = Assets.playerCrouch[0];
			else
				currentAnimationState = Assets.playerCrouch[1];
			
			this.CollisionBox = crouchCollisionBox;
			
			xMove = 0;
			yMove = 0;
			
		}else {
			this.CollisionBox = normalCollisionBox;
		}
		
		
		

		
		
		left.setSpeed(animationSpeed);
		right.setSpeed(animationSpeed);
		
		
		
		
		

		
		left.tick();
		right.tick();
		
		isOnJumpPad = false;
		
	}

	
//////////////////////////////////////////////////////////GETTERS AND SETTERS////////////////////////////////////////////////////

	public void setIsOnJumpPad(boolean isOnJumpPad) {
		this.isOnJumpPad = isOnJumpPad;
	}
	public boolean isOnJumpPad() {
		return isOnJumpPad;
	}
	public void setLives(int lives) {
		this.livesLeft = lives;
	}
	public int getLivesLeft() {
		return livesLeft;
	}
	
	
	
	
	public void drawCollisionCube(Graphics g,boolean drawRight, boolean drawLeft, boolean drawDown, boolean drawUp) {
		
		
		/////////////////////////////////////////////RIGHT/////////////////////////////////////////////
		if(drawRight) {
			
			int dx = (int)(x + CollisionBox.x + CollisionBox.width + xMove);
			int height = (int) Math.round(CollisionBox.height / Block.BLOCKHEIGHT + 0.5); 				
			int distanceFromTopToBottom  = Block.BLOCKHEIGHT - ((int)(y + CollisionBox.y) % Block.BLOCKHEIGHT);
			if(distanceFromTopToBottom < (CollisionBox.height % Block.BLOCKHEIGHT))
				height++;
			
			Block[] blocks = new Block[height]; 
			 
			for(int i = 0;i < height;i++) {
				Point p = handler.getWorld().getAbsPos(dx,y + CollisionBox.y + (i * Block.BLOCKHEIGHT));
				Block b = handler.getWorld().getBlock(p.x, p.y); 
				if(b.getBounds().contains(x,y + CollisionBox.y + CollisionBox.height + 1)) {

				}else {
					blocks[i] = b;
				}
			}
			
			
			
			for(Block b : blocks) {
				if(b!= null && b.isSolid()) {
					g.drawRect((int) (b.getX() * Block.BLOCKWIDTH - handler.getGameCamera().getxOffset()), (int) (b.getY() * Block.BLOCKHEIGHT - handler.getGameCamera().getyOffset()), Block.BLOCKWIDTH, Block.BLOCKHEIGHT); 
				}
			}
			

			

			
			
			
			
			
		}
		
		
		if(drawLeft) {
			
			g.setColor(Color.MAGENTA);
			
			int dx = (int)(x + CollisionBox.x -2);
			int height = (int) Math.round(CollisionBox.height / Block.BLOCKHEIGHT + 0.5); 				
			int distanceFromTopToBottom = Block.BLOCKHEIGHT - ((int)(y + CollisionBox.y) % Block.BLOCKHEIGHT);
			
			if(distanceFromTopToBottom < (CollisionBox.height % Block.BLOCKHEIGHT))
				height++;
			if(velocity == 0)
				height --;
			
			Block[] right = new Block[height]; 
			
			for(int i = 0;i < height;i++) {
				Point p = handler.getWorld().getAbsPos(dx, y + CollisionBox.y + (i * Block.BLOCKHEIGHT));
				right[i] = handler.getWorld().getBlock(p.x, p.y); 
			}
			
			for(Block b : right) {
				g.drawRect((int) (b.getX() * Block.BLOCKWIDTH - handler.getGameCamera().getxOffset()), (int) (b.getY() * Block.BLOCKHEIGHT - handler.getGameCamera().getyOffset()), Block.BLOCKWIDTH, Block.BLOCKHEIGHT); 
			}
			
			
			
			
			
		}
		
		
		
		if(drawUp) {
			
			g.setColor(Color.red);
			int length2 = (int) Math.round(CollisionBox.width / Block.BLOCKWIDTH + 0.5); 				
			int distanceFromFarLeftToPost2  = Block.BLOCKWIDTH - ((int)(x + CollisionBox.x) % Block.BLOCKWIDTH);
			if(distanceFromFarLeftToPost2 < (CollisionBox.width % 32))
				length2++;
			
			Block[] up = new Block[length2]; 
			
			for(int i = 0;i < length2;i++) {
				Point p3 = handler.getWorld().getAbsPos(x + CollisionBox.x + (i * Block.BLOCKWIDTH), y + CollisionBox.y - 10);
				up[i] = handler.getWorld().getBlock(p3.x, p3.y); 
			}
			
			for(Block b : up) {
				g.drawRect((int) (b.getX() * Block.BLOCKWIDTH - handler.getGameCamera().getxOffset()), (int) (b.getY() * Block.BLOCKHEIGHT - handler.getGameCamera().getyOffset()), Block.BLOCKWIDTH, Block.BLOCKHEIGHT); 
			}
			
			
		}

		

		if(drawDown) {
			g.setColor(Color.DARK_GRAY);
			
			
			int length = (int) Math.round(CollisionBox.width / Block.BLOCKWIDTH + 0.5); 
			//we need to see how long we need to be						//chungus but 0 in theory
			int distanceFromFarLeftToPost  = Block.BLOCKWIDTH - ((int)(x + CollisionBox.x) % Block.BLOCKWIDTH);
			//if this number is greater than CollisionBox.width % 32, than we dip into next block
			if(distanceFromFarLeftToPost < (CollisionBox.width % 32))
				length++;
			//if distanceFromFarLeftToPost is less than whats left over, then more will spill into the block at the end
			
			Point[] blockCollisions = new Point[length]; 
			
			for(int i = 0;i < length;i++) {
				blockCollisions[i] = handler.getWorld().getAbsPos(x + CollisionBox.x + (i * Block.BLOCKWIDTH), y + CollisionBox.y + CollisionBox.height + 10);
				Block b = handler.getWorld().getBlock(blockCollisions[i].x, blockCollisions[i].y);
				g.drawRect((int)(b.getX() * Block.BLOCKWIDTH - handler.getGameCamera().getxOffset()),(int)(b.getY() * Block.BLOCKHEIGHT - handler.getGameCamera().getyOffset()),Block.BLOCKWIDTH, Block.BLOCKHEIGHT); 
			}
		}
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
}
