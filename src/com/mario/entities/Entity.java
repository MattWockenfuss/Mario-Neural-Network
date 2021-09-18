package com.mario.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.mario.Handler;
import com.mario.blocks.Block;

public abstract class Entity {
	
	protected Handler handler;
	
	protected float xMove,yMove;
	protected float x, y;
	protected int width,height;
	protected Rectangle CollisionBox;
	
	protected float velocity;
	public static final float DEFAULT_SPEED = 5.0F;
	protected final static float ACCELERATION = 0.40f;
	protected final static float TERMINAL_VELOCITY = 30;
	
	//Might add a mass variable to see if we can push big things, etc
	
	////////////////////////////Entity Flags
	
	protected boolean active = true;
	
	protected boolean hasGravity = true;
	protected boolean collidesWithOthers = true;
	protected boolean isHeavy = false;
	
	protected boolean isJumping = false;
	protected boolean canJump = true;
	protected boolean isDeadly = false;
	protected boolean canDie = false;
	

	
	
	
	
	
	public Entity(Handler handler,float x,float y,int width,int height) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		CollisionBox = new Rectangle(0,0,width,height);
		
	}
	
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void move() {
		if(hasGravity) {
			calculateGravity();
		}
			
		if(collidesWithOthers) {
			moveY();
			moveX();
		}else {
			x += xMove;
			y += yMove;
		}

		//check to see if outside map
		if(x < 0 || y < 0 || x > handler.getWorld().getWidth() * Block.BLOCKWIDTH || y > handler.getWorld().getHeight() * Block.BLOCKHEIGHT) {
			x = handler.getWorld().getSpawnX();
			y = handler.getWorld().getSpawnY();
		}
		
		
		
	}
	
	public void moveX() {
		
		if(xMove > 0) {//Moving Right

			
			
			
//			if((preventingBlock != null) && !(preventingEntity != null)) {//collided with block but not entity
//				
//				
//			}else if(!(preventingBlock != null) && (preventingEntity != null)) { //collided with entity but not block
//
//				
//			}else if((preventingBlock != null) && (preventingEntity != null)) {//collided with both
//				//then see which is closer
//				
//				if(x - preventingEntity.x > (x - preventingBlock.getX() * Block.BLOCKWIDTH)) {
//					
//					//then we collided with the entity first
//					x = (int)(preventingEntity.x + preventingEntity.getCollisionBox().x - ((width - CollisionBox.x - CollisionBox.width) + CollisionBox.width));
//
//				}else {
//					//then we collided with the block or for some reason they are the same
//					x = preventingBlock.getX() * Block.BLOCKWIDTH - (CollisionBox.width + CollisionBox.x);
//				}
//				
//				
//				
//			}else {//collided with niether
//				x += xMove;
//			}
			
			
			
			
			float dx = x + CollisionBox.x + xMove;// the place we want to move to
			
			Block preventingBlock = collisionWithBlock(dx, true,true);
			Entity preventingEntity = collisionWithEntity(dx,true);
			
			
		
   			
   			if(preventingBlock != null) {
   				//then we snap to it
   				x = preventingBlock.getX() * Block.BLOCKWIDTH - (width - CollisionBox.x);
   			}else {
   				
   				//check to see if we hit an entity
   				if(preventingEntity != null) {
   					x = (int)(preventingEntity.x + preventingEntity.getCollisionBox().x - ((width - CollisionBox.x - CollisionBox.width) + CollisionBox.width));
   					preventingEntity.setxMove(1);
   				}else {
   					x += xMove;
   				}
   				
   				
   			}
			
			
		}else if(xMove < 0) {//Moving Left////////////////////////////////////////////////////////////
			
			float dx = x + CollisionBox.x + xMove;// the place we want to move to
			
			Block preventingBlock = collisionWithBlock(dx, true,false);
			Entity preventingEntity = collisionWithEntity(dx,true);
			
			
			if(preventingBlock != null) {
   				//then we snap to it
   				x = preventingBlock.getX() * Block.BLOCKWIDTH + Block.BLOCKWIDTH - (CollisionBox.x);
   			}else {
   				
   				//check to see if we hit an entity
   				if(preventingEntity != null) {
   					x = (int)(preventingEntity.x + preventingEntity.getCollisionBox().x + preventingEntity.getCollisionBox().width - (this.CollisionBox.x));
   					//preventingEntity.setxMove(xMove); 
   					preventingEntity.setxMove(-1);
   				}else {
   					x += xMove;
   				}
   				
   				
   			}
			
			
		}
		

		
		
		
	}
   	public void moveY() {
   		
   		if(yMove > 0) { //////////////////////////////////////////////////////////////////////////////////
   		
   			
   	   		int dy = (int)(y + CollisionBox.y + yMove);
   			
   			Block preventingBlock = collisionWithBlock(dy, false,true);
   			Entity preventingEntity = collisionWithEntity(dy,false);
   	   		
   	   		
   			
   			if(preventingBlock != null) {
   				//then we snap to it
   	   			y = preventingBlock.getY() * Block.BLOCKHEIGHT - (CollisionBox.height + CollisionBox.y);
   	   			velocity = 0;
   				
   	   			canJump = true;
   				isJumping = false;
   			}else {
   				
   				//check to see if we hit an entity
   				if(preventingEntity != null) {
   				
//   					if(isHeavy) {//i am heavy
//   						if(preventingEntity.isHeavy) {//and they are heavy
//   							//then do nothing?
//   							//y = preventingEntity.getY() - (CollisionBox.y + CollisionBox.height);
//   						}else {
//   							//i am heavy and they arent
//   							//then they get moved up with me
//   							
//   							preventingEntity.setY(y + CollisionBox.y + CollisionBox.height - (preventingEntity.getCollisionBox().y) + 1);
//   							preventingEntity.setyMove(yMove); 
//   							y += yMove;
//   						}
//   						
//   						
//   						
//   					}else {//i am not heavy
//   						
//   						if(preventingEntity.isHeavy) {//but they are
//   							//then we stop
//   							y = preventingEntity.getY() - (CollisionBox.y + CollisionBox.height); 
//   						}else {//niether of us are heavy
//   							
//   							preventingEntity.setY(y + CollisionBox.y + CollisionBox.height - (preventingEntity.getCollisionBox().y) + 1);
//   							preventingEntity.setyMove(yMove); 
//   							y += yMove;
//   						}
//   						
//   						
//   						
//   					}
   					
   					
   					
   					//moving in the down direction we hit something, what do we do
   					
   					if(isHeavy) {
   						if(preventingEntity.isHeavy) {
   							//if we are both heavy, then we both must stop
   							preventingEntity.setyMove(0); 
   							yMove = 0;
   						}else {
   							//i am heavy and they are not, we are moving down
   							if(preventingEntity.getyMove() > 0) {//we are both moving down, i am heavy and they are not
   								//i mate to him, but dont push him
   								
   								y = preventingEntity.y + preventingEntity.getCollisionBox().y - (CollisionBox.y + CollisionBox.height);
   								canJump = true;
   		   	   					isJumping = false;
   								
   							}else if(preventingEntity.getyMove() < 0) {
   								//they are moving up we are moving down, we are heavy they are not,
   								
   								//stop them from moving, we mate, and chill
   								preventingEntity.setyMove(0);
   								y = preventingEntity.y + preventingEntity.getCollisionBox().y - (CollisionBox.y + CollisionBox.height);
   								canJump = true;
   		   	   					isJumping = false;
   							}
   						}
   					}else {
   						if(preventingEntity.isHeavy) {
								y = preventingEntity.y + preventingEntity.getCollisionBox().y - (CollisionBox.y + CollisionBox.height);
								canJump = true;
		   	   					isJumping = false;
   						}else {
								y = preventingEntity.y + preventingEntity.getCollisionBox().y - (CollisionBox.y + CollisionBox.height);
								canJump = true;
		   	   					isJumping = false;
		   	   					preventingEntity.setyMove(yMove);
   						}
   					}
   					
   					
   					
   					if(preventingEntity.isHeavy) {
   						y = preventingEntity.y + preventingEntity.getCollisionBox().y - (CollisionBox.y + CollisionBox.height);
   	   					canJump = true;
   	   					isJumping = false;
   					}else {
   						//must check for blocks underneath this thang
   						//y += yMove;
   						preventingEntity.setyMove(yMove); 
   					}
   						
   					
   					
   					
   					
   					
   					
   					
   					

   				}else {
   					y += yMove;
   				}
   				
   				
   			}
   			
   			
   			
   		}else if(yMove < 0) {////////////////////////////////////////////////////////////////////////////////// UP
   			
   			
   	   		int dy = (int)(y + CollisionBox.y + yMove);
   			
   			Block preventingBlock = collisionWithBlock(dy, false,false);
   			Entity preventingEntity = collisionWithEntity(dy,false);
   	   		
   	   		
   			
   			if(preventingBlock != null) {
   				//then we snap to it
   				y = (preventingBlock.getY() * Block.BLOCKHEIGHT + Block.BLOCKHEIGHT - CollisionBox.y);
   				velocity = 0;
   			}else {
   				
   				//check to see if we hit an entity
   				if(preventingEntity != null) {
   					
   					if(isHeavy) {//i am heavy
   						if(preventingEntity.isHeavy) {//and they are heavy
   							//then do nothing?
   							y = preventingEntity.getY() - (CollisionBox.y + CollisionBox.height);
   						}else {
   							//i am heavy and they arent
   							//then they get moved up with me
   							
   							preventingEntity.setY(y + CollisionBox.y - (preventingEntity.getCollisionBox().y + preventingEntity.getCollisionBox().height + 1));
   							preventingEntity.setyMove(yMove); 
   							y += yMove;
   						}
   						
   						
   						
   					}else {//i am not heavy
   						
   						if(preventingEntity.isHeavy) {//but they are
   							//then we stop
   							y = (preventingEntity.y + preventingEntity.getCollisionBox().y + preventingEntity.getCollisionBox().height - CollisionBox.y); 
   						}else {//niether of us are heavy
   							
   							preventingEntity.setY(y + CollisionBox.y - (preventingEntity.getCollisionBox().y + preventingEntity.getCollisionBox().height + 1));
   							preventingEntity.setyMove(yMove);
   							y += yMove;
   						}
   						
   						
   						
   					}
   					
   					velocity = 0;
   					
   				}else {
   					y += yMove;
   				}
   				
   				
   			}
   			
   			
   			
   			
   			
   		}//////////////////////////////////////////////////////////////////////////////////
	}
   	
  
   	
   	
	public void calculateGravity() {
		
		int dy = (int)(y + CollisionBox.y + 1);
		
		Block preventingBlock = collisionWithBlock(dy, false, true);
		Entity preventingEntity = collisionWithEntity(dy,false);
		
		if((preventingBlock == null) && (preventingEntity == null)) {
			
			if(velocity < TERMINAL_VELOCITY) {
				velocity += ACCELERATION;
			}
					

		}else {
			if(!isJumping)
				velocity = 0;
		}
		
		yMove = velocity;	
	}
	

	
	  
	
	
	
	

	protected Block collisionWithBlock(float d, boolean  trueLEFTorRIGHT_falseUPorDOWN, boolean true_RIGHT_OR_DOWN_FALSE_LEFT_OR_UP) { 
		
		// this function takes the bounds of the entity calling it, d which is dx or dy
		// depending on boolean , and returns the closest Block on the axis
		
		if(trueLEFTorRIGHT_falseUPorDOWN) {
			Rectangle myBounds = new Rectangle((int) d, (int) y + CollisionBox.y, CollisionBox.width, CollisionBox.height);
			ArrayList<Block> collidingBlocks = new ArrayList<Block>();
			
			if(true_RIGHT_OR_DOWN_FALSE_LEFT_OR_UP) {
				d += CollisionBox.width;
			}
			
			//get the end point blocks, get their ys and every block inbetween
			
			Block startBlockY = handler.getWorld().getBlock(d, y + CollisionBox.y);
			Block endBlockY = handler.getWorld().getBlock(d, y + CollisionBox.y + CollisionBox.height);
			
			if(startBlockY == null || endBlockY == null)
				return null;
			
			int startY = startBlockY.getY();
			int endY = endBlockY.getY();
			
			//get every Block inbetween
			
			for(int y = startY; y <= endY; y++) {
				Block b = handler.getWorld().getBlock(d, y * Block.BLOCKHEIGHT);
				//we only want to add the blocks if we actually collide with them
				
				if(b != null && b.isSolid() && myBounds.intersects(b.getBounds())) {
					collidingBlocks.add(b);
				}	
			}
			//okay so now we have an arraylist of all the blocks we are colliding with
			
			//if we dont have any blocks, then return none
			if(collidingBlocks.size() == 0)
				return null;
			//otherwise just return one of the blocks we ran into, because they are grided we dont need to check which is closest as all will snap to same x value
			return collidingBlocks.get(0);
			
			
			
			
			
		}else {	//UP AND DOWN AXIS
			Rectangle myBounds = new Rectangle((int) (x + CollisionBox.x), (int) d, CollisionBox.width, CollisionBox.height);
			ArrayList<Block> collidingBlocks = new ArrayList<Block>();
			
			if(true_RIGHT_OR_DOWN_FALSE_LEFT_OR_UP) {
				d += CollisionBox.height;
			}
			
			//get the end point blocks, get their Xs and every block inbetween
			
			Block startBlockX = handler.getWorld().getBlock(x + CollisionBox.x, d);
			Block endBlockX = handler.getWorld().getBlock(x + CollisionBox.x + CollisionBox.width, d);
			
			if(startBlockX == null || endBlockX == null)
				return null;
			
			int startX = startBlockX.getX();
			int endX = endBlockX.getX();
			
			for(int x = startX; x <= endX; x++) {
				Block b = handler.getWorld().getBlock(x * Block.BLOCKWIDTH, d);
				
				if(b != null && b.isSolid() && myBounds.intersects(b.getBounds())) {
					collidingBlocks.add(b);
				}
				
				
			}
			
			if(collidingBlocks.size() == 0) {
				return null;
			}
				
			//otherwise just return one of the blocks we ran into, because they are grided we dont need to check which is closest as all will snap to same x value
			return collidingBlocks.get(0);
			
			
			
		}
		
	}
	protected Entity collisionWithEntity(float d,boolean trueLEFTorRIGHT_falseUPorDOWN) {

		// this function takes the bounds of the entity calling it, d which is dx or dy
		// depending on boolean , and returns the closest Entity on the axis
		
		if (trueLEFTorRIGHT_falseUPorDOWN) {		// Left and Right Axis (x Axis)
			
			Rectangle myBounds = new Rectangle((int) d, (int) y + CollisionBox.y, CollisionBox.width, CollisionBox.height);
			ArrayList<Entity> collidingEntities = new ArrayList<Entity>();

			// if we collide with an entity add it to the list

			for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
				// we also want to check and see if this entity is probably the entity calling
				// this function,
				if (e.getX() == x && e.getY() == y) {
					continue;
				}

				if (myBounds.intersects(new Rectangle((int) (e.getX() + e.getCollisionBox().x), (int) (e.getY() + e.getCollisionBox().y), e.getCollisionBox().width, e.getCollisionBox().height)))
					collidingEntities.add(e);
			}

			// no entities
			if (collidingEntities.size() == 0)
				return null;

			// okay so now all the entities we will collide with are in this arraylist, now
			// see which one is the closest
			Entity closestEntity = collidingEntities.get(0);
			float distanceBetweenRecord = (Math.abs((x + CollisionBox.x) - (closestEntity.getX() + closestEntity.getCollisionBox().x)));
			// remeber we are checking on the x axis

			for (Entity e : collidingEntities) {
				float distanceBetween = (Math.abs(x - e.getX()));
				if (distanceBetween > distanceBetweenRecord) {
					distanceBetweenRecord = distanceBetween;
					closestEntity = e;
				}
			}

			// now we are returning null if no entities and we will return the closest on
			// the x axis (since boolean is true)
			return closestEntity;
		} else {

			// Up and Down Axis (y Axis)

			Rectangle myBounds = new Rectangle((int) (x + CollisionBox.x), (int) d, CollisionBox.width, CollisionBox.height);
			
			ArrayList<Entity> collidingEntities = new ArrayList<Entity>();

			// if we collide with an entity add it to the list

			for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
				// we also want to check and see if this entity is probably the entity calling
				// this function,
				if (e.getX() == x && e.getY() == y && this.getClass() == e.getClass()) {
					continue;
				}

				if (myBounds.intersects(new Rectangle((int) (e.getX() + e.getCollisionBox().x), (int) (e.getY() + e.getCollisionBox().y), e.getCollisionBox().width, e.getCollisionBox().height))) {
					collidingEntities.add(e);
				}
					
			}

			// no entities
			if (collidingEntities.size() == 0)
				return null;

			// okay so now all the entities we will collide with are in this arraylist, now
			// see which one is the closest
			Entity closestEntity = collidingEntities.get(0);
			float distanceBetweenRecord = (Math.abs(y - closestEntity.getY()));
			// remeber we are checking on the x axis

			for (Entity e : collidingEntities) {
				float distanceBetween = (Math.abs(y - e.getY()));
				if (distanceBetween > distanceBetweenRecord) {
					distanceBetweenRecord = distanceBetween;
					closestEntity = e;
				}

			}

			// now we are returning null if no entities and we will return the closest on
			// the x axis (since boolean is true)
			return closestEntity;
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	//////////////////////////////////////////////GETTERS AND SETTERS///////////////////////////////////////////////////
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	public Rectangle getCollisionBox() {
		return CollisionBox;
	}
	
	
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public boolean hasGravity() {
		return hasGravity;
	}
	public void setGravity(boolean hasGravity) {
		this.hasGravity = hasGravity;
	}

	public boolean isJumping() {
		return isJumping;
	}
	public void setJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}
	public boolean canJump() {
		return canJump;
	}
	
	
	
	public float getxMove() {
		return xMove;
	}
	public void setxMove(float xMove) {
		this.xMove = xMove;
	}
	public float getyMove() {
		return yMove;
	}
	public void setyMove(float yMove) {
		this.yMove = yMove;
	}
	public float getVelocity() {
		return velocity;
	}
	public void setVelocity(float velocity) {
		this.velocity = velocity;
	}

	
	
	
	
}
