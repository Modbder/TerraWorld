package terraWorld.terraArts.Common.Item;

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

public class ItemArtifact_Wings extends ItemArtifact{

	public ItemArtifact_Wings() {
		super();
	}
	
	@Override
	public boolean holdJump(ItemStack par1ItemStack, EntityPlayer p) {
		boolean ret = false;
		if(par1ItemStack != null)
		{
			NBTTagCompound tag = MiscUtils.getStackTag(par1ItemStack);
			if(!tag.hasKey("TAdata"))
			{
				tag.setString("TAdata", "||jump:100");
			}
			String dataString = tag.getString("TAdata");
			DummyData[] dat = DataStorage.parseData(dataString);
			if(dat.length > 0)
			{
				int jumped = (int)Double.parseDouble(dat[0].fieldValue);
				if(jumped > 0)
				{
					p.motionY += 0.08F;
						if(p.worldObj.rand.nextFloat() < 0.2F)
							p.worldObj.playSound(p.posX, p.posY, p.posZ, "dig.snow", 1, 0.001F, true);
					jumped -= 1;
				}else
				{
					if(p.motionY < -0.2D)
					{
						p.motionY = -0.2D;
					}
				}
				ret = jumped > 0 && !p.onGround;
				
				DummyData jDat = new DummyData("jump",jumped);
					tag.setString("TAdata", jDat.toString());
					par1ItemStack.setTagCompound(tag);
			}
		}
		return ret;
	}
	
	@Override
	public float setFallDistance(ItemStack par1ItemStack, EntityPlayer p,
			float am) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void onArtUpdate(ItemStack par1ItemStack, EntityPlayer p) {
		TAUtils.jumpTagUpdate(par1ItemStack, p);
	}

}
