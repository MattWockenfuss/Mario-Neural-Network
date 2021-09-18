package com.mario.blocks.common.bricks;

import com.mario.blocks.Block;
import com.mario.gfx.Assets;

public class WhiteBrick extends Block{

	public WhiteBrick(int x,int y) {
		super(null, Assets.brick[2], x, y); 
	}
	
	public boolean isSolid() {
		return true;
	}

}
