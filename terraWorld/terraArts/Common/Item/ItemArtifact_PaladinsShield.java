package terraWorld.terraArts.Common.Item;

import DummyCore.Utils.MathUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemArtifact_PaladinsShield extends ItemArtifact{

	public ItemArtifact_PaladinsShield() {
		super();
	}
	
	@Override
	public float setDamage(ItemStack par1ItemStack, EntityPlayer p,
			float am) {
		return am *= 0.75F;
	}
}
