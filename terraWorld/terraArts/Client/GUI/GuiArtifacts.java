package terraWorld.terraArts.Client.GUI;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import terraWorld.terraArts.Common.Inventory.ContainerArtifacts;

@SideOnly(Side.CLIENT)
public class GuiArtifacts extends GuiContainer
{
    private static final ResourceLocation craftingTableGuiTextures = new ResourceLocation("terraarts","textures/gui/arts.png");
    public static EntityPlayer player;

    public GuiArtifacts(InventoryPlayer par1InventoryPlayer)
    {
        super(new ContainerArtifacts(par1InventoryPlayer, true, par1InventoryPlayer.player));
        this.allowUserInput = true;
        player = par1InventoryPlayer.player;
    }

    /**
     * Draw the foreground layer for the GuiContainer  (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        this.fontRendererObj.drawString("Accessories", 58, 6, 4210752);
        this.fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(craftingTableGuiTextures);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }
}
