package terraWorld.terraArts.Common.Item;

import DummyCore.Utils.MathUtils;
import DummyCore.Utils.MiscUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockIce;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class ItemArtifact_JellyfishNeclase extends ItemArtifact{

	public ItemArtifact_JellyfishNeclase() {
		super();
	}
	
	@Override
	public void onArtUpdate(ItemStack par1ItemStack, EntityPlayer p) {
		Block b = p.worldObj.getBlock((int)p.posX, (int)p.posY+1, (int)p.posZ);
		if(p.isInsideOfMaterial(Material.water))
		{
			p.addPotionEffect(new PotionEffect(Potion.nightVision.id,20,0,true));
			
		}else
		{
			
		}
	}
	
}
