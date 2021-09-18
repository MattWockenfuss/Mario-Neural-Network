package com.mario.blocks.common.bricks;

import com.mario.blocks.Block;
import com.mario.gfx.Assets;

public class WhiteCrackedBrick extends Block{

	public WhiteCrackedBrick(int x,int y) {
		super(null, Assets.cracked_block[2], x, y); 
	}
	
	public boolean isSolid() {
		return true;
	}

}
