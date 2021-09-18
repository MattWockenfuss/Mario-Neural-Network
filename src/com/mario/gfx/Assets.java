package com.mario.gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {
	
	public static final int width = 32;
	public static final int height = 32;
	
	
	//blocks
	public static BufferedImage air,dirt,grass,question_block,bridge,fence,crate;
	public static BufferedImage[] brick,cracked_block,fancy_block,jump_pad,water,lava,coin;
	
	
	//entities
	public static BufferedImage jumpLeft,jumpRight;
	public static BufferedImage[] playerLeft,playerRight,playerJump,playerfall,playerCrouch;
	
	public static BufferedImage goombaStill,life;
	public static BufferedImage[] goomba,clouds;
	
	
	
	//UI
	public static BufferedImage background,title,minimap,castlebackground;
	public static BufferedImage[] buttons;
	public static BufferedImage inventory_gui,furnace_gui,crate_gui,crafting_station_gui,hotbar,selectedItem;




	
	public static Font menu,debug,updateLog,newMenu,achievementLOL;
	public static Color InventoryGray,tan,tinted_black;
	public static Color backgroundBlue;
	
	public static void init() {
		
		BlockInit();
		entityInit();
		uiInit();
		

	}
	
	
	
	private static void BlockInit() {
		SpriteSheet block_sheet = new SpriteSheet(ImageLoader.loadImage("/textures/block_spritesheet.png"));
		
		air = block_sheet.crop(0 * width, 0 * height, width, height);
		dirt = block_sheet.crop(1 * width, 0 * height, width, height);
		grass = block_sheet.crop(2 * width, 0 * height, width, height);
		
		bridge = block_sheet.crop(3 * width, 2 * height, width, height);
		fence = block_sheet.crop(3 * width, 3 * height, width, height);
		
		
		
		brick = new BufferedImage[3];
		brick[0] = block_sheet.crop(0 * width, 1 * height, width, height);
		brick[1] = block_sheet.crop(1 * width, 1 * height, width, height);
		brick[2] = block_sheet.crop(2 * width, 1 * height, width, height);
		
		cracked_block = new BufferedImage[3];
		cracked_block[0] = block_sheet.crop(0 * width, 2 * height, width, height);
		cracked_block[1] = block_sheet.crop(1 * width, 2 * height, width, height);
		cracked_block[2] = block_sheet.crop(2 * width, 2 * height, width, height);
		
		fancy_block = new BufferedImage[3];
		fancy_block[0] = block_sheet.crop(0 * width, 3 * height, width, height);
		fancy_block[1] = block_sheet.crop(1 * width, 3 * height, width, height);
		fancy_block[2] = block_sheet.crop(2 * width, 3 * height, width, height);
		
		question_block = block_sheet.crop(3 * width, 1 * height, width, height);
		
		
		jump_pad = new BufferedImage[2];
		jump_pad[0] = block_sheet.crop(4 * width, 2 * height, width, height);
		jump_pad[1] = block_sheet.crop(5 * width, 2 * height, width, height);
		
		
		lava = new BufferedImage[3];
		lava[0] = block_sheet.crop(6 * width, 0 * height, width, height);
		lava[1] = block_sheet.crop(7 * width, 0 * height, width, height);
		lava[2] = block_sheet.crop(8 * width, 0 * height, width, height);
		
		water = new BufferedImage[14];
		for(int i = 0;i < water.length;i++) {
			water[i] = block_sheet.crop(i * width, 12 * height, width, height);
		}
		
		coin = new BufferedImage[16];
		for(int i = 0;i < coin.length;i++) {
			coin[i] = block_sheet.crop(i * width, 10 * height, width, height);
		}
		
		
		playerRight = new BufferedImage[7];
		playerRight[0] = block_sheet.crop(4, 477, 15, 28);
		playerRight[1] = block_sheet.crop(28, 477, 15, 28);
		playerRight[2] = block_sheet.crop(48, 477, 15, 28);
		playerRight[3] = block_sheet.crop(68, 477, 15, 28);
		playerRight[4] = block_sheet.crop(86, 476, 15, 29);
		playerRight[5] = block_sheet.crop(104, 477, 15, 28);
		playerRight[6] = block_sheet.crop(122, 477, 15, 28);
		
		playerLeft = new BufferedImage[7];
		playerLeft[0] = block_sheet.crop(121, 436, 15, 28);
		playerLeft[1] = block_sheet.crop(97, 436, 15, 28);
		playerLeft[2] = block_sheet.crop(77, 436, 15, 28);
		playerLeft[3] = block_sheet.crop(57, 436, 15, 28);
		playerLeft[4] = block_sheet.crop(39, 435, 15, 29);
		playerLeft[5] = block_sheet.crop(21, 436, 15, 28);
		playerLeft[6] = block_sheet.crop(3, 436, 15, 28);
		
		jumpLeft = block_sheet.crop(139, 437, 16, 28);
		jumpRight = block_sheet.crop(143, 476, 16, 28);
		
		playerCrouch = new BufferedImage[2];
		playerCrouch[0] = block_sheet.crop(399, 485, 16, 26); //left
		playerCrouch[1] = block_sheet.crop(345, 477, 16, 26); //right
		
		
		goomba = new BufferedImage[2];
		goombaStill = block_sheet.crop(15 * width, 4 * height, width, height);
		goomba[0] = block_sheet.crop(15 * width, 5 * height, width, height);
		goomba[1] = block_sheet.crop(15 * width, 6 * height, width, height);
		
		
		crate = block_sheet.crop(14 * width, 5 * height, width, height);
		life = block_sheet.crop(14 * width, 4 * height, width, height);
		
	}

	private static void entityInit() {
		
		SpriteSheet cloud_sheet = new SpriteSheet(ImageLoader.loadImage("/textures/cloudSheet.png"));
		
		clouds = new BufferedImage[5];
		clouds[0] = cloud_sheet.crop(17, 44, 153, 75);
		clouds[1] = cloud_sheet.crop(181, 22, 105, 75);
		clouds[2] = cloud_sheet.crop(79, 135, 104, 75);
		clouds[3] = cloud_sheet.crop(204, 106, 154, 76);
		clouds[4] = cloud_sheet.crop(348, 50, 103, 75);
		
		
		
		
	}
	private static void uiInit() {
		background = ImageLoader.loadImage("/textures/UI/misc/background.png");
		title = ImageLoader.loadImage("/textures/UI/misc/title.png");
		castlebackground = ImageLoader.loadImage("/textures/UI/misc/castlebackground.png");
		
		SpriteSheet UISheet = new SpriteSheet(ImageLoader.loadImage("/textures/UI/misc/widgets.png"));
		
		buttons = new BufferedImage[2];
		buttons[0] = UISheet.crop(0, 172, 400, 40);
		buttons[1] = UISheet.crop(0, 132, 400, 40);
		
		
		//Containers
		inventory_gui = ImageLoader.loadImage("/textures/UI/Container/inventory.png");
		furnace_gui = ImageLoader.loadImage("/textures/UI/Container/furnacePanel.png");
		crate_gui = ImageLoader.loadImage("/textures/UI/Container/cratePanel.png");
		crafting_station_gui = ImageLoader.loadImage("/textures/UI/Container/craftingPanel.png");
		
		SpriteSheet hotbar_gui = new SpriteSheet(ImageLoader.loadImage("/textures/UI/Container/hotbar.png"));
		hotbar = hotbar_gui.crop(1, 1, 362, 42);
		selectedItem = hotbar_gui.crop(1, 45, 46, 46); 
		
		
		
		
		
		
		//FONTS
		menu = new Font("Times New Roman",Font.BOLD,24);
		debug = new Font("Verdana",Font.BOLD,16);
		updateLog = new Font("Verdana",Font.PLAIN,20);
		newMenu = new Font("Times New Roman",Font.BOLD,48);
		achievementLOL = new Font("Times New Roman",Font.PLAIN,96);
		
		//Color
		InventoryGray = new Color(220,220,220);
		tan = new Color(255,178,102);
		tinted_black = new Color(0,0,0,0.2f);
		
		backgroundBlue = new Color(0,174,255);
		
		
		Color[] light = new Color[100];
		for(int i = 0;i < light.length;i++) {
			light[i] = new Color(0,0,0,i/100f);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
}
