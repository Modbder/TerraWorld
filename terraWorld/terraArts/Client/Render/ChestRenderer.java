package terraWorld.terraArts.Client.Render;

import terraWorld.terraArts.Common.Block.BlockTAChest;
import terraWorld.terraArts.Common.Tile.TileEntityTAChest;
import terraWorld.terraArts.Network.TAPacketHandler;
import terraWorld.terraArts.Utils.TAConfig;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class ChestRenderer implements ISimpleBlockRenderingHandler{

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId,
			RenderBlocks renderer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		if(block instanceof BlockTAChest)
		{
			BlockTAChest taChest = (BlockTAChest) block;
			TileEntityTAChest chest = (TileEntityTAChest) world.getTileEntity(x, y, z);
			TAPacketHandler.sendRenderRequestToServer(x, y, z, chest.getWorldObj().provider.dimensionId, Minecraft.getMinecraft().thePlayer,chest.textureIndex);
			int metadata = world.getBlockMetadata(x, y, z);
			int type = taChest.type;
			renderer.setOverrideBlockTexture(taChest.allOverlays[0]);
			renderer.setRenderBounds(-0.001D, 0.999D, -0.001D, 1.001D, 1.001D, 1.001D);
			renderer.renderStandardBlock(Blocks.glass, x, y, z);
			renderer.setRenderBounds(-0.001D, -0.001D, -0.001D, 1.001D, 0.001D, 1.001D);
			renderer.renderStandardBlock(Blocks.glass, x, y, z);
			renderer.setOverrideBlockTexture(taChest.allOverlays[1]);
			renderer.setRenderBounds(-0.001D, 0.001D, -0.001D, 1.001D, 0.999D, 1.001D);
			renderer.renderStandardBlock(Blocks.glass, x, y, z);
			
			renderer.setRenderBounds(0.5D, 0.5D, 0.5D, 0.5D, 0.5D, 0.5D);
			
			
			switch(metadata)
			{
				case 2:
				{
					renderer.setRenderBounds(0.001D, 0.001D, -0.002D, 0.999D, 0.999D, 0.999D);
					break;
				}
				case 3:
				{
					renderer.setRenderBounds(0.001D, 0.001D, 0.001D, 0.999D, 0.999D, 1.002D);
					break;
				}
				case 4:
				{
					renderer.setRenderBounds(-0.002D, 0.001D, 0.001D, 0.999D, 0.999D, 0.999D);
					break;
				}
				case 5:
				{
					renderer.setRenderBounds(0.001D, 0.001D, 0.001D, 1.002D, 0.999D,0.999D );
					break;
				}
				
			}
			
			float rC = 1, gC = 1, bC = 1;
			
			switch(type)
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
			renderer.renderStandardBlockWithColorMultiplier(Blocks.glass, x, y, z, rC, gC, bC);
			
			renderer.setOverrideBlockTexture(taChest.allOverlays[4+chest.textureIndex]);
			renderer.setRenderBounds(0D, 0D, 0D, 1D, 1D, 1D);
			renderer.renderStandardBlock(Blocks.glass, x, y, z);
			
			renderer.clearOverrideBlockTexture();
			return true;
		}
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getRenderId() {
		// TODO Auto-generated method stub
		return TAConfig.chestRendererID;
	}

}
