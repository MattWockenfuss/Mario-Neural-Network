package com.mario.states.SinglePlayer;

import java.awt.Color;
import java.awt.Graphics;

import com.mario.Handler;
import com.mario.entities.mobs.Player;
import com.mario.gfx.Assets;
import com.mario.states.GameStateManager;
import com.mario.states.State;
import com.mario.ui.ClickListener;
import com.mario.ui.UIImageButton;
import com.mario.ui.UIManager;
import com.mario.world.World;

public class SinglePlayerGameState extends State{
	
	public boolean debug = false;
	public static boolean escape = false;
	boolean toggle = false,toggle2 = false;
	
	private int buttonWidth = handler.getWidth() / 5;
	private int buttonHeight = handler.getHeight() / 18;
	
	
	private UIManager gameUIManager;
	private UIManager escapeUIManager;
	
	//Stuff the GameState needs
	private World currentWorld;
	public Player player;
	public int coins = 0;
	
	public SinglePlayerGameState(Handler handler) {
		super(handler);
		gameUIManager  = new UIManager(handler);
		escapeUIManager = new UIManager(handler);
		
		escapeUIManager.addObject(new UIImageButton(handler.getWidth() / 5, handler.getHeight() / 5, buttonWidth, buttonHeight, Assets.buttons, new ClickListener() {

			@Override
			public void onClick() {
				GameStateManager.setState(handler.getMenuState());
				escape = false;
			}
		}));
		escapeUIManager.addObject(new UIImageButton(handler.getWidth() / 5, handler.getHeight() / 5 + 100, buttonWidth, buttonHeight, Assets.buttons, new ClickListener() {

			@Override
			public void onClick() {
				GameStateManager.setState(handler.getSettingsState());
			}
		}));
		escapeUIManager.addObject(new UIImageButton(handler.getWidth() / 5 + (handler.getWidth() / 3), handler.getHeight() / 5, buttonWidth, buttonHeight, Assets.buttons, new ClickListener() {

			@Override
			public void onClick() {
				GameStateManager.setState(handler.getAchievementState()); 
			}
		}));


		
		
		
	}
	
	
	public void tick() {
		if(handler.getMouseManager().getUIManager() != gameUIManager)
			handler.getMouseManager().setUIManager(gameUIManager);
		if(currentWorld == null) {
			GameStateManager.setState(handler.getMenuState());
			System.out.println("There Was A Problem Loading the World!");
		}
		
			
		handler.getKeyManager().tick();
		
		if(handler.getKeyManager().escape && !toggle2) {
			escape = !escape; 
			toggle2 = true;
		}else if(!handler.getKeyManager().escape) {
			toggle2 = false;
		}
		
		if(escape) {
			if(handler.getMouseManager().getUIManager() != escapeUIManager)
				handler.getMouseManager().setUIManager(escapeUIManager);
			escapeUIManager.tick();
		}else {
			unPausedTick();
		}
		
		
		
		
		
		
		
		
	}
	public void render(Graphics g) {
		currentWorld.render(g); 		//The Player is now rendered with the entities, and the world
		
		
		
		if(debug) {
			renderDebugMenu(g);
		}
		if(escape) {
			renderPauseMenu(g); 
		}
		
		g.setFont(Assets.debug);
		g.setColor(Color.yellow); 
		g.drawString("COINS " + coins, 100, 50); 
		g.setColor(Color.red);
		g.drawString("Lives: " + player.getLivesLeft(), 100, 90);
	
	}
	
	
	
	
	
	public void renderDebugMenu(Graphics g) {
		g.setColor(Color.white);
		g.setFont(Assets.debug);
		
		int start = 70;
		int gap = 17;
		
		

		
		g.drawString("Mario Bro 0.0.1" , 50, start + (gap * 1));
		g.drawString("", 50, start + (gap * 2));
		g.drawString("FPS: " + handler.getGame().getFPS() + "        (" + (int)(player.getX()) + "," + (int)(player.getY()) + ")", 50, start + (gap * 3)); 
		g.drawString("E:(" + handler.getWorld().getEntityManager().getEntitiesRendered() + "/" + handler.getWorld().getEntityManager().getEntitiesUpdated() + "/" + handler.getWorld().getEntityManager().getEntities().size() + ")" , 50, start + (gap * 4)); 
		g.drawString("", 50, start + (gap * 5));
		g.drawString("mouseX: " + handler.getMouseManager().getMouseX(), 50, start + (gap * 6));
		g.drawString("mouseY: " + handler.getMouseManager().getMouseY(), 50, start + (gap * 7));
		g.drawString("mouseXOffset: " + (handler.getMouseManager().getMouseX() + handler.getGameCamera().getxOffset()), 50, start + (gap * 8));
		g.drawString("mouseYOffset: " + (handler.getMouseManager().getMouseY() + handler.getGameCamera().getyOffset()), 50, start + (gap * 9));
		g.drawString("", 50, start + (gap * 10));
		g.drawString("", 50, start + (gap * 11));
		g.drawString("Player Velocity: " + player.getVelocity(), 50, start + (gap * 12));
		
		
	}
	public void renderPauseMenu(Graphics g) {
		g.setColor(Assets.tinted_black);
		g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
		
		escapeUIManager.render(g);
		
		g.setColor(Color.WHITE);
		g.setFont(Assets.menu);
		
		//Menu Screen
		g.drawString("Menu Screen", handler.getWidth() / 4 + (buttonWidth / 2) - ("Menu Screen".length() * (Assets.menu.getSize() / 2)) - 20, handler.getHeight() / 5 + 35);
		//Settings Menu
		g.drawString("Settings", handler.getWidth() / 4 + (buttonWidth / 2) - ("Settings".length() * (Assets.menu.getSize() / 2)) - 30, handler.getHeight() / 5 + 135);
		//Achievements
		g.drawString("Achievements", handler.getWidth() / 4 + (buttonWidth / 2) - ("Achievements".length() * (Assets.menu.getSize() / 2)) - 10 + (handler.getWidth() / 3), handler.getHeight() / 5 + 35); 
	}
	
	

	public void unPausedTick() {
		//if(!handler.getKeyManager().space) {
			currentWorld.tick();
		//}
		
		
		if(handler.getKeyManager().t) {
			player.setX(handler.getWorld().getSpawnX());
			player.setY(handler.getWorld().getSpawnY());
			player.setVelocity(0.0f);
		}
		if(handler.getKeyManager().F3 && !toggle) {
			debug = !debug;
			toggle = true;
		}else if(!handler.getKeyManager().F3) {
			toggle = false;
		}
	}

	/////////////////////////////////////////////////////////////////////GETTERS AND SETTERS/////////////////////////////////////////////////////////////////////
	public void setCurrentWorld(World world) {
		currentWorld = world;
	}
	public World getCurrentWorld() {
		return currentWorld;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}

	
	

}
