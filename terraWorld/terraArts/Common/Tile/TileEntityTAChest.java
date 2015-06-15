package terraWorld.terraArts.Common.Tile;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.Loader;
import terraWorld.terraArts.Utils.EnumOverlay;
import DummyCore.Utils.MiscUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

public class TileEntityTAChest extends TileEntity implements IInventory
{
	public int textureIndex = -1;
	public int rarity = -1;//Not used for now
	public boolean unlocked = false;
	
    private ItemStack[] dispenserContents = new ItemStack[9];

    /**
     * random number generator for instance. Used in random item stack selection.
     */
    private Random dispenserRandom = new Random();
    protected String customName;

    public void initChest()
    {
    	int x = this.xCoord;
    	int y = this.yCoord;
    	int z = this.zCoord;
    	BiomeGenBase b = this.worldObj.getBiomeGenForCoords(x, z);
    	b.getFloatTemperature(x, y, z);
    	WorldProvider p = this.worldObj.provider;
    	if(p != null)
    	{
    		boolean hellWorld = p.isHellWorld;
    		BiomeDictionary.Type[] t = BiomeDictionary.getTypesForBiome(b);
    		List<BiomeDictionary.Type> tp = Arrays.asList(t);
    		
    		if(Loader.isModLoaded("Thaumcraft"))
    		{
    			try
    			{
    				Class<?> fake = Class.forName("thaumcraft.common.lib.world.dim.WorldProviderOuter");
    				if(p.getClass().equals(fake))
    				{
    	    			this.textureIndex = EnumOverlay.ELDRITCH.getID();
    	    			return;
    				}
    			}
    			catch(Exception e)
    			{
    				//Silent error catching
    			}
    		}
    		
    		if(p.dimensionId == 1)
    		{
    			this.textureIndex = EnumOverlay.END.getID();
    			return;
    		}
    		
    		if(hellWorld)
    		{
    			this.textureIndex = EnumOverlay.HELL.getID();
    			return;
    		}
    		
    		if(tp.contains(Type.SANDY))
    		{
    			this.textureIndex = EnumOverlay.SURFACE_DESERT.getID();
    			return;
    		}
    		
    		if(tp.contains(Type.SWAMP))
    		{
    			this.textureIndex = EnumOverlay.SURFACE_SWAMP.getID();
    			return;
    		}
    		
    		if(y > p.getAverageGroundLevel())
    		{
    			if(tp.contains(Type.JUNGLE))
    			{
        			this.textureIndex = EnumOverlay.SURFACE_JUNGLE.getID();
        			return;
    			}
    			this.textureIndex = EnumOverlay.SURFACE_GENERIC.getID();
    			return;
    			
    		}else
    		{
    			if(tp.contains(Type.JUNGLE))
    			{
        			this.textureIndex = EnumOverlay.UNDERGROUND_JUNGLE.getID();
        			return;
    			}
    			if(tp.contains(Type.COLD))
    			{
        			this.textureIndex = EnumOverlay.UNDERGROUND_COLD.getID();
        			return;
    			}
    			this.textureIndex = EnumOverlay.UNDERGROUND_GENERIC.getID();
    			return;
    		}
    		
    	}
    }
    
    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return 9;
    }

    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int par1)
    {
        return this.dispenserContents[par1];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    public ItemStack decrStackSize(int par1, int par2)
    {
        if (this.dispenserContents[par1] != null)
        {
            ItemStack itemstack;

            if (this.dispenserContents[par1].stackSize <= par2)
            {
                itemstack = this.dispenserContents[par1];
                this.dispenserContents[par1] = null;
                this.markDirty();
                return itemstack;
            }
            else
            {
                itemstack = this.dispenserContents[par1].splitStack(par2);

                if (this.dispenserContents[par1].stackSize == 0)
                {
                    this.dispenserContents[par1] = null;
                }

                this.markDirty();
                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    public ItemStack getStackInSlotOnClosing(int par1)
    {
        if (this.dispenserContents[par1] != null)
        {
            ItemStack itemstack = this.dispenserContents[par1];
            this.dispenserContents[par1] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    public int getRandomStackFromInventory()
    {
        int i = -1;
        int j = 1;

        for (int k = 0; k < this.dispenserContents.length; ++k)
        {
            if (this.dispenserContents[k] != null && this.dispenserRandom.nextInt(j++) == 0)
            {
                i = k;
            }
        }

        return i;
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
        this.dispenserContents[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
        {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }

        this.markDirty();
    }

    /**
     * Add item stack in first available inventory slot
     */
    public int addItem(ItemStack par1ItemStack)
    {
        for (int i = 0; i < this.dispenserContents.length; ++i)
        {
            if (this.dispenserContents[i] == null || this.dispenserContents[i].stackSize == 0)
            {
                this.setInventorySlotContents(i, par1ItemStack);
                return i;
            }
        }

        return -1;
    }

    /**
     * Returns the name of the inventory.
     */
    public String getInvName()
    {
        return "Chest";
    }

    public boolean onPlaced()
    {
    	NBTTagCompound tag = new NBTTagCompound();
    	this.writeToNBT(tag);
    	MiscUtils.syncTileEntity(tag, -10);
    	return true;
    }
    		
    public void setCustomName(String par1Str)
    {
        this.customName = par1Str;
    }

    /**
     * If this returns false, the inventory name will be used as an unlocalized name, and translated into the player's
     * language. Otherwise it will be used directly.
     */
    public boolean isInvNameLocalized()
    {
        return this.customName != null;
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items",10);
        this.dispenserContents = new ItemStack[this.getSizeInventory()];
        
        this.rarity = par1NBTTagCompound.getInteger("rarity");
        this.textureIndex = par1NBTTagCompound.getInteger("textureIndex");
        this.unlocked = par1NBTTagCompound.getBoolean("unlocked");
        
        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 255;

            if (j >= 0 && j < this.dispenserContents.length)
            {
                this.dispenserContents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        if (par1NBTTagCompound.hasKey("CustomName"))
        {
            this.customName = par1NBTTagCompound.getString("CustomName");
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = new NBTTagList();

        par1NBTTagCompound.setInteger("rarity", this.rarity);
        par1NBTTagCompound.setInteger("textureIndex", this.textureIndex);
        par1NBTTagCompound.setBoolean("unlocked", this.unlocked);
        
        for (int i = 0; i < this.dispenserContents.length; ++i)
        {
            if (this.dispenserContents[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.dispenserContents[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        par1NBTTagCompound.setTag("Items", nbttaglist);

        if (this.isInvNameLocalized())
        {
            par1NBTTagCompound.setString("CustomName", this.customName);
        }
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
     * this more of a set than a get?*
     */
    public int getInventoryStackLimit()
    {
        return 64;
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    public void openChest() {}

    public void closeChest() {}

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */
    public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack)
    {
        return true;
    }

	@Override
	public String getInventoryName() {
		// TODO Auto-generated method stub
		return this.getInvName();
	}

	@Override
	public boolean hasCustomInventoryName() {
		// TODO Auto-generated method stub
		return this.isInvNameLocalized();
	}

	@Override
	public void openInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub
		
	}
	
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
    	this.readFromNBT(pkt.func_148857_g());
    	this.worldObj.markBlockRangeForRenderUpdate(xCoord-3, yCoord-3, zCoord-3, xCoord+3, yCoord+3, zCoord+3);
    }

}
