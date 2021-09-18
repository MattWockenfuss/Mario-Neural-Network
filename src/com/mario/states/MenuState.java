package com.mario.states;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JOptionPane;

import com.mario.Handler;
import com.mario.gfx.Assets;
import com.mario.ui.ClickListener;
import com.mario.ui.UIImageButton;
import com.mario.ui.UIManager;

public class MenuState extends State {

	private UIManager MenuUIManager;
	private int buttonWidth, buttonHeight;
	
	private String[] updates;
	public MenuState(Handler handler) {
		super(handler);
		buttonWidth = (int)(handler.getWidth() / 3.25); 
		buttonHeight = Math.min(handler.getHeight() / 17, 44); 
		MenuUIManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(MenuUIManager);

		int gap = 10;

		MenuUIManager.addObject(new UIImageButton(handler.getWidth() / 2 - (buttonWidth/2), 270.0F + (gap * 0), buttonWidth,buttonHeight, Assets.buttons, new ClickListener() {

					@Override
					public void onClick() {
						handler.getMouseManager().setUIManager(null);
						GameStateManager.setState(handler.getSinglePlayerState());

					}
				}));
		MenuUIManager.addObject(new UIImageButton(handler.getWidth() / 2  - (buttonWidth/2), (270.0F + 44 + (gap * 1)), buttonWidth,buttonHeight, Assets.buttons, new ClickListener() {

					@Override
					public void onClick() {
						JOptionPane.showConfirmDialog(null, "Multiplayer is not Implemented yet!");
					}
				}));
		MenuUIManager.addObject(new UIImageButton(handler.getWidth() / 2 - (buttonWidth / 2), (270.0F + 88 + (gap * 2)), buttonWidth / 2 - 20,buttonHeight, Assets.buttons, new ClickListener() {

					@Override
					public void onClick() {
						GameStateManager.setState(handler.getSettingsState());
					}
				}));
		MenuUIManager.addObject(new UIImageButton(handler.getWidth() / 2 + 10, (270.0F + 88 + (gap * 2)), buttonWidth / 2 - 10,buttonHeight, Assets.buttons, new ClickListener() {

			@Override
			public void onClick() {
				System.exit(0);

			}
		}));
		
		updates = new String[]{
				"               Mario Platformer            ",
				"Add some kind of screen with a list of levels",
				"mobs, add goombas and maybe like a fireball guy",
				"level design",
				"",
				"checkpoints",
				"",
				"",
				"",
				"",
				"",
				"",
				"",
				"",
				"",
				"Known Bugs",
				"",
				"",
		};
		
		
	
	}
	
	
	
	public void tick() {
		if (handler.getMouseManager().getUIManager() != MenuUIManager)
			handler.getMouseManager().setUIManager(MenuUIManager);
		handler.getKeyManager().tick();
		MenuUIManager.tick();

	}
	public void render(Graphics g) {
		
		g.drawImage(Assets.background, 0, 0, handler.getWidth(), handler.getHeight(), null);			//Background
		

		
		//g.setColor(Assets.tinted_black);
		//g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
		
		
		g.setFont(Assets.achievementLOL);
		g.setColor(Color.BLACK);
		g.drawString("Spelunked!", handler.getWidth() / 2 - (210), 150);
		
		MenuUIManager.render(g);
		g.setColor(Color.white);
		g.setFont(Assets.menu);
		g.drawString("Single Player", (handler.getWidth() / 2 - 60), 300);
		g.drawString("Multiplayer", (handler.getWidth() / 2 - 60), 350);
		g.drawString("Options", (handler.getWidth() / 2 - 190), 405);
		g.drawString("Quit", (handler.getWidth() / 2 + 105), 405);
		
		
		g.setColor(Color.BLACK);
		g.fillRect(handler.getWidth() - 622, 128,504,470 + (20 * 9) + 24);
		g.setColor(Color.white);
		g.fillRect(handler.getWidth() - 620, 130,500,470 + (20 * 9) + 20);
		
		g.setColor(Color.black); 
		g.setFont(Assets.updateLog);
		for(int i = 0;i < updates.length;i++) {
			g.drawString(updates[i], handler.getWidth() - 600, 150 + (24 * i)); 
		}
		
		
		
		

	}

}
