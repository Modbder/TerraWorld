package terraWorld.terraArts.Common.Item;

import terraWorld.terraArts.Network.TAPacketHandler;
import DummyCore.Utils.MathUtils;
import DummyCore.Utils.MiscUtils;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemArtifact_WaterWalkers extends ItemArtifact{

	public ItemArtifact_WaterWalkers() {
		super();
	}
	
	@Override
	public void onArtUpdate(ItemStack par1ItemStack, EntityPlayer p) {
		Material material = p.worldObj.getBlock((int)p.posX, (int)p.posY-1, (int)p.posZ).getMaterial();
		if(material == Material.water && p.motionY < 0 && !p.isInWater() && !p.isSneaking())
		{
			TAPacketHandler.changePositionOnServer(0, -p.motionY, 0, p);
			p.motionY = 0;
		}
	}

}
