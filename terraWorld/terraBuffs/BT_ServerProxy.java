package terraWorld.terraBuffs;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class BT_ServerProxy implements IGuiHandler
{

	public void preload()
	{
		
	}
	
	public int getAnvilRenderID()
	{
		return 0;
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity t = world.getTileEntity(x, y, z);
		if(t instanceof BT_TileAnvil)
		{
			return new BT_ContainerAnvil(player.inventory,t);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		return null;
	}
	
}
