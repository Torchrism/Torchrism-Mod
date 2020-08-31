package com.atomuze.torchrism.item.torchonomicon;

import java.io.IOException;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.atomuze.torchrism.Torchrism;
import com.atomuze.torchrism.sound.ModSound;

import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class GuiTorchonomicon extends GuiScreen {
	private static final ResourceLocation PG = new ResourceLocation(Torchrism.MODID, "textures/gui/torchonomicon_bg.png");
	private GuiButton exit, menu, nextPage, PreviousPage, overview, utilities, altar, ch1, ch2, ch3, ch4, blocks;
	private int crrBG;
	private int branch = 0;
	private int crrPage = 1;

	private boolean ButtonMenu = false;
	private boolean ButtonNextPage = false;
	private boolean ButtonPreviousPage = false;
	private boolean ButtonOverview = true;
	private boolean ButtonUtilities = true;
	private boolean ButtonAltar = true;
	private boolean ButtonBlock = true;

	private int PagesMenu = 1;
	private int totalPages = 1;
	private int bookWidth = 175;
	private int bookHeight = 209;
	

	protected String modid = Torchrism.MODID;

	String StringOverView = I18n.format(modid + "." + "book.overview_text");
	String StringuUilities = I18n.format(modid + "." + "book.utilities_text");
	String StringAltar = I18n.format(modid + "." + "book.altar_text");
	String StringBlocks = I18n.format(modid + "." + "book.blocks");

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawBackground(crrBG);
		super.drawScreen(mouseX, mouseY, partialTicks);
//		if(Keyboard.isKeyDown(Keyboard.KEY_E)) {
//			this.mc.player.closeScreen();
//		}
		
		
		String StrText = "";
		String StrTitle = "";
		// System.out.println("branch" + branch + " " +"crrPage" + crrPage);
		// System.out.println("totalPages" + totalPages);

		switch (branch) {
		case 1:
			StrText = StringOverView;
			StrTitle = I18n.format(modid + "." + "bookTitle.overview");
			break;
		case 2:
			StrText = StringuUilities;
			StrTitle = I18n.format(modid + "." + "bookTitle.utilities");
			break;
		case 3:
			StrText = StringAltar;
			StrTitle = I18n.format(modid + "." + "bookTitle.altar");
			break;
		case 4:
			StrText = StringBlocks;
			StrTitle = I18n.format(modid + "." + "bookTitle.blocks");
			break;
		default:
			StrText = "";
			StrTitle = I18n.format(modid + "." + "bookTitle.menu");
		}

		this.drawSplitString(StrText, this.width / 2 - 60, (this.height / 2) - 58, 115, 0.7f, 000000, crrPage);
		this.drawString(StrTitle, ((this.width / 2) - this.fontRenderer.getStringWidth(StrTitle)) / 2, (this.height / 2 - 80) / 2, 2.0f, 000000);

	}

	public void drawSplitString(String str, float drawx, float drawy, int wrapWidth, float size, int colour, int crrpage) {

		int i = 0;
		int Pages = 1;
		for (String pagetext : str.split("<NP>")) {
			if (crrpage == Pages) {
				for (String segment : pagetext.split("<NL>")) {
					for (String string : fontRenderer.listFormattedStringToWidth(segment, (int) (wrapWidth / size))) {
						drawString(string, (int) (drawx / size), (int) (drawy / size) + (int) (i * fontRenderer.FONT_HEIGHT * 1.2f), size, colour);
						i++;
//						if (i * (int) (fontRenderer.FONT_HEIGHT * 1.2f) > (this.height / 2) + 68) {
//							drawx = (this.width / 2) + 9;
//							i = 0;
//						}
					}
				}
			}
			Pages++;
		}

		totalPages = Pages - 1;
//		System.out.println("totalPages" + totalPages);
		ButtonUpdate();

	}

	public void drawString(String text, int x, int y, float size, int color) {
		float mSize = (float) Math.pow(size, -1);
		GL11.glScalef(size, size, size);
		this.fontRenderer.drawString(text, x, y, color);
		GL11.glScalef(mSize, mSize, mSize);
	}

	@Override
	public void drawBackground(int tint) {
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		switch (tint) {
		case 1:
			this.mc.getTextureManager().bindTexture(PG);
			break;
		default:
			this.mc.getTextureManager().bindTexture(PG);
		}
		this.drawTexturedModalRect((float) (this.width - bookWidth) / 2, (float) (this.height - bookHeight - 10) / 2, 0, 0, bookWidth, bookHeight);
		// 146x180
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	public void initGui() {

//		this.buttonList.add(this.exit			 = new ButtonTorchonomicon(0	, this.width  / 2 + 56		, this.height / 2 - 90, 37	, 187, 14, 14, ""));
		this.buttonList.add(this.menu = new ButtonTorchonomicon(1, (this.width - 70) / 2, this.height / 2 + 55, 57, 186, 70, 19, "Menu"));
		this.buttonList.add(this.nextPage = new ButtonTorchonomicon(2, (this.width + 92) / 2, this.height / 2 + 55, 6, 186, 14, 19, ""));
		this.buttonList.add(this.PreviousPage = new ButtonTorchonomicon(3, (this.width - 126) / 2, this.height / 2 + 55, 19, 186, 14, 19, ""));
		this.buttonList.add(this.overview = new ButtonTorchonomicon(11, this.width / 2 - 60, this.height / 2 - 60, 57, 186, 70, 19, I18n.format(modid + "." + "bookButton.overview")));
		this.buttonList.add(this.utilities = new ButtonTorchonomicon(12, this.width / 2 - 60, this.height / 2 - 30, 57, 186, 70, 19, I18n.format(modid + "." + "bookButton.utilities")));
		this.buttonList.add(this.altar = new ButtonTorchonomicon(13, this.width / 2 - 60, this.height / 2, 57, 186, 70, 19, I18n.format(modid + "." + "bookButton.altar")));
		this.buttonList.add(this.blocks = new ButtonTorchonomicon(14, this.width / 2 - 60, this.height / 2 + 30, 57, 186, 70, 19, I18n.format(modid + "." + "bookButton.blocks")));

		ButtonUpdate();

	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {

		if (button.enabled) {
			if (button.id == 0) {
				this.mc.displayGuiScreen((GuiScreen) null);
				crrPage = 1;
			} else if (button.id == 1) {
				branch = 0;
				ButtonMenu = false;
				ButtonNextPage = false;
				ButtonPreviousPage = false;
				ButtonOverview = true;
				ButtonUtilities = true;
				ButtonAltar = true;
				ButtonBlock = true;
				crrPage = 1;
			} else if (button.id == 2) {
				if (totalPages > crrPage) {
					crrPage++;
				}
			} else if (button.id == 3) {
				if (crrPage > 1) {
					crrPage--;
				}
			} else if (button.id == 11) {
				branch = 1;
				crrPage = 1;
				totalPages = 1;
			} else if (button.id == 12) {
				branch = 2;
				crrPage = 1;
				totalPages = 1;
			} else if (button.id == 13) {
				branch = 3;
				crrPage = 1;
				totalPages = 1;
			} else if (button.id == 14) {
				branch = 4;
				crrPage = 1;
				totalPages = 1;
			}
			ButtonUpdate();

			Random ran = new Random();
			int r = ran.nextInt(3);

			switch (r) {
			case 1:
				mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(ModSound.ITEM_TORCHONOMICON_CHANGEPAGE_1, 1.0F));
				break;
			case 2:
				mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(ModSound.ITEM_TORCHONOMICON_CHANGEPAGE_2, 1.0F));
				break;
			default:
				mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(ModSound.ITEM_TORCHONOMICON_CHANGEPAGE_3, 1.0F));
			}

		}
	}

	private void ButtonUpdate() {
		if (branch != 0) {

			ButtonOverview = false;
			ButtonUtilities = false;
			ButtonAltar = false;
			ButtonBlock = false;
			ButtonMenu = true;

			if (totalPages == crrPage) {
				ButtonNextPage = false;
			} else {
				ButtonNextPage = true;
			}
			if (1 == crrPage) {
				ButtonPreviousPage = false;
			} else {
				ButtonPreviousPage = true;
			}
		}

		this.menu.visible = ButtonMenu;
		this.nextPage.visible = ButtonNextPage;
		this.PreviousPage.visible = ButtonPreviousPage;
		this.overview.visible = ButtonOverview;
		this.utilities.visible = ButtonUtilities;
		this.altar.visible = ButtonAltar;
		this.blocks.visible = ButtonBlock;

	}
}