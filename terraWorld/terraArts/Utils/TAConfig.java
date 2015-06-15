package terraWorld.terraArts.Utils;

import DummyCore.Utils.IDummyConfig;
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
		showGenerationInformation = cfg.get("Global", "showGenerationInformation", false).getBoolean(false);
		cfg.get("Global", "showGenerationInformation", false).comment = "If set to true whenever the chest generates successfully the message will appear in the console, giving the coordinates and the rarity of a chest that has just generated";
		keyChance = cfg.get("Global", "keyGenerationChance", 0.4F).getDouble();
		cfg.get("Global", "keyGenerationChance", 0.4F).comment = "The value here represents the chance of a key to be generated - default is 0.4, which is 40%. Setting this number higher than 1.0 will result in nothing happening.";
		chestsRequireKeys = cfg.get("Global", "chestsRequireKeys", true).getBoolean(true);
		cfg.get("Global", "chestsRequireKeys", true).comment = "If set to true chests will require a player to have a key in their hands to open the chest, if false - chests can be opened even without a key";
		saveChestInformation = cfg.get("Global", "saveChestInformation", true).getBoolean(true);
		cfg.get("Global", "saveChestInformation", true).comment = "If set to true chests will remember them being unlocked, and, therefore, will not require keys upon future unlocks.";
		chestsConsumeKeys = cfg.get("Global", "chestsConsumeKeys", false).getBoolean(false);
		cfg.get("Global", "chestsConsumeKeys", false).comment = "If set to true chests will actually consume the key with which they are opened with. This option will ONLY have effect, if \" saveChestInformation \" option is set to TRUE";
		chestRendererID = cfg.get("Render", "chestRenderID", 1736001).getInt();
		cfg.get("Render", "chestRenderID", 1736001).comment = "The ID of rendering for the chest. Change this if you are experiencing weird render crashes on startup.";
		chestsGeneratePlatform = cfg.getBoolean("chestsGeneratePlatforms", "World", false, "If there should be a platform below the chest once it generates.");
		cfg.save();
	}
	
	public static Configuration cfg;
	public static int[] mobID = new int[8];
	public static int attemptsToGenerate = 3;
	public static int[] slotsToExtract = new int[]{8};
	public static double[] chestRarities = new double[]{0.5D,0.2D,0.1D,0.03D};
	public static boolean showGenerationInformation = true;
	public static double keyChance = 0.4F;
	public static boolean chestsRequireKeys = true;
	public static boolean saveChestInformation = true;
	public static boolean chestsConsumeKeys = false;
	public static boolean chestsGeneratePlatform = false;
	public static int chestRendererID = 1736001;
}
