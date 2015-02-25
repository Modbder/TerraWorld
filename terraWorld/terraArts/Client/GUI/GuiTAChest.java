package terraWorld.terraArts.Client.GUI;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerDispenser;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import terraWorld.terraArts.Common.Inventory.ContainerChest;
import terraWorld.terraArts.Common.Tile.TileEntityTAChest;

@SideOnly(Side.CLIENT)
public class GuiTAChest extends GuiContainer
{
    private static final ResourceLocation dispenserGuiTextures = new ResourceLocation("textures/gui/container/dispenser.png");
    public TileEntityTAChest theDispenser;

    public GuiTAChest(InventoryPlayer par1InventoryPlayer, TileEntityTAChest par2TileEntityDispenser)
    {
        super(new ContainerChest(par1InventoryPlayer, par2TileEntityDispenser));
        this.theDispenser = par2TileEntityDispenser;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        String s = "Chest";
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(dispenserGuiTextures);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }
}
