package terraWorld.terraArts.Common.Registry;

import terraWorld.terraArts.Common.Tile.TileEntityTAChest;
import cpw.mods.fml.common.registry.GameRegistry;

public class TileRegistry {
	public static void register()
	{
		GameRegistry.registerTileEntity(TileEntityTAChest.class, "TA.Tile.Chest");
	}
}
