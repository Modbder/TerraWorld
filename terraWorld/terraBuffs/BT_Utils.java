package terraWorld.terraBuffs;


import java.util.List;
import java.util.UUID;

import DummyCore.Utils.DataStorage;
import DummyCore.Utils.DummyData;
import DummyCore.Utils.MiscUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.ISpecialArmor;

public class BT_Utils {
	
	public static void addRandomEffects(ItemStack stk)
	{
		int type = getISType(stk);
		if(type != -1)
		{
			if(type == 0)
			{
				MiscUtils.createNBTTag(stk);
				NBTTagCompound itemTag = stk.getTagCompound();
				NBTTagCompound buffsTag = new NBTTagCompound();
				String originalName = null;
				if(itemTag.hasKey("BT_TagList"))
				{
					originalName = itemTag.getString("BT_OriginalName");
					itemTag.removeTag("BT_TagList");
				}
				BT_Effect effect = BT_EffectsLib.getRandomEffect(type);
				List<DummyData> l = effect.getEffects();
				for(int i = 0; i < l.size(); ++i)
				{
					DummyData d = l.get(i);
					DataStorage.addDataToString(d);
				}
				String data = DataStorage.getDataString();
				buffsTag.setString("BT_Buffs", data);
				buffsTag.setString("BT_UUID", UUID.randomUUID().toString());
				if(originalName == null || originalName.isEmpty())
					itemTag.setString("BT_OriginalName", stk.getDisplayName());
				NBTTagCompound display = new NBTTagCompound();
				if(itemTag.hasKey("display"))
				{
					display = itemTag.getCompoundTag("display");
				}
				if(originalName == null || originalName.isEmpty())
					display.setString("Name", effect.getColor()+effect.getName()+" "+stk.getDisplayName());
				else
					display.setString("Name", effect.getColor()+effect.getName()+" "+originalName);
				itemTag.setTag("display", display);
				itemTag.setTag("BT_TagList", buffsTag);
				stk.setTagCompound(itemTag);
			}
		}
	}
	
	public static int getISType(ItemStack stk)
	{
		if(isItemBuffable(stk))
		{
			if(stk.getItem() instanceof ItemArmor || stk.getItem() instanceof ISpecialArmor)
				return 1;
			return 0;
		}
		return -1;
	}
	
	public static boolean isItemBuffable(ItemStack stk)
	{
		boolean enable = false;
		if(!enable)
			enable = isTConstructTool(stk);
		return (stk != null && stk.getItem() != null && !(stk.getItem() instanceof ItemBlock) && stk.getItem().isItemTool(stk)) || enable;
	}
	
	public static boolean itemHasEffect(ItemStack stack)
	{
		if(stack.hasTagCompound() && stack.getTagCompound().hasKey("BT_TagList"))
		{
			NBTTagCompound tag = (NBTTagCompound)stack.getTagCompound().getTag("BT_TagList");
			if(tag.hasKey("BT_Buffs"))
			{
				return true;
			}
		}
		return false;
	}
	
	public static boolean isTConstructTool(ItemStack stk)
	{
		if(stk == null || stk.getItem() == null)return false;
		try
		{
			Class clazz = Class.forName("tconstruct.library.tools.ToolCore");
			Class toolClazz = stk.getItem().getClass();
			return clazz.isAssignableFrom(toolClazz);
		}catch(Exception e)
		{
			return false;
		}
	}


}
