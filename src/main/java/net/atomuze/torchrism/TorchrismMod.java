package net.atomuze.torchrism;

import net.atomuze.torchrism.blocks.ModBlocks;
import net.atomuze.torchrism.items.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class TorchrismMod implements ModInitializer {
	
   
    @Override
    public void onInitialize() {
        ModBlocks.register();
        ModItems.register();
    } 
    
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
    		new Identifier("torchrism", "general"),
    		() -> new ItemStack(ModBlocks.TORCH_PLACER));
}
