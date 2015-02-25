package terraWorld.terraArts.Common.Item;

import terraWorld.terraArts.Network.TAPacketHandler;
import terraWorld.terraArts.Utils.TAUtils;
import DummyCore.Utils.DataStorage;
import DummyCore.Utils.DummyData;
import DummyCore.Utils.MathUtils;
import DummyCore.Utils.MiscUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemArtifact_HermesBoots extends ItemArtifact{

	public ItemArtifact_HermesBoots() {
		super();
	}
	
	@Override
	public String getSpeedModifierName(ItemStack par1ItemStack) {
		// TODO Auto-generated method stub
		return "00013";
	}

	@Override
	public float getSpeedModifierValue(ItemStack par1ItemStack) {
		// TODO Auto-generated method stub
		return 0.5F;
	}
	
	@Override
	public void onArtUpdate(ItemStack par1ItemStack, EntityPlayer p) {
		if(par1ItemStack != null)
		{
			NBTTagCompound tag = MiscUtils.getStackTag(par1ItemStack);
			if(!tag.hasKey("data"))
			{
				tag.setString("data", "||sprint:0");
			}
			String dataString = tag.getString("data");
			DummyData[] dat = DataStorage.parseData(dataString);
			if(dat.length > 0)
			{
				int jumped = Integer.parseInt(dat[0].fieldValue);
				if(jumped > 50)
				{
					if(!p.isSprinting())
					{
						jumped = 0;
					}else
					{
					}
					TAPacketHandler.spawnParticleOnServer("reddust", p.posX, p.posY, p.posZ, 0, 1, 1, 16, p.dimension);
					TAPacketHandler.playSoundOnServer("dig.cloth",p.posX, p.posY, p.posZ, 1, 2, 16, p.dimension);
					TAUtils.applySpeedModifier(p, getSpeedModifierName(par1ItemStack), getSpeedModifierValue(par1ItemStack), false);
				}else
				{
					if(p.isSprinting())
					{
						if(jumped <= 50)
							++jumped;
					}else
					{
						jumped = 0;
					}
					TAUtils.applySpeedModifier(p, getSpeedModifierName(par1ItemStack), getSpeedModifierValue(par1ItemStack), true);
				}
				DummyData jDat = new DummyData("sprint",jumped);
				tag.setString("data", jDat.toString());
				par1ItemStack.setTagCompound(tag);
			}
		}
		
	}

}
