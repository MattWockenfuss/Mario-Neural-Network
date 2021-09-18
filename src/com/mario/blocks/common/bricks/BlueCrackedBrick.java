package com.mario.blocks.common.bricks;

import com.mario.blocks.Block;
import com.mario.gfx.Assets;

public class BlueCrackedBrick extends Block{

	public BlueCrackedBrick(int x,int y) {
		super(null, Assets.cracked_block[1], x, y); 
	}
	
	public boolean isSolid() {
		return true;
	}

}
