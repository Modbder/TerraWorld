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

public class ItemArtifact_SandstormInABottle extends ItemArtifact{

	public ItemArtifact_SandstormInABottle() {
		super();
	}
	
	@Override
	public boolean performJump(ItemStack par1ItemStack, EntityPlayer p) {
		boolean ret = false;
		if(par1ItemStack != null)
		{
			NBTTagCompound tag = MiscUtils.getStackTag(par1ItemStack);
			if(!tag.hasKey("TAdata"))
			{
				tag.setString("TAdata", "||jump:0");
			}
			String dataString = tag.getString("TAdata");
			DummyData[] dat = DataStorage.parseData(dataString);
			if(dat.length > 0)
			{
				int jumped = (int)Double.parseDouble(dat[0].fieldValue);
				if(jumped <= 0)
					for(int i = 0; i < 10; ++i)
					{
						for(int i1 = 0; i1 < 5; ++i1)
							p.worldObj.spawnParticle("reddust", p.posX+MathUtils.randomDouble(p.worldObj.rand)/10, p.posY+ MathUtils.randomDouble(p.worldObj.rand), p.posZ+ MathUtils.randomDouble(p.worldObj.rand)/10,1,1,0);
						p.worldObj.playSound(p.posX, p.posY, p.posZ, "dig.cloth", 1, 1+p.worldObj.rand.nextFloat(), true);
					}
				ret = jumped <= 0 && !p.onGround;
				
				jumped = 1;
				DummyData jDat = new DummyData("jump",jumped);
					tag.setString("TAdata", jDat.toString());
					par1ItemStack.setTagCompound(tag);
			}
		}
		return ret;
	}
	
	@Override
	public void onArtUpdate(ItemStack par1ItemStack, EntityPlayer p) {
		TAUtils.jumpTagUpdate(par1ItemStack, p);
	}

}
