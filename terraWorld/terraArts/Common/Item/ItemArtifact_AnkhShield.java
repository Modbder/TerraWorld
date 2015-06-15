package terraWorld.terraArts.Common.Item;

import java.util.Collection;

import terraWorld.terraArts.Utils.TAUtils;
import DummyCore.Utils.MathUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemArtifact_AnkhShield extends ItemArtifact{

	public ItemArtifact_AnkhShield() {
		super();
	}
	
	@Override
	public String getSpeedModifierName(ItemStack par1ItemStack) {
		// TODO Auto-generated method stub
		return "00016";
	}
	
	@Override
	public float getKnockbackModifierValue(ItemStack par1ItemStack) {
		// TODO Auto-generated method stub
		return 1F;
	}

	@Override
	public float getSpeedModifierValue(ItemStack par1ItemStack) {
		// TODO Auto-generated method stub
		return 0F;
	}
	
	@Override
	public void onArtUpdate(ItemStack par1ItemStack, EntityPlayer p) {
		if(p.isBurning())
		{
			p.extinguish();
		}
		TAUtils.applyKnokbackModifier(p, getSpeedModifierName(par1ItemStack), getKnockbackModifierValue(par1ItemStack), false);
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
