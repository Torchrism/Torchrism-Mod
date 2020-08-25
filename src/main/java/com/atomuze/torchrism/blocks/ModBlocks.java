package com.atomuze.torchrism.blocks;

import com.atomuze.torchrism.TorchrismMod;
import com.atomuze.torchrism.blocks.torch.CompactedTorch;
import com.atomuze.torchrism.blocks.torch.DoubleCompactedTorch;
import com.atomuze.torchrism.blocks.torchPlacer.TorchPlacer;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, TorchrismMod.MODID);

    public static final RegistryObject<Block> torchPlacer = BLOCKS.register("torch_placer", TorchPlacer::new);
    public static final RegistryObject<Block> compactedTorch = BLOCKS.register("compacted_torch", CompactedTorch::new);
    public static final RegistryObject<Block> doubleCompactedTorch = BLOCKS.register("double_compacted_torch", DoubleCompactedTorch::new);
}
