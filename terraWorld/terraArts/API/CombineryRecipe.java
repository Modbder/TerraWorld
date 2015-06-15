package terraWorld.terraArts.API;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class CombineryRecipe implements IRecipe{
	
	public CombineryRecipe(ItemStack slot0, ItemStack slot1, ItemStack result, int requiredExperience)
	{
		recipeItems = new ItemStack[2];
		recipeItems[0] = slot0.copy();
		recipeItems[1] = slot1.copy();
		this.result = result.copy();
		TAApi.combineryRecipes.add(this);
		recCost = requiredExperience;
	}

	public ItemStack[] recipeItems;
	public ItemStack result;
	public int recCost;
	
	@Override
	public boolean matches(InventoryCrafting p_77569_1_, World p_77569_2_) {
		
		return p_77569_1_.getSizeInventory() >= 2 && this.recipeItems.length >= 2
				&& p_77569_1_.getStackInSlot(0) != null && this.recipeItems[0] != null
				&& p_77569_1_.getStackInSlot(1) != null && this.recipeItems[1] != null
				;
	}
	
	public boolean matches(ItemStack... is) {
		
		return is.length >= 2 && this.recipeItems.length >= 2
				&& is[0] != null && this.recipeItems[0] != null
				&& is[1] != null && this.recipeItems[1] != null
				&& is[0].isItemEqual(this.recipeItems[0]) 
				&& is[1].isItemEqual(this.recipeItems[1]) 
				;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting p_77572_1_) {
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public int getRecipeSize() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public ItemStack getRecipeOutput() {
		// TODO Auto-generated method stub
		return result;
	}

}
