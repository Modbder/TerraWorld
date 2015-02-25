package terraWorld.terraArts.Common.Registry;

import terraWorld.terraArts.Utils.TATradeHandler;
import terraWorld.terraArts.Utils.TAWorldGen;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraftforge.oredict.ShapedOreRecipe;
import DummyCore.Core.CoreInitialiser;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;

public class CommonRegistry {
	
	public static void register()
	{
		BlockRegistry.register();
		ItemRegistry.register();
		EntityRegistry.register();
		TileRegistry.register();
		VillagerRegistry.instance().registerVillagerId(8);
		
		VillagerRegistry.instance().registerVillageTradeHandler(8, new TATradeHandler());
		EntityVillager.blacksmithSellingList.put(BlockRegistry.tt, new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
		GameRegistry.registerWorldGenerator(new TAWorldGen(), 0);
		
		//TODO keyRecipe
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.key),new Object[]{
			"III",
			"IDG",
			"   ",
			'I',"ingotIron",
			'G',"ingotGold",
			'D',"gemDiamond"
		}));
	}

}
