package net.atomuze.torchrism.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class BlockTorchPlacer extends Block {

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public BlockTorchPlacer(Settings settings) {
        super(settings);
        setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }
 
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
    	int offset = 5; //TorchrismConfig.COMMON.offset.get()+1;

        // Relative to Torch Placer
        int placerPosX = pos.getX() - offset * 8;
        int placePosY = 255;
        int placerPosZ = pos.getZ() - offset * 8;

        if (!world.isClient) {
            int giveBackToPlayerCount = 256;
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
            for (int placePosX = placerPosX; placePosX < placerPosX + 16 * offset; placePosX = placePosX + offset) {
                for (int placePosZ = placerPosZ; placePosZ < placerPosZ + 16 * offset; placePosZ = placePosZ + offset) {
                    for (placePosY = 255; placePosY >= 0; placePosY--) {
                        BlockPos PlacePos = new BlockPos(placePosX, placePosY, placePosZ);

                        Material m = world.getBlockState(PlacePos).getMaterial();
                        Material mDown = world.getBlockState(PlacePos.down()).getMaterial();
                        state = world.getBlockState(PlacePos.down());
                        if(mDown == Material.WATER || mDown == Material.LAVA){
                            break;
                        }else if (mDown.isReplaceable() && mDown != Material.AIR){
                            world.setBlockState(PlacePos.down(), Blocks.AIR.getDefaultState());
                            continue;
                        }else if (sideCoversSmallSquare(world, PlacePos.down(), Direction.UP) && m == Material.AIR) {
                            world.setBlockState(PlacePos, Blocks.TORCH.getDefaultState());
                            giveBackToPlayerCount--;
                            break;
                        }
                    }
                }
            }
            BlockTorchCorrector.player.giveItemStack(new ItemStack(Blocks.TORCH, giveBackToPlayerCount));
        }
        return ActionResult.SUCCESS;
    }


    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(Properties.HORIZONTAL_FACING);
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState().with(FACING, ctx.getPlayerFacing());
    }
}