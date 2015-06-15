package terraWorld.terraArts.Client.Render;

import org.lwjgl.opengl.GL11;

import terraWorld.terraArts.Common.Block.BlockTAChest;
import terraWorld.terraArts.Common.Tile.TileEntityTAChest;
import terraWorld.terraArts.Network.TAPacketHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class ItemChestRenderer implements IItemRenderer{

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		BlockTAChest taChest = (BlockTAChest) Block.getBlockFromItem(item.getItem());
		int type1 = taChest.type;
		int x = 0,y = 0, z = 0;
		RenderBlocks renderer = (RenderBlocks) data[0];
		RenderHelper.enableStandardItemLighting();
		renderer.setOverrideBlockTexture(taChest.allOverlays[0]);
		GL11.glPushMatrix();
			GL11.glScalef(1.001F, 0.001F, 1.001F);
			GL11.glTranslatef(0, 500, 0);
			renderer.renderBlockAsItem(Blocks.glass, 1, 1);
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();
			GL11.glScalef(1.001F, 0.001F, 1.001F);
			GL11.glTranslatef(0, -500, 0);
			renderer.renderBlockAsItem(Blocks.glass, 1, 1);
		GL11.glPopMatrix();
		
		renderer.setOverrideBlockTexture(taChest.allOverlays[1]);
		
		GL11.glPushMatrix();
			GL11.glScalef(1.001F, 0.999F, 1.001F);
			renderer.renderBlockAsItem(Blocks.glass, 1, 1);
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();
			float rC = 1, gC = 1, bC = 1;
			
			switch(type1)
			{
				case 0:
				{
					break;
				}
				case 1:
				{
					bC = 0;
					break;
				}
				case 2:
				{
					rC = 0;
					break;
				}
				case 3:
				{
					gC = 0;
					bC = 0;
					break;
				}	
				case 4:
				{
					gC = 0;
					rC = 0.6F;
					break;
				}
			}
			
			renderer.setOverrideBlockTexture(taChest.allOverlays[2]);
			renderer.useInventoryTint = false;
			
			GL11.glColor4f(rC, gC, bC,1F);
			GL11.glScalef(1, 1, 0.001F);
			GL11.glTranslatef(0, 0, 500);
			renderer.renderBlockAsItem(Blocks.glass, 1, 1);
			renderer.useInventoryTint = true;
			
		GL11.glPopMatrix();
		
		renderer.setOverrideBlockTexture(taChest.allOverlays[4+ (item.getTagCompound() == null ? 0 : item.getTagCompound().getInteger("textureIndex"))]);
		renderer.setRenderBounds(0D, 0D, 0D, 1D, 1D, 1D);
		renderer.renderBlockAsItem(Blocks.glass, 1, 1);
		
		RenderHelper.disableStandardItemLighting();RenderHelper.enableGUIStandardItemLighting();
		renderer.clearOverrideBlockTexture();
	}

}
