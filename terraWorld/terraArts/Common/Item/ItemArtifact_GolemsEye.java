package terraWorld.terraArts.Common.Item;

import terraWorld.terraArts.Network.TAPacketHandler;
import DummyCore.Utils.MathUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemArtifact_GolemsEye extends ItemArtifact{

	public ItemArtifact_GolemsEye() {
		super();
	}
	
	@Override
	public float setDamageOnAttack(ItemStack par1ItemStack, EntityPlayer p,
			EntityLivingBase base, float am) {
		if(p.worldObj.rand.nextFloat() < 0.3F)
		{
			for(int i = 0; i < 20; ++i)
			{
				TAPacketHandler.spawnParticleOnServer("magicCrit", base.posX+MathUtils.randomFloat(p.worldObj.rand), base.posY+1+MathUtils.randomFloat(p.worldObj.rand), base.posZ+MathUtils.randomFloat(p.worldObj.rand), 0, 0, 0, 16, p.dimension);
			}
			am *= 2;
		}
		return am;
	}
}
