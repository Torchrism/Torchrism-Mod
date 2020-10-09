package com.atomuze.torchrism.blocks;

import com.atomuze.torchrism.tileentity.ModTileEntity;
import com.atomuze.torchrism.tileentity.TileEntityTorchCorrector;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class BlockTorchCorrector extends Block {

   public static PlayerEntity player;

    public BlockTorchCorrector() {
        super(Block.Properties.create(Material.WOOD)
                .hardnessAndResistance(2.0f, 6.0f)
                .sound(SoundType.WOOD)
                .harvestLevel(2)
                .harvestTool(ToolType.AXE)
                .lightValue(15));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
    	return ModTileEntity.torchCorrector.get().create();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        BlockTorchCorrector.player = player;
        final TileEntityTorchCorrector tileEntity = (TileEntityTorchCorrector) world.getTileEntity(pos);
        if (tileEntity != null) {

            tileEntity.toggleActive();
        }

        TileEntity tileentity = world.getTileEntity(pos);

        if (tileentity instanceof TileEntityTorchCorrector) {
        	tileentity.setWorldAndPos(world, pos);
        }

        return ActionResultType.SUCCESS;
    }
}
