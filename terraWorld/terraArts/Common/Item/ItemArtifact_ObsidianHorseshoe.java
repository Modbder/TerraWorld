package terraWorld.terraArts.Common.Item;

import java.util.Collection;

import DummyCore.Utils.MathUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemArtifact_ObsidianHorseshoe extends ItemArtifact{

	public ItemArtifact_ObsidianHorseshoe() {
		super();
	}
	
	@Override
	public float setFallDistance(ItemStack par1ItemStack, EntityPlayer p,
			float am) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void onArtUpdate(ItemStack par1ItemStack, EntityPlayer p) {
		if(p.isBurning())
		{
			p.extinguish();
		}
	}

}
