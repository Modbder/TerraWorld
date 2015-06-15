package terraWorld.terraArts.Common.Item;

import DummyCore.Utils.EnumRarityColor;
import terraWorld.terraArts.API.IArtifact;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemArtifact extends Item implements IArtifact{

	EnumRarityColor rc;
	
	public Item setRarity(EnumRarityColor c)
	{
		rc = c;
		return this;
	}
	
	public ItemArtifact() {
		super();
	}

	@Override
	public String getSpeedModifierName(ItemStack par1ItemStack) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getSpeedModifierValue(ItemStack par1ItemStack) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public float getKnockbackModifierValue(ItemStack par1ItemStack) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public float setDamage(ItemStack par1ItemStack, EntityPlayer p, float am) {
		// TODO Auto-generated method stub
		return am;
	}

	@Override
	public float setFallDistance(ItemStack par1ItemStack, EntityPlayer p,
			float am) {
		// TODO Auto-generated method stub
		return am;
	}

	@Override
	public void setJump(ItemStack par1ItemStack, EntityPlayer p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float setDamageOnAttack(ItemStack par1ItemStack, EntityPlayer p,
			EntityLivingBase base, float am) {
		// TODO Auto-generated method stub
		return am;
	}

	@Override
	public void onArtUpdate(ItemStack par1ItemStack, EntityPlayer p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean performJump(ItemStack par1ItemStack, EntityPlayer p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean holdJump(ItemStack par1ItemStack, EntityPlayer p) {
		// TODO Auto-generated method stub
		return false;
	}
	
    public String getItemStackDisplayName(ItemStack p_77653_1_)
    {
    	return rc.getRarityColor()+super.getItemStackDisplayName(p_77653_1_);
    }

}
