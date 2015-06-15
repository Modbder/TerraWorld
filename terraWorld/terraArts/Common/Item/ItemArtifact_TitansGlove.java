package terraWorld.terraArts.Common.Item;

import DummyCore.Utils.MathUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemArtifact_TitansGlove extends ItemArtifact{

	public ItemArtifact_TitansGlove() {
		super();
	}
	
	@Override
	public float setDamageOnAttack(ItemStack par1ItemStack, EntityPlayer p,
			EntityLivingBase base, float am) {
		float i = 1.7F;
		base.addVelocity((double)(-MathHelper.sin(p.rotationYaw * (float)Math.PI / 180.0F) * (float)i * 0.5F), 0.1D, (double)(MathHelper.cos(p.rotationYaw * (float)Math.PI / 180.0F) * (float)i * 0.5F));
		return am;
	}
}
