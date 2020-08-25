package com.atomuze.torchrism.config;

import com.atomuze.torchrism.TorchrismMod;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

@Mod.EventBusSubscriber(modid = TorchrismMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TorchrismConfig {

    public static class Common {

        public final ForgeConfigSpec.IntValue offset;

        public Common(ForgeConfigSpec.Builder builder) {
            builder.comment("Torchrism Mod Configurations")
                    .push("torchrism");

            offset = builder
                    .comment("The interval between each torch.")
                    .translation("torchrism.configgui.offset")
                    .worldRestart()
                    .defineInRange("offset", 4, 0, 8);

            builder.pop();
        }
    }

    public static final ForgeConfigSpec COMMON_SPEC;
    public static final Common COMMON;
    static {
        final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }

    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading event) {

    }

    @SubscribeEvent
    public static void onFileChange(final ModConfig.Reloading event) {

    }
}