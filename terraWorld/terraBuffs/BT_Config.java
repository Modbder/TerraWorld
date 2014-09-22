package terraWorld.terraBuffs;

import java.io.File;
import java.util.Set;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.common.config.Property.Type;
import DummyCore.Utils.DataStorage;
import DummyCore.Utils.DummyData;
import DummyCore.Utils.EnumRarityColor;
import DummyCore.Utils.Notifier;

public class BT_Config extends Configuration{
	
	private static int buffsCount = 0;
	
	public BT_Config(File f)
	{
		super(f);
	}
	
	public void loadCFG()
	{
		this.load();
		ConfigCategory help = this.getCategory("Help");
		help.setComment(" Using this .cfg file you can add your own effects, which will be applied to tools. "
		+ "\n Adding your own effect is very simple. Above you can see the example of how it's done. You need to create a custom category, using # chars, then write the code effect name(make sure your name is unique, otherwise your effect will most likely be ignored)."
		+ "\n Then you need to write this name again, this time without # chars, and add {} after it."
		+ "\n Now, there are 3 fields you need to write in this {}."
		+ "\n The first one is 'name'. This says, what name will be actually shown in-game(the tool prefix)."
		+ "\n Second one is 'color'. You should put one hex number there. You can choose from this number set: 8,f,a,2,9,d,e,6,b,3,c,4. These represent the rarity of your effect. You can learn more about rarities in DummyCore code, in EnumRarityColor file."
		+ "\n The last one is 'dataArray'. This represents the effects, that will be applied to your buff. There are 5 effects by now - 'damage','speed','invulTime','durability' and 'crit'."
		+ "\n To write this data you need to follow the simple rules: after : put ||, then put an actual name of one of the 5 possible effects. Then put : again, and after that write your value. It can be below 0, and it is always not an integer. This value is persentage-based, and it scales, as 1 = 100%, and 0.25 = 25%"
		+ "\n If you want to add more than one effect, ust put || after the value you have last written, and start writing another data string. But remember, that || represents the beginning of the new datastring, so something like |||| will most likely lead to crash.");
		ConfigCategory c = this.getCategory("BT:Effect:Durable");
		c.put("name", new Property("name","Durable",Type.STRING));
		c.put("color", new Property("color","a",Type.STRING));
		DummyData durDat = new DummyData("durability", 0.25D);
		DataStorage.addDataToString(durDat);
		String str = DataStorage.getDataString();
		c.put("dataArray", new Property("dataArray",str,Type.STRING));
		this.save();
		Set s = this.getCategoryNames();
		for(int i = 0; i < s.size(); ++i)
		{
			ConfigCategory cat = this.getCategory((String) s.toArray()[i]);
			String codeName = cat.getQualifiedName();
			if(cat.containsKey("name") && cat.containsKey("color") && cat.containsKey("dataArray"))
			{
				String name = cat.get("name").getString();
				String hex = cat.get("color").getString();
				String data = cat.get("dataArray").getString();
				DummyData[] dat = DataStorage.parseData(data);
				BT_Effect aaa = new BT_Effect(codeName, name, EnumRarityColor.getColorByHex(hex), dat).registerEffect();
				Notifier.notifyCustomMod("BuffedTools", "Adding a new effect with name "+name+", rarity "+EnumRarityColor.getColorByHex(hex).getName() + " and data "+data);
				++buffsCount;
			}
		}
		Notifier.notifyCustomMod("BuffedTools","Loaded "+buffsCount+" custom effect(s).");
	}

}
