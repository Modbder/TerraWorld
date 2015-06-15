package terraWorld.terraArts.Common.Item;

import terraWorld.terraArts.API.IArtifact;
import terraWorld.terraArts.Common.Inventory.InventoryArtifacts;
import terraWorld.terraArts.Utils.TAUtils;
import DummyCore.Utils.DataStorage;
import DummyCore.Utils.DummyData;
import DummyCore.Utils.MathUtils;
import DummyCore.Utils.MiscUtils;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemArtifact_LavaAmulet extends ItemArtifact{

	public ItemArtifact_LavaAmulet() {
		super();
	}
	
	@Override
	public void onArtUpdate(ItemStack par1ItemStack, EntityPlayer p) {
		if(par1ItemStack != null)
		{
			NBTTagCompound tag = MiscUtils.getStackTag(par1ItemStack);
			if(!tag.hasKey("data"))
			{
				tag.setString("data", "||sprint:140");
			}
			String dataString = tag.getString("data");
			DummyData[] dat = DataStorage.parseData(dataString);
			if(dat.length > 0)
			{
				int jumped = Integer.parseInt(dat[0].fieldValue);
				Material material = p.worldObj.getBlock((int)p.posX, (int)p.posY-1, (int)p.posZ).getMaterial();
				boolean isInLava = false;
				if(material == Material.lava && p.motionY < 0 && !p.isInWater() && !p.isSneaking())
				{
					if(jumped > 0)
					p.addPotionEffect(new PotionEffect(Potion.fireResistance.id,10,0,true));
					jumped -= 1;
					isInLava = true;
				}
				material = p.worldObj.getBlock((int)p.posX, (int)p.posY+1, (int)p.posZ).getMaterial();
				if(material == Material.lava && p.motionY < 0 && !p.isInWater() && !p.isSneaking() && !isInLava)
				{
					if(jumped > 0)
					p.addPotionEffect(new PotionEffect(Potion.fireResistance.id,10,0,true));
					jumped -= 1;
					isInLava = true;
				}
				material = p.worldObj.getBlock((int)p.posX, (int)p.posY, (int)p.posZ).getMaterial();
				if(material == Material.lava && p.motionY < 0 && !p.isInWater() && !p.isSneaking() && !isInLava)
				{
					if(jumped > 0)
					p.addPotionEffect(new PotionEffect(Potion.fireResistance.id,10,0,true));
					jumped -= 1;
					isInLava = true;
				}
				if(jumped < -1)
				{
					jumped = 0;
				}
				int maxLavaResistance = 140;
				if(TAUtils.playerInvTable.containsKey(p.getCommandSenderName()))
				{
					InventoryArtifacts ia = (InventoryArtifacts) TAUtils.playerInvTable.get(p.getCommandSenderName());
					for(int i = 0; i < 5; ++i)
					{
						ItemStack stack = ia.mainInventory[i];
						if(stack != null && stack.getItem() instanceof IArtifact)
						{
							IArtifact art = (IArtifact) stack.getItem();
							if(art instanceof ItemArtifact_LavaWalkers)
							{
								maxLavaResistance += 140;
								break;
							}
						}
					}
				}
				if(!isInLava && jumped < maxLavaResistance)
				{
					++jumped;
				}
				DummyData jDat = new DummyData("sprint",jumped);
				tag.setString("data", jDat.toString());
				par1ItemStack.setTagCompound(tag);
			}
		}
		
	}

}
