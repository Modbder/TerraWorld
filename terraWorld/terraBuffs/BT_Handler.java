package terraWorld.terraBuffs;

import java.util.List;
import java.util.UUID;

import DummyCore.Utils.DataStorage;
import DummyCore.Utils.DummyData;
import DummyCore.Utils.EnumRarityColor;
import DummyCore.Utils.MiscUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

public class BT_Handler{

	@SubscribeEvent
	public void onCrafting(ItemCraftedEvent event) {
		EntityPlayer player = event.player;
		ItemStack item = event.crafting;
		if(!player.worldObj.isRemote && item != null)
		{
			BT_Utils.addRandomEffects(item);
		}
	}
	
	@SubscribeEvent
	public void event_ItemTooltipEvent(ItemTooltipEvent event)
	{
		ItemStack stack = event.itemStack;
		if(stack.hasTagCompound() && stack.getTagCompound().hasKey("BT_TagList"))
		{
			NBTTagCompound tag = (NBTTagCompound)stack.getTagCompound().getTag("BT_TagList");
			if(tag.hasKey("BT_Buffs"))
			{
				String s = tag.getString("BT_Buffs");
				DummyData[] d = DataStorage.parseData(s);
				for(int i = 0; i < d.length; ++i)
				{
					DummyData data = d[i];
					String name = data.fieldName;
					String nameLetter1 = String.valueOf(name.charAt(0)).toUpperCase();
					String mainName = nameLetter1+name.substring(1, name.length());
					double da = Double.parseDouble(data.fieldValue);
					da *= 100;
					if(da > 0)
					event.toolTip.add(EnumRarityColor.GOOD.getRarityColor()+"+"+(int)da+"% "+mainName);
					else
					event.toolTip.add(EnumRarityColor.ULTIMATE.getRarityColor()+(int)da+"% "+mainName);	
				}
			}
		}
	}
	
	@SubscribeEvent
	public void event_AttackEntityEvent(AttackEntityEvent event)
	{
		EntityPlayer p = event.entityPlayer;
		World w = p.worldObj;
		if(p.getCurrentEquippedItem() != null && BT_Utils.itemHasEffect(p.getCurrentEquippedItem()) && !w.isRemote)
		{
			ItemStack stack = p.getCurrentEquippedItem();
			String dummyDataString = stack.getTagCompound().getCompoundTag("BT_TagList").getString("BT_Buffs");
			DummyData[] d = DataStorage.parseData(dummyDataString);
			for(int i1 = 0; i1 < d.length; ++i1)
			{
				DummyData data = d[i1];
				String name = data.fieldName;
				double value = Double.parseDouble(data.fieldValue);
				if(name.contains("durability"))
				{
					if(value > 0 && w.rand.nextDouble() < value && stack.getItemDamage() > 0)
					{
						stack.setItemDamage(stack.getItemDamage()-1);
					}
					if(value < 0 && w.rand.nextDouble() < -value)
					{
						stack.setItemDamage(stack.getItemDamage()+1);
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public void event_HarvestCheck(BreakEvent event)
	{
		EntityPlayer p = event.getPlayer();
		World w = p.worldObj;
		if(p != null && p.getCurrentEquippedItem() != null && BT_Utils.itemHasEffect(p.getCurrentEquippedItem()) && !w.isRemote)
		{
			ItemStack stack = p.getCurrentEquippedItem();
			String dummyDataString = stack.getTagCompound().getCompoundTag("BT_TagList").getString("BT_Buffs");
			DummyData[] d = DataStorage.parseData(dummyDataString);
			for(int i1 = 0; i1 < d.length; ++i1)
			{
				DummyData data = d[i1];
				String name = data.fieldName;
				double value = Double.parseDouble(data.fieldValue);
				if(name.contains("durability"))
				{
					if(value > 0 && w.rand.nextDouble() < value && stack.getItemDamage() > 0)
					{
						stack.setItemDamage(stack.getItemDamage()-1);
					}
					if(value < 0 && w.rand.nextDouble() < -value)
					{
						stack.setItemDamage(stack.getItemDamage()+1);
					}
				}
			}
		}
	}
	
	@SubscribeEvent(priority=EventPriority.LOW)
	public void event_LivingHurtEvent(LivingHurtEvent event)
	{
		DamageSource dms = event.source;
		if(dms instanceof EntityDamageSource)
		{
			EntityDamageSource edms = (EntityDamageSource)dms;
			if(edms.damageType.contains("player") && edms.getSourceOfDamage() instanceof EntityPlayer)
			{
				EntityPlayer p = (EntityPlayer) edms.getSourceOfDamage();
				if(p.getCurrentEquippedItem() != null && BT_Utils.itemHasEffect(p.getCurrentEquippedItem()))
				{
					ItemStack stack = p.getCurrentEquippedItem();
					String dummyDataString = stack.getTagCompound().getCompoundTag("BT_TagList").getString("BT_Buffs");
					DummyData[] d = DataStorage.parseData(dummyDataString);
					for(int i1 = 0; i1 < d.length; ++i1)
					{
						DummyData data = d[i1];
						String name = data.fieldName;
						double value = Double.parseDouble(data.fieldValue);
						if(name.contains("damage"))
						{
							float dam = event.ammount;
							if(value < 0)
							{
								value = -value;
								float mainDam = dam*=value;
								event.ammount -= mainDam;
							}else
							{
								float mainDam = dam*=value;
								event.ammount += mainDam;
							}
						}
						if(name.contains("crit"))
						{
							if(p.worldObj.rand.nextDouble() <= value)
							{
								event.ammount*=3;
							}
						}
						if(name.contains("speed"))
						{
							MiscUtils.damageEntityIgnoreEvent(event.entityLiving, edms, event.ammount);
							int damageResistance = 20;
							damageResistance -= value*40;
							event.entityLiving.hurtResistantTime = damageResistance;
							event.entityLiving.hurtTime = damageResistance;
							p.swingProgress -= value*100;
							p.swingProgressInt -= value*100;
							event.setCanceled(true);
						}
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public void event_BreakSpeed(BreakSpeed event)
	{
		EntityPlayer p = event.entityPlayer;
		World w = p.worldObj;
		Block b = event.block;
		if(p.getCurrentEquippedItem() != null && BT_Utils.itemHasEffect(p.getCurrentEquippedItem()))
		{
			ItemStack stack = p.getCurrentEquippedItem();
			String dummyDataString = stack.getTagCompound().getCompoundTag("BT_TagList").getString("BT_Buffs");
			DummyData[] d = DataStorage.parseData(dummyDataString);
			for(int i1 = 0; i1 < d.length; ++i1)
			{
				DummyData data = d[i1];
				String name = data.fieldName;
				double value = Double.parseDouble(data.fieldValue);
				if(name.contains("speed"))
				{
					float speed = event.originalSpeed;
					if(value < 0)
					{
						value = -value;
						float mainSpeed = (float) (speed*value);
						event.newSpeed = speed-mainSpeed;
					}else
					{
						float mainSpeed = (float) (speed*value);
						event.newSpeed = speed+mainSpeed;
					}
				}
			}
		}
	}
	
}
