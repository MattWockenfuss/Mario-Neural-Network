package com.mario.blocks.common.bricks;

import com.mario.blocks.Block;
import com.mario.gfx.Assets;

public class RedBrick extends Block{

	public RedBrick(int x,int y) {
		super(null, Assets.brick[0], x, y); 
	}
	
	public boolean isSolid() {
		return true;
	}

}
