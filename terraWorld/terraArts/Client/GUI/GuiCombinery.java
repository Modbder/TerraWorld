package terraWorld.terraArts.Client.GUI;

import org.lwjgl.opengl.GL11;

import terraWorld.terraArts.API.CombineryRecipe;
import terraWorld.terraArts.API.TAApi;
import terraWorld.terraArts.Common.Tile.TileEntityTACombiner;
import terraWorld.terraBuffs.BT_TileAnvil;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import DummyCore.Client.GuiCommon;
import DummyCore.Utils.MiscUtils;

public class GuiCombinery extends GuiCommon{
	
	public GuiCombinery(Container c, TileEntity tile) {
		super(c, tile);
	}

    public void initGui()
    {
        super.initGui();
	    int k = (this.width - this.xSize) / 2;
	    int l = (this.height - this.ySize) / 2;
	    this.buttonList.add(new GuiButton(0,k+48,l+42,80,20,"Combine"));
    }
    
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
    {
    	boolean canCombine = canItemsBeCombined();
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.zLevel = 1F;
        GL11.glPushMatrix();
        GL11.glTranslatef(0, 0, 1);
        this.fontRendererObj.drawString("Tinker's Table", k+52, l+4, 4210752);
        
	    MiscUtils.bindTexture("minecraft", "textures/entity/experience_orb.png");
	    GL11.glPushMatrix();
	    GL11.glScalef(0.25F, 0.25F, 0.25F);
        float f10 = 255.0F;
        float f11 = ((float)Minecraft.getMinecraft().thePlayer.ticksExisted + p_73863_3_) / 2.0F;
        float l1 = (int)((MathHelper.sin(f11 + 0.0F) + 1.0F) * 0.5F * f10);
        float i1 = (int)f10;
        float j1 = (int)((MathHelper.sin(f11 + 4.1887903F) + 1.0F) * 0.1F * f10);
        
        if(canCombine)
	    GL11.glColor3f(l1/256, i1/256, j1/256);
        else
        GL11.glColor3f(l1/896, i1/896, j1/896);
	    this.drawTexturedModalRect((k+50)*4, (l+64)*4, 0, 64, 64, 64);
		
	    GL11.glPopMatrix();
        
        GL11.glPopMatrix();
        
    	MiscUtils.bindTexture("minecraft", "textures/gui/container/furnace.png");
    	GL11.glColor3f(1, 1, 1);
    
    	this.drawTexturedModalRect(k+56, l+22, 176, 14, 24, 17);
    	
    	int p_73729_1_ = k+98;
    	int p_73729_2_ = l+22;
    	int p_73729_3_ = 176;
    	int p_73729_4_ = 14;
    	int p_73729_5_ = 24;
    	int p_73729_6_ = 17;
    	
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV((double)(p_73729_1_ + 0), (double)(p_73729_2_ + p_73729_6_), (double)this.zLevel, (double)((float)(p_73729_3_ + p_73729_5_) * f), (double)((float)(p_73729_4_ + p_73729_6_) * f1));
        tessellator.addVertexWithUV((double)(p_73729_1_ + p_73729_5_), (double)(p_73729_2_ + p_73729_6_), (double)this.zLevel, (double)((float)(p_73729_3_ + 0) * f), (double)((float)(p_73729_4_ + p_73729_6_) * f1));
        tessellator.addVertexWithUV((double)(p_73729_1_ + p_73729_5_), (double)(p_73729_2_ + 0), (double)this.zLevel, (double)((float)(p_73729_3_ + 0) * f), (double)((float)(p_73729_4_ + 0) * f1));
        tessellator.addVertexWithUV((double)(p_73729_1_ + 0), (double)(p_73729_2_ + 0), (double)this.zLevel, (double)((float)(p_73729_3_ + p_73729_5_) * f), (double)((float)(p_73729_4_ + 0) * f1));
        tessellator.draw();
        
    	RenderHelper.disableStandardItemLighting();
    	RenderHelper.enableGUIStandardItemLighting();
        GL11.glPushMatrix();
        GL11.glTranslatef(0, 0, 1);
        ((GuiButton)this.buttonList.get(0)).enabled = canCombine;
    	if(this.genericTile != null && this.genericTile instanceof TileEntityTACombiner)
    	{
    		TileEntityTACombiner combiner = (TileEntityTACombiner) this.genericTile;
    		if(combiner.getStackInSlot(0) != null && combiner.getStackInSlot(1) != null && combiner.getStackInSlot(2) == null)
    		{
    			CombineryRecipe rec = TAApi.getRecipeByCP(combiner.getStackInSlot(0), combiner.getStackInSlot(1));
    			if(rec != null)
    			{
    				boolean blendEnabled = GL11.glIsEnabled(GL11.GL_BLEND);
    				GL11.glEnable(GL11.GL_BLEND);
    				GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
    				float timeIndex = Minecraft.getMinecraft().theWorld.getWorldTime() % 20;
    				if(timeIndex >= 10)
    					timeIndex = 10-(Minecraft.getMinecraft().theWorld.getWorldTime() % 20 - 10);
    				GL11.glColor4f(timeIndex/10F, timeIndex/10F, timeIndex/10F, 1F);
    				this.drawItemStack(rec.result, k+81, l+23, "");
    				if(!blendEnabled)
    					GL11.glDisable(GL11.GL_BLEND);
    				
    			    
    				int k1 = (int)l << 16 | (int)i1 << 8 | (int)j1;
    				int reqExp = rec.recCost;
    				if(canCombine)
    					this.fontRendererObj.drawStringWithShadow(reqExp+" Levels", k+70, l+68, k1);
    				else
    					this.fontRendererObj.drawStringWithShadow(reqExp+" Levels", k+70, l+68, 0xff4444);
    			}
    		}
    	}
    	GL11.glColor3f(1, 1, 1);
    	GL11.glPopMatrix();
    	
    	this.zLevel = 0;
    	super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
    	RenderHelper.enableStandardItemLighting();
    }
    
    protected void actionPerformed(GuiButton par1GuiButton) 
    {
    	super.actionPerformed(par1GuiButton);
    	EntityPlayer player = Minecraft.getMinecraft().thePlayer;
    	if(!player.capabilities.isCreativeMode)
    		player.experienceLevel-=this.getCombineCost();
    	for(int i = 0; i < 20; ++i)
    		player.worldObj.playAuxSFX(2001, genericTile.xCoord, genericTile.yCoord, genericTile.zCoord, Block.getIdFromBlock(genericTile.getWorldObj().getBlock(genericTile.xCoord, genericTile.yCoord, genericTile.zCoord)));
    	MiscUtils.handleButtonPress(par1GuiButton.id, this.getClass(), par1GuiButton.getClass(), this.mc.thePlayer, this.genericTile.xCoord, this.genericTile.yCoord, this.genericTile.zCoord,"||xpCost:"+this.getCombineCost());
    }
    
    public boolean canItemsBeCombined()
    {
    	if(this.genericTile != null && this.genericTile instanceof TileEntityTACombiner)
    	{
    		TileEntityTACombiner combiner = (TileEntityTACombiner) this.genericTile;
    		if(combiner.getStackInSlot(0) != null && combiner.getStackInSlot(1) != null && combiner.getStackInSlot(2) == null)
    		{
    			CombineryRecipe rec = TAApi.getRecipeByCP(combiner.getStackInSlot(0), combiner.getStackInSlot(1));
    			if(rec != null)
    			{
    				int cost = rec.recCost;
    				EntityPlayer player = Minecraft.getMinecraft().thePlayer;
    				if(player.experienceLevel >= cost || player.capabilities.isCreativeMode)
    					return true;
    			}
    		}
    	}
    	return false;
    }
    
    public int getCombineCost()
    {
    	if(this.genericTile != null && this.genericTile instanceof TileEntityTACombiner)
    	{
    		TileEntityTACombiner combiner = (TileEntityTACombiner) this.genericTile;
    		if(combiner.getStackInSlot(0) != null && combiner.getStackInSlot(1) != null && combiner.getStackInSlot(2) == null)
    		{
    			CombineryRecipe rec = TAApi.getRecipeByCP(combiner.getStackInSlot(0), combiner.getStackInSlot(1));
    			if(rec != null)
    			{
    				int cost = rec.recCost;
    				return cost;
    			}
    		}
    	}
    	return 0;
    }
    
    private void drawItemStack(ItemStack p_146982_1_, int p_146982_2_, int p_146982_3_, String p_146982_4_)
    {
        FontRenderer font = null;
        if (p_146982_1_ != null) font = p_146982_1_.getItem().getFontRenderer(p_146982_1_);
        if (font == null) font = fontRendererObj;
        itemRender.renderWithColor = false;
        itemRender.renderItemAndEffectIntoGUI(font, this.mc.getTextureManager(), p_146982_1_, p_146982_2_, p_146982_3_);
        itemRender.renderItemOverlayIntoGUI(font, this.mc.getTextureManager(), p_146982_1_, p_146982_2_, p_146982_3_ - 0, p_146982_4_);
        itemRender.renderWithColor = true;
    }
}
