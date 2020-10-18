package net.atomuze.torchrism.blocks;

import net.atomuze.torchrism.TorchrismMod;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {
	
	private final static String mod_id = TorchrismMod.MOD_ID;
	
    public static final BlockTorchPlacer TORCH_PLACER = new BlockTorchPlacer(FabricBlockSettings.of(Material.WOOD).lightLevel(15).requiresTool().strength(3f, 4f));
    public static final BlockCompactedTorch COMPACTED_TORCH = new BlockCompactedTorch(FabricBlockSettings.copyOf(Blocks.TORCH));
    public static final BlockDoubleCompactedTorch DOUBLE_COMPACTED_TORCH = new BlockDoubleCompactedTorch(FabricBlockSettings.copyOf(Blocks.TORCH));
    public static final BlockWaterTorch WATER_TORCH = new BlockWaterTorch(FabricBlockSettings.of(Material.WOOD).lightLevel(15).collidable(false));
    public static final BlockWallWaterTorch WALL_WATER_TORCH = new BlockWallWaterTorch(FabricBlockSettings.copyOf(WATER_TORCH));
    public static final BlockTorchDice TORCH_DICE = new BlockTorchDice(FabricBlockSettings.of(Material.WOOD).requiresTool().strength(1f, 4f));
    public static final BlockTorchCorrector TORCH_CORRECTOR = new BlockTorchCorrector(FabricBlockSettings.copyOf(TORCH_PLACER));
    
    public static final BlockPillarTop PILLAR_TOP = new BlockPillarTop(FabricBlockSettings.copyOf(Blocks.QUARTZ_BLOCK));
    public static final BlockPillar PILLAR = new BlockPillar(FabricBlockSettings.copyOf(Blocks.QUARTZ_BLOCK));
    public static final BlockPillarBottom PILLAR_BOTTOM = new BlockPillarBottom(FabricBlockSettings.copyOf(Blocks.QUARTZ_BLOCK).lightLevel(15));
    public static final BlockSunMoon SUN_MOON_BLOCK = new BlockSunMoon(FabricBlockSettings.copyOf(Blocks.QUARTZ_BLOCK).lightLevel(15));
    public static final BlockSunMoon SUN_MOON_BLOCK_NIGHT = new BlockSunMoon(FabricBlockSettings.copyOf(Blocks.QUARTZ_BLOCK));
    
    public static final BlockGreatWallBuilder GREAT_WALL_BUILDER = new BlockGreatWallBuilder(FabricBlockSettings.copyOf(TORCH_PLACER));
    public static final Block ILLUMINATE_BLOCK = new Block(FabricBlockSettings.copyOf(SUN_MOON_BLOCK));
    public static final BlockWall WALL = new BlockWall(FabricBlockSettings.copyOf(TORCH_PLACER).lightLevel(0));
    public static final BlockWallLight WALL_LIGHT = new BlockWallLight(FabricBlockSettings.copyOf(SUN_MOON_BLOCK));
    public static final Block POWERED_STONE = new Block(FabricBlockSettings.copyOf(Blocks.QUARTZ_BLOCK));
    public static final Block CHISELED_POWERED_STONE = new Block(FabricBlockSettings.copyOf(Blocks.QUARTZ_BLOCK));
    public static final Block POWER_INFUSED_STONE = new Block(FabricBlockSettings.copyOf(SUN_MOON_BLOCK));
    public static final BlockUnPoweredStone UN_POWERED_STONE = new BlockUnPoweredStone(FabricBlockSettings.copyOf(Blocks.QUARTZ_BLOCK));
    public static final BlockPowerStoneStair POWERED_STONE_STAIR = new BlockPowerStoneStair(POWERED_STONE.getDefaultState(),FabricBlockSettings.copyOf(Blocks.QUARTZ_BLOCK));
    public static final SlabBlock POWERED_STONE_SLABS = new SlabBlock(FabricBlockSettings.copyOf(Blocks.QUARTZ_BLOCK));

    public static void register() {
        Registry.register(Registry.BLOCK, new Identifier(mod_id, "torch_placer"), TORCH_PLACER);
        Registry.register(Registry.BLOCK, new Identifier(mod_id, "compacted_torch"), COMPACTED_TORCH);
        Registry.register(Registry.BLOCK, new Identifier(mod_id, "double_compacted_torch"), DOUBLE_COMPACTED_TORCH);
        Registry.register(Registry.BLOCK, new Identifier(mod_id, "water_torch"), WATER_TORCH);
        Registry.register(Registry.BLOCK, new Identifier(mod_id, "wall_water_torch"), WALL_WATER_TORCH);
        Registry.register(Registry.BLOCK, new Identifier(mod_id, "torch_dice"), TORCH_DICE);
        Registry.register(Registry.BLOCK, new Identifier(mod_id, "torch_corrector"), TORCH_CORRECTOR);
        
        Registry.register(Registry.BLOCK, new Identifier(mod_id, "pillar_top"), PILLAR_TOP);
        Registry.register(Registry.BLOCK, new Identifier(mod_id, "pillar"), PILLAR);
        Registry.register(Registry.BLOCK, new Identifier(mod_id, "pillar_bottom"), PILLAR_BOTTOM);
        Registry.register(Registry.BLOCK, new Identifier(mod_id, "sun_moon_block"), SUN_MOON_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(mod_id, "sun_moon_block_night"), SUN_MOON_BLOCK_NIGHT);
        
        Registry.register(Registry.BLOCK, new Identifier(mod_id, "great_wall_builder"), GREAT_WALL_BUILDER);
        Registry.register(Registry.BLOCK, new Identifier(mod_id, "illuminate_block"), ILLUMINATE_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(mod_id, "wall"), WALL);
        Registry.register(Registry.BLOCK, new Identifier(mod_id, "wall_light"), WALL_LIGHT);
        Registry.register(Registry.BLOCK, new Identifier(mod_id, "powered_stone"), POWERED_STONE);
        Registry.register(Registry.BLOCK, new Identifier(mod_id, "chiseled_powered_stone"), CHISELED_POWERED_STONE);
        Registry.register(Registry.BLOCK, new Identifier(mod_id, "power_infused_stone"), POWER_INFUSED_STONE);
        Registry.register(Registry.BLOCK, new Identifier(mod_id, "un_powered_stone"), UN_POWERED_STONE);
        Registry.register(Registry.BLOCK, new Identifier(mod_id, "powered_stone_stairs"), POWERED_STONE_STAIR);
        Registry.register(Registry.BLOCK, new Identifier(mod_id, "powered_stone_slab"), POWERED_STONE_SLABS);
        
        
        //BlockItems
        Registry.register(Registry.ITEM, new Identifier(mod_id, "torch_placer"), new BlockItem(TORCH_PLACER, new Item.Settings().group(TorchrismMod.ITEM_GROUP)));
        Registry.register(Registry.ITEM, new Identifier(mod_id, "compacted_torch"), new BlockItem(COMPACTED_TORCH, new Item.Settings().group(TorchrismMod.ITEM_GROUP)));
        Registry.register(Registry.ITEM, new Identifier(mod_id, "double_compacted_torch"), new BlockItem(DOUBLE_COMPACTED_TORCH, new Item.Settings().group(TorchrismMod.ITEM_GROUP)));
        Registry.register(Registry.ITEM, new Identifier(mod_id, "torch_dice"), new BlockItem(TORCH_DICE, new Item.Settings().group(TorchrismMod.ITEM_GROUP)));
        Registry.register(Registry.ITEM, new Identifier(mod_id, "torch_corrector"), new BlockItem(TORCH_CORRECTOR, new Item.Settings().group(TorchrismMod.ITEM_GROUP)));
        
        Registry.register(Registry.ITEM, new Identifier(mod_id, "pillar_top"), new BlockItem(PILLAR_TOP, new Item.Settings().group(TorchrismMod.ITEM_GROUP)));
        Registry.register(Registry.ITEM, new Identifier(mod_id, "pillar"), new BlockItem(PILLAR, new Item.Settings().group(TorchrismMod.ITEM_GROUP)));
        Registry.register(Registry.ITEM, new Identifier(mod_id, "pillar_bottom"), new BlockItem(PILLAR_BOTTOM, new Item.Settings().group(TorchrismMod.ITEM_GROUP)));
        Registry.register(Registry.ITEM, new Identifier(mod_id, "sun_moon_block"), new BlockItem(SUN_MOON_BLOCK, new Item.Settings().group(TorchrismMod.ITEM_GROUP)));
        
        Registry.register(Registry.ITEM, new Identifier(mod_id, "great_wall_builder"), new BlockItem(GREAT_WALL_BUILDER, new Item.Settings().group(TorchrismMod.ITEM_GROUP)));
        Registry.register(Registry.ITEM, new Identifier(mod_id, "illuminate_block"), new BlockItem(ILLUMINATE_BLOCK, new Item.Settings().group(TorchrismMod.ITEM_GROUP)));
        Registry.register(Registry.ITEM, new Identifier(mod_id, "wall"), new BlockItem(WALL, new Item.Settings().group(TorchrismMod.ITEM_GROUP)));
        Registry.register(Registry.ITEM, new Identifier(mod_id, "powered_stone"), new BlockItem(POWERED_STONE, new Item.Settings().group(TorchrismMod.ITEM_GROUP)));
        Registry.register(Registry.ITEM, new Identifier(mod_id, "chiseled_powered_stone"), new BlockItem(CHISELED_POWERED_STONE, new Item.Settings().group(TorchrismMod.ITEM_GROUP)));
        Registry.register(Registry.ITEM, new Identifier(mod_id, "un_powered_stone"), new BlockItem(UN_POWERED_STONE, new Item.Settings().group(TorchrismMod.ITEM_GROUP)));
        Registry.register(Registry.ITEM, new Identifier(mod_id, "powered_stone_stairs"), new BlockItem(POWERED_STONE_STAIR, new Item.Settings().group(TorchrismMod.ITEM_GROUP)));
        Registry.register(Registry.ITEM, new Identifier(mod_id, "powered_stone_slab"), new BlockItem(POWERED_STONE_SLABS, new Item.Settings().group(TorchrismMod.ITEM_GROUP)));

    }
    
    

   
}
