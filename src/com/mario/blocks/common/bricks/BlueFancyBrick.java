package com.mario.blocks.common.bricks;

import com.mario.blocks.Block;
import com.mario.gfx.Assets;

public class BlueFancyBrick extends Block{

	public BlueFancyBrick(int x,int y) {
		super(null, Assets.fancy_block[1], x, y); 
	}
	
	public boolean isSolid() {
		return true;
	}

}
