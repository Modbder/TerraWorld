package terraWorld.terraBuffs;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import DummyCore.Client.GuiCommon;
import DummyCore.Utils.DataStorage;
import DummyCore.Utils.DummyData;
import DummyCore.Utils.MiscUtils;

public class BT_GuiAnvil extends GuiCommon{

	public BT_GuiAnvil(Container c, TileEntity tile) {
		super(c, tile);
	}

    public void initGui()
    {
        super.initGui();
	    int k = (this.width - this.xSize) / 2;
	    int l = (this.height - this.ySize) / 2;
	    this.buttonList.add(new GuiButton(0,k+48,l+42,80,20,"Reforge"));
    }
    
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
    {
    	boolean reforgable = this.isItemReforgable();
    	GuiButton btn = (GuiButton) this.buttonList.get(0);
    	btn.enabled = reforgable;
    	GL11.glPushMatrix();
    	GL11.glTranslatef(0, 0, 1);
	    int k = (this.width - this.xSize) / 2;
	    int l1 = (this.height - this.ySize) / 2;
	    this.fontRendererObj.drawString("Reforging Anvil", k+52, l1+4, 4210752);
	    MiscUtils.bindTexture("minecraft", "textures/entity/experience_orb.png");
	    GL11.glPushMatrix();
	    GL11.glScalef(0.25F, 0.25F, 0.25F);
        float f10 = 255.0F;
        float f11 = ((float)Minecraft.getMinecraft().thePlayer.ticksExisted + p_73863_3_) / 2.0F;
        float l = (int)((MathHelper.sin(f11 + 0.0F) + 1.0F) * 0.5F * f10);
        float i1 = (int)f10;
        float j1 = (int)((MathHelper.sin(f11 + 4.1887903F) + 1.0F) * 0.1F * f10);
        
        if(reforgable)
	    GL11.glColor3f(l/256, i1/256, j1/256);
        else
        GL11.glColor3f(l/896, i1/896, j1/896);
	    this.drawTexturedModalRect((k+50)*4, (l1+22)*4, 0, 64, 64, 64);
	    GL11.glPopMatrix();
	    
	    if(this.genericTile != null)
	    {
	    	BT_TileAnvil anvil = (BT_TileAnvil) this.genericTile;
	    	if(anvil.getStackInSlot(1) != null)
	    	{
	    		this.fontRendererObj.drawStringWithShadow("Full Output!", k+65, l1+26, 0xff4444);
	    	}else
		    	if(anvil.getStackInSlot(0) == null)
		    	{
		    		this.fontRendererObj.drawStringWithShadow("No item!", k+70, l1+26, 0xff4444);
		    	}else
		    	{
		    		int k1 = (int)l << 16 | (int)i1 << 8 | (int)j1;
		    		int reqExp = this.getReforgeCost(anvil.getStackInSlot(0));
		    		if(reforgable)
		    			this.fontRendererObj.drawStringWithShadow(reqExp+" Levels", k+70, l1+26, k1);
		    		else
		    			this.fontRendererObj.drawStringWithShadow(reqExp+" Levels", k+70, l1+26, 0xff4444);
		    	}
	    	
	    }
	    GL11.glPopMatrix();
	    GL11.glColor3f(1, 1, 1);
	    super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
    }
    
    
    public boolean isItemReforgable()
    {
    	if(this.genericTile != null)
    	{
    		BT_TileAnvil anvil = (BT_TileAnvil) this.genericTile;
    		if(anvil.getStackInSlot(0) != null && anvil.getStackInSlot(1) == null)
    		{
    			if(BT_Utils.isItemBuffable(anvil.getStackInSlot(0)))
    			{
    				int cost = getReforgeCost(anvil.getStackInSlot(0));
    				EntityPlayer player = Minecraft.getMinecraft().thePlayer;
    				if(player.experienceLevel >= cost || player.capabilities.isCreativeMode)
    					return true;
    			}
    		}
    	}
    	return false;
    }
    
    public int getReforgeCost(ItemStack stk)
    {
    	if(!BT_Utils.itemHasEffect(stk))return 10;
    	NBTTagCompound primalTag = MiscUtils.getStackTag(stk);
    	NBTTagCompound tag = primalTag.getCompoundTag("BT_TagList");
    	if(tag.hasKey("BT_Buffs"))
    	{
    		DummyData[] data = DataStorage.parseData(tag.getString("BT_Buffs"));
    		return data.length*5;
    	}
    	return 0;
    }
    
    protected void actionPerformed(GuiButton par1GuiButton) 
    {
    	super.actionPerformed(par1GuiButton);
    	EntityPlayer player = Minecraft.getMinecraft().thePlayer;
    	if(!player.capabilities.isCreativeMode)
    		player.experienceLevel-=this.getReforgeCost(((BT_TileAnvil) this.genericTile).getStackInSlot(0));
    	player.worldObj.playAuxSFX(1021, this.genericTile.xCoord, this.genericTile.yCoord, this.genericTile.zCoord, 0);
    	MiscUtils.handleButtonPress(par1GuiButton.id, this.getClass(), par1GuiButton.getClass(), this.mc.thePlayer, this.genericTile.xCoord, this.genericTile.yCoord, this.genericTile.zCoord,"||xpCost:"+this.getReforgeCost(((BT_TileAnvil) this.genericTile).getStackInSlot(0)));
    }
}
