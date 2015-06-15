package terraWorld.terraArts.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import terraWorld.terraArts.API.TAApi;
import terraWorld.terraArts.Common.Registry.BlockRegistry;
import terraWorld.terraArts.Common.Registry.ItemRegistry;
import terraWorld.terraArts.Common.Tile.TileEntityTAChest;
import DummyCore.Core.CoreInitialiser;
import DummyCore.Utils.MiscUtils;
import DummyCore.Utils.Notifier;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.ChestGenHooks;
import cpw.mods.fml.common.IWorldGenerator;

public class TAWorldGen implements IWorldGenerator{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		for(int j = 0; j < TAConfig.attemptsToGenerate; ++j)
		{
			int rarity = 0;
			float floatRarity = random.nextFloat();
			if(floatRarity > TAConfig.chestRarities[0])
				rarity = 0;
			if(floatRarity <= TAConfig.chestRarities[0]&& floatRarity > TAConfig.chestRarities[1])
				rarity = 1;
			if(floatRarity <= TAConfig.chestRarities[1]&& floatRarity > TAConfig.chestRarities[2])
				rarity = 2;
			if(floatRarity <= TAConfig.chestRarities[2]&& floatRarity > TAConfig.chestRarities[3])
				rarity = 3;
			if(floatRarity <= TAConfig.chestRarities[3])
				rarity = 4;
			int x = chunkX*16+random.nextInt(16);
			int z = chunkZ*16+random.nextInt(16);
			int y = random.nextInt(128)+6;
			if(world.getBlock(x, y, z) != null && world.getBlock(x, y, z).isOpaqueCube() && !world.getBlock(x, y, z).isAir(world, x, y, z) && world.getBlock(x, y, z) != Blocks.bedrock)
			{
				if(world.getBlock(x, y+1, z) == null || world.getBlock(x, y+1, z).isAir(world, x, y+1, z))
				{
					if(TAConfig.showGenerationInformation)
						Notifier.notifyDebugCustomMod("TerraArts", x+"|"+y+"|"+z+"|"+rarity+"|"+floatRarity);
					if(TAConfig.chestsGeneratePlatform)
						for(int dx = -1; dx <= 1; ++dx)
						{
							for(int dz = -1; dz <= 1; ++dz)
							{
								world.setBlock(x+dx, y, z+dz, Blocks.planks,0,3);
							}
						}
					world.setBlock(x, y+1, z, BlockRegistry.chests[rarity],random.nextInt(4),2);
					TileEntityTAChest chest = (TileEntityTAChest) world.getTileEntity(x, y+1, z);
					if(chest == null)
					{
						chest = new TileEntityTAChest();
						chest.xCoord = x;
						chest.yCoord = y+1;
						chest.zCoord = z;
						world.setTileEntity(x, y+1, z, chest);
					}
					Item artID = TAApi.rarityLists[rarity].get(random.nextInt(TAApi.rarityLists[rarity].size()));
					ItemStack artStack = new ItemStack(artID,1,0);
                    ChestGenHooks info = ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST);
                    WeightedRandomChestContent.generateChestContents(random, info.getItems(random), chest, info.getCount(random));
					chest.setInventorySlotContents(0, artStack);
					if(rarity < 4 && random.nextFloat() < TAConfig.keyChance)
					{
						ItemStack keyStack = new ItemStack(ItemRegistry.key,1,rarity+1);
						chest.setInventorySlotContents(1, keyStack);
					}
					chest.initChest();
					chest.onPlaced();
				}
			}
		}
	}

}
