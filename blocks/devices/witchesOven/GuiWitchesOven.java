package Sncwy.WitcheryUpdated.blocks.devices.witchesOven;

import Sncwy.WitcheryUpdated.init.ModBlocks;
import Sncwy.WitcheryUpdated.util.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiWitchesOven extends GuiContainer {

	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/gui/witches_oven.png");
	private InventoryPlayer player;
	private TileEntityWitchesOven tileEntity;
	
	public GuiWitchesOven(Container container, InventoryPlayer player, TileEntityWitchesOven tileEntity) {
		super(container);
		this.player = player;
		this.tileEntity = tileEntity;
	}
	
	@Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) { //displays the gui
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) { //writes Witches' Oven and Inventory on the gui
		String name = I18n.format(ModBlocks.WITCHES_OVEN.getUnlocalizedName() + ".name");
		fontRenderer.drawString(name, this.xSize / 2 - fontRenderer.getStringWidth(name) / 2, 6, 0x404040);
		fontRenderer.drawString(player.getDisplayName().getUnformattedText(), 8, this.ySize - 94, 0x404040);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) { //displays Items placed in slots as well as the
		drawDefaultBackground();																 //the fire animation and progress while something
		GlStateManager.color(1, 1, 1, 1);														 //is smelting in the oven
		mc.getTextureManager().bindTexture(TEXTURES);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);

		this.drawTexturedModalRect(this.guiLeft + 56, this.guiTop + 36, 176, 0, 14, 14);
		if (TileEntityWitchesOven.isBurning(tileEntity)) {
			this.drawTexturedModalRect(this.guiLeft + 56, this.guiTop + 36, 56, 36, 15, 12 - this.getBurnScale(14));
		}
		else {
			this.drawTexturedModalRect(this.guiLeft + 56, this.guiTop + 36, 56, 36, 15, 15);
		}
		
		this.drawTexturedModalRect(this.guiLeft + 79, this.guiTop + 21, 176, 14, this.getCookProgressScaled(24) + 1, 16);
	}
	
	private int getBurnScale(int pixels) {
		int i = tileEntity.getBurnTime();

		return i / pixels;
	}
	
	private int getCookProgressScaled(int pixels) {
		int i = tileEntity.getCookTime();
		int j = tileEntity.getTotalCookTime(tileEntity.getSmelting());
		return j != 0 && i != 0 ? i * pixels / j : 0; 
	}
}
