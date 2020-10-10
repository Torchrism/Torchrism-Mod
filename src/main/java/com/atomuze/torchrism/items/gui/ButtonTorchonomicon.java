package com.atomuze.torchrism.items.gui;


import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;

public class ButtonTorchonomicon //extends GuiButton {
	{
//	private static final ResourceLocation BUTTON_TEXTURES_T = new ResourceLocation(Torchrism.MODID, "textures/gui/torchonomicon_bg.png");
//	private int textx,texty;
//	private int widthIn;
//	private int heightIn;
//	
//	public ButtonTorchonomicon(int buttonId, int x, int y, int textx, int texty, int widthIn, int heightIn, String buttonText) {
//		super(buttonId, x, y, widthIn, heightIn, buttonText);
//        this.x = x;
//        this.y = y;
//        this.widthIn = widthIn;
//        this.heightIn = heightIn;
//        this.textx = textx;
//        this.texty = texty;
//        this.displayString = buttonText;
//	}
//	
//	private void drawTexture(int xCoord, int yCoord, int minU, int minV, int maxU, int maxV) {
//		this.drawTexturedModalRect(xCoord, yCoord, minU, minV, maxU, maxV);
//	}
//	
//	@Override
//	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
//		if (this.visible) {
//			FontRenderer fontrenderer = mc.fontRenderer;
//			mc.getTextureManager().bindTexture(BUTTON_TEXTURES_T);
//			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
//			this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
//			int i = this.getHoverState(this.hovered);
//			GlStateManager.enableBlend();
//			GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
//			GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
//			drawTexture(this.x, this.y, this.textx, this.texty, this.widthIn, this.heightIn);
////			drawTexture(this.x, this.y, 0, 46 + i * 20, this.width / 2, this.height);
////			drawTexture(this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + i * 20, this.width / 2, this.height);
//			this.mouseDragged(mc, mouseX, mouseY);
//			int j = 14737632;
//
//			if (packedFGColour != 0) {
//				j = packedFGColour;
//			} else if (!this.enabled) {
//				j = 10526880;
//			} else if (this.hovered) {
//				j = 16777120;
//			}
//
//			this.drawCenteredString(fontrenderer, this.displayString, this.x + this.width / 2, this.y + (this.height - 8) / 2, j);
//		}
//	}
//
//	@Override
//	public void playPressSound(SoundHandler soundHandlerIn) {
//		
//	}
}
