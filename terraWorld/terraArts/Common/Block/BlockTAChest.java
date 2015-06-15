package terraWorld.terraArts.Common.Block;

import DummyCore.Utils.MathUtils;
import DummyCore.Utils.MiscUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.Random;

import terraWorld.terraArts.Common.Registry.ItemRegistry;
import terraWorld.terraArts.Common.Tile.TileEntityTAChest;
import terraWorld.terraArts.Mod.TerraArts;
import terraWorld.terraArts.Network.TAPacketHandler;
import terraWorld.terraArts.Utils.EnumOverlay;
import terraWorld.terraArts.Utils.TAConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockTAChest extends BlockContainer
{
	public static String[] names = {"Iron","Gold","Diamond","Gem","Darkness"};
	public int type;
    @SideOnly(Side.CLIENT)
    private IIcon furnaceIconTop;
    @SideOnly(Side.CLIENT)
    private IIcon furnaceIconFront;
    
    public IIcon[] allOverlays = new IIcon[128];

    public BlockTAChest(int rarity)
    {
        super(Material.iron);
        type = rarity;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    public int getRenderType()
    {
    	return TAConfig.chestRendererID;
    }
    
    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return null;
    }
    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        super.onBlockAdded(par1World, par2, par3, par4);
        this.setDefaultDirection(par1World, par2, par3, par4);
        
    }

    /**
     * set a blocks direction
     */
    private void setDefaultDirection(World par1World, int x, int y, int z)
    {
        if (!par1World.isRemote)
        {
            Block block = par1World.getBlock(x, y, z - 1);
            Block block1 = par1World.getBlock(x, y, z + 1);
            Block block2 = par1World.getBlock(x - 1, y, z);
            Block block3 = par1World.getBlock(x + 1, y, z);
            byte b0 = 3;

            if (block.func_149730_j() && !block1.func_149730_j())
            {
                b0 = 3;
            }

            if (block1.func_149730_j() && !block.func_149730_j())
            {
                b0 = 2;
            }

            if (block2.func_149730_j() && !block3.func_149730_j())
            {
                b0 = 5;
            }

            if (block3.func_149730_j() && !block2.func_149730_j())
            {
                b0 = 4;
            }

            par1World.setBlockMetadataWithNotify(x, y, z, b0, 2);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
    	this.allOverlays[0] = par1IconRegister.registerIcon("terraarts:chestoverlay_generic");
    	this.allOverlays[1] = par1IconRegister.registerIcon("terraarts:chestoverlay_side");
    	this.allOverlays[2] = par1IconRegister.registerIcon("terraarts:chestoverlay_lock");
    	for(int i = 3; i < EnumOverlay.values().length + 3; ++i)
    	{
    		this.allOverlays[i] = par1IconRegister.registerIcon("terraarts:chestoverlay_"+EnumOverlay.values()[i-3].getName());
    	}
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        if (par1World.isRemote)
        {
            return true;
        }
        else
        {
        	TileEntityTAChest chest = (TileEntityTAChest) par1World.getTileEntity(par2, par3, par4);
        	boolean shouldOpen = false;
        	//TODO keys
        	ItemStack heldStack = par5EntityPlayer.getCurrentEquippedItem();
        	if(heldStack != null)
        	{
        		if(heldStack.getItem() == ItemRegistry.key)
        		{
        			if(heldStack.getItemDamage() >= this.type)
        			{
        				shouldOpen = true;
        			}
        		}
        	}
        	if((shouldOpen || !TAConfig.chestsRequireKeys) || (chest != null && chest.unlocked && TAConfig.saveChestInformation))
        	{
        		if(TAConfig.saveChestInformation)
        		{
        			chest.unlocked = true;
        			TAPacketHandler.playSoundOnServer("random.levelup", par2+0.5D, par3+0.5D, par4+0.5D, 1, 2, 16, par5EntityPlayer.dimension);
        			chest.onPlaced();
        			if(TAConfig.chestsConsumeKeys && heldStack != null && heldStack.getItem() == ItemRegistry.key)
        			{
        				par5EntityPlayer.inventory.decrStackSize(par5EntityPlayer.inventory.currentItem, 1);
        				TAPacketHandler.playSoundOnServer("random.break", par2+0.5D, par3+0.5D, par4+0.5D, 1, 1, 16, par5EntityPlayer.dimension);
        			}
        		}
        		par5EntityPlayer.openGui(TerraArts.instance, 374436, par1World, par2, par3, par4);
        		TAPacketHandler.playSoundOnServer("random.door_close", par2+0.5D, par3+0.5D, par4+0.5D, 1, 0.2D, 16, par5EntityPlayer.dimension);
        	}
        	else
        	{
        		par5EntityPlayer.addChatMessage(new ChatComponentText("This chest seems to be locked with some kind of magic"));
        		TAPacketHandler.playSoundOnServer("random.door_open", par2+0.5D, par3+0.5D, par4+0.5D, 1, 2D, 16, par5EntityPlayer.dimension);
        		TAPacketHandler.playSoundOnServer("random.door_close", par2+0.5D, par3+0.5D, par4+0.5D, 1, 2D, 16, par5EntityPlayer.dimension);
        	}
            return true;
        }
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World par1World)
    {
        return new TileEntityTAChest();
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack chestDropStack)
    {
        int l = MathHelper.floor_double((double)(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
        }

        if (l == 1)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 2);
        }

        if (l == 2)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
        }

        if (l == 3)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 2);
        }
        TileEntityTAChest chest = (TileEntityTAChest) par1World.getTileEntity(par2, par3, par4);
        NBTTagCompound dropTag = MiscUtils.getStackTag(chestDropStack);
        chest.readFromNBT(dropTag);
        chest.xCoord = par2;
        chest.yCoord = par3;
        chest.zCoord = par4;
        chest.onPlaced();
    }

    /**
     * Called on server worlds only when the block has been replaced by a different block ID, or the same block with a
     * different metadata value, but before the new metadata value is set. Args: World, x, y, z, old block ID, old
     * metadata
     */
    public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6)
    {
    	ItemStack chestDropStack = new ItemStack(this,1,0);
    	NBTTagCompound dropTag = MiscUtils.getStackTag(chestDropStack);
    	TileEntityTAChest chest = (TileEntityTAChest) par1World.getTileEntity(par2, par3, par4);
    	if(chest != null)
    	{
	    	chest.writeToNBT(dropTag);
	    	chestDropStack.setTagCompound(dropTag);
	    	EntityItem chestItem = new EntityItem(par1World,par2+0.5D,par3+0.5D,par4+0.5D,chestDropStack);
	    	chestItem.setPositionAndRotation(par2+0.5D,par3+0.5D,par4+0.5D, 1, 1);
	    	chestItem.motionX += MathUtils.randomDouble(par1World.rand)/3;
	    	chestItem.motionY += MathUtils.randomDouble(par1World.rand)/3;
	    	chestItem.motionZ += MathUtils.randomDouble(par1World.rand)/3;
	    	if(!par1World.isRemote)
	    		par1World.spawnEntityInWorld(chestItem);
	    	TAPacketHandler.playSoundOnServer("fireworks.blast", par2+0.5D, par3+0.5D, par4+0.5D, 1, 0.1D, 16, par1World.provider.dimensionId);
    	}
        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }


	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		// TODO Auto-generated method stub
		return this.createNewTileEntity(p_149915_1_);
	}

}
