package com.mario.blocks.common.bricks;

import com.mario.blocks.Block;
import com.mario.gfx.Assets;

public class RedFancyBrick extends Block{

	public RedFancyBrick(int x,int y) {
		super(null, Assets.fancy_block[0], x, y); 
	}
	
	public boolean isSolid() {
		return true;
	}

}
