package terraWorld.terraArts.API;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IArtifact {
	
	public abstract void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5);
	
	public abstract String getSpeedModifierName(ItemStack par1ItemStack);
	
	public abstract float getSpeedModifierValue(ItemStack par1ItemStack);
	
	public abstract float setDamage(ItemStack par1ItemStack,EntityPlayer p, float am);
	
	public abstract float setFallDistance(ItemStack par1ItemStack,EntityPlayer p, float am);
	
	public abstract void setJump(ItemStack par1ItemStack,EntityPlayer p);
	
	public abstract void onArtUpdate(ItemStack par1ItemStack,EntityPlayer p);
	
	public abstract float setDamageOnAttack(ItemStack par1ItemStack,EntityPlayer p,EntityLivingBase base, float am);

	public abstract float getKnockbackModifierValue(ItemStack par1ItemStack);
	
	public abstract boolean performJump(ItemStack par1ItemStack,EntityPlayer p);
	
	public abstract boolean holdJump(ItemStack par1ItemStack,EntityPlayer p);
}
