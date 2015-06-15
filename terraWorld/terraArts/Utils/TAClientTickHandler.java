package terraWorld.terraArts.Utils;

import java.util.EnumSet;

import org.lwjgl.input.Keyboard;

import terraWorld.terraArts.Mod.TerraArts;
import terraWorld.terraArts.Network.TAPacketHandler;
import terraWorld.terraArts.Network.Proxy.ClientProxy;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import DummyCore.Utils.DataStorage;
import DummyCore.Utils.DummyData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class TAClientTickHandler
{

	public static boolean isKeyPressed = false;
	public static boolean isJumpPressed = false;
	
	@SubscribeEvent
	public void tickStart(TickEvent.PlayerTickEvent event) 
	{
		
		if(event.player.worldObj.isRemote)
		{
			if(event.phase == Phase.START)
			{
				Minecraft mc = Minecraft.getMinecraft();
				World w = mc.theWorld;
				EntityPlayer player = mc.thePlayer;
				if(w != null && player != null && (mc.currentScreen == null))
				{
					if(Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindJump.getKeyCode()))
					{
						DummyData a0 = new DummyData("username",player.getCommandSenderName());
						DataStorage.addDataToString(a0);
						String dataString = DataStorage.getDataString();
						TerraArts.network.sendToServer(TAPacketHandler.getPacketFor("TA.JHold", dataString));
						TAUtils.onPlayerHoldJump(player);
					}
					if(!isJumpPressed && Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindJump.getKeyCode()) && !player.onGround && player.worldObj.isRemote)
					{
						isJumpPressed = true;
						DummyData a0 = new DummyData("username",player.getCommandSenderName());
						DataStorage.addDataToString(a0);
						String dataString = DataStorage.getDataString();
						TerraArts.network.sendToServer(TAPacketHandler.getPacketFor("TA.PJump", dataString));
						TAUtils.onPlayerJump(player);
					}
					if(!isKeyPressed && ClientProxy.kbOpenGUI.getIsKeyPressed())
					{
						isKeyPressed = true;
						DummyData a0 = new DummyData("username",player.getCommandSenderName());
						DataStorage.addDataToString(a0);
						String dataString = DataStorage.getDataString();
						TerraArts.network.sendToServer(TAPacketHandler.getPacketFor("TA.Button", dataString));
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void tickEnd(TickEvent.PlayerTickEvent event)
	{
		if(event.player.worldObj.isRemote)
		{
			if(event.phase == Phase.END)
			{
				if(isKeyPressed && !Keyboard.isKeyDown(Keyboard.KEY_L))
				{
					isKeyPressed = false;
				}
				if(isJumpPressed && !Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindJump.getKeyCode()))
				{
					isJumpPressed = false;
				}
				if(!isJumpPressed && Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindJump.getKeyCode()))
				{
					isJumpPressed = true;
				}
			}
		}
	}
}
