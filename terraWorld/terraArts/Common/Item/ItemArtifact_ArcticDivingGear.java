package terraWorld.terraArts.Common.Item;

import terraWorld.terraArts.Utils.TAUtils;
import DummyCore.Utils.MathUtils;
import DummyCore.Utils.MiscUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockIce;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class ItemArtifact_ArcticDivingGear extends ItemArtifact{

	public ItemArtifact_ArcticDivingGear() {
		super();
	}
	
	
	@Override
	public String getSpeedModifierName(ItemStack par1ItemStack) {
		// TODO Auto-generated method stub
		return "0001A";
	}

	@Override
	public float getSpeedModifierValue(ItemStack par1ItemStack) {
		// TODO Auto-generated method stub
		return 0.8F;
	}
	
	
	@Override
	public void onArtUpdate(ItemStack par1ItemStack, EntityPlayer p) {
		if(p.isInsideOfMaterial(Material.water))
		{
			p.addPotionEffect(new PotionEffect(Potion.nightVision.id,20,0,true));
			if(p.getAir() < 320 && p.ticksExisted % 3 == 0)
			{
				p.setAir(p.getAir()+1);
			}
		}else
		{
		}
		Block b = MiscUtils.getBlock(p.worldObj, (int)p.posX, (int)p.posY-1, (int)p.posZ);
		if(b != null && b instanceof BlockIce)
		{
			TAUtils.applySpeedModifier(p, getSpeedModifierName(par1ItemStack), getSpeedModifierValue(par1ItemStack), false);
		}else
		{
			TAUtils.applySpeedModifier(p, getSpeedModifierName(par1ItemStack), getSpeedModifierValue(par1ItemStack), true);
		}
		if(b != null && b.getMaterial() == Material.water)
		{
			TAUtils.applySpeedModifier(p, getSpeedModifierName(par1ItemStack), getSpeedModifierValue(par1ItemStack), false);
		}else
		{
			TAUtils.applySpeedModifier(p, getSpeedModifierName(par1ItemStack), getSpeedModifierValue(par1ItemStack), true);
		}
	}
	
}
