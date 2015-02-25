package terraWorld.terraArts.Network.Proxy;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import org.lwjgl.input.Keyboard;

import terraWorld.terraArts.Client.GUI.GuiArtifacts;
import terraWorld.terraArts.Common.Entity.EntityFallingStar;
import terraWorld.terraArts.Common.Tile.TileEntityTAChest;
import terraWorld.terraArts.Network.TAPacketIMSG;
import terraWorld.terraArts.Utils.TAClientTickHandler;
import terraWorld.terraArts.Utils.TAUtils;
import DummyCore.Utils.DataStorage;
import DummyCore.Utils.DummyData;
import DummyCore.Utils.MiscUtils;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy{
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		
		Side s = FMLCommonHandler.instance().getEffectiveSide();
		if(ID == 374435)
		{
			return new GuiArtifacts(player.inventory);
		}
		TileEntity tile = world.getTileEntity(x, y, z);
		//TODO GUI!
		if(tile instanceof TileEntityTAChest)
		{
			return new GuiChest(player.inventory, (TileEntityTAChest) tile);
		}
		return super.getClientGuiElement(ID, player, world, x, y, z);
	}
	
	@Override
	public void registerRenderInformation()
	{
		//TODO Render Info...
		FMLCommonHandler.instance().bus().register(new TAClientTickHandler());
		RenderingRegistry.registerEntityRenderingHandler(EntityFallingStar.class, new RenderSnowball(Items.nether_star));
		kbOpenGUI =  new KeyBinding("OpenArtifactsGUI", Keyboard.KEY_L, "key.categories.misc");
		ClientRegistry.registerKeyBinding(kbOpenGUI);
    	boolean[] repeat = {false};
		VillagerRegistry.instance().registerVillagerSkin(8,new ResourceLocation("terraarts","textures/entity/YE3TE2y.png"));
	}
	
	@Override
	public void recieveClientPacket(TAPacketIMSG packet, EntityPlayer p)
	{
		try{
		if(packet.packetType.equals("TA.Particle"))
		{
			String data = packet.dummyData;
			DummyData[] dat = DataStorage.parseData(data);
			if(dat.length != 0)
			{
				String pname = dat[0].fieldValue;
				double pPosX = Double.parseDouble(dat[1].fieldValue);
				double pPosY = Double.parseDouble(dat[2].fieldValue);
				double pPosZ = Double.parseDouble(dat[3].fieldValue);
				double pMX = Double.parseDouble(dat[4].fieldValue);
				double pMY = Double.parseDouble(dat[5].fieldValue);
				double pMZ = Double.parseDouble(dat[6].fieldValue);
				p.worldObj.spawnParticle(pname, pPosX, pPosY, pPosZ, pMX, pMY, pMZ);
			}
		}
		if(packet.packetType.equals("TA.Sound"))
		{
			String data = packet.dummyData;
			DummyData[] dat = DataStorage.parseData(data);
			if(dat.length != 0)
			{
				String pname = dat[0].fieldValue;
				double pPosX = Double.parseDouble(dat[1].fieldValue);
				double pPosY = Double.parseDouble(dat[2].fieldValue);
				double pPosZ = Double.parseDouble(dat[3].fieldValue);
				double v = Double.parseDouble(dat[4].fieldValue);
				double pitch = Double.parseDouble(dat[5].fieldValue);
				p.worldObj.playSound(pPosX, pPosY, pPosZ, pname, (float)v, (float)pitch, true);
			}
		}
		if(packet.packetType.equals("TA.Move"))
		{
			String data = packet.dummyData;
			DummyData[] dat = DataStorage.parseData(data);
			if(dat.length != 0)
			{
				double mX = Double.parseDouble(dat[0].fieldValue);
				double mY = Double.parseDouble(dat[1].fieldValue);
				double mZ = Double.parseDouble(dat[2].fieldValue);
				p.motionX += mX;
				p.motionY += mY;
				p.motionZ += mZ;
			}
		}
		if(packet.packetType.equals("TA.Sync"))
		{
			NBTTagCompound tag = packet.syncedNBT;
			if(TAUtils.clientInventory != null)
				TAUtils.clientInventory.readFromNBT(tag);
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public EntityPlayer getSidedPlayer()
	{
		return Minecraft.getMinecraft().thePlayer;
	}
	
	public static KeyBinding kbOpenGUI;
}
