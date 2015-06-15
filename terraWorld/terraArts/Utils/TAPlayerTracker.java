package terraWorld.terraArts.Utils;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import terraWorld.terraArts.Common.Inventory.ContainerArtifacts;
import terraWorld.terraArts.Common.Inventory.InventoryArtifacts;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class TAPlayerTracker{
	
	public static final Logger logger = LogManager.getLogger();
	
	public File createFilesFor(File f)
	{
		logger.debug("Setting up player dat file of file "+f);
		try 
		{
			if(f.exists() && f.isFile())
			{
				logger.debug(" *File found and exist, no modifications needed");
			}else
			{
				if(f.exists())
				{
					throw new IOException("File"+f+" is a directory?");
				}else
				{
					logger.debug(" *File does not exists, creating new...");
					if(f.createNewFile())
						logger.debug(" *Success");
					else
						logger.debug(" *Failure");
				}
			}
				
			return f;
		} 
		catch (Exception e) 
		{
			logger.error(" *Error creating file "+f, e);
			return f;
		}finally
		{
			logger.debug("Finished setting up file "+f);
		}
		
	}
	
	public InventoryArtifacts createInventoryFor(EntityPlayer player, String username)
	{
		InventoryArtifacts iarts = new InventoryArtifacts(player);
		TAUtils.playerInvTable.put(username, iarts);
		TAUtils.playerInvContainer.put(username, new ContainerArtifacts(player.inventory, player.worldObj.isRemote, player));
		return iarts;
	}
	
	public void loadInventoryFromFile(File file,InventoryArtifacts iarts) throws IOException
	{
		FileInputStream stream = new FileInputStream(file);
		try 
		{
			
			NBTTagCompound compressedTag = CompressedStreamTools.readCompressed(stream);
			if(iarts != null)
				iarts.readFromNBT(compressedTag);
			stream.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			stream.close();
		}
	}

	@SubscribeEvent
	public void onPlayerFileLoaded(PlayerEvent.LoadFromFile event)
	{
			String username = event.entityPlayer.getDisplayName();
			File loadedPlayerDirectory = event.playerDirectory;
			File playerNBTFile = new File(loadedPlayerDirectory.getAbsolutePath()+"//TerraArtsData_"+username+".dat");
			
			InventoryArtifacts ia = createInventoryFor(event.entityPlayer,username);
			if(!event.entityPlayer.worldObj.isRemote)
			{
				try
				{
					loadInventoryFromFile(createFilesFor(playerNBTFile), ia);
				}
				catch(Exception e)
				{
					logger.fatal(" *File does not exists even after attempting to create it! Please, read the log above this message to find out, what went wrong! DO NOT report this message only, report the log ABOVE! Reporting THIS message and a crash AFTER it WILL BE IGNORED!!!");
					e.printStackTrace();
				}
			}
	}

	@SubscribeEvent
	public void onPlayerFileSaved(PlayerEvent.SaveToFile event)
	{
		try {
			EntityPlayer player = event.entityPlayer;
			if(!player.worldObj.isRemote)
			{
				File loadedPlayerDirectory = event.playerDirectory;
				File playerNBTFile = createFilesFor(new File(loadedPlayerDirectory.getAbsolutePath()+"//TerraArtsData_"+player.getCommandSenderName()+".dat"));
				
				try {
					FileOutputStream stream = new FileOutputStream(playerNBTFile);
					InventoryArtifacts iarts = (InventoryArtifacts) TAUtils.playerInvTable.get(player.getCommandSenderName());
					if(iarts != null)
					{
						NBTTagCompound tag = new NBTTagCompound();
						iarts.writeToNBT(tag);
						CompressedStreamTools.writeCompressed(tag, stream);
					}
					stream.flush();
					stream.close();
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

}
