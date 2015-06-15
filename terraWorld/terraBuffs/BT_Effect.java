package terraWorld.terraBuffs;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import DummyCore.Utils.DummyData;
import DummyCore.Utils.EnumRarityColor;

public class BT_Effect {
	
	private EnumRarityColor rarity;
	private List<DummyData> buffs = new ArrayList();
	private String name;
	private String codeName;
	public int rarity_int;
	public int type;
	
	public BT_Effect(String s, String s1,EnumRarityColor color, DummyData ... effect)
	{
		codeName = s;
		name = s1;
		rarity = color;
		type = 0;
		for(int i = 0; i < effect.length; ++i)
		{
			buffs.add(effect[i]);
		}
		
	}
	
	public BT_Effect registerEffect()
	{
		BT_EffectsLib.effects.put(codeName, this);
		BT_EffectsLib.effects_list.add(this);
		if(this.type == 0)
			BT_EffectsLib.tools_effects_list.add(this);
		else
			BT_EffectsLib.armor_effects_list.add(this);
		return this;
	}
	
	public BT_Effect setArmorType()
	{
		type = 1;
		return this;
	}
	
	public List<DummyData> getEffects()
	{
		return buffs;
	}
	
	public String getName()
	{
		return rarity.getRarityColor()+name;
	}
	
	public String getColor()
	{
		return rarity.getRarityColor();
	}

}
