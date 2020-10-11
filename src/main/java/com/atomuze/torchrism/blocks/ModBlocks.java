package com.atomuze.torchrism.blocks;

import com.atomuze.torchrism.TorchrismMod;

import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, TorchrismMod.MODID);

    public static final RegistryObject<Block> torchPlacer = BLOCKS.register("torch_placer", BlockTorchPlacer::new);
    public static final RegistryObject<Block> compactedTorch = BLOCKS.register("compacted_torch", BlockCompactedTorch::new);
    public static final RegistryObject<Block> doubleCompactedTorch = BLOCKS.register("double_compacted_torch", BlockDoubleCompactedTorch::new);
    public static final RegistryObject<Block> waterTorch = BLOCKS.register("water_torch", BlockWaterTorch::new);
    public static final RegistryObject<Block> wallWaterTorch = BLOCKS.register("wall_water_torch", BlockWallWaterTorch::new);
    public static final RegistryObject<Block> torchDice = BLOCKS.register("torch_dice", BlockTorchDice::new);
    public static final RegistryObject<Block> torchCorrector = BLOCKS.register("torch_corrector", BlockTorchCorrector::new);

    public static final RegistryObject<Block> pillarTop = BLOCKS.register("pillar_top", BlockPillarTop::new);
    public static final RegistryObject<Block> pillar = BLOCKS.register("pillar", BlockPillar::new);
    public static final RegistryObject<Block> pillarBottom = BLOCKS.register("pillar_bottom", BlockPillarBottom::new);
    public static final RegistryObject<Block> sunMoonBlock = BLOCKS.register("sun_moon_block", BlockSunMoon::new);
    public static final RegistryObject<Block> sunMoonBlock_night = BLOCKS.register("sun_moon_block_night", BlockSunMoon::new);

    public static final RegistryObject<Block> greatWallBuilder = BLOCKS.register("great_wall_builder", BlockGreatWallBuilder::new);
    public static final RegistryObject<Block> illuminateBlock = BLOCKS.register("illuminate_block", BlockIlluminate::new);
    public static final RegistryObject<Block> wall = BLOCKS.register("wall", BlockWall::new);
    public static final RegistryObject<Block> wallLight = BLOCKS.register("wall_light", BlockWallLight::new);
    public static final RegistryObject<Block> poweredStone = BLOCKS.register("powered_stone", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 4.0f).harvestLevel(2).harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> chiseledPoweredStone = BLOCKS.register("chiseled_powered_stone", () -> new Block(Block.Properties.from(poweredStone.get())));
    public static final RegistryObject<Block> powerInfusedStone = BLOCKS.register("power_infused_stone", BlockPowerInfusedStone::new);
    public static final RegistryObject<Block> unPoweredStone = BLOCKS.register("un_powered_stone", BlockUnPoweredStone::new);
    public static final RegistryObject<Block> poweredStoneStair = BLOCKS.register("powered_stone_stairs", () -> new StairsBlock(() ->  poweredStone.get().getDefaultState(), Block.Properties.from(poweredStone.get())));
    public static final RegistryObject<Block> poweredStoneSlab = BLOCKS.register("powered_stone_slab",  () -> new SlabBlock(Block.Properties.from(poweredStone.get())));

}
