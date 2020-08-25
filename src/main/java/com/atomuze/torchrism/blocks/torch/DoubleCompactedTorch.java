package com.atomuze.torchrism.blocks.torch;

import com.atomuze.torchrism.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import java.util.ArrayList;
import java.util.Random;

public class DoubleCompactedTorch extends Block {
    public static final AxisAlignedBB DoubleCompactedTorchAABB = new AxisAlignedBB(0.3125, 0, 0.3125, 0.6875, 1.0, 0.6875);
    VoxelShape shape1 = VoxelShapes.create(DoubleCompactedTorchAABB);

    public DoubleCompactedTorch() {
        super(Properties.create(Material.WOOD)
                .hardnessAndResistance(2.0f, 6.0f)
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
