package com.mario.blocks.common;

import com.mario.Handler;
import com.mario.blocks.Block;
import com.mario.gfx.Assets;

public class AirBlock extends Block{

	public AirBlock(Handler handler,int x,int y) {
		super(handler, Assets.air, x, y); 
	}

}
