package net.atomuze.torchrism.blocks;

import net.atomuze.torchrism.TorchrismMod;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {
	 //blocks
    public static final Block TORCH_PLACER = new Block(FabricBlockSettings.of(Material.WOOD).hardness(3.0f).resistance(4.0f));
    
    public static void register() {
        Registry.register(Registry.BLOCK, new Identifier("torchrism", "torch_placer"), TORCH_PLACER);
        Registry.register(Registry.ITEM, new Identifier("torchrism", "torch_placer"), new BlockItem(TORCH_PLACER, new Item.Settings().group(TorchrismMod.ITEM_GROUP)));

    }
    
}
