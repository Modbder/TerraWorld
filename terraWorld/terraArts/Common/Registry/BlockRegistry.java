package terraWorld.terraArts.Common.Registry;

import terraWorld.terraArts.Common.Block.BlockTAChest;
import terraWorld.terraArts.Common.Block.BlockTinkersBench;
import terraWorld.terraArts.Common.Block.ItemBlockTAChest;
import terraWorld.terraArts.Mod.TerraArts;
import DummyCore.Blocks.BlocksRegistry;
import net.minecraft.block.Block;

public class BlockRegistry {
	public static void register()
	{
		tt = new BlockTinkersBench().setHardness(1.0F).setStepSound(Block.soundTypeWood).setBlockName("TA.Block.TT");
		chests[0] = new BlockTAChest(0).setHardness(1.0F).setStepSound(Block.soundTypeMetal).setBlockName("TA.Block.IronChest");
		chests[1] = new BlockTAChest(1).setHardness(1.0F).setStepSound(Block.soundTypeMetal).setBlockName("TA.Block.GoldChest");
		chests[2] = new BlockTAChest(2).setHardness(1.0F).setStepSound(Block.soundTypeMetal).setBlockName("TA.Block.DiamondChest");
		chests[3] = new BlockTAChest(3).setHardness(1.0F).setStepSound(Block.soundTypeMetal).setBlockName("TA.Block.GemChest");
		chests[4] = new BlockTAChest(4).setHardness(1.0F).setStepSound(Block.soundTypeMetal).setBlockName("TA.Block.DarknessChest");
		BlocksRegistry.registerBlock(tt, "tTable", TerraArts.class, null);
		BlocksRegistry.registerBlock(chests[0], "ironChest", TerraArts.class, ItemBlockTAChest.class);
		BlocksRegistry.registerBlock(chests[1], "goldChest", TerraArts.class, ItemBlockTAChest.class);
		BlocksRegistry.registerBlock(chests[2], "diamondChest", TerraArts.class, ItemBlockTAChest.class);
		BlocksRegistry.registerBlock(chests[3], "gemChest", TerraArts.class, ItemBlockTAChest.class);
		BlocksRegistry.registerBlock(chests[4], "darknessChest", TerraArts.class, ItemBlockTAChest.class);
	}
	
	public static Block tt;
	public static Block[] chests = new Block[5];
}
