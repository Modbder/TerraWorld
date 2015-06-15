package terraWorld.terraArts.Network;

import io.netty.channel.ChannelHandler;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import terraWorld.terraArts.Mod.TerraArts;
import DummyCore.Utils.DataStorage;
import DummyCore.Utils.DummyData;
import DummyCore.Utils.DummyPacketIMSG;
import DummyCore.Utils.MiscUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
@ChannelHandler.Sharable
public class TAPacketHandler implements IMessageHandler<TAPacketIMSG, IMessage>{


	@Override
	public IMessage onMessage(TAPacketIMSG message, MessageContext ctx) {
		Side s = ctx.side;
		if(s == Side.SERVER)
			TerraArts.proxy.recieveServerPacket(message, null);
		if(s == Side.CLIENT)
			TerraArts.proxy.recieveClientPacket(message, TerraArts.proxy.getSidedPlayer());
		return null;
	}
	
	public static void spawnParticleOnServer(String pName, double x, double y, double z, double par1, double par2, double par3, float range, int dimID)
	{
		DummyData[] datArray = new DummyData[7];
		datArray[0] = new DummyData("name",pName);
		datArray[1] = new DummyData("x",x);
		datArray[2] = new DummyData("y",y);
		datArray[3] = new DummyData("z",z);
		datArray[4] = new DummyData("px",par1);
		datArray[5] = new DummyData("py",par2);
		datArray[6] = new DummyData("pz",par3);
		for(int i = 0; i < 7; ++i)
		{
			DataStorage.addDataToString(datArray[i]);
		}
		TerraArts.network.sendToAllAround(getPacketFor("TA.Particle",DataStorage.getDataString()), new TargetPoint(dimID, x, y, z, range));
	}
	
	public static void playSoundOnServer(String sName, double x, double y, double z, double par1, double par2, float range, int dimID)
	{
		DummyData[] datArray = new DummyData[6];
		datArray[0] = new DummyData("name",sName);
		datArray[1] = new DummyData("x",x);
		datArray[2] = new DummyData("y",y);
		datArray[3] = new DummyData("z",z);
		datArray[4] = new DummyData("v",par1);
		datArray[5] = new DummyData("p",par2);
		for(int i = 0; i < 6; ++i)
		{
			DataStorage.addDataToString(datArray[i]);
		}
		TerraArts.network.sendToAllAround(getPacketFor("TA.Sound",DataStorage.getDataString()), new TargetPoint(dimID, x, y, z, range));
	}
	
	public static void changePositionOnServer(double x, double y, double z, EntityPlayer e)
	{
		DummyData[] datArray = new DummyData[3];
		datArray[0] = new DummyData("x",x);
		datArray[1] = new DummyData("y",y);
		datArray[2] = new DummyData("z",z);
		for(int i = 0; i < 3; ++i)
		{
			DataStorage.addDataToString(datArray[i]);
		}
		TerraArts.network.sendTo(getPacketFor("TA.Move",DataStorage.getDataString()),(EntityPlayerMP) e);
	}
	
	public static void sendRenderRequestToServer(double x, double y, double z, int dimension, EntityPlayer p, int currentRenderID)
	{
		DummyData[] datArray = new DummyData[6];
		datArray[0] = new DummyData("x",x);
		datArray[1] = new DummyData("y",y);
		datArray[2] = new DummyData("z",z);
		datArray[3] = new DummyData("dim",dimension);
		datArray[4] = new DummyData("username",p.getCommandSenderName());
		datArray[5] = new DummyData("render",currentRenderID);
		for(int i = 0; i < 6; ++i)
		{
			DataStorage.addDataToString(datArray[i]);
		}
		TerraArts.network.sendToServer(getPacketFor("TA.RenderRequest",DataStorage.getDataString()));
	}
	
	public static TAPacketIMSG getPacketFor(String packetName, String packetData)
	{
		return new TAPacketIMSG(packetData).setType(packetName);
	}


}
