package terraWorld.terraBuffs;

import java.io.File;
import java.io.IOException;

import DummyCore.Utils.DummyData;
import DummyCore.Utils.EnumRarityColor;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = "buffedtools",name = "Buffed Tools")
public class BT_Mod {
	
	File configDir;
	BT_Config config;
	@EventHandler
	public void preinit(FMLPreInitializationEvent event)
	{
		configDir = event.getSuggestedConfigurationFile();
		config = new BT_Config(configDir);
		File file = new File(configDir.getAbsolutePath());
		if(!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	@EventHandler
	public static void onServerStarted(FMLServerStartedEvent event)
	{
		BT_EffectsLib.rand = MinecraftServer.getServer().worldServers[0].rand;
	}
	

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		//GameRegistry.registerCraftingHandler(new BT_Handler());
		FMLCommonHandler.instance().bus().register(new BT_Handler());
		MinecraftForge.EVENT_BUS.register(new BT_Handler());
		//TickRegistry.registerTickHandler(new BT_TickHandler(), Side.SERVER);
		registerEffects();
		config.loadCFG();
		
	}
	
	public void registerEffects()
	{
		BT_Effect eaa = new BT_Effect("BT.Effect.Damaged", "Damaged", EnumRarityColor.BROKEN, new DummyData("damage", -0.2D)).registerEffect();
		BT_Effect eab = new BT_Effect("BT.Effect.Dull", "Dull", EnumRarityColor.BROKEN, new DummyData("damage", -0.20D)).registerEffect();
		BT_Effect eac = new BT_Effect("BT.Effect.Sluggish", "Sluggish", EnumRarityColor.BROKEN, new DummyData("speed", -0.23D)).registerEffect();
		BT_Effect ead = new BT_Effect("BT.Effect.Slow", "Slow", EnumRarityColor.BROKEN, new DummyData("speed", -0.15D)).registerEffect();
		BT_Effect eae = new BT_Effect("BT.Effect.Lazy", "Lazy", EnumRarityColor.BROKEN, new DummyData("speed", -0.08D)).registerEffect();
		BT_Effect eaf = new BT_Effect("BT.Effect.Cracky", "Cracky", EnumRarityColor.BROKEN, new DummyData("durability", -0.2D)).registerEffect();
		BT_Effect eag = new BT_Effect("BT.Effect.Broken", "Broken", EnumRarityColor.BROKEN, new DummyData("damage", -0.4D),new DummyData("speed", -0.2D)).registerEffect();
		BT_Effect eah = new BT_Effect("BT.Effect.Annoying", "Annoying", EnumRarityColor.BROKEN, new DummyData("damage", -0.2D),new DummyData("speed", -0.15D)).registerEffect();
		BT_Effect eai = new BT_Effect("BT.Effect.Shoddy", "Shoddy", EnumRarityColor.BROKEN, new DummyData("damage", -0.2D),new DummyData("speed", -0.15D)).registerEffect();
		BT_Effect eaj = new BT_Effect("BT.Effect.Terrible", "Terrible", EnumRarityColor.BROKEN, new DummyData("damage", -0.2D),new DummyData("speed", -0.15D),new DummyData("durability", -0.08D)).registerEffect();
		BT_Effect eak = new BT_Effect("BT.Effect.Unhappy", "Unhappy", EnumRarityColor.BROKEN, new DummyData("damage", -0.15D),new DummyData("speed", -0.1D),new DummyData("durability", -0.1D)).registerEffect();
		BT_Effect eal = new BT_Effect("BT.Effect.Heavy", "Heavy", EnumRarityColor.COMMON, new DummyData("damage", 0.15D),new DummyData("speed", -0.15D)).registerEffect();
		BT_Effect eam = new BT_Effect("BT.Effect.Light", "Light", EnumRarityColor.COMMON, new DummyData("damage", -0.15D),new DummyData("speed", 0.15D)).registerEffect();
		BT_Effect ean = new BT_Effect("BT.Effect.Ruthless", "Ruthless", EnumRarityColor.COMMON, new DummyData("damage", 0.2D),new DummyData("speed", -0.15D)).registerEffect();
		BT_Effect eao = new BT_Effect("BT.Effect.Shameful", "Shameful", EnumRarityColor.COMMON, new DummyData("damage", -0.2D),new DummyData("speed", -0.2D),new DummyData("durability", 0.15D)).registerEffect();
		BT_Effect eap = new BT_Effect("BT.Effect.Bulky", "Bulky", EnumRarityColor.GOOD, new DummyData("damage", 0.05D),new DummyData("speed", -0.15D),new DummyData("durability", 0.1D)).registerEffect();
		BT_Effect eaq = new BT_Effect("BT.Effect.Nasty", "Nasty", EnumRarityColor.GOOD, new DummyData("damage", 0.05D),new DummyData("speed", 0.01D),new DummyData("durability", 0.1D)).registerEffect();
		BT_Effect ear = new BT_Effect("BT.Effect.Sharp", "Sharp", EnumRarityColor.UNCOMMON, new DummyData("damage", 0.2D)).registerEffect();
		BT_Effect eas = new BT_Effect("BT.Effect.Pointy", "Pointy", EnumRarityColor.UNCOMMON, new DummyData("damage", 0.1D)).registerEffect();
		BT_Effect eat = new BT_Effect("BT.Effect.Hurtful", "Hurtful", EnumRarityColor.UNCOMMON, new DummyData("damage", 0.1D)).registerEffect();
		BT_Effect eau = new BT_Effect("BT.Effect.Strong", "Strong", EnumRarityColor.UNCOMMON, new DummyData("durability", 0.15D)).registerEffect();
		BT_Effect eav = new BT_Effect("BT.Effect.Forceful", "Forceful", EnumRarityColor.UNCOMMON, new DummyData("durability", 0.15D)).registerEffect();
		BT_Effect eaw = new BT_Effect("BT.Effect.Quick", "Quick", EnumRarityColor.UNCOMMON, new DummyData("speed", 0.1D)).registerEffect();
		BT_Effect eax = new BT_Effect("BT.Effect.Nimble", "Nimble", EnumRarityColor.UNCOMMON, new DummyData("speed", 0.05D)).registerEffect();
		BT_Effect eay = new BT_Effect("BT.Effect.Zealous", "Zealous", EnumRarityColor.UNCOMMON, new DummyData("crit", 0.25D)).registerEffect();
		BT_Effect eaz = new BT_Effect("BT.Effect.Keen", "Keen", EnumRarityColor.UNCOMMON, new DummyData("crit", 0.30D)).registerEffect();
		BT_Effect eba = new BT_Effect("BT.Effect.Massive", "Massive", EnumRarityColor.UNCOMMON, new DummyData("speed", 0.18D)).registerEffect();
		BT_Effect ebb = new BT_Effect("BT.Effect.Large", "Large", EnumRarityColor.UNCOMMON, new DummyData("speed", 0.12D)).registerEffect();
		BT_Effect ebc = new BT_Effect("BT.Effect.Demonic", "Demonic", EnumRarityColor.RARE, new DummyData("damage", 0.15D), new DummyData("crit", 0.15D)).registerEffect();
		BT_Effect ebd = new BT_Effect("BT.Effect.Agile", "Agile", EnumRarityColor.RARE, new DummyData("speed", 0.1D), new DummyData("crit", 0.1D)).registerEffect();
		BT_Effect ebe = new BT_Effect("BT.Effect.Deadly", "Deadly", EnumRarityColor.RARE, new DummyData("damage", 0.1D), new DummyData("speed", 0.1D)).registerEffect();
		BT_Effect ebf = new BT_Effect("BT.Effect.Unpleasant", "Unpleasant", EnumRarityColor.RARE, new DummyData("damage", 0.05D), new DummyData("durability", 0.15D)).registerEffect();
		BT_Effect ebg = new BT_Effect("BT.Effect.Rapid", "Rapid", EnumRarityColor.RARE, new DummyData("speed", 0.15D), new DummyData("durability", 0.10D)).registerEffect();
		BT_Effect ebh = new BT_Effect("BT.Effect.Godly", "Godly", EnumRarityColor.UNIQUE, new DummyData("damage", 0.15D), new DummyData("crit", 0.25D), new DummyData("durability", 0.15D)).registerEffect();
		BT_Effect ebj = new BT_Effect("BT.Effect.Superior", "Superior", EnumRarityColor.UNIQUE, new DummyData("damage", 0.1D), new DummyData("crit", 0.3D), new DummyData("durability", 0.1D)).registerEffect();
		BT_Effect ebk = new BT_Effect("BT.Effect.Dangerous", "Dangerous", EnumRarityColor.UNIQUE, new DummyData("damage", 0.05D), new DummyData("crit", 0.22D), new DummyData("speed", 0.05D)).registerEffect();
		BT_Effect ebl = new BT_Effect("BT.Effect.Savage", "Savage", EnumRarityColor.UNIQUE, new DummyData("damage", 0.1D), new DummyData("speed", 0.1D), new DummyData("durability", 0.10D)).registerEffect();
		BT_Effect ebm = new BT_Effect("BT.Effect.Murderous", "Murderous", EnumRarityColor.UNIQUE, new DummyData("damage", 0.07D), new DummyData("crit", 0.35D), new DummyData("speed", 0.06D)).registerEffect();
		BT_Effect ebn = new BT_Effect("BT.Effect.Legendary", "Legendary", EnumRarityColor.LEGENDARY, new DummyData("damage", 0.15D), new DummyData("speed", 0.1D), new DummyData("crit", 0.45D), new DummyData("durability", 0.15D)).registerEffect();
	}
}
