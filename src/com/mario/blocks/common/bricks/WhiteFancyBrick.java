package com.mario.blocks.common.bricks;

import com.mario.blocks.Block;
import com.mario.gfx.Assets;

public class WhiteFancyBrick extends Block{

	public WhiteFancyBrick(int x,int y) {
		super(null, Assets.fancy_block[2], x, y); 
	}
	
	public boolean isSolid() {
		return true;
	}

}
