package com.atomuze.torchrism.blocks.torch;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.ToolType;

public class CompactedTorch extends Block {
    public static final AxisAlignedBB CompactedTorchAABB = new AxisAlignedBB(0.375, 0, 0.375, 0.625, 0.8125, 0.625);
    VoxelShape shape1 = VoxelShapes.create(CompactedTorchAABB);

    public CompactedTorch() {
        super(Block.Properties.create(Material.WOOD)
                .hardnessAndResistance(1.0f, 6.0f)
                .sound(SoundType.WOOD)
                .harvestLevel(2)
                .harvestTool(ToolType.AXE)
                .lightValue(15));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        // TODO Auto-generated method stub
        return shape1;
    }


    @Override
    public VoxelShape getRenderShape(BlockState state, IBlockReader worldIn, BlockPos pos)
    {
        return shape1;
    }


    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos,
                                        ISelectionContext context) {
        return shape1;
    }
}
