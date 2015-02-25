package terraWorld.terraArts.Common.Item;

import DummyCore.Utils.MathUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemArtifact_BandOfRegen extends ItemArtifact{

	public ItemArtifact_BandOfRegen() {
		super();
	}
	
	@Override
	public void onArtUpdate(ItemStack par1ItemStack, EntityPlayer p) {
		int ticks = p.ticksExisted % 120;
		if(ticks == 0 && p.getHealth() < p.getMaxHealth())
		{
			p.heal(1);
		}
	}

}
