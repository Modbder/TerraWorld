package terraWorld.terraArts.Common.Item;

import terraWorld.terraArts.Utils.TAUtils;
import DummyCore.Utils.MathUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemArtifact_Aglet extends ItemArtifact{

	public ItemArtifact_Aglet() {
		super();
	}
	
	@Override
	public String getSpeedModifierName(ItemStack par1ItemStack) {
		// TODO Auto-generated method stub
		return "00010";
	}

	@Override
	public float getSpeedModifierValue(ItemStack par1ItemStack) {
		// TODO Auto-generated method stub
		return 0.05F;
	}
	
	@Override
	public void onArtUpdate(ItemStack par1ItemStack, EntityPlayer p) {
		TAUtils.applySpeedModifier(p, getSpeedModifierName(par1ItemStack), getSpeedModifierValue(par1ItemStack), false);
	}

}
