package com.atomuze.torchrism;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;

@Config(modid = TorchrismMod.MODID, name = TorchrismMod.MODID)
public class ModConfig
{
    @Name("Torch Offset")
    @Comment("The space between the torch")
    public static int offset = 4;
    
    public static int getTorchOffset() {
    	return offset+1;
    }
}
