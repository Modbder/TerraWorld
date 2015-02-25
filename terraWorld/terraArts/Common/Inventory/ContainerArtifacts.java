package terraWorld.terraArts.Common.Inventory;

import cpw.mods.fml.common.FMLCommonHandler;
import terraWorld.terraArts.Utils.TAUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public class ContainerArtifacts extends Container
{

    public boolean isLocalWorld;
    protected final EntityPlayer thePlayer;
    public InventoryArtifacts inventory;
    public boolean isSlotClicked;
    public boolean isSlotClicked2;

    public ContainerArtifacts(InventoryPlayer par1InventoryPlayer, boolean par2, EntityPlayer par3EntityPlayer)
    {
        this.isLocalWorld = par2;
        this.thePlayer = par3EntityPlayer;
        if(!isLocalWorld)
        	inventory = (InventoryArtifacts) TAUtils.playerInvTable.get(par3EntityPlayer.getCommandSenderName());
        else
        {
        	inventory = new InventoryArtifacts(thePlayer);
        	TAUtils.clientInventory = inventory;
        }
        int i;
        int j;
        for (i = 0; i < 5; ++i)
        {
        	  this.addSlotToContainer(new SlotArtifacts(inventory, i, 26+18+i*18, 34));
        }
        for (i = 0; i < 3; ++i)
        {
            for (j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(par1InventoryPlayer, j + (i + 1) * 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, i, 8 + i * 18, 142));
        }
   }

    public void onContainerClosed(EntityPlayer par1EntityPlayer)
    {
        super.onContainerClosed(par1EntityPlayer);
    }

    public boolean canInteractWith(EntityPlayer par1EntityPlayer)
    {
        return true;
    }

    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        return null;
    }
    
    public ItemStack slotClick(int par1, int par2, int par3, EntityPlayer par4EntityPlayer)
    {
    	
		ItemStack clickStack = super.slotClick(par1, par2, par3, par4EntityPlayer);
		return clickStack;
    }
    
    protected void retrySlotClick(int par1, int par2, boolean par3, EntityPlayer par4EntityPlayer)
    {
        this.slotClick(par1, par2, 1, par4EntityPlayer);
    }

}
