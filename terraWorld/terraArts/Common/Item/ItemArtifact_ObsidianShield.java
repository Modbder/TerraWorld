package terraWorld.terraArts.Common.Item;

import terraWorld.terraArts.Utils.TAUtils;
import DummyCore.Utils.MathUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemArtifact_ObsidianShield extends ItemArtifact{

	public ItemArtifact_ObsidianShield() {
		super();
	}
	
	@Override
	public String getSpeedModifierName(ItemStack par1ItemStack) {
		// TODO Auto-generated method stub
		return "00012";
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
	}

}
