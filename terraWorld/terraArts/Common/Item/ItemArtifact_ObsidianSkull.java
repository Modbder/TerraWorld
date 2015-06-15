package terraWorld.terraArts.Common.Item;

import java.util.Collection;

import DummyCore.Utils.MathUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemArtifact_ObsidianSkull extends ItemArtifact{

	public ItemArtifact_ObsidianSkull() {
		super();
	}
	
	@Override
	public void onArtUpdate(ItemStack par1ItemStack, EntityPlayer p) {
		if(p.isBurning())
		{
			p.extinguish();
		}
	}

}
