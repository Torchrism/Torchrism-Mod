package net.atomuze.torchrism.blocks;

import net.atomuze.torchrism.blocks.blockentity.BlockEntityTorchCorrector;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BlockTorchCorrector extends Block implements BlockEntityProvider {

   public static PlayerEntity player;

    public BlockTorchCorrector(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        BlockTorchCorrector.player = player;
        final BlockEntityTorchCorrector bE = (BlockEntityTorchCorrector) world.getBlockEntity(pos);
        if (bE != null) {
            bE.toggleActive();
        }

        BlockEntity blockEntity = world.getBlockEntity(pos);

        if (blockEntity instanceof BlockEntityTorchCorrector) {
            blockEntity.setLocation(world, pos);
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockView world) {
        return new BlockEntityTorchCorrector();
    }
}