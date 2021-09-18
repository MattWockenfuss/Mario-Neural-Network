package com.mario.blocks.common;

import com.mario.blocks.Block;
import com.mario.gfx.Assets;

public class Grass extends Block{

	public Grass(int x,int y) {
		super(null, Assets.grass, x, y); 
	}
	public boolean isSolid() {
		return true;
	}

}
