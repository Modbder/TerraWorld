package terraWorld.terraBuffs;


import java.util.List;
import java.util.UUID;

import DummyCore.Utils.DataStorage;
import DummyCore.Utils.DummyData;
import DummyCore.Utils.MiscUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
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
				if(itemTag.hasKey("BT_TagList"))
				{
					buffsTag = itemTag.getCompoundTag("BT_TagList");
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
				NBTTagCompound display = new NBTTagCompound();
				if(itemTag.hasKey("display"))
				{
					display = itemTag.getCompoundTag("display");
				}
				display.setString("Name", effect.getColor()+effect.getName()+" "+stk.getDisplayName());
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
		return stk != null && stk.getItem().isItemTool(stk);
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
	


}
