package com.mario.blocks.special;

import com.mario.blocks.Block;
import com.mario.entities.mobs.Player;
import com.mario.gfx.Assets;

public class JumpPad extends Block{

	public JumpPad(int x,int y) {
		super(null, Assets.jump_pad[0],x,y);
	}
	
	
	//if the player is standing ontop of the block, then turn red and let them jump really high
	
	public void tick() {
		Player p = playerOnTop();
		
		
		if(p != null) {
			p.setIsOnJumpPad(true); 
			texture  = Assets.jump_pad[0];
		}else {
			texture = Assets.jump_pad[1];
		}
		
		
		
	}
	

	
	
	
	public boolean isSolid() {
		return true;
	}

}
