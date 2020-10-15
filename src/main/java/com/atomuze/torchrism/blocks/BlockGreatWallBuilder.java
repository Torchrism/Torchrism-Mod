package com.atomuze.torchrism.blocks;


import com.atomuze.torchrism.tileentity.ModTileEntity;
import com.atomuze.torchrism.tileentity.TileEntityGreatWallBuilder;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class BlockGreatWallBuilder extends Block {

    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    public BlockGreatWallBuilder() {
        super(AbstractBlock.Properties.create(Material.WOOD)
                .hardnessAndResistance(3.0f, 4.0f)
                .sound(SoundType.WOOD)
                .harvestLevel(1)
                .harvestTool(ToolType.AXE)
                .func_235838_a_((blockState) -> 15));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
    	return ModTileEntity.greatWallBuilder.get().create();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        System.out.println("onBlockActivated");
        final TileEntityGreatWallBuilder tileEntity = (TileEntityGreatWallBuilder) world.getTileEntity(pos);
        if (tileEntity != null) {
            tileEntity.toggleActive();
        }

        TileEntity tileentity = world.getTileEntity(pos);

        if (tileentity instanceof TileEntityGreatWallBuilder) {
            tileentity.setWorldAndPos(world, pos);
        }

        return ActionResultType.SUCCESS;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing());
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}