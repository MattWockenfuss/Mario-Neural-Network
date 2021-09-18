package com.mario.blocks.common;

import com.mario.Handler;
import com.mario.blocks.Block;
import com.mario.gfx.Assets;

public class Dirt extends Block{

	public Dirt(Handler handler,int x,int y) {
		super(handler, Assets.dirt, x, y); 
	}
	public boolean isSolid() {
		return true;
	}
}
