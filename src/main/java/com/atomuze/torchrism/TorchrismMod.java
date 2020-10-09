package com.atomuze.torchrism;

import com.atomuze.torchrism.blocks.ModBlocks;
import com.atomuze.torchrism.config.TorchrismConfig;
import com.atomuze.torchrism.items.ModItems;
import com.atomuze.torchrism.tileentity.ModTileEntity;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(TorchrismMod.MODID)
public class TorchrismMod {
	public static final String MODID = "torchrism";
	public static final String NAME = "Torchrism";
	public static final String VERSION = "1.1";
	// Directly reference a log4j logger.
//	private static final Logger LOGGER = LogManager.getLogger();

	public TorchrismMod() {
	//	LOGGER.log(null, "Torchrism run");
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, TorchrismConfig.COMMON_SPEC, "torchrism.toml");

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

		ModBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
		ModItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		ModTileEntity.TILE_ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());

		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setup(final FMLCommonSetupEvent event) {
	}

	private void doClientStuff(final FMLClientSetupEvent event) {
	}

	// Custom ItemGroup TAB
	public static final ItemGroup TAB = new ItemGroup("torchrismTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(ModItems.torchPlacer_Item.get());
		}
	};

}
