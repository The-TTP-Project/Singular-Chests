package com.tterrag.singularChests.block;

import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlock {

	public static Block singularChest;
	
	public static void init() {
		singularChest = new BlockSingularChest(1900, 0);
		
		GameRegistry.registerBlock(singularChest, "singularChest");
	}

	public static void addNames() {
		
	}

}
