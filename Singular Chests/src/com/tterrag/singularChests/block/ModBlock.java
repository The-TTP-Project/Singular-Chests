package com.tterrag.singularChests.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlock {

	public static Block singularChest;
	
	public static void init() {
		singularChest = new BlockSingularChest(54, 0).setHardness(2.5F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("chest");
	}

	public static void addNames() {
		
	}

}
