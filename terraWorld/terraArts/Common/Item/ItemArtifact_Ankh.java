package terraWorld.terraArts.Common.Item;

import java.util.Collection;

import DummyCore.Utils.MathUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemArtifact_Ankh extends ItemArtifact{

	public ItemArtifact_Ankh() {
		super();
	}
	
	@Override
	public void onArtUpdate(ItemStack par1ItemStack, EntityPlayer p) {
		Collection coll = p.getActivePotionEffects();
		if(!coll.isEmpty())
		{
			for(int i = 0; i < coll.size(); ++i)
			{
				PotionEffect eff = (PotionEffect) coll.toArray()[i];
				if(Potion.potionTypes[eff.getPotionID()].isBadEffect())
				{
					p.removePotionEffect(eff.getPotionID());
				}
			}
		}
	}

}
