package terraWorld.terraArts.Common.Item;

import DummyCore.Utils.MathUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemArtifact_GoldenCross extends ItemArtifact{

	public ItemArtifact_GoldenCross() {
		super();
	}
	
	@Override
	public float setDamage(ItemStack par1ItemStack, EntityPlayer p,
			float am) {
		p.hurtResistantTime = 60;
		p.hurtTime = 60;
		return am;
	}
}
