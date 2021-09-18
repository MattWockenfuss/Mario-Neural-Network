package com.mario.blocks.special;

import com.mario.blocks.Block;
import com.mario.gfx.Assets;

public class QuestionBlock extends Block{

	public QuestionBlock(int x,int y) {
		super(null, Assets.question_block,x,y);
	}
	
	public boolean isSolid() {
		return true;
	}

}
