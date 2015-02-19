package terraWorld.terraBuffs;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import DummyCore.Events.DummyEvent_OnClientGUIButtonPress;
import DummyCore.Utils.DataStorage;

public class BT_EventHandler {
	
	@SubscribeEvent
	public void onClientPacketRecieved(DummyEvent_OnClientGUIButtonPress event)
	{
		if(event.client_ParentClassPath.equalsIgnoreCase("terraWorld.terraBuffs.BT_GuiAnvil"))
		{
			TileEntity tile = event.presser.worldObj.getTileEntity(event.x, event.y, event.z);
			if(tile != null && tile instanceof BT_TileAnvil)
			{
				BT_TileAnvil anvil = (BT_TileAnvil) tile;
				ItemStack stk = anvil.getStackInSlot(0).copy();
				BT_Utils.addRandomEffects(stk);
				anvil.setInventorySlotContents(0, null);
				anvil.setInventorySlotContents(1, stk);
				anvil.markDirty();
				int cost = Integer.parseInt(event.additionalData[0].fieldValue);
				if(!event.presser.capabilities.isCreativeMode)
					event.presser.experienceLevel -= cost;
			}
		}
	}

}
