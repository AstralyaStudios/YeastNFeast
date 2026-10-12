package net.astralya.yeastnfeast.block.custom;

import net.astralya.yeastnfeast.particle.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class MapleLeavesBlock extends LeavesBlock {
    public MapleLeavesBlock(Properties properties) {
        super(properties);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        super.animateTick(state, world, pos, random);
        if (random.nextInt(10) == 0) {
            BlockPos blockPos = pos.below();
            BlockState blockState = world.getBlockState(blockPos);
            if (!Block.isFaceFull(blockState.getCollisionShape(world, blockPos), Direction.UP)) {
                world.addParticle(ModParticles.MAPLE_LEAVES.get(), pos.getX() + random.nextDouble(), pos.getY(), pos.getZ() + random.nextDouble(), 0.0, 0.0, 0.0);
            }
        }
    }
}