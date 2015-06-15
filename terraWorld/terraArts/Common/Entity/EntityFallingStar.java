package terraWorld.terraArts.Common.Entity;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityFallingStar extends EntityThrowable{

    public EntityFallingStar(World par1World)
    {
        super(par1World);
        this.setSize(0.25F, 0.25F);
    }
	
	public EntityFallingStar(World par1World,
			EntityLivingBase par2EntityLivingBase) {
		super(par1World, par2EntityLivingBase);
	}
	
	
	
	 public void onUpdate()
	 {
		 super.onUpdate();
		 this.motionY -= 0.1D;
		 if(this.worldObj.rand.nextFloat() < 0.2F)
			 this.worldObj.playSound(posX, posY, posZ, "fireworks.launch", 10, 0.1F, true);
		 List<EntityLivingBase> elb = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(posX-2, posY-2, posZ-2, posX+2, posY+2, posZ+2));
		 if(!elb.isEmpty())
		 {
			 for(int i = 0; i < elb.size(); ++i)
			 {
				 EntityLivingBase base = elb.get(i);
				 if(base.equals(getThrower()))
				 {
					 
				 }else
				 {
					 base.attackEntityFrom(DamageSource.causeMobDamage(base), 5);
				 }
			 }
		 }
	 }

	@Override
	protected void onImpact(MovingObjectPosition movingobjectposition) {
		this.setDead();
	}

}
