package com.atomuze.torchrism;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.RangeInt;

@Config(modid = Torchrism.MODID, name = Torchrism.MODID)
public class ModConfig
{
    @Name("Torch Offset")
    @Comment("The interval between each torch")
    @RangeInt(min = 0,max = 8) 
    public static int offset = 4;
    
    public static int getTorchOffset() {
    	return offset+1;
    }
    
    @Name("Entity Flying Torch ID")
    @Comment("Entity IDs below 201 are used by Minecraft")
    @RangeInt(min = 201,max = 999) 
    public static int flyingTorchId = 201;
    
    public static int getflyingTorchId() {
    	return flyingTorchId;
    }
}
