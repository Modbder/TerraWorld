package terraWorld.terraArts.Network.Proxy;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import terraWorld.terraArts.API.IArtifact;
import terraWorld.terraArts.Client.GUI.GuiCombinery;
import terraWorld.terraArts.Common.Inventory.ContainerArtifacts;
import terraWorld.terraArts.Common.Inventory.ContainerChest;
import terraWorld.terraArts.Common.Inventory.ContainerCombinery;
import terraWorld.terraArts.Common.Inventory.InventoryArtifacts;
import terraWorld.terraArts.Common.Tile.TileEntityTAChest;
import terraWorld.terraArts.Common.Tile.TileEntityTACombiner;
import terraWorld.terraArts.Mod.TerraArts;
import terraWorld.terraArts.Network.TAPacketIMSG;
import terraWorld.terraArts.Utils.TAUtils;
import DummyCore.Utils.DataStorage;
import DummyCore.Utils.DummyData;
import DummyCore.Utils.MiscUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		if(ID == 374435)
		{
			return new ContainerArtifacts(player.inventory, player.worldObj.isRemote, player);
		}
		TileEntity tile = world.getTileEntity(x, y, z);
		//TODO GUI opening!
		if(tile instanceof TileEntityTAChest)
		{
			return new ContainerChest(player.inventory, (TileEntityTAChest) tile);
		}
		if(tile instanceof TileEntityTACombiner)
		{
			return new ContainerCombinery(player.inventory,tile);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		return null;
	}
	
	public void registerRenderInformation()
	{
		
	}
	
	public void recieveServerPacket(TAPacketIMSG packet, EntityPlayer p)
	{
			try
			{
			if(packet.packetType.equals("TA.Button"))
			{
			
				String data = packet.dummyData;
				DummyData[] dat = DataStorage.parseData(data);
				if(dat.length != 0)
				{
					String username = dat[0].fieldValue;
					EntityPlayer pl = MinecraftServer.getServer().getConfigurationManager().func_152612_a(username);
					pl.openGui(TerraArts.instance, 374435, pl.worldObj, 0, 0, 0);
				}
			}
			if(packet.packetType.equals("TA.RenderRequest"))
			{
			
				String data = packet.dummyData;
				DummyData[] dat = DataStorage.parseData(data);
				if(dat.length != 0)
				{
					double x = Double.parseDouble(dat[0].fieldValue);
					double y = Double.parseDouble(dat[1].fieldValue);
					double z = Double.parseDouble(dat[2].fieldValue);
					int dim = Integer.parseInt(dat[3].fieldValue);
					String username = dat[4].fieldValue;
					int render = Integer.parseInt(dat[5].fieldValue);
					EntityPlayer pl = MinecraftServer.getServer().getConfigurationManager().func_152612_a(username);
					World w = MinecraftServer.getServer().worldServerForDimension(dim);
					if(w != null)
					{
						TileEntity t = w.getTileEntity((int)x, (int)y, (int)z);
						if(t != null && t instanceof TileEntityTAChest)
						{
							TileEntityTAChest chest = (TileEntityTAChest) t;
							if(render != chest.textureIndex)
								chest.onPlaced();
						}
					}
				}
			}
			if(packet.packetType.equals("TA.PJump"))
			{
				String data = packet.dummyData;
				DummyData[] dat = DataStorage.parseData(data);
				if(dat.length != 0)
				{
					String username = dat[0].fieldValue;
					EntityPlayer pl = MinecraftServer.getServer().getConfigurationManager().func_152612_a(username);
					TAUtils.onPlayerJump(pl);
				}
			}
			if(packet.packetType.equals("TA.JHold"))
			{
				String data = packet.dummyData;
				DummyData[] dat = DataStorage.parseData(data);
				if(dat.length != 0)
				{
					String username = dat[0].fieldValue;
					EntityPlayer pl = MinecraftServer.getServer().getConfigurationManager().func_152612_a(username);
					TAUtils.onPlayerHoldJump(pl);
				}
			}
			if(packet.packetType.equals("TA.Jump"))
			{
				String data = packet.dummyData;
				DummyData[] dat = DataStorage.parseData(data);
				if(dat.length != 0)
				{
					String username = dat[0].fieldValue;
					EntityPlayer pl = MinecraftServer.getServer().getConfigurationManager().func_152612_a(username);
					if(TAUtils.playerInvTable.containsKey(pl.getCommandSenderName()))
					{
						InventoryArtifacts ia = (InventoryArtifacts) TAUtils.playerInvTable.get(pl.getCommandSenderName());
						for(int i = 0; i < 5; ++i)
						{
							ItemStack stack = ia.mainInventory[i];
							if(stack != null && stack.getItem() instanceof IArtifact)
							{
								IArtifact art = (IArtifact) stack.getItem();
								art.setJump(stack, pl);
							}
						}
					}
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			return;
		}
	}
	
	public void recieveClientPacket(TAPacketIMSG packet, EntityPlayer p)
	{

	}
	
	public EntityPlayer getSidedPlayer()
	{
		return null;
	}

}
