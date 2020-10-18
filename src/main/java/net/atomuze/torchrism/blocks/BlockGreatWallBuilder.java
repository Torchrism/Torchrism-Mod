package net.atomuze.torchrism.blocks;


import net.atomuze.torchrism.blocks.blockentity.BlockEntityGreatWallBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BlockGreatWallBuilder extends Block implements BlockEntityProvider {

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public BlockGreatWallBuilder(AbstractBlock.Settings settings) {
        super(settings);
        setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        final BlockEntityGreatWallBuilder bE = (BlockEntityGreatWallBuilder) world.getBlockEntity(pos);
        if (bE != null) {
            bE.toggleActive();
        }

        BlockEntity blockEntity = world.getBlockEntity(pos);

        if (blockEntity instanceof BlockEntityGreatWallBuilder) {
            blockEntity.setLocation(world, pos);
        }

        return ActionResult.SUCCESS;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(Properties.HORIZONTAL_FACING);
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing());
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockView world) {
        return new BlockEntityGreatWallBuilder();
    }
}