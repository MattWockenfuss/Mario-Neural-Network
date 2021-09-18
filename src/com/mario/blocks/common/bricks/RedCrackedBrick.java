package com.mario.blocks.common.bricks;

import com.mario.blocks.Block;
import com.mario.gfx.Assets;

public class RedCrackedBrick extends Block{

	public RedCrackedBrick(int x,int y) {
		super(null, Assets.cracked_block[0], x, y); 
	}
	
	public boolean isSolid() {
		return true;
	}

}
