package com.tterrag.singularChests;

import net.minecraft.block.Block;

import com.tterrag.singularChests.block.ModBlock;
import com.tterrag.singularChests.config.ConfigHandler;
import com.tterrag.singularChests.lib.Reference;
import com.tterrag.singularChests.proxy.CommonProxy;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class SingularChests {

	@Instance(Reference.MOD_ID)
	public static SingularChests instance;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		ConfigHandler.init(event.getSuggestedConfigurationFile());
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		ModBlock.addNames();

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
		Block.blocksList[Block.chest.blockID] = null;
		ModBlock.init();
		Block.blocksList[Block.chest.blockID] = ModBlock.singularChest;

	}
}
