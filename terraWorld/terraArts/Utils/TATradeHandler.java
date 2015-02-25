package terraWorld.terraArts.Utils;

import java.util.Random;

import terraWorld.terraArts.Common.Registry.BlockRegistry;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageTradeHandler;

public class TATradeHandler implements IVillageTradeHandler{

	@Override
	public void manipulateTradesForVillager(EntityVillager villager,
			MerchantRecipeList recipeList, Random random) {
		int profession = villager.getProfession();
			if(random.nextFloat() <= 1F)
			{
				recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald,10+random.nextInt(30),0), new ItemStack(BlockRegistry.tt,1,0)));
			}
		
	}

}
