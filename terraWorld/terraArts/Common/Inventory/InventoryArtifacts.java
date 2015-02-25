package terraWorld.terraArts.Common.Inventory;

import terraWorld.terraArts.Mod.TerraArts;
import terraWorld.terraArts.Network.TAPacketIMSG;
import DummyCore.Utils.DataStorage;
import DummyCore.Utils.DummyData;
import DummyCore.Utils.MiscUtils;
import net.minecraft.block.Block;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ReportedException;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class InventoryArtifacts implements IInventory
{
    public ItemStack[] mainInventory = new ItemStack[5];
    /** The player whose inventory this is. */
    public EntityPlayer player;
    public boolean inventoryChanged;
    public boolean isSlotClicked;

    public void readFromNBT(NBTTagCompound tag)
    {
    	for(int i = 0; i < this.mainInventory.length; ++i)
    	{
    		this.mainInventory[i] = null;
    		if(tag.hasKey("item_tag_"+i))
    		{
    			NBTTagCompound itemTag = tag.getCompoundTag("item_tag_"+i);
    			mainInventory[i] = ItemStack.loadItemStackFromNBT(itemTag);
    		}
    	}
    	
    }
    
    public void writeToNBT(NBTTagCompound tag)
    {
    	for(int i = 0; i < this.mainInventory.length; ++i)
    	{
    		if(this.mainInventory[i] != null)
    		{
    			NBTTagCompound tg = new NBTTagCompound();
    			mainInventory[i].writeToNBT(tg);
    			tag.setTag("item_tag_"+i, tg);
    		}
    	}
    }
    
    public InventoryArtifacts(EntityPlayer par1EntityPlayer)
    {
        this.player = par1EntityPlayer;
    }

	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return mainInventory[i];
	}

	@Override
    public ItemStack decrStackSize(int par1, int par2)
    {
	        if (this.mainInventory[par1] != null)
	        {
	            ItemStack itemstack;
	
	            if (this.mainInventory[par1].stackSize <= par2)
	            {
	                itemstack = this.mainInventory[par1];
	                this.mainInventory[par1] = null;
	                return itemstack;
	            }
	            else
	            {
	                itemstack = this.mainInventory[par1].splitStack(par2);
	
	                if (this.mainInventory[par1].stackSize == 0)
	                {
	                    this.mainInventory[par1] = null;
	                }
	
	                return itemstack;
	            }
	        }
	        else
	        {
	            return null;
	        }
    }

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
        if (this.mainInventory[i] != null)
        {
            ItemStack itemstack = this.mainInventory[i];
            this.mainInventory[i] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
			mainInventory[i] = itemstack;
	        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit())
	        {
	        	itemstack.stackSize = this.getInventoryStackLimit();
	        }
	}

	@Override
	public String getInventoryName() {
		return "PlayerArtifact";
	}


	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public void markDirty() {
		if(!this.player.worldObj.isRemote)
		{
			NBTTagCompound tag = new NBTTagCompound();
			this.writeToNBT(tag);
			TerraArts.network.sendTo(new TAPacketIMSG(tag).setType("TA.Sync"), (EntityPlayerMP) player);
		}
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}

	@Override
	public boolean hasCustomInventoryName() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void openInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub
		
	}

}
