package com.atomuze.torchrism.blocks;

import com.atomuze.torchrism.config.TorchrismConfig;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class BlockTorchPlacer extends Block {

    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    public BlockTorchPlacer() {
        super(AbstractBlock.Properties.create(Material.WOOD)
                .hardnessAndResistance(3.0f, 4.0f)
                .sound(SoundType.WOOD)
                .harvestLevel(2)
                .harvestTool(ToolType.AXE)
                .func_235838_a_((blockState) -> 15));

    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {

        int offset = TorchrismConfig.COMMON.offset.get()+1;


        //ModConfig.getTorchOffset();

        // Relative to Torch Placer
        int placerPosX = pos.getX() - offset * 8;
        int placePosY = 255;
        int placerPosZ = pos.getZ() - offset * 8;

        if (!world.isRemote) {
            int giveBackToPlayerCount = 256;
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
            for (int placePosX = placerPosX; placePosX < placerPosX + 16 * offset; placePosX = placePosX + offset) {
                for (int placePosZ = placerPosZ; placePosZ < placerPosZ + 16 * offset; placePosZ = placePosZ + offset) {
                    for (placePosY = 255; placePosY >= 0; placePosY--) {
                        BlockPos PlacePos = new BlockPos(placePosX, placePosY, placePosZ);

                        Material m = world.getBlockState(PlacePos).getMaterial();
                        Material mDown = world.getBlockState(PlacePos.down()).getMaterial();
                        state = world.getBlockState(PlacePos.down());

                        if(mDown == Material.WATER){
                            break;
                        }else if (mDown.isReplaceable() && mDown != Material.AIR){
                            world.setBlockState(PlacePos.down(), Blocks.AIR.getDefaultState());
                            continue;
                        }else if (state.isSolid() && m == Material.AIR) {
                            world.setBlockState(PlacePos, Blocks.TORCH.getDefaultState());
                            giveBackToPlayerCount--;
                            break;
                        }
                    }
                }
            }
            player.addItemStackToInventory(new ItemStack(Blocks.TORCH, giveBackToPlayerCount));
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
