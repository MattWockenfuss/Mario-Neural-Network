package com.mario.blocks.common.bricks;

import com.mario.blocks.Block;
import com.mario.gfx.Assets;

public class BlueBrick extends Block{

	public BlueBrick(int x,int y) {
		super(null, Assets.brick[1], x, y); 
	}
	
	public boolean isSolid() {
		return true;
	}

}
