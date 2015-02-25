package terraWorld.terraArts.Common.Item;

import terraWorld.terraArts.Common.Entity.EntityFallingStar;
import DummyCore.Utils.MathUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemArtifact_StarVeil extends ItemArtifact{

	public ItemArtifact_StarVeil() {
		super();
	}
	
	@Override
	public float setDamage(ItemStack par1ItemStack, EntityPlayer p,
			float am) {
		p.hurtResistantTime = 60;
		p.hurtTime = 60;
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
