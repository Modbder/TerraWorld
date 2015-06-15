package terraWorld.terraArts.API;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TAApi {
	public static List<String> itemsNamesByID = new ArrayList();
	public static Hashtable<String,Item> itemsByNames = new Hashtable();
	public static Hashtable<String,Integer> artRarityTable = new Hashtable<String,Integer>();
	public static List<Item>[] rarityLists = new ArrayList[5];
	public static List<CombineryRecipe> combineryRecipes = new ArrayList<CombineryRecipe>();
	
	public static CombineryRecipe getRecipeByCP(ItemStack stk_0, ItemStack stk_1)
	{
		if(stk_0 != null && stk_1 != null)
		{
			for(int i = 0; i < combineryRecipes.size(); ++i)
			{
				CombineryRecipe r = combineryRecipes.get(i);
				if(r.matches(stk_0, stk_1) || r.matches(stk_1, stk_0))
					return r;
			}
			
		}
		return null;
	}

}
