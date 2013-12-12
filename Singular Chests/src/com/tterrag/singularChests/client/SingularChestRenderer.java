package com.tterrag.singularChests.client;

import java.util.Calendar;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelLargeChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.tterrag.singularChests.tile.TileSingularChest;

import cpw.mods.fml.common.FMLLog;

public class SingularChestRenderer extends TileEntitySpecialRenderer{

	private static final ResourceLocation RES_TRAPPED_SINGLE = new ResourceLocation("textures/entity/chest/trapped.png");
	private static final ResourceLocation RES_CHRISTMAS_SINGLE = new ResourceLocation("textures/entity/chest/christmas.png");
	private static final ResourceLocation RES_NORMAL_SINGLE = new ResourceLocation("textures/entity/chest/normal.png");

	/** The normal small chest model. */
	private ModelChest chestModel = new ModelChest();

	/** The large double chest model. */
	private ModelChest largeChestModel = new ModelLargeChest();

	/** If true, chests will be rendered with the Christmas present textures. */
	private boolean isChristmas;

	public SingularChestRenderer()
	{
		Calendar calendar = Calendar.getInstance();

		if (calendar.get(2) + 1 == 12 && calendar.get(5) >= 24 && calendar.get(5) <= 26)
		{
			this.isChristmas = true;
		}
	}

	/**
	 * Renders the TileEntity for the chest at a position.
	 */
	public void renderSingularChestAt(TileSingularChest par1TileEntity, double par2, double par4, double par6, float par8)
	{
		int i;

		if (!par1TileEntity.hasWorldObj())
		{
			i = 0;
		}
		else
		{
			Block block = par1TileEntity.getBlockType();
			i = par1TileEntity.getBlockMetadata();

			if (block instanceof BlockChest && i == 0)
			{
				try
				{
					((BlockChest)block).unifyAdjacentChests(par1TileEntity.getWorldObj(), par1TileEntity.xCoord, par1TileEntity.yCoord, par1TileEntity.zCoord);
				}
				catch (ClassCastException e)
				{
					FMLLog.severe("Attempted to render a chest at %d,  %d, %d that was not a chest",
							par1TileEntity.xCoord, par1TileEntity.yCoord, par1TileEntity.zCoord);
				}
				i = par1TileEntity.getBlockMetadata();
			}
		}


		ModelChest modelchest;

		modelchest = this.chestModel;

		if (par1TileEntity.getChestType() == 1)
		{
			this.bindTexture(RES_TRAPPED_SINGLE);
		}
		else if (this.isChristmas)
		{
			this.bindTexture(RES_CHRISTMAS_SINGLE);
		}
		else
		{
			this.bindTexture(RES_NORMAL_SINGLE);
		}


		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float)par2, (float)par4 + 1.0F, (float)par6 + 1.0F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		short short1 = 0;

		if (i == 2)
		{
			short1 = 180;
		}

		if (i == 3)
		{
			short1 = 0;
		}

		if (i == 4)
		{
			short1 = 90;
		}

		if (i == 5)
		{
			short1 = -90;
		}

		if (i == 2 && par1TileEntity.adjacentChestXPos != null)
		{
			GL11.glTranslatef(1.0F, 0.0F, 0.0F);
		}

		if (i == 5 && par1TileEntity.adjacentChestZPosition != null)
		{
			GL11.glTranslatef(0.0F, 0.0F, -1.0F);
		}

		GL11.glRotatef((float)short1, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		float f1 = par1TileEntity.prevLidAngle + (par1TileEntity.lidAngle - par1TileEntity.prevLidAngle) * par8;
		float f2;

		if (par1TileEntity.adjacentChestZNeg != null)
		{
			f2 = par1TileEntity.adjacentChestZNeg.prevLidAngle + (par1TileEntity.adjacentChestZNeg.lidAngle - par1TileEntity.adjacentChestZNeg.prevLidAngle) * par8;

			if (f2 > f1)
			{
				f1 = f2;
			}
		}

		if (par1TileEntity.adjacentChestXNeg != null)
		{
			f2 = par1TileEntity.adjacentChestXNeg.prevLidAngle + (par1TileEntity.adjacentChestXNeg.lidAngle - par1TileEntity.adjacentChestXNeg.prevLidAngle) * par8;

			if (f2 > f1)
			{
				f1 = f2;
			}
		}

		f1 = 1.0F - f1;
		f1 = 1.0F - f1 * f1 * f1;
		modelchest.chestLid.rotateAngleX = -(f1 * (float)Math.PI / 2.0F);
		modelchest.renderAll();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}


	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
	{
		this.renderSingularChestAt((TileSingularChest)par1TileEntity, par2, par4, par6, par8);
	}
}