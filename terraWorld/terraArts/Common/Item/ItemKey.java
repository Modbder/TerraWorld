package terraWorld.terraArts.Common.Item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemKey extends Item{
	
	public String[] unlocalizedNames = new String[]{"ironKey","goldKey","diamondKey","gemKey","darknessKey"};
	public IIcon[] icons = new IIcon[5];
	
	public ItemKey()
	{
		this.setHasSubtypes(true);
	}
	
    public String getUnlocalizedName(ItemStack stk)
    {
        return super.getUnlocalizedName(stk)+".type."+unlocalizedNames[stk.getItemDamage()];
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int i)
    {
    	return icons[i];
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister p_94581_1_)
    {
    	for(int i = 0; i < icons.length; ++i)
    	{
    		this.icons[i] = p_94581_1_.registerIcon("terraarts:"+unlocalizedNames[i]);
    	}
    }
    
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item i, CreativeTabs c, List l)
    {
    	for(int i1 = 0; i1 < unlocalizedNames.length; ++i1)
    	{
    		l.add(new ItemStack(i,1,i1));
    	}
    }

}
