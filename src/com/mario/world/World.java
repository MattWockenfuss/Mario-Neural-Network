package com.mario.world;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import com.mario.Handler;
import com.mario.blocks.Block;
import com.mario.blocks.common.AirBlock;
import com.mario.blocks.common.Dirt;
import com.mario.blocks.common.Fence;
import com.mario.blocks.common.Grass;
import com.mario.blocks.common.bricks.BlueBrick;
import com.mario.blocks.common.bricks.BlueCrackedBrick;
import com.mario.blocks.common.bricks.BlueFancyBrick;
import com.mario.blocks.common.bricks.RedBrick;
import com.mario.blocks.common.bricks.RedCrackedBrick;
import com.mario.blocks.common.bricks.RedFancyBrick;
import com.mario.blocks.common.bricks.WhiteBrick;
import com.mario.blocks.common.bricks.WhiteCrackedBrick;
import com.mario.blocks.common.bricks.WhiteFancyBrick;
import com.mario.blocks.special.Coin;
import com.mario.blocks.special.JumpPad;
import com.mario.blocks.special.Lava;
import com.mario.blocks.special.QuestionBlock;
import com.mario.blocks.special.Water;
import com.mario.entities.EntityManager;
import com.mario.entities.mobs.Cloud;
import com.mario.entities.mobs.Crate;
import com.mario.entities.mobs.Goomba;
import com.mario.entities.mobs.Life;
import com.mario.entities.mobs.MovingPlatform;
import com.mario.entities.mobs.Player;
import com.mario.gfx.Assets;
import com.mario.gfx.ImageLoader;
import com.mario.utils.PlatformVector;
import com.mario.utils.Utils;

public class World {
	
	private Handler handler;
	
	private String name;
	private Block[][] blocks;
	private int width, height;
	private int spawnX,spawnY;
	
	private EntityManager entityManager;
	private BufferedImage blockImage;
    
    
    
  
    
    public World(Handler handler, String path) {
        this.spawnX = 0;
        this.spawnY = 0;
        this.handler = handler;
        
        entityManager = new EntityManager(handler);
        
        preLoad(path);
        
      }
    
    public void tick() {
    	
    	//tick all blocks regardless if they can be seen
    	for(int y = 0;y < height;y++) {
        	for(int x = 0;x < width;x++) {
        		
        		blocks[x][y].tick();
        		
        	}
    	}
    	
        entityManager.tick();
    }
    public void render(Graphics g) {
    	
    	g.setColor(Assets.backgroundBlue);
    	g.fillRect(0, 0, handler.getWidth(), handler.getHeight()); 
    	
    	int startX = (int)(handler.getGameCamera().getxOffset() / Block.BLOCKWIDTH);
    	int endX = (int)((handler.getGameCamera().getxOffset() + handler.getWidth()) / Block.BLOCKWIDTH) + 1;
    	int startY = Math.min(height, (int)(handler.getGameCamera().getyOffset() / Block.BLOCKHEIGHT));
    	int endY = Math.min(height, (int)((handler.getGameCamera().getyOffset() + handler.getHeight()) / Block.BLOCKHEIGHT) + 1);
    	
    	
    	for(int y = startY;y < endY;y++) {
        	for(int x = startX;x < endX;x++) {
        		blocks[x][y].render(g); 
        	}
    	}
    	
    	entityManager.render(g); 
    	
    }
    
    
    public void loadWorld(String path) {
        initColors();

        blocks = new Block[width][height];
        
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
            	
            	Color c = new Color(blockImage.getRGB(x, y));
            	
            	if(c.equals(AIR))
            		blocks[x][y] = new AirBlock(handler,x,y);
            	else if(c.equals(DIRT))
            		blocks[x][y] = new Dirt(handler, x, y);
            	else if(c.equals(GRASS))
            		blocks[x][y] = new Grass(x, y);
            	else if(c.equals(QUESTION_BLOCK)) 
            		blocks[x][y] = new QuestionBlock(x, y);
            	else if(c.equals(FENCE)) 
            		blocks[x][y] = new Fence(x, y);
            	
            	else if(c.equals(redBrick)) 
            		blocks[x][y] = new RedBrick(x, y);
            	else if(c.equals(blueBrick)) 
            		blocks[x][y] = new BlueBrick(x, y);
            	else if(c.equals(whiteBrick)) 
            		blocks[x][y] = new WhiteBrick(x, y);
            	
            	else if(c.equals(redCrackedBrick)) 
            		blocks[x][y] = new RedCrackedBrick(x, y);
            	else if(c.equals(blueCrackedBrick)) 
            		blocks[x][y] = new BlueCrackedBrick(x, y);
            	else if(c.equals(whiteCrackedBrick)) 
            		blocks[x][y] = new WhiteCrackedBrick(x, y);
            	
            	else if(c.equals(redFancyBrick)) 
            		blocks[x][y] = new RedFancyBrick(x, y);
            	else if(c.equals(blueFancyBrick)) 
            		blocks[x][y] = new BlueFancyBrick(x, y);
            	else if(c.equals(whiteFancyBrick)) 
            		blocks[x][y] = new WhiteFancyBrick(x, y);
            	else if(c.equals(JUMPPAD)) 
            		blocks[x][y] = new JumpPad(x, y);
            	else if(c.equals(COIN)) 
            		blocks[x][y] = new Coin(x, y);
            	else if(c.equals(WATER)) 
            		blocks[x][y] = new Water(x, y);
            	else if(c.equals(LAVA)) 
            		blocks[x][y] = new Lava(x, y);
            	
            	
            	
            	else
            		blocks[x][y] = new AirBlock(handler, x, y); 
            	
                
                
                
            }
        }
        
        

        entityManager.addEntity(new Cloud(handler)); 
        entityManager.addEntity(new Cloud(handler)); 
        entityManager.addEntity(new Cloud(handler)); 
       
        
        PlatformVector[] vectors = {
        		new PlatformVector(2400, 400, 2400, 800, 500),
        		new PlatformVector(2400, 800, 2400, 400, 500)
        };
        
         
        entityManager.addEntity(new MovingPlatform(handler, 270, 30, vectors, false)); 
        //entityManager.addEntity(new Goomba(handler, 2400, 0, 1, Assets.goomba));
        entityManager.addEntity(new Goomba(handler, 2300, 960 - (1 * Block.BLOCKHEIGHT), 1, Assets.goomba));
        entityManager.addEntity(new Goomba(handler, 2900, 960 - (5 * Block.BLOCKHEIGHT), 5, Assets.goomba));
        
        entityManager.addEntity(new Crate(handler, 4900, 960 - (1 * Block.BLOCKHEIGHT), 1));
        entityManager.addEntity(new Crate(handler, 5100, 960 - (1 * Block.BLOCKHEIGHT), 1));

        entityManager.addEntity(new Crate(handler, 50, 960 - (2 * Block.BLOCKHEIGHT), 2));
        entityManager.addEntity(new Crate(handler, 150, 960 - (3 * Block.BLOCKHEIGHT), 3));
        entityManager.addEntity(new Crate(handler, 250, 960 - (6 * Block.BLOCKHEIGHT), 6));
        
        entityManager.addEntity(new Life(handler, 1300, 960 - (1 * Block.BLOCKHEIGHT)));
        
        handler.getGameState().setPlayer(new Player(this.handler, (float) spawnX, (float)spawnY, 100));
        
    }
    public void preLoad(String path) {
        name = path.substring(path.lastIndexOf(47)).substring(1);
        
        String blockPath = (String.valueOf(path) + "/tiles.png").substring(3);
        String propertiesPath = String.valueOf(path) + "/properties.txt";
        
        blockImage = ImageLoader.loadImage(blockPath);
        

        
        width = blockImage.getWidth();
        height = blockImage.getHeight();
        
        
        /////////////////////READING THE PROPERTIES FILE/////////////////////////////      
        String file = Utils.loadFileAsString(propertiesPath);
        String[] lines = file.split("\n");
        
        for (int length = lines.length, i = 0; i < length; ++i) {
            String[] tokens = lines[i].split("=");
            
            if (tokens[0].equalsIgnoreCase("spawnpoint")) {
                String[] coords = tokens[1].split(",");
                spawnX = Utils.parseInt(coords[0]);
                spawnY = Utils.parseInt(coords[1]);
            }
            
            
            
        }
        
        
        handler.setWorld(this);
        Assets.minimap = blockImage;
    }
    
    
    
    
    
    
    
	private static Color AIR,DIRT,GRASS,QUESTION_BLOCK,FENCE,JUMPPAD,COIN,LAVA,WATER;
	public static Color redBrick,blueBrick,whiteBrick;
	public static Color redCrackedBrick,blueCrackedBrick,whiteCrackedBrick;
	public static Color redFancyBrick,blueFancyBrick,whiteFancyBrick;
	
    public void initColors() {
    	AIR = new Color(255, 255, 255);
    	DIRT = new Color(127,61,17);
    	GRASS = new Color(67,201,12);
    	
    	QUESTION_BLOCK = new Color(253,229,0);
    	FENCE = new Color(255,229,0);
    	
    	redBrick = new Color(204,78,12);
    	blueBrick = new Color(78,204,12);
    	whiteBrick = new Color(204,204,12);
    	
    	redCrackedBrick = new Color(201,78,12);
    	blueCrackedBrick = new Color(78,201,12);
    	whiteCrackedBrick = new Color(204,201,12);
    	
    	redFancyBrick = new Color(200,78,12);
    	blueFancyBrick = new Color(78,200,12);
    	whiteFancyBrick = new Color(204,200,12);
    	
    	JUMPPAD = new Color(255,111,71);
    	COIN = new Color(255,216,0);
    	LAVA = new Color(255,0,0);
    	WATER = new Color(0,0,255);
    	
    	
    }
    
    
    
    public Block getBlock(int x, int y) {//block coords
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return null;
        }
        Block b = blocks[x][y];

        return b;
    }
    public void setBlock(Block b,int x,int y) {
    	blocks[x][y] = b;
    }
    
    public Block getBlock(float x, float y) { //pixel coords
        
    	Point p = getAbsPos(x, y);
        if (p.x < 0 || p.y < 0 || p.x >= width || p.y >= height) {
            return null;
        }
    	Block b = blocks[p.x][p.y]; 
        return b;
    }
    
    
    
	public Point getAbsPos(float x,float y) {// in coords by pixel
		//so to input, take screen coords and add offset
		
		float newX = ((x) / Block.BLOCKWIDTH);
		float newY = ((y) / Block.BLOCKHEIGHT);
		
		if(newX < 0)
			newX -= 1;
		if(newY < 0)
			newY -= 1;
		
		
		return new Point((int)(newX),(int)(newY)); 
	}
    
    public String getName() {
        return this.name;
    }
    public int getWidth() {
        return this.width;
    }
    public int getHeight() {
        return this.height;
    }
    public int getSpawnX() {
        return this.spawnX;
    }
    public int getSpawnY() {
        return this.spawnY;
    }
    public EntityManager getEntityManager() {
        return this.entityManager;
    }





























}
