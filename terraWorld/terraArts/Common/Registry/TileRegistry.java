package terraWorld.terraArts.Common.Registry;

import terraWorld.terraArts.Common.Tile.TileEntityTAChest;
import terraWorld.terraArts.Common.Tile.TileEntityTACombiner;
import cpw.mods.fml.common.registry.GameRegistry;

public class TileRegistry {
	public static void register()
	{
		GameRegistry.registerTileEntity(TileEntityTAChest.class, "TA.Tile.Chest");
		GameRegistry.registerTileEntity(TileEntityTACombiner.class, "TA.Tile.Combiner");
	}
}
