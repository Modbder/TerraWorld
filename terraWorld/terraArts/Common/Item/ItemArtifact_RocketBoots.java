package terraWorld.terraArts.Common.Item;

import DummyCore.Utils.DataStorage;
import DummyCore.Utils.DummyData;
import DummyCore.Utils.MathUtils;
import DummyCore.Utils.MiscUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemArtifact_RocketBoots extends ItemArtifact{

	public ItemArtifact_RocketBoots() {
		super();
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
			if(dat.length > 0)
			{
				int jumped = Integer.parseInt(dat[0].fieldValue);
				if(jumped > 0)
				{
					p.motionY += 0.08F;
					for(int i = 0; i < 10; ++i)
					{
							p.worldObj.spawnParticle("flame", p.posX, p.posY-1.3D, p.posZ, MathUtils.randomDouble(p.worldObj.rand)/20, -p.worldObj.rand.nextFloat()/2, MathUtils.randomDouble(p.worldObj.rand)/20);
						p.worldObj.playSound(p.posX, p.posY, p.posZ, "game.tnt.primed", 1, 1+p.worldObj.rand.nextFloat(), true);
					}
					jumped -= 1;
				}
				ret = jumped > 0 && !p.onGround;
				
				DummyData jDat = new DummyData("jump",jumped);
					tag.setString("data", jDat.toString());
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
				tag.setString("data", "||jump:100");
			}
			String dataString = tag.getString("data");
			DummyData[] dat = DataStorage.parseData(dataString);
			if(dat.length > 0)
			{
				int jumped = Integer.parseInt(dat[0].fieldValue);
				if(p.onGround)
					jumped = (int) (1.6*20);
				DummyData jDat = new DummyData("jump",jumped);
				tag.setString("data", jDat.toString());
				par1ItemStack.setTagCompound(tag);
			}
		}
	}

}
