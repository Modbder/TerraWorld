package terraWorld.terraArts.Common.Item;

import terraWorld.terraArts.API.IArtifact;
import terraWorld.terraArts.Common.Entity.EntityFallingStar;
import terraWorld.terraArts.Common.Inventory.InventoryArtifacts;
import terraWorld.terraArts.Utils.TAUtils;
import DummyCore.Utils.MathUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemArtifact_StarCloak extends ItemArtifact{

	public ItemArtifact_StarCloak() {
		super();
	}
	
	@Override
	public float setDamage(ItemStack par1ItemStack, EntityPlayer p,
			float am) {
		boolean shouldWork = true;
		if(TAUtils.playerInvTable.containsKey(p.getCommandSenderName()))
		{
			InventoryArtifacts ia = (InventoryArtifacts) TAUtils.playerInvTable.get(p.getCommandSenderName());
			for(int i = 0; i < 5; ++i)
			{
				ItemStack stack = ia.mainInventory[i];
				if(stack != null && stack.getItem() instanceof IArtifact)
				{
					IArtifact art = (IArtifact) stack.getItem();
					if(art instanceof ItemArtifact_StarVeil)
					{
						shouldWork = false;
						break;
					}
				}
			}
		}
		if(shouldWork)
		for(int i = 0; i < 1+p.worldObj.rand.nextInt(5); ++i)
		{
			EntityFallingStar star = new EntityFallingStar(p.worldObj, p);
			star.setPositionAndRotation(p.posX+MathUtils.randomDouble(p.worldObj.rand)*4, p.posY+32,  p.posZ+MathUtils.randomDouble(p.worldObj.rand)*4, 0, 0);
			if(!p.worldObj.isRemote)
				p.worldObj.spawnEntityInWorld(star);
			star.motionX = 0;
			star.motionZ = 0;
		}
		return am;
	}
}
