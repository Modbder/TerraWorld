package terraWorld.terraArts.Utils;

import java.io.File;

import cpw.mods.fml.common.SidedProxy;
import DummyCore.Utils.IDummyConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.config.Configuration;

public class TAConfig implements IDummyConfig{

	@Override
	public void load(Configuration config) {
		cfg = config;
		cfg.load();
		mobID[0] = cfg.get("Entities", "fallingStarID", 414).getInt();
		attemptsToGenerate = cfg.get("World", "attemptsToGenerateChestPerChunk", 3).getInt();
		cfg.get("World", "attemptsToGenerateChestPerChunk", 3).comment = "This variable allows you to change the amount of times the chests are tried to be generated in a chunk. Notice, that it is not a 100% chance for a chest to spawn, it is at most 30-20%, and usually it is somewhere around 5-13%";
		slotsToExtract = cfg.get("Global", "slotsToAllowExtraction", new int[]{0,1,2,3,4,5,6,7,8}).getIntList();
		cfg.get("Global", "slotsToAllowExtraction", new int[]{8}).comment = "This allows you to control the extraction of items(using hoppers or pipes) from the chests that spawn naturally in the world. The numbers are slots. The artifact always generates in a slot number 0, and the key always generates in slot 1. There are 9 slots, so to allow extraction from all slots it has to be 0,1,2,3,4,5,6,7,8";
		chestRarities = cfg.get("World", "chestRarities", new double[]{0.5D,0.2D,0.1D,0.03D}).getDoubleList();
		cfg.get("World", "chestRarities", new double[]{0.5D,0.2D,0.1D,0.03D}).comment = "This numbers allow you to control the chance of a spawned chest to be the specific rarity. By default the numbers are - 50% for an iron chest, 30% for a golden chest,10% for a diamond chest, 7% for a gemstone chest and 3% for the darkness chest";
		showGenerationInformation = cfg.get("Global", "showGenerationInformation", true).getBoolean(true);
		cfg.get("Global", "showGenerationInformation", true).comment = "If set to true whenever the chest generates successfully the message will appear in the console, giving the coordinates and the rarity of a chest that has just generated";
		cfg.save();
	}
	
	public static Configuration cfg;
	public static int[] mobID = new int[8];
	public static int attemptsToGenerate = 3;
	public static int[] slotsToExtract = new int[]{8};
	public static double[] chestRarities = new double[]{0.5D,0.2D,0.1D,0.03D};
	public static boolean showGenerationInformation = true;
}
