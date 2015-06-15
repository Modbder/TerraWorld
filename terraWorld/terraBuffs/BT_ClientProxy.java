package terraWorld.terraBuffs;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraft.util.StringTranslate;
import net.minecraft.world.World;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class BT_ClientProxy extends BT_ServerProxy
{
	@Override
	public void preload()
	{
		RenderingRegistry.registerBlockHandler(new BT_AnvilRenderer());
	}
	
	@Override
	public int getAnvilRenderID()
	{
		return BT_AnvilRenderer.getStaticRenderId();
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity t = world.getTileEntity(x, y, z);
		if(t instanceof BT_TileAnvil)
		{
			return new BT_GuiAnvil(new BT_ContainerAnvil(player.inventory,t),t);
		}
		return null;
	}
}
