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

public class ItemArtifact_FluffyBoots extends ItemArtifact{

	public ItemArtifact_FluffyBoots() {
		super();
	}
	
	@Override
	public String getSpeedModifierName(ItemStack par1ItemStack) {
		// TODO Auto-generated method stub
		return "00014";
	}

	@Override
	public float getSpeedModifierValue(ItemStack par1ItemStack) {
		// TODO Auto-generated method stub
		return 0.5F;
	}
	
	@Override
	public boolean holdJump(ItemStack par1ItemStack, EntityPlayer p) {
		boolean ret = false;
		if(par1ItemStack != null)
		{
			NBTTagCompound tag = MiscUtils.getStackTag(par1ItemStack);
			if(!tag.hasKey("data"))
			{
				tag.setString("data", "||jump:100");
			}
			String dataString = tag.getString("data");
			DummyData[] dat = DataStorage.parseData(dataString);
			if(dat.length > 1)
			{
				int jumped = Integer.parseInt(dat[1].fieldValue);
				if(jumped > 0)
				{
					p.motionY += 0.08F;
					for(int i = 0; i < 10; ++i)
					{
						p.worldObj.spawnParticle("reddust", p.posX, p.posY-1.3D, p.posZ, 0,0.8D,1);
						p.worldObj.playSound(p.posX, p.posY, p.posZ, "dig.snow", 1, 0.1F, true);
					}
					jumped -= 1;
				}
				ret = jumped > 0 && !p.onGround;
				int jumped1 = Integer.parseInt(dat[0].fieldValue);
				DummyData jDat = new DummyData("jump",jumped);
				DummyData jDat1 = new DummyData("sprint",jumped1);
				DataStorage.addDataToString(jDat1);
				DataStorage.addDataToString(jDat);
				tag.setString("data", DataStorage.getDataString());
					par1ItemStack.setTagCompound(tag);
			}
		}
		return ret;
	}
	
	@Override
	public void onArtUpdate(ItemStack par1ItemStack, EntityPlayer p) {
		if(par1ItemStack != null)
		{
			NBTTagCompound tag = MiscUtils.getStackTag(par1ItemStack);
			if(!tag.hasKey("data"))
			{
				tag.setString("data", "||sprint:0||jump:0");
			}
			String dataString = tag.getString("data");
			DummyData[] dat = DataStorage.parseData(dataString);
			if(dat.length > 1)
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
				int jumped1 = Integer.parseInt(dat[1].fieldValue);
				if(p.onGround)
					jumped1 = (int) (1.6*20);
				DummyData jDat = new DummyData("jump",jumped1);
				DummyData jDat1 = new DummyData("sprint",jumped);
				DataStorage.addDataToString(jDat1);
				DataStorage.addDataToString(jDat);
				tag.setString("data", DataStorage.getDataString());
				par1ItemStack.setTagCompound(tag);
			}
		}
		
	}

}
