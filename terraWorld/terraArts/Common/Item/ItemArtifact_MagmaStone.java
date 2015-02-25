package terraWorld.terraArts.Common.Item;

import DummyCore.Utils.MathUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemArtifact_MagmaStone extends ItemArtifact{

	public ItemArtifact_MagmaStone() {
		super();
	}
	
	@Override
	public float setDamageOnAttack(ItemStack par1ItemStack, EntityPlayer p,
			EntityLivingBase base, float am) {
		if(p.worldObj.rand.nextFloat()<0.25F)
		{
			base.setFire(4);
		}
		return am;
	}
}
