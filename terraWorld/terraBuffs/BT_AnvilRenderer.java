package terraWorld.terraBuffs;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class BT_AnvilRenderer implements ISimpleBlockRenderingHandler{

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId,
			RenderBlocks renderer) 
	{
		renderer.setOverrideBlockTexture(Blocks.anvil.getBlockTextureFromSide(0));
		renderer.renderBlockAsItem(Blocks.stone, 1, 1);
		renderer.setOverrideBlockTexture(Blocks.iron_bars.getBlockTextureFromSide(0));
		GL11.glPushMatrix();
			GL11.glTranslatef(0, -0.2F, 0);
			GL11.glScalef(1.01F, 0.4F, 1.01F);
			renderer.renderBlockAsItem(Blocks.stone, 1, 1);
		GL11.glPopMatrix();
		
		renderer.setOverrideBlockTexture(Blocks.iron_block.getBlockTextureFromSide(0));
		GL11.glTranslatef(0, -0.449F, 0);
		GL11.glScalef(1.01F, 0.1F, 1.01F);
		renderer.renderBlockAsItem(Blocks.stone, 1, 1);
		GL11.glTranslatef(0, 5F, 0);
		renderer.renderBlockAsItem(Blocks.stone, 1, 1);
		GL11.glTranslatef(0, 2F, 0);
		renderer.renderBlockAsItem(Blocks.stone, 1, 1);
		
		renderer.clearOverrideBlockTexture();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer)
	{
		renderer.setOverrideBlockTexture(Blocks.anvil.getBlockTextureFromSide(0));
		renderer.renderStandardBlock(Blocks.stone, x, y, z);
		renderer.setOverrideBlockTexture(Blocks.iron_bars.getBlockTextureFromSide(0));
		renderer.setRenderBounds(-0.001D, 0.1D, -0.001D, 1.001D, 0.5D,1.001D);
		renderer.renderStandardBlock(Blocks.stone, x, y, z);
		renderer.setOverrideBlockTexture(Blocks.iron_block.getBlockTextureFromSide(0));
		renderer.setRenderBounds(-0.001D, 0.0D, -0.001D, 1.001D, 0.1D,1.001D);
		renderer.renderStandardBlock(Blocks.stone, x, y, z);
		renderer.setRenderBounds(-0.001D, 0.5D, -0.001D, 1.001D, 0.6D,1.001D);
		renderer.renderStandardBlock(Blocks.stone, x, y, z);
		renderer.setRenderBounds(-0.001D, 0.7D, -0.001D, 1.001D, 0.8D,1.001D);
		renderer.renderStandardBlock(Blocks.stone, x, y, z);
		renderer.clearOverrideBlockTexture();
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) 
	{
		return true;
	}

	@Override
	public int getRenderId()
	{
		return getStaticRenderId();
	}
	
	public static int getStaticRenderId()
	{
		return 53333;
	}

}
