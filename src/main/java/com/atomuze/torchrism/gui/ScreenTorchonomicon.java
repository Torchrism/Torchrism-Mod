package com.atomuze.torchrism.gui;

import java.net.IDN;
import java.util.List;
import java.util.function.Predicate;

import org.lwjgl.opengl.GL11;

import com.atomuze.torchrism.TorchrismMod;
import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;

import it.unimi.dsi.fastutil.booleans.BooleanConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.WorldServer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ScreenTorchonomicon extends Screen {
	   protected final ITextComponent field_201550_f;
	   private final List<String> field_201553_i = Lists.newArrayList();
	   protected final String field_201551_g;
	   private int field_201549_s;

	   public ScreenTorchonomicon(Runnable p_i48623_1_, ITextComponent p_i48623_2_, ITextComponent p_i48623_3_) {
	      this(p_i48623_2_, p_i48623_3_, "gui.back");
	   }

	   public ScreenTorchonomicon(ITextComponent p_i49786_2_, ITextComponent p_i49786_3_, String p_i49786_4_) {
	      super(p_i49786_2_);
	      this.field_201550_f = p_i49786_3_;
	      this.field_201551_g = I18n.format(p_i49786_4_);
	      System.out.println("addScheduledTask");
	   }

	   protected void init() {
	      super.init();
	      this.field_201553_i.clear();
	      this.field_201553_i.addAll(this.font.listFormattedStringToWidth(this.field_201550_f.getFormattedText(), this.width - 50));
	   }

	   public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
	      this.renderBackground();
	      this.drawCenteredString(this.font, this.title.getFormattedText(), this.width / 2, 70, 16777215);
	      int i = 90;

	      for(String s : this.field_201553_i) {
	         this.drawCenteredString(this.font, s, this.width / 2, i, 16777215);
	         i += 9;
	      }

	      super.render(p_render_1_, p_render_2_, p_render_3_);
	   }

	   public void tick() {
	      super.tick();
	      if (--this.field_201549_s == 0) {
	         for(Widget widget : this.buttons) {
	            widget.active = true;
	         }
	      }

	   }
//	private static final ResourceLocation BG = new ResourceLocation(TorchrismMod.MODID,"textures/gui/torchonomicon_bg.png");
//	public static ResourceLocation BookPicture = null;
//	private Button exit, menu, nextPage, PreviousPage, overview, utilities, altar, ch1, ch2, ch3, ch4, blocks;
//	private int crrBG;
//	private int branch = 0;
//	private int crrPage = 1;
//
//	private boolean ButtonMenu = false;
//	private boolean ButtonNextPage = false;
//	private boolean ButtonPreviousPage = false;
//	private boolean ButtonOverview = true;
//	private boolean ButtonUtilities = true;
//	private boolean ButtonAltar = true;
//	private boolean ButtonBlock = true;
//
//	private int PagesMenu = 1;
//	private int totalPages = 1;
////	private int bookWidth = 175;
////	private int bookHeight = 209;
//	private int bookWidth = 146;
//	private int bookHeight = 180;
//
//	protected String modid = TorchrismMod.MODID;
//
//	
//	
//	public void renderDirtBackground(int p_renderDirtBackground_1_) {
//	      Tessellator tessellator = Tessellator.getInstance();
//	      BufferBuilder bufferbuilder = tessellator.getBuffer();
//	      this.minecraft.getTextureManager().bindTexture(BG);
//	      RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
//	      float f = 32.0F;
//	      bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
//	      bufferbuilder.pos(0.0D, (double)this.height, 0.0D).tex(0.0F, (float)this.height / 32.0F + (float)p_renderDirtBackground_1_).color(64, 64, 64, 255).endVertex();
//	      bufferbuilder.pos((double)this.width, (double)this.height, 0.0D).tex((float)this.width / 32.0F, (float)this.height / 32.0F + (float)p_renderDirtBackground_1_).color(64, 64, 64, 255).endVertex();
//	      bufferbuilder.pos((double)this.width, 0.0D, 0.0D).tex((float)this.width / 32.0F, (float)p_renderDirtBackground_1_).color(64, 64, 64, 255).endVertex();
//	      bufferbuilder.pos(0.0D, 0.0D, 0.0D).tex(0.0F, (float)p_renderDirtBackground_1_).color(64, 64, 64, 255).endVertex();
//	      tessellator.draw();
//	      net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.GuiScreenEvent.BackgroundDrawnEvent(this));
//	   }

//	
//	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
//		this.renderBackground();
////		super.drawScreen(mouseX, mouseY, partialTicks);
////		if(Keyboard.isKeyDown(Keyboard.KEY_E)) {
////			this.mc.player.closeScreen();
////		}
//		
//		String StrText = "";
//		String StrTitle = "";
//		// System.out.println("branch" + branch + " " +"crrPage" + crrPage);
//		// System.out.println("totalPages" + totalPages);
//
//		switch (branch) {
//		case 1:
//			StrText = I18n.format(modid + "." + "book.overview_text");
//			StrTitle = I18n.format(modid + "." + "bookTitle.overview");
//			break;
//		case 2:
//			StrText = I18n.format(modid + "." + "book.utilities_text");
//			StrTitle = I18n.format(modid + "." + "bookTitle.utilities");
//			break;
//		case 3:
//			StrText = I18n.format(modid + "." + "book.altar_text");
//			StrTitle = I18n.format(modid + "." + "bookTitle.altar");
//			break;
//		case 4:
//			StrText = I18n.format(modid + "." + "book.blocks");
//			StrTitle = I18n.format(modid + "." + "bookTitle.blocks");
//			break;
//		default:
//			StrText = "";
//			StrTitle = I18n.format(modid + "." + "bookTitle.menu");
//		}

//		this.drawSplitString(StrText, this.width / 2 - 60, (this.height / 2) - 58, 115, 0.7f, 000000, crrPage);
//		this.drawString(StrTitle, ((this.width / 2) - this.fontRenderer.getStringWidth(StrTitle)) / 2, (this.height / 2 - 80) / 2, 2.0f, 000000);

//	}
//
//	public void drawSplitString(String str, float drawx, float drawy, int wrapWidth, float size, int colour, int crrpage) {
//
//		int i = 0;
//		int Pages = 1;
//		for (String pagetext : str.split("<NP>")) {
//			if (crrpage == Pages) {
//				for (String segment : pagetext.split("<NL>")) {
//					for (String string : fontRenderer.listFormattedStringToWidth(segment, (int) (wrapWidth / size))) {
//						drawString(string, (int) (drawx / size), (int) (drawy / size) + (int) (i * fontRenderer.FONT_HEIGHT * 1.2f), size, colour);
//						i++;
////						if (i * (int) (fontRenderer.FONT_HEIGHT * 1.2f) > (this.height / 2) + 68) {
////							drawx = (this.width / 2) + 9;
////							i = 0;
////						}
//					}
//				}
//			}
//			Pages++;
//		}
//		
//		totalPages = Pages - 1;
//		ButtonUpdate();
//
//	}

	public void drawString(String text, int x, int y, float size, int color) {
		float mSize = (float) Math.pow(size, -1);
		GL11.glScalef(size, size, size);
//		this.fontRenderer.drawString(text, x, y, color);
		GL11.glScalef(mSize, mSize, mSize);
	}

//	@Override
//	public void drawBackground(int tint) {
//		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
//		switch (tint) {
//		case 1:
//			this.mc.getTextureManager().bindTexture(PG);
//			break;
//		default:
//			this.mc.getTextureManager().bindTexture(PG);
//		}
//		this.drawTexturedModalRect((float) (this.width - bookWidth) / 2, (float) (this.height - bookHeight - 10) / 2, 0, 0, bookWidth, bookHeight);
//		// 146x180
//	}

//	@Override
//	public void initGui() {
//
////		this.buttonList.add(this.exit			 = new ButtonTorchonomicon(0	, this.width  / 2 + 56		, this.height / 2 - 90, 37	, 187, 14, 14, ""));
//		this.buttons.add(this.menu = new ButtonTorchonomicon(1, (this.width - 70) / 2, this.height / 2 + 55, 57, 186, 70, 19, "Menu"));
//		this.buttons.add(this.nextPage = new ButtonTorchonomicon(2, (this.width + 92) / 2, this.height / 2 + 55, 6, 186, 14, 19, ""));
//		this.buttons.add(this.PreviousPage = new ButtonTorchonomicon(3, (this.width - 126) / 2, this.height / 2 + 55, 19, 186, 14, 19, ""));
//		this.buttons.add(this.overview = new ButtonTorchonomicon(11, this.width / 2 - 60, this.height / 2 - 60, 57, 186, 70, 19, I18n.format(modid + "." + "bookButton.overview")));
//		this.buttons.add(this.utilities = new ButtonTorchonomicon(12, this.width / 2 - 60, this.height / 2 - 30, 57, 186, 70, 19, I18n.format(modid + "." + "bookButton.utilities")));
//		this.buttons.add(this.altar = new ButtonTorchonomicon(13, this.width / 2 - 60, this.height / 2, 57, 186, 70, 19, I18n.format(modid + "." + "bookButton.altar")));
//		this.buttons.add(this.blocks = new ButtonTorchonomicon(14, this.width / 2 - 60, this.height / 2 + 30, 57, 186, 70, 19, I18n.format(modid + "." + "bookButton.blocks")));
//
//		ButtonUpdate();
//
//	}
//	
//	@Override
//	protected void actionPerformed(Button button) throws IOException {
////		System.out.println("actionPerformed");
//		if (button.enabled) {
//			if (button.id == 0) {
//				this.minecraft.displayGuiScreen((Screen) null);
//				crrPage = 1;
//			} else if (button.id == 1) {
//				branch = 0;
//				ButtonMenu = false;
//				ButtonNextPage = false;
//				ButtonPreviousPage = false;
//				ButtonOverview = true;
//				ButtonUtilities = true;
//				ButtonAltar = true;
//				ButtonBlock = true;
//				crrPage = 1;
//			} else if (button.id == 2) {
//				if (totalPages > crrPage) {
//					crrPage++;
//				}
//			} else if (button.id == 3) {
//				if (crrPage > 1) {
//					crrPage--;
//				}
//			} else if (button.id == 11) {
//				branch = 1;
//				crrPage = 1;
//				totalPages = 1;
//			} else if (button.id == 12) {
//				branch = 2;
//				crrPage = 1;
//				totalPages = 1;
//			} else if (button.id == 13) {
//				branch = 3;
//				crrPage = 1;
//				totalPages = 1;
//			} else if (button.id == 14) {
//				branch = 4;
//				crrPage = 1;
//				totalPages = 1;
//			}
//			ButtonUpdate();
//
//			Random ran = new Random();
//			int r = ran.nextInt(3);
//
////			switch (r) {
////			case 1:
////				minecraft.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(ModSound.ITEM_TORCHONOMICON_CHANGEPAGE_1, 1.0F));
////				break;
////			case 2:
////				minecraft.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(ModSound.ITEM_TORCHONOMICON_CHANGEPAGE_2, 1.0F));
////				break;
////			default:
////				minecraft.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(ModSound.ITEM_TORCHONOMICON_CHANGEPAGE_3, 1.0F));
////			}
//
//		}
//	}
//	
//	private void ButtonUpdate() {
//		if (branch != 0) {
//
//			ButtonOverview = false;
//			ButtonUtilities = false;
//			ButtonAltar = false;
//			ButtonBlock = false;
//			ButtonMenu = true;
//
//			if (totalPages == crrPage) {
//				ButtonNextPage = false;
//			} else {
//				ButtonNextPage = true;
//			}
//			if (1 == crrPage) {
//				ButtonPreviousPage = false;
//			} else {
//				ButtonPreviousPage = true;
//			}
//		}
//
//		this.menu.visible = ButtonMenu;
//		this.nextPage.visible = ButtonNextPage;
//		this.PreviousPage.visible = ButtonPreviousPage;
//		this.overview.visible = ButtonOverview;
//		this.utilities.visible = ButtonUtilities;
//		this.altar.visible = ButtonAltar;
//		this.blocks.visible = ButtonBlock;
//
//	}
}