package terraWorld.terraArts.Utils;

import terraWorld.terraArts.API.IArtifact;
import terraWorld.terraArts.Common.Inventory.InventoryArtifacts;
import terraWorld.terraArts.Mod.TerraArts;
import terraWorld.terraArts.Network.TAPacketHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class TAEventHandler {
	
	@SubscribeEvent(priority = EventPriority.HIGH)
	public void event_hurt(LivingHurtEvent event)
	{
		EntityLivingBase base = event.entityLiving;
		DamageSource src = event.source;
		if(src instanceof EntityDamageSource && src.getSourceOfDamage() instanceof EntityLivingBase)
		{
			EntityLivingBase eBase = (EntityLivingBase) src.getSourceOfDamage();
			if(eBase instanceof EntityPlayer && !eBase.worldObj.isRemote)
			{
				EntityPlayer p = (EntityPlayer) eBase;
				if(TAUtils.playerInvTable.containsKey(p.getCommandSenderName()))
				{
					InventoryArtifacts ia = (InventoryArtifacts) TAUtils.playerInvTable.get(p.getCommandSenderName());
					for(int i = 0; i < 5; ++i)
					{
						ItemStack stack = ia.mainInventory[i];
						if(stack != null && stack.getItem() instanceof IArtifact)
						{
							IArtifact art = (IArtifact) stack.getItem();
							event.ammount = art.setDamageOnAttack(stack, p, base, event.ammount);
						}
					}
				}
			}
		}
		if(base instanceof EntityPlayer && !event.entityLiving.worldObj.isRemote)
		{
			EntityPlayer p = (EntityPlayer) base;
			if(TAUtils.playerInvTable.containsKey(p.getCommandSenderName()))
			{
				InventoryArtifacts ia = (InventoryArtifacts) TAUtils.playerInvTable.get(p.getCommandSenderName());
				for(int i = 0; i < 5; ++i)
				{
					ItemStack stack = ia.mainInventory[i];
					if(stack != null && stack.getItem() instanceof IArtifact)
					{
						IArtifact art = (IArtifact) stack.getItem();
						event.ammount = art.setDamage(stack, p, event.ammount);
					}
				}
			}
		}
	}
	
	@SubscribeEvent(priority = EventPriority.HIGH)
	public void event_fall(LivingFallEvent event)
	{
		EntityLivingBase base = event.entityLiving;
		if(base instanceof EntityPlayer && !event.entityLiving.worldObj.isRemote)
		{
			EntityPlayer p = (EntityPlayer) base;
			if(TAUtils.playerInvTable.containsKey(p.getCommandSenderName()))
			{
				InventoryArtifacts ia = (InventoryArtifacts) TAUtils.playerInvTable.get(p.getCommandSenderName());
				for(int i = 0; i < 5; ++i)
				{
					ItemStack stack = ia.mainInventory[i];
					if(stack != null && stack.getItem() instanceof IArtifact)
					{
						IArtifact art = (IArtifact) stack.getItem();
						event.distance = art.setFallDistance(stack, p, event.distance);
					}
				}
			}
		}
	}
	
	@SubscribeEvent(priority = EventPriority.HIGH)
	public void event_jump(LivingJumpEvent event)
	{
		Entity base = event.entity;
		if(base instanceof EntityPlayer)
		{
			EntityPlayer p = (EntityPlayer) base;
			TerraArts.network.sendToServer(TAPacketHandler.getPacketFor("TA.Jump", "||username:"+p.getCommandSenderName()));
			if(TAUtils.playerInvTable.containsKey(p.getCommandSenderName()))
			{
				InventoryArtifacts ia = (InventoryArtifacts) TAUtils.playerInvTable.get(p.getCommandSenderName());
				for(int i = 0; i < 5; ++i)
				{
					ItemStack stack = ia.mainInventory[i];
					if(stack != null && stack.getItem() instanceof IArtifact)
					{
						IArtifact art = (IArtifact) stack.getItem();
						art.setJump(stack, p);
					}
				}
			}
		}
	}
	
	

}
