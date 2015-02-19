package terraWorld.terraBuffs;

import DummyCore.Utils.MiscUtils;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BT_Anvil extends BlockContainer{
	
	public BT_Anvil()
	{
		super(Material.anvil);
		this.setBlockName("bt.anvil");
		this.setBlockTextureName("anvil_base");
		this.setHardness(3);
		this.setResistance(3F);
		this.setStepSound(soundTypeAnvil);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		GameRegistry.registerBlock(this, "anvil");
		GameRegistry.registerTileEntity(BT_TileAnvil.class, "bt.tile.anvil");

	}
	
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    public int getRenderType()
    {
        return BT_Mod.proxy().getAnvilRenderID();
    }

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		// TODO Auto-generated method stub
		return new BT_TileAnvil();
	}
	
	@Override
	 public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	    {
	        if (par1World.isRemote)
	        {
	            return true;
	        }else
	        {
	        	if(!par5EntityPlayer.isSneaking())
	        	{
	        		par5EntityPlayer.openGui(BT_Mod.instance, -46346, par1World, par2, par3, par4);
	            	return true;
	        	}
	        	else
	        	{
	        		return false;
	        	}
	        }
	    }
	
	@Override
   public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6)
   {
   	MiscUtils.dropItemsOnBlockBreak(par1World, par2, par3, par4, par5, par6);
   	super.breakBlock(par1World, par2, par3, par4, par5, par6);
   }

}
