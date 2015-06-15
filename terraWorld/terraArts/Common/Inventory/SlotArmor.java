package terraWorld.terraArts.Common.Inventory;

import terraWorld.terraArts.API.IArtifact;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

class SlotArtifacts extends Slot
{
	
    public SlotArtifacts(IInventory par2IInventory, int par3, int par4, int par5)
    {
        super(par2IInventory, par3, par4, par5);
    }

    public int getSlotStackLimit()
    {
        return 1;
    }

    public boolean isItemValid(ItemStack par1ItemStack)
    {
        Item item = null;
        if(par1ItemStack != null)
        	item = par1ItemStack.getItem();
        boolean shouldAddItem = true;
        for(int i = 0; i < this.inventory.getSizeInventory(); ++i)
        {
        	ItemStack stack = this.inventory.getStackInSlot(i);
        	if(stack != null && stack.areItemStacksEqual(stack, par1ItemStack))
        		shouldAddItem = false;
        }
        return shouldAddItem && (item == null || item instanceof IArtifact);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getBackgroundIconIndex()
    {
        return null;
    }
}
