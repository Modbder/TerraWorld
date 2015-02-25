package terraWorld.terraArts.Mod;

import java.io.IOException;

import terraWorld.terraArts.Common.Registry.CommonRegistry;
import terraWorld.terraArts.Network.TAPacketHandler;
import terraWorld.terraArts.Network.TAPacketIMSG;
import terraWorld.terraArts.Network.Proxy.CommonProxy;
import terraWorld.terraArts.Utils.TAConfig;
import terraWorld.terraArts.Utils.TAEventHandler;
import terraWorld.terraArts.Utils.TAPlayerTickHandler;
import terraWorld.terraArts.Utils.TAPlayerTracker;
import net.minecraftforge.common.MinecraftForge;
import DummyCore.Core.Core;
import DummyCore.Utils.DummyPacketHandler;
import DummyCore.Utils.DummyPacketIMSG;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = "terraarts",name = "Terra Arts",version = "1.0.1710.1")
public class TerraArts {
	public static TAConfig cfg;
	@SidedProxy(clientSide = "terraWorld.terraArts.Network.Proxy.ClientProxy",serverSide = "terraWorld.terraArts.Network.Proxy.CommonProxy")
	public static CommonProxy proxy;
	public static TerraArts instance;
	public static SimpleNetworkWrapper network;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		network = NetworkRegistry.INSTANCE.newSimpleChannel("terraarts");
	    network.registerMessage(TAPacketHandler.class, TAPacketIMSG.class, 0, Side.SERVER);
	    network.registerMessage(TAPacketHandler.class, TAPacketIMSG.class, 1, Side.CLIENT);
		cfg = new TAConfig();
		try {
			instance = this;
			Core.registerModAbsolute(getClass(), "Terra Arts", event.getModConfigurationDirectory().getAbsolutePath(), cfg);
			MinecraftForge.EVENT_BUS.register(new TAEventHandler());
			MinecraftForge.EVENT_BUS.register(new TAPlayerTracker());
			NetworkRegistry.INSTANCE.registerGuiHandler(this, proxy);
			FMLCommonHandler.instance().bus().register(new TAPlayerTickHandler());
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		CommonRegistry.register();
		proxy.registerRenderInformation();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
}
