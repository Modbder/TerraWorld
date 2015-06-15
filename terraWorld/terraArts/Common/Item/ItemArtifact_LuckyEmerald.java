package terraWorld.terraArts.Common.Item;

import DummyCore.Utils.MathUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemArtifact_LuckyEmerald extends ItemArtifact{

	public ItemArtifact_LuckyEmerald() {
		super();
	}
	
	@Override
	public float setDamageOnAttack(ItemStack par1ItemStack, EntityPlayer p,
			EntityLivingBase base, float am) {
		if(p.worldObj.rand.nextFloat()<0.05F)
		{
			Item emerald = Items.emerald;
			p.dropItem(emerald,1);
		}
		return am;
	}
}
