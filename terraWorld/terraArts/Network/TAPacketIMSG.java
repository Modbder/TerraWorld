package terraWorld.terraArts.Network;

import net.minecraft.nbt.NBTTagCompound;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class TAPacketIMSG implements IMessage{
	
	public NBTTagCompound syncedNBT;
	private int type;
	public String dummyData;
	public String packetType;
	
	public TAPacketIMSG setType(String type)
	{
		packetType = type;
		return this;
	}
	
	public TAPacketIMSG(String data)
	{
		type = 0;
		dummyData = data;
	}
	
	public TAPacketIMSG(NBTTagCompound tag)
	{
		type = 1;
		syncedNBT = tag;
	}
	
	public TAPacketIMSG()
	{
		
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		packetType = ByteBufUtils.readUTF8String(buf);
		type = ByteBufUtils.readVarShort(buf);
		if(type == 0)
			dummyData = ByteBufUtils.readUTF8String(buf);
		if(type == 1)
			syncedNBT = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, packetType);
		ByteBufUtils.writeVarShort(buf, type);
		if(type == 0)
			ByteBufUtils.writeUTF8String(buf, dummyData);
		if(type == 1)
			ByteBufUtils.writeTag(buf, syncedNBT);
	}

}
