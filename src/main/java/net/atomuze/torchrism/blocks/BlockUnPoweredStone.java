package net.atomuze.torchrism.blocks;

import net.atomuze.torchrism.items.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockUnPoweredStone extends Block {
    public BlockUnPoweredStone(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (player.getStackInHand(hand).getItem() == ModItems.TORCH_STAFF) {
			if(world.getBlockState(pos.down()).getBlock() == Blocks.TORCH) {
				world.setBlockState(pos, ModBlocks.POWER_INFUSED_STONE.getDefaultState());
			}
			return ActionResult.SUCCESS;
		}
        return ActionResult.FAIL;
    }
}
